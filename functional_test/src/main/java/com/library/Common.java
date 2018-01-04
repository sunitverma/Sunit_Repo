package com.library;

import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Properties;

public class Common {

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
    		}catch (IOException io) {
    		io.printStackTrace();
    		}
    }
        
    //WebElement 
    public WebElement webElementId(String identifier,String locator)
    {
        WebElement e=null;
        switch (identifier)
        {
        case "id" : 
            e=driver.findElement(By.id(locator));
                        break;
        case "className" : 
            e=driver.findElement(By.className(locator));
                        break;
        case "tagName" : 
            e=driver.findElement(By.tagName(locator));
                        break;
        case "name" : 
            e=driver.findElement(By.name(locator));
                        break;       
        case "linkText" : 
            e=driver.findElement(By.linkText(locator));
                        break;       
        case "partialLinkText" : 
            e=driver.findElement(By.partialLinkText(locator));
                        break;          
        case "cssSelector" : 
            e=driver.findElement(By.cssSelector(locator));
                        break;
        case "xpath" :
            e=driver.findElement(By.xpath(locator));
                        break;
        default : 
            System.out.println("Locator not found");
                    e=null;
        }
        return e;
    }
    
    public List<AndroidElement> webElementIds(String identifier,String locator)
    {
       List<AndroidElement> e=null;
        switch (identifier)
        {
        case "id" :
            e=driver.findElements(By.id(locator));
                        break;
        case "className" :
            e=driver.findElements(By.className(locator));
                        break;
        case "tagName" :
            e=driver.findElements(By.tagName(locator));
                        break;
        case "name" :
            e=driver.findElements(By.name(locator));
                        break;      
        case "linkText" :
            e=driver.findElements(By.linkText(locator));
                        break;      
        case "partialLinkText" :
            e=driver.findElements(By.partialLinkText(locator));
                        break;          
       case "cssSelector" :
            e=driver.findElements(By.cssSelector(locator));
                        break;
        case "xpath" :
            e=driver.findElements(By.xpath(locator));
                        break;
        default :
            System.out.println("Locator not found");
                    e=null;
        }
        return e;
    }
    
    //Send key method
    public void sendKeys(String identifier,String locator,String content)
    {
        
        WebElement e=webElementId(identifier, locator);
        e.sendKeys(content);        
    }
    
    //Clear text  field method
    public void clearTextField(String identifier,String locator)
    {
        WebElement e=webElementId(identifier, locator);
        e.clear();      
    }
    
    //click general method
    public void click(String identifier,String locator)
    {
        WebElement e=webElementId(identifier, locator); 
        e.click();
    }
        
    //Wait until the Element is present
    public void waitUntilElementPresent(String elementpath)
    {
        WebElement elementpresent=(new WebDriverWait(driver,60)).
        until(ExpectedConditions.presenceOfElementLocated
                (By.xpath(elementpath)));
    }
    
    //Verify Text // sunit change on 20/11/2017
    public void verifyText(String identifier,String locator,String text)
    {
        WebElement e=webElementId(identifier, locator);
        if (e.getText().equals(text))
        {
            System.out.println(text+" text displayed");
        }
        else
        {
            System.out.println(text+" text did not displayed");
        }
    }
    
    //verify element present
    public boolean verifyElementPresent(String identifier,String locator)
    {
        WebElement e=webElementId(identifier, locator);
        if (e.isDisplayed())
        {
            return true;
        	//System.out.println("Element or Link present");
        }
        else
        {
        	return false;
            //System.out.println("Element or Link is not present");
        }
    }
    
    //Thread sleep
    public void sleepThread(long sleeptime) 
    {
        try
        {
          Thread.sleep(sleeptime);
        } catch (Exception e) 
        {
            System.out.println(e);
        }
    }
           
    //capture screenshot
    public void screenShot(){
    // Take screenshot and store as a file format
    File src= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    try {
    
    // now copy the  screenshot under target folder
    FileUtils.copyFile(src, new File("target/surefire-reports/screenshots/"+System.currentTimeMillis()+".png"));
        } catch (IOException e)
           {
           System.out.println(e.getMessage());
           }
    }
  
    // M - Button Enabled
    public boolean isButtonEnabled(String identifier,String locator)
    {
        WebElement e=webElementId(identifier, locator);
        if (e.isEnabled())
        {
            return true;
        	//System.out.println("Button is enabled");
        }
        else
        {
        	return false;
            //System.out.println("Button is disabled");
        }
    }
    
    //Get the text of element
    public String getText(String identifier,String locator)
    {
        WebElement e=webElementId(identifier, locator);
        String msg=e.getText();
        return msg;
    }
    
    //send the keyboard key
    public void keyboardKeys(int key)
    {
    	driver.pressKeyCode(key);
    }
    
    //reset the count for a user, use this when want to unlock the password
    public void resetCount(String username) throws Exception
    {
        Process p = Runtime.getRuntime().exec(new String[] {"explorer", "http://hillevi:7070/passwordreset/api/user/reset/"+username});
        sleepThread(1000);
        p.destroy();
    }
    
    //verify element present
    public boolean compareTexts(String actual,String expected)
    {
    	assertTrue(actual.equals(expected));
    	return false;
     }
    
    //Close App and quit
    public void quitObject()
    {
        driver.closeApp();
        driver.quit();
    }
    
    //Get the count of element
    public int getCount(String identifier,String locator)
    {
        List<AndroidElement> e=webElementIds(identifier, locator);
        int count=e.size();
        return count;
    }
    
    //Scroll down the page
    public void scrollDown(String Direction,int Start,int End) throws InterruptedException
    {
        char a = '\"';
        driver.executeScript("client:client.swipe("+ a + Direction + a + "," + Start + ","+ End +")");
    }
    
    //Get the Size of element
    public int getSize(String identifier,String locator)
    {
        List<AndroidElement> e=webElementIds(identifier, locator);
        return e.size();
    }
    
    //Reset the App
    public void reset()
    {
        driver.resetApp();
    }
}