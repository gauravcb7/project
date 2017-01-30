package com.smartdeal.steps;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.security.UserAndPassword;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class stepDefinitions {
	
	//Declaration
	private Map<String, String> mapData = null;
	private  WebDriver driver=null;
	private WebDriverWait wait=null;
	private boolean validationStatusRateCard;
	private boolean validationStatusPnL;
	excelUtill excel = new excelUtill();
	private String mainWindowHandler = null;
	private String dealNumber = null;
	private int contractyear = 0;
	private int rows =0;
	Scenario scenario;
	String finalTestDataExcel = null;
	List<String> cust_roles = new ArrayList<String>();

	@After
	public void afterSceanrio(Scenario s){
		//Write the 
		s.write("RATE CARD & PnL ACTUAL DATA SHEET : "+finalTestDataExcel);
		s.write("Sceanrio Name:"+ s.getName());
		s.write("URL at failure: " + driver.getCurrentUrl());
		
		if (s.isFailed()) {
	        try {
	            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
	            s.embed(screenshot, "image/png" );
	           
	           
	        } catch (WebDriverException wde) {
	            s.write("Embed Failed " + wde.getMessage());
	        } catch (ClassCastException cce) {
	            cce.printStackTrace();
	        }
	    }
		new DriverFactory().destroyDriver();
	}
	
	
	@SuppressWarnings("unchecked")
	@Given("^Workbook \"([^\"]*)\" Sheet \"([^\"]*)\" and DataSet \"([^\"]*)\"$")
	public void workbook_Sheet_and_DataSet(String workbook, String sheet, String dataset) throws Throwable {
	    // A collection map is used to retrieve the test date from TEST_DATA excel using the excelUtil class
		mapData=null;
		mapData = excel.getTestData(workbook, sheet, dataset);
		
	}
	
	@Given("^Browser name and navigate to Url$")
	public void browser_name_and_navigate_to_Url() throws Throwable {
	    // <Browser> name and desired <Url> passed from CONFIG excel file
		String browser = excel.getConfig("Browser");
		String url = excel.getConfig("Url");
		//driver = (driverFactory=new DriverFactory(browser)).getDriver();
		driver = new DriverFactory(browser).getDriver();
		
		
		driver.navigate().to(url);
		System.out.println(driver.getTitle());
		
		try{
			wait= new WebDriverWait(driver,30);
			wait.until(ExpectedConditions.titleIs("Smart Deal - Home"));
		//assertEquals(driver.getTitle(),"Smart Deal - Home");
		}catch(TimeoutException e){
			Select dropdown = new Select(driver.findElement(By.xpath("//div/select[@id='ctl00_PlaceHolderMain_ClaimsLogonSelector']")));
			dropdown.selectByVisibleText("Windows Authentication");
			wait.until(ExpectedConditions.titleIs("Smart Deal - Home"));
		}catch(UnhandledAlertException e){
			WebDriverWait wait = new WebDriverWait(driver,2);
			Alert alert =wait.until(ExpectedConditions.alertIsPresent());
			alert.authenticateUsing(new UserAndPassword("srivastavag","Welcomecb7$"));
		}
	}
	
	@When("^Click New Deal button$")
	public void click_New_Deal_button() throws Throwable {
	    // Click the New Deal button to navigate to KIF page
		wait.until(ExpectedConditions.titleIs("Smart Deal - Home"));
		driver.findElement(By.xpath("//div/a[@class='newDeal']")).click();
		try{
		wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.titleIs("Smart Deal - KIF"));
		}catch(TimeoutException e){
			
			Select dropdown = new Select(driver.findElement(By.xpath("//div/select[@id='ctl00_PlaceHolderMain_ClaimsLogonSelector']")));
			dropdown.selectByVisibleText("Windows Authentication");
		}
	}
	
	

	@Then("^Navigates to KIF page$")
	public void navigates_to_KIF_page() throws Throwable {
	    // Verify user is on KIF page
		wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.titleIs("Smart Deal - KIF"));
		assertEquals(driver.getTitle(), "Smart Deal - KIF");
	}
	
	
	@And("^Enter KIF page fields as per DataSet \"([^\"]*)\"$")
	public void enter_KIF_page_fields_as_per_DataSet(String dataset) throws Throwable {
	    // Value of customer name field : no validation
		//mapData=null;
		//mapData = excel.getTestData(workbook, sheet, dataset);
		driver.findElement(By.xpath("//div/input[@id='txtCustomer']")).sendKeys(mapData.get("Customer_Name"));
		
		 //Value of customer vertical : validate for SAP & EAS
		Select selectVertical = new Select(driver.findElement(By.xpath("//div/select[@id='ddlServiceLines']")));
		selectVertical.selectByVisibleText(mapData.get("Customer_Vertical"));
		if(mapData.get("Customer_Vertical").equals("SAP") || mapData.get("Customer_Vertical").equals("EAS") ){
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div/select[@id='ddlVBUEASSupport']")));
			assertTrue("Related Dropdown Exist",driver.findElement(By.xpath("//div/select[@id='ddlVBUEASSupport']")).isDisplayed());
			//As the customer vertical is SAP or EAS check the existence of input for related dropdown if available enter the value
			if(!(mapData.get("Customer_Vertical_DD_EXTRA").equalsIgnoreCase("NA"))){
				// Select the value from dropdown as per argument
				Select selectDD = new Select(driver.findElement(By.xpath("//div/select[@id='ddlVBUEASSupport']")));
				selectDD.selectByValue(mapData.get("Customer_Vertical_DD_EXTRA"));
			}
		}
		
		// Value of deal size. Deal size greater than 3 have alert message validation based on EN/NN field value selection
				//Above validation done on EN/NN field entry
		if(funcLib.checkMandate(mapData, "Deal_Size")){
				Select selectSize = new Select(driver.findElement(By.xpath("//div/select[@id='ddlDealSize']")));
				selectSize.selectByVisibleText(mapData.get("Deal_Size"));
				if(Integer.parseInt((selectSize.getFirstSelectedOption().getAttribute("value"))) > 4){
					//verify the About company and Competitors fields should be mandatory
					String attrValueCompany = driver.findElement(By.xpath("//div[@id='divLblAboutCompany']/sup[@class='mandatoryField']")).getAttribute("style");
					assertTrue("Mandatory Field About Company",attrValueCompany.contains("display: inline;"));
					String attrValueCompetitors = driver.findElement(By.xpath("//div[@id='divLCompetitors']/sup[@class='mandatoryField']")).getAttribute("style");
					assertTrue("Mandatory Field Competitors",attrValueCompetitors.contains("display: inline;"));
					System.out.println("$$$$$$"+attrValueCompany+"$$$$$$"+attrValueCompetitors );
					if(!(mapData.get("About_Company").equalsIgnoreCase("NA"))){
						// Enter About company name as the deal is bigger than 10-25Mn
						driver.findElement(By.xpath("//div/input[@id='txtAbtCompany']")).sendKeys(mapData.get("About_Company"));
					}
					if(!(mapData.get("Competitors").equalsIgnoreCase("NA"))){
						// Enter Competitor name as the deal is bigger than 10-25Mn
						driver.findElement(By.xpath("//div/input[@id='txtCompetition']")).sendKeys(mapData.get("Competitors"));
					}
				  }
		}
		// Value of deal type. No validation
		if(funcLib.checkMandate(mapData, "Deal_Type")){
				Select selectType = new Select(driver.findElement(By.xpath("//div/select[@id='ddlDealType']")));
				selectType.selectByVisibleText(mapData.get("Deal_Type"));
		}
		// Value to be entered only day(DD). No month and year allowed for simplication of test condition
		if(funcLib.checkMandate(mapData, "Sales_Date")){
				String mainWindowHandler = driver.getWindowHandle();
				WebElement cal = driver.findElement(By.xpath("//div/table/tbody/tr/td/a/img[@id='spdtRFPReceivedbySalesDateDateDatePickerImage']"));
				cal.click();
				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(By.xpath("//div/table/tbody/tr/td/iframe[@id='spdtRFPReceivedbySalesDateDateDatePickerFrame']"))));
				funcLib.datePicker(driver,mapData.get("Sales_Date"),false);
				driver.switchTo().window(mainWindowHandler);
		}		
		// Value of vertical. Validation the text box if vertical selected as other
				Select selectVertical1 = new Select(driver.findElement(By.xpath("//div/select[@id='ddlVertical']")));
				selectVertical1.selectByVisibleText(mapData.get("Vertical"));
				if(mapData.get("Vertical").contains("Other")){
					wait = new WebDriverWait(driver,2);
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div/input[@id='txtspecifyOther']")));
					assertTrue("Specify Other input box exist",driver.findElement(By.xpath("//div/input[@id='txtspecifyOther']")).isDisplayed());
					if(!(mapData.get("").equalsIgnoreCase("NA"))){
					// Enter other Vertical field
					driver.findElement(By.xpath("//div/input[@id='txtspecifyOther']")).sendKeys(mapData.get("Specify_Other_Vertical"));
					}
				}		
			
		// Value of G2000 ranking. Validate if Yes selected
				Select selectRanking = new Select(driver.findElement(By.xpath("//div/select[@id='ddlWhetherPartofRanking']")));
				selectRanking.selectByVisibleText(mapData.get("G_2000_Rankings"));
				if(mapData.get("G_2000_Rankings").equalsIgnoreCase("Yes")){
					wait = new WebDriverWait(driver,2);
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div/input[@id='txtRankdescription']")));
					assertTrue("If yes the Rank input box exist",driver.findElement(By.xpath("//div/input[@id='txtRankdescription']")).isDisplayed());
					if(!(mapData.get("Rank").equals("NA"))){
						// Enter rank
						driver.findElement(By.xpath("//div/input[@id='txtRankdescription']")).sendKeys(mapData.get("Rank"));
					}
				}
				
		// Value of customer side . If selected other than validate the text input box
				Select selectConsultant = new Select(driver.findElement(By.xpath("//div/select[@id='ddlthirdpartyConsultant']")));
				selectConsultant.selectByVisibleText(mapData.get("Customer_side"));
				if(mapData.get("Customer_side").contains("Other")){
					wait = new WebDriverWait(driver,2);
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div/input[@id='txtspecifyotherconsultant']")));
					assertTrue("Specify other text box exist",driver.findElement(By.xpath("//div/input[@id='txtspecifyotherconsultant']")).isDisplayed());
					if(!(mapData.get("Specify_Other_Consultant").equalsIgnoreCase("NA"))){
						// Enter Other customer field
						driver.findElement(By.xpath("//div/input[@id='txtspecifyotherconsultant']")).sendKeys(mapData.get("Specify_Other_Consultant"));
					}
				}
				
	// Value of EN/NN field. Validation of deal size field when EN or NN selected. Corresponding message displayed for long deal.
				String actualMsg = null;
				String expectedMsg = "This is a large deal and we are supporting through excel for these deals. Please write a mail to bfcteam@hcl.com for SPOC allocation.";
				Select selectEnnn = new Select(driver.findElement(By.xpath("//div/select[@id='ddlENNN']")));
				selectEnnn.selectByVisibleText(mapData.get("EN_NN"));
				//verify the message displayed when deal size greater based on EN or NN selection
				Select selectSize1 = new Select(driver.findElement(By.xpath("//div/select[@id='ddlDealSize']")));
				int dealsize = Integer.parseInt(selectSize1.getFirstSelectedOption().getAttribute("value"));
				
				if((mapData.get("EN_NN").equalsIgnoreCase("EN")) & (dealsize>4)){
					
					actualMsg = driver.findElement(By.xpath("//div[@class='msg']/span[@id='lblMsg']")).getText();
					//verify message displayed
					assertEquals("Large Deal Msg Displayed", expectedMsg, actualMsg);
				}else if((mapData.get("EN_NN").equalsIgnoreCase("NN")) & (dealsize>3)){
					
					actualMsg = driver.findElement(By.xpath("//div[@class='msg']/span[@id='lblMsg']")).getText();
					//verify message displayed
					assertEquals("Large Deal Msg Displayed", expectedMsg, actualMsg);
				}
				
	// Value to be entered only day(DD). No month and year allowed for simplication of test condition
			if(funcLib.checkMandate(mapData, "Issue_date")){
				mainWindowHandler = driver.getWindowHandle();
				WebElement cal1 = driver.findElement(By.xpath("//div/table/tbody/tr/td/a/img[@id='spdtRFPRFIssueDateDateDatePickerImage']"));
				cal1.click();
				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(By.xpath("//div/table/tbody/tr/td/iframe[@id='spdtRFPRFIssueDateDateDatePickerFrame']"))));
				funcLib.datePicker(driver,mapData.get("Issue_date"),false);
				driver.switchTo().window(mainWindowHandler);		
			}
	 // Value of Geo entered. No validation on field(s)
				Select selectGeo = new Select(driver.findElement(By.xpath("//div/select[@id='ddlGeography']")));
				selectGeo.selectByVisibleText(mapData.get("Geography"));			
				
	// Value to be entered only day(DD). No month and year allowed for simplication of test condition.
	// Validation : Submission date should not be less than current date (Intimation by Sales to BFC Date)
				mainWindowHandler = driver.getWindowHandle();
				String bfcDate = driver.findElement(By.xpath("//div/span[@id='lblSalestoBFCDate']")).getText();
				System.out.println(">>>>>>"+bfcDate);
				// Crop the DD part from MM/DD/YYYY format
				int actualDate = funcLib.getDD(bfcDate);
				int inputDate = Integer.parseInt(mapData.get("Submission_Date"));
				if(inputDate == actualDate){
					WebElement cal11= driver.findElement(By.xpath("//div/table/tbody/tr/td/a/img[@id='spdtSubmissionDateDateDatePickerImage']"));
					cal11.click();
					wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(By.xpath("//div/table/tbody/tr/td/iframe[@id='spdtSubmissionDateDateDatePickerFrame']"))));
					funcLib.datePicker(driver, mapData.get("Submission_Date"),true);
					driver.switchTo().window(mainWindowHandler);
				}else if(inputDate > actualDate){
					WebElement cal11= driver.findElement(By.xpath("//div/table/tbody/tr/td/a/img[@id='spdtSubmissionDateDateDatePickerImage']"));
					cal11.click();
					wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(By.xpath("//div/table/tbody/tr/td/iframe[@id='spdtSubmissionDateDateDatePickerFrame']"))));
					funcLib.datePicker(driver, mapData.get("Submission_Date"), false);
					driver.switchTo().window(mainWindowHandler);
					}else{
					WebElement cal11= driver.findElement(By.xpath("//div/table/tbody/tr/td/a/img[@id='spdtSubmissionDateDateDatePickerImage']"));
					cal11.click();
					wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(By.xpath("//div/table/tbody/tr/td/iframe[@id='spdtSubmissionDateDateDatePickerFrame']"))));
					funcLib.datePicker(driver, mapData.get("Submission_Date"), false);
					driver.switchTo().window(mainWindowHandler);
					//press tab to invoke the alert message
					driver.findElement(By.xpath("//div/table/tbody/tr/td/input[@id='spdtSubmissionDateDate']")).sendKeys(Keys.TAB);
					String ver_text = "Submission Date should not be less than Current Date!";
					wait.until(ExpectedConditions.alertIsPresent());
					Alert alert = driver.switchTo().alert();
					System.out.println(alert.getText());
					assertTrue(alert.getText().contains(ver_text));
					alert.accept();
					driver.switchTo().window(mainWindowHandler);
				}
				
				
	}
	
	@When("^Click save button$")
	public void click_save_button() throws Throwable {
	    // //Press save button
		
		try{
			driver.findElement(By.xpath("//div/input[@id='btnKIFSave']")).click();
			Thread.sleep(2000);
			}catch(UnhandledAlertException e){
			System.out.println("ISSUE WITH ALERT BOX");
			message_Alert_displayed_Enter_all_mandatory_fields();
		}
		wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.titleIs("Smart Deal - KIF"));
	}
	
	
	
	@Then("^Message Alert displayed Enter all mandatory fields$")
	public void message_Alert_displayed_Enter_all_mandatory_fields() throws Throwable {
	 //verify the text present on alert message
		String ver_text = "Please fill all mandatory fields and save again.";
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.alertIsPresent());
		Alert alert = driver.switchTo().alert();
		System.out.println(alert.getText());
		assertTrue(alert.getText().contains(ver_text));
		alert.accept();
	}
	
	@Then("^Deal Number Successfully Created$")
	public void deal_number_successfully_Created() throws Throwable {
		
		wait = new WebDriverWait(driver, 40);
	    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div/span[contains(@id ,'lblDealNo') and starts-with(text(), 'Deal No:')]"))); // textToBePresentInElementValue(By.xpath("//div/span[@id='lblCustomerName']"),custMsg));
	    
	    dealNumber = driver.findElement(By.xpath("//div/span[@id='lblDealNo']")).getText();
	   
	    System.out.println(">>>>>>>>>>>>>>>>>>>>>>"+dealNumber);
	    assertFalse(dealNumber.isEmpty());
	    
	    // After verify the attachment and SPOC button enablement
	    
	  //verify Attach button is enabled
	  		//assertEquals(true, driver.findElement(By.xpath("//div/input[@id='btnAttach']")).isEnabled());
	  		
	  		//verify SPOCs button is enabled
	  		//assertEquals(true, driver.findElement(By.xpath("//div/input[@id='btnSPOCSelection']")).isEnabled());
	    
	       	    
	}
	
	@When("^Click the SPOC button$")
	public void click_the_SPOC_button() throws Throwable {
	    //assert for deal already being created
		String dealNumber = driver.findElement(By.xpath("//div/span[@id='lblDealNo']")).getText();
		assertTrue(dealNumber.startsWith("Deal No:"));
		
		//check spoc enable
		assertTrue(driver.findElement(By.xpath("//div/input[@id='btnSPOCSelection']")).isEnabled());
		
		
		//click spoc button
		mainWindowHandler = driver.getWindowHandle();
		driver.findElement(By.xpath("//div/input[@id='btnSPOCSelection']")).click();
		// wait until frame loads succesfully
		
		wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//div/iframe[@class='ms-dlgFrame']")));
	}

	
	@Then("^Enter SPOC details \"([^\"]*)\"$")
	public void enter_SPOC_details(String userid) throws Throwable {
	    // Enter the information of various SPOC's. For simplicity using same userid
		//input account manger
				wait = new WebDriverWait(driver,10);
				driver.findElement(By.xpath("//div/table/tbody/tr/td/input[@id='ctl00_PlaceHolderMain_txtSearchActManager']")).sendKeys(userid);
				//wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath("//div/table/tbody/tr/td/div/div/select[@id='ctl00_PlaceHolderMain_lbUserListActManager']/option"),1));
				Select accMangerL = new Select(driver.findElement(By.xpath("//div/table/tbody/tr/td/div/div/select[@id='ctl00_PlaceHolderMain_lbUserListActManager']")));
				accMangerL.selectByIndex(0);
				driver.findElement(By.xpath("//div/table/tbody/tr/td/input[@id='ctl00_PlaceHolderMain_btnAddActManager']")).click();
				wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//div/table/tbody/tr/td/div/div/select[@id='ctl00_PlaceHolderMain_lstBSelectedUsersActManager']/option"),2));
			
		// select delivery spoc
				driver.findElement(By.xpath("//div/table/tbody/tr/td/input[@id='ctl00_PlaceHolderMain_txtSearchDeliverySpoc']")).sendKeys(userid);
				//wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath("//div/table/tbody/tr/td/div/div/select[@id='ctl00_PlaceHolderMain_lbUserListActManager']/option"),1));

				//wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//div/table/tbody/tr/td/div/div/select[@id='ctl00_PlaceHolderMain_lblUserlistDeliverySpoc']/option"),1));
				Select accMangerL1 = new Select(driver.findElement(By.xpath("//div/table/tbody/tr/td/div/div/select[@id='ctl00_PlaceHolderMain_lblUserlistDeliverySpoc']")));
				accMangerL1.selectByIndex(0);
				driver.findElement(By.xpath("//div/table/tbody/tr/td/input[@id='ctl00_PlaceHolderMain_btnAddDeliveryspoc']")).click();
				wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//div/table/tbody/tr/td/div/div/select[@id='ctl00_PlaceHolderMain_lstBSelectedUsersDeliveryspoc']/option"),2));
		
	 // select pre-sales spoc
				driver.findElement(By.xpath("//div/table/tbody/tr/td/input[@id='ctl00_PlaceHolderMain_txtSearchPreSalesSpoc']")).sendKeys(userid);
				//wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath("//div/table/tbody/tr/td/div/div/select[@id='ctl00_PlaceHolderMain_lbUserListActManager']/option"),1));

				//wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//div/table/tbody/tr/td/div/div/select[@id='ctl00_PlaceHolderMain_lblUserlistPreSalesSpoc']/option"), 1));
				Select accMangerL11 = new Select(driver.findElement(By.xpath("//div/table/tbody/tr/td/div/div/select[@id='ctl00_PlaceHolderMain_lblUserlistPreSalesSpoc']")));
				accMangerL11.selectByIndex(0);
				driver.findElement(By.xpath("//div/table/tbody/tr/td/input[@id='ctl00_PlaceHolderMain_btnAddPreSalesSpoc']")).click();
				wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//div/table/tbody/tr/td/div/div/select[@id='ctl00_PlaceHolderMain_lstBSelectedUsersPreSalesSpoc']/option"), 2));
				
	}
	
	@Then("^Click save button on SPOC selection page$")
	public void click_save_button_on_SPOC_selection_page() throws Throwable {
	    driver.findElement(By.xpath("//div/table/tbody/tr/td/input[@id='ctl00_PlaceHolderMain_SaveAll']")).click();
	}

	@Then("^verify SPOC Allocation section populated$")
	public void verify_SPOC_Allocation_section_populated() throws Throwable{
		System.out.println(driver.getTitle());
		driver.switchTo().window(mainWindowHandler);
		//Account manger field verification
		assertTrue(driver.findElement(By.xpath("//div/span[@id='lblAccountManager']")).isDisplayed());
		//Delivery SPOC field verification
		assertTrue(driver.findElement(By.xpath("//div/span[@id='lblDeliverySpoc']")).isDisplayed());
		//Sales SPOC
		assertTrue(driver.findElement(By.xpath("//div/span[@id='lblSalesSpoc']")).isDisplayed());
	}
	
	@When("^click Next button on KIF page$")
	public void click_Next_button_on_KIF_page() throws Throwable{
		//click Next button to move SI page
		
		driver.findElement(By.xpath("//div/input[@id='btnKIFNext']")).click();
		try{
			wait = new WebDriverWait(driver,30);
			wait.until(ExpectedConditions.titleIs("Smart Deal - StandardInput"));
		}catch(TimeoutException e){
			if(driver.getTitle().equalsIgnoreCase("Sign-In")){
			Select dropdown = new Select(driver.findElement(By.xpath("//div/select[@id='ctl00_PlaceHolderMain_ClaimsLogonSelector']")));
			dropdown.selectByVisibleText("Windows Authentication");
		    }
		}
	}
	
	@Then("^Navigates to SI page$")
	public void navigates_to_SI_page() throws Throwable {
	    // verify SI page should display
		
		assertTrue(driver.getTitle().equalsIgnoreCase("Smart Deal - StandardInput"));
	}
	
