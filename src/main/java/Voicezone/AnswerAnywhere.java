package Voicezone;



import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import java.io.File;
import java.io.PrintStream;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.ui.Select;

import jxl.Cell;
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
public class AnswerAnywhere extends CommonFunctions
{
                String table, tns[];
                String tlimit,username,pwd;
                String ac1,midtn1,lastfour1;
                String phoneline,phoneline_ac,phoneline_midtn,phoneline_lastfour;
                int chk123=0;
                int tncount,tlim;


  public AnswerAnywhere(String s)
  {
                  this.path = s;
  }

  public String flowrun(String br,WebDriver driver) throws Exception
  {
                                boolean status = driver.findElement(By.id("AnswerAnywhereOn")).isSelected();
                                
                                boolean status1 = driver.findElement(By.id("AnswerAnywhereOff")).isSelected();
                                
                                String from="",to="";
                                if(status)
                                {
                                                from = "Enabled";
                                                to="Disabled";
                                                driver.findElement(By.id("AnswerAnywhereOff")).click();
                                }
                                else if (!status)
                                {
                                                from = "Disabled";
                                                to="Enabled";
                                                driver.findElement(By.id("AnswerAnywhereOn")).click();
                                  }
                                                
                                  Thread.sleep(5000);            
                                  focusClick(driver,driver.findElement(By.id("mainSubmitButton")),br);   
                
Thread.sleep(20000);
                

                                String schk=orderprocess(driver,br);
                                Thread.sleep(2000);
                  
                                if(schk.equals("Fail"))
                                {
                                                statusTracker(br,"Fail","Verify order processing when switching from "+from+" to "+to,"Order processing has failed","Order should be processed successfully");
                                               /* if(isElementPresent(driver,(By.id("modalContinueButton"))))
                                                {  
                                                	Thread.sleep(2000);
                                                                focusClick(driver, driver.findElement(By.id("modalContinueButton")),br);              
                                                }*/
                                }
                                else
                                {
                                                statusTracker(br,"Pass","Verify order processing when switching from "+from+" to "+to,"Order processing has completed successfully","Order should be processed successfully");
                                }
                                return schk;
  }
   
