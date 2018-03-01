package com.utils.ExtentReports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ExtentManager {
  
  private static ExtentReports extent;
  private static ExtentTest test;
  private static ExtentHtmlReporter htmlReporter;
  private static String filePath = "./extentreport.html";
  
  
  public static ExtentReports GetExtent(){
      if (extent != null)
                  return extent; //avoid creating new instance of html file
              extent = new ExtentReports();       
      extent.attachReporter(getHtmlReporter());
      return extent;
  }

  private static ExtentHtmlReporter getHtmlReporter() {
  
      htmlReporter = new ExtentHtmlReporter(filePath);
      
      // make the charts visible on report open
      htmlReporter.config().setChartVisibilityOnOpen(true);
      
      htmlReporter.config().setDocumentTitle("Kratos Automation Report");
      htmlReporter.config().setReportName("Regression cycle");
      return htmlReporter;
  }
  
  public static ExtentTest createTest(String name, String description){
      test = extent.createTest(name, description);
      return test;
  }
}

/*import com.aventstack.extentreports.ExtentReports;

public class ExtentManager {

  private static ExtentReports extent;
  
  public synchronized static ExtentReports getReporter(){
      if(extent == null){
          //Set HTML reporting file location
          String workingDir = System.getProperty("user.dir");
          extent = new ExtentReports ();//new ExtentReports(workingDir+"ExtentReports\\ExtentReportResults.html", true);
      }
      
      return extent;
  }
}
*/