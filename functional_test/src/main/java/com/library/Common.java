package com.library;

import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.MultiTouchAction;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.WordUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import io.appium.java_client.android.*;

//import com.android.uiautomator.core.UiObjectNotFoundException;
//import com.android.uiautomator.core.UiScrollable;
//import com.android.uiautomator.core.UiSelector;
//import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class Common {

    ExtentReports extent;
    ExtentTest test;
  
    public static WebDriverWait wait = null;
   
    protected AndroidDriver<AndroidElement> driver = null;
     
    DesiredCapabilities dc = new DesiredCapabilities();
              
    public void setUp() {
      
      Properties Des = new Properties();
      InputStream input = null;

      try {
        input = new FileInputStream("./src/main/java/com/capabilities/DesiredCapabilities.properties");
        
        // load a properties file
        Des.load(input);      
        
        //update the UDID_number, Device_name and AVD_name in DesiredCapabilites properties file as per your device
        //dc.setCapability(MobileCapabilityType.UDID, Des.getProperty("UDID_number")); //Need for real device
        dc.setCapability(MobileCapabilityType.DEVICE_NAME, Des.getProperty("Device_name"));
        dc.setCapability(AndroidMobileCapabilityType.AVD, Des.getProperty("AVD_name")); // Applicable only for Emulator
        
        dc.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
        dc.setCapability(MobileCapabilityType.LANGUAGE, "en"); // Applicable only for Emulator
        dc.setCapability(MobileCapabilityType.LOCALE, "US"); // Applicable only for Emulator
        dc.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.jncb.mobile");
        dc.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, ".MainActivity");
        dc.setCapability(AndroidMobileCapabilityType.APP_WAIT_ACTIVITY, "com.jncb.mobile.*");
        dc.setCapability(AndroidMobileCapabilityType.DONT_STOP_APP_ON_RESET, true);
        dc.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS, true);
        //dc.setCapability(MobileCapabilityType.FULL_RESET, true); // to uninstall current apk
        
        driver = new AndroidDriver<AndroidElement>(new URL("http://localhost:4723/wd/hub"), dc);
        
        
     // Set my TestObject API Key
        /*
        dc.setCapability("testobjectApiKey", "0F2FA3005F2B4E6DAD4219368CCF01B6");
         
        // Dynamic device allocation of an iPhone 7, running iOS 10.3 device
        dc.setCapability("platformName", "Andorid");
        dc.setCapability("platformVersion", "7");
        dc.setCapability("deviceName", "iPhone 7");
        
        dc.setCapability("automationName", "uiautomator2");
  
        // Set allocation from private device pool only
        dc.setCapability("privateDevicesOnly", "true");
         
        // Set Application under test
        dc.setCapability("testobject_app_id", "1");
        dc.setCapability("name", "My Test 1!");
 
        // Set Appium version
        dc.setCapability("appiumVersion", "1.6.2");
             
        // Set Appium end point
        driver = new AndroidDriver<AndroidElement>(new URL("https://us1.appium.testobject.com/wd/hub"), dc);*/
      } catch (IOException io) {
        io.printStackTrace();
      }
    }

    //AndroidElement 
    public AndroidElement AndroidElementId(String identifier,String locator) {
    	
    	AndroidElement e = null;
    	switch (identifier) {
    	case "id":
    		e=driver.findElementById(locator);
    		break;
        case "className":
        	e=driver.findElementByClassName(locator);
        	break;
        case "name":
        	e=driver.findElementByName(locator);
        	break;       
        case "xpath":
        	e=driver.findElementByXPath(locator);
        	break;
        case "accessibilityId":
        	e=driver.findElementByAccessibilityId(locator);
        	break;
        case "androidUIAutomator":
        	e=driver.findElementByAndroidUIAutomator(locator);
        	break;
        default:
        	System.out.println("Locator not found");
        	e=null;
    	}
    	return e;
    }
   
    //AndroidElements
    public List<AndroidElement> AndroidElementIds(String identifier,String locator) {
    	
    	List<AndroidElement> e=null;
    	switch (identifier) {
    	case "id":
    		e=driver.findElementsById(locator);
    		break;
        case "className":
        	e=driver.findElementsByClassName(locator);
        	break;
        case "name":
        	e=driver.findElementsByName(locator);
        	break;      
        case "xpath":
        	e=driver.findElementsByXPath(locator);
        	break;
        case "accessibilityId":
        	e=driver.findElementsByAccessibilityId(locator);
        	break;
        case "androidUIAutomator":
        	e=driver.findElementsByAndroidUIAutomator(locator);
        	break;
        default:
        	System.out.println("Locator not found");
        	e=null;
    	}
    	return e;
    }
    
    //Send key method
    public void sendKeys(String identifier,String locator,String content) {
    	
    	AndroidElement e=AndroidElementId(identifier, locator);
    	e.sendKeys(content);
    }
        
    //Clear text  field method
    public void clearTextField(String identifier,String locator) {
    	
    	AndroidElement e=AndroidElementId(identifier, locator);
    	e.clear(); 
    }
    
    //click general method
    public void click(String identifier,String locator) {
    	
    	AndroidElement e=AndroidElementId(identifier, locator); 
    	e.click();
    }
        
    //Wait until the Element is present
    public void waitUntilElementPresent(String elementpath) {
    	
    	WebElement elementpresent=(new WebDriverWait(driver,60)).
    			until(ExpectedConditions.presenceOfElementLocated
    					(By.xpath(elementpath)));
    }
        
    //Verify Text // sunit change on 20/11/2017
    public void verifyText(String identifier,String locator,String text) {
    	
    	AndroidElement e=AndroidElementId(identifier, locator);
    	if (e.getText().trim().equals(text)) {
    		System.out.println(text+" text displayed");
    		//test.log(Status.INFO, text+" text displayed");
    		//return true;
    		Assert.assertTrue(true);
    	}
    	else {
    		System.out.println(text+" text did not displayed");
    		//test.log(Status.INFO, text+" text did not displayed");
    		//return false;
    		Assert.assertTrue(false);
    	}
    }
        
    //verify element present
    public boolean verifyElementPresent(String identifier,String locator) {
    	
    	AndroidElement e=AndroidElementId(identifier, locator);
    	if (e.isDisplayed()) {
    		return true;
    	}
    	else {
        return false;
    	}
    }
    
    //Thread sleep
    public void sleepThread(long sleeptime) {
      
      try {
    	  Thread.sleep(sleeptime);
      } catch (Exception e) {
    	  System.out.println(e);
      }
    }
               
    //capture screenshot
    public void screenShot() {
    	
    	// Take screenshot and store as a file format
    	File src= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    	
    	try {
    		// now copy the  screenshot under target folder
    		FileUtils.copyFile(src, new File("target/surefire-reports/screenshots/"+System.currentTimeMillis()+".png"));
    	} catch (IOException e) {
    		System.out.println(e.getMessage());
    	}
    }
    
    // M - Button Enabled
    public boolean isButtonEnabled(String identifier,String locator) {
    	
    	AndroidElement e=AndroidElementId(identifier, locator);
    	if (e.isEnabled()) {
    		return true;
    	}
    	else {
    		return false;
    	}
    }
       
    //Get the text of element
    public String getText(String identifier,String locator) {
    	
    	AndroidElement e=AndroidElementId(identifier, locator);
    	String msg=e.getText().trim();
    	return msg;
    }
        
    //send the keyboard key
    public void keyboardKey(int key) {
    	
    	driver.pressKeyCode(key);
    }
    
    //reset the count for a user, use this when want to unlock the password
    public void resetUser(String username) throws Exception {
    	
    	Process p = Runtime.getRuntime().exec(new String[] {"explorer", "http://hillevi:7070/passwordreset/api/user/reset/"+username});
    	sleepThread(1000);
    	p.destroy();
    }
    
    //verify element present
    public boolean compareTexts(String actual,String expected) {
    	
    	Assert.assertTrue(actual.equals(expected));
    	return false;
    }
    
    //Close App and quit
    public void quitObject() {
    	
    	driver.closeApp();
    	driver.quit();
    }
   
    //Scroll up or down the page
    public void scroll(String Direction,int Start,int End) {
    	
    	char a = '\"';
    	driver.executeScript("client:client.swipe("+ a + Direction + a + "," + Start + ","+ End +")");
    }
    
    // 
    public void count() throws Exception {
//    	List<AndroidElement> a = driver.findElementsByAndroidUIAutomator("new UiScrollable(new UiSelector().className(\"android.view.View\")).getChildCount("
//    	+ "new UiSelector().className(\"android.widget.TextView\"), \"merchantText\")");
    	
    	//List<AndroidElement> a2 = driver.findElementsByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).className(\"android.widget.TextView\"), \"merchantText\")).getChildCount()");
    	
    	//List<AndroidElement> a2 = driver.findElementsByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).className(\"android.widget.TextView\"), \"merchantText\").childSelector(new UiSelector())");
    	List<AndroidElement> a2 = driver.findElementsByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).getChildCount(new UiSelector().className(\"android.widget.TextView\").description(\"merchantText\"))");
    	System.out.println(a2);
	
	
//    	int settingsList = new UiScrollable(new UiSelector().scrollable(true)).getChildCount(new UiSelector().className("android.widget.TextView").description("merchantText"));
//    	boolean aaa = new UiScrollable(new UiSelector()).flingToBeginning(10);
    	
    
    			//.description("merchantText")).getChildCount();
//    	UiObject btItem = settingsList.getChildByText(new UiSelector().className(LinearLayout.class.getName()),"Bluetooth", true);
    	
//  MobileElement element = driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\"com.android.vending:id/tab_recycler_view\")).getChildByText("
//				+ "new UiSelector().className(\"android.widget.TextView\"), \"Games We Are Playing\")"));
//    	System.out.println(a);
//    	//return a;
    	
    	  //Created UI Object for list view
    	//int a1 = driver.findElementsByAndroidUIAutomator("new UiScrollable(new UiSelector().className("\android.widget.ListView\")");


    	//Printing the number of child ements present in the List View by using getchildCount() method
    	//System.out.println("List view elements : "+listview_elements.getChildCount());*
    }
    
    public boolean scrollForObject(String text) {
    	    	
    	driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(new UiSelector().textContains(\""+ text +"\"))");
    	return true;
    }
    
    public boolean scrollTotop(){

    	boolean fling = driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).flingToBeginning(3)") != null;
		if (fling==true) {
			System.out.println("scroll to top");
		}
		else
		{
			System.out.println("not able to reach top");
		}
    	return true;
    }
    
    //Get the Size of element
    public int getSize(String identifier,String locator) {
    	
    	List<AndroidElement> e=AndroidElementIds(identifier, locator);
    	return e.size();
    }
    
    //Reset the App
    public void reset() {
    	
    	driver.resetApp();
    }
    
    //Value SubString
    public String subString(String value, int begin, int end) {
    	
    	String result=value.substring(begin, end);
    	return result;
    }
    
    // Check box selected
    public boolean isButtonSelected(String identifier,String locator) {
    	
    	AndroidElement e=AndroidElementId(identifier, locator);
    	if (e.isSelected()) {
    		return true;
    	}
    	else {
    		return false;
    	}
    }
        
    //Get the char count of text box
    public int getCharCount(String identifier,String locator) {
    	
    	AndroidElement e=AndroidElementId(identifier, locator);
    	int count=e.getText().length();
    	return count;
    }
    
    //Change First letter to Capital
    public String titleCase(String value) {
    	
    	String result=WordUtils.capitalizeFully(value);
    	return result;
    }
}
