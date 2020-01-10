package com.spring.board.board.model.vo;

public class FileVo {
	private int fileNo;
	private int boardNo;
	private String orgFileName;
	private String storedFileName;
	private String regDate;
	private String delGb;
	private String fileSize;
	
	@Override
	public String toString() {
		return "File [fileNo=" + fileNo + ", boardNo=" + boardNo + ", orgFileName=" + orgFileName + ", storedFileName="
				+ storedFileName + ", regDate=" + regDate + ", delGb=" + delGb + ", fileSize=" + fileSize + "]";
	}

	public int getFileNo() {
		return fileNo;
	}

	public void setFileNo(int fileNo) {
		this.fileNo = fileNo;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public String getOrgFileName() {
		return orgFileName;
	}

	public void setOrgFileName(String orgFileName) {
		this.orgFileName = orgFileName;
	}

	public String getStoredFileName() {
		return storedFileName;
	}

	public void setStoredFileName(String storedFileName) {
		this.storedFileName = storedFileName;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public String getDelGb() {
		return delGb;
	}

	public void setDelGb(String delGb) {
		this.delGb = delGb;
	}

	public String getFileSize() {
		return fileSize;
	}

	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}

	public FileVo(int fileNo, int boardNo, String orgFileName, String storedFileName, String regDate, String delGb,
			String fileSize) {
		super();
		this.fileNo = fileNo;
		this.boardNo = boardNo;
		this.orgFileName = orgFileName;
		this.storedFileName = storedFileName;
		this.regDate = regDate;
		this.delGb = delGb;
		this.fileSize = fileSize;
	}
}
