package Voicezone;
import java.io.File;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;

public class SelectiveCallAcceptance extends CommonFunctions {
                
                String table, tns[];
    String tlimit,username,pwd;
    int tncount;                                                    
                                
    String phoneline,phoneline_ac,Acccode;
                int rank[]= new int[50];
                

                int c_sequence;
                String name_for_rank[]= new String[40];
                String price, rank_channels;

                public SelectiveCallAcceptance(String path) {
                                this.path = path;
                }
   
  public String flowrun(WebDriver driver,String br) throws Exception
  {
	                            boolean status = driver.findElement(By.id("Activated")).isSelected();
                                System.out.println("radio"+status);
                                String status1="Disabled";
                                System.out.println("status"+status);
                                System.out.println("status1"+status1);
                                String from="",to="";
                                if(status)
                                {
                                                from = "Enabled";
                                                to="Disabled";
                                                focusClick(driver,driver.findElement(By.xpath("(//input[@id='Activated'])[2]")),br);
                                }
                                else if (!(status))
                                {
                                                from = "Disabled";
                                                to="Enabled";
                                                focusClick(driver,driver.findElement(By.id("Activated")),br);
                                                Thread.sleep(3000);
                                                if(driver.findElement(By.xpath("//html/body/div[4]")).isDisplayed())
                                                {
                                                                statusTracker(br,"Pass","Verify if the 911 warning message is displayed","The 911 warning message is being displayed","The 911 warning message should be displayed");
                                                                Thread.sleep(2000);
                                                                focusClick(driver,driver.findElement(By.cssSelector(("#dialog > div.modal-footer > #scaWarningYes"))),br);
                                                               int chk=0;
                                                     		      do{
                                                     		           Thread.sleep(1000);       
                                                     		          chk++;
                                                     		          System.out.println(chk);
                                                     		                }
                                                     		      while(driver.findElement(By.cssSelector("#dialog > div.modal-footer > #scaWarningYes")).isDisplayed());
                                                            
                                                }
                                                else
                                                                statusTracker(br,"Fail","Verify if the 911 warning message is displayed","The 911 warning message is not being displayed","The 911 warning message should be displayed");
                                }
                                Thread.sleep(1000);
                                focusClick(driver,driver.findElement(By.id("mainSubmitButton")),br);
                                String schk=orderprocess(driver,br);
                  //OMG
                                //schk="Pass";
                                if(schk.equals("Fail"))
                                {
                                                statusTracker(br,"Fail","Verify order processing when switching from "+from+" to "+to,"Order processing has failed","Order should be processed successfully");
                                }
                                else
                                {
                                                statusTracker(br,"Pass","Verify order processing when switching from "+from+" to "+to,"Order processing has completed successfully","Order should be processed successfully");
                                }
                                return schk;
  }
   
