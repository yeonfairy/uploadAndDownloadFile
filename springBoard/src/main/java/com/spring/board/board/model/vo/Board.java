package com.spring.board.board.model.vo;

import java.util.List;

public class Board {
	
	private int boardNo;
	private String boardTitle;
	private String boardContent;
	private String boardWriter;
	private int boardCount;
	private String boardPwd;
	private String orgFileName;

	public Board() {
		// TODO Auto-generated constructor stub
	}

	public String getOrgFileName() {
		return orgFileName;
	}

	public void setOrgFileName(String orgFileName) {
		this.orgFileName = orgFileName;
	}

	public Board(int boardNo, String boardPwd) {
		super();
		this.boardNo = boardNo;
		this.boardPwd = boardPwd;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public String getBoardTitle() {
		return boardTitle;
	}

	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}

	public String getBoardContent() {
		return boardContent;
	}

	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}

	public String getBoardWriter() {
		return boardWriter;
	}

	public void setBoardWriter(String boardWriter) {
		this.boardWriter = boardWriter;
	}

	public int getBoardCount() {
		return boardCount;
	}

	public void setBoardCount(int boardCount) {
		this.boardCount = boardCount;
	}

	public String getBoardPwd() {
		return boardPwd;
	}

	public void setBoardPwd(String boardPwd) {
		this.boardPwd = boardPwd;
	}

	@Override
	public String toString() {
		return "Board [boardNo=" + boardNo + ", boardTitle=" + boardTitle + ", boardContent=" + boardContent
				+ ", boardWriter=" + boardWriter + ", boardCount=" + boardCount + ", boardPwd=" + boardPwd
				+ ", orgFileName=" + orgFileName + "]";
	}

	public Board(int boardNo, String boardTitle, String boardContent, String boardWriter, int boardCount,
			String boardPwd, String orgFileName) {
		super();
		this.boardNo = boardNo;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.boardWriter = boardWriter;
		this.boardCount = boardCount;
		this.boardPwd = boardPwd;
		this.orgFileName = orgFileName;
	}
}