//SI page methods
	
	@And("^Enter basic SI page fields$")
	public void enter_basic_SI_page_fields() throws Throwable {
		// Enter values of corresponding fields from excel as per dataset
				//Select the Pricing currency
				Select select = new Select(driver.findElement(By.xpath("//div/table/tbody/tr/td/div/select[@id='ddlPricingCurrency']")));
				select.selectByVisibleText(mapData.get("Pricing_Currency"));
				
				//Select the total contract period
				select = new Select(driver.findElement(By.xpath("//div/table/tbody/tr/td/div/select[@id='ddlTotalContractPeriod']")));
				select.selectByVisibleText(mapData.get("Total_Contract_Period"));
				
				//Select Rebadging involved
				select = new Select(driver.findElement(By.xpath("//div/table/tbody/tr/td/div/select[@id='ddlRebadgeRequire']")));
				select.selectByVisibleText(mapData.get("Rebadging_involved"));
				
				//Select Towers Required
				mainWindowHandler = driver.getWindowHandle();

				select = new Select(driver.findElement(By.xpath("//div/table/tbody/tr/td/div/select[@id='ddlTowerRequire']")));
				
				
				

				if(mapData.get("Towers_Required").equals("Yes")){
					
					select.selectByVisibleText(mapData.get("Towers_Required"));
					WebElement close = driver.findElement(By.className("ms-dlgCloseBtnImg"));
					wait = new WebDriverWait(driver,10);
					wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.className("ms-dlgFrame")));
					
					//WebElement frame = driver.findElement(By.className("ms-dlgFrame"));
					//System.out.println("FRAMEEEE>>>>>>>>"+frame);
					//driver.switchTo().frame(frame);
					
					//System.out.println(">>>>>>>>"+ driver.getTitle());
					//check the entry for towers
					if(!(mapData.get("Tower_1").equalsIgnoreCase("NA"))){
						driver.findElement(By.id("txtTower2")).sendKeys(mapData.get("Tower_1"));
					}
					if(!(mapData.get("Tower_2").equalsIgnoreCase("NA"))){
						driver.findElement(By.id("txtTower3")).sendKeys(mapData.get("Tower_2"));
					}
					if(!(mapData.get("Tower_3").equalsIgnoreCase("NA"))){
						driver.findElement(By.id("txtTower4")).sendKeys(mapData.get("Tower_3"));
					}		
					
					driver.findElement(By.xpath("//div/input[@id='btnSaveTower']")).click();
					driver.switchTo().window(mainWindowHandler);
					Actions builder = new Actions(driver);
					builder.moveToElement(close).click().perform();
					//close.click();
			  }else{
				  select.selectByVisibleText(mapData.get("Towers_Required"));
			  }
				
				
			//Select Contractor employees involved
				select = new Select(driver.findElement(By.xpath("//div/table/tbody/tr/td/div/select[@id='ddlContractorRateRequire']")));
				select.selectByVisibleText(mapData.get("Contractor_employees"));
				
			//Select Pricing Inclusive / Exclusive of COLA
			if(funcLib.checkMandate(mapData, "Pricing_COLA")){	
				select = new Select(driver.findElement(By.xpath("//div/table/tbody/tr/td/div/select[@id='ddlInclusiveColaPricing']")));
				select.selectByVisibleText(mapData.get("Pricing_COLA"));
				//If Exclusive selected then addition select box appears
				if(mapData.get("Pricing_COLA").equals("Exclusive")){
					wait = new WebDriverWait(driver,10);
					wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div/table/tbody/tr/td/div/select[@id='ddlCustomerApproval']")));
					if(!(mapData.get("Customer_Approval_COLA").equals("NA"))){
						select = new Select(driver.findElement(By.xpath("//div/table/tbody/tr/td/div/select[@id='ddlCustomerApproval']")));
						select.selectByVisibleText(mapData.get("Customer_Approval_COLA"));
						if(mapData.get("Customer_Approval_COLA").equals("Customer specific COLA")){
							wait = new WebDriverWait(driver,10);
							wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div/table/tbody/tr/td/div/input[@id='txtOnsitecolaRevenue']")));
							wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div/table/tbody/tr/td/div/input[@id='txtOffshoreColaRevenue']")));
							if(!(mapData.get("COLA_Nearshore").equals("NA") & mapData.get("COLA_Offshore").equals("NA"))){
								WebElement nearshore = driver.findElement(By.xpath("//div/table/tbody/tr/td/div/input[@id='txtOnsitecolaRevenue']"));
								nearshore.clear(); nearshore.sendKeys(mapData.get("COLA_Nearshore"));
								WebElement offshore = driver.findElement(By.xpath("//div/table/tbody/tr/td/div/input[@id='txtOffshoreColaRevenue']"));
								offshore.clear(); offshore.sendKeys(mapData.get("COLA_Offshore"));
							}
						}
					}
				}
			}	
			// Select At Onsite Laptop to be provided by HCL
			if(funcLib.checkMandate(mapData, "Laptop_HCL")){	
				select = new Select(driver.findElement(By.xpath("//div/table/tbody/tr/td/div/select[@id='ddlOnsiteLaptopProvided']")));
				select.selectByVisibleText(mapData.get("Laptop_HCL"));
			}
				//Select Other Direct Cost (Hourly) to be included (In Pricing Currency) 
			if(funcLib.checkMandate(mapData, "Other_Direct_Cost")){
				select = new Select(driver.findElement(By.xpath("//div/table/tbody/tr/td/div/select[@id='ddlOtherDirectCosttobeIncluded']")));
				select.selectByVisibleText(mapData.get("Other_Direct_Cost"));
				if(mapData.get("Other_Direct_Cost").equals("Yes")){
					wait = new WebDriverWait(driver,10);
					wait.until(ExpectedConditions.presenceOfElementLocated(By.id("txtOnsiteODC")));
					wait.until(ExpectedConditions.presenceOfElementLocated(By.id("txtOffshoreODC")));
					if(!(mapData.get("Nearshore").equals("NA") & mapData.get("Offshore").equals("NA"))){
						WebElement nearS = driver.findElement(By.id("txtOnsiteODC"));
						nearS.clear(); nearS.sendKeys(mapData.get("Nearshore"));
						WebElement offS = driver.findElement(By.id("txtOffshoreODC"));
						offS.clear(); offS.sendKeys(mapData.get("Offshore"));
					}
				}
			}
			// Select InCountry Axon(Per Diem Inclusion) 
			if(funcLib.checkMandate(mapData, "InCountry_Axon")){
				select = new Select(driver.findElement(By.xpath("//div/table/tbody/tr/td/div/select[@id='ddlInCountryPerDiem']")));
				select.selectByVisibleText(mapData.get("InCountry_Axon"));		
			}	
	}

	@And("^Enter KT fields in SI page$")
	public void enter_KT_fields_in_SI_page() throws Throwable {
		// Knowledge Transition & Governance 
				//Enter KT period(In Months)
		if(funcLib.checkMandate(mapData, "KT_period")){
				WebElement ktterm = driver.findElement(By.id("txtKTNotIncludedinDealTerm"));
				ktterm.clear();ktterm.sendKeys(mapData.get("KT_period"));
		}	
		// Enter % of KT to be absorbed
		if(funcLib.checkMandate(mapData, "PER_KT")){
				WebElement ktper = driver.findElement(By.id("txtktnottobeCharged"));
				ktper.clear();ktper.sendKeys(mapData.get("PER_KT"));
		}	
		//Select KT including in the deal term
		if(funcLib.checkMandate(mapData, "KT_Deal_Term")){
				Select select = new Select(driver.findElement(By.id("ddlKTIncludingDealTerm")));
				select.selectByVisibleText(mapData.get("KT_Deal_Term"));
		}		
		//Select KT to be amortized
		if(funcLib.checkMandate(mapData, "KT_amortized")){
				Select select = new Select(driver.findElement(By.id("ddlkttobeAmortized")));
				select.selectByVisibleText(mapData.get("KT_amortized"));
		}	
		// Enter KT Amortization Period (In Years)
		if(funcLib.checkMandate(mapData, "KT_Amortization_Period")){
				WebElement ktamor = driver.findElement(By.id("txtKTAmortizationPeriod"));
				ktamor.clear();ktamor.sendKeys(mapData.get("KT_Amortization_Period"));
		}
	}

	@And("^Enter Additiona Expense fields in SI page$")
	public void enter_Additiona_Expense_fields_in_SI_page() throws Throwable {
		//Additional Expenses    
		
			contractyear = Integer.parseInt(mapData.get("Total_Contract_Period"));
				
		//One time cost
			if(funcLib.checkMandate(mapData, "One_Time_Cost")){	
				additionExpenseEntry("txtOthersOneTimeY","One_Time_Cost");
				//One time cost addition fields
				WebElement temp = null;
				Select select = new Select(driver.findElement(By.id("ddlOtheroneTimeCharge")));
				select.selectByVisibleText(mapData.get("OT_Charged"));
				if(mapData.get("OT_Charged").equals("Yes")){
				 //% to be charged
				      temp = driver.findElement(By.id("txtOthersOneTimeChargedup"));
					  temp.clear();temp.sendKeys(mapData.get("OT_Per_Charged"));
				//Markup to be charged 
				      temp = driver.findElement(By.id("txtOthersOneTimeChargedAtMarkup"));
					  temp.clear();temp.sendKeys(mapData.get("OT_Markup_Charged"));
					  
				}
			}						
		//Others Recurring Cost
			if(funcLib.checkMandate(mapData, "Others_Recurring_Cost")){		
				additionExpenseEntry("txtOthersRecurringY","Others_Recurring_Cost");
				//Select charged yes/no
				Select select = new Select(driver.findElement(By.id("ddlOtherRecurringCharge")));
				select.selectByVisibleText(mapData.get("OTR_Charged"));
				if(mapData.get("OTR_Charged").equals("Yes")){
					//% to be charged
					WebElement temp = driver.findElement(By.id("txtOthersRecurringChargedup"));
					temp.clear();temp.sendKeys(mapData.get("OTR_Per_Charged"));
					
					//Markup to be charged 
					temp = driver.findElement(By.id("txtOthersRecurringChargedAtMarkup"));
					temp.clear();temp.sendKeys(mapData.get("OTR_Markup_Charged"));	
				}
			}	
		//Travel (No. of Trips)
			if(funcLib.checkMandate(mapData, "Travel_Cost")){			
				additionExpenseEntry("txtNoOfTravelY","Travel_Cost");
				//Travel cost other fields	
				//Select charged yes/no
				Select select = new Select(driver.findElement(By.id("ddlTravelCharge")));
				select.selectByVisibleText(mapData.get("TC_Charged"));
				if(mapData.get("TC_Charged").equals("Yes")){
					//% to be charged
					WebElement temp = driver.findElement(By.id("txtTravelChargedup"));
					temp.clear();temp.sendKeys(mapData.get("TC_Per_Charged"));
						
					//Markup to be charged 
					temp = driver.findElement(By.id("txtTravelChargedAtMarkup"));
					temp.clear();temp.sendKeys(mapData.get("TC_Markup_Charged"));	
				}
			}
	}

	@And("^Enter Effort Overrun Contingencies fields in SI page$")
	public void enter_Effort_Overrun_Contingencies_fields_in_SI_page() throws Throwable {
		//Effort Overrun Contingencies(Delivery Commitment)
		int contryr = Integer.parseInt(mapData.get("Total_Contract_Period"));
		//Fixed Price Project (FPP)
		if(funcLib.checkMandate(mapData, "Fixed_Price_Project")){	
				additionExpenseEntry("txtOverrunFPPY","Fixed_Price_Project");
		}
					
		//Rate Card Deal
		if(funcLib.checkMandate(mapData, "Rate_Card_Deal")){	
				additionExpenseEntry("txtOverrunRateCardDealY","Rate_Card_Deal");
		}
				
		//Annuity Deal
		if(funcLib.checkMandate(mapData, "Annuity_Deal")){
				additionExpenseEntry("txtOverrunAnnuityDealY","Annuity_Deal");
		}
				
		//FPP-(HCL control < 80%)
		if(funcLib.checkMandate(mapData, "FPP_HCL")){
				additionExpenseEntry("txtOverrunFPPOtherY","FPP_HCL");
		}
	}
	
	
	
	
	
	
	@When("^Click save and move to Role Creation button$")
	public void click_save_and_move_to_Role_Creation_button() throws Throwable {
	    // Save the SI page and move to Role Creation page
		driver.findElement(By.id("btnSaveAndMoveValidation")).click();
		
	}

	@Then("^Navigates to Role Creation page$")
	public void navigates_to_Role_Creation_page() throws Throwable {
	    //Verify Role Creation page should open
		wait = new WebDriverWait(driver,40);
		wait.until(ExpectedConditions.titleIs("Smart Deal - RoleCreation"));
	}

 public void additionExpenseEntry(String id, String mapfield){
	 
	 for(int i=1 ; i<=contractyear ; i++){
			String idappent = id+i;
			WebElement temp = driver.findElement(By.id(idappent));
			temp.clear();temp.sendKeys(mapData.get(mapfield));
	 }
 }
 
 @SuppressWarnings("unchecked")
