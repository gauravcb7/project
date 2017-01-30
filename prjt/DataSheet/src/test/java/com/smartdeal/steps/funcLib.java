package com.smartdeal.steps;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class funcLib {

	public static void datePicker(WebDriver driver, String date, Boolean same){
		
		WebElement table = driver.findElement(By.xpath("//div[@id='DatePickerDiv']/div/div/div[@class='ms-picker-body']/table[@class='ms-picker-table']/tbody"));
		List<WebElement> validColumns=null;
			//For any other valid date
			// WebElement validColumn = table.findElement(By.xpath("//td[@class='ms-picker-daycenter']/a[contains(text(), date)]"));
			 //validColumn.click();
		System.out.println("ERROR"+date);
		if(same){
			/*List<WebElement> cells = table.findElements(By.cssSelector("td.ms-picker-today"));
			 for(WebElement cell:cells){
			  String cellvalue=cell.findElement(By.tagName("a")).getText();
				 System.out.println(cellvalue);
				 if(date.equals(cellvalue)){
					 	
						System.out.println("matched");
						cell.click();
					
				 }*/
			table.findElement(By.className("ms-picker-today"));
			//driver.findElement(By.xpath("//div[@class='ms-picker-body']/table[@class='ms-picker-table']/tbody/tr/td[@class='td.ms-picker-today']/a")).click();
			
		}else{
			validColumns = table.findElements(By.cssSelector("td.ms-picker-daycenter"));
			 for(WebElement cell:validColumns){
				 String cellvalue=cell.findElement(By.tagName("a")).getText();
				 System.out.println(cellvalue);
				 if(date.equals(cellvalue)){
					 	
						System.out.println("matched");
						cell.click();
						break;
				 }
			 }
			 
		}
		 			 
	}
	public static int getDD(String date){
		
		// Split the string based on / delimiter
		String[] subString = date.split("/");
		int rValue = Integer.parseInt(subString[1]);
				return rValue;
		
	}
	
	public static String getdealNo(String dealName){
		String [] dealNo = dealName.split(":");
		return dealNo[1];
		
	}
	
	public static boolean checkMandate(Map<String, String> mapData, String field){
		if(mapData.get(field).equals("NA") || mapData.get(field).equals("") )
			return false;
		else
			return true;	
	}
	 
}
