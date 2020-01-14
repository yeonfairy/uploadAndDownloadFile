package com.spring.board.board.model.service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.spring.board.board.model.dao.BoardDao;
import com.spring.board.board.model.vo.Board;
import com.spring.board.board.model.vo.InputVo;
import com.spring.board.board.util.ExcelRead;
import com.spring.board.board.util.ExcelRead2;
import com.spring.board.board.util.ExcelReadOption;
import com.spring.board.board.util.FileUtils;

@Service("bService")
public class BoardService {
	@Resource(name="fileUtils")
	private FileUtils fileUtils;
	
	@Autowired
	private BoardDao bDao;

	public ArrayList<Board> selectBoardList() {
		return bDao.selectBoardList();
	}
	
	public void insertBoard(Board boardVO, MultipartHttpServletRequest mpRequest) throws Exception {
		//게시글 추가
		bDao.insertBoard(boardVO);
		//첨부파일 추가
		List<Map<String, Object>> list = fileUtils.parseInsertFileInfo(boardVO, mpRequest);
		int size = list.size();
		for (int i = 0; i < size; i++) {
			bDao.insertBoardFile(list.get(i));
		}
	}
	
	public ArrayList<InputVo> selectInputList() {
		return bDao.selectInputList();
	}
	
	public void insertInputList(InputVo inputVo, MultipartHttpServletRequest mpRequest) throws Exception {
		//게시글 추가
		bDao.insertInputList(inputVo);
	}
	
	//첨부파일 조회
	public List<Map<String, Object>> selectFileList(int boardNo) throws Exception {
		return bDao.selectFileList(boardNo);
	}
	
	//첨부파일 다운로드
	public Map<String, Object> selectFileInfo(int boardNo) throws Exception {
		Map<String, Object> resultMap = new HashMap<String,Object>();
		return  bDao.selectFileInfo(boardNo);
	}
	//엑셀 업로드
    public void excelUpload(Board boardVO, File destFile) throws Exception{
    	List<Board> list = bDao.selectBoardList();
        ExcelReadOption excelReadOption = new ExcelReadOption();
        excelReadOption.setFilePath(destFile.getAbsolutePath());
        excelReadOption.setOutputColumns("A","B","C","D","E","F");
        excelReadOption.setStartRow(2);
        
        
        List<Map<String, String>> excelContent =ExcelRead.read(excelReadOption);
        List<String> elist = new ArrayList();

        for(Map<String, String> article: excelContent){
            System.out.println(article.get("A"));
            boardVO.setBoardNo(Integer.parseInt(article.get("A")));
            System.out.println(article.get("B"));
            boardVO.setBoardTitle(article.get("B"));
            System.out.println(article.get("C"));
            boardVO.setBoardContent(article.get("C"));
            System.out.println(article.get("D"));
            boardVO.setBoardWriter(article.get("D"));
            System.out.println(article.get("E"));
            boardVO.setBoardCount(Integer.parseInt(article.get("E")));
            System.out.println(article.get("F"));
            boardVO.setBoardPwd(article.get("F"));
            bDao.insertBoard(boardVO);
        }
    }
	//엑셀 업로드
    public void excelUpload2(InputVo inputVo, File destFile) throws Exception{
    	List<InputVo> list = bDao.selectInputList();
        ExcelReadOption excelReadOption = new ExcelReadOption();
        excelReadOption.setFilePath(destFile.getAbsolutePath());
        excelReadOption.setOutputColumns("A","B","C","D","E","F","G");
        excelReadOption.setStartRow(2);
        
        
        List<Map<String, String>> excelContent = ExcelRead2.read2(excelReadOption);
        System.out.println("ExcelRead2.read(excelReadOption):" + ExcelRead2.read2(excelReadOption));
        List<String> ilist = new ArrayList();
    
        for(Map<String, String> article: excelContent){
            System.out.println(article.get("A"));
            inputVo.setNo(Integer.parseInt(article.get("A")));
            System.out.println(article.get("B"));
            inputVo.setInputValue1(Integer.parseInt(article.get("B")));
            System.out.println(article.get("C"));
            inputVo.setInputValue2(Integer.parseInt(article.get("C")));
            System.out.println(article.get("D"));
            inputVo.setInputValue3(Integer.parseInt(article.get("D")));
            System.out.println(article.get("E"));
            inputVo.setInputValue4(Integer.parseInt(article.get("E")));
            System.out.println(article.get("F"));
            inputVo.setInputValue5(Integer.parseInt(article.get("F")));
            System.out.println(article.get("G"));
            inputVo.setTotalValue(Integer.parseInt(article.get("G")));
            System.out.println("DDD :"+inputVo.getInputValue1());
            
            bDao.insertInputList(inputVo);
        }
    }
    
	public Board selectBoardOne(int boardNo) {
		Board board = bDao.selectBoardOne(boardNo);
		if (board != null) {
			int result = bDao.updateBoardCount(boardNo);
			if (result > 0)
				board.setBoardCount(board.getBoardCount() + 1);
		}
		return board;
	}
	
	public int selectBoardPwd(Board board) {
		return bDao.selectBoardPwd(board);
	}

	public int updateBoardPwd(Board board) {
		return bDao.updateBoardPwd(board);
	}

}