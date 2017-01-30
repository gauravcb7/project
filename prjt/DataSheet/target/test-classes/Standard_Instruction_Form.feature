Feature: Input and validate various fields of SI page
Test data for input fields fetch from data excel with corresponding field name. Please see the mapping.
To run any scenario(s) of this feature it is required to run the create deal KIF scenario

Scenario Outline: Enter all fields of SI page with input from excel and then save

	Given Workbook "<Workbook>" Sheet "<Sheet>" and DataSet "<Dataset>"
	Given User open Browser and navigate to Url
	When User open existing Deal "<Deal_no>"
	Then User click the SI tab
	And User select Pricing currency "<Price_Curr>"
	And User select Total contract period "<contr_period>"
	And User select Rebadging involved "<Rebadge>"
	And User select Towers Required "<Towers_Required>"
	And User select Contractor employees "<Contractor_employees>"
	And User select Pricing Inclusive / Exclusive of COLA "<Pricing_COLA>"
	And User select COLA Approval Required "<COLA_Approval>"
	And User enter COLA rate for Onsite/Nearshore "<COLA_Nearshore>"
	And User enter COLA rate for Offshore/On-Off "<COLA_Offshore>"
	And User select Laptop by HCL "<HCL_Laptop>"
	And User select Other Direct Cost "<Other_cost>"
	And User enter Nearshore "<Nearshore>"
	And User enter Offshore "<Offshore>"
	And User select InCountry Axon "<InCountry_Axon>"
	And User enter KT period "<KT_period>"
	And User select % of KT to be absorbed "<PER_KT>"
	And User select KT including in the deal term "<KT_Deal_Term>"
	And User select KT to be amortized "<KT_amortized>"
	And User enter KT Amortization Period "<KT_Amortization_Period>"
	
	 
Examples:
	|Workbook|Sheet|Dataset|Deal_no|
	|TEST_DATA|SI|DS1|Deal_no|



