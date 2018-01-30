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

public class PinChange extends CommonFunctions {
                
                String table, tns[];
    String tlimit,username,pwd;
    int tncount;                                                    
                                
    String phoneline,phoneline_ac,Acccode;
                int rank[]= new int[50];
                

                int c_sequence;
                String name_for_rank[]= new String[40];
                String price, rank_channels;
                String pin1;
                public PinChange(String path) {
                                this.path = path;
                }

  public String flowrun(WebDriver driver, String br) throws Exception
  {
	   pin1 = randomNO(9999,1000);
System.out.println("flowrun");
driver.findElement(By.id("PasswordForNewPin")).clear();
driver.findElement(By.id("PasswordForNewPin")).sendKeys(pin1);
driver.findElement(By.id("PasswordForConfirmPin")).clear();
driver.findElement(By.id("PasswordForConfirmPin")).sendKeys(pin1);
	  driver.findElement(By.id("mainSubmitButton")).click();
	  String schk=orderprocess(driver,br);
      System.out.println("right");	
       if(schk.equals("Pass"))
		  statusTracker(br,"Pass","Verify if changing pin order is processed successfully","Order is successfully processed for changing the new pin to "+ pin1,"Order should be successfully processed");
	  else
		  statusTracker(br,"Fail","Verify if changing pin order is processed successfully","Order is not successfully processed for changing the new pin to "+ pin1,"Order should be successfully processed");
	  return schk;
  }

  
  public String Pincheck(String br,String pin, String cpin, String check,WebDriver driver) throws Exception
  {
	  
	  System.out.println("Iam cumng too");
	  String schk="Fail";
	  System.out.println("Befor entering pins");
	  driver.findElement(By.id("PasswordForNewPin")).clear();
	  driver.findElement(By.id("PasswordForNewPin")).sendKeys(pin);
	  driver.findElement(By.id("PasswordForConfirmPin")).clear();
	  driver.findElement(By.id("PasswordForConfirmPin")).sendKeys(cpin);
	  driver.findElement(By.id("mainSubmitButton")).click();
      System.out.println("after submitting");
      Thread.sleep(3000);
    //  driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
      
      
      if((driver.findElement(By.cssSelector("span.help-block.error")).isDisplayed()))
     {
	  System.out.println("printing");
       statusTracker(br,"Pass","Verify if error message is displayed when adding "+check+" PIN","Error message is displayed:"+driver.findElement(By.cssSelector("span.help-block.error")).getText(),"Error message should be displayed");
     } 
      else if(driver.findElement(By.xpath("//div[@id='pin-reset-form']/div[5]/span")).isDisplayed())
     {
      statusTracker(br,"Pass","Verify if error message is displayed when adding "+check+" PIN","Error message is displayed: "+ driver.findElement(By.xpath("//div[@id='pin-reset-form']/div[5]/span")).getText(),"Error message should be displayed");
     }  
      else
      {
    	  
    	  statusTracker(br,"Fail","Verify if error message is displayed when adding "+check+" PIN","Error message is not displayed","Error message should be displayed");
      }
    	  
       schk ="Pass";
     
  return schk;
  }
  private String getText() {
	// TODO Auto-generated method stub
	return null;
}

private void ImplicitlyWait(int i) {
	// TODO Auto-generated method stub
	
}


private Object isElementPresent(String string) {
	// TODO Auto-generated method stub
	return null;
}

public String PinValidation(String br,WebDriver driver) throws Exception
  {
	System.out.println("iam cumng");
	  //String pin1 = randomNO(9999,1000);
	  String schk ="Fail";
	  String pin,pin2;
	  
	  schk=Pincheck(br,"","1234","Blank New ",driver);
	  
	  schk=Pincheck(br,"1234","","Blank Confirm",driver);
	  
	  String seftn=driver.findElement(By.xpath("//html/body/div[3]/form/div/div[2]/div/button/span")).getAttribute("data-friendlyname-contact");
	  seftn=seftn.substring(6);
	  schk=Pincheck(br,seftn,seftn,"Self",driver);
	  
	  schk=Pincheck(br,"1234","1234","consecutive",driver);
	  System.out.println("order done");
	  //driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	  
	  pin=randomNO(9,1);
	  pin=pin+pin+pin+pin;
	  System.out.println("m not hapenning"+pin);
	  schk=Pincheck(br,pin,pin,"same digits",driver);
	  
	  System.out.println("m not hapenning1"+pin1);
	  if(schk.equals("Pass"))
	  schk=Pincheck(br,pin1,pin1,"same",driver);
	 // driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	  
	  pin=randomNO(999,100);
	  schk=Pincheck(br,pin,pin,"less than 4 digit",driver);
	  System.out.println("4 digits");
	 // driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	  
	  pin=randomNO(9999,1000);
	  pin2=randomNO(9999,1000);
	  schk=Pincheck(br,pin,pin2,"different confirm",driver);
      System.out.println("different");
      //driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
       schk="Pass";
	  return schk;
  }
  
public void execute(String br, WebDriver driver, String url, int loc, String name1) throws Exception {
    
    Feature_Name="CFNA";
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
	Thread.sleep(5000);
			
			//selenium.click("link=VoiceMail Non-Pin Settings");
			//selenium.waitForPageToLoad(tlimit);
			
			//String status = selenium.getValue("id=Activated");
			String schk = "Pass";
			System.out.println("run");
  		driver.findElement(By.linkText("Voicemail PIN Settings")).click();;
  	    schk=flowrun(driver,br);
		schk=PinValidation(br,driver);
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
      wb.close();
      System.out.println("Complete");
    }
  }

}