  protected boolean assertTrue(Object elementPresent) {
                // TODO Auto-generated method stub
                return false;
}

private Object isElementPresent(WebElement webElement) {
                // TODO Auto-generated method stub
                return null;
}

public String flowrun1(WebDriver driver,String br) throws Exception
  {
                  String schk ="Fail";
                String ac;
                  String midtn;
                  String lastfour;
                  String tn;
                  int count;
                 int limit =driver.findElements(By.xpath("//html/body/div[3]/section/form/div[2]/div/div//tr")).size();
       
              ac=randomNO(999,200);
               midtn=randomNO(999,200);
              //String midtn="345";
              lastfour= randomNO(9999,1000);
                  
                driver.findElement(By.id("txtAreaCode")).sendKeys(ac);
               driver.findElement(By.id("txtExchange")).sendKeys(midtn);
                driver.findElement(By.id("txtTelNum")).sendKeys(lastfour);
                // System.out.println(selenium.getXpathCount("//*[@id='TNGridRefresh']/div"));
                  Thread.sleep(5000);
                  focusClick(driver,driver.findElement(By.id("AddToPhoneNumbers")),br);
                  Thread.sleep(4000);
                  tn=ac+midtn+lastfour;
                  
                
                  //System.out.println(selenium.getXpathCount("//*[@id='TNGridRefresh']/div"));
                  
                  count =driver.findElements(By.xpath("//html/body/div[3]/form/div/div[3]/div[2]/div/div/div/div/div[2]/div/div/div[5]/table/tbody/tr")).size();
                  System.out.println("tns present: " +count);
                  int chk=0;
                  for(int i=1;i<=count;i++)
                  {
                                String a=driver.findElement(By.xpath("//html/body/div[3]/form/div/div[3]/div[2]/div/div/div/div/div[2]/div/div/div[5]/table/tbody/tr["+i+"]/td/input")).getAttribute("value");                                
                                 //System.out.println(selenium.getValue("//html/body/div/section/form/div[2]/div/div["+(i+1)+"]/table/tbody/tr/td//input"));
                                  if(a.equals(tn))
                                  //if((selenium.getValue("//html/body/div/section/form/div[2]/div/div/div["+(i+1)+"]/table/tbody/tr/td//input")).equals(tn))
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
                                  schk=orderprocess(driver,br);
                                  boolean chk1=false;
                                  //schk="Pass";
                                  if(schk.equals("Pass"))
                                  {
                                                  statusTracker(br,"Pass","Verify if add TN order is processed successfully","Order is successfully processed","Order should be successfully processed");  
                                                  int count1 =driver.findElements(By.xpath("//html/body/div[3]/form/div/div[3]/div[2]/div/div/div/div/div[2]/div/div/div[5]/table/tbody/tr")).size();
                                                 
                                                  String elem="TN_"+ tn;
                                                  System.out.println("elem"+elem);
                                                  for(int cn=1;cn<=count1;cn++)
                                                  {
                                                	 String ck= driver.findElement(By.xpath("//html/body/div[3]/form/div/div[3]/div[2]/div/div/div/div/div[2]/div/div/div[5]/table/tbody/tr["+cn+"]/td/input")).getAttribute("value");
                                                	 if(ck.equals(tn))
                                                	 {
                                                		  chk1=true;
                                                	 }
                                                	 Thread.sleep(5000);
                                                  }
                                                  if(chk1)
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
                                                  schk="Fail";
                                  }
                  }                         
                                  
                  if(schk.equals("Pass"))
                  {
                	  Thread.sleep(5000);
                	  focusClick(driver,driver.findElement(By.id("DeleteNumber_"+ tn )),br);                                 
                                  Thread.sleep(5000);
                                  focusClick(driver,driver.findElement(By.id("mainSubmitButton")),br);
                                  schk=orderprocess(driver,br);
                                  boolean chk2=false;
                                ///OMG
                                  //schk="Pass";
                                  if(schk.equals("Pass"))
                                  {
                                                  statusTracker(br,"Pass","Verify if remove TN order is processed successfully","Order is successfully processed","Order should be successfully processed");  
                                                  //int count1 =selenium.getXpathCount("//*[@id='TNGridRefresh']/div").intValue();
                                                  int count1 =driver.findElements(By.xpath("//html/body/div[3]/form/div/div[3]/div[2]/div/div/div/div/div[2]/div/div/div[5]/table/tbody/tr")).size();
                                                  
                                                  String elem="TN_"+tn;
                                                  System.out.println("elem1"+elem);
                                                  for(int cn1=1;cn1<=count1;cn1++)
                                                  {
                                                	 String ck1= driver.findElement(By.xpath("//html/body/div[3]/form/div/div[3]/div[2]/div/div/div/div/div[2]/div/div/div[5]/table/tbody/tr["+cn1+"]/td/input")).getAttribute("value");
                                                	 if(ck1.equals(tn))
                                                	 {
                                                		  chk2=true;
                                                	 }
                                                  }
                                                  if(!(chk2))
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
                                                  schk="Fail";
                                  }
                                  
                                  
                  }
                  
                  return schk;
  }
  
