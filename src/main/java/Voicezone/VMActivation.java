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

public class VMActivation extends CommonFunctions {
                
                String table, tns[];
    String tlimit,username,pwd;
    int tncount;                                                    
                                
   
                int rank[]= new int[50];
                

                int c_sequence;
                String name_for_rank[]= new String[40];
                String price, rank_channels;

                public VMActivation(String path) {
                                this.path = path;
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
			            wb.close();
        
            driver.manage().timeouts().implicitlyWait(tlim,TimeUnit.SECONDS);
            logger.info("qtest1");
            try {
                    if(first==0)
                {
                    login(driver,username,pwd);
                }
                logger.info("a");
    	                    	
        		focusClick(driver, driver.findElement(By.linkText("Settings")),br);
        		int VM=driver.findElements(By.xpath("//*[@id='ContentRefresh']/div/div[11]/div[2]/img")).size();
        		statusTracker(br,"","Verify Whether Voicemail Activation option is displayed or not","","");
        		
        		if(VM!=0)
        		  {
        			focusClick(driver, driver.findElement(By.xpath("//button[@type='button']")),br);
        	 
        	    
        	    statusTracker(br,"Pass","Verify on clicking Activate button is navigated to Voicemail page","User is navigated to Voicemail page","User should be navigated to Voicemail page");

        	    String Check1=driver.findElement(By.xpath("//button[@type='button']")).getText();
        	    int Check2=driver.findElements(By.cssSelector("div.modal-body")).size();
        	    String Status=driver.findElement(By.cssSelector("div.modal-body")).getText();
        	    
        	    
        	    statusTracker(br,"","Verify Whether Voicemail Activation is complete or not","","");
        	    if(Check1.equals("Activate Voicemail Now"))
        	    {
        	    	focusClick(driver, driver.findElement(By.xpath("//button[@type='button']")),br);
        	  String schk=orderprocess(driver,br);;
        	  Thread.sleep(3000);
        	    if(Check2!=0)
        	    {
        	    	 statusTracker(br,"Pass","Voicemail Activation","User is able to Activate the voicemail feature"+Status,"User should be able to activate the voicemail");
        	    	 driver.findElement(By.id("close")).click();
        	    	 }
        	    else
        	    		 
        	    	  statusTracker(br,"Fail","Voicemail Activation","User is able to see error message" +Status ,"User should be able to activate the voicemail");
        	         
        		  
        	    }
        		  
        		  }
        		else
        	    { 
        		statusTracker(br,"Fail","Verify on VM Activate button is Present in summary Setting page","Voicemail is already activated","User is displayed  VM Activate button in summary settings page");
        	    }
        	    
        		focusClick(driver,driver.findElement(By.id("mainCancelButton")),br);   	    
        	    
first=1;      
      }  
                  
                                
      
      catch (Exception e)
      {
    	  exceptionHandler(br,e,driver);
      }
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
				
}


