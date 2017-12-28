package Voicezone;

import java.io.File;
import java.io.PrintStream;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;


import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class CallWaiting extends CommonFunctions 
{
                String table;
                String tlimit,username,pwd;
                int tncount,tlim;
               
                
                private boolean isElementPresent(WebDriver driver, By by)
                {
              	  try{
              		  driver.findElement(by);
              		  return true;
              	  }
              	  catch(NoSuchElementException e){
              		  return false;
              	  }
                }
                
                public CallWaiting(String s)
                {
                                this.path = s;
                }
                
                public String flowrun( String br,WebDriver driver,String xpath) throws Exception
                {
                	boolean statusdisabled = driver.findElement(By.id("cwdisabled")).isSelected();
        			System.out.println("val" +statusdisabled);
        			boolean statuscw = driver.findElement(By.id("cw")).isSelected();
        			System.out.println(statuscw);
        			boolean statuscwcid = driver.findElement(By.id("cwcid")).isSelected();
        			System.out.println(statuscwcid);
        			
              		String from,to;
              		if(statusdisabled)
              			from = "Disabled";
              		else if (statuscw)
              			from = "Call Waiting";
              		else
              			from = "Call Waiting Caller ID";
              			 
              		System.out.println("after selection");
              		to=xpath;
              		if(to.contains("1"))
              			to="Disabled";
              		
              		else if(to.contains("2"))
              			to="Call Waiting";
              		else
              			to="Call Waiting Caller ID";
              		System.out.println("xpath assignment");
              		
              		driver.findElement(By.id(xpath)).click();
              		 focusClick(driver,driver.findElement(By.id("mainSubmitButton")),br);
              	  	String schk=orderprocess(driver,br);;
              	  	System.out.println("test");
              	    Thread.sleep(8000);
              	 
              	  	if(schk.equals("Fail"))
              		{
              			statusTracker(br,"Fail","Verify order processing when switching from "+from+" to "+to,"Order processing has failed","Order should be processed successfully");
              			if((assertTrue(isElementPresent(driver.findElement(By.id("modalContinueButton"))))))
              			{
              				focusClick(driver,driver.findElement(By.id("modalContinueButton")),br);
              			}
              		}
              		else
              		{
              			System.out.println("Mpass");
              			statusTracker(br,"Pass","Verify order processing when switching from "+from+" to "+to,"Order processing has completed successfully","Order should be processed successfully");
              		}
              	  	return schk;
                }
                
                
public void execute(String br, WebDriver driver, String url, int loc, String name1) throws Exception {
          	  	  
          	  File data = new File(this.path);
          	        WorkbookSettings ws = new WorkbookSettings();
          	        ws.setLocale(new Locale("er", "ER"));
          	        Workbook wb = Workbook.getWorkbook(data, ws);
          	        Sheet sheet2 = wb.getSheet(0);
          	        tlimit = sheet2.getCell(5, loc).getContents();
          	        username = sheet2.getCell(6, loc).getContents();
          	        pwd = sheet2.getCell(7, loc).getContents();
          	     
          	        tlim = Integer.parseInt(tlimit);
          	        wb.close();

          	     driver.manage().timeouts().implicitlyWait(tlim,TimeUnit.SECONDS); 
          	                            //TimeUnit.SECONDS);
          	     System.out.println("qtest1");
          	        //setUp();
          	        try {
          	    
          	      	  if(first==0)
          	            {
          	      		  login(driver,username,pwd);
          	            }
          	      	 int chk=0;
       		      do{
       		           Thread.sleep(1000);       
       		          chk++;
       		          System.out.println(chk);
       		                }
       		      while(driver.findElement(By.xpath("//*[@id='progress']")).isDisplayed());
          	      Thread.sleep(5000);

    
      
      focusClick(driver, driver.findElement(By.xpath("(//a[contains(text(),'(edit)')])[5]")),br);
      Thread.sleep(5000);
			boolean statusdisabled = driver.findElement(By.id("cwdisabled")).isSelected();
			System.out.println(statusdisabled);
//			boolean statuscw = driver.findElement(By.xpath("(//input[@id='SelectedOption'])[2]")).isSelected();
			boolean statuscw = driver.findElement(By.id("cw")).isSelected();
			System.out.println(statuscw);
//			boolean statuscwcid = driver.findElement(By.xpath("(//input[@id='SelectedOption'])[3]")).isSelected();
			boolean statuscwcid = driver.findElement(By.id("cwcid")).isSelected();
			System.out.println(statuscwcid);
			
			String schk="Pass";
			System.out.println("fgjjfghgdjgldglaadgj");
			if(statusdisabled)
			{
				driver.findElement(By.id("cwdisabled")).click();
				 focusClick(driver,driver.findElement(By.id("mainSubmitButton")),br);
				System.out.println("ffghfh");
				schk=orderprocess(driver,br);;
				System.out.println("fgjjfghgdjgldglaadgj1");
				if(schk.equals("Fail"))
				{
					statusTracker(br,"Fail","Verify order processing to disable Call Waiting","Order processing has Failed","Order should be processed successfully");
					if(assertTrue(isElementPresent(driver.findElement(By.id("modalContinueButton")))))
					{
						 focusClick(driver,driver.findElement(By.id("modalContinueButton")),br);
						
					}
				}
			}
			System.out.println("run");
			if(schk.equals("Pass"))
				schk =flowrun(br,driver, "cw");
			if(schk.equals("Pass"))
				schk =flowrun(br,driver, "cwdisabled");
			if(schk.equals("Pass"))
				schk =flowrun(br,driver,"cwcid");
			if(schk.equals("Pass"))
				schk =flowrun(br,driver,"cwdisabled");
			if(schk.equals("Pass"))
				schk =flowrun(br,driver,"cw");
			if(schk.equals("Pass"))
				schk =flowrun(br,driver,"cwcid");
			if(schk.equals("Pass"))
				schk =flowrun(br,driver,"cwdisabled");
			if(schk.equals("Pass"))
				schk =flowrun(br,driver,"cwcid");
			if(schk.equals("Pass"))
				schk =flowrun(br,driver,"cw");
			if(schk.equals("Pass"))
				schk =flowrun(br,driver,"cwdisabled");
			 focusClick(driver,driver.findElement(By.id("mainCancelButton")),br);
	              
			first=1;       
		    
      }
                  
                                            
      
      catch (Exception e)
      {
                  exceptionHandler(br,e,driver);
      }
    
    finally {
                //statusTracker("end","","");
      wb.close();
     
    }
  }




private Object isElementPresent(WebElement findElement) {
	// TODO Auto-generated method stub
	return null;
}
}
