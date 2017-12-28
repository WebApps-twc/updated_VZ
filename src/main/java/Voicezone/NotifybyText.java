package Voicezone;

import java.io.File;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;

public class NotifybyText extends CommonFunctions {
                
                String table, tns[];
    String tlimit,username,pwd;
    int tncount;                                                    
                                
    String phoneline,phoneline_ac,Acccode,phoneline_midtn,phoneline_lastfour;
                int rank[]= new int[50];
                

                int c_sequence;
                String name_for_rank[]= new String[40];
                String price, rank_channels;
                int count1;

                public NotifybyText(String path) {
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
  
  public String addremove(WebDriver driver,String br) throws Exception
  {
                  String schk="Fail";
                  System.out.println("goin inside addremove");
                  if((driver.findElements(By.xpath("//html/body/div[3]/form/div/div[3]/div[2]/div/div/div/div/div/div[4]/div/div[2]/table/tbody/tr/td")).size()==0))
                  { 
                                  String ac;
                                  String midtn;
                                  String lastfour;
                                  String rands=randomNO(5,3);
                                  rands="5";
                                  int rand=Integer.parseInt(rands);
                                  for(int i=1;i<=rand;i++)
                                  {
                                                                ac=randomNO(999,200);
                                                                midtn=randomNO(999,200);
                                                                lastfour= randomNO(9999,1000);
                                                                
                                                                driver.findElement(By.id("txtAreaCode")).sendKeys(ac);
                                                Thread.sleep(2000);
                                                                driver.findElement(By.id("txtExchange")).sendKeys(midtn);
                                                                Thread.sleep(2000);
                                                                driver.findElement(By.id("txtTelNum")).sendKeys(lastfour);
                                                                Thread.sleep(2000);
                                                     System.out.println("added");
                                                                driver.findElement(By.id("SelectMobileProvider")).click();
                                                                String j=randomNO(15,1);
                                                                int k=Integer.parseInt(j);
                                                                
                                                    Thread.sleep(5000);
                                                    driver.findElement(By.xpath("/html/body/div[3]/form/div/div[3]/div[2]/div/div/div/div/div/div[4]/div/div/div/div/div/ul/li["+k+"]/a")).click();






                                                  Thread.sleep(5000);
                                                                //driver.findElement(By.id("CTR_"+num1)).click();
                                                                driver.findElement(By.id("btnAddNumbertolist")).click();
                                                                Thread.sleep(3000);
                                                                if(i==rand)
                                                                {
                                                                                String ac1 = ac;
                                                                                String midtn1 = midtn;
                                                                                String lastfour1 = lastfour;
                                                                                Thread.sleep(3000);
                                                                }
                                  }
                                  
                                  driver.findElement(By.id("mainSubmitButton")).click();
                                  schk=orderprocess(driver,br);
                                  String s[]=new String[rand];
                                  if(schk.equals("Pass"))
                                  {
                                                statusTracker(br,"Pass","Verify if TNs could be added", rand+ " TNS were added successfully","TNS were added successfully");
                                                String rands2=randomNO(2,1);
                                                int rand2=Integer.parseInt(rands2);
                                             /////////////////////////////////////////////////////////////////////////////////////////////////
                                                /* for(int i=1;i<=rand;i++)
                                                  {
                                                                String Mail=driver.findElement(By.xpath("/html/body/div[3]/form/div/div[3]/div[2]/div/div/div/div/div/div[4]/div/div[2]/table/tbody/tr/td")).getAttribute("value");
                                                                  //s[i-1]=selenium.getAttribute("//html/body/div[3]/form/div/div[3]/div[2]/div/div/div/div[5]/div/div[2]/table/tbody/tr["+i+"]/td/input@value");
                                                                  //s[i-1]=selenium.getText("//html/body/div[3]/form/div/div[3]/div[2]/div/div/div/div[5]/div/div[2]/div/table/tbody/tr["+i+"]/td");
                                                                  //s[i-1]=s[i-1].replaceAll(" ","");
                                                                  //s[i-1]=s[i-1].replaceAll(" ","");
                                                                  System.out.println(Mail);
                                                  }
                                                  */
                                                                  
                                                                  for(int i=1;i<=rand2;i++)
                                                                  {              Thread.sleep(5000);
                                                                                  driver.findElement(By.xpath("//html/body/div[3]/form/div/div[3]/div[2]/div/div/div/div/div/div[4]/div/div[2]/table/tbody/tr[1]/td[3]/button")).click();
                                                                                  int count1= driver.findElements(By.xpath("//html/body/div[3]/form/div/div[3]/div[2]/div/div/div/div/div/div[4]/div/div[2]/table/tbody/tr/td")).size();
                                                                                  //selenium.click("id=DeleteNumber_"+s[i-1]);
                                                                                  System.out.println("count"+count1);
                                                                                  Thread.sleep(3000);
                                                                  }
                                                                  driver.findElement(By.id("mainSubmitButton")).click();
                                                                  schk=orderprocess(driver,br);
                                                                  
                                                                  
                                                                  int count= driver.findElements(By.xpath("/html/body/div[3]/form/div/div[3]/div[2]/div/div/div/div/div/div[4]/div/div[2]/table/tbody/tr/td")).size();
                                                                  System.out.println(count);
                                                                  for(int i=1;i<=count;i++)
                                                                  {
                                                                                  //selenium.click("//html/body/div[3]/form/div/div[3]/div[2]/div/div/div/div/div[3]/div/div[2]/table/tbody/tr/td[2]/button");
                                                                                count= driver.findElements(By.xpath("/html/body/div[3]/form/div/div[3]/div[2]/div/div/div/div/div/div[3]/div/div[2]/table/tbody/tr/td[5]/label")).size();
                                                                                  //fin[i-1]=selenium.getText("//html/body/div[3]/form/div/div[3]/div[2]/div/div/div/div[5]/div/div[2]/div/table/tbody/tr["+i+"]/td");
                                                                                  //fin[i-1]=fin[i-1].replaceAll(" ","");
                                                                                  //fin[i-1]=fin[i-1].replaceAll(" ","");
                                                                                  System.out.println(count);
                                                                  }
                                                                  
                                                                
                                                                  if(schk.equals("Pass"))
                                                                  {
                                                                                  statusTracker(br,"Pass","Verify if TNS could be removed", rand2+ " TNS were removed successfully","TNS should be removed successfully");
                                                                                  
                                                                                  count= driver.findElements(By.xpath("/html/body/div[3]/form/div/div[3]/div[2]/div/div/div/div/div/div[4]/div/div[2]/table/tbody/tr/td")).size();
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
                                                                                                  statusTracker(br,"Pass","Verify if TNS are the same after order process", "TNS are the same after order process","TNS should be the same after order process");
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

     public String deleteall(WebDriver driver,String br) throws Exception
                {
                                  String schk="Fail";
                                  System.out.println(schk);
                                
                                                  System.out.println("goin inside notify email"); 
                                                 int count= driver.findElements(By.xpath("/html/body/div[3]/form/div/div[3]/div[2]/div/div/div/div/div/div[4]/div/div[2]/table/tbody/tr")).size();
                                                
                                                  System.out.println("count"+count);
                                                  String s[]=new String[count];
                                                  if (count!=0)
                                                  {
                                                                  System.out.println("inside loop");
                                         
                                                  
                                                  for(int i=1;i<=count;i++)
                                                  {
                                                	      System.out.println("value"+i);  
                                                  driver.findElement(By.xpath("/html/body/div[3]/form/div/div[3]/div[2]/div/div/div/div/div/div[4]/div/div[2]/table/tbody/tr[1]/td[3]/button")).click();
                                                  System.out.println("value1"+i);        
                                                          System.out.println("here");     
                                                                  Thread.sleep(2000);
                                                  }
                                                  
                                                  driver.findElement(By.id("mainSubmitButton")).click();
                                                  schk=orderprocess(driver,br);
                                                  
                                                  if(schk.equals("Pass"))
                                                  {
                                                                  statusTracker(br,"Pass","Verify if all the TNS could be removed", "TNS were removed successfully","TNS should be removed successfully");
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
  public String TNcheck(String br,String ac, String midtn, String lastfour, String check,WebDriver driver) throws Exception
                  {
                                  String schk="Fail";
                                  
                                  int limit= driver.findElements(By.xpath("/html/body/div[3]/form/div/div[3]/div[2]/div/div/div/div/div/div[4]/div/div[2]/table/tbody/tr")).size();
                                  int count;
                                  String tn;
          
                                  count=driver.findElements(By.xpath("/html/body/div[3]/form/div/div[3]/div[2]/div/div/div/div/div/div[4]/div/div[2]/table/tbody/tr")).size();
                                  System.out.println("count"+count);
                                  //do{

                                  if(check.equals("max") && count<5)
                                  {
                                                  String num;
                                                  String rands=randomNO(5,3);
                                                  for(int i=count;i<5;i++)
                                                  {
                                                     String ac2,midtn2,lastfour2;
                                                      ac=randomNO(999,200);
                                                      midtn=randomNO(999,200);
                                                       lastfour= randomNO(9999,1000);
                                                        num=randomNO(14,1);
                                                                                int num1=Integer.parseInt(num);
                                                                                driver.findElement(By.id("txtAreaCode")).clear();
                                                                                driver.findElement(By.id("txtAreaCode")).sendKeys(ac);
                                                                                driver.findElement(By.id("txtExchange")).clear();
                                                                                driver.findElement(By.id("txtExchange")).sendKeys(midtn);
                                                                                driver.findElement(By.id("txtTelNum")).clear();
                                                                                driver.findElement(By.id("txtTelNum")).sendKeys(lastfour);
                                                                     System.out.println("added");
                                                                                driver.findElement(By.id("SelectMobileProvider")).click();
                                                                                String j=randomNO(15,1);
                                                                                int k=Integer.parseInt(j);
                                                                    Thread.sleep(5000);
                                                                    driver.findElement(By.xpath("//html/body/div[3]/form/div/div[3]/div[2]/div/div/div/div/div/div[4]/div/div/div/div/div/ul/li["+k+"]/a")).click();
                                                                                driver.findElement(By.id("btnAddNumbertolist")).click();
                                                                    Thread.sleep(8000);
                                                  }
                                  }
                                  
                                  driver.findElement(By.id("txtAreaCode")).clear();
                                  driver.findElement(By.id("txtAreaCode")).sendKeys(ac);
                                  driver.findElement(By.id("txtExchange")).clear();
                                  driver.findElement(By.id("txtExchange")).sendKeys(midtn);
                                  driver.findElement(By.id("txtTelNum")).clear();
                                  driver.findElement(By.id("txtTelNum")).sendKeys(lastfour);
                                  driver.findElement(By.id("SelectMobileProvider")).click();
                                 
                                  if(check.equals("blank carrier"))
                                  {  
                                	  System.out.println(" blank carrier");
                                    driver.findElement(By.id("btnAddNumbertolist")).click();    
                                    count= driver.findElements(By.xpath("/html/body/div[3]/form/div/div[3]/div[2]/div/div/div/div/div/div[4]/div/div[2]/table/tbody/tr")).size();
                                    do
                                	{
                                    	Thread.sleep(1000);
                                	 System.out.println("new7");	
                                	}while(!(isElementPresent(driver,By.cssSelector("#MobileGridRefresh > #text-message-form > div.label-wrapper.clearfix > span.help-inline.error")) && driver.findElement(By.cssSelector("#MobileGridRefresh > #text-message-form > div.label-wrapper.clearfix > span.help-inline.error")).isDisplayed()));
                                
                                    if((isElementPresent(driver,By.cssSelector("#MobileGridRefresh > #text-message-form > div.label-wrapper.clearfix > span.help-inline.error"))) && (count==limit || check.equals("max")))
                            	     {
                                	
                            		  statusTracker(br,"Pass","Verify if error message is displayed when adding "+check+" email","Error message is displayed: "+driver.findElement(By.cssSelector("#MobileGridRefresh > #text-message-form > div.label-wrapper.clearfix > span.help-inline.error")).getText(),"Error message should be displayed");
                            		  schk="Pass";
                            	      }
                            	   else
                            	    { 
                            		  statusTracker(br,"Fail","Verify if error message is displayed when adding "+check+" email","Error message is not displayed","Error message should be displayed");
                            		  schk="Fail";
                            	     }
                                  
                                  
                                  }
                                  else             
                                   {
                                           System.out.println("Not blank carrier");
                                                
                                               String num;
                                      	  		num=randomNO(14,1);
                                      	  		int num1=Integer.parseInt(num);
                                      	  		
                                      	  	driver.findElement(By.xpath("//html/body/div[3]/form/div/div[3]/div[2]/div/div/div/div/div/div[4]/div/div/div/div/div/ul/li["+num1+"]/a")).click();
                                                             
                                  
                                  driver.findElement(By.id("btnAddNumbertolist")).click();                      
                                    
                                  count= driver.findElements(By.xpath("/html/body/div[3]/form/div/div[3]/div[2]/div/div/div/div/div/div[4]/div/div[2]/table/tbody/tr")).size();
                                  
                                  
                                  do
                              	{Thread.sleep(5000);
                              	 System.out.println("new7");	
                              	}while(!(isElementPresent(driver,By.cssSelector("#text-message-form > span.help-inline.error")) && driver.findElement(By.cssSelector("#text-message-form > span.help-inline.error")).isDisplayed()));
                                  
                                  
                                  if((isElementPresent(driver,By.cssSelector("#text-message-form > span.help-inline.error"))) && (count==limit || check.equals("max")))
                            	  {
                                	
                            		  statusTracker(br,"Pass","Verify if error message is displayed when adding "+check+" email","Error message is displayed: "+driver.findElement(By.cssSelector("#text-message-form > span.help-inline.error")).getText(),"Error message should be displayed");
                            		  schk="Pass";
                            	  }
                            	  else
                            	  { 
                            		  statusTracker(br,"Fail","Verify if error message is displayed when adding "+check+" email","Error message is not displayed","Error message should be displayed");
                            		  schk="Fail";
                            	  }
                                }              
                                                  
                               return schk;
                                  
                  }
                                  
                                 
                 
   public String emailvalidation(WebDriver driver,String br) throws Exception
                  {
                              String schk ="Pass";
                     schk=TNcheck(br,"938","493","4837","blank carrier",driver);
                     Thread.sleep(5000);
                    schk=TNcheck(br,"022","300","4000","first digit 0",driver);
                    Thread.sleep(5000);
                schk=TNcheck(br,"222","000","4000","fourth digit 0",driver);
                Thread.sleep(5000);
                schk=TNcheck(br,"122","300","4000","first digit 1",driver);
                Thread.sleep(5000);
                schk=TNcheck(br,"222","152","4000","fourth digit 1",driver);
                Thread.sleep(5000);
                schk=TNcheck(br,"222","000","4000","fourth digit 0",driver);
                Thread.sleep(5000);
               // schk=TNcheck(br,phoneline_ac,phoneline_midtn,phoneline_lastfour,"existing",driver);
               // Thread.sleep(5000);
                   schk=TNcheck(br,"","","","Empty",driver);
                   Thread.sleep(5000);
                 schk=TNcheck(br,"99","9","99","Invalid",driver);   
                 Thread.sleep(5000);
                 schk=TNcheck(br,"923","329","4399","max",driver);  
                 Thread.sleep(5000);
                driver.findElement(By.xpath("/html/body/div[3]/form/div/div[3]/div[2]/div/div/div/div/div/div[5]/div/button[1]")).click();
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
                                   //  driver.findElement(By.linkText("Settings")).click();
                                     Thread.sleep(5000);          
                                    driver.findElement(By.xpath("(//a[contains(text(),'(edit)')])[2]")).click();
                                    Thread.sleep(2000);
                                  /*  driver.findElement(By.xpath("//html/body/div[3]/form/div/div[2]/a")).click();
                                    System.out.println("here");
                                    Thread.sleep(2000);
                                    driver.findElement(By.id("FriendlyNameDialogContactName")).clear();
                                    System.out.println("here1");
                                    driver.findElement(By.id("FriendlyNameDialogContactName")).sendKeys("");
                                    Thread.sleep(1000);
                                    System.out.println("here2");
                                   driver.findElement(By.xpath("/html/body/div[4]/div/div[3]/button")).click();
                                    Thread.sleep(3000);
                                    System.out.println("here3");
                                    phoneline=driver.findElement(By.id("phonePullDownSelected")).getText();
                        		
                        			System.out.println(phoneline);
                        	          phoneline_ac=phoneline.substring(1,4);
                        	          System.out.println(phoneline_ac);
                        	  		  phoneline_midtn=phoneline.substring(6,9);
                        	  		System.out.println(phoneline_midtn);
                        	  		  phoneline_lastfour=phoneline.substring(10,14);
                        	  		System.out.println(phoneline_lastfour);
                                
                        	  		statusTracker(br,"","Phone Number selected for the Feature Change is : "+phoneline,"","");*/
                                    String schk = "Pass";  
                                    schk=deleteall(driver,br);
                                 /*  WebElement provider=driver.findElement(By.id("SelectMobileProvider"));
                                   Select dropdown=new Select (provider);
                                   dropdown.selectByVisibleText("AT&T");*/
                                   
                                                
                                  
                              
                       			
                       			if(schk.equals("Pass"))
                       				schk=addremove(driver,br);
                       		   if(schk.equals("Pass"))
                       		 schk=emailvalidation(driver,br);
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