@Then("^Add Customer Role as per Datset \"([^\"]*)\" from Workbook \"([^\"]*)\" and Sheet \"([^\"]*)\"$")
 public void add_Customer_Role_as_per_Datset_from_Workbook_and_Sheet(String dataset, String workbook, String sheet) throws Throwable {
     // Add customer role as per data provided from excel: Same method called to add another customer role
	 try{
	 mapData= null;
	 mapData = excel.getTestData(workbook, sheet, dataset);
	 WebElement text = driver.findElement(By.id("txtCustomerRoleFooter"));
	 text.click();
	 text.sendKeys(mapData.get("Customer_Role"));
	 Select select = new Select(driver.findElement(By.id("ddlVBUFooter")));
	 select.selectByVisibleText(mapData.get("Service_Type"));
	 
	 if(mapData.get("Service_Type").equals("ERS")){
		 select = new Select(driver.findElement(By.id("ddlhclJobFamilyFooter")));
		 select.selectByVisibleText(mapData.get("HCL_job_Family"));
		 select = new Select(driver.findElement(By.id("ddlhclRoleFooter")));
		 select.selectByVisibleText(mapData.get("HCL_Role"));
		 driver.findElement(By.id("txtSkillNoFooter")).sendKeys(mapData.get("Skill_No"));
		 driver.findElement(By.id("txtSkillNoFooter")).sendKeys(Keys.TAB);
		 WaitForAjax();
		 wait = new WebDriverWait(driver,30);
		 wait.until(ExpectedConditions.elementToBeClickable(By.id("btnInsertValidation")));
		 driver.findElement(By.id("btnInsertValidation")).click();
		 wait.until(ExpectedConditions.titleIs("Smart Deal - RoleCreation"));
	     wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//div/table/tbody/tr/td/select[@id='ddlVBUoldFooter']/option"), 1));
		 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		 
		 
		 //wait.until(ExpectedConditions.textToBePresentInElementValue(By.id("txtCustomerRoleFooter"), ""));
		 
	 }else{
	 select = new Select(driver.findElement(By.id("ddlVBUoldFooter")));
	 select.selectByVisibleText(mapData.get("VBU"));
	 select = new Select(driver.findElement(By.id("ddlhclJobFamilyFooter")));
	 select.selectByVisibleText(mapData.get("HCL_job_Family"));
	 select = new Select(driver.findElement(By.id("ddlhclRoleFooter")));
	 select.selectByVisibleText(mapData.get("HCL_Role"));
	 driver.findElement(By.id("txtSkillNoFooter")).sendKeys(mapData.get("Skill_No"));
	 driver.findElement(By.id("txtSkillNoFooter")).sendKeys(Keys.TAB);
	 WaitForAjax();
	 wait = new WebDriverWait(driver,30);
	 wait.until(ExpectedConditions.elementToBeClickable(By.id("btnInsertValidation")));
	 driver.findElement(By.id("btnInsertValidation")).click();
	 wait.until(ExpectedConditions.titleIs("Smart Deal - RoleCreation"));
	 wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//div/table/tbody/tr/td/select[@id='ddlVBUoldFooter']/option"), 1));
	 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	 }}catch(StaleElementReferenceException e){
		 add_Customer_Role_as_per_Datset_from_Workbook_and_Sheet(dataset,workbook,sheet);
	 }
 }

 @SuppressWarnings("unchecked")
