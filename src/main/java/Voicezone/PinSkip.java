package Voicezone;

import java.io.File;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;

public class PinSkip extends CommonFunctions {
                
                String table, tns[];
    String tlimit,username,pwd;
    int tncount;                                                    
                                
    String phoneline,phoneline_ac,Acccode;
                int rank[]= new int[50];
                boolean status;

                int c_sequence;
                String name_for_rank[]= new String[40];
                String price, rank_channels;
                String schk;
                public PinSkip(String path) {
                                this.path = path;
                }

  public String flowrun(WebDriver driver,String br) throws Exception
  {
	  status = driver.findElement(By.id("PinSkipStatus")).isSelected();
	  System.out.println("Initial state: "+status);
	  Boolean status1=false;
	
	  if(status==(true))
	  {
		 
		  driver.findElement(By.id("PinSkipStatus")).click();
		  driver.findElement(By.id("mainSubmitButton")).click();
		  schk=orderprocess(driver,br);
		  status1=false;
		  if(schk.equals("Pass"))
		  {
			  statusTracker(br,"Pass","Verify if disabling pin skip order is processed successfully","Order is successfully processed from "+ status +" to "+status1,"Order should be successfully processed");
		  }
		  else
		  {
			  statusTracker(br,"Fail","Verify if disabling pin skip order is processed successfully","Order is not successfully processed","Order should be successfully processed");
		  }
		  status = driver.findElement(By.id("PinSkipStatus")).isSelected();
		  driver.findElement(By.id("PinSkipStatus")).click();
		  int chk=0;
		      do{
		           Thread.sleep(1000);       
		          chk++;
		          System.out.println(chk);
		                }
		      while(driver.findElement(By.cssSelector("#dialog > div.modal-footer > #ok_PinSkip")).isDisplayed());
		  driver.findElement(By.cssSelector("#dialog > div.modal-footer > #ok_PinSkip")).click();
		  Thread.sleep(2000);  
		  driver.findElement(By.id("mainSubmitButton")).click();
		  schk=orderprocess(driver,br);
		  status1=true;
		  if(schk.equals("Pass"))
		  {
			  statusTracker(br,"Pass","Verify if enabling pin skip order is processed successfully","Order is successfully processed from "+ status +" to "+status1,"Order should be successfully processed");
		  }
		  else
		  {
			  statusTracker(br,"Fail","Verify if enabling pin skip order is processed successfully","Order is not successfully processed","Order should be successfully processed");
		  }
	  }  
		  else
	  
	  { 
		  
			  status = driver.findElement(By.id("PinSkipStatus")).isSelected();
			  driver.findElement(By.id("PinSkipStatus")).click();
			  int chk=0;
		      do{
		           Thread.sleep(1000);       
		          chk++;
		          System.out.println(chk);
		                }
		      while(!(driver.findElement(By.cssSelector("#dialog > div.modal-footer > #ok_PinSkip")).isDisplayed()));
		    driver.findElement(By.cssSelector("#dialog > div.modal-footer > #ok_PinSkip")).click();
		    Thread.sleep(2000);  
			  driver.findElement(By.id("mainSubmitButton")).click();
			  schk=orderprocess(driver,br);
			  status1=true;
			  if(schk.equals("Pass"))
			  {
				  statusTracker(br,"Pass","Verify if enabling pin skip order is processed successfully","Order is successfully processed from "+ status +" to "+status1,"Order should be successfully processed");
			  }
			  else
			  {
				  statusTracker(br,"Fail","Verify if enabling pin skip order is processed successfully","Order is not successfully processed","Order should be successfully processed");
			  }
			  status = driver.findElement(By.id("PinSkipStatus")).isSelected();
			  driver.findElement(By.id("PinSkipStatus")).click();
			  driver.findElement(By.id("mainSubmitButton")).click();
			  schk=orderprocess(driver,br);
			  status1=false;
			  if(schk.equals("Pass"))
			  {
				  statusTracker(br,"Pass","Verify if disabling pin skip order is processed successfully","Order is successfully processed from "+ status +" to "+status1,"Order should be successfully processed");
			  }
			  else
			  {
				  statusTracker(br,"Fail","Verify if disabling pin skip order is processed successfully","Order is not successfully processed","Order should be successfully processed");
			  }
	  } 
	  return schk;
  }

  public void execute(String br, WebDriver driver, String url, int loc, String name1) throws Exception {
      
 
      int tlim=3;
      String status1="",state = "Fail";
      File data = new File(this.path);
      WorkbookSettings ws = new WorkbookSettings();
      ws.setLocale(new Locale("er", "ER"));
      Workbook wb = Workbook.getWorkbook(data, ws);
      try
      {
                      Sheet sheet2 = wb.getSheet(0);
tlimit = sheet2.getCell(5, loc).getContents();
username = sheet2.getCell(6, loc).getContents();
pwd = sheet2.getCell(7, loc).getContents();
tlim = Integer.parseInt(tlimit);
//wb.close();

driver.manage().timeouts().implicitlyWait(tlim,TimeUnit.SECONDS);
logger.info("qtest1");
try {
      if(first==0)
  {
    	  login(driver,username,pwd);
  }
      else
      {
    	  focusClick(driver,driver.findElement(By.id("settings-summary")),br); 
      }
  logger.info("a");
		    Thread.sleep(10000);
			int chk=0;
		      do{
		           Thread.sleep(1000);       
		          chk++;
		          System.out.println(chk);
		                }
		      while(driver.findElement(By.xpath("//*[@id='progress']")).isDisplayed());
			Thread.sleep(5000);
			driver.findElement(By.xpath("(//a[contains(text(),'(edit)')])[2]")).click();
		
				Thread.sleep(5000);
				do{
				}while(assertTrue(isElementPresent("//body/div[11]")));
				Thread.sleep(2000);
				
				
			//String status = selenium.getValue("id=Activated");
				
	  		driver.findElement(By.linkText("Voicemail PIN Settings")).click();
	  		String schk = "Pass";
			System.out.println("run");
			if(schk.equals("Pass"))
				  schk=flowrun(driver,br);
			    
		    focusClick(driver,driver.findElement(By.id("mainCancelButton")),br); 
			first=1;      
      }
      catch (Exception e)
      {
    	  exceptionHandler(br,e, driver);
      }
    }
    catch (Exception e)
    {
    	exceptionHandler(br,e, driver);
      
    }
    finally {
    	wb.close();
    	 System.out.println("Complete");

    }
  }



private Object isElementPresent(String string) {
	// TODO Auto-generated method stub
	return null;
}


}

