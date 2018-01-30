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

public class ThreeWayCalling extends CommonFunctions {
                
                String table, tns[];
    String tlimit,username,pwd;
    int tncount;                                                    
       boolean status,status1;                         
    String phoneline,phoneline_ac,Acccode;
                int rank[]= new int[50];
                

                int c_sequence;
                String name_for_rank[]= new String[40];
                String price, rank_channels;

                public ThreeWayCalling(String path) {
                                this.path = path;
                }
   
                public void execute(String br, WebDriver driver, String url, int loc, String name1) throws Exception {
                    
                   
                    int tlim=3;
                   
                    String state = "Fail";
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
           // wb.close();
        
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
//                focusClick(driver, driver.findElement(By.linkText("Settings")),br);
                focusClick(driver, driver.findElement(By.xpath("(//a[contains(text(),'(edit)')])[7]")),br);
          // driver.findElement(By.id("Activated")).click();
         
                Thread.sleep(5000);
                status1=false;
                status = driver.findElement(By.id("threewayActivated")).isSelected();
               System.out.println("Initial state: "+status);
             
               
           if (status==(false))
           {
              
                String schk="Fail";
                Thread.sleep(5000);
                focusClick(driver,driver.findElement(By.id("threewayActivated")),br);
                Thread.sleep(5000);
                focusClick(driver,driver.findElement(By.id("mainSubmitButton")),br);            
                       schk=orderprocess(driver,br);
                       status1=true;
                
                       if(schk.equals("Pass"))
                       {
                             statusTracker(br,"Pass","Verify if enabling 3 way calling is processed successfully","Order is successfully processed from "+ status +" to "+status1,"Order should be successfully processed");
                       }
                       else
                       {
                             statusTracker(br,"Fail","Verify if enabling 3 way calling is processed successfully","Order is not successfully processed","Order should be successfully processed");
                       }
           
                     status = driver.findElement(By.id("threewayActivated")).isSelected();
                     Thread.sleep(5000);
                       focusClick(driver,driver.findElement(By.id("threewayActivated")),br);
                       Thread.sleep(5000);
                       focusClick(driver,driver.findElement(By.id("mainSubmitButton")),br);     
                       System.out.println(status);
                       schk=orderprocess(driver,br);
                       status1=false;

                       if(schk.equals("Pass"))
                       {
                             statusTracker(br,"Pass","Verify if disabling 3 way calling is processed successfully","Order is successfully processed from "+ status +" to "+status1,"Order should be successfully processed");
                       }
                       else
                       {
                             statusTracker(br,"Fail","Verify if disabling 3 way calling is processed successfully","Order is not successfully processed","Order should be successfully processed");
                       }
          }
                     
           
           else
                       {
                             String schk="Fail";
                             status = driver.findElement(By.id("threewayActivated")).isSelected();
                             System.out.println(status);
                             focusClick(driver,driver.findElement(By.id("threewayActivated")),br);
                             Thread.sleep(5000);
                       focusClick(driver,driver.findElement(By.id("mainSubmitButton")),br);     
                       schk=orderprocess(driver,br);
                       status1=false;

                             if(schk.equals("Pass"))
                             {
                                    statusTracker(br,"Pass","Verify if disabling 3 way calling is processed successfully","Order is successfully processed from "+ status +" to "+status1,"Order should be successfully processed");
                             }
                             else
                             {
                                    statusTracker(br,"Fail","Verify if disabling 3 way calling is processed successfully","Order is not successfully processed","Order should be successfully processed");
                             }
                             status = driver.findElement(By.id("threewayActivated")).isSelected();
                             focusClick(driver,driver.findElement(By.id("threewayActivated")),br);
                             Thread.sleep(5000);
                             focusClick(driver,driver.findElement(By.id("mainSubmitButton")),br);                     
                             schk=orderprocess(driver,br);
                             status1=true;
                            System.out.println(status);
                             if(schk.equals("Pass"))
                             {
                                    statusTracker(br,"Pass","Verify if enabling 3 way calling is processed successfully","Order is successfully processed from "+ status +" to "+status1,"Order should be successfully processed");
                             }
                             else
                             {
                                    statusTracker(br,"Fail","Verify if enabling 3 way calling is processed successfully","Order is not successfully processed","Order should be successfully processed");
                             }
                       }      
              
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
            //statusTracker("end","","");
  wb.close();

}
}



                }