  public String flowrun1(String br,WebDriver driver) throws Exception
  {
                  String schk ="Fail";
                  
                  
                  String ac;
                  String midtn;
                  String lastfour;
                  String tn;
                  int count;
                   ac=randomNO(999,200);
                                  midtn=randomNO(999,200);
                                  //String midtn="345";
                                  lastfour= randomNO(9999,1000);
                                // ac=phoneline_ac;   
                                  //midtn=phoneline_midtn; 
                                  driver.findElement(By.id("txtAreaCode")).clear();
                                    driver.findElement(By.id("txtAreaCode")).sendKeys(ac);
                                    driver.findElement(By.id("txtExchange")).clear();
                                    driver.findElement(By.id("txtExchange")).sendKeys(midtn);
                                    driver.findElement(By.id("txtTelNum")).clear();
                                   driver.findElement(By.id("txtTelNum")).sendKeys(lastfour);
                                    focusClick(driver,driver.findElement(By.id("AddToPhoneNumbers")),br);           
                                    Thread.sleep(5000);
                                   focusClick(driver,driver.findElement(By.id("mainSubmitButton")),br);      
                                
                                  Thread.sleep(5000);
                                  schk=orderprocess(driver,br);
                  
                  tn=ac+midtn+lastfour;
                  
                 
                  
                  
                  count =driver.findElements(By.xpath("//html/body/div[3]/form/div/div[3]/div[2]/div/div/div/div/div[3]/div/div[4]/table/tbody/tr")).size();
                  System.out.println("tns present: " +count);
                  int chk=0;
                  for(int i=1;i<=count;i++)
                  {
                                  Thread.sleep(5000);
                                  String value=driver.findElement(By.xpath("//html/body/div[3]/form/div/div[3]/div[2]/div/div/div/div/div[3]/div/div[4]/table/tbody/tr["+i+"]/td")).getText();
                                  System.out.println(value+tn);
                                  value=value.replaceAll(" ","");
                                  value=value.replaceAll("\\(","");
                                  value=value.replaceAll("\\)","");
                                  value=value.replaceAll("-","");
                                  if(value.equals(tn))
                                  {
                                                  statusTracker(br,"Pass","Verify if added TN is present in the list","Added TN is present in the list","Added TN should be present in the list");
                                                  chk=1;
                                                  schk="Pass";
                                  }
                  }
                  if(chk==0)
                  {
                                                statusTracker(br,"Fail","Verify if added TN is present in the list","Added TN is not present in the list","Added TN should be present in the list");
                                                schk="Fail";
                  }
                  if(schk.equals("Pass"))
                  {
                                  focusClick(driver,driver.findElement(By.id("mainSubmitButton")),br);   
                                  Thread.sleep(5000);
                                  schk=orderprocess(driver,br);
                                  Thread.sleep(5000);
                                  //schk="Pass";
                                  if(schk.equals("Pass"))
                                  {
                                                  statusTracker(br,"Pass","Verify if add TN order is processed successfully","Order is successfully processed","Order should be successfully processed");  
                                                  //int count1 =selenium.getXpathCount("//html/body/div[3]/form/div/div[3]/div[2]/div/div/div/div/div/div/div/div[5]/table/tbody//tr").intValue();
                                                  
                                  }
                                  else
                                  {
                                                  statusTracker(br,"Fail","Verify if add TN order is processed successfully","Order is not successfully processed","Order should be successfully processed");
                                                  if(isElementPresent(driver,(By.id("mainSubmitButton"))))
                                                  {
                                                                focusClick(driver,driver.findElement(By.id("mainSubmitButton")),br);      
                                                                                
                                                  }
                                                  schk="Fail";
                                  }
                 }

                  //schk="Pass";
                  
                                  
                                  
                  if(schk.equals("Pass"))
                  {               Thread.sleep(5000);
                                  driver.findElement(By.id("DeleteNumber_"+ tn)).click();
                                  do{
                                                  
                                  }while((isElementPresent(driver,(By.xpath("//div[13]")))&&chk123==0));
                                  chk123=0;
                                Thread.sleep(5000);
                                focusClick(driver,driver.findElement(By.id("mainSubmitButton")),br);      
                                Thread.sleep(4000);
                                  schk=orderprocess(driver,br);
                                
                                  Thread.sleep(5000);
                                  if(schk.equals("Pass"))
                                  {
                                                  statusTracker(br,"Pass","Verify if remove TN order is processed successfully","Order is successfully processed","Order should be successfully processed");  
                                                  
                                                  
                                  }
                                  else
                                  {
                                                  statusTracker(br,"Fail","Verify if remove TN order is processed successfully","Order is not successfully processed","Order should be successfully processed");
                                                  
                                                  schk="Fail";
                                  }
                                  
                                  
                  }
                  
                  return schk;
  }
  
