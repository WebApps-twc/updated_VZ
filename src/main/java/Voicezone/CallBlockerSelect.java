package Voicezone;

import java.io.File;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;



import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;

public class CallBlockerSelect extends CommonFunctions {
                
                String table, tns[];
    String tlimit,username,pwd;
    int tncount;                                                    
                                
    String phoneline,phoneline_ac,Acccode;
                int rank[]= new int[50];
                

                int c_sequence;
                String name_for_rank[]= new String[40];
                String price, rank_channels;

public CallBlockerSelect(String path) 
{
	this.path = path;
}
  public String flowrun(WebDriver driver,String br) throws Exception
  {
	  boolean status = driver.findElement(By.id("callBlockerOn")).isSelected();
      System.out.println("radio"+status);
      String status1="Disabled";
      System.out.println("status"+status);
      System.out.println("status1"+status1);
      String from="",to="";
      if(status)
      {
                      from = "Enabled";
                      to="Disabled";
                      Thread.sleep(5000);
                      focusClick(driver,driver.findElement(By.id("callBlockerOff")),br);
      }
      else if (!(status))
      {
         from = "Disabled";
         to="Enabled";
         Thread.sleep(5000);
         focusClick(driver,driver.findElement(By.id("callBlockerOn")),br);
      }
      Thread.sleep(5000);
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

private Object isElementPresent(WebElement findElement) {
                // TODO Auto-generated method stub
                return null;
}

public String flowrun1(WebDriver driver,String br) throws Exception
  {
    System.out.println("Flow run1 Started");              
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
                  
                  tn=ac+midtn+lastfour;

                  Thread.sleep(5000);

                  count =driver.findElements(By.xpath("//table[@id='PhoneNumberGridTable']/tbody/tr")).size();
                  System.out.println("tns present: " +count);
                  int chk=0;
                  for(int i=1;i<=count;i++)
                  {
                	  Thread.sleep(8000);
                	  String a=driver.findElement(By.xpath("//table[@id='PhoneNumberGridTable']/tbody/tr["+i+"]/td/input")).getAttribute("value");                     
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
                	  Thread.sleep(5000);
                	  focusClick(driver,driver.findElement(By.id("mainSubmitButton")),br);
                                  schk=orderprocess(driver,br);
                                  boolean chk1=false;
                                  //schk="Pass";
                                  if(schk.equals("Pass"))
                                  {
                                                  statusTracker(br,"Pass","Verify if add TN order is processed successfully","Order is successfully processed","Order should be successfully processed");  
                                                  int count1 =driver.findElements(By.xpath("//table[@id='PhoneNumberGridTable']/tbody/tr")).size();
                                                 String elem="id=TN_"+ tn;
                                                 for(int cn=1;cn<=count1;cn++)
                                                 {
                                               	 String ck= driver.findElement(By.xpath("//table[@id='PhoneNumberGridTable']/tbody/tr["+cn+"]/td/input")).getAttribute("value");
                                               	 if(ck.equals(tn))
                                               	 {
                                               		  chk1=true;
                                               	 }
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
                  driver.navigate().refresh();
                  if(schk.equals("Pass"))
                  {  
                	  Thread.sleep(5000);
                	  focusClick(driver,driver.findElement(By.id("DeleteNumber_"+ tn )),br);

                                  Thread.sleep(5000);
                                  focusClick(driver,driver.findElement(By.id("mainSubmitButton")),br);
                                  schk=orderprocess(driver,br);
                                  boolean chk2=false;
                               
                                  if(schk.equals("Pass"))
                                  {
                                                  statusTracker(br,"Pass","Verify if remove TN order is processed successfully","Order is successfully processed","Order should be successfully processed");  
                                                  //int count1 =selenium.getXpathCount("//*[@id='TNGridRefresh']/div").intValue();
                                                  int count1 =driver.findElements(By.xpath("//table[@id='PhoneNumberGridTable']/tbody/tr")).size();
                                                  
                                                  String elem="id=TN_"+ tn;
                                                  for(int cn1=1;cn1<=count1;cn1++)
                                                  {
                                                	 String ck1= driver.findElement(By.xpath("//table[@id='PhoneNumberGridTable']/tbody/tr["+cn1+"]/td/input")).getAttribute("value");
                                                	 if(ck1.equals(tn))
                                                	 {
                                                		  chk2=true;
                                                	 }
                                                  }
                                                  if(!(chk2))
                                                  {
                                                                  statusTracker(br,"Pass","Verify if TN is not displayed after Deleting","TN is not displayed after Deleting ","TN is not displayed after Deleting");  
                                                  }
                                                  else
                                                  {
                                                                  statusTracker(br,"Fail","Verify if TN is not displayed after Deleting","TN is displayed after Deleting ","TN is not displayed after Deleting");
                                                                  schk="Fail";
                                                  }
                                  }
                                  else
                                  {
                                                  statusTracker(br,"Fail","Verify if remove TN order is processed successfully","Order is not successfully processed","Order should be successfully processed");
                                                  schk="Fail";
                                  }
                                  
                                  
                  }
                  System.out.println("Flow run1 Complete");  
                  
                  return schk;
  }
  
  private Object isElementPresent(String elem) {
                // TODO Auto-generated method stub
                return null;
}

public String deleteall(WebDriver driver,String br)  throws Exception
  {
                  String schk ="Fail";
                  int chk;
                  
                  int count =driver.findElements(By.xpath("//table[@id='PhoneNumberGridTable']/tbody/tr")).size();
                  System.out.println("count"+count);
                  String tn;
                  
                  if(count!=0)
                  {
                                  for(int i=1;i<=count;i++)
                                  {
                                       Thread.sleep(2000);           
                                	  tn=driver.findElement(By.xpath("//table[@id='PhoneNumberGridTable']/tbody/tr["+i+"]/td/input")).getAttribute("value");
                                      System.out.println(tn);
                                      Thread.sleep(5000);
                                      //selenium.click("//*[@id='DeleteNumber_"+ tn + "']");
                                      focusClick(driver,driver.findElement(By.id("DeleteNumber_"+ tn )),br);
                                      Thread.sleep(2000);
                                      i=0;
                                      count =driver.findElements(By.xpath("//table[@id='PhoneNumberGridTable']/tbody/tr")).size();
                                      System.out.println("ss: "+count);
                                  }
                                  
                                  count =driver.findElements(By.xpath("//table[@id='PhoneNumberGridTable']/tbody/tr")).size();
                                  System.out.println(count);
                                  
                                  boolean status = driver.findElement(By.id("callBlockerOn")).isSelected();
                                  if(!(status))
                                  {
                                	  Thread.sleep(5000);
                                	  focusClick(driver,driver.findElement(By.id("callBlockerOn")),br);
                                  }
                                  Thread.sleep(5000);
                                  focusClick(driver,driver.findElement(By.id("mainSubmitButton")),br);
                                  Thread.sleep(5000);
                                  //schk=orderprocess("span.help-block.error");
                                 
                                  if(driver.findElement(By.cssSelector("span.help-block.error")).isDisplayed())
                                  {
                                   statusTracker(br,"Pass","Verify if error message is displayed when trying to enable feature with no TNs","Error message is displayed: " +driver.findElement(By.cssSelector("span.help-block.error")).getText(),"Error message should be displayed");
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
                                  String lastfour1 = randomNO(9999,1000);
                  
                                  driver.findElement(By.id("txtAreaCode")).sendKeys(ac1);
                                  driver.findElement(By.id("txtExchange")).sendKeys(midtn1);
                                  driver.findElement(By.id("txtTelNum")).sendKeys(lastfour1);
                                  Thread.sleep(2000);
                                  focusClick(driver,driver.findElement(By.id("AddToPhoneNumbers")),br);

                                  Thread.sleep(5000);
                                  
                                  focusClick(driver,driver.findElement(By.id("mainSubmitButton")),br);
                                  schk=orderprocess(driver,br);
                  }
  return schk;
}
  
  protected Object isElementPresent(By cssSelector) {
                // TODO Auto-generated method stub
                return null;
}

public String flowrunmaxtn(WebDriver driver,String br) throws Exception
  {
                 String schk ="Fail";
                 String ac;
                  String midtn;
                  String lastfour;
                  String tn;
                  int count;                  
//              TN=TN+lastfour;
                  count =driver.findElements(By.xpath("//html/body/div[3]/form/div/div[3]/div[2]/div/div/div/div/div/div/div/div[5]/table/tbody//tr")).size();
                  if (count==30)
                	  schk ="Pass";
                	  
                  while(count<30){
                                  ac=randomNO(999,200);
                                  if(ac.equals("900") || ac.equals("976"))
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

                  focusClick(driver,driver.findElement(By.id("AddToPhoneNumbers")),br);
                 // driver.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);
                  tn=ac+midtn+lastfour;

                  Thread.sleep(10000);

                  count =driver.findElements(By.xpath("//table[@id='PhoneNumberGridTable']/tbody/tr")).size();
                  int chk=0;
                  for(int i=1;i<=count;i++)
                  {
                	  Thread.sleep(5000);
                	  String tn1=driver.findElement(By.xpath("//table[@id='PhoneNumberGridTable']/tbody/tr["+i+"]/td/input")).getAttribute("value");
                	  Thread.sleep(5000);
                                if(tn1.equals(tn))
                                  {
                                                  statusTracker(br,"Pass","Verify if added TN is present in the list","Added TN is present in the list. Added TN: "+count,"Added TN should be present in the list");
                                                  chk=1;
                                                  schk="Pass";
                                  }
                  }
                  Thread.sleep(5000);
            	  focusClick(driver,driver.findElement(By.id("mainSubmitButton")),br);
            	  driver.navigate().refresh();
            	  Thread.sleep(5000);
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
                                  ac=randomNO(999,200);
                                  midtn=randomNO(999,200);
                                  //String midtn="345";
                                  lastfour= randomNO(9999,1000);
                                  driver.findElement(By.id("txtAreaCode")).sendKeys(ac);
                                  driver.findElement(By.id("txtExchange")).sendKeys(midtn);
                                  driver.findElement(By.id("txtTelNum")).sendKeys(lastfour);
                                  //System.out.println(selenium.getXpathCount("//*[@id='TNGridRefresh']/div"));
                                  Thread.sleep(5000);
                                  focusClick(driver,driver.findElement(By.id("AddToPhoneNumbers")),br);
                                  Thread.sleep(2000);
                                  tn=ac+midtn+lastfour;
                                  //do{
                                  //}while(selenium.isElementPresent("//body/div[10]"));
                                  
                                  
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
                	  System.out.println("submit");
                	  focusClick(driver,driver.findElement(By.id("mainSubmitButton")),br);
                                  schk=orderprocess(driver,br);
                                 
                                  System.out.println("after submit");
                                  if(schk.equals("Pass"))
                                  {
                                                  statusTracker(br,"Pass","Verify if adding 30 TNs is processed successfully","Order is successfully processed","Order should be successfully processed");  
                                                  int count1 = driver.findElements(By.xpath("//table[@id='PhoneNumberGridTable']/tbody/tr")).size();
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
  
  public String TNcheck(String ac, String midtn, String lastfour, String check,WebDriver driver,String br) throws Exception
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
        Thread.sleep(3000);
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
      schk=TNcheck("","","","blank",driver,br);
      Thread.sleep(5000);
   //   schk=TNcheck(ac1,midtn1,lastfour1,"existing",driver,br);
   //   Thread.sleep(5000);
      schk=TNcheck("99","9","99","Invalid",driver,br);  
      Thread.sleep(5000);
      schk="Pass";
      return schk;
  }
  
  public String[] SCAcheck(WebDriver driver,String br) throws Exception
  {
	  focusClick(driver,driver.findElement(By.id("sitemaptoggle")),br);
                  do
                {Thread.sleep(1000);
                
                }while(!(assertTrue(isElementPresent("sitemapdev"))));
                  
                 
                  focusClick(driver,driver.findElement(By.linkText("Selective Call Acceptance")),br);
                                
                                
                                int count =driver.findElements(By.xpath("//*[@id='TNGridRefresh']/div")).size();
                                  String tns[]=new String[31];
                                  if(count==0)
                                  {
                                                  tns[0]=Integer.toString(0);;
                                  }
                                  else
                                  {
                                                  tns[0]=Integer.toString(count);
                                                  for(int i=1;i<=count;i++)
                                                  {
                                                                  
                                                                  System.out.println(driver.findElement(By.xpath("//html/body/div/section/form/div[2]/div/div["+i+"]/table/tbody/tr/td//input")));
                                                                  tns[i]=driver.findElement(By.xpath("//html/body/div/section/form/div[2]/div/div["+i+"]/table/tbody/tr/td//input")).getAttribute("value");
                                                                  //if((selenium.getValue("//html/body/div/section/form/div[2]/div/div["+i+"]/table/tbody/tr/td//input")).equals(tn))
                                                                  //{
                                                                                  //statusTracker("Pass","Verify if added TN is present in the list","Added TN is present in the list. Added TN: "+count,"Added TN should be present in the list");
                                                                                  //chk=1;
                                                                                  //schk="Pass";
                                                                  //}
                                                  }
                                  }
                                  focusClick(driver,driver.findElement(By.id("sitemaptoggle")),br);
                                  do
                                {Thread.sleep(1000);
                                
                                }while(!(assertTrue(isElementPresent("sitemapdev"))));
                                  
                                  //selenium.getText("id=sitemapdev")
                                  
                                                //Thread.sleep(3000);
                                  //selenium.start("captureNetworkTraffic=true");
                                  //selenium.click("link=Distinctive Ring");
                                  focusClick(driver,driver.findElement(By.linkText("Selective Call Blocking")),br);
                                                
                                  return tns;
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
		//  wb.close();

  driver.manage().timeouts().implicitlyWait(tlim,TimeUnit.SECONDS);
  logger.info("qtest1");
  try {
	  if(first==0)
	  {
		  login(driver,username,pwd);
	  }
	  else {
		  focusClick(driver,driver.findElement(By.id("settings-summary")),br);  
	  }
	  logger.info("a");
     Thread.sleep(5000);
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
              boolean status =driver.findElement(By.id("callBlockerOn")).isSelected();
                                                    
                    String status1="off";
                    if(status)
                         status1="on";
                    
                    String schk="Pass"; 
                    
                    schk=deleteall(driver,br);
                    Thread.sleep(5000);
                    
                    schk=flowrun(driver,br);
                    Thread.sleep(5000);
                    
                   if(schk.equals("Pass"))
                        schk=flowrun(driver,br);
                    
                    if(schk.equals("Pass"))
                    {
			            status = driver.findElement(By.id("callBlockerOn")).isSelected();
			            statusTracker(br,"","Verify TN add/remove when feature is "+status,"","");
			            schk=flowrun1(driver,br);
                    }
                    
                    if(schk.equals("Pass"))
                     schk=flowrun(driver,br);

                    
                    if(schk.equals("Pass"))
                    {
	                    status =driver.findElement(By.id("callBlockerOn")).isSelected();
	                    statusTracker(br,"","Verify TN add/remove when feature is "+status,"","");
	                    schk=flowrun1(driver,br);
                    }
                    
                    if(schk.equals("Pass"))
                    {
                        statusTracker(br,"","Verify TN Validation","","");
                        schk=TNValidation(driver,br);
                    }
                    
                   /* if(schk.equals("Pass"))
                    {
	                    statusTracker(br,"","Verify maximum TN operations","","");
	                    schk=flowrunmaxtn(driver,br);
                    }
                    */
                    int count2=driver.findElements(By.xpath("//html/body/div[3]/form/div/div[3]/div[2]/div/div/div/div/div/div/div/div[5]/table/tbody//tr")).size();
                    logger.info("count2"+count2);
                    if(count2<1)
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
                    Thread.sleep(5000);
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
