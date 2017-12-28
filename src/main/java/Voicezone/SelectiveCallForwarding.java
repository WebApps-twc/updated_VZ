package Voicezone;

import java.io.File;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;

import org.openqa.selenium.JavascriptExecutor;

public class SelectiveCallForwarding extends CommonFunctions {
                
                String table, tns[];
    String tlimit,username,pwd;
    int tncount;                                                    
                                
    String phoneline,phoneline_ac,Acccode;
                int rank[]= new int[50];
                

                int c_sequence;
                String name_for_rank[]= new String[40],slftn,phoneline_midtn,phoneline_lastfour,ac1,midtn1,lastfour1;
                String price, rank_channels;

                public SelectiveCallForwarding(String path) {
                                this.path = path;
                }

  public String flowrun(WebDriver driver,String br) throws Exception
  {
	  	focusClick(driver,driver.findElement(By.id("SCFStatusdrop")),br);//Dropdown
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
		String j=randomNO(9,2);
		int k=Integer.parseInt(j);
		focusClick(driver,driver.findElement(By.id("ddlSCFnum")),br);
		Thread.sleep(2000);
		focusClick(driver,driver.findElement(By.linkText(text[k])),br);
		Thread.sleep(2000);
	
	Thread.sleep(2000);
	focusClick(driver,driver.findElement(By.id("mainSubmitButton")),br);	
	String schk=orderprocess(driver,br);
	
	if(schk.equals("Fail"))
	{
		statusTracker(br,"Fail","Verify order processing when switching from Alternate TN to dropdown","Order processing has failed","Order should be processed successfully");	
	}
	else
	{
		statusTracker(br,"Pass","Verify order processing when switching from Alternate TN to dropdown","Order processing has processed successfully","Order should be processed successfully");
		Thread.sleep(1000);
		String acchk=driver.findElement(By.id("txtAreaCode2")).getAttribute("value");
		String midtnchk=driver.findElement(By.id("txtExchange2")).getAttribute("value");
		String lastfourchk=driver.findElement(By.id("txtTelNum2")).getAttribute("value");
		
		String tn = acchk+midtnchk+lastfourchk;
		tn="("+tn.substring(0,3)+") "+tn.substring(3,6)+"-"+tn.substring(6);
		text[k]=text[k].substring(0, 14);
		System.out.println(text[k]+" SSS "+tn);
		if(tn.equals(text[k]))
		{
			statusTracker(br,"Pass","Verify if the TN selected from the dropdown is reflected in the Alternate TN field","After order processing, the TN is reflected in the Alternate TN field","After order processing, the TN should be reflected in the Alternate TN field");
			schk="Pass";
		}
		else
			statusTracker(br,"Fail","Verify if the TN selected from the dropdown is reflected in the Alternate TN field","After order processing, the TN is not reflected in the Alternate TN field","After order processing, the TN should be reflected in the Alternate TN field");
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
	  
//	  TN=TN+lastfour;	  	  
	  //int limit =driver.findElements(By.xpath("//html/body/div[3]/form/div/div[3]/div[2]/div/div/div/div/div[4]/div/div[4]/table/tbody//tr")).size();		
	  	  
		  ac=randomNO(999,200);
		  midtn=randomNO(999,200);
		  //String midtn="345";
		  lastfour= randomNO(9999,1000);
		  driver.findElement(By.id("txtAreaCode")).sendKeys(ac);
		  driver.findElement(By.id("txtExchange")).sendKeys(midtn);
		  driver.findElement(By.id("txtTelNum")).sendKeys(lastfour);
	 // System.out.println(selenium.getXpathCount("//*[@id='TNGridRefresh']/div"));
	  Thread.sleep(1000);
	  focusClick(driver,driver.findElement(By.id("AddToPhoneNumbers")),br);
	  
	  tn=ac+midtn+lastfour;
	  //do{
	  //}while(selenium.isElementPresent("//body/div[10]"));
	  Thread.sleep(5000);
	  
	  count =driver.findElements(By.xpath("//html/body/div[3]/form/div/div[3]/div[2]/div/div/div/div/div[4]/div/div[2]/div[3]/table/tbody/tr")).size();		
 	
	  int chk=0;
	  for(int i=1;i<=count;i++)
	  {
		  
		  //System.out.println(selenium.getValue("//html/body/div/section/form/div[2]/div/div["+(i+1)+"]/table/tbody/tr/td//input"));
		  //"//html/body/div[3]/form/div/div[3]/div[2]/div/div/div[3]/div/div/div[3]/table/tbody/tr["+i+"]/td/input"
		  if((driver.findElement(By.xpath("//html/body/div[3]/form/div/div[3]/div[2]/div/div/div/div/div[4]/div/div[2]/div[3]/table/tbody/tr["+i+"]/td/input")).getText()).equals(tn));		
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
		  Thread.sleep(5000);
		  ///OMG
		  //schk="Pass";
		  if(schk.equals("Pass"))
		  {
			  statusTracker(br,"Pass","Verify if add TN order is processed successfully","Order is successfully processed","Order should be successfully processed");  
			  int count1 =driver.findElements(By.xpath("//html/body/div[3]/form/div/div[3]/div[2]/div/div/div/div/div[4]/div/div[2]/div[3]/table/tbody/tr")).size();		
			  
			  String elem="DeleteNumber_"+ tn;
			  System.out.println("elem"+elem);
			  if(driver.findElement(By.id(elem)).isDisplayed())
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
	  //OMG
	  //schk="Pass";
	  
		  
		  
	  if(schk.equals("Pass"))
	  {
		  focusClick(driver,driver.findElement(By.id("DeleteNumber_"+ tn )),br);
		  
		  Thread.sleep(3000);
		  focusClick(driver,driver.findElement(By.id("mainSubmitButton")),br);
		  schk=orderprocess(driver,br);
		
		///OMG
		  //schk="Pass";
		  if(schk.equals("Pass"))
		  {
			  statusTracker(br,"Pass","Verify if remove TN order is processed successfully","Order is successfully processed","Order should be successfully processed");  
			  //int count1 =selenium.getXpathCount("//*[@id='TNGridRefresh']/div").intValue();
			  int count1 =driver.findElements(By.xpath("//html/body/div[3]/form/div/div[3]/div[2]/div/div/div/div/div[4]/div/div[2]/div[3]/table/tbody/tr")).size();		
			  
			  String elem="TN_"+ tn;
			  
			  if(!(assertTrue(isElementPresent(By.cssSelector(elem)))))
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
			  
		  }
		  
		  
	  }
	  
	  return schk;
  }
  
  public String deleteall(WebDriver driver,String br) throws Exception
  {
	  String schk ="Fail";
	  int chk;
	  
	  int count =driver.findElements(By.xpath("//html/body/div[3]/form/div/div[3]/div[2]/div/div/div/div/div[4]/div/div[2]/div[3]/table/tbody/tr")).size();		
	  System.out.println("count"+count);
	  String tn;
	  	  
	  Thread.sleep(2000);
	  
	  boolean chk1=assertTrue(isElementPresent(By.cssSelector("td.phone.carddiv")));
	  
	 /* System.out.println("chk1"+chk1);
	  String aval="";
	  do{
		  try{
			  
			   aval=driver.findElement(By.id("td.phone")).getText();
		  System.out.println("aaaaaaa0.5");
		  }
		  catch(Exception e)
		  {
			  System.out.println("Exception caught");
			  aval="";
		  }
	  }while(aval.equals(""));*/
	  
	  if(count!=0)
	  {
		  for(int i=1;i<=count;i++)
		  {
			  
			  //String s= selenium.getText("//html/body/div[3]/form/div/div[3]/div[2]/div/div/div[3]/div/div/div[2]/table/tbody/tr[1]/td/label");
			  //s=s.replaceAll(" ","");
			  //s=s.replaceAll("\\(","");
			  //s=s.replaceAll("\\)","");
			  //s=s.replaceAll("-","");
//			  System.out.println(selenium.getValue("//html/body/div/section/form/div[2]/div/div["+i+"]/table/tbody/tr/td//input"));
			  //tn=s;
			  tn=driver.findElement(By.xpath("//html/body/div[3]/form/div/div[3]/div[2]/div/div/div/div/div[4]/div/div[2]/div[3]/table/tbody/tr["+i+"]/td/input")).getAttribute("value");
			  
			  System.out.println("tn"+tn);
			  //selenium.click("//*[@id='DeleteNumber_"+ tn + "']");
			  focusClick(driver,driver.findElement(By.id("DeleteNumber_"+ tn)),br);
			  
			  //do{
			  //}while(selenium.isElementPresent("//body/div[10]"));
			  Thread.sleep(1000);			  
			  count =driver.findElements(By.xpath("//html/body/div[3]/form/div/div[3]/div[2]/div/div/div/div/div[4]/div/div[4]/table/tbody//tr")).size();		
			  System.out.println("ss: "+count);
			  //count =selenium.getXpathCount("//*[@id='TNGridRefresh']/div").intValue();
		  
		  }
		  
		  String status= driver.findElement(By.id("SCFActivated")).getAttribute("class");
		  System.out.println("class is : "+status);

//		  String status = selenium.getValue("id=Activated");
		  if(!(status.equals("btn active")))
		  {
			  System.out.println("class is : off");
			  focusClick(driver,driver.findElement(By.id("SCFActivatedOff")),br);
			  
		  }
		  focusClick(driver,driver.findElement(By.id("mainSubmitButton")),br);
		  schk=orderprocess(driver,br);
		  if(schk.equals("Fail"))
		  {
			  statusTracker(br,"Fail","Verify if order processing is being done successfully","Order processing was not successfully completed","Order processing should be successfully completed");
		  }
	  }
	  else 
		  schk="Pass";
	  
	  System.out.println("outside");
	  
	  if(!(assertTrue(isElementPresent(By.cssSelector("td.phone")))) && schk.equals("Pass"))
	  {
		  //selenium.type("id=txtAreaCode", "999");
		  //selenium.type("id=txtExchange", "999");
		  //selenium.type("id=txtTelNum", "9999");
		  //System.out.println(selenium.getXpathCount("//*[@id='TNGridRefresh']/div"));
		  count =driver.findElements(By.xpath("//html/body/div[3]/form/div/div[3]/div[2]/div/div/div/div/div[4]/div/div[4]/table/tbody//tr")).size();		
		  System.out.println(count);
		  
		  System.out.println("inside");
		  
		  String status= driver.findElement(By.id("SCFActivated")).getAttribute("value");
		  System.out.println("status"+status);
		  System.out.println("after");
		  if(status.equals("false"))
		  {
			  focusClick(driver,driver.findElement(By.id("SCFActivatedOn")),br);
		  }
  
		  focusClick(driver,driver.findElement(By.id("mainSubmitButton")),br);
		  
		  /*String a="";
		  do{
			  try{
				  
			  a=driver.findElement(By.cssSelector("span.help-block.error")).getText();
			  System.out.println("aaaaaaa0.5");
			  }
			  catch(Exception e)
			  {
				  System.out.println("Exception caught");
				  a="";
			  }
		  }while(a.equals(""));*/
		  
		  schk=orderprocess(driver,br);
		  
		  if(driver.findElement(By.cssSelector("span.help-block.error")).isDisplayed())
		  {
			  statusTracker(br,"Pass","Verify if error message is displayed when trying to enable feature with no Forward To TN","Error message is displayed: " + driver.findElement(By.cssSelector("span.help-block.error")).getText(),"Error message should be displayed");
		  }
		  else
		  {
			  statusTracker(br,"Fail","Verify if error message is displayed when trying to enable feature with no Forward To TN","Error message is not displayed","Error message should be displayed");
			  schk="Fail";
		  }
		  
		  
		  status= driver.findElement(By.id("SCFActivated")).getAttribute("value");
		  if(status.equals("true"))
		  {
			  focusClick(driver,driver.findElement(By.id("SCFActivatedOn")),br);
		  }
		  
		  slftn=driver.findElement(By.xpath("//html/body/div[3]/form/div/div[2]/div/button/span")).getAttribute("data-friendlyname-contact");
			System.out.println("selftn"+slftn);
		    String ac=slftn.substring(0,3);
			String midtn=slftn.substring(3,6);
		  
		  //String midtn="345";
		  String lastfour= randomNO(9999,1000);
	  
		  driver.findElement(By.id("txtAreaCode2")).sendKeys(ac);
		  driver.findElement(By.id("txtExchange2")).sendKeys(midtn);
		  driver.findElement(By.id("txtTelNum2")).sendKeys(lastfour);
		  Thread.sleep(1000);
		  //selenium.click("id=AddToPhoneNumbers");
		  
		  //do{
		  //}while(selenium.isElementPresent("//body/div[10]"));
		  //Thread.sleep(3000);
		  focusClick(driver,driver.findElement(By.id("mainSubmitButton")),br);
		  
		  String a1="";
		  do{
			  try{
				  
			  a1=driver.findElement(By.cssSelector("span.help-block.error")).getText();
			  System.out.println("aaaaaaa0.5");
			  }
			  catch(Exception e)
			  {
				  System.out.println("Exception caught");
				  a1="";
			  }
		  }while(a1.equals(""));
		  
		  Thread.sleep(1000);
		 		  
		  //schk=orderprocess();
		  if(driver.findElement(By.cssSelector("span.help-block.error")).isDisplayed())
		  {			 
			  
			  statusTracker(br,"Pass","Verify if error message is displayed when trying to enable feature with no TNs","Error message is displayed: " + driver.findElement(By.cssSelector("span.help-block.error")).getText(),"Error message should be displayed");
			  
			  slftn=driver.findElement(By.xpath("//html/body/div[3]/form/div/div[2]/div/button/span")).getAttribute("data-friendlyname-contact");
				System.out.println("selftn"+slftn);
			    String ac2=slftn.substring(0,3);
				String midtn2=slftn.substring(3,6);
			  //ac1=randomNO(999,200);
			  //midtn1=randomNO(999,200);
			  //String midtn="345";
			  String lastfour2= randomNO(9999,1000);
		  
			  driver.findElement(By.id("txtAreaCode")).sendKeys(ac2);
			  driver.findElement(By.id("txtExchange")).sendKeys(midtn2);
			  driver.findElement(By.id("txtTelNum")).sendKeys(lastfour2);
		
			  Thread.sleep(1000);
			  focusClick(driver,driver.findElement(By.id("AddToPhoneNumbers")),br);		
			  Thread.sleep(3000);
			  focusClick(driver,driver.findElement(By.id("mainSubmitButton")),br);			  
			  schk=orderprocess(driver,br);
			  if(schk.equals("Pass"))
			  {
				  statusTracker(br,"Pass","Verify if order processing is done successfully for adding a TN","Order processing is done successfully","Order processing should be done successfully");
				  schk="Pass";
			  }
			  else
			  {
				  statusTracker(br,"Fail","Verify if order processing is done successfully for adding a TN","Order processing is not done successfully","Order processing should be done successfully");
			  }
			  
		  }
		  else
		  {
			  statusTracker(br,"Fail","Verify if error message is displayed when trying to enable feature with no TNs","Error message is not displayed","Error message should be displayed");
			  schk="Fail";
		  }
		  //schk=orderprocess();
	  }
  return schk;
}
  
  public String flowrunmaxtn(WebDriver driver,String br) throws Exception
  {
	  String schk ="Fail";
	  String ac;
	  String midtn;
	  String lastfour;
	  String tn;
	  int count;
	  
//	  TN=TN+lastfour;
	  int limit =driver.findElements(By.xpath("//html/body/div[3]/form/div/div[3]/div[2]/div/div/div/div/div[4]/div/div[2]/div[3]/table/tbody/tr")).size();		
	  
	  do{
		  ac=randomNO(999,200);
		  if(ac.equals("900") || ac.equals("976"))
			  ac=randomNO(999,200);
		  midtn=randomNO(999,200);
		  //String midtn="345";
		  lastfour= randomNO(9999,1000);
	  
		  driver.findElement(By.id("txtAreaCode")).sendKeys(ac);
		  driver.findElement(By.id("txtExchange")).sendKeys(midtn);
		  driver.findElement(By.id("txtTelNum")).sendKeys(lastfour);

		  //System.out.println(selenium.getXpathCount("//*[@id='TNGridRefresh']/div"));
		  //Thread.sleep(1000);
		  focusClick(driver,driver.findElement(By.id("AddToPhoneNumbers")),br);		
		  
		  tn=ac+midtn+lastfour;
		  //do{
		  //}while(selenium.isElementPresent("//body/div[10]"));
		  Thread.sleep(10000);
		  
		  //System.out.println(selenium.getXpathCount("//*[@id='TNGridRefresh']/div"));
		  count =driver.findElements(By.xpath("//html/body/div[3]/form/div/div[3]/div[2]/div/div/div/div/div[4]/div/div[2]/div[3]/table/tbody/tr")).size();		
		  int chk=0;
		  for(int i=1;i<=count;i++)
		  {
			  
			  //System.out.println(selenium.getValue("//html/body/div/section/form/div[2]/div/div/div["+(i+1)+"]/table/tbody/tr/td//input"));
			  //selenium.getValue("//html/body/div[3]/form/div/div[3]/div[2]/div/div/div[3]/div/div/div[3]/table/tbody/tr["+i+"]/td//input");
			  System.out.println(driver.findElement(By.xpath("//html/body/div[3]/form/div/div[3]/div[2]/div/div/div/div/div[4]/div/div[2]/div[3]/table/tbody/tr["+i+"]/td/input")).getText());
			  if((driver.findElement(By.xpath("//html/body/div[3]/form/div/div[3]/div[2]/div/div/div/div/div[4]/div/div[2]/div[3]/table/tbody/tr["+i+"]/td/input")).getText()).equals(tn))
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
		  if(assertTrue(isElementPresent(By.cssSelector("span.help-block.error"))))
		  {
			  		statusTracker(br,"Fail","Verify if 30 TNs can be added to the list","Error message is present before the 30 TNs are completed added","TN should be added to the list");
			  		schk="Fail";
		  }
	  }while(count<30 && schk.equals("Pass"));
		  
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
		  Thread.sleep(1000);
		  focusClick(driver,driver.findElement(By.id("AddToPhoneNumbers")),br);
		  
		  tn=ac+midtn+lastfour;
		  //do{
		  //}while(selenium.isElementPresent("//body/div[10]"));
		  
		  Thread.sleep(3000);
		  if(assertTrue(isElementPresent(By.cssSelector("span.help-block.error"))))
		  {
			  statusTracker(br,"Pass","Verify if error message is displayed when adding 31st TN","Error message is displayed: " + driver.findElement(By.cssSelector("span.help-block.error")).getText(),"Error message should be displayed");
		  }
		  else
		  {
			  statusTracker(br,"Fail","Verify if error message is displayed when adding 31st TN","Error message is not displayed","Error message should be displayed");
			  schk="Fail";
		  }
	  }
	  
	  if(schk.equals("Pass"))
	  {
		  focusClick(driver,driver.findElement(By.id("mainSubmitButton")),br);
		  
		  schk=orderprocess(driver,br);
		  ///OMG
		  //schk="Pass";
		  if(schk.equals("Pass"))
		  {
			  statusTracker(br,"Pass","Verify if adding 30 TNs is processed successfully","Order is successfully processed","Order should be successfully processed");  
			  int count1 =driver.findElements(By.xpath("//html/body/div[3]/form/div/div[3]/div[2]/div/div/div/div/div[4]/div/div[2]/div[3]/table/tbody/tr")).size();		
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
  
  public String TNcheck(WebDriver driver,String ac, String midtn, String lastfour, String check,String br) throws Exception
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
      
   /*   focusClick(driver,driver.findElement(By.xpath("//html/body/div[3]/form/div/div[3]/div[2]/div/div/div/div/div[4]/div/div[2]/div/p/span[2]/button[1]")),br);
	  do{
    	  Thread.sleep(1000);
      }while(!(drive.findElement(By.xpath("//body/div[11]")).isDisplayed()));
	  
      if(driver.findElements(By.xpath("//html/body/div[4]/div")).size()!=0)
      {
             statusTracker(br,"Pass","Verify if error message is displayed on clicking the friendly icon when adding "+check+" TN","Error message is displayed: "+ driver.findElement(By.cssSelector("div.modal-body > p")).getText(),"Error message should be displayed");
             schk="Pass";
             focusClick(driver,driver.findElement(By.cssSelector("div.modal-footer > button.btn.btn-primary")),br);
      }
      else
      {
             statusTracker(br,"Fail","Verify if error message is displayed on clicking the friendly icon when adding "+check+" TN","Error message is not displayed","Error message should be displayed");
             schk="Fail";
             focusClick(driver,driver.findElement(By.cssSelector("div.modal-footer > button.btn.btn-primary")),br);
      }*/
      
      return schk;

      
	  /*String schk="Fail";
	  int limit =driver.findElements(By.xpath("//html/body/div[3]/form/div/div[3]/div[2]/div/div/div/div/div[4]/div/div[2]/div[3]/table/tbody/tr")).size();		

	  int count;
	  String tn;

	  if(check.equals("same as To"))
	  {
		  focusClick(driver,driver.findElement(By.id("SCFActivatedOn")),br);
		  driver.findElement(By.id("txtAreaCode2")).clear();
		  driver.findElement(By.id("txtAreaCode2")).sendKeys(ac);
		  driver.findElement(By.id("txtExchange2")).clear();
		  driver.findElement(By.id("txtExchange2")).sendKeys(midtn);
		  driver.findElement(By.id("txtTelNum2")).clear();
		  driver.findElement(By.id("txtTelNum2")).sendKeys(lastfour);

	  }
	  driver.findElement(By.id("txtAreaCode")).clear();
	  driver.findElement(By.id("txtAreaCode")).sendKeys(ac);
	  
	  driver.findElement(By.id("txtExchange")).clear();
	  driver.findElement(By.id("txtExchange")).sendKeys(midtn);
	  
	  driver.findElement(By.id("txtTelNum")).clear();
	  driver.findElement(By.id("txtTelNum")).sendKeys(lastfour);
	 
	  focusClick(driver,driver.findElement(By.id("AddToPhoneNumbers")),br);	
	  tn=ac+midtn+lastfour;
	  //do{
	  //}while(selenium.isElementPresent("//body/div[10]"));
	  drive.manage().timeouts().implicitlyWait(60,TimeUnit.SECONDS);
	  	        
	  if(check.equals("same as To"))
	  {
		  focusClick(driver,driver.findElement(By.id("mainSubmitButton")),br);		 
		  schk=orderprocess(driver,br);
		  Thread.sleep(1000);
	  }
	  
	  count=driver.findElements(By.xpath("//html/body/div[3]/form/div/div[3]/div[2]/div/div/div/div/div[4]/div/div[2]/div[3]/table/tbody/tr")).size();		

	  boolean errmesg= driver.findElement(By.cssSelector("span.help-block.error")).isDisplayed();
      System.out.println("error"+errmesg);
      
	  if(errmesg && count==limit)
	  {
		  statusTracker(br,"Pass","Verify if error message is displayed when adding "+check+" TN to the From TN list","Error message is displayed: "+ driver.findElement(By.cssSelector("span.help-block.error")).getText(),"Error message should be displayed");
		  schk="Pass";
	  }
	  else
	  {
		  statusTracker(br,"Fail","Verify if error message is displayed when adding "+check+" TN to the From TN list","Error message is not displayed","Error message should be displayed");
		  schk="Fail";
	  }
	                                                  
	  focusClick(driver,driver.findElement(By.xpath("//html/body/div[3]/form/div/div[3]/div[2]/div/div/div/div/div[4]/div/div[2]/div/p/span[2]/a")),br);
	  do{
    	  Thread.sleep(1000);
      }while(!(drive.findElement(By.xpath("//body/div[11]")).isDisplayed()));
      //boolean errmesg1= driver.findElements(By.cssSelector("div.modal-body > p")).size();
      //System.out.println("error123"+errmesg1);
      if(driver.findElements(By.xpath("//html/body/div[4]/div")).size()!=0)
      {
             statusTracker(br,"Pass","Verify if error message is displayed on clicking the friendly icon when adding "+check+" TN","Error message is displayed: "+ driver.findElement(By.cssSelector("div.modal-body > p")).getText(),"Error message should be displayed");
             schk="Pass";
             focusClick(driver,driver.findElement(By.cssSelector("div.modal-footer > button.btn.btn-primary")),br);
      }
      else
      {
             statusTracker(br,"Fail","Verify if error message is displayed on clicking the friendly icon when adding "+check+" TN","Error message is not displayed","Error message should be displayed");
             schk="Fail";
             focusClick(driver,driver.findElement(By.cssSelector("div.modal-footer > button.btn.btn-primary")),br);
      }
          	  
	  return schk;*/
  }
  
  public String TNValidation(WebDriver driver,String br) throws Exception
  {
	  String status= driver.findElement(By.id("SCFActivated")).getAttribute("value");
	  if(status.equals("false"))
	  {
		  focusClick(driver,driver.findElement(By.id("SCFActivatedOn")),br);
	  }
	  String schk ="Pass";
	  schk=TNcheck(driver,"022","300","4000","first digit 0",br);
	  Thread.sleep(5000);
	  schk=TNcheck(driver,"222","000","4000","fourth digit 0",br);
	  Thread.sleep(5000);
	  schk=TNcheck(driver,"122","300","4000","first digit 1",br);
	  Thread.sleep(5000);
	  schk=TNcheck(driver,"222","152","4000","fourth digit 1",br);
	  Thread.sleep(5000);
	  schk=TNcheck(driver,"","","","blank",br);
	  Thread.sleep(5000);
	  schk=TNcheck(driver,ac1,midtn1,lastfour1,"existing",br);
	  Thread.sleep(5000);
	  schk=TNcheck(driver,"99","9","99","Invalid",br);
	  Thread.sleep(5000);
	  schk=TNcheck(driver,phoneline_ac,phoneline_midtn,phoneline_lastfour,"self",br);
	  Thread.sleep(5000);
	  //schk=TNcheck(driver,"984","985","9849","same as To");
	  
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
  
  public String TNcheck2(WebDriver driver,String ac, String midtn, String lastfour, String check,String br) throws Exception
  {
	  String schk="Fail";
	  String tn;
	  if(check.equals("no dropdown"))
      {
		  focusClick(driver,driver.findElement(By.id("SCFStatusdrop")),br);
     	  Thread.sleep(1000);
      }
	  else
	  {
		  driver.findElement(By.id("txtAreaCode2")).clear();
		  driver.findElement(By.id("txtAreaCode2")).sendKeys(ac);
		  
		  driver.findElement(By.id("txtExchange2")).clear();
		  driver.findElement(By.id("txtExchange2")).sendKeys(midtn);
		  
		  driver.findElement(By.id("txtTelNum2")).clear();
		  driver.findElement(By.id("txtTelNum2")).sendKeys(lastfour);
		  Thread.sleep(1000);
	  }
	  focusClick(driver,driver.findElement(By.id("mainSubmitButton")),br);	
	  tn=ac+midtn+lastfour;
	  //do{
	  //}while(selenium.isElementPresent("//body/div[10]"));
	  drive.manage().timeouts().implicitlyWait(60,TimeUnit.SECONDS);
	  
	  boolean errmesg= driver.findElement(By.cssSelector("span.help-block.error")).isDisplayed();
      System.out.println("error"+errmesg);
	  if(errmesg)
	  {
		  statusTracker(br,"Pass","Verify if error message is displayed when adding "+check+" TN to the To TN","Error message is displayed: "+ driver.findElement(By.cssSelector("span.help-block.error")).getText(),"Error message should be displayed");
		  schk="Pass";
	  }
	  else
	  {
		  statusTracker(br,"Fail","Verify if error message is displayed when adding "+check+" TN to the To TN","Error message is not displayed","Error message should be displayed");
		  schk="Fail";
	  }
	                                                  
	  focusClick(driver,driver.findElement(By.xpath("//html/body/div[3]/form/div/div[3]/div[2]/div/div/div/div/div[4]/div[1]/div[1]/div[2]/span/span[2]/a")),br);
	  do{
    	  Thread.sleep(1000);
      }while(!(drive.findElement(By.xpath("//body/div[11]")).isDisplayed()));
      //boolean errmesg1= driver.findElements(By.cssSelector("div.modal-body > p")).size();
      //System.out.println("error123"+errmesg1);
      if(driver.findElements(By.xpath("//html/body/div[4]/div")).size()!=0)
      {
             statusTracker(br,"Pass","Verify if error message is displayed on clicking the friendly icon when adding "+check+" TN","Error message is displayed: "+ driver.findElement(By.xpath("div.modal-body > p")).getText(),"Error message should be displayed");
             schk="Pass";
             focusClick(driver,driver.findElement(By.cssSelector("div.modal-footer > button.btn.btn-primary")),br);
      }
      else
      {
             statusTracker(br,"Fail","Verify if error message is displayed on clicking the friendly icon when adding "+check+" TN","Error message is not displayed","Error message should be displayed");
             schk="Fail";
             focusClick(driver,driver.findElement(By.cssSelector("div.modal-footer > button.btn.btn-primary")),br);
      }      
	  
	  return schk;
  }
  
  public String TNValidation2(WebDriver driver,String br) throws Exception
  {	
		  String status= driver.findElement(By.id("SCFActivated")).getAttribute("value");
		  if(status.equals("false"))
		  {
			  focusClick(driver,driver.findElement(By.id("SCFActivatedOn")),br);
		  }
			
	  String schk ="Pass";
	  schk=TNcheck2(driver,"022","300","4000","first digit 0",br);
	  schk=TNcheck2(driver,"222","000","4000","fourth digit 0",br);
	  schk=TNcheck2(driver,"122","300","4000","first digit 1",br);
	  schk=TNcheck2(driver,"222","152","4000","fourth digit 1",br);
	  schk=TNcheck2(driver,"900","800","4000","toll free",br);
	  schk=TNcheck2(driver,"976","800","4000","toll free",br);
	  schk=TNcheck2(driver,"","","","blank",br);
	  schk=TNcheck2(driver,"99","9","99","Invalid",br);
	  schk=TNcheck2(driver,phoneline_ac,phoneline_midtn,phoneline_lastfour,"self",br);
	  schk=TNcheck2(driver,ac1,midtn1,lastfour1,"same as From",br);
	  schk=TNcheck2(driver,"","","","no dropdown",br);
	  
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
                      wb.close();

                      driver.manage().timeouts().implicitlyWait(tlim,TimeUnit.SECONDS);
                      logger.info("qtest1");
                      try {
                    	  if(first==0)
                    	  {
                    		  login(driver,username,pwd);
                    	  }
                    	  logger.info("a");
                    	  int chk=0;
              		      do{
              		           Thread.sleep(1000);       
              		          chk++;
              		          System.out.println(chk);
              		                }
              		      while(driver.findElement(By.xpath("//*[@id='progress']")).isDisplayed());
  	    
                    	 // focusClick(driver,driver.findElement(By.linkText("Settings")),br);
                    	  
                    	  Thread.sleep(5000);
                    	  focusClick(driver,driver.findElement(By.xpath("(//a[contains(text(),'(edit)')])[4]")),br);
  	      
                    	  Thread.sleep(5000);
                    	  do{
                    	  }while(assertTrue(isElementPresent(By.cssSelector("#Answer-Anywhere > h1"))));
                    	  Thread.sleep(2000);
                    	  focusClick(driver,driver.findElement(By.xpath("//div[@id='ContentRefresh']/ul/li[4]/a")),br);			
                    	  Thread.sleep(3000);
			drive.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
			
			String status=driver.findElement(By.id("SCFActivatedOn")).getAttribute("class");			
			String status1="off";
			if(status.equals("btn"))
				status1="on";
			
			String schk="Pass";
			
			//System.out.println(selenium.getValue("//html/body/div[3]/form/div/div[3]/div[2]/div/div/div[3]/div/div/div[2]/table/tbody/tr[1]/td/input"));
			
			
			//int count = selenium.getXpathCount("/html/body/div[3]/form/div/div[3]/div[2]/div/div/div[3]/div/div/div[2]/table/tbody/tr").intValue();
			//System.out.println("ys "+count);
			
		/*	selenium.click("//*[@id='SCFActivatedOn']");
			Thread.sleep(1000);
			int listcount=selenium.getXpathCount("//html/body/div[3]/form/div/div[3]/div[2]/div/div/div/div/div[4]/div/div/div[7]/div/div/ul//li").intValue();
			String text[]=new String[listcount+1];
			String text2[]=new String[listcount+1];
			for(int i=1;i<=listcount;i++)
			{
				
				text[i]=selenium.getText("//html/body/div[3]/form/div/div[3]/div[2]/div/div/div/div/div[4]/div/div/div[7]/div/div/ul/li["+i+"]");
				text2[i]=selenium.getAttribute("//html/body/div[3]/form/div/div[3]/div[2]/div/div/div/div/div[4]/div/div/div[7]/div/div/ul/li["+i+"]/a@data-friendlyname-contact");
				text[i]=text[i].substring(0,14);
				System.out.println(text[i]);
			}
			System.out.println("check1");
			selenium.click("id=SCFStatusdrop");
			
			System.out.println("check2");
			//Thread.sleep(1000);
			  selenium.click("id=ddlSCFnum");
			  System.out.println("link="+text[2]);
			  selenium.click("link="+text[2]);
			  Thread.sleep(2000);*/
			  
			  //link=(816) 888-8888
			//selenium.select("//html/body/div[3]/form/div/div[3]/div[2]/div/div/div/div/div[4]/div/div/div[7]/div/div/ul/li[2]");
			Thread.sleep(5000);
			schk=deleteall(driver,br);
			
			if(schk.equals("Fail"))
			{
				driver.get("https://voice.atgeng.timewarnercable.com/VZ2/CallForwardingBusy/CallForwardingBusy");
			    focusClick(driver,driver.findElement(By.xpath("//div[@id='ContentRefresh']/ul/li[4]/a")),br);
			    focusClick(driver,driver.findElement(By.id("notSavedAlertAnchor")),br);
			}
			
			if(schk.equals("Pass"))
				schk=flowrun(driver,br);
			
			if(schk.equals("Pass"))
			schk=flowrun(driver,br);
			
			if(schk.equals("Pass"))
			{
				status = driver.findElement(By.id("SCFActivatedOn")).getAttribute("class");
				if(status.equals("btn"))
					status="Off";
				else
					status="On";

				statusTracker(br,"","Verify TN add/remove when feature is "+status,"","");
				schk=flowrun1(driver,br);
			}
		
			if(schk.equals("Pass"))
			{
				focusClick(driver,driver.findElement(By.id("SCFActivatedOff")),br);				
				status = driver.findElement(By.id("SCFActivatedOn")).getAttribute("class");
				
				if(status.equals("btn"))
					status="Off";
				else
					status="On";
				statusTracker(br,"","Verify TN add/remove when feature is "+status,"","");
				schk=flowrun1(driver,br);
			}
			
			if(schk.equals("Pass"))
			{
				statusTracker(br,"","Verify From TN Validation","","");
				schk=TNValidation(driver,br);
			}
			
			if(schk.equals("Pass"))
			{
				statusTracker(br,"","Verify To TN Validation","","");
				schk=TNValidation2(driver,br);
			}
			
			if(schk.equals("Pass"))
			{
				statusTracker(br,"","Verify maximum TN operations","","");
				schk=flowrunmaxtn(driver,br);
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

protected boolean assertTrue(Object elementPresent) {
	// TODO Auto-generated method stub
	return false;
}

protected Object isElementPresent(By cssSelector) {
	// TODO Auto-generated method stub
	return null;
}
}
