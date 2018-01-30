package Voicezone;
import java.io.File;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;



//import java.lang.Number;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;

public class CallForwardingUnconditional extends CommonFunctions
{
                String table, tns[];
                String tlimit,username,password;
                String ac1,midtn1,lastfour1;
                String phoneline,phoneline_ac,phoneline_midtn,phoneline_lastfour;
                int tncount,tlim;
public CallForwardingUnconditional(String s)
  {
	this.path = s;
  } 
 private boolean isElementPresent(WebDriver driver, By by)
{
                  try{
                                  driver.findElement(by);
                                  return true;
                  }
                  catch(NoSuchElementException e)
                  {
                                  return false;
                  }
}

 
public String flowrun1( String br,String status, String status1,WebDriver driver) throws Exception
  {
                                  String schk="Fail";
          String from="",to="";
          if(status.equals("on"))
          {
                 from = "Enabled";
                 to="Disabled";
                 focusClick(driver,driver.findElement(By.id("CFUActivatedOff")),br);	
                 focusClick(driver,driver.findElement(By.id("mainSubmitButton")),br);	
             	
               
                 schk=orderprocess(driver,br);;
            //OMG
                 //schk="Pass";
                 if(schk.equals("Fail"))
                 {
                        statusTracker(br,"Fail","Verify order processing when switching from "+from+" to "+to,"Order processing has failed","Order should be processed successfully");
                        
/*
                        if((assertTrue(isElementPresent(By.id("modalContinueButton")))))
                             focusClick(driver,driver.findElement(By.id("mainSubmitButton")),br);
                        {
                        	  focusClick(driver,driver.findElement(By.id("modalContinueButton")),br);   
                        	
                        }*/
                 }
                 else
                 {
                        statusTracker(br,"Pass","Verify order processing when switching from "+from+" to "+to,"Order processing has completed successfully","Order should be processed successfully");
                        schk="Pass";
                 }
                 
          }
          else if (status.equals("off"))
          {
                 from = "Disabled";
                 to="Enabled";
             //  String schk=EditTN();    
                 focusClick(driver, driver.findElement(By.id("CFUActivatedOn")),br);
            
                 schk=EditTN(br,driver);
                 if(schk.equals("Pass"))
                 { 
                                 // statusTracker("Pass","Verify order processing when switching from "+from+" to "+to,"Order processing has completed successfully","Order should be processed successfully");
                                 schk=Drpdwn(br,driver);
                     if(schk.equals("Fail"))
                     {
                            statusTracker(br,"Fail","Verify order processing using a TN from drop down","Order processing has failed","Order should be processed successfully");
                           
                           /* if((assertTrue(isElementPresent(By.id("modalContinueButton")))))
                            {
                               focusClick(driver,driver.findElement(By.id("modalContinueButton")),br);                                 
                            }*/
                     }
                     else
                     {
                            statusTracker(br,"Pass","Verify order processing using a TN from drop down","Order processing has completed successfully","Order should be processed successfully");
                            schk="Pass";
                     }
                 }
                 else
                                 statusTracker(br,"Fail","Verify order processing when switching from "+from+" to "+to,"Order processing has not completed successfully","Order should be processed successfully");
          }
          return schk;
  
} 
 private boolean assertTrue(WebElement findElement) {
                // TODO Auto-generated method stub
                return false;
}



public String EditTN( String br,WebDriver driver) throws Exception
  {
                    String from = "Disabled";
        String to="Enabled";
                    //String schk="pass";
        
        Thread.sleep(2000);
        driver.findElement(By.id("txtAreaCode0")).clear();
        driver.findElement(By.id("txtAreaCode0")).sendKeys(phoneline_ac);
        driver.findElement(By.id("txtExchange0")).clear();
        driver.findElement(By.id("txtExchange0")).sendKeys(phoneline_midtn);
        driver.findElement(By.id("txtTelNum0")).clear();
        //driver.findElement(By.id("txtTelNum0")).sendKeys(phoneline_lastfour);
        driver.findElement(By.id("txtTelNum0")).sendKeys((randomNO(9000,1000)));
     focusClick(driver,driver.findElement(By.id("mainSubmitButton")),br);
                                System.out.println("b4loop");                   
                                String schk=orderprocess(driver,br);;
                                System.out.println("afrloop");
                                if(schk.equals("Fail"))
                    {
                           statusTracker(br,"Fail","Verify order processing when switching from "+from+" to "+to +" using Alternate TN","Order processing has failed","Order should be processed successfully");
                           /*if((assertTrue(isElementPresent(By.id("modalContinueButton")))))
                           {
                                  focusClick(driver,driver.findElement(By.id("modalContinueButton")),br);  
                           }*/
                    }
                    else
                    {
                                statusTracker(br,"Pass","Verify order processing when switching from "+from+" to "+to +" using Alternate TN","Order processing has completed successfully","Order should be processed successfully");
                           schk="Pass";
                    }
                                return schk;
  }
  