@Then("^Add Role Location as per Datset \"([^\"]*)\" from Workbook \"([^\"]*)\" and Sheet \"([^\"]*)\"$")
 public void add_Role_Location_as_per_Datset_from_Workbook_and_Sheet(String dataset, String workbook, String sheet) throws Throwable {
     // Add role location: 
	 mapData= null;
	 mapData = excel.getTestData(workbook, sheet, dataset);
	 Select select = new Select(driver.findElement(By.id("ddlContinent")));
	 select.selectByVisibleText(mapData.get("Continent"));
	 wait.until(ExpectedConditions.titleIs("Smart Deal - RoleCreation"));
	 wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath("//div/table/tbody/tr/td/select[@id='ddlLocation']/option"),1));
	 select = new Select(driver.findElement(By.xpath("//div/table/tbody/tr/td/select[@id='ddlLocation']")));
	 select.selectByVisibleText(mapData.get("Location"));
	 wait.until(ExpectedConditions.titleIs("Smart Deal - RoleCreation"));
	 driver.findElement(By.xpath("//div/table/tbody/tr/td/input[@id='btnInsert']")).click();
	 wait.until(ExpectedConditions.titleIs("Smart Deal - RoleCreation"));
 }

 @When("^Click Rampup button$")
 public void click_Rampup_button() throws Throwable {
     // Click RampUp button to move to ramp up page
	 wait= new WebDriverWait(driver,40);
	 wait.until(ExpectedConditions.titleIs("Smart Deal - RoleCreation"));
	 ((JavascriptExecutor) driver)
     .executeScript("window.scrollTo(0, document.body.scrollHeight)");
	// wait.until(ExpectedConditions.elementToBeClickable(By.id("btnNext")));
	 driver.findElement(By.id("btnNext")).click();
	 
 }

 @Then("^Navigates to Ramp Up page$")
 public void navigates_to_Ramp_Up_page() throws Throwable {
     // Navigates to ramp up page
	 try{
	 wait= new WebDriverWait(driver,60);
	 wait.until(ExpectedConditions.titleIs("Smart Deal - RampUp"));
	 }catch(TimeoutException e){
		 WebElement element = driver.findElement(By.id("btnNext"));
		 ((JavascriptExecutor) driver)
	     .executeScript("window.scrollTo(0, document.body.scrollHeight)");
		 ((JavascriptExecutor) driver)
	     .executeScript("arguments[0].click();", element);
	 }
 }

 @SuppressWarnings("unchecked")
