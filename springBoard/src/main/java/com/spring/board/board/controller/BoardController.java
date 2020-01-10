package com.spring.board.board.controller;

import java.io.File;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.spring.board.board.model.service.BoardService;
import com.spring.board.board.model.vo.Board;
import com.spring.board.board.model.vo.FileVo;

@Controller
public class BoardController {
	
	private static Logger logger = LoggerFactory.getLogger(BoardController.class);
	@Autowired
	private BoardService bService;

	@RequestMapping("board.do")
	public String boardView(Model model, Board boardVo) throws Exception{

		ArrayList<Board> bList = bService.selectBoardList();
//		System.out.println("????" + bList);

		model.addAttribute("bList", bList);
		List<Map<String, Object>> fileList = bService.selectFileList(boardVo.getBoardNo());
		model.addAttribute("file", fileList);
//		logger.info("board =================== ");
//		logger.warn("warn!!!!!!");

		return "board/boardView";
	}
	
	//첨부파일 다운로드
	@RequestMapping(value="fileDown.do")
	public String fileDown(@RequestParam int boardNo, HttpServletResponse response) throws Exception{
		//System.out.println("fileDown.fileDown::::::::::::::::::::::::::::::::::"+request.getParameter("boardNo"));

		Map<String, Object> resultMap  = bService.selectFileInfo(boardNo);
		if(resultMap != null) {

		String storedFileName = (String) resultMap.get("STORED_FILE_NAME");
		String originalFileName = (String) resultMap.get("ORG_FILE_NAME");
		// 파일을 저장했던 위치에서 첨부파일을 읽어 byte[]형식으로 변환한다.
		byte fileByte[] = org.apache.commons.io.FileUtils.readFileToByteArray(new File("C:\\work\\upload\\"+originalFileName));
		
		response.setContentType("application/java-archive"); //jar파일로 다운로드
		response.setContentType("application/octet-stream"); // 다운로드시 다른이름으로 저장이 뜨도록 설정
		response.setContentLength(fileByte.length);
		response.setHeader("Content-Disposition",  "attachment; fileName=\""+URLEncoder.encode(originalFileName, "UTF-8")+"\";");
		response.getOutputStream().write(fileByte);
		response.getOutputStream().flush();
		response.getOutputStream().close();
		} else {
			return "board/boardDetail";
		}
		return "board/boardDetail";
	}

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
		System.out.println("boardInsert.boardInsert::::::::::::::::::::::::::::::::::"+board);

	    bService.insertBoard(board, mpRequest);

		return "redirect:board.do";
	
	}

	@RequestMapping("boardDetail.do")
	public String boardDetail(int bNo, String inputPwd, Model model, Board boardVo, HttpServletRequest request) throws Exception{
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
}