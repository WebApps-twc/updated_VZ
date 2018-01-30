package Voicezone;

import java.io.File;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;

public class DistinctiveRing extends CommonFunctions {
                
                String table, tns[];
    String tlimit,username,pwd;
    int tncount;                                                    
                                
    String phoneline,phoneline_ac,Acccode,ac1,midtn1,lastfour1;
                int rank[]= new int[50];
                

                int c_sequence;
                String name_for_rank[]= new String[40];
                String price, rank_channels;

                public DistinctiveRing(String path) {
                                this.path = path;
                }

  public String flowrun(WebDriver driver,String br) throws Exception
  {
                  System.out.println("STarting flowrun");
                  Boolean status = driver.findElement(By.xpath("/html/body/div[3]/form/div/div[3]/div[2]/div/div/div[3]/div/div[1]/div[1]/p[1]/input[1]")).isSelected();
                  System.out.println("Initial state: "+status);
                Boolean status1=false;
                  //System.out.println("Initial state: "+status1);
                  //if (status.equals(false))
                                //  System.out.println("Initial state1: "+status);
                  //status1=true;
                  //System.out.println("Initial state1: "+status1);
                                
                  String schk="Fail";
                  if(status.equals(true))
                  {
                	  Thread.sleep(5000);
                                  //System.out.println("Initial state: "+status);
                                  
                                  //focusClick(driver,driver.findElement(By.xpath("/html/body/div[3]/form/div/div[3]/div[2]/div/div/div[3]/div/div[1]/div[1]/p[1]/input[1]")),br);
          focusClick(driver,driver.findElement(By.id("drActivated")),br);
          Thread.sleep(2000);
                                  focusClick(driver,driver.findElement(By.id("mainSubmitButton")),br);
                                  System.out.println("Status changed to: "+status1);
                                  
                                  schk=orderprocess(driver,br);
                                  if(schk.equals("Pass"))
                                  {
                                                  statusTracker(br,"Pass","Verify if disabling DistinctiveRing order is processed successfully","Order is successfully processed from changing status to "+status1,"Order should be successfully processed");
                                  }
                                  else
                                  {
                                                  statusTracker(br,"Fail","Verify if disabling DistinctiveRing order is processed successfully","Order is not successfully processed","Order should be successfully processed");
                                  }
                  }
                  else if(status.equals(false))
                  {
                                  //driver.findElement(By.id("Activated")).click();
                                  //Thread.sleep(1000);
                                  System.out.println("Initial state2: "+status);
                                  Thread.sleep(5000);
                                  
                                                 // driver.findElement(By.id("Activated")).click();
                                                //  Thread.sleep(1000);
                                  focusClick(driver,driver.findElement(By.id("drActivated")),br);
                                 // focusClick(driver,driver.findElement(By.xpath("/html/body/div[3]/form/div/div[3]/div[2]/div/div/div[3]/div/div[1]/div[1]/p[1]/input[1]")),br);
                                                  String ac=randomNO(999,200);
                                                  String midtn=randomNO(999,200);
                                                  //String midtn="345";
                                                  String lastfour= randomNO(9999,1000);
                                  
                          driver.findElement(By.id("txtAreaCode")).clear();
                          driver.findElement(By.id("txtAreaCode")).sendKeys(ac);
                          driver.findElement(By.id("txtExchange")).clear();
                          driver.findElement(By.id("txtExchange")).sendKeys(midtn);
                          driver.findElement(By.id("txtTelNum")).clear();
                          driver.findElement(By.id("txtTelNum")).sendKeys(lastfour);
                          Thread.sleep(2000);
                          focusClick(driver,driver.findElement(By.id("AddToPhoneNumbers")),br);
                          Thread.sleep(2000);
                          focusClick(driver,driver.findElement(By.id("mainSubmitButton")),br);
                                                  schk=orderprocess(driver,br);
                                                  System.out.println("Initial state2: "+status);
                                                  if(schk.equals("Pass"))
                                                  {
                                                                  statusTracker(br,"Pass","Verify if enabling DistinctiveRing order is processed successfully","Order is successfully processed to state "+status1,"Order should be successfully processed");
                                                  }
                                                  else
                                                  {
                                                                  statusTracker(br,"Fail","Verify if enabling DistinctiveRing order is processed successfully","Order is not successfully processed","Order should be successfully processed");
                                                
                                                  }
                                                  System.out.println("Ending flowrun");
                  }
                  
                return schk;
                  }

public String flowrun1(WebDriver driver,String br) throws Exception
  {
                  String schk ="Fail";
                  
                  
                  String ac;
                  String midtn;
                  String lastfour;
                  String tn;
                  int count;
                  System.out.println("Starting flowrun1");
//              TN=TN+lastfour;
                                  
                  int limit =driver.findElements(By.xpath("//html/body/div[3]/form/div/div[3]/div[2]/div/div/div[3]/div/div/div[2]/table/tbody/tr")).size();
                                  
                                  ac=randomNO(999,200);
                                  midtn=randomNO(999,200);
                                  //String midtn="345";
                                  lastfour= randomNO(9999,1000);
                  
                                  driver.findElement(By.id("txtAreaCode")).clear();
          driver.findElement(By.id("txtAreaCode")).sendKeys(ac);
          driver.findElement(By.id("txtExchange")).clear();
          driver.findElement(By.id("txtExchange")).sendKeys(midtn);
          driver.findElement(By.id("txtTelNum")).clear();
          driver.findElement(By.id("txtTelNum")).sendKeys(lastfour);

                // System.out.println(selenium.getXpathCount("//*[@id='TNGridRefresh']/div"));
                  Thread.sleep(1000);
                  focusClick(driver,driver.findElement(By.id("AddToPhoneNumbers")),br);
                  tn=ac+midtn+lastfour;
                  //do{
                  //}while(selenium.isElementPresent("//body/div[10]"));
                  Thread.sleep(2000);
                  
                  //System.out.println(selenium.getXpathCount("//*[@id='TNGridRefresh']/div"));
                  
                  count =driver.findElements(By.xpath("//html/body/div[3]/form/div/div[3]/div[2]/div/div/div[3]/div/div/div[3]/table/tbody//tr")).size();
                  int chk=0;
                  for(int i=1;i<=count;i++)
                  {
                                  
                                  System.out.println(driver.findElement(By.xpath("//html/body/div[3]/form/div/div[3]/div[2]/div/div/div[3]/div/div/div[3]/table/tbody/tr["+i+"]/td//input")).getAttribute("value"));
                                  //"//html/body/div[3]/form/div/div[3]/div[2]/div/div/div[3]/div/div/div[3]/table/tbody/tr["+i+"]/td/input"
                                  if((driver.findElement(By.xpath("//html/body/div[3]/form/div/div[3]/div[2]/div/div/div[3]/div/div/div[3]/table/tbody/tr["+i+"]/td//input")).getAttribute("value")).equals(tn))
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
                                //  driver.findElement(By.id("mainSubmitButton")).click();
                                  schk=orderprocess(driver,br);
                                  ///OMG
                                  //schk="Pass";
                                  if(schk.equals("Pass"))
                                  {
                                                  statusTracker(br,"Pass","Verify if add TN order is processed successfully","Order is successfully processed","Order should be successfully processed");  
                                                  int count1 =driver.findElements(By.xpath("//html/body/div[3]/form/div/div[3]/div[2]/div/div/div[3]/div/div/div[3]/table/tbody//tr")).size();
                                                  
                                                  for(int i=1;i<=count1;i++)
                                                  {
                                                                  
                                                                  System.out.println(driver.findElement(By.xpath("//html/body/div[3]/form/div/div[3]/div[2]/div/div/div[3]/div/div/div[3]/table/tbody/tr["+i+"]/td//input")).getAttribute("value"));
                                                                  //"//html/body/div[3]/form/div/div[3]/div[2]/div/div/div[3]/div/div/div[3]/table/tbody/tr["+i+"]/td/input"
                                                                  if((driver.findElement(By.xpath("//html/body/div[3]/form/div/div[3]/div[2]/div/div/div[3]/div/div/div[3]/table/tbody/tr["+i+"]/td//input")).getAttribute("value")).equals(tn))
                                                                  {
                                                                                  chk=1;
                                                                                  schk="Pass";
                                                                  }
                                                  }

                                                  if(chk==1)
                                                  {
                                                                  statusTracker(br,"Pass","Verify if TN is displayed after order process","TN is displayed after order process","TN is displayed after order process");  
                                                  }
                                                  else
                                                  {
                                                                  statusTracker(br,"Fail","Verify if TN is displayed after order process","TN is not displayed after order process","TN is displayed after order process");
                                                                  schk="Fail";
                                                  }
                                  }
                                  else
                                  {
                                                  statusTracker(br,"Fail","Verify if add TN order is processed successfully","Order is not successfully processed","Order should be successfully processed");
                                                  /*if(isElementPresent("modalContinueButton"))
                                                  {
                                                                  focusClick(driver,driver.findElement(By.id("modalContinueButton")),br);
                                                  }*/
                                                  schk="Fail";
                                  }
                  }
                  //OMG
                  //schk="Pass";
                  
                                  
                                  
                  if(schk.equals("Pass"))
                  {
                                  focusClick(driver,driver.findElement(By.id("DeleteNumber_"+ tn )),br);

                                  Thread.sleep(2000);
                //              driver.findElement(By.id("mainSubmitButton")).click();
                                  schk=orderprocess(driver,br);
                                
                                ///OMG
                                  //schk="Pass";
                                  if(schk.equals("Pass"))
                                  {
                                                  statusTracker(br,"Pass","Verify if remove TN order is processed successfully","Order is successfully processed","Order should be successfully processed");  
                                                  //int count1 =selenium.getXpathCount("//*[@id='TNGridRefresh']/div").intValue();
                                                  int count1 =driver.findElements(By.xpath("//html/body/div[3]/form/div/div[3]/div[2]/div/div/div[3]/div/div/div[2]/table/tbody/tr")).size();
                                                  
                                                  String elem="id=TN_"+ tn;
                                                  
                                                  if(!(isElementPresent(elem)))
                                                  {
                                                                  statusTracker(br,"Pass","Verify if TN is not displayed after order process","TN is not displayed after order process","TN is not displayed after order process");  
                                                  }
                                                  else
                                                  {
                                                                  statusTracker(br,"Fail","Verify if TN is not displayed after order process","TN is displayed after order process","TN is not displayed after order process");
                                                                  schk="Fail";
                                                  }
                                  }
                                  else
                                  {
                                                  statusTracker(br,"Fail","Verify if remove TN order is processed successfully","Order is not successfully processed","Order should be successfully processed");
                                                  /*if(isElementPresent("modalContinueButton"))
                                                  {
                                                                  focusClick(driver,driver.findElement(By.id("modalContinueButton")),br);
                                                  }*/
                                                  schk="Fail";
                                  }
                                  
                                  
                  }
                  System.out.println("Ending flowrun1");
                  return schk;
  }
  
private boolean isElementPresent(String elem) {
                // TODO Auto-generated method stub
                return false;
}

public String deleteall(WebDriver driver,String br) throws Exception
{
                String schk ="Fail";
                int chk;
                
                int count1 =driver.findElements(By.xpath("//html/body/div[3]/form/div/div[3]/div[2]/div/div/div[3]/div/div/div[3]/table/tbody/tr")).size();
                int count = count1-1;
                System.out.println("Starting deleteall");
                System.out.println("count"+count);
                String tn;
                
                if(count!=0)
                {
                                for(int i=1;i<=count;i++)
                                {
                                                Thread.sleep(5000);
                                                tn=driver.findElement(By.xpath("//html/body/div[3]/form/div/div[3]/div[2]/div/div/div[3]/div/div/div[3]/table/tbody/tr["+i+"]/td/input")).getAttribute("value");
                                               
                                                System.out.println(tn);
                                                Thread.sleep(5000);
                                                //selenium.click("//*[@id='DeleteNumber_"+ tn + "']");
                                                focusClick(driver,driver.findElement(By.id("DeleteNumber_"+ tn )),br);
                                               
                                                Thread.sleep(3000);
                                                i=0;
                                                count =driver.findElements(By.xpath("//html/body/div[3]/form/div/div[3]/div[2]/div/div/div[3]/div/div/div[3]/table/tbody/tr")).size();
                                                System.out.println("ss: "+count);
                                  
                                }
                                
                                count = driver.findElements(By.xpath("//html/body/div[3]/form/div/div[3]/div[2]/div/div/div[3]/div/div/div[3]/table/tbody/tr")).size();
                                System.out.println(count);
                                
                                boolean status = driver.findElement(By.id("drActivated")).isEnabled();
                                System.out.println(status);
                               
                                if(status)
                                {
                                	Thread.sleep(5000);
                                            focusClick(driver,driver.findElement(By.id("drActivated")),br);
                                            Thread.sleep(5000);
                                            focusClick(driver,driver.findElement(By.id("mainSubmitButton")),br);
                                }
                                
                               Thread.sleep(5000);
                                
                                if(!(status))
                                { 
                                	Thread.sleep(5000);
                                            focusClick(driver,driver.findElement(By.id("drActivated")),br);
                                            Thread.sleep(5000);
                                            focusClick(driver,driver.findElement(By.id("mainSubmitButton")),br);
                                            schk=orderprocess(driver,br);   
                                }
                                
                                Thread.sleep(3000);
                                if(driver.findElement(By.cssSelector("span.help-block.error")).isDisplayed())
                                {
                                                statusTracker(br,"Pass","Verify if error message is displayed when trying to enable feature with no TNs","Error message is displayed: " + driver.findElement(By.cssSelector("span.help-block.error")).getText(),"Error message should be displayed");
                                }
                                else
                                {
                                                statusTracker(br,"Fail","Verify if error message is displayed when trying to enable feature with no TNs","Error message is not displayed","Error message should be displayed");
                                                schk="Fail";
                                }
                                
                                
                }
                if(!(assertTrue(isElementPresent("td.phone"))))
                {
                               String ac1=randomNO(999,200);
                               String midtn1=randomNO(999,200);
                               String lastfour1= randomNO(9999,1000);
                
                                driver.findElement(By.id("txtAreaCode")).sendKeys(ac1);
                                driver.findElement(By.id("txtExchange")).sendKeys( midtn1);
                                driver.findElement(By.id("txtTelNum")).sendKeys(lastfour1);
                                Thread.sleep(1000);
                                focusClick(driver,driver.findElement(By.id("AddToPhoneNumbers")),br);
                                Thread.sleep(5000);
                                
                                focusClick(driver,driver.findElement(By.id("mainSubmitButton")),br);
                                schk=orderprocess(driver,br);
                }
                System.out.println("Ending deleteall");
return schk;
}

  
  private boolean assertTrue(boolean elementPresent) {
                // TODO Auto-generated method stub
                return false;
}

public String flowrunmaxtn(WebDriver driver,String br) throws Exception
  {
                  String schk ="Fail";
                  
                  System.out.println("Starting flowrunmaxtn");
                  String ac;
                  String midtn;
                  String lastfour;
                  String tn;
                  int count=0;
                  
                  int limit =driver.findElements(By.xpath("//html/body/div[3]/form/div/div[3]/div[2]/div/div/div[3]/div/div/div[2]/table/tbody/tr")).size();
                  System.out.println(limit);
                  while(count<30) {
                                   ac=randomNO(999,200);  
                                  midtn=randomNO(999,200);
                                  //String midtn="345";
                                  lastfour= randomNO(9999,1000);
                  
          driver.findElement(By.id("txtAreaCode")).sendKeys(ac);
          driver.findElement(By.id("txtExchange")).sendKeys( midtn);
          driver.findElement(By.id("txtTelNum")).sendKeys(lastfour);
          Thread.sleep(5000);
          focusClick(driver,driver.findElement(By.id("AddToPhoneNumbers")),br);
         

                  
                 
                  //do{
                  //}while(selenium.isElementPresent("//body/div[10]"));
                  Thread.sleep(1000);
                  
                  //System.out.println(selenium.getXpathCount("//*[@id='TNGridRefresh']/div"));
                  //count =driver.findElements(By.xpath("//html/body/div[3]/form/div/div[3]/div[2]/div/div/div[3]/div/div/div[3]/table/tbody//tr")).size();
                  count =driver.findElements(By.xpath("//*[@class = 'table-dotted']")).size();
                  System.out.println(count);
                  int chk=0;
                  tn=ac+midtn+lastfour;
                  for(int i=1;i<=count;i++)
                  {
                	  Thread.sleep(5000);
                	  String TN=driver.findElement(By.xpath("//html/body/div[3]/form/div/div[3]/div[2]/div/div/div[3]/div/div/div[3]/table/tbody/tr["+i+"]/td/input")).getAttribute("value");
                	  System.out.println(TN);
                                if(TN.equals(tn))
                                  {                       
                                                  statusTracker(br,"Pass","Verify if added TN is present in the list","Added TN is present in the list. Added TN: "+count, "Added TN should be present in the list");
                                                  System.out.println("Added TN"+tn);
                                                  chk=1;
                                                  schk="Pass";
                                  }
                  }
                  
                  if(chk==0)
                  {
                                                statusTracker(br,"Fail","Verify if added TN is present in the list","Added TN is not present in the list. Total Tns present "+count,"Added TN should be present in the list");
                                                schk="Fail";
                  }
                  int errmesg1= driver.findElements(By.cssSelector("span.help-block.error")).size();
                  if(errmesg1>0)
                  {
                                                                statusTracker(br,"Fail","Verify if 30 TNs can be added to the list","Error message is present before the 30 TNs are completed added","TN should be added to the list");
                                                                schk="Fail";
                  }
                  Thread.sleep(5000);
                  focusClick(driver,driver.findElement(By.id("mainSubmitButton")),br);
                  
           }
                
                 
                  
                 System.out.println("Printing First Error Message");
                  
                 if(schk.equals("Pass"))
                  {
                   ac=randomNO(999,200);
                   midtn=randomNO(999,200);
                                  //String midtn="345";
                   lastfour= randomNO(9999,1000);
                   driver.findElement(By.id("txtAreaCode")).sendKeys(ac);
                   driver.findElement(By.id("txtExchange")).sendKeys( midtn);
                   driver.findElement(By.id("txtTelNum")).sendKeys(lastfour);
                   Thread.sleep(5000);
                   focusClick(driver,driver.findElement(By.id("AddToPhoneNumbers")),br);
                                  tn=ac+midtn+lastfour;
                                  Thread.sleep(5000);
                                  int errmesg1= driver.findElements(By.cssSelector("span.help-block.error")).size();
                                  if(errmesg1>0)
                                  {
                                                  statusTracker(br,"Pass","Verify if error message is displayed when adding 31st TN","Error message is displayed","Error message should be displayed");
                                  }
                                  else
                                  {
                                                  statusTracker(br,"Fail","Verify if error message is displayed when adding 31st TN","Error message is not displayed","Error message should be displayed");
                                                  schk="Fail";
                                  }
                  }
                 
                 System.out.println("Printing Second Error Message");
                        
                                  if(schk.equals("Pass"))
                                  {
                                                  statusTracker(br,"Pass","Verify if adding 30 TNs is processed successfully","Order is successfully processed","Order should be successfully processed"); 
                                                  Thread.sleep(3000);
                                                  int count1 = driver.findElements(By.xpath("//html/body/div[3]/form/div/div[3]/div[2]/div/div/div[3]/div/div/div[3]/table/tbody//tr")).size();
                                                  if(count1==30)
                                                  {
                                                                  statusTracker(br,"Pass","Verify if 30 TNs are displayed after order process","30 TNs are displayed after order process","TN is displayed after order process");  
                                                  }
                                                  else
                                                  {
                                                                  statusTracker(br,"Fail","Verify if 30 TNs are displayed after order process","30 TNs are not displayed after order process","TN is displayed after order process");
                                                                  schk="Fail";
                                                  }
                                  }
                                  else
                                  {
                                                  statusTracker(br,"Fail","Verify if adding 30 TNs is processed successfully","Order is not successfully processed","Order should be successfully processed");
                                                  schk="Fail";
                                  }
                  
                  System.out.println("Ending Maximum TNcheck");
                  return schk;
  }
  
public String TNcheck(String ac, String midtn, String lastfour, String check,WebDriver driver,String br) throws Exception
{
                String schk="Fail";
                int limit =driver.findElements(By.xpath("//html/body/div[3]/form/div/div[3]/div[2]/div/div/div[3]/div/div/div[3]/table/tbody//tr")).size();
                int count;
                String tn;
                System.out.println("Starting TNcheck");
                driver.findElement(By.id("txtAreaCode")).clear();
                driver.findElement(By.id("txtAreaCode")).sendKeys(ac);
                driver.findElement(By.id("txtExchange")).clear();
                driver.findElement(By.id("txtExchange")).sendKeys(midtn);
                driver.findElement(By.id("txtTelNum")).clear();
                driver.findElement(By.id("txtTelNum")).sendKeys(lastfour);
                Thread.sleep(5000);
                focusClick(driver,driver.findElement(By.id("AddToPhoneNumbers")),br);
                orderprocess(driver,br);
                tn=ac+midtn+lastfour;

                count=driver.findElements(By.xpath("//html/body/div[3]/form/div/div[3]/div[2]/div/div/div[3]/div/div/div[3]/table/tbody/tr")).size();
                System.out.println("count "+count );
                boolean errmesg= driver.findElement(By.cssSelector("span.help-block.error")).isDisplayed();
                System.out.println("error"+errmesg);
                if(errmesg && count==limit)
                {
                                statusTracker(br,"Pass","Verify if error message is displayed when adding "+check+" TN","Error message is displayed: "+ driver.findElement(By.cssSelector("span.help-block.error")).getText(),"Error message should be displayed");
                                schk="Pass";
                }
                else
                {
                                statusTracker(br,"Fail","Verify if error message is displayed when adding "+check+" TN","Error message is not displayed","Error message should be displayed!!");
                                schk="Fail";
                }
                
                        
            /*    driver.findElement(By.xpath("//*[@class = 'card poping buttonLink']")).click();
                
               if(driver.findElements(By.xpath("//html/body/div[4]/div")).size()!=0)
                {       focusClick(driver,driver.findElement(By.cssSelector("div.modal-footer > button.btn.btn-primary")),br);                                       
                       statusTracker(br,"Pass","Verify if error message is displayed on clicking the friendly icon when adding "+check+" TN","Error message is displayed: "+ driver.findElement(By.cssSelector("div.modal-body > p")).getText(),"Error message should be displayed");
                       schk="Pass";
                }
                else
                {
                	   focusClick(driver,driver.findElement(By.cssSelector("div.modal-footer > button.btn.btn-primary")),br);
                       statusTracker(br,"Fail","Verify if error message is displayed on clicking the friendly icon when adding "+check+" TN","Error message is not displayed","Error message should be displayed");
                       schk="Fail";      
                }
               */
               System.out.println("Ending TNcheck");            
                return schk;
}

public String TNValidation(WebDriver driver,String br) throws Exception
{
                String schk ="Pass";
                schk=TNcheck("022","300","4000","first digit 0",driver,br);
                Thread.sleep(5000);
                schk=TNcheck("222","000","4000","fourth digit 0",driver,br);
                Thread.sleep(5000);
                schk=TNcheck("122","300","4000","first digit 1",driver,br);
                Thread.sleep(5000);
                schk=TNcheck("222","152","4000","fourth digit 1",driver,br);
                Thread.sleep(5000);
                schk=TNcheck("222","000","4000","fourth digit 0",driver,br);
                Thread.sleep(5000);
                schk=TNcheck("","","","blank",driver,br);
              //  Thread.sleep(5000);
              //  schk=TNcheck(ac1,midtn1,lastfour1,"existing",driver,br);
                Thread.sleep(5000);
                schk=TNcheck("99","9","99","Invalid",driver,br);
                
                driver.findElement(By.id("txtAreaCode")).clear();
                driver.findElement(By.id("txtExchange")).clear();
                driver.findElement(By.id("txtTelNum")).clear();
              
                /*String tns[]=SCAcheck();
                if(!(tns[0].equals(0)))
                {
                                String tn= randomNO(Integer.parseInt(tns[0]),1);
                                System.out.println(tn);
                                schk=TNcheck(tns[Integer.parseInt(tn)].substring(0,3),tns[Integer.parseInt(tn)].substring(3,6),tns[Integer.parseInt(tn)].substring(6),"a Selective Call Acceptance");
                }*/
                
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

public void execute(String br, WebDriver driver, String url, int loc, String name1) throws Exception {
    
    Feature_Name="DistinctiveRing";
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
                    //focusClick(driver,driver.findElement(By.linkText("Settings")),br);
                           Thread.sleep(5000);
                   // focusClick(driver,driver.findElement(By.xpath("(//a[contains(text(),'(edit)')])[6]")),br);
                     driver.findElement(By.xpath("//*[@href='/DistinctiveRing/DistinctiveRing']")).click();
                   //*[@id="ContentRefresh"]/div/div[3]/div[6]/div/a
                     boolean status = driver.findElement(By.id("drActivated")).isEnabled();  
                    System.out.println("checked for activation");

          String schk=flowrun(driver,br);
                                                
                                if(schk.equals("Pass"))
                                                schk=flowrun(driver,br);
                                                
                                                if(schk.equals("Pass"))
                                                {
                                                                status = driver.findElement(By.id("drActivated")).isEnabled();

                                                                statusTracker(br,"","Verify TN add/remove when feature is "+status,"","");
                                                                Thread.sleep(2000);
                                                                schk=flowrun1(driver,br);
                                                }
                                                
                                                /*if(schk.equals("Pass"))
                                                                schk=flowrun(driver,br);

                                                if(schk.equals("Pass"))
                                                {
                                                                status = driver.findElement(By.id("drActivated")).isEnabled();

                                                                statusTracker(br,"","Verify TN add/remove when feature is "+status,"","");
                                                                schk=flowrun1(driver,br);
                                                }*/
                                                
                                                if(schk.equals("Pass"))
                                                {
                                                                statusTracker(br,"","Verify TN Validation","","");
                                                                System.out.println("Starting TN validation");
                                                                Thread.sleep(2000);
                                                                schk=TNValidation(driver,br);
                                                }
                                                if(schk.equals("Pass"))
                                                {
                                                                statusTracker(br,"","Verify Deleting all numbers","","");
                                                                Thread.sleep(2000);
                                                                schk=deleteall(driver,br);
                                                }
                                                
                                            /*    if(schk.equals("Pass"))
                                                {
                                                                statusTracker(br,"","Verify maximum TN operations","","");
                                                                Thread.sleep(2000);
                                                                schk=flowrunmaxtn(driver,br);
                                                }*/
                                                
                                            
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

