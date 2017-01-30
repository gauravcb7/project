Feature: Create Deal Based On Test Data Import From Excel
All scenarios are data driver. Browser and URL are variable parameter in user story
@run
Scenario Outline: Create deal with all mandatory fields
In this scenario user enter the mandatory fields values and validates corresponding fields action
	
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
	Then User write the deal number to excel for DataSet "<Dataset>"
	When click Next button on KIF page
    	
Examples:
	|Workbook|Sheet|Dataset|Customer_Name|Customer_Vertical|Deal_Size|Deal_Type|RFP_RFI_Sales_Date|Vertical|G_2000_Rankings|Customer_side|EN_NN|RFP_RFI_Issue_Date|Geography|Submission_Date|Acc_Manager|Deliv_Spoc|Pre_Sale_Spoc|
	|TEST_DATA|KIF|DS1|Customer_Name|Customer_Vertical|Deal_Size|Deal_Type|Sales_Date|Vertical|G_2000_Rankings|Customer_side|EN_NN|Issue_date|Geography|Submission_Date|srivastavag|srivastavag|srivastavag|

Scenario Outline: Create large deal for EN and verify message displayed
In this scenario user select deal size created than 25-50Mn and EN/NN type as EN to verify the message displayed then create the deal

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
	And User enter About The Company "<About_Company>"
	And User enter Issue date "<RFP_RFI_Issue_Date>"
	And User select Geography "<Geography>"
	And User enter Submission date "<Submission_Date>"
	And User enter Competitors "<Competitors>"
	And User click Save button
	Then Deal Number Successfully Created

Examples:
	|Workbook|Sheet|Dataset|Customer_Name|Customer_Vertical|Deal_Size|Deal_Type|RFP_RFI_Sales_Date|Vertical|G_2000_Rankings|Customer_side|EN_NN|RFP_RFI_Issue_Date|Geography|Submission_Date|Competitors|About_Company|
	|TEST_DATA|KIF|DS2|Customer_Name|Customer_Vertical|Deal_Size|Deal_Type|Sales_Date|Vertical|G_2000_Rankings|Customer_side|EN_NN|Issue_date|Geography|Submission_Date|Competitors|About_Company|