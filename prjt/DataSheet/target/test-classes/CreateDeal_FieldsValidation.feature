Feature: Fields Validation while creating deals
scenarios where deal are created including fields validation

Scenario Outline: Create deal validating Customer Vertical and other fields
Create deal with Customer Vertical as EAS and also validate Vertical, G 2000Ranking,
and Third Party enabler
	
	Given User is on Home Page
	When User Navigate to Deal Page
	When User select Customer Vertical as "<Cust_Ver>"
	Then another related dropdown appear
	And User select "<Cust_Ver_Rel>" value from related dropdown
	When User select Vertical as other
	Then Specify other input field displayed
	When User select G 2000 ranking as Yes
	Then Rank input field displayed
	When User select Third party Enabler as other
	Then Specify other third party input field displayed
	And User enter remaining fields
	And User click Save button
	Then Message displayed Deal Successfully Created
	
	Examples:
    |Cust_Ver|Cust_Ver_Rel|
    |EAS|Maintenance/ Support|
    |SAP|Development/ Implementation|
    
    
    
    Given Workbook "<Workbook>" Sheet "<Sheet>" and DataSet "<Dataset>" 
	Given User open Browser and navigate to Url
	When User click New Deal button
	Then User navigates to KIF page
	And User enter Customer name "<Customer_Name>"
	And User select Customer vertical "<Customer_Vertical>"
	And User select Deal size "<Deal_Size>"
	And User select Deal type "<Deal_Type>"
	And User enter Sales date "<RFP_RFI_Sales_Date>"
	And User select Vertical "<Vertical>"
	And User select Ranking "<G_2000_Rankings>"
	And User select Customer side "<Customer_side>"
	And User select EN/NN "<EN_NN>"
	And User enter Issue date "<RFP_RFI_Issue_Date>"
	And User select Geography "<Geography>"
	And User enter Submission date "<Submission_Date>"
	And User click Save button
	Then Deal Number Successfully Created
	Then User click the SPOC button
	And User Select Account Manger "<Acc_Manager>"
	And User Select Delivery SPOC "<Deliv_Spoc>"
	And User Select Pre Sales SPOC "<Pre_Sale_Spoc>"
	Then User click save button on SPOC selection page 
	Then verify SPOC Allocation section populated
	When click Next button on KIF page
    	
  And Add Role Location as per Datset "<DatasetRC>" from Workbook "<WorkbookRC>" and Sheet "<SheetRC>"
	And Add Role Location as per Datset "<DatasetRC1>" from Workbook "<WorkbookRC>" and Sheet "<SheetRC>"  	
    	
    	@RunWith(Cucumber.class)
@Feature(value = "my/package/featureFoo.feature", tags={"@foo"})
@Feature(value = "my/package/featureBar.feature", tags={"@bar"})
or

@RunWith(Cucumber.class)
@Feature(value = "my/package/**/*.feature", tags={"@foo"})
@Feature(value = "my/other/package/**/*.feature", tags={"@bar"})