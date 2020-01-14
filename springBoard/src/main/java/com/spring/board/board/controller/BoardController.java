package com.spring.board.board.controller;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor.HSSFColorPredefined;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.spring.board.board.model.service.BoardService;
import com.spring.board.board.model.vo.Board;
import com.spring.board.board.model.vo.InputVo;
@Controller
public class BoardController {

	private static Logger logger = LoggerFactory.getLogger(BoardController.class);
	@Autowired
	private BoardService bService;

	private static List<Board> eList = new ArrayList<Board>();

	@RequestMapping(value = "board.do")
	public String boardView(Model model, Board boardVo) throws Exception {

		ArrayList<Board> bList = bService.selectBoardList();
//		System.out.println("????" + bList);

		model.addAttribute("bList", bList);
		List<Map<String, Object>> fileList = bService.selectFileList(boardVo.getBoardNo());
		model.addAttribute("file", fileList);
//		logger.info("board =================== ");
//		logger.warn("warn!!!!!!");

		return "board/boardView";
	}

	@ResponseBody
	@RequestMapping(value = "excelUploadAjax.do", method = RequestMethod.POST)
	public String excelUploadAjax(Board board, MultipartHttpServletRequest request) throws Exception {
		MultipartFile excelFile = request.getFile("excelFile");
		System.out.println("엑셀 파일 업로드 컨트롤러");
	
		if (excelFile == null || excelFile.isEmpty()) {
			throw new RuntimeException("엑셀파일을 선택 해 주세요.");
		}

		File destFile = new File("D:\\" + excelFile.getOriginalFilename());
		try {
			excelFile.transferTo(destFile);
		} catch (IllegalStateException | IOException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		System.out.println("destFile :" + destFile);
		bService.excelUpload(board, destFile);

	    destFile.delete();

		return "board/excelUploadAjax";
	}
	@ResponseBody
	@RequestMapping(value = "excelUploadTotal.do", method = RequestMethod.POST)
	public String excelUploadTotal(Model model, InputVo inputVo, MultipartHttpServletRequest request) throws Exception {
		ArrayList<InputVo> iList = bService.selectInputList();
		model.addAttribute("iList", iList);
		
		MultipartFile excelFile = request.getFile("excelFile");
		System.out.println("엑셀 파일 업로드 컨트롤러");
	
		if (excelFile == null || excelFile.isEmpty()) {
			throw new RuntimeException("엑셀파일을 선택 해 주세요.");
		}

		File destFile = new File("D:\\" + excelFile.getOriginalFilename());
		try {
			excelFile.transferTo(destFile);
		} catch (IllegalStateException | IOException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		System.out.println("destFile :" + destFile);
		bService.excelUpload2(inputVo, destFile);

	    destFile.delete();

		return "board/boardView";
	}

	// 첨부파일 다운로드
	@RequestMapping(value = "fileDown.do")
	public String fileDown(@RequestParam int boardNo, Board board, HttpServletResponse response,
			MultipartHttpServletRequest mpRequest) throws Exception {
		// System.out.println("fileDown.fileDown::::::::::::::::::::::::::::::::::"+request.getParameter("boardNo"));

		Map<String, Object> resultMap = bService.selectFileInfo(boardNo);
		if (resultMap != null) {

			String storedFileName = (String) resultMap.get("STORED_FILE_NAME");
			String originalFileName = (String) resultMap.get("ORG_FILE_NAME");
			// 파일을 저장했던 위치에서 첨부파일을 읽어 byte[]형식으로 변환한다.
			byte fileByte[] = org.apache.commons.io.FileUtils
					.readFileToByteArray(new File("C:\\work\\upload\\" + originalFileName));

			response.setContentType("application/java-archive"); // jar파일로 다운로드
			response.setContentType("application/octet-stream"); // 다운로드시 다른이름으로 저장이 뜨도록 설정
			response.setContentLength(fileByte.length);
			response.setHeader("Content-Disposition",
					"attachment; fileName=\"" + URLEncoder.encode(originalFileName, "UTF-8") + "\";");
			response.getOutputStream().write(fileByte);
			response.getOutputStream().flush();
			response.getOutputStream().close();
		} else {
			return "board/boardDetail";
		}

		return "board/boardDetail";
	}

	// 엑셀 파일 다운로드
	@RequestMapping(value = "excelDown.do")
	public void excelDown(HttpServletResponse response) throws Exception {
		List<Board> list = bService.selectBoardList();

		// 워크북 생성
		Workbook wb = new HSSFWorkbook();
		Sheet sheet = wb.createSheet("게시판");
		Row row = null;
		Cell cell = null;
		int rowNo = 0;
		// 테이블 헤더용 스타일
		CellStyle headStyle = wb.createCellStyle();
		// 가는 경계선을 가집니다.
		headStyle.setBorderTop(BorderStyle.THIN);
		headStyle.setBorderBottom(BorderStyle.THIN);
		headStyle.setBorderLeft(BorderStyle.THIN);
		headStyle.setBorderRight(BorderStyle.THIN);
		// 배경색은 노란색입니다.
		headStyle.setFillForegroundColor(HSSFColorPredefined.YELLOW.getIndex());
		headStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		// 데이터는 가운데 정렬합니다.
		headStyle.setAlignment(HorizontalAlignment.CENTER);
		// 데이터용 경계 스타일 테두리만 지정
		CellStyle bodyStyle = wb.createCellStyle();
		bodyStyle.setBorderTop(BorderStyle.THIN);
		bodyStyle.setBorderBottom(BorderStyle.THIN);
		bodyStyle.setBorderLeft(BorderStyle.THIN);
		bodyStyle.setBorderRight(BorderStyle.THIN);
		// 헤더 생성
		row = sheet.createRow(rowNo++);
		cell = row.createCell(0);
		cell.setCellStyle(headStyle);
		cell.setCellValue("번호");
		cell = row.createCell(1);
		cell.setCellStyle(headStyle);
		cell.setCellValue("제목");
		cell = row.createCell(2);
		cell.setCellStyle(headStyle);
		cell.setCellValue("내용");
		cell = row.createCell(3);
		cell.setCellStyle(headStyle);
		cell.setCellValue("작성자");
		cell = row.createCell(4);
		cell.setCellStyle(headStyle);
		cell.setCellValue("조회수");
		cell = row.createCell(5);
		cell.setCellStyle(headStyle);
		cell.setCellValue("첨부파일");

		// 데이터 부분 생성
		for (Board vo : list) {
			row = sheet.createRow(rowNo++);
			cell = row.createCell(0);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(vo.getBoardNo());
			cell = row.createCell(1);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(vo.getBoardTitle());
			cell = row.createCell(2);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(vo.getBoardContent());
			cell = row.createCell(3);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(vo.getBoardWriter());
			cell = row.createCell(4);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(vo.getBoardCount());
			cell = row.createCell(5);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(vo.getOrgFileName());
		}
		// 컨텐츠 타입과 파일명 지정
		response.setContentType("ms-vnd/excel");
		response.setHeader("Content-Disposition", "attachment;filename=ExcelFormDown.xls");
		// 엑셀 출력
		wb.write(response.getOutputStream());
		wb.close();

	}

	// 엑셀 파일 다운로드
	@RequestMapping(value = "excelFormDown.do")
	public void excelFormDown(HttpServletResponse response) throws Exception {

		// 워크북 생성
		Workbook wb = new HSSFWorkbook();
		Sheet sheet = wb.createSheet("게시판");
		Row row = null;
		Cell cell = null;
		int rowNo = 0;
		// 테이블 헤더용 스타일
		CellStyle headStyle = wb.createCellStyle();
		// 가는 경계선을 가집니다.
		headStyle.setBorderTop(BorderStyle.THIN);
		headStyle.setBorderBottom(BorderStyle.THIN);
		headStyle.setBorderLeft(BorderStyle.THIN);
		headStyle.setBorderRight(BorderStyle.THIN);
		// 배경색은 노란색입니다.
		headStyle.setFillForegroundColor(HSSFColorPredefined.YELLOW.getIndex());
		headStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		// 데이터는 가운데 정렬합니다.
		headStyle.setAlignment(HorizontalAlignment.CENTER);
		// 데이터용 경계 스타일 테두리만 지정
		CellStyle bodyStyle = wb.createCellStyle();
		bodyStyle.setBorderTop(BorderStyle.THIN);
		bodyStyle.setBorderBottom(BorderStyle.THIN);
		bodyStyle.setBorderLeft(BorderStyle.THIN);
		bodyStyle.setBorderRight(BorderStyle.THIN);
		// 헤더 생성
		row = sheet.createRow(rowNo++);
		cell = row.createCell(0);
		cell.setCellStyle(headStyle);
		cell.setCellValue("번호");
		cell = row.createCell(1);
		cell.setCellStyle(headStyle);
		cell.setCellValue("제목");
		cell = row.createCell(2);
		cell.setCellStyle(headStyle);
		cell.setCellValue("내용");
		cell = row.createCell(3);
		cell.setCellStyle(headStyle);
		cell.setCellValue("작성자");
		cell = row.createCell(4);
		cell.setCellStyle(headStyle);
		cell.setCellValue("조회수");
		cell = row.createCell(5);
		cell.setCellStyle(headStyle);
		cell.setCellValue("비밀번호");

		// 데이터 부분 생성

		// 컨텐츠 타입과 파일명 지정
		response.setContentType("ms-vnd/excel");
		response.setHeader("Content-Disposition", "attachment;filename=ExcelFormDown.xls");
		// 엑셀 출력
		wb.write(response.getOutputStream());
		wb.close();

	}

	// 게시판 등록
	@RequestMapping(value = "insertForm.do", method = RequestMethod.GET)
	public String boardInsertView() {
		return "board/boardInsert";
	}

	@RequestMapping("inputPwd.do")
	public String boardPwdInpur() {
		return "board/pwdSetting";
	}

	@RequestMapping(value = "boardInsert.do", method = RequestMethod.POST)
	public String boardInsert(Board board, MultipartHttpServletRequest mpRequest) throws Exception {
		System.out.println("boardInsert.boardInsert::::::::::::::::::::::::::::::::::" + board);

		bService.insertBoard(board, mpRequest);

		return "redirect:board.do";

	}

	@RequestMapping(value = "inputListInsert.do", method = RequestMethod.POST)
	public String inputListInsert(InputVo inputVo, MultipartHttpServletRequest mpRequest) throws Exception {
		System.out.println("boardInsert.boardInsert::::::::::::::::::::::::::::::::::" + inputVo);

		bService.insertInputList(inputVo, mpRequest);

		return "redirect:excelUploadTotal.do";

	}
	
	@RequestMapping("boardDetail.do")
	public String boardDetail(int bNo, String inputPwd, Model model, Board boardVo, HttpServletRequest request)
			throws Exception {
		Board board = bService.selectBoardOne(bNo);
		String id = request.getParameter("bNo");
		model.addAttribute("board", board);
		List<Map<String, Object>> fileList = bService.selectFileList(bNo);
		model.addAttribute("file", fileList);

		return "board/boardDetail";
	}

	@RequestMapping("checkPwd.do")
	public String checkingPwd(int bNo, Model model) {
		model.addAttribute("bNo", bNo);
		return "board/pwdChecking";
	}

	@ResponseBody
	@RequestMapping("checkcheck.do")
	public String checkcheck(String pwd, int bNo) {
		int result = bService.selectBoardPwd(new Board(bNo, pwd));
		return result + "";
	}

	@ResponseBody
	@RequestMapping("settingpwd.do")
	public String settingPwd(String pwd, int bNo) {
		int result = bService.updateBoardPwd(new Board(bNo, pwd));
		return result + "";
	}

	// 엑셀 업로드 형식
	@RequestMapping(value = "fileUpload.do", method = RequestMethod.GET)
	public String showForm(ModelMap model) {

		return "board/excelUploadAjax";
	}
	// 엑셀 업로드 형식
	@RequestMapping(value = "excelUpload.do", method = RequestMethod.GET)
	public String showForm2(ModelMap model) {

		return "board/excelUploadTotal";
	}
}