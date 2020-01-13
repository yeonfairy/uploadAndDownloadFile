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
	//첨부파일 조회
	public List<Map<String, Object>> selectFileList(int boardNo) throws Exception {
		return bDao.selectFileList(boardNo);
	}
	
	//첨부파일 다운로드
	public Map<String, Object> selectFileInfo(int boardNo) throws Exception {
		Map<String, Object> resultMap = new HashMap<String,Object>();
		return  bDao.selectFileInfo(boardNo);
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