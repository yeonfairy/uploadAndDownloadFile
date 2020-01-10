package com.spring.board.board.util;
import java.util.List;

import com.spring.board.board.model.vo.*;

public class MainApplication {

	public static void main(String[] args) {

		CustomerExcelReader excelReader = new CustomerExcelReader();

		System.out.println("*****xls*****");
		List<Board> xlsList = excelReader.xlsToBoardList("C:\\excel\\ExcelFormDown.xls");
		printList(xlsList);
	}
  
	public static void printList(List<Board> list) {
		Board vo;

		for (int i = 0; i < list.size(); i++) {
			vo = list.get(i);
			System.out.println(vo.toString());
		}
	}
}
