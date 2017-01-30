Feature: Basic Deal Creation with all mandatory fields
This Feature cover basic deal creation having all mandatory fields and perform basic field validation
All scenarios are data driver from excel. Browser and URL are also from data table. SPOC variable parameter in user story

@test
Scenario Outline: Basic deal creation with rebadge tab and all mandatory fields and validation

	Given Workbook "<WorkbookKIF>" Sheet "<SheetKIF>" and DataSet "<DatasetKIF>" 
	Given Browser name and navigate to Url
	When Click New Deal button
	Then Navigates to KIF page
	And Enter KIF page fields as per DataSet "<DatasetKIF>"
	When Click save button
	Then Deal Number Successfully Created
	When Click the SPOC button
	Then Enter SPOC details "<SPOC>"
	And Click save button on SPOC selection page
	Then verify SPOC Allocation section populated
	When click Next button on KIF page
	Then Navigates to SI page
	Given Workbook "<WorkbookSI>" Sheet "<SheetSI>" and DataSet "<DatasetSI>"
	And Enter basic SI page fields
	And Enter KT fields in SI page
	And Enter Additiona Expense fields in SI page
	And Enter Effort Overrun Contingencies fields in SI page
	When Click save and move to Role Creation button
	Then Navigates to Role Creation page
	And Add Customer Role as per Datset "<DatasetRC1>" from Workbook "<WorkbookRC>" and Sheet "<SheetRC>"
	And Add Customer Role as per Datset "<DatasetRC2>" from Workbook "<WorkbookRC>" and Sheet "<SheetRC>"
	And Add Customer Role as per Datset "<DatasetRC3>" from Workbook "<WorkbookRC>" and Sheet "<SheetRC>"
	When Click Rampup button
	Then Navigates to Ramp Up page
	And set Target Net Margin percent on Ramp up page as per Datset "<DatasetRU>" from Workbook "<WorkbookRU>" and Sheet "<SheetRU>"
	And Insert number of rows for multiple ramp up
	And Enter details of ramp up in Insert Ramp Up window from Workbook "<WorkbookRU>" and Sheet "<SheetRUINSERT>"
	When Click Insert on Ramp Up window
	Then Ramp up rows get saved and reflected on UI
	And Click Sales Price link And Enter details of Sales Price and save
	And Click Year wise link And Enter details of Yearly as per Workbook "<WorkbookRU>" and Sheet "<SheetRUYEAR>"
	When Click Next button on Ramp Up page
	Then Navigates to Rebadge page
	Given Workbook "<WorkbookRU>" Sheet "<SheetREBADGE>" and DataSet "<DatasetRB>"
	And Enter Retension or KT Bonus
	And Enter Notice period and Severence
	When Click Save and Next on Rebadge page
	Then Navigates to Rate Card page
	
	
	

Examples:
	|WorkbookKIF|SheetKIF|DatasetKIF|SPOC|WorkbookSI|SheetSI|DatasetSI|WorkbookRC|SheetRC|DatasetRC1|DatasetRC2|DatasetRC3|WorkbookRU|SheetRU|DatasetRU|SheetRUINSERT|SheetRUYEAR|
	|TEST_DATA|KIF|DS1|srivastavag|TEST_DATA|SI|DS1|TEST_DATA|RC|DS1|DS2|DS3|TEST_DATA|RU|DS1|RUINSERT|RU_YEAR|

@run	
Scenario Outline: Basic deal creation without rebadge tab and all mandatory fields and validation

	Given Workbook "<Workbook>" Sheet "<SheetKIF>" and DataSet "<DatasetKIF>" 
	Given Browser name and navigate to Url
	When Click New Deal button
	Then Navigates to KIF page
	And Enter KIF page fields as per DataSet "<DatasetKIF>"
	When Click save button
	Then Deal Number Successfully Created
	When Click the SPOC button
	Then Enter SPOC details "<SPOC>"
	And Click save button on SPOC selection page
	Then verify SPOC Allocation section populated
	When click Next button on KIF page
	Then Navigates to SI page
	Given Workbook "<Workbook>" Sheet "<SheetSI>" and DataSet "<DatasetSI>"
	And Enter basic SI page fields
	And Enter KT fields in SI page
	And Enter Additiona Expense fields in SI page
	And Enter Effort Overrun Contingencies fields in SI page
	When Click save and move to Role Creation button
	Then Navigates to Role Creation page
	And Add Customer Role as per Datset "<DatasetRC1>" from Workbook "<Workbook>" and Sheet "<SheetRC>"
	And Add Customer Role as per Datset "<DatasetRC2>" from Workbook "<Workbook>" and Sheet "<SheetRC>"
	And Add Customer Role as per Datset "<DatasetRC3>" from Workbook "<Workbook>" and Sheet "<SheetRC>"
	When Click Rampup button
	Then Navigates to Ramp Up page
	And Insert number of rows for multiple ramp up as per Datset "<DatasetRU>" from Workbook "<Workbook>" and Sheet "<SheetRU>"
	And Enter details of ramp up in Insert Ramp Up window from Workbook "<Workbook>" and Sheet "<SheetRUINSERT>"
	When Click Insert on Ramp Up window
	Then Ramp up rows get saved and reflected on UI
	When Click Next button on Ramp Up page
	Then Navigates to Rate Card page
	And Validate Rate Card Table Data For Given Workbook "<WorkbookDeal>" and Sheet "<SheetRateCard>"
	When Click Next button on Rate Card page
	Then Navigates to PnL page
	And Validate PnL Table Data For Given Workbook "<WorkbookDeal>" and Sheet "<SheetPnL>"
Examples:
	|Workbook|SheetKIF|DatasetKIF|SPOC|SheetSI|DatasetSI|SheetRC|DatasetRC1|DatasetRC2|DatasetRC3|SheetRU|DatasetRU|SheetRUINSERT|WorkbookDeal|SheetRateCard|SheetPnL|
	|TEST_DATA|KIF|DS2|srivastavag|SI|DS2|RC|DS4|DS5|DS6|RU|DS2|RUINSERT|TEST_DEAL_01|RateCardData|PnLData|
	
@dry
Scenario Outline: Basic deal creation without rebadge tab and all mandatory fields and validation	

	Given Workbook "<Workbook>" Sheet "<SheetKIF>" and DataSet "<DatasetKIF>" 
	Given Browser name and navigate to Url
	When Click New Deal button
	Then Navigates to KIF page
	And Enter KIF page fields as per DataSet "<DatasetKIF>"
	When Click save button
Examples:
	|Workbook|SheetKIF|DatasetKIF|
	|TEST_DATA|KIF|DS2|srivastavag|