 public String Drpdwn( String br,WebDriver driver) throws Exception
  {
                

     String schk="Fail";
     driver.findElement(By.id("CFUStatusdrop")).click();//Dropdown
     Thread.sleep(5000);

     int listcount=driver.findElements(By.xpath("//html/body/div[3]/form/div/div[3]/div[2]/div/div/div/div/div[4]/div/div/div[7]/div/div/ul/li")).size(); 
                                                  
     System.out.println("listcount"+listcount);
     String text[]=new String[listcount+1];

     for(int i=1;i<=listcount;i++)
     {
                 WebElement labelNode = driver.findElement(By.xpath("//html/body/div[3]/form/div/div[3]/div[2]/div/div/div/div/div[4]/div/div/div[7]/div/div/ul/li["+i+"]//a")); 
         String labelNodeText = (String) ((JavascriptExecutor)driver).executeScript("return arguments[0].innerHTML",labelNode);
         labelNodeText=labelNodeText.substring(21,labelNodeText.length()-16);
         labelNodeText=labelNodeText.replaceAll("\\n","");
         text[i]=labelNodeText;
         System.out.println("String: "+labelNodeText);   
         System.out.println("TN "+text[i]);
  }

     String j=randomNO(9,1);
     int k=Integer.parseInt(j);
     driver.findElement(By.id("ddlCFUnum")).click();
     Thread.sleep(2000);
     driver.findElement(By.linkText(text[k])).click();
     Thread.sleep(2000);
  focusClick(driver,driver.findElement(By.id("mainSubmitButton")),br);
     schk=orderprocess(driver,br);;
     drive.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
                return j;
                                
                                
                                
               
  }
  
