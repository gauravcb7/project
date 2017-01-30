package com.smartdeal.steps;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class DriverFactory {
	protected static WebDriver driver;
	private enum Browser{
		IE, FF, CHROME;
	}

	public DriverFactory(String browser){
		initialize(browser);
	}
	
	public DriverFactory() {
		initialize("KILL");
	}

	public void initialize(String browser){
		if(driver==null)
			createDriverInstance(browser);
	}
	
	public void createDriverInstance(String browser){
		Browser br = Browser.valueOf(browser);
		switch(br){
		
		case IE :   System.setProperty("webdriver.ie.driver", "C:\\Users\\srivastavag\\Downloads\\IEDriverServer_Win32_2.45.0\\IEDriverServer.exe");
					DesiredCapabilities dc = new DesiredCapabilities();
					dc.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
					driver= new InternetExplorerDriver(dc);
					driver.manage().deleteAllCookies();
					driver.manage().window().maximize();
					driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
					break;
		case FF :   //TBD
					break;
		case CHROME : //TBD
						break;
		default : System.out.println("%%%%%ERROR :"+ browser + "Not Supported!!!");				
		}
		
		
	}
	
	public WebDriver getDriver(){
		return driver;
	}
	
	public void destroyDriver(){
		driver.close();
        driver = null;
	}
}