  public String deleteall(String br,WebDriver driver) throws Exception
  {
                  System.out.println("Starting deleteall");
                  String schk ="false";
                  int chk;
                  
                                                                                                                                                                 
                  int count = driver.findElements(By.xpath("//html/body/div[3]/form/div/div[3]/div[2]/div/div/div/div/div[3]/div/div[4]/table/tbody/tr")).size();
                  System.out.println("count"+count);
                  String tn;
                  
                  if(isElementPresent(driver, By.cssSelector("td.phone")))
                  {
                                  for(int i=1;i<=count;i++)
                                  {
                                                  
                                                  Thread.sleep(2000);
                                                  String s= driver.findElement(By.xpath("//html/body/div[3]/form/div/div[3]/div[2]/div/div/div/div/div[3]/div/div[4]/table/tbody/tr["+i+"]/td")).getText();
                                                  s=s.replaceAll(" ","");
                                                  s=s.replaceAll("\\(","");
                                                  s=s.replaceAll("\\)","");
                                                  s=s.replaceAll("-","");
//                                              System.out.println(selenium.getValue("//html/body/div/section/form/div[2]/div/div["+i+"]/table/tbody/tr/td//input"));
                                                  
                                                  tn=s;
                                                  System.out.println(tn);
                                                  //selenium.click("//*[@id='DeleteNumber_"+ tn + "']");
                                                  Thread.sleep(5000);
                                                  driver.findElement(By.id("DeleteNumber_"+ tn)).click();
                                                  Thread.sleep(5000);
                                                
                                                  i=0;
                                                  count =driver.findElements(By.xpath("//html/body/div[3]/form/div/div[3]/div[2]/div/div/div/div/div[3]/div/div[4]/table/tbody/tr")).size();
                                                  System.out.println("ss: "+count);
                                                
                                  
                                  }
                                
                                  boolean status =driver.findElement(By.id("AnswerAnywhereOn")).isSelected();
                                  System.out.println(status);
                                  if(status)
                                  {    
                                                  driver.findElement(By.id("AnswerAnywhereOff")).click();
                                                  System.out.println("rrr"); 
                                  }
                                  Thread.sleep(2000);
                                focusClick(driver,driver.findElement(By.id("mainSubmitButton")),br);      
                                Thread.sleep(20000);
                                  schk=orderprocess(driver,br);
                
                                  count =driver.findElements(By.xpath("//html/body/div[3]/form/div/div[3]/div[2]/div/div/div/div/div[3]/div/div[4]/table/tbody/tr")).size();
                                  System.out.println(count);
                                  
                                  status = driver.findElement(By.id("AnswerAnywhereOn")).isSelected();
                                  System.out.println(status);
                                  if(!status)
                                  {
                                                  focusClick(driver,driver.findElement(By.id("AnswerAnywhereOn")),br);
                                                  
                                                  System.out.println("rrra"); 
                                                  
                                                  
                                                  /*do{
                                                                  
                                                                }while((drive.findElements(By.xpath("//div[13]")).size()!=0));*/
                                  }
                                  Thread.sleep(5000);
                                focusClick(driver,driver.findElement(By.id("mainSubmitButton")),br);      
                       
                                
                                if(driver.findElement(By.xpath("//div[@id='ValidationBlock']/div/span")).isDisplayed())
                                  {
                                                  statusTracker(br,"Pass","Verify if error message is displayed when trying to enable feature with no TNs","Error message is displayed: " + driver.findElement(By.cssSelector("span.help-block.error")).getText(),"Error message should be displayed");
                                  }
                                  else
                                  {
                                                  statusTracker(br,"Fail","Verify if error message is displayed when trying to enable feature with no TNs","Error message is not displayed","Error message should be displayed");
                                                  schk="Fail";
                                  }
                                  
                                  
                  }
                  System.out.println("chk222");
                  if(!(isElementPresent(drive, By.cssSelector("td.phone"))))
                  {
                                  
                                 ac1=randomNO(999,200);
                                  midtn1=randomNO(999,200);
                                  //String midtn="345";
                                  lastfour1= randomNO(9999,1000);
                                  //ac1=phoneline_ac;  //-------------------------------------------------------------
                                  //midtn1=phoneline_midtn; //-------------------------------------------------------
                                   driver.findElement(By.id("txtAreaCode")).clear();
                                    driver.findElement(By.id("txtAreaCode")).sendKeys(ac1);
                                    driver.findElement(By.id("txtExchange")).clear();
                                    driver.findElement(By.id("txtExchange")).sendKeys(midtn1);
                                    driver.findElement(By.id("txtTelNum")).clear();
                                    driver.findElement(By.id("txtTelNum")).sendKeys(lastfour1);
                                    focusClick(driver,driver.findElement(By.id("AddToPhoneNumbers")),br);
                                  Thread.sleep(5000);
                

                                  
                                focusClick(driver,driver.findElement(By.id("mainSubmitButton")),br);      
                                Thread.sleep(15000);
                                  schk=orderprocess(driver,br);
                
                  }
  return schk;
}
  
