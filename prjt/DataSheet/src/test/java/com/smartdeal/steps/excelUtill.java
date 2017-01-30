package com.smartdeal.steps;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class excelUtill {

	public Map getTestData(String workbook, String worksheet, String dataset) throws IOException{
		
		String filepath = "C:\\Users\\srivastavag\\workspace\\DataSheet\\DataExcel\\"+workbook+".xlsx";
		Map<String, String> mapData = new HashMap<String, String>();
		ArrayList<String> keys = new ArrayList<String>();
		ArrayList<String> values = new ArrayList<String>();
		InputStream file=null;
		XSSFWorkbook wb=null;
		XSSFSheet sheet=null;
		XSSFRow row;
		XSSFCell cell;
		
		file = new FileInputStream(filepath);
		wb = new XSSFWorkbook(file);
		sheet = wb.getSheet(worksheet);
		
	
		Iterator rows = sheet.rowIterator();
		//Keys array list
		while(rows.hasNext()){
			try{
			row = (XSSFRow) rows.next();
			keys.add(row.getCell(0).getStringCellValue().trim());
			}catch(NullPointerException e){
				keys.add("NA");
			}
		}
		
		//Value array list
		rows = sheet.rowIterator();
		row = (XSSFRow) rows.next();
		Iterator cells = row.cellIterator();
		while(cells.hasNext()){
			cell = (XSSFCell) cells.next();
			cell.setCellType(Cell.CELL_TYPE_STRING);
			if(cell.getStringCellValue().equalsIgnoreCase(dataset)){
				int index = cell.getColumnIndex();
				values.add(row.getCell(index).getStringCellValue().trim());
				while(rows.hasNext()){
					row = (XSSFRow) rows.next();
					try{
					(row.getCell(index)).setCellType(Cell.CELL_TYPE_STRING);
					if(row.getCell(index).getStringCellValue()=="")
						values.add("NA");
					else
						values.add(row.getCell(index).getStringCellValue());
					}catch(NullPointerException e){
						values.add("NA");
					}
					
				}	
			}
		}
		
		int keylength = keys.size();
		int valueslength = values.size();
		System.out.println(">>>>"+keylength+"<<<<"+valueslength);
		
		if(keylength==valueslength){
			
			for(int index=0 ; index<keylength ; index++){
				mapData.put(keys.get(index), values.get(index));
			}
			file.close();
			return mapData;
		}
		else{
			System.out.println("%%%ERROR%%%% KEY-Value pair mismatched");
			file.close();
			return null;
		}
	}
	
	public void setExcelRows(String workbook, String worksheet, String dataset, List<String> rampup_data) throws IOException{
		
		String filepath = "C:\\Users\\srivastavag\\workspace\\DataSheet\\DataExcel\\"+workbook+".xlsx";
		FileInputStream fileIn = new FileInputStream(filepath);
		XSSFWorkbook wb = new XSSFWorkbook(fileIn);
		XSSFSheet sheet = wb.getSheet(worksheet);
		XSSFRow row = sheet.getRow(0);
		Iterator cells = row.cellIterator();
		XSSFCell cell;
		int colum_index=0;
		wb.setMissingCellPolicy(XSSFRow.RETURN_NULL_AND_BLANK);
		while(cells.hasNext()){
			cell = (XSSFCell) cells.next();
			if(cell.getStringCellValue().equalsIgnoreCase(dataset)){
				colum_index = cell.getColumnIndex();
				break;
			}
		}
		if(colum_index==0)
			System.out.println("%%%ERROR%%% WRONG ROW SELECTION FOR ENTRY");
		System.out.println("COLN_INDEX:::::::::::"+ colum_index);
		Iterator rows = sheet.rowIterator();
		int counter=0;
		while(rows.hasNext()){
			row=(XSSFRow) rows.next();
			if(row.getRowNum()==0){
				continue;
			}else{	
			if(row.getCell(colum_index)==null){
				row.createCell(colum_index);
				row.getCell(colum_index).setCellType(Cell.CELL_TYPE_STRING);
				row.getCell(colum_index).setCellValue(rampup_data.get(counter));
			}else{
				row.getCell(colum_index).setCellType(Cell.CELL_TYPE_STRING);
				row.getCell(colum_index).setCellValue(rampup_data.get(counter));
			}
			}	
			counter++;
			
		}
		
		FileOutputStream fileout = new FileOutputStream(filepath);
		wb.write(fileout);
		fileout.close();
		fileIn.close();
		
	}
	
	public boolean columnValidation(String actualColumnName,String expectedColumnName,String workbook, String worksheet) throws IOException{
		//This function compare two given columns of excel (data-wise)
		boolean flag = false;
		String filepath = "C:\\Users\\srivastavag\\workspace\\DataSheet\\DataExcel\\"+workbook+".xlsx";
		FileInputStream fileIn = new FileInputStream(filepath);
		XSSFWorkbook wb = new XSSFWorkbook(fileIn);
		XSSFSheet sheet = wb.getSheet(worksheet);
		XSSFCellStyle passStyle = wb.createCellStyle();
		passStyle.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
		passStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
		XSSFCellStyle failStyle = wb.createCellStyle();
		failStyle.setFillForegroundColor(IndexedColors.RED.getIndex());
		failStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
		XSSFRow row = sheet.getRow(0);
		Iterator cells = row.cellIterator();
		XSSFCell cell;
		int actualColumIndex=0, expectedColumnIndex=0;
		wb.setMissingCellPolicy(XSSFRow.RETURN_NULL_AND_BLANK);
		while(cells.hasNext()){
			cell = (XSSFCell) cells.next();
			if(cell.getStringCellValue().equalsIgnoreCase(actualColumnName))
				actualColumIndex = cell.getColumnIndex();
			if(cell.getStringCellValue().equalsIgnoreCase(expectedColumnName))
				expectedColumnIndex = cell.getColumnIndex();
		}
	if(actualColumIndex==0 || expectedColumnIndex==0 )
		System.out.println("%%%ERROR%%% MATCH NOT FOUND IN GIVEN EXCEL FILE CHECK THE SOURCE EXCEL");
	
	Iterator rows = sheet.rowIterator();
	
	while(rows.hasNext()){
		row= (XSSFRow) rows.next();
		if(row.getRowNum()==0)
			continue;
		row.getCell(actualColumIndex).setCellType(Cell.CELL_TYPE_STRING);
		row.getCell(expectedColumnIndex).setCellType(Cell.CELL_TYPE_STRING);
		if(row.getCell(actualColumIndex).getStringCellValue().equals(row.getCell(expectedColumnIndex).getStringCellValue())){
			//Make both cells green
			row.getCell(actualColumIndex).setCellStyle(passStyle);
			row.getCell(expectedColumnIndex).setCellStyle(passStyle);
			//System.out.println("MAKE GREEN");
		}
		else{
			//Make both cells red and raise the error flag
			flag=true;
			row.getCell(actualColumIndex).setCellStyle(failStyle);
			row.getCell(expectedColumnIndex).setCellStyle(failStyle);
			//System.out.println("MAKE RED");
		}
	}
	
	FileOutputStream fileout = new FileOutputStream(filepath);
	wb.write(fileout);
	fileout.close();
	fileIn.close();
	return flag;
}	
	
public void setDealNo(String workbook, String worksheet, String dataset, String deal_no) throws IOException{
	//write deal value on SI etc sheet
	String filepath = "C:\\Users\\srivastavag\\workspace\\DataSheet\\DataExcel\\"+workbook+".xlsx";
	FileInputStream fileIn = new FileInputStream(filepath);
	XSSFWorkbook wb = new XSSFWorkbook(fileIn);
	XSSFSheet sheet = wb.getSheet(worksheet);
	XSSFRow row = sheet.getRow(0);
	Iterator cells = row.cellIterator();
	XSSFCell cell;
	int colum_index=0;
	wb.setMissingCellPolicy(XSSFRow.RETURN_NULL_AND_BLANK);
	while(cells.hasNext()){
		cell = (XSSFCell) cells.next();
		if(cell.getStringCellValue().equalsIgnoreCase(dataset)){
			colum_index = cell.getColumnIndex();
			break;
		}
	}
	
	Iterator rows = sheet.rowIterator();
	while(rows.hasNext()){
		row = (XSSFRow) rows.next();
		if(row.getCell(0).getStringCellValue().equalsIgnoreCase("Deal_no")){
			//do the write operation 
			System.out.println("DATA ENTRY*************"+row.getRowNum()+">>>>>"+colum_index);
			if(row.getCell(colum_index)==null){
				row.createCell(colum_index);
				row.getCell(colum_index).setCellType(Cell.CELL_TYPE_STRING);
				row.getCell(colum_index).setCellValue(deal_no);
			}else{
				row.getCell(colum_index).setCellType(Cell.CELL_TYPE_STRING);
				row.getCell(colum_index).setCellValue(deal_no);
			}
			
					
			FileOutputStream fileout = new FileOutputStream(filepath);
			wb.write(fileout);
			fileout.close();
		}
	}
	
	fileIn.close();
}
	
public String getConfig(String serviceType) throws IOException{
	String filepath = "C:\\Users\\srivastavag\\workspace\\DataSheet\\DataExcel\\CONFIG.xlsx";
	String serviceValue = null;
	InputStream file = new FileInputStream(filepath);
	XSSFWorkbook wb = new XSSFWorkbook(file);
	XSSFSheet sheet = wb.getSheetAt(0);
	
	XSSFRow row;
	
	
	Iterator rows = sheet.rowIterator();
	while(rows.hasNext()){
		row = (XSSFRow) rows.next();
		if(row.getCell(0).getStringCellValue().equalsIgnoreCase(serviceType)){
			serviceValue = row.getCell(1).getStringCellValue();
			file.close();
			return serviceValue;
		}
	}
	file.close();
	return null;
	
}


	
}
