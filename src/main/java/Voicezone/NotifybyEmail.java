package Voicezone;

import java.io.File;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;

public class NotifybyEmail extends CommonFunctions {
                
                String table, tns[];
    String tlimit,username,pwd;
    int tncount;                                                    
                                
    String phoneline,phoneline_ac,Acccode;
                int rank[]= new int[50];
                

                int c_sequence;
                String name_for_rank[]= new String[40];
                String price, rank_channels;
                String num,email;
                int count1;
                public NotifybyEmail(String path) {
                                this.path = path;
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
  
  public String add(WebDriver driver,String br) throws Exception
  {
	  String schk="Fail";
	  System.out.println("goin inside addremove");
	  if(!(assertTrue(isElementPresent("//html/body/div[3]/form/div/div[3]/div[2]/div/div/div/div/div[3]/div/div[2]/table/thead/tr"))))
	  {
		  int rand=Integer.parseInt("5");
		  for(int i=1;i<=rand;i++)
		  {
			  	num=randomNO(9999,1000);
			  	driver.findElement(By.id("txtEmailAddress")).sendKeys("test"+num+"@test.tst");
				Thread.sleep(3000);
			  	driver.findElement(By.id("btnAddtolist")).click();
		  	
		  		if(i==rand)
		  			email="test"+num+"@test.tst";
		  }
		  Thread.sleep(2000);
		  driver.findElement(By.id("mainSubmitButton")).click();
		  schk=orderprocess(driver,br);
		  String s[]=new String[rand];
		  if(schk.equals("Pass"))
		  {
			 statusTracker(br,"Pass","Verify if emails could be added", rand+ " Email addresses were added successfully","Email address should be added successfully");
			 String rands2=randomNO(2,1);
	  
		  int rand2=Integer.parseInt(rands2);
		  
		/*	 for(int i=1;i<=rand;i++)
			  {
				String Mail=driver.findElement(By.cssSelector("tbody > tr > td")).getAttribute("value");
				  //s[i-1]=selenium.getText("//html/body/div[3]/form/div/div[3]/div[2]/div/div/div/div[5]/div/div[2]/div/table/tbody/tr["+i+"]/td");
				  //s[i-1]=s[i-1].replaceAll(" ","");
				  //s[i-1]=s[i-1].replaceAll(" ","");
				  System.out.println(Mail);
			  }
			  */
			  for(int i=1;i<=rand2;i++)
			  {
				  driver.findElement(By.cssSelector("button.close-delete.poping")).click();
				  count1= driver.findElements(By.xpath("/html/body/div[3]/form/div/div[3]/div[2]/div/div/div/div/div/div[3]/div/div[2]/table/tbody/tr/td[5]/label")).size();
				  //selenium.click("id=DeleteNumber_"+s[i-1]);
				  System.out.println(count1);
				  Thread.sleep(3000);
			  }
			  driver.findElement(By.id("mainSubmitButton")).click();
			  Thread.sleep(3000);
			  schk=orderprocess(driver,br);
			  
			  
			  int count= driver.findElements(By.xpath("/html/body/div[3]/form/div/div[3]/div[2]/div/div/div/div/div/div[3]/div/div[2]/table/tbody/tr/td[5]/label")).size();
			  System.out.println(count);
			/*  for(int i=1;i<=count;i++)
			  {
				  //selenium.click("//html/body/div[3]/form/div/div[3]/div[2]/div/div/div/div/div[3]/div/div[2]/table/tbody/tr/td[2]/button");
				count= driver.findElements(By.xpath("/html/body/div[3]/form/div/div[3]/div[2]/div/div/div/div/div/div[3]/div/div[2]/table/tbody/tr/td[5]/label")).size();
				  //fin[i-1]=selenium.getText("//html/body/div[3]/form/div/div[3]/div[2]/div/div/div/div[5]/div/div[2]/div/table/tbody/tr["+i+"]/td");
				  //fin[i-1]=fin[i-1].replaceAll(" ","");
				  //fin[i-1]=fin[i-1].replaceAll(" ","");
				  System.out.println(count);
			  }
			  */
			 
			  if(schk.equals("Pass"))
			  {
				  statusTracker(br,"Pass","Verify if emails could be removed", rand2+ " Email addresses were removed successfully","Email address should be removed successfully");
				  
				  count= driver.findElements(By.xpath("//html/body/div[3]/form/div/div[3]/div[2]/div/div/div/div/div/div[3]/div/div[2]/table/tbody/tr/td[5]/label")).size();
				  int schk2=0;
				  for(int i=1;i<=count;i++)
				  {
					  if(count1==count)
					  {
						  schk2=1;
					  }
				  }  
				  if(schk2==0)
				  {
					  statusTracker(br,"Pass","Verify if email addresses are the same after order process", "Email addresses are the same after order process","Email address should be the same after order process");
					  schk="Pass";
				  }
				  else
				  {
					  statusTracker(br,"Fail","Verify if email addresses are the same after order process", "Email addresses are not the same after order process","Email address should be the same after order process");
					  schk="Fail";
				  }
			  }
			  else
			  {
				 statusTracker(br,"Fail","Verify if emails could be removed", rand2+ " Email addresses were not removed successfully","Email address should be removed successfully");
				 schk="Fail";
			  }
			  
		  }
		  else
		  {
			 statusTracker(br,"Fail","Verify if emails could be added", rand+ " Email addresses were not added successfully","Email address should be added successfully");
			 schk="Fail";
		  }

		  

		  //selenium.type("id=txtEmailAddress","test@test.tst");
		  //selenium.click("id=btnAddtolist");
		  //Thread.sleep(3000);
		  //System.out.println("check1");
	  }
	  else
	  {
		  schk="Pass";
		  System.out.println("goin inside addremove2");
	  }
	  
	  return schk;
}

private Object isElementPresent(String string) {
	// TODO Auto-generated method stub
	return null;
}

public String deleteall(WebDriver driver,String br) throws Exception
{
	  String schk="Fail";
	  System.out.println(schk);
	
		  System.out.println("goin inside notify email"); 
		 int count= driver.findElements(By.xpath("/html/body/div[3]/form/div/div[3]/div[2]/div/div/div/div/div/div[3]/div/div[2]/table/tbody/tr/td[5]/label")).size();
		 
		  System.out.println("count"+count);
		  String s[]=new String[count];
		  if (count!=0)
		  {
			  System.out.println("inside loop");
		  for(int i=1;i<=count;i++)
		  {
			  System.out.println("inside for");
			 String Mail2=driver.findElement(By.cssSelector("tbody > tr > td")).getAttribute("value");
			  //s[i-1]=selenium.getText("//html/body/div[3]/form/div/div[3]/div[2]/div/div/div/div[5]/div/div[2]/div/table/tbody/tr["+i+"]/td");
			  //s[i-1]=s[i-1].replaceAll(" ","");
			  //s[i-1]=s[i-1].replaceAll(" ","");
			  System.out.println(Mail2);
		  }
		  
		  for(int i=1;i<=count;i++)
		  {
			  driver.findElement(By.cssSelector("button.close-delete.poping")).click();
			  //selenium.click("id=DeleteNumber_"+s[i-1]);
			  Thread.sleep(5000);
		  }
		  driver.findElement(By.id("mainSubmitButton")).click();
		  schk=orderprocess(driver,br);
		  
		  if(schk.equals("Pass"))
		  {
			  statusTracker(br,"Pass","Verify if all the emails could be removed", "Email addresses were removed successfully","Email address should be removed successfully");
			  schk="Pass";
		  }
		  else
		  {
			  statusTracker(br,"Fail","Verify if all the emails could be removed", "Email addresses were not removed successfully","Email address should be removed successfully");
		  }
		  //selenium.type("id=txtEmailAddress","test@test.tst");
		  //selenium.click("id=btnAddtolist");
		  //Thread.sleep(3000);
		  //System.out.println("check1");
	  }
	  else
		  schk="Pass";
	  System.out.println(schk);
	  return schk;
}






public String TNcheck(String br,String email, String check,WebDriver driver) throws Exception
  {

	  
	  System.out.println("Iam cumng too");
	  String schk="Fail";
	  System.out.println("Befor entering pins");
	  driver.findElement(By.id("txtEmailAddress")).clear();	  
	  driver.findElement(By.id("txtEmailAddress")).sendKeys(email);
	  Thread.sleep(3000);
		driver.findElement(By.id("btnAddtolist")).click();
      System.out.println("after submitting");
      Thread.sleep(5000);
    //  driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
      
      
      if((driver.findElement(By.cssSelector("span.help-inline.error")).isDisplayed()))
     {
	  System.out.println("printing");
		  statusTracker(br,"Pass","Verify if error message is displayed when adding "+check+" email","Error message is displayed: "+ driver.findElement(By.cssSelector("span.help-inline.error")).getText(),"Error message should be displayed");
		  schk="Pass";
	  }
	  else
	  {
		  statusTracker(br,"Fail","Verify if error message is displayed when adding "+check+" email","Error message is not displayed","Error message should be displayed");
		  schk="Fail";
	  }
	  
	  
	  return schk;
  }
  
  public String emailvalidation(String br,WebDriver driver) throws Exception
  {
	  String schk ="Pass";
	  int count= driver.findElements(By.xpath("/html/body/div[3]/form/div/div[3]/div[2]/div/div/div/div/div/div[3]/div/div[2]/table/tbody/tr/td[5]/label")).size();
		 if(count==0)
			{
			  driver.findElement(By.id("txtEmailAddress")).clear();	  
			  driver.findElement(By.id("txtEmailAddress")).sendKeys("aa@gmail.com");
			  driver.findElement(By.id("btnAddtolist")).click();
			} 
	  Thread.sleep(2000);
	  schk=TNcheck(br,"","blank",driver);
	  Thread.sleep(2000);
	  schk=TNcheck(br,"ss.com","invalid",driver);
	  Thread.sleep(5000);
	 // String self=driver.findElement(By.xpath("/html/body/div[3]/form/div/div[3]/div[2]/div/div/div/div/div/div[3]/div/div[2]/table/tbody/tr[1]/td[1]")).getText();
	  String self=driver.findElement(By.xpath(".//*[@id='EmailGridRefresh']/div[2]/table/tbody/tr[1]/td[1]")).getText(); 
	  System.out.println(self);
	  schk=TNcheck(br,self,"Same",driver);
	  Thread.sleep(2000);
	  schk=deleteall(driver,br);
	  int rand=Integer.parseInt("5");
	  for(int i=1;i<=rand;i++)
	  {
		  	num=randomNO(9999,1000);
		  	driver.findElement(By.id("txtEmailAddress")).clear();
		  	driver.findElement(By.id("txtEmailAddress")).sendKeys("test"+num+"@test.tst");
		  	driver.findElement(By.id("btnAddtolist")).click();
	  		Thread.sleep(3000);
	  		if(i==rand)
	  			email="test"+num+"@test.tst";
	  }
	  
	  driver.findElement(By.id("mainSubmitButton")).click();
	  schk=orderprocess(driver,br);
	  Thread.sleep(5000);
	  schk=TNcheck(br,"eodi@odi.com","max",driver);
      driver.findElement(By.xpath("/html/body/div[3]/form/div/div[3]/div[2]/div/div/div/div/div/div[5]/div/button[1]")).click();
      schk=deleteall(driver,br);
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
		 //	driver.findElement(By.linkText("Settings")).click();
			
		    driver.findElement(By.xpath("(//a[contains(text(),'(edit)')])[2]")).click();
		
				Thread.sleep(5000);
				do{
				}while(assertTrue(isElementPresent("//body/div[11]")));
				Thread.sleep(2000);
               
               


				
			//selenium.click("link=VoiceMail Non-Pin Settings");
			//selenium.waitForPageToLoad(tlimit);		
			//Thread.sleep(2000);
			
			//String status = selenium.getValue("id=Activated");
			String schk = "Pass";
			//String status = selenium.getValue("id=VoicemailToText");
	
		schk=deleteall(driver,br);
			
		if(schk.equals("Pass"))
		schk=add(driver,br);
		Thread.sleep(5000);
	           
		if(schk.equals("Pass"))
	    schk=emailvalidation(br,driver);
		/*	Friendly Name Code is commented as infinite spinner is displayed after Order Process*/
		 Thread.sleep(5000);
		 
		  int count= driver.findElements(By.xpath("//html/body/div[3]/form/div/div[3]/div[2]/div/div/div/div/div/div[3]/div/div[2]/table/tbody/tr/td[5]/label")).size();
		  System.out.println(count);
			 if(count==0)
				{
				  driver.findElement(By.id("txtEmailAddress")).clear();	  
				  driver.findElement(By.id("txtEmailAddress")).sendKeys("aa@gmail.com");
				  driver.findElement(By.id("btnAddtolist")).click();
				}
		        	 String icn1="//html/body/div[3]/form/div/div[3]/div[2]/div/div/div/div/div/div[3]/div/div[2]/table/tbody/tr/td[3]/span/button";
			         String nme1="//html/body/div[3]/form/div/div[3]/div[2]/div/div/div/div/div/div[3]/div/div[2]/table/tbody/tr/td[4]/span/span";
		        	 System.out.println("Inside loop");
	             
	             String name="Home";    
	             Thread.sleep(10000);
	             focusClick(driver, driver.findElement(By.xpath(icn1)),br);
	            // driver.findElement(By.xpath(icn1)).click();
	          Thread.sleep(5000);
	        /*  do
	          {Thread.sleep(1000);
	                 
	          }while(!(driver.findElement(By.xpath("//html/body/div[4]/div")).isDisplayed()) && driver.findElement(By.xpath("//html/body/div[4]/div")).isDisplayed());*/
	            
	         
	         System.out.println("Inside loop1");
	          driver.findElement(By.id("FriendlyNameDialogContactName")).clear();
	          driver.findElement(By.id("FriendlyNameDialogContactName")).sendKeys(name);
	          System.out.println("Inside loop2");
	          driver.findElement(By.cssSelector("#friendlyNameDialog > div.modal-footer > button.btn.btn-primary")).click();
	       
	          do{
	          }while(drive.findElements(By.xpath("//body/div[11]")).size()!=0);
	          Thread.sleep(10000);
	          
	          String name2=driver.findElement(By.xpath(nme1)).getText();
	          System.out.println("name:"+name+"name1:"+name2);
		        
	          //////////////////////////////////////////////////////////////
	          
	          if(name.equals(name2))
	          {
	          statusTracker(br,"Pass","Verify if the friendly name updated properly:"+name,"The friendly name updated correctly: "+name2,"The name should update correctly");                   
	          }
	          else
	          {
	          statusTracker(br,"Fail","Verify if the friendly name updated properly:"+name,"The friendly name not updated correctly: "+name2,"The name should update correctly");
	          }
	     
	

			
			//schk=flowrun();
			
			//if(schk.equals("Pass"))
//				schk=flowrun();
 
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