  public String flowrunmaxtn(String br,WebDriver driver) throws Exception
  {
                  String schk ="Fail";
                  
                  
                  String ac;
                  String midtn;
                  String lastfour;
                  String tn;
                  int count;
                  
//              TN=TN+lastfour;
                // int limit =selenium.getXpathCount("//html/body/div[3]/form/div/div[3]/div[2]/div/div/div/div/div/div/div/div[5]/table/tbody//tr").intValue();
                  
                  do{
                                  ac=randomNO(999,200);
                                  if(ac.equals("900") || ac.equals("976"))
                                                  ac=randomNO(999,200);  
                                  midtn=randomNO(999,200);
                                  //String midtn="345";
                                  lastfour= randomNO(9999,1000);
                                  ac=phoneline_ac;  //-------------------------------------------------------------
                                  midtn=phoneline_midtn; //-------------------------------------------------------
                                  driver.findElement(By.id("txtAreaCode")).clear();
                                    driver.findElement(By.id("txtAreaCode")).sendKeys(ac);
                                    driver.findElement(By.id("txtExchange")).clear();
                                    driver.findElement(By.id("txtExchange")).sendKeys(midtn);
                                    driver.findElement(By.id("txtTelNum")).clear();
                                    driver.findElement(By.id("txtTelNum")).sendKeys(lastfour);
                                    
                                    focusClick(driver,driver.findElement(By.id("AddToPhoneNumbers")),br);
                                    
                                  Thread.sleep(2000);
                
                                
                                  
                                focusClick(driver,driver.findElement(By.id("mainSubmitButton")),br);
                                  Thread.sleep(2000);
                                  schk=orderprocess(driver,br);
                
                  
                  tn=ac+midtn+lastfour;
                  //do{
                  //}while(selenium.isElementPresent("//body/div[10]"));
                
                  
                  //System.out.println(selenium.getXpathCount("//*[@id='TNGridRefresh']/div"));
                  count =driver.findElements(By.xpath("//html/body/div[3]/form/div/div[3]/div[2]/div/div/div/div/div[3]/div/div[4]/table/tbody/tr")).size();
                  int chk=0;
                  for(int i=1;i<=count;i++)
                  {
                                  String value=driver.findElement(By.xpath("//html/body/div[3]/form/div/div[3]/div[2]/div/div/div/div/div[3]/div/div[4]/table/tbody/tr["+i+"]/td")).getText();
                                  System.out.println(value);
                                
                                  value=value.replaceAll(" ","");
                                  value=value.replaceAll("\\(","");
                                  value=value.replaceAll("\\)","");
                                  value=value.replaceAll("-","");
                                  if(value.equals(tn))
                                  {
                                                  statusTracker(br,"Pass","Verify if added TN is present in the list","Added TN is present in the list. Added TN: "+count,"Added TN should be present in the list");
                                                  chk=1;
                                                  schk="Pass";
                                  }
                  }
                  if(chk==0)
                  {

                  
                                                statusTracker(br,"Fail","Verify if added TN is present in the list","Added TN is not present in the list. Total Tns present "+count,"Added TN should be present in the list");
                                                schk="Fail";
                  }
                  if(isElementPresent(driver,By.cssSelector("span.help-block.error")))
                  {
                                                                statusTracker(br,"Fail","Verify if 5 TNs can be added to the list","Error message is present before the 5 TNs are completed added","TN should be added to the list");
                                                                schk="Fail";
                  }
                  }while(count<5 && schk.equals("Pass"));
                                  
                  if(schk.equals("Pass"))
                  {
                                  ac=randomNO(999,200);
                                  midtn=randomNO(999,200);
                                  //String midtn="345";
                                  lastfour= randomNO(9999,1000);
                                  ac=phoneline_ac;  //-------------------------------------------------------------
                                  midtn=phoneline_midtn; //-------------------------------------------------------
                                  
                                  driver.findElement(By.id("txtAreaCode")).clear();
                                    driver.findElement(By.id("txtAreaCode")).sendKeys(ac);
                                    driver.findElement(By.id("txtExchange")).clear();
                                    driver.findElement(By.id("txtExchange")).sendKeys(midtn);
                                    driver.findElement(By.id("txtTelNum")).clear();
                                    driver.findElement(By.id("txtTelNum")).sendKeys(lastfour);
                                    focusClick(driver,driver.findElement(By.id("AddToPhoneNumbers")),br);
                                    
                                    Thread.sleep(2000);
                
                                  
                                  if(isElementPresent(driver,By.cssSelector("span.help-block.error")))
                                  {
                                                  statusTracker(br,"Pass","Verify if error message is displayed when adding 6th TN","Error message is displayed","Error message should be displayed");
                                  }
                                  else
                                  {
                                                  statusTracker(br,"Fail","Verify if error message is displayed when adding 6th TN","Error message is not displayed","Error message should be displayed");
                                                  schk="Fail";
                                  }
                  }
                  
                  if(schk.equals("Pass"))
                  {
           
                                  
                                focusClick(driver,driver.findElement(By.id("mainSubmitButton")),br);      
                                  schk=orderprocess(driver,br);
                                  if(schk.equals("Pass"))
                                  {
                                                  statusTracker(br,"Pass","Verify if adding 5 TNs is processed successfully","Order is successfully processed","Order should be successfully processed");
                                                  int count1 =driver.findElements(By.xpath("//html/body/div[3]/form/div/div[3]/div[2]/div/div/div/div/div[3]/div/div[4]/table/tbody/tr")).size();
                                                  if(count1==5)
                                                  {
                                                                  statusTracker(br,"Pass","Verify if 5 TNs are displayed after order process","5 TNs are displayed after order process","TN is displayed after order process");  
                                                  }
                                                  else
                                                  {
                                                                  statusTracker(br,"Fail","Verify if 5 TNs are displayed after order process","5 TNs are not displayed after order process","TN is displayed after order process");
                                                                  schk="Fail";
                                                  }
                                  }
                                  else
                                  {
                                                  statusTracker(br,"Fail","Verify if adding 5 TNs is processed successfully","Order is not successfully processed","Order should be successfully processed");
                                                  if(isElementPresent(driver,By.id("modalContinueButton")))
                                                  {
                                                                focusClick(driver,driver.findElement(By.id("mainSubmitButton")),br);      
                                                  }
                                                  schk="Fail";
                                  }
                  }
                  
                  return schk;
  }
  
