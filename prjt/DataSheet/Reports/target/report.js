$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("Feature/01Basic_Deal_Creation.feature");
formatter.feature({
  "line": 1,
  "name": "Basic Deal Creation with all mandatory fields",
  "description": "This Feature cover basic deal creation having all mandatory fields and perform basic field validation\r\nAll scenarios are data driver from excel. Browser and URL are also from data table. SPOC variable parameter in user story",
  "id": "basic-deal-creation-with-all-mandatory-fields",
  "keyword": "Feature"
});
formatter.scenarioOutline({
  "line": 56,
  "name": "Basic deal creation without rebadge tab and all mandatory fields and validation",
  "description": "",
  "id": "basic-deal-creation-with-all-mandatory-fields;basic-deal-creation-without-rebadge-tab-and-all-mandatory-fields-and-validation",
  "type": "scenario_outline",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "line": 55,
      "name": "@run"
    }
  ]
});
formatter.step({
  "line": 58,
  "name": "Workbook \"\u003cWorkbook\u003e\" Sheet \"\u003cSheetKIF\u003e\" and DataSet \"\u003cDatasetKIF\u003e\"",
  "keyword": "Given "
});
formatter.step({
  "line": 59,
  "name": "Browser name and navigate to Url",
  "keyword": "Given "
});
formatter.step({
  "line": 60,
  "name": "Click New Deal button",
  "keyword": "When "
});
formatter.step({
  "line": 61,
  "name": "Navigates to KIF page",
  "keyword": "Then "
});
formatter.step({
  "line": 62,
  "name": "Enter KIF page fields as per DataSet \"\u003cDatasetKIF\u003e\"",
  "keyword": "And "
});
formatter.step({
  "line": 63,
  "name": "Click save button",
  "keyword": "When "
});
formatter.step({
  "line": 64,
  "name": "Deal Number Successfully Created",
  "keyword": "Then "
});
formatter.step({
  "line": 65,
  "name": "Click the SPOC button",
  "keyword": "When "
});
formatter.step({
  "line": 66,
  "name": "Enter SPOC details \"\u003cSPOC\u003e\"",
  "keyword": "Then "
});
formatter.step({
  "line": 67,
  "name": "Click save button on SPOC selection page",
  "keyword": "And "
});
formatter.step({
  "line": 68,
  "name": "verify SPOC Allocation section populated",
  "keyword": "Then "
});
formatter.step({
  "line": 69,
  "name": "click Next button on KIF page",
  "keyword": "When "
});
formatter.step({
  "line": 70,
  "name": "Navigates to SI page",
  "keyword": "Then "
});
formatter.step({
  "line": 71,
  "name": "Workbook \"\u003cWorkbook\u003e\" Sheet \"\u003cSheetSI\u003e\" and DataSet \"\u003cDatasetSI\u003e\"",
  "keyword": "Given "
});
formatter.step({
  "line": 72,
  "name": "Enter basic SI page fields",
  "keyword": "And "
});
formatter.step({
  "line": 73,
  "name": "Enter KT fields in SI page",
  "keyword": "And "
});
formatter.step({
  "line": 74,
  "name": "Enter Additiona Expense fields in SI page",
  "keyword": "And "
});
formatter.step({
  "line": 75,
  "name": "Enter Effort Overrun Contingencies fields in SI page",
  "keyword": "And "
});
formatter.step({
  "line": 76,
  "name": "Click save and move to Role Creation button",
  "keyword": "When "
});
formatter.step({
  "line": 77,
  "name": "Navigates to Role Creation page",
  "keyword": "Then "
});
formatter.step({
  "line": 78,
  "name": "Add Customer Role as per Datset \"\u003cDatasetRC1\u003e\" from Workbook \"\u003cWorkbook\u003e\" and Sheet \"\u003cSheetRC\u003e\"",
  "keyword": "And "
});
formatter.step({
  "line": 79,
  "name": "Add Customer Role as per Datset \"\u003cDatasetRC2\u003e\" from Workbook \"\u003cWorkbook\u003e\" and Sheet \"\u003cSheetRC\u003e\"",
  "keyword": "And "
});
formatter.step({
  "line": 80,
  "name": "Add Customer Role as per Datset \"\u003cDatasetRC3\u003e\" from Workbook \"\u003cWorkbook\u003e\" and Sheet \"\u003cSheetRC\u003e\"",
  "keyword": "And "
});
formatter.step({
  "line": 81,
  "name": "Click Rampup button",
  "keyword": "When "
});
formatter.step({
  "line": 82,
  "name": "Navigates to Ramp Up page",
  "keyword": "Then "
});
formatter.step({
  "line": 83,
  "name": "Insert number of rows for multiple ramp up as per Datset \"\u003cDatasetRU\u003e\" from Workbook \"\u003cWorkbook\u003e\" and Sheet \"\u003cSheetRU\u003e\"",
  "keyword": "And "
});
formatter.step({
  "line": 84,
  "name": "Enter details of ramp up in Insert Ramp Up window from Workbook \"\u003cWorkbook\u003e\" and Sheet \"\u003cSheetRUINSERT\u003e\"",
  "keyword": "And "
});
formatter.step({
  "line": 85,
  "name": "Click Insert on Ramp Up window",
  "keyword": "When "
});
formatter.step({
  "line": 86,
  "name": "Ramp up rows get saved and reflected on UI",
  "keyword": "Then "
});
formatter.step({
  "line": 87,
  "name": "Click Next button on Ramp Up page",
  "keyword": "When "
});
formatter.step({
  "line": 88,
  "name": "Navigates to Rate Card page",
  "keyword": "Then "
});
formatter.step({
  "line": 89,
  "name": "Validate Rate Card Table Data For Given Workbook \"\u003cWorkbookDeal\u003e\" and Sheet \"\u003cSheetRateCard\u003e\"",
  "keyword": "And "
});
formatter.step({
  "line": 90,
  "name": "Click Next button on Rate Card page",
  "keyword": "When "
});
formatter.step({
  "line": 91,
  "name": "Navigates to PnL page",
  "keyword": "Then "
});
formatter.step({
  "line": 92,
  "name": "Validate PnL Table Data For Given Workbook \"\u003cWorkbookDeal\u003e\" and Sheet \"\u003cSheetPnL\u003e\"",
  "keyword": "And "
});
formatter.examples({
  "line": 93,
  "name": "",
  "description": "",
  "id": "basic-deal-creation-with-all-mandatory-fields;basic-deal-creation-without-rebadge-tab-and-all-mandatory-fields-and-validation;",
  "rows": [
    {
      "cells": [
        "Workbook",
        "SheetKIF",
        "DatasetKIF",
        "SPOC",
        "SheetSI",
        "DatasetSI",
        "SheetRC",
        "DatasetRC1",
        "DatasetRC2",
        "DatasetRC3",
        "SheetRU",
        "DatasetRU",
        "SheetRUINSERT",
        "WorkbookDeal",
        "SheetRateCard",
        "SheetPnL"
      ],
      "line": 94,
      "id": "basic-deal-creation-with-all-mandatory-fields;basic-deal-creation-without-rebadge-tab-and-all-mandatory-fields-and-validation;;1"
    },
    {
      "cells": [
        "TEST_DATA",
        "KIF",
        "DS2",
        "srivastavag",
        "SI",
        "DS2",
        "RC",
        "DS4",
        "DS5",
        "DS6",
        "RU",
        "DS2",
        "RUINSERT",
        "TEST_DEAL_01",
        "RateCardData",
        "PnLData"
      ],
      "line": 95,
      "id": "basic-deal-creation-with-all-mandatory-fields;basic-deal-creation-without-rebadge-tab-and-all-mandatory-fields-and-validation;;2"
    }
  ],
  "keyword": "Examples"
});
formatter.scenario({
  "line": 95,
  "name": "Basic deal creation without rebadge tab and all mandatory fields and validation",
  "description": "",
  "id": "basic-deal-creation-with-all-mandatory-fields;basic-deal-creation-without-rebadge-tab-and-all-mandatory-fields-and-validation;;2",
  "type": "scenario",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "line": 55,
      "name": "@run"
    }
  ]
});
formatter.step({
  "line": 58,
  "name": "Workbook \"TEST_DATA\" Sheet \"KIF\" and DataSet \"DS2\"",
  "matchedColumns": [
    0,
    1,
    2
  ],
  "keyword": "Given "
});
formatter.step({
  "line": 59,
  "name": "Browser name and navigate to Url",
  "keyword": "Given "
});
formatter.step({
  "line": 60,
  "name": "Click New Deal button",
  "keyword": "When "
});
formatter.step({
  "line": 61,
  "name": "Navigates to KIF page",
  "keyword": "Then "
});
formatter.step({
  "line": 62,
  "name": "Enter KIF page fields as per DataSet \"DS2\"",
  "matchedColumns": [
    2
  ],
  "keyword": "And "
});
formatter.step({
  "line": 63,
  "name": "Click save button",
  "keyword": "When "
});
formatter.step({
  "line": 64,
  "name": "Deal Number Successfully Created",
  "keyword": "Then "
});
formatter.step({
  "line": 65,
  "name": "Click the SPOC button",
  "keyword": "When "
});
formatter.step({
  "line": 66,
  "name": "Enter SPOC details \"srivastavag\"",
  "matchedColumns": [
    3
  ],
  "keyword": "Then "
});
formatter.step({
  "line": 67,
  "name": "Click save button on SPOC selection page",
  "keyword": "And "
});
formatter.step({
  "line": 68,
  "name": "verify SPOC Allocation section populated",
  "keyword": "Then "
});
formatter.step({
  "line": 69,
  "name": "click Next button on KIF page",
  "keyword": "When "
});
formatter.step({
  "line": 70,
  "name": "Navigates to SI page",
  "keyword": "Then "
});
formatter.step({
  "line": 71,
  "name": "Workbook \"TEST_DATA\" Sheet \"SI\" and DataSet \"DS2\"",
  "matchedColumns": [
    0,
    4,
    5
  ],
  "keyword": "Given "
});
formatter.step({
  "line": 72,
  "name": "Enter basic SI page fields",
  "keyword": "And "
});
formatter.step({
  "line": 73,
  "name": "Enter KT fields in SI page",
  "keyword": "And "
});
formatter.step({
  "line": 74,
  "name": "Enter Additiona Expense fields in SI page",
  "keyword": "And "
});
formatter.step({
  "line": 75,
  "name": "Enter Effort Overrun Contingencies fields in SI page",
  "keyword": "And "
});
formatter.step({
  "line": 76,
  "name": "Click save and move to Role Creation button",
  "keyword": "When "
});
formatter.step({
  "line": 77,
  "name": "Navigates to Role Creation page",
  "keyword": "Then "
});
formatter.step({
  "line": 78,
  "name": "Add Customer Role as per Datset \"DS4\" from Workbook \"TEST_DATA\" and Sheet \"RC\"",
  "matchedColumns": [
    0,
    6,
    7
  ],
  "keyword": "And "
});
formatter.step({
  "line": 79,
  "name": "Add Customer Role as per Datset \"DS5\" from Workbook \"TEST_DATA\" and Sheet \"RC\"",
  "matchedColumns": [
    0,
    6,
    8
  ],
  "keyword": "And "
});
formatter.step({
  "line": 80,
  "name": "Add Customer Role as per Datset \"DS6\" from Workbook \"TEST_DATA\" and Sheet \"RC\"",
  "matchedColumns": [
    0,
    6,
    9
  ],
  "keyword": "And "
});
formatter.step({
  "line": 81,
  "name": "Click Rampup button",
  "keyword": "When "
});
formatter.step({
  "line": 82,
  "name": "Navigates to Ramp Up page",
  "keyword": "Then "
});
formatter.step({
  "line": 83,
  "name": "Insert number of rows for multiple ramp up as per Datset \"DS2\" from Workbook \"TEST_DATA\" and Sheet \"RU\"",
  "matchedColumns": [
    0,
    10,
    11
  ],
  "keyword": "And "
});
formatter.step({
  "line": 84,
  "name": "Enter details of ramp up in Insert Ramp Up window from Workbook \"TEST_DATA\" and Sheet \"RUINSERT\"",
  "matchedColumns": [
    0,
    12
  ],
  "keyword": "And "
});
formatter.step({
  "line": 85,
  "name": "Click Insert on Ramp Up window",
  "keyword": "When "
});
formatter.step({
  "line": 86,
  "name": "Ramp up rows get saved and reflected on UI",
  "keyword": "Then "
});
formatter.step({
  "line": 87,
  "name": "Click Next button on Ramp Up page",
  "keyword": "When "
});
formatter.step({
  "line": 88,
  "name": "Navigates to Rate Card page",
  "keyword": "Then "
});
formatter.step({
  "line": 89,
  "name": "Validate Rate Card Table Data For Given Workbook \"TEST_DEAL_01\" and Sheet \"RateCardData\"",
  "matchedColumns": [
    13,
    14
  ],
  "keyword": "And "
});
formatter.step({
  "line": 90,
  "name": "Click Next button on Rate Card page",
  "keyword": "When "
});
formatter.step({
  "line": 91,
  "name": "Navigates to PnL page",
  "keyword": "Then "
});
formatter.step({
  "line": 92,
  "name": "Validate PnL Table Data For Given Workbook \"TEST_DEAL_01\" and Sheet \"PnLData\"",
  "matchedColumns": [
    13,
    15
  ],
  "keyword": "And "
});
formatter.match({
  "arguments": [
    {
      "val": "TEST_DATA",
      "offset": 10
    },
    {
      "val": "KIF",
      "offset": 28
    },
    {
      "val": "DS2",
      "offset": 46
    }
  ],
  "location": "testStepKIF.workbook_Sheet_and_DataSet(String,String,String)"
});
formatter.result({
  "duration": 2197744074,
  "status": "passed"
});
formatter.match({
  "location": "testStepKIF.browser_name_and_navigate_to_Url()"
});
formatter.result({
  "duration": 40427867354,
  "error_message": "org.openqa.selenium.NoSuchWindowException: Unable to find element on closed window (WARNING: The server did not provide any stacktrace information)\nCommand duration or timeout: 16 milliseconds\nBuild info: version: \u00272.53.0\u0027, revision: \u002735ae25b\u0027, time: \u00272016-03-15 16:57:40\u0027\nSystem info: host: \u0027DS-7071BC8FF3F4\u0027, ip: \u0027127.0.0.1\u0027, os.name: \u0027Windows 7\u0027, os.arch: \u0027x86\u0027, os.version: \u00276.1\u0027, java.version: \u00271.8.0_92\u0027\nDriver info: org.openqa.selenium.ie.InternetExplorerDriver\nCapabilities [{browserAttachTimeout\u003d0, enablePersistentHover\u003dtrue, ie.forceCreateProcessApi\u003dfalse, ie.usePerProcessProxy\u003dfalse, ignoreZoomSetting\u003dfalse, handlesAlerts\u003dtrue, version\u003d11, platform\u003dWINDOWS, nativeEvents\u003dtrue, ie.ensureCleanSession\u003dfalse, elementScrollBehavior\u003d0, ie.browserCommandLineSwitches\u003d, requireWindowFocus\u003dfalse, browserName\u003dinternet explorer, initialBrowserUrl\u003dhttp://localhost:39537/, takesScreenshot\u003dtrue, javascriptEnabled\u003dtrue, ignoreProtectedModeSettings\u003dfalse, enableElementCacheCleanup\u003dtrue, cssSelectorsEnabled\u003dtrue, unexpectedAlertBehaviour\u003dignore}]\nSession ID: c79ac752-0a4f-4fb0-959f-efb82b4ddaeb\n*** Element info: {Using\u003dxpath, value\u003d//div/select[@id\u003d\u0027ctl00_PlaceHolderMain_ClaimsLogonSelector\u0027]}\r\n\tat sun.reflect.GeneratedConstructorAccessor20.newInstance(Unknown Source)\r\n\tat sun.reflect.DelegatingConstructorAccessorImpl.newInstance(Unknown Source)\r\n\tat java.lang.reflect.Constructor.newInstance(Unknown Source)\r\n\tat org.openqa.selenium.remote.ErrorHandler.createThrowable(ErrorHandler.java:206)\r\n\tat org.openqa.selenium.remote.ErrorHandler.throwIfResponseFailed(ErrorHandler.java:158)\r\n\tat org.openqa.selenium.remote.RemoteWebDriver.execute(RemoteWebDriver.java:678)\r\n\tat org.openqa.selenium.remote.RemoteWebDriver.findElement(RemoteWebDriver.java:363)\r\n\tat org.openqa.selenium.remote.RemoteWebDriver.findElementByXPath(RemoteWebDriver.java:500)\r\n\tat org.openqa.selenium.By$ByXPath.findElement(By.java:361)\r\n\tat org.openqa.selenium.remote.RemoteWebDriver.findElement(RemoteWebDriver.java:355)\r\n\tat com.stepDefinition.testStepKIF.browser_name_and_navigate_to_Url(testStepKIF.java:101)\r\n\tat ✽.Given Browser name and navigate to Url(Feature/01Basic_Deal_Creation.feature:59)\r\n",
  "status": "failed"
});
formatter.match({
  "location": "testStepKIF.click_New_Deal_button()"
});
formatter.result({
  "status": "skipped"
});
formatter.match({
  "location": "testStepKIF.navigates_to_KIF_page()"
});
formatter.result({
  "status": "skipped"
});
formatter.match({
  "arguments": [
    {
      "val": "DS2",
      "offset": 38
    }
  ],
  "location": "testStepKIF.enter_KIF_page_fields_as_per_DataSet(String)"
});
formatter.result({
  "status": "skipped"
});
formatter.match({
  "location": "testStepKIF.click_save_button()"
});
formatter.result({
  "status": "skipped"
});
formatter.match({
  "location": "testStepKIF.deal_number_successfully_Created()"
});
formatter.result({
  "status": "skipped"
});
formatter.match({
  "location": "testStepKIF.click_the_SPOC_button()"
});
formatter.result({
  "status": "skipped"
});
formatter.match({
  "arguments": [
    {
      "val": "srivastavag",
      "offset": 20
    }
  ],
  "location": "testStepKIF.enter_SPOC_details(String)"
});
formatter.result({
  "status": "skipped"
});
formatter.match({
  "location": "testStepKIF.click_save_button_on_SPOC_selection_page()"
});
formatter.result({
  "status": "skipped"
});
formatter.match({
  "location": "testStepKIF.verify_SPOC_Allocation_section_populated()"
});
formatter.result({
  "status": "skipped"
});
formatter.match({
  "location": "testStepKIF.click_Next_button_on_KIF_page()"
});
formatter.result({
  "status": "skipped"
});
formatter.match({
  "location": "testStepKIF.navigates_to_SI_page()"
});
formatter.result({
  "status": "skipped"
});
formatter.match({
  "arguments": [
    {
      "val": "TEST_DATA",
      "offset": 10
    },
    {
      "val": "SI",
      "offset": 28
    },
    {
      "val": "DS2",
      "offset": 45
    }
  ],
  "location": "testStepKIF.workbook_Sheet_and_DataSet(String,String,String)"
});
formatter.result({
  "status": "skipped"
});
formatter.match({
  "location": "testStepKIF.enter_basic_SI_page_fields()"
});
formatter.result({
  "status": "skipped"
});
formatter.match({
  "location": "testStepKIF.enter_KT_fields_in_SI_page()"
});
formatter.result({
  "status": "skipped"
});
formatter.match({
  "location": "testStepKIF.enter_Additiona_Expense_fields_in_SI_page()"
});
formatter.result({
  "status": "skipped"
});
formatter.match({
  "location": "testStepKIF.enter_Effort_Overrun_Contingencies_fields_in_SI_page()"
});
formatter.result({
  "status": "skipped"
});
formatter.match({
  "location": "testStepKIF.click_save_and_move_to_Role_Creation_button()"
});
formatter.result({
  "status": "skipped"
});
formatter.match({
  "location": "testStepKIF.navigates_to_Role_Creation_page()"
});
formatter.result({
  "status": "skipped"
});
formatter.match({
  "arguments": [
    {
      "val": "DS4",
      "offset": 33
    },
    {
      "val": "TEST_DATA",
      "offset": 53
    },
    {
      "val": "RC",
      "offset": 75
    }
  ],
  "location": "testStepKIF.add_Customer_Role_as_per_Datset_from_Workbook_and_Sheet(String,String,String)"
});
formatter.result({
  "status": "skipped"
});
formatter.match({
  "arguments": [
    {
      "val": "DS5",
      "offset": 33
    },
    {
      "val": "TEST_DATA",
      "offset": 53
    },
    {
      "val": "RC",
      "offset": 75
    }
  ],
  "location": "testStepKIF.add_Customer_Role_as_per_Datset_from_Workbook_and_Sheet(String,String,String)"
});
formatter.result({
  "status": "skipped"
});
formatter.match({
  "arguments": [
    {
      "val": "DS6",
      "offset": 33
    },
    {
      "val": "TEST_DATA",
      "offset": 53
    },
    {
      "val": "RC",
      "offset": 75
    }
  ],
  "location": "testStepKIF.add_Customer_Role_as_per_Datset_from_Workbook_and_Sheet(String,String,String)"
});
formatter.result({
  "status": "skipped"
});
formatter.match({
  "location": "testStepKIF.click_Rampup_button()"
});
formatter.result({
  "status": "skipped"
});
formatter.match({
  "location": "testStepKIF.navigates_to_Ramp_Up_page()"
});
formatter.result({
  "status": "skipped"
});
formatter.match({
  "arguments": [
    {
      "val": "DS2",
      "offset": 58
    },
    {
      "val": "TEST_DATA",
      "offset": 78
    },
    {
      "val": "RU",
      "offset": 100
    }
  ],
  "location": "testStepKIF.insert_number_of_rows_for_multiple_ramp_up(String,String,String)"
});
formatter.result({
  "status": "skipped"
});
formatter.match({
  "arguments": [
    {
      "val": "TEST_DATA",
      "offset": 65
    },
    {
      "val": "RUINSERT",
      "offset": 87
    }
  ],
  "location": "testStepKIF.enter_details_of_ramp_up_in_Insert_Ramp_Up_window(String,String)"
});
formatter.result({
  "status": "skipped"
});
formatter.match({
  "location": "testStepKIF.click_Insert_on_Ramp_Up_window()"
});
formatter.result({
  "status": "skipped"
});
formatter.match({
  "location": "testStepKIF.ramp_up_rows_get_saved_and_reflected_on_UI()"
});
formatter.result({
  "status": "skipped"
});
formatter.match({
  "location": "testStepKIF.click_Next_button_on_Ramp_Up_page()"
});
formatter.result({
  "status": "skipped"
});
formatter.match({
  "location": "testStepKIF.navigates_to_Rate_Card_page()"
});
formatter.result({
  "status": "skipped"
});
formatter.match({
  "arguments": [
    {
      "val": "TEST_DEAL_01",
      "offset": 50
    },
    {
      "val": "RateCardData",
      "offset": 75
    }
  ],
  "location": "testStepKIF.validate_Rate_Card_Table_Data_For_Given_Workbook_and_Sheet(String,String)"
});
formatter.result({
  "status": "skipped"
});
formatter.match({
  "location": "testStepKIF.click_Next_button_on_Rate_Card_page()"
});
formatter.result({
  "status": "skipped"
});
formatter.match({
  "location": "testStepKIF.navigates_to_PnL_page()"
});
formatter.result({
  "status": "skipped"
});
formatter.match({
  "arguments": [
    {
      "val": "TEST_DEAL_01",
      "offset": 44
    },
    {
      "val": "PnLData",
      "offset": 69
    }
  ],
  "location": "testStepKIF.validate_PnL_Table_Data_For_Given_Workbook_and_Sheet(String,String)"
});
formatter.result({
  "status": "skipped"
});
formatter.write("RATE CARD \u0026 PnL ACTUAL DATA SHEET : null");
formatter.write("Sceanrio Name:Basic deal creation without rebadge tab and all mandatory fields and validation");
formatter.after({
  "duration": 4205846,
  "error_message": "org.openqa.selenium.NoSuchWindowException: Unable to get browser (WARNING: The server did not provide any stacktrace information)\nCommand duration or timeout: 0 milliseconds\nBuild info: version: \u00272.53.0\u0027, revision: \u002735ae25b\u0027, time: \u00272016-03-15 16:57:40\u0027\nSystem info: host: \u0027DS-7071BC8FF3F4\u0027, ip: \u0027127.0.0.1\u0027, os.name: \u0027Windows 7\u0027, os.arch: \u0027x86\u0027, os.version: \u00276.1\u0027, java.version: \u00271.8.0_92\u0027\nDriver info: org.openqa.selenium.ie.InternetExplorerDriver\nCapabilities [{browserAttachTimeout\u003d0, enablePersistentHover\u003dtrue, ie.forceCreateProcessApi\u003dfalse, ie.usePerProcessProxy\u003dfalse, ignoreZoomSetting\u003dfalse, handlesAlerts\u003dtrue, version\u003d11, platform\u003dWINDOWS, nativeEvents\u003dtrue, ie.ensureCleanSession\u003dfalse, elementScrollBehavior\u003d0, ie.browserCommandLineSwitches\u003d, requireWindowFocus\u003dfalse, browserName\u003dinternet explorer, initialBrowserUrl\u003dhttp://localhost:39537/, takesScreenshot\u003dtrue, javascriptEnabled\u003dtrue, ignoreProtectedModeSettings\u003dfalse, enableElementCacheCleanup\u003dtrue, cssSelectorsEnabled\u003dtrue, unexpectedAlertBehaviour\u003dignore}]\nSession ID: c79ac752-0a4f-4fb0-959f-efb82b4ddaeb\r\n\tat sun.reflect.GeneratedConstructorAccessor20.newInstance(Unknown Source)\r\n\tat sun.reflect.DelegatingConstructorAccessorImpl.newInstance(Unknown Source)\r\n\tat java.lang.reflect.Constructor.newInstance(Unknown Source)\r\n\tat org.openqa.selenium.remote.ErrorHandler.createThrowable(ErrorHandler.java:206)\r\n\tat org.openqa.selenium.remote.ErrorHandler.throwIfResponseFailed(ErrorHandler.java:158)\r\n\tat org.openqa.selenium.remote.RemoteWebDriver.execute(RemoteWebDriver.java:678)\r\n\tat org.openqa.selenium.remote.RemoteWebDriver.execute(RemoteWebDriver.java:701)\r\n\tat org.openqa.selenium.remote.RemoteWebDriver.getCurrentUrl(RemoteWebDriver.java:326)\r\n\tat com.stepDefinition.testStepKIF.afterSceanrio(testStepKIF.java:57)\r\n\tat sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\r\n\tat sun.reflect.NativeMethodAccessorImpl.invoke(Unknown Source)\r\n\tat sun.reflect.DelegatingMethodAccessorImpl.invoke(Unknown Source)\r\n\tat java.lang.reflect.Method.invoke(Unknown Source)\r\n\tat cucumber.runtime.Utils$1.call(Utils.java:37)\r\n\tat cucumber.runtime.Timeout.timeout(Timeout.java:13)\r\n\tat cucumber.runtime.Utils.invoke(Utils.java:31)\r\n\tat cucumber.runtime.java.JavaHookDefinition.execute(JavaHookDefinition.java:60)\r\n\tat cucumber.runtime.Runtime.runHookIfTagsMatch(Runtime.java:223)\r\n\tat cucumber.runtime.Runtime.runHooks(Runtime.java:211)\r\n\tat cucumber.runtime.Runtime.runAfterHooks(Runtime.java:205)\r\n\tat cucumber.runtime.model.CucumberScenario.run(CucumberScenario.java:46)\r\n\tat cucumber.runtime.junit.ExecutionUnitRunner.run(ExecutionUnitRunner.java:91)\r\n\tat org.junit.runners.Suite.runChild(Suite.java:128)\r\n\tat org.junit.runners.Suite.runChild(Suite.java:27)\r\n\tat org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)\r\n\tat org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)\r\n\tat org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)\r\n\tat org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)\r\n\tat org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)\r\n\tat org.junit.runners.ParentRunner.run(ParentRunner.java:363)\r\n\tat cucumber.runtime.junit.ExamplesRunner.run(ExamplesRunner.java:59)\r\n\tat org.junit.runners.Suite.runChild(Suite.java:128)\r\n\tat org.junit.runners.Suite.runChild(Suite.java:27)\r\n\tat org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)\r\n\tat org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)\r\n\tat org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)\r\n\tat org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)\r\n\tat org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)\r\n\tat org.junit.runners.ParentRunner.run(ParentRunner.java:363)\r\n\tat cucumber.runtime.junit.ScenarioOutlineRunner.run(ScenarioOutlineRunner.java:53)\r\n\tat cucumber.runtime.junit.FeatureRunner.runChild(FeatureRunner.java:63)\r\n\tat cucumber.runtime.junit.FeatureRunner.runChild(FeatureRunner.java:18)\r\n\tat org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)\r\n\tat org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)\r\n\tat org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)\r\n\tat org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)\r\n\tat org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)\r\n\tat org.junit.runners.ParentRunner.run(ParentRunner.java:363)\r\n\tat cucumber.runtime.junit.FeatureRunner.run(FeatureRunner.java:70)\r\n\tat cucumber.api.junit.Cucumber.runChild(Cucumber.java:93)\r\n\tat cucumber.api.junit.Cucumber.runChild(Cucumber.java:37)\r\n\tat org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)\r\n\tat org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)\r\n\tat org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)\r\n\tat org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)\r\n\tat org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)\r\n\tat org.junit.runners.ParentRunner.run(ParentRunner.java:363)\r\n\tat cucumber.api.junit.Cucumber.run(Cucumber.java:98)\r\n\tat org.eclipse.jdt.internal.junit4.runner.JUnit4TestReference.run(JUnit4TestReference.java:86)\r\n\tat org.eclipse.jdt.internal.junit.runner.TestExecution.run(TestExecution.java:38)\r\n\tat org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.runTests(RemoteTestRunner.java:459)\r\n\tat org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.runTests(RemoteTestRunner.java:675)\r\n\tat org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.run(RemoteTestRunner.java:382)\r\n\tat org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.main(RemoteTestRunner.java:192)\r\n",
  "status": "failed"
});
});