 public String TNcheck( String br,String fstn, String midtn, String lstn, String check,WebDriver driver) throws Exception
  {
                System.out.println(fstn);
                System.out.println(midtn);
                System.out.println(lstn);
                
         String schk="Fail";
         if(check.equals("no dropdown"))
         {
                
             driver.findElement(By.id("CFUStatusdrop")).click();
                 Thread.sleep(1000);
                // driver.findElement(By.id("ddlCFUnum")).click();
                 
         }
         else
         {
                 
                 System.out.println("else");
                 driver.findElement(By.id("CFUStatus")).click();
                 
                 Thread.sleep(2000);
                 
                 driver.findElement(By.id("txtAreaCode0")).clear();
                 driver.findElement(By.id("txtAreaCode0")).sendKeys(fstn);
                 driver.findElement(By.id("txtExchange0")).clear();
                 driver.findElement(By.id("txtExchange0")).sendKeys(midtn);
                 driver.findElement(By.id("txtTelNum0")).clear();
                 driver.findElement(By.id("txtTelNum0")).sendKeys(lstn);
                 
                      //   System.out.println(driver.findElement(By.xpath("//*[@id='TNGridRefresh']/div")).getText());
                         Thread.sleep(1000);
         }
      focusClick(driver,driver.findElement(By.id("mainSubmitButton")),br);
      Thread.sleep(3000);
        
         if(driver.findElement(By.cssSelector("span.help-block.error")).isDisplayed())
                                 {
                 statusTracker(br,"Pass","Verify if error message is displayed when adding "+check+" TN","Error message is displayed: "+ driver.findElement(By.cssSelector("span.help-block.error")).getText(),"Error message should be displayed");
                 schk="Pass";
                                 }
                                 else
                                 {
                                                 statusTracker(br,"Fail","Verify if error message is displayed when adding "+check+" TN","Error message is not displayed","Error message should be displayed");
                                                 schk="Fail";
                                 }
         return schk;
  }  

private boolean assertTrue(By elementPresent) {
                // TODO Auto-generated method stub
                return false;
}

public String TNValidation(String br,WebDriver driver) throws Exception
  {
         String schk ="Pass";
         schk=TNcheck(br,"022","300","4000","first digit 0",driver);
         schk=TNcheck(br,"222","000","4000","fourth digit 0",driver);
         schk=TNcheck(br,"122","300","4000","first digit 1",driver);
         schk=TNcheck(br,"222","152","4000","fourth digit 1",driver);
         schk=TNcheck(br,"900","000","4000","first digits 900",driver);
         schk=TNcheck(br,"976","000","4000","first digits 976",driver);
         schk=TNcheck(br,"","","","blank",driver);
        // schk=TNcheck(driver, ac1,midtn1,lastfour1,"existing");
         schk=TNcheck(br,"99","9","99","Invalid",driver);
         schk=TNcheck(br,phoneline_ac,phoneline_midtn,phoneline_lastfour,"self",driver);
         schk=TNcheck(br,"","","","no dropdown",driver);
         driver.findElement(By.id("mainCancelButton")).click();              
         schk="Pass";
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
  //  wb.close();

 driver.manage().timeouts().implicitlyWait(tlim,TimeUnit.SECONDS); 
                        //TimeUnit.SECONDS);
 System.out.println("qtest1");
    //setUp();
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
  	  //  focusClick(driver, driver.findElement(By.linkText("Settings")),br);fire
  	    Thread.sleep(5000);
  	  focusClick(driver, driver.findElement(By.xpath("(//a[contains(text(),'(edit)')])[4]")),br);
  	  
     Thread.sleep(5000);
                              
       focusClick(driver,  driver.findElement(By.xpath(".//*[@id='tab_5']/a")),br);
           Thread.sleep(5000);
           focusClick(driver, driver.findElement(By.id("CFUActivatedOn")),br);  
       focusClick(driver, driver.findElement(By.xpath(".//*[@class='card poping buttonLink']")),br);
   	Thread.sleep(5000);
	    driver.findElement(By.id("FriendlyNameDialogContactName")).clear();
	    Thread.sleep(5000);

	    focusClick(driver,   driver.findElement(By.cssSelector("div.modal-footer > button.btn.btn-primary")),br);
	  
               
                phoneline=driver.findElement(By.id("phonePullDownSelected")).getText();

  

                               phoneline_ac=phoneline.substring(1,4);
                               phoneline_midtn=phoneline.substring(6,9);
                               phoneline_lastfour=phoneline.substring(10,14); 
      
             
         String status= driver.findElement(By.id("CFUActivatedOn")).getText();

         System.out.println("class is : "+status);
         String status1="on";
         //String status="off";
         if(status.equals("On"))
         {
               status1="off";
               status="on";
               System.out.println("On");
         }
         else
         {
                 status1="on";
             status="off";
             System.out.println("Off");
         }
         String schk="Pass"; 
         schk=flowrun1(br,status, status1, driver);
         System.out.println("one transation sucess"+schk);
         if(schk.equals("Pass"))
         {
                 System.out.println("check transation sucess");
                 
                  status= driver.findElement(By.id("CFUActivatedOff")).getText();
                  System.out.println("Hai");
                  if(status.equals("Off"))
                  {
                                  status1="on";
                  status="off";
                                  schk=flowrun1(br,status, status1, driver);
                                  System.out.println("one transation sucess"+schk);
                                  if(schk.equals("Pass"))
                  {
                        statusTracker(br,"","Verify TN Validation","","");
                        schk=TNValidation(br,driver);
                        System.out.println("Hai1");
                  }
                  }
                  else
                  {
                     if(schk.equals("Pass"))
                  {
                        statusTracker(br,"","Verify TN Validation","","");
                        schk=TNValidation(br,driver);
                        System.out.println("TN Validation done");
                  }
                  status1="off";
                  status="on";
                  schk=flowrun1(br,status, status1, driver);
                  }
                  focusClick(driver,driver.findElement(By.id("mainCancelButton")),br);
                  first=1;         
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