  public String TNcheck(String br,String ac, String midtn, String lastfour, String check,WebDriver driver) throws Exception
  {
                  String schk="Fail";
                  int limit =driver.findElements(By.xpath("//html/body/div[3]/form/div/div[3]/div[2]/div/div/div/div/div[3]/div/div[4]/table/tbody/tr")).size();
                  System.out.println("limit is"+limit);
                  int count;
                  String tn;

                  driver.findElement(By.id("txtAreaCode")).clear();
                    driver.findElement(By.id("txtAreaCode")).sendKeys(ac);
                    driver.findElement(By.id("txtExchange")).clear();
                    driver.findElement(By.id("txtExchange")).sendKeys(midtn);
                    driver.findElement(By.id("txtTelNum")).clear();
                    driver.findElement(By.id("txtTelNum")).sendKeys(lastfour);
                    Thread.sleep(5000);
                    
                    driver.findElement(By.id("AddToPhoneNumbers")).click();
                    Thread.sleep(5000);
                  
                    /*  if(errmesg)
                      {
                             Thread.sleep(2000);
                                  statusTracker(br,"Pass","Verify if error message is displayed on clicking the friendly icon when adding "+check+" TN","Error message is displayed: "+  driver.findElement(By.cssSelector("div.modal-body > p")).getText(),"Error message should be displayed");
                           
                             System.out.println("Error message"+ driver.findElement(By.cssSelector("div.modal-body > p")).getText());
                             schk="Pass";
                      }
                      else
                      {
                             statusTracker(br,"Fail","Verify if error message is displayed on clicking the friendly icon when adding "+check+" TN","Error message is not displayed","Error message should be displayed");
                             schk="Fail";
                      }
                      
                      Thread.sleep(4000);*/
                   
                  

                  tn=ac+midtn+lastfour;
                  count=driver.findElements(By.xpath("//html/body/div[3]/form/div/div[3]/div[2]/div/div/div/div/div[3]/div/div[4]/table/tbody/tr")).size();
                  boolean errmesg= driver.findElement(By.cssSelector("span.help-block.error")).isDisplayed();
                  System.out.println(count);
                  if(errmesg && count==limit)
                  {
                                  statusTracker(br,"Pass","Verify if error message is displayed when adding "+check+" TN","Error message is displayed: "+ driver.findElement(By.xpath(".//*[@id='ValidationBlock']/div/span")).getText(),"Error message should be displayed");
                                  schk="Pass";
                  }
                  else
                  {
                                  statusTracker(br,"Fail","Verify if error message is displayed when adding "+check+" TN","Error message is not displayed","Error message should be displayed");
                                  schk="Fail";
                  }
                  
                  
                  return schk;
  }
  
