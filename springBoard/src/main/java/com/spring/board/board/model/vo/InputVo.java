package com.spring.board.board.model.vo;

public class InputVo {
	private int no;
	private int inputValue1;
	private int inputValue2;
	private int inputValue3;
	private int inputValue4;
	private int inputValue5;
	private int totalValue;

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public int getInputValue1() {
		return inputValue1;
	}

	public void setInputValue1(int inputValue1) {
		this.inputValue1 = inputValue1;
	}

	public int getInputValue2() {
		return inputValue2;
	}

	public void setInputValue2(int inputValue2) {
		this.inputValue2 = inputValue2;
	}

	public int getInputValue3() {
		return inputValue3;
	}

	public void setInputValue3(int inputValue3) {
		this.inputValue3 = inputValue3;
	}

	public int getInputValue4() {
		return inputValue4;
	}

	public void setInputValue4(int inputValue4) {
		this.inputValue4 = inputValue4;
	}

	public int getInputValue5() {
		return inputValue5;
	}

	public void setInputValue5(int inputValue5) {
		this.inputValue5 = inputValue5;
	}

	public int getTotalValue() {
		return totalValue;
	}

	public void setTotalValue(int totalValue) {
		this.totalValue = totalValue;
	}

	@Override
	public String toString() {
		return "InputVo [no=" + no + ", inputValue1=" + inputValue1 + ", inputValue2=" + inputValue2 + ", inputValue3="
				+ inputValue3 + ", inputValue4=" + inputValue4 + ", inputValue5=" + inputValue5 + ", totalValue="
				+ totalValue + "]";
	}

	public InputVo(int no, int inputValue1, int inputValue2, int inputValue3, int inputValue4, int inputValue5,
			int totalValue) {
		super();
		this.no = no;
		this.inputValue1 = inputValue1;
		this.inputValue2 = inputValue2;
		this.inputValue3 = inputValue3;
		this.inputValue4 = inputValue4;
		this.inputValue5 = inputValue5;
		this.totalValue = totalValue;
	}
	public InputVo() {
		// TODO Auto-generated constructor stub
	}
}