@Then("^set Target Net Margin percent on Ramp up page as per Datset \"([^\"]*)\" from Workbook \"([^\"]*)\" and Sheet \"([^\"]*)\"$")
 public void set_Target_Net_Margin_percent_on_Ramp_up_page(String dataset, String workbook, String sheet) throws Throwable {
     // Enter the value of Onsite, OffShore, NearShore and Rebadge
	 wait= new WebDriverWait(driver,60);
	 wait.until(ExpectedConditions.titleIs("Smart Deal - RampUp"));
	 mapData = null;
	 mapData = excel.getTestData(workbook, sheet, dataset);
	 WebElement temp = driver.findElement(By.id("txtOnsite"));
	 temp.clear();temp.sendKeys(mapData.get("onsite"));
	 
	 temp = driver.findElement(By.id("txtOffshore"));
	 temp.clear();temp.sendKeys(mapData.get("offshore"));
	 
	 temp = driver.findElement(By.id("txtNearshore"));
	 temp.clear();temp.sendKeys(mapData.get("nearshore"));
	 
	 temp = driver.findElement(By.id("txtRebadge"));
	 temp.clear();temp.sendKeys(mapData.get("rebadge"));
	 
	 //click save margin button
	 driver.findElement(By.id("btnSaveMargin")).click();
	 wait.until(ExpectedConditions.titleIs("Smart Deal - RampUp"));
 }

 @And("^Insert number of rows for multiple ramp up as per Datset \"([^\"]*)\" from Workbook \"([^\"]*)\" and Sheet \"([^\"]*)\"$")
 public void insert_number_of_rows_for_multiple_ramp_up(String dataset, String workbook, String sheet) throws Throwable {
     //Insert the no. of rows and click insert rows
	 wait= new WebDriverWait(driver,30);
	 mapData = null;
	 mapData = excel.getTestData(workbook, sheet, dataset);
	 wait.until(ExpectedConditions.titleIs("Smart Deal - RampUp"));
	 driver.findElement(By.id("txtNumberOfRows")).sendKeys(mapData.get("Insert_row"));
	 driver.findElement(By.id("btnBulkRowInsert")).click();
 }

 @Then("^Enter details of ramp up in Insert Ramp Up window from Workbook \"([^\"]*)\" and Sheet \"([^\"]*)\"$")
 public void enter_details_of_ramp_up_in_Insert_Ramp_Up_window(String workbook, String sheet) throws Throwable {
     // Enter details of ramp in tables rows
	 mainWindowHandler = driver.getWindowHandle(); 
	wait = new WebDriverWait(driver,30);
	wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//div/iframe[@class='ms-dlgFrame']")));
	int limit = Integer.parseInt(mapData.get("Insert_row"));
	int rowno = 0;
	for(int i=1; i<=limit ;i++){
		String dataset = mapData.get("data_set"+i);
		System.out.println("DATASET>>>>>>"+dataset);
		rowno = i-1;
		rampupInsert(workbook,sheet,dataset,rowno);
		
	}
	
 }

 @When("^Click Insert on Ramp Up window$")
 public void click_Insert_on_Ramp_Up_window() throws Throwable {
     // Click btnInsert to insert the data 
	 try{
	 driver.findElement(By.id("btnInsert")).click();
	 wait = new WebDriverWait(driver,60);
	 wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div/iframe[@class='ms-dlgFrame']")));
	 }catch(UnhandledAlertException e){
		 WebDriverWait wait1 = new WebDriverWait(driver,10);
			Alert alert =wait1.until(ExpectedConditions.alertIsPresent());
			alert.accept();
	 }
 }

 @Then("^Ramp up rows get saved and reflected on UI$")
 public void ramp_up_rows_get_saved_and_reflected_on_UI() throws Throwable {
     // Verify the table should populated with data: no validation of data
	 driver.switchTo().window(mainWindowHandler);
	 wait.until(ExpectedConditions.titleIs("Smart Deal - RampUp"));
	 List<WebElement> rows = driver.findElements(By.xpath("//div/table[@id='gvRampup']/tbody/tr"));
	 assertTrue("Ramp up data not succesfully inserted",rows.size() > Integer.parseInt(mapData.get("Insert_row")) );
	 
 }

