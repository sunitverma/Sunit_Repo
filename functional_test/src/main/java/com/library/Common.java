package com.library;

import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.WordUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Properties;
import static org.testng.Assert.assertTrue;

public class Common {

    ExtentReports extent;
    ExtentTest test;
  
    public static WebDriverWait wait = null;
    private String reportDirectory = "reports";
    private String reportFormat = "xml";
    private String testName = "Untitled";
    protected AndroidDriver<AndroidElement> driver = null;
     
    DesiredCapabilities dc = new DesiredCapabilities();
              
    public void setUp() {
      
      Properties Des = new Properties();
      InputStream input = null;

      try {
        input = new FileInputStream("./src/main/java/com/capabilities/DesiredCapabilities.properties");
        
        // load a properties file
        Des.load(input);      
         
        dc.setCapability("reportDirectory", reportDirectory);
        dc.setCapability("reportFormat", reportFormat);
        dc.setCapability("testName", testName);
               
        //update the UDID_number in DesiredCapabilites properties file as per your device
        dc.setCapability(MobileCapabilityType.UDID, Des.getProperty("UDID_number"));
        dc.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.jncb.mobile");
        dc.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, ".MainActivity");
        dc.setCapability(AndroidMobileCapabilityType.UNICODE_KEYBOARD,"True");
        driver = new AndroidDriver<AndroidElement>(new URL("http://localhost:4723/wd/hub"), dc);
      } catch (IOException io) {
        io.printStackTrace();
      }
    }

    //AndroidElement 
    public AndroidElement androidElementId(String identifier,String locator) {
      
      AndroidElement e = null;
      switch (identifier) {
        case "id":
          e=driver.findElementById(locator);
          break;
        case "className":
          e=driver.findElementByClassName(locator);
          break;
        case "tagName":
          e=driver.findElementByTagName(locator);
          break;
        case "name":
          e=driver.findElementByName(locator);
          break;       
        case "linkText":
          e=driver.findElementByLinkText(locator);
          break;       
        case "partialLinkText":
          e=driver.findElementByPartialLinkText(locator);
          break;          
        case "cssSelector":
          e=driver.findElementByCssSelector(locator);
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
    public List<AndroidElement> androidElementIds(String identifier,String locator) {
      
      List<AndroidElement> e=null;
      switch (identifier) {
        case "id":
          e=driver.findElementsById(locator);
          break;
        case "className":
          e=driver.findElementsByClassName(locator);
          break;
        case "tagName":
          e=driver.findElementsByTagName(locator);
          break;
        case "name":
          e=driver.findElementsByName(locator);
          break;      
        case "linkText":
          e=driver.findElementsByLinkText(locator);
          break;      
        case "partialLinkText":
          e=driver.findElementsByPartialLinkText(locator);
          break;          
        case "cssSelector":
          e=driver.findElementsByCssSelector(locator);
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
      
      AndroidElement e=androidElementId(identifier, locator);
      e.sendKeys(content);  
    }
        
    //Clear text  field method
    public void clearTextField(String identifier,String locator) {
      
      AndroidElement e=androidElementId(identifier, locator);
      e.clear(); 
    }
    
    //click general method
    public void click(String identifier,String locator) {
      
      AndroidElement e=androidElementId(identifier, locator); 
      e.click();
    }
        
    //Wait until the Element is present
    public void waitUntilElementPresent(String elementpath) {
      
      WebElement elementpresent=(new WebDriverWait(driver,60)).
          until(ExpectedConditions.presenceOfElementLocated
              (By.xpath(elementpath)));
    }
        
    //Verify Text // sunit change on 20/11/2017
    public boolean verifyText(String identifier,String locator,String text) {
      
      AndroidElement e=androidElementId(identifier, locator);
      if (e.getText().equals(text)) {
        System.out.println(text+" text displayed");
        
        return true;
      }
      else  {
        System.out.println(text+" text did not displayed");
        return false;
      }
    }
        
    //verify element present
    public boolean verifyElementPresent(String identifier,String locator) {
      
      AndroidElement e=androidElementId(identifier, locator);
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
      
      AndroidElement e=androidElementId(identifier, locator);
      if (e.isEnabled()) {
        return true;
      }
      else {
        return false;
      }
    }
       
    //Get the text of element
    public String getText(String identifier,String locator) {
      
      AndroidElement e=androidElementId(identifier, locator);
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
      
      assertTrue(actual.equals(expected));
      return false;
    }
    
    //Close App and quit
    public void quitObject() {
      
      driver.closeApp();
      driver.quit();
    }
   
    //Get the count of element
    public int getCount(String identifier,String locator) {
      
      List<AndroidElement> e=androidElementIds(identifier, locator);
      int count=e.size();
      return count;
    }
    
    //Scroll up or down the page
    public void scroll(String Direction,int Start,int End) {
      
      char a = '\"';
      driver.executeScript("client:client.swipe("+ a + Direction + a + "," + Start + ","+ End +")");
    }
    
    //Get the Size of element
    public int getSize(String identifier,String locator) {
      
      List<AndroidElement> e=androidElementIds(identifier, locator);
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
      
      AndroidElement e=androidElementId(identifier, locator);
      if (e.isSelected()) {
        return true;
      }
      else {
        return false;
      }
    }
        
    //Get the char count of text box
    public int getCharCount(String identifier,String locator) {
      
      AndroidElement e=androidElementId(identifier, locator);
      int count=e.getText().length();
      return count;
    }
    
    //Change First letter to Capital
    public String titleCase(String identifier,String locator) {
      
      AndroidElement e=androidElementId(identifier, locator);
      String capText=e.getText();
      String result=WordUtils.capitalizeFully(capText);
      return result;
    }
}