  private Object isElementPresent(List<WebElement> findElements) {
                // TODO Auto-generated method stub
                return null;
}

private Object isElementPresent(String elem) {
                // TODO Auto-generated method stub
                return null;
}

public String deleteall(WebDriver driver,String br) throws Exception
  {
                  String schk ="Fail";
                  int chk;
                  
                int count =driver.findElements(By.xpath("//html/body/div[3]/form/div/div[3]/div[2]/div/div/div/div/div[2]/div/div/div[5]/table/tbody/tr")).size();
                  System.out.println("count"+count);
                  String tn;
                  
                  if(count!=0)
                  {
                                  for(int i=1;i<=count;i++)
                                  {
                                                 Thread.sleep(2000); 
                                                  tn=driver.findElement(By.xpath("//html/body/div[3]/form/div/div[3]/div[2]/div/div/div/div/div[2]/div/div/div[5]/table/tbody/tr["+i+"]/td/input")).getAttribute("value");
                                                  System.out.println(tn);
                                                  //selenium.click("//*[@id='DeleteNumber_"+ tn + "']");
                                                  Thread.sleep(5000);
                                                  focusClick(driver,driver.findElement(By.id("DeleteNumber_"+ tn )),br);
                                                  Thread.sleep(5000);
                                                  i=0;
                                                  count =driver.findElements(By.xpath("//html/body/div[3]/form/div/div[3]/div[2]/div/div/div/div/div[2]/div/div/div[5]/table/tbody/tr")).size();
                                                  System.out.println("ss: "+count);
                                  }
                                  System.out.println("outside the loop");
                                  boolean status = driver.findElement(By.id("Activated")).isSelected();
                                  if(status)
                                  {
                                	  focusClick(driver,driver.findElement(By.xpath("(//input[@id='Activated'])[2]")),br);                                                            
                                  }
                                  Thread.sleep(2000);
                                  focusClick(driver,driver.findElement(By.id("mainSubmitButton")),br);
                                  schk=orderprocess(driver,br);
                
                                  count =driver.findElements(By.xpath("//html/body/div[3]/form/div/div[3]/div[2]/div/div/div/div/div[2]/div/div/div[5]/table/tbody/tr")).size();
                                  System.out.println(count);
                                  
                                  status = driver.findElement(By.id("Activated")).isSelected();
                                  if(!(status))
                                  {
                                	  focusClick(driver,driver.findElement(By.id("Activated")),br);
                                       Thread.sleep(3000);
                                       focusClick(driver,driver.findElement(By.cssSelector("#dialog > div.modal-footer > #scaWarningYes")),br);
                                 
                                        chk=0;
                           		      do{
                           		           Thread.sleep(1000);       
                           		          chk++;
                           		          System.out.println(chk);
                           		                }
                           		      while(driver.findElement(By.cssSelector("#dialog > div.modal-footer > #scaWarningYes")).isDisplayed());
                                  
                                  }
                                  Thread.sleep(2000);
  
                                  focusClick(driver,driver.findElement(By.id("mainSubmitButton")),br);
                                 
                                  //schk=orderprocess();
                                  if(driver.findElement(By.cssSelector("span.help-block.error")).isDisplayed())
                                  {
                                                  statusTracker(br,"Pass","Verify if error message is displayed when trying to enable feature with no TNs","Error message is displayed: "+ driver.findElement(By.cssSelector(("span.help-block.error"))).getText(),"Error message should be displayed");
                                  }
                                  else
                                  {
                                                  statusTracker(br,"Fail","Verify if error message is displayed when trying to enable feature with no TNs","Error message is not displayed","Error message should be displayed");
                                                  schk="Fail";
                                  }
                                  
                                  
                  }
                  if(!((assertTrue(isElementPresent(By.cssSelector("td.phone"))))))
                  {

                        String ac1 = randomNO(999,200);
                        String midtn1 = randomNO(999,200);
                        //String midtn="345";
                        String lastfour1 = randomNO(9999,1000);
                        System.out.println("ac1"+ac1);
                        System.out.println("midtn1"+midtn1);
                        System.out.println("lastfour1"+lastfour1);
                        driver.findElement(By.id("txtAreaCode")).sendKeys(ac1);
                        driver.findElement(By.id("txtExchange")).sendKeys(midtn1);
                        driver.findElement(By.id("txtTelNum")).sendKeys(lastfour1);
                        Thread.sleep(3000);
                        focusClick(driver,driver.findElement(By.id("AddToPhoneNumbers")),br);
                        Thread.sleep(3000);
                        focusClick(driver,driver.findElement(By.id("mainSubmitButton")),br);
                        schk=orderprocess(driver,br);
                  }
  return schk;
}
  