public void rampupInsert(String workbook, String sheet, String dataset, int rowno) throws IOException, InterruptedException{
	Map<String, String> maptemp = excel.getTestData(workbook, sheet, dataset);
	//Pricing model dropdown
	String id = "ddlPricingModel_"+rowno;
	Select select = new  Select(driver.findElement(By.id(id)));
	select.selectByVisibleText(maptemp.get("pricing_model"));
	WaitForAjax();
	System.out.println("~~~~~~~~~~~~~~~~~~1~~~~~~~~~~~~~~~~~~~~~~~~");
	
	//Governance
	id = "ddlGovernanceOthers_"+rowno;
	select = new  Select(driver.findElement(By.id(id)));
	select.selectByVisibleText(maptemp.get("governance"));
	WaitForAjax();
	System.out.println("~~~~~~~~~~~~~~~~~~~~2~~~~~~~~~~~~~~~~~~~~~~");
	
	//FTE
	id = "ddlTypeOfFTE_"+rowno;
	select = new  Select(driver.findElement(By.id(id)));
	select.selectByVisibleText(maptemp.get("FTE"));
	WaitForAjax();
	System.out.println("~~~~~~~~~~~~~~~~~~~~~3~~~~~~~~~~~~~~~~~~~~~");
	
	//Location 
	id = "ddlLocation_" +rowno;
	select = new  Select(driver.findElement(By.id(id)));
	select.selectByVisibleText(maptemp.get("Location"));
	WaitForAjax();
	System.out.println("~~~~~~~~~~~~~~~~~~~~~~4~~~~~~~~~~~~~~~~~~~~");
	
	//Customer role
	id= "ddlCustomerRole_"+rowno;
	select = new  Select(driver.findElement(By.id(id)));
	select.selectByVisibleText(maptemp.get("customer_role"));
	//Add the role to customer role list for validation of Rate card page
	cust_roles.add(maptemp.get("customer_role"));
	//id = "lblServiceType_"+rowno;
	//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	WaitForAjax();
	System.out.println("~~~~~~~~~~~~~~~~~~~~5~~~~~~~~~~~~~~~~~~~~~~");
	
	//Phases
	id = "ddlPhases_"+rowno;
	
	select = new  Select(driver.findElement(By.id(id)));
	select.selectByVisibleText(maptemp.get("phases"));
	WaitForAjax();
	System.out.println("~~~~~~~~~~~~~~~~~~~~6~~~~~~~~~~~~~~~~~~~~~~");
	driver.findElement(By.id(id)).sendKeys(Keys.TAB);
	
	//Contract buy rate
	id = "txtContractorBuyRate_"+rowno;
	if(driver.findElement(By.id(id)).isEnabled()){
		WebElement element = driver.findElement(By.id(id));
		wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id(id)));
		element.click();
		element.sendKeys(maptemp.get("contr_rate"));
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~7~~~~~~~~~~~~~~~~~~~~");
		driver.findElement(By.id(id)).sendKeys(Keys.TAB);
	}
	
	
	//Rebadge salary
	id= "txtRebadgeSalary_"+rowno;
	
	if(driver.findElement(By.id(id)).isEnabled()){
		
		WebElement element = driver.findElement(By.id(id));
		wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id(id)));
		element.click();
		element.sendKeys(maptemp.get("rebadge_rate"));
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~8~~~~~~~~~~~~~~~~~~~~");
		driver.findElement(By.id(id)).sendKeys(Keys.TAB);
	}
	
}
 
