package com.spring.board.board.model.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.board.board.model.vo.Board;
import com.spring.board.board.model.vo.InputVo;

@Repository("bDao")
public class BoardDao {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	//게시판 조회
	public ArrayList<Board> selectBoardList() {
		return (ArrayList)sqlSession.selectList("boardMapper.selectBoardList");
	}
	//합계 추가 리스트
	public ArrayList<InputVo> selectInputList() {
		return (ArrayList)sqlSession.selectList("inputMapper.selectInputList");
	}
	//게시판 등록
	public int insertBoard(Board board) {
		return sqlSession.insert("boardMapper.insertBoard", board);
	}
	//합계 추가
	public int insertInputList(InputVo inputVo) {
		return sqlSession.insert("inputMapper.insertInputList", inputVo);
	}
    // 첨부파일 조회
	public List<Map<String, Object>> selectFileList(int boardNo) throws Exception{
		return sqlSession.selectList("boardMapper.selectFileList", boardNo);
	}
	//첨부파일 등록
	public void insertBoardFile(Map<String, Object> map) throws Exception {
		sqlSession.insert("boardMapper.insertBoardFile", map);
	}
	//엑셀 업로드
	public int insertExcel(Board board) {
		return sqlSession.insert("boardMapper.insertBoard", board);
	}	
	//엑셀 업로드 2
	public int insertExcel2(InputVo inputVo) {
		return sqlSession.insert("inputMapper.insertInputList", inputVo);
	}		
	// 첨부파일 다운로드
	public Map<String, Object> selectFileInfo(int boardNo) throws Exception {
		return sqlSession.selectOne("boardMapper.selectFileInfo", boardNo);
	}
	//게시판 조회
	public Board selectBoardOne(int boardNo) {
		return sqlSession.selectOne("boardMapper.selectBoardOne", boardNo);
	}
	//게시판 조회수 수정
	public int updateBoardCount(int boardNo) {
		return sqlSession.update("boardMapper.updateBoardCount", boardNo);
	}
	//게시판 비번
	public int selectBoardPwd(Board board) {
		return sqlSession.selectOne("boardMapper.selectBoardPwd", board);
	}
	public int updateBoardPwd(Board board) {
		return sqlSession.update("boardMapper.updateBoardPwd", board);
	}
	public static Object getInstance() {
		// TODO Auto-generated method stub
		return null;
	}
}