  protected Object isElementPresent(By xpath) {
                // TODO Auto-generated method stub
                return null;
}

public String flowrunmaxtn(WebDriver driver,String br) throws Exception
  {
                  String schk ="Fail";
                                    
                  String ac0;
                  String midtn0;
                  String lastfour0;
                  String tn;
                  int count;
//              TN=TN+lastfour;
                   count =driver.findElements(By.xpath("//html/body/div[3]/form/div/div[3]/div[2]/div/div/div/div/div[2]/div/div/div[5]/table/tbody/tr")).size();
                  if (count==30)
                	  schk ="Pass";
                  
                  while(count<30){
                                  ac0=randomNO(999,200);
                                  if(ac0.equals("900") || ac0.equals("976"))
                                       ac0=randomNO(999,200);  
                                  
                                  midtn0=randomNO(999,200);
                                  //String midtn="345";
                                  lastfour0= randomNO(9999,1000);
                      driver.findElement(By.id("txtAreaCode")).clear();
                      driver.findElement(By.id("txtAreaCode")).sendKeys(ac0);
                      driver.findElement(By.id("txtExchange")).clear();
                      driver.findElement(By.id("txtExchange")).sendKeys(midtn0);
                      driver.findElement(By.id("txtTelNum")).clear();
                      driver.findElement(By.id("txtTelNum")).sendKeys(lastfour0);
                      Thread.sleep(5000);
                      focusClick(driver,driver.findElement(By.id("AddToPhoneNumbers")),br);
                      //driver.manage().timeouts().implicitlyWait(3, TimeUnit.MINUTES);
                      tn=ac0+midtn0+lastfour0;
                      System.out.println("tn"+tn);
                      Thread.sleep(4000);
                    count =driver.findElements(By.xpath("//html/body/div[3]/form/div/div[3]/div[2]/div/div/div/div/div[2]/div/div/div[5]/table/tbody/tr")).size();
                    System.out.println("count"+count);
                    int chk=0;
                    for(int i=1;i<=count;i++)
                    {
                      Thread.sleep(5000);
                	  String tn1=driver.findElement(By.xpath("//html/body/div[3]/form/div/div[3]/div[2]/div/div/div/div/div[2]/div/div/div[5]/table/tbody/tr["+i+"]/td/input")).getAttribute("value");
                	  System.out.println("tn1"+tn1);
                	  System.out.println("tn"+tn);
                	  Thread.sleep(5000);
                                if(tn1.equals(tn))
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
                    int errmesg1= driver.findElements(By.cssSelector("span.help-block.error")).size();
                    if(errmesg1>0)
                    {
                                                 statusTracker(br,"Fail","Verify if 30 TNs can be added to the list","Error message is present before the 30 TNs are completed added","TN should be added to the list");
                                                 schk="Fail";
                    }
                  }
                                  
                  if(schk.equals("Pass"))
                  {
                                  ac0=randomNO(999,200);
                                  midtn0=randomNO(999,200);
                                  //String midtn="345";
                                  lastfour0= randomNO(9999,1000);
                                  driver.findElement(By.id("txtAreaCode")).sendKeys(ac0);
                                  driver.findElement(By.id("txtExchange")).sendKeys(midtn0);
                                  driver.findElement(By.id("txtTelNum")).sendKeys(lastfour0);
                                  //System.out.println(selenium.getXpathCount("//*[@id='TNGridRefresh']/div"));
                                  Thread.sleep(5000);
                                  focusClick(driver,driver.findElement(By.id("AddToPhoneNumbers")),br);
                                  
                                  tn=ac0+midtn0+lastfour0;

                                  Thread.sleep(2000);
                                  int errmesg2=driver.findElements(By.cssSelector("span.help-block.error")).size();                                  
                                  if(errmesg2>0)
                                  {
                                                  statusTracker(br,"Pass","Verify if error message is displayed when adding 31st TN","Error message is displayed","Error message should be displayed");
                                  }
                                  else
                                  {
                                                  statusTracker(br,"Fail","Verify if error message is displayed when adding 31st TN","Error message is not displayed","Error message should be displayed");
                                                  schk="Fail";
                                  }
                  }
                  
                  if(schk.equals("Pass"))
                  { 
                	  Thread.sleep(5000);
                	  focusClick(driver,driver.findElement(By.id("mainSubmitButton")),br);
                                  schk=orderprocess(driver,br);
                                
                                  ///OMG
                                  //schk="Pass";
                                  if(schk.equals("Pass"))
                                  {
                                                  statusTracker(br,"Pass","Verify if adding 30 TNs is processed successfully","Order is successfully processed","Order should be successfully processed");  
                                                  int count1 =driver.findElements(By.xpath("//html/body/div[3]/form/div/div[3]/div[2]/div/div/div/div/div/div/div/div[5]/table/tbody//tr")).size();
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
                  }
                  
                  return schk;
  }
  
  public String TNcheck(String ac, String midtn, String lastfour, String check ,WebDriver driver,String br) throws Exception
  {
                  String schk="Fail";
                  int limit =driver.findElements(By.xpath("//html/body/div[3]/form/div/div[3]/div[2]/div/div/div/div/div/div/div/div[5]/table/tbody//tr")).size();
                  int count;
                  String tn;
                  driver.findElement(By.id("txtAreaCode")).clear();
                  driver.findElement(By.id("txtAreaCode")).sendKeys(ac);
                  driver.findElement(By.id("txtExchange")).clear();
                  driver.findElement(By.id("txtExchange")).sendKeys(midtn);
                  driver.findElement(By.id("txtTelNum")).clear();
                  driver.findElement(By.id("txtTelNum")).sendKeys(lastfour);
                 
                  focusClick(driver,driver.findElement(By.id("AddToPhoneNumbers")),br);
                  Thread.sleep(5000);
                  tn=ac+midtn+lastfour;
                
                  count=driver.findElements(By.xpath("//html/body/div[3]/form/div/div[3]/div[2]/div/div/div/div/div/div/div/div[5]/table/tbody//tr")).size();
                  Thread.sleep(3000);
                  boolean errmesg= driver.findElement(By.cssSelector("span.help-block.error")).isDisplayed();
                  System.out.println("error"+errmesg);
                  
                  if(errmesg && count==limit)
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
  
  public String TNValidation(WebDriver driver,String br) throws Exception
  {
                  String schk ="Pass";
                  String ac1 = null;
                  String midtn1 = null;
                  String lastfour1=null;
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
                 // schk=TNcheck("","","","blank",driver,br);
                 // Thread.sleep(5000);
                //  schk=TNcheck(ac1,midtn1,lastfour1,"existing",driver,br);
                //  Thread.sleep(5000);
                  schk=TNcheck("99","9","99","Invalid",driver,br);  
                  Thread.sleep(5000);
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
      
      Feature_Name="CFNA";
      int tlim=3;
      
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
            		      focusClick(driver,driver.findElement(By.xpath("(//a[contains(text(),'(edit)')])[1]")),br);
                    	 Thread.sleep(5000);
                    	  focusClick(driver,driver.findElement(By.xpath("//div[@id='ContentRefresh']/ul/li[4]/a")),br);
                    	  Thread.sleep(5000);
                    
                     boolean status = driver.findElement(By.id("Activated")).isSelected();
                     System.out.println("radio"+status);
                     String status1="off";
                     if(status)
                        status1="on";
                                                
                      String schk="Pass"; 
                                                
                      schk=deleteall(driver,br);
                               System.out.println("delete all done");                 
                    //  schk=flowrun(driver,br);
                                                
                      if(schk.equals("Pass"))
                          schk=flowrun(driver,br);
                                                
                     if(schk.equals("Pass"))
                         {
                            status = driver.findElement(By.id("Activated")).isSelected();   
                            Thread.sleep(5000);
                            statusTracker(br,"","Verify TN add/remove when feature is "+status,"","");
                            schk=flowrun1(driver,br);
                           
                              
                           }
                          
                  /*     if(schk.equals("Pass"))
                            schk=flowrun(driver,br);

                                                
                        if(schk.equals("Pass"))
                            {
                               status = driver.findElement(By.id("Activated")).isSelected();                               
                                   statusTracker(br,"","Verify TN add/remove when feature is "+status,"","");
                                    schk=flowrun1(driver,br);      
                             }*/
                                                
                             if(schk.equals("Pass"))
                              {
                                statusTracker(br,"","Verify TN Validation","","");
                                 schk=TNValidation(driver,br);
                               }
                                                
                             /*  if(schk.equals("Pass"))
                                 {
                                   statusTracker(br,"","Verify maximum TN operations","","");
                                    schk=flowrunmaxtn(driver,br);
                                  }*/
                               
                               int count2=driver.findElements(By.xpath("//html/body/div[3]/form/div/div[3]/div[2]/div/div/div/div/div[2]/div/div/div[5]/table/tbody/tr")).size();
                               if(count2==0)
                               {	
                               String ac2=randomNO(999,200);
                               String midtn2=randomNO(999,200);
                               String lastfour2=randomNO(999,200);
                               driver.findElement(By.id("txtAreaCode")).clear();
                               driver.findElement(By.id("txtAreaCode")).sendKeys(ac2);
                               driver.findElement(By.id("txtExchange")).clear();
                               driver.findElement(By.id("txtExchange")).sendKeys(midtn2);
                               driver.findElement(By.id("txtTelNum")).clear();
                               driver.findElement(By.id("txtTelNum")).sendKeys(lastfour2);
                               Thread.sleep(1000);
                               focusClick(driver,driver.findElement(By.id("AddToPhoneNumbers")),br);
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