public void WaitForAjax() throws InterruptedException
{
	int counter =0;
    while (counter<10)
    {
        if ((Boolean) ((JavascriptExecutor)driver).executeScript("return jQuery.active == 0")){
            break;
        }
        Thread.sleep(100);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~WAIT~~~~~~~~~~~~~~~~~~~");
        counter++;
        
    }
}

@Then("^Click Sales Price link And Enter details of Sales Price and save$")
public void click_Sales_Price_link_And_Enter_details_of_Sales_Price_and_save() throws Throwable {
    // Open the sales price window and enter the details
	if(mapData.get("sales_price_include").equals("Yes")){	
	driver.findElement(By.xpath("//div/table/tbody/tr/th/a[@id='lnkSalePrice']")).click();
	mainWindowHandler = driver.getWindowHandle();
	wait = new WebDriverWait(driver,30);
	wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//div/iframe[@class='ms-dlgFrame']")));
	rows = Integer.parseInt(mapData.get("Insert_row"));
	int row = rows+1;
	for(int i=2; i<=row; i++){
		String name = "ctl00$PlaceHolderMain$gvRampupSalePrice$ctl0"+i+"$txtSalePrice";
		String key = "sale_price"+(i-1);
		WebElement element = driver.findElement(By.name(name));
		element.clear(); element.sendKeys(mapData.get(key));
	}
	
	//Click save button
	driver.findElement(By.id("btnSave")).click();
	wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div/iframe[@class='ms-dlgFrame']")));
	driver.switchTo().window(mainWindowHandler);
  }
}