  public String TNValidation(String br,WebDriver driver) throws Exception
  {
                  String schk ="Pass";
                  schk=TNcheck(br,"022","300","4000","first digit 0",driver);
                  Thread.sleep(2000);
                  schk=TNcheck(br,"222","000","4000","fourth digit 0",driver);
                  Thread.sleep(2000);
                  schk=TNcheck(br,"122","300","4000","first digit 1",driver);
                  Thread.sleep(2000);
                  schk=TNcheck(br,"222","152","4000","fourth digit 1",driver);
                  Thread.sleep(2000);
                 schk=TNcheck(br,"222","000","4000","fourth digit 0",driver);
                 Thread.sleep(2000);
                  schk=TNcheck(br,"976","222","4000","976 TN",driver);
                  Thread.sleep(2000);
                  schk=TNcheck(br,"976","222","4000","900 TN",driver);
                  Thread.sleep(2000);
                  schk=TNcheck(br,"","","","blank",driver);
                  Thread.sleep(2000);
                // schk=TNcheck(br,ac1,midtn1,lastfour1,"existing",driver);
                  schk=TNcheck(br,"99","9","99","Invalid",driver);
                  Thread.sleep(2000);
                  
                  
                  schk="Pass";
                  return schk;
  }
  
  public String randomNO(int max, int min)
  {
                                int Max=max;
                                int Min=min;
                                double random1=Min + (int)(Math.random() * ((Max - Min) + 1));
                                System.out.println(random1);
                                int random2=(int)random1;
                                System.out.println(random2);
                                String s1 = new Integer(random2).toString();
                                return(s1);
                                
  }
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
     // wb.close();

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
                  focusClick(driver, driver.findElement(By.xpath("(//a[contains(text(),'(edit)')])[4]")),br);
                 Thread.sleep(5000);
               /* focusClick(driver, driver.findElement(By.xpath("html/body/div[3]/form/div/div/div[3]/div[4]/div/a")),br);
                    do{
                                      Thread.sleep(1000);
                                      System.out.println("chk value is" +chk);
                                       chk++;
                                     }while( !(driver.findElements(By.id("FriendlyNameDialogContactName")).size()>0) && chk<30);
                
                    driver.findElement(By.id("FriendlyNameDialogContactName")).clear();
                  
                    
                    focusClick(driver,   driver.findElement(By.cssSelector("div.modal-footer > button.btn.btn-primary")),br);
                  
                    
                                                phoneline=driver.findElement(By.id("phonePullDownSelected")).getText();*/
                                                
                                                /*System.out.println(phoneline);
                          phoneline_ac=phoneline.substring(1,4);
                          System.out.println(phoneline_ac);
                                                  phoneline_midtn=phoneline.substring(6,9);
                                                System.out.println(phoneline_midtn);
                                                  phoneline_lastfour=phoneline.substring(10,14);
                                                System.out.println(phoneline_lastfour);*/
                                                boolean status =driver.findElement(By.id("AnswerAnywhereOn")).isSelected();
                                                System.out.println("chk000"+status);
                                                String status1="off";
                                                if(!status)
                                                                status1="true";
                                                
                                                String schk="Pass"; 
                                                System.out.println("line 623");
                                                
                                                
                                                schk=deleteall(br,driver);
                                                
                                                
                                                if(schk.equals("Pass"))
                                                                schk=flowrun(br,driver);
                                                
                                                if(schk.equals("Pass"))
                                                {
                                                                status = driver.findElement(By.id("AnswerAnywhereOn")).isSelected();
                                                                
                                                                statusTracker(br,"","Verify TN add/remove when feature is "+status,"","");
                                                                schk=flowrun1(br,driver);
                                                }
                                                
                                                if(schk.equals("Pass"))
                                                                schk=flowrun(br,driver);
                                                
                                                if(schk.equals("Pass"))
                                                {
                                                                status = driver.findElement(By.id("AnswerAnywhereOn")).isSelected();
                                                                
                                                                statusTracker(br,"","Verify TN add/remove when feature is "+status,"","");
                                                                schk=flowrun1(br,driver);
                                                }
                                                
                                                if(schk.equals("Pass"))
                                                {
                                                                statusTracker(br,"","Verify TN Validation","","");
                                                                schk=TNValidation(br,driver);
                                                }
                                                
                                             /*   if(schk.equals("Pass"))
                                                {
                                                                statusTracker(br,"","Verify maximum TN operations","","");
                                                                schk=flowrunmaxtn(br,driver);                  
                
                                                }
                                                */
                                                
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
}
