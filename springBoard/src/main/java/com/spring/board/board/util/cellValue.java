package com.spring.board.board.util;

import java.text.SimpleDateFormat;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;

public class cellValue {
	  public String cellValue(Cell cell) {
		  
	        String value = null;
	        if (cell == null) value = "";
	        else {
	            switch (cell.getCellType()) { //cell 타입에 따른 데이타 저장
	            case Cell.CELL_TYPE_FORMULA:
	                value = cell.getCellFormula();
	                break;
	            case Cell.CELL_TYPE_NUMERIC:
	                if (DateUtil.isCellDateFormatted(cell)) {
	                    //you should change this to your application date format
	                    SimpleDateFormat objSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	                    value = "" + objSimpleDateFormat.format(cell.getDateCellValue());
	                } else {
	                    value = "" + String.format("%.0f", new Double(cell.getNumericCellValue()));
	                }
	                break;
	            case Cell.CELL_TYPE_STRING:
	                value = "" + cell.getStringCellValue();
	                break;
	            case Cell.CELL_TYPE_BLANK:
	                //value=""+cell.getBooleanCellValue();
	                value = "";
	                break;
	            case Cell.CELL_TYPE_ERROR:
	                value = "" + cell.getErrorCellValue();
	                break;
	            default:
	            }
	        }
	        
	        return value.trim();
	    }
}