@Then("^Click Year wise link And Enter details of Yearly as per Workbook \"([^\"]*)\" and Sheet \"([^\"]*)\"$")
public void click_Year_wise_link_And_Enter_details_of_Yearly_as_per_Datset_from_Workbook_and_Sheet(String workbook, String sheet) throws Throwable {
    // Enter the year wise data
	wait = new WebDriverWait(driver,30);
	wait.until(ExpectedConditions.titleIs("Smart Deal - RampUp"));
	mainWindowHandler = driver.getWindowHandle();
	for(int i =1 ; i<=contractyear ; i++){
		
		String path = "//div/table/tbody/tr/th/a[@id='lnkY"+i +"']";
		String dataset_1 = "Y"+i;
		driver.findElement(By.xpath(path)).click();
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//div/iframe[@class='ms-dlgFrame']")));
		
		for(int j =1 ; j<=rows ; j++){
			
			String dataset_2 = "R" + j;
			String dataset = dataset_1 + dataset_2;
		
			mapData = excel.getTestData(workbook, sheet, dataset);
			if(mapData!=null){
				// Then perform the operation otherwise continue the code
				for(int k =1 ; k<=12 ; k++){
					String month = "M"+k;
				String id = "ctl00_PlaceHolderMain_gvRampupYear_ctl0"+(j+1)+"_txtM"+k;
				WebElement element = driver.findElement(By.id(id));
				element.clear(); element.sendKeys(mapData.get(month));
				}
			}
			
			
		}
		driver.findElement(By.id("ctl00_PlaceHolderMain_btnSave")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div/iframe[@class='ms-dlgFrame']")));
		driver.switchTo().window(mainWindowHandler);
		wait.until(ExpectedConditions.titleIs("Smart Deal - RampUp"));
	}
 }

@When("^Click Next button on Ramp Up page$")
public void click_Next_button_on_Ramp_Up_page() throws Throwable {
    // Click the next  button on ramp up page if rebadge tab is there then move to rebadge page
	wait = new WebDriverWait(driver,20);
	wait.until(ExpectedConditions.titleIs("Smart Deal - RampUp"));
	driver.findElement(By.id("btnRampupNext")).click();
  
}

@Then("^Navigates to Rebadge page$")
public void navigates_to_Rebadge_page() throws Throwable {
    // Verify rebadge page open
	wait = new WebDriverWait(driver,30);
	wait.until(ExpectedConditions.titleIs("Smart Deal - Rebadge"));
}

@And("^Enter Retension or KT Bonus$")
public void enter_Retension_or_KT_Bonus() throws Throwable {
    // Enter values for Retension bonus per FTE values
	wait = new WebDriverWait(driver,30);
	wait.until(ExpectedConditions.titleIs("Smart Deal - Rebadge"));
	
	WebElement element = driver.findElement(By.id("txtRetentionBounsAmount"));
	element.clear();element.sendKeys(mapData.get("Retention_Bonus_Amount"));
	
	element = driver.findElement(By.id("txtRetentionBounsNoofFTE"));
	element.clear();element.sendKeys(mapData.get("Retention_Bonus_FTE"));
	
	Select select = new Select(driver.findElement(By.id("ddlRetentionBounsAmortized")));
	select.selectByVisibleText(mapData.get("Retention_Bonus_Amortized"));
	
	element = driver.findElement(By.id("txtRetentionBounsAbsorved"));
	element.clear();element.sendKeys(mapData.get("Retention_Bonus_Absorbed"));
	
	//Enter values of KT Bonus per FTE 
	element = driver.findElement(By.id("txtKTBonusAmount"));
	element.clear();element.sendKeys(mapData.get("KT_Bonus_Amount"));
	
	element = driver.findElement(By.id("txtKTBonusNoofFTE"));
	element.clear();element.sendKeys(mapData.get("KT_Bonus_FTE"));
	
	select = new Select(driver.findElement(By.id("ddlKTBonusAmortized")));
	select.selectByVisibleText(mapData.get("KT_Bonus_Amortized"));
	
	element = driver.findElement(By.id("txtKTBonusAbsorved"));
	element.clear();element.sendKeys(mapData.get("KT_Bonus_Absorbed"));
}

@And("^Enter Notice period and Severence$")
public void enter_Notice_period_and_Severence() throws Throwable {
    // Enter notice period and severence. Currently all location have same notice and severence. 
	//Need to update logic for different location
	
	// currently not implemented
}

@When("^Click Save and Next on Rebadge page$")
public void click_Save_and_Next_on_Rebadge_page() throws Throwable {
    // click save and next button
	wait = new WebDriverWait(driver,30);
	wait.until(ExpectedConditions.titleIs("Smart Deal - Rebadge"));
	
	driver.findElement(By.id("btnSaveNext")).click();
			
}

@Then("^Navigates to Rate Card page$")
public void navigates_to_Rate_Card_page() throws Throwable {
    // Verify Rate card page open
	wait = new WebDriverWait(driver,60);
	wait.until(ExpectedConditions.titleIs("Smart Deal - RateCard"));
	
}


@And("^Validate Rate Card Table Data For Given Workbook \"([^\"]*)\" and Sheet \"([^\"]*)\"$")
public void validate_Rate_Card_Table_Data_For_Given_Workbook_and_Sheet(String workbook, String worksheet) throws Throwable {
    
	List<WebElement> rows = driver.findElements(By.xpath("//div/table[@id='gvRateCard']/tbody/tr"));
	ArrayList<String> row_data = new ArrayList<String>();
	System.out.println(">>>>"+cust_roles+cust_roles.size());
	//Get the no. of rows, including header
	int row_count = rows.size(); System.out.println("###############################"+row_count);
	assertTrue("ERROR::::::RAMP UP Rows Not Matched With Rate Card Rows",cust_roles.size()==(row_count-1));
	for(int i =2 ; i<=row_count ; i++){
		//Get the no. of columns
		System.out.println(">>>>"+i);
		
		List<WebElement> columns = driver.findElements(By.xpath("//div/table[@id='gvRateCard']/tbody/tr["+i+"]/td"));
		int column_count = columns.size();
		System.out.println(">>>>COLUMNSIZE:"+column_count);
		for(int j=1 ; j<=column_count; j++){
			System.out.println("<<<<"+j);
			WebElement col_data = driver.findElement(By.xpath("//div/table[@id='gvRateCard']/tbody/tr["+i+"]/td["+j+"]/span"));
			String values = col_data.getText(); System.out.println(values);
			if(values!=null)
				row_data.add(values);
		}
		System.out.println(">>>>"+row_data);
		//Match the customer role from customer role list
		//Take a temp list to hold the customer role list
		List<String> temp_role = new ArrayList<String>(cust_roles);
		temp_role.retainAll(row_data); // Now temp_role hold only matched role name
		System.out.println(">>>>>>>"+temp_role);
		//User the role name and push the data to excel based on role column in excel
		//Call the excel util function to save the data on sheet based on column  value
		String dataset = temp_role.get(0).trim()+"_Actual";
		System.out.println("TO BE FIND:::"+dataset);
		excel.setExcelRows(workbook, worksheet, dataset, row_data);
		row_data.clear();
	}
	
	for(int i=0; i<cust_roles.size(); i++){
		String actualColumnName = cust_roles.get(i)+"_Actual";
		String expectedColumnName = cust_roles.get(i)+"_Expected";
		validationStatusRateCard = excel.columnValidation(actualColumnName, expectedColumnName, workbook, worksheet);
		//validation status have the information regarding all pass or fail of excel validation
		
	}

 }

@When("^Click Next button on Rate Card page$")
public void click_Next_button_on_Rate_Card_page() throws Throwable {
    // Click the next button on rate card page
	wait = new WebDriverWait(driver,30);
	wait.until(ExpectedConditions.titleIs("Smart Deal - RateCard"));
	
	driver.findElement(By.id("btnRatecardNext")).click();
}

@Then("^Navigates to PnL page$")
public void navigates_to_PnL_page() throws Throwable {
    // Wait until PnL page loads successfully 
	wait = new WebDriverWait(driver,60);
	wait.until(ExpectedConditions.titleIs("Smart Deal - PnL"));
}

@Then("^Validate PnL Table Data For Given Workbook \"([^\"]*)\" and Sheet \"([^\"]*)\"$")
public void validate_PnL_Table_Data_For_Given_Workbook_and_Sheet(String workbook, String worksheet) throws Throwable {
    // Validate the PnL rows year wise with given expected result in excel passed in parameters
	ArrayList<String> columnDataYear = new ArrayList<String>();
	ArrayList<String> columnTotal = new ArrayList<String>();
	String id = null;
	String datasetinit = "Y";
	
	//Store the name of data excel used for validation
		finalTestDataExcel=workbook;
		
		
	List<WebElement> rows = driver.findElements(By.xpath("//div/table[@id='pnlTable']/tbody/tr"));
	
	//Get the no. of rows, including header
	int row_count = rows.size(); System.out.println("###############################"+row_count);
	//Workaround for hidden rows or all data fields have some value
	for(int j=1 ; j<=row_count; j++){
		try{
		driver.findElement(By.xpath("//table[@id='pnlTable']/tbody/tr["+j+"]/td[1]")).click();
		}catch(Exception e){
			continue;
		}	
	}
	for(int i=3; i<=contractyear+2; i++){
		
		for(int j=2 ; j<=row_count; j++){
			if(j==3){
			WebElement cell_data = driver.findElement(By.xpath("//table[@id='pnlTable']/tbody/tr["+j+"]/td["+i+"]/input"));
			columnDataYear.add(cell_data.getAttribute("value"));
			}
			else{
				WebElement cell_data = driver.findElement(By.xpath("//table[@id='pnlTable']/tbody/tr["+j+"]/td["+i+"]/span"));
				columnDataYear.add(cell_data.getText());
			}
		}
		
		String dataset = datasetinit+(i-2)+"_Actual";
		System.out.println("TO BE FIND:::"+dataset);
		
		System.out.println(columnDataYear);
		excel.setExcelRows(workbook, worksheet, dataset, columnDataYear);
		columnDataYear.clear();
	}
	
	// Store the total year vales in array list
	for(int j=2 ; j<=row_count; j++){
			WebElement cell_data = driver.findElement(By.xpath("//table[@id='pnlTable']/tbody/tr["+j+"]/td[13]/span"));
			columnTotal.add(cell_data.getText());
		}
	String dataset = "Total_Actual";
	System.out.println(columnTotal);
	excel.setExcelRows(workbook, worksheet, dataset, columnTotal);
	
	//Now compare columns year wise
	for(int i=1 ; i<=contractyear; i++){
		String actualColumnName = datasetinit + i +"_Actual";
		String expectedColumnName = datasetinit + i + "_Expected";
		validationStatusPnL = excel.columnValidation(actualColumnName, expectedColumnName, workbook, worksheet);
	}
	
	//Compare total with expected
	String columnini = "Total_";
	validationStatusPnL = excel.columnValidation(columnini+"Actual", columnini+"Expected", workbook, worksheet);

	
	
	// Use assert to verify the matching column function return value if true then match of columns fails
	//If false then all the columns rows matched
	
	assertFalse("RateCard Data Not Matched with Expected Result",validationStatusRateCard);
	assertFalse("PnL Data Not Matched with Expected Result",validationStatusPnL);
}	

	
}
