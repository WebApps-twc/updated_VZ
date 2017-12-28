package Voicezone;

import java.io.File;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.ui.Select;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;

import org.openqa.selenium.*;

public class CallForwardingBusy extends CommonFunctions {
                
                String table, tns[];
                String tlimit,username,pwd;
                int tncount;                                                                                   
                String phoneline,phoneline_ac,phoneline_midtn,phoneline_lastfour,Acccode;
                int rank[]= new int[50];                
                int c_sequence;
                String name_for_rank[]= new String[40];
                String price, rank_channels;

public CallForwardingBusy(String path) {
                                this.path = path;
                }

  public String flowrun(String status, String status1,WebDriver driver,String br) throws Exception
  {
	  	String schk="";
	  	String from="",to="";
	  	  if(status.equals("on"))
	  	   {
             from =status; to=status1;
 
             //String from1=status,to1=status1;
           	 //String status3="off";
           	 focusClick(driver,driver.findElement(By.id("CFBActivatedOff")),br);           	 
             schk=orderprocess(driver,br);

             if(schk.equals("Fail"))
             {
                    statusTracker(br,"Fail","Verify order processing when switching from "+from+" to "+to,"Order processing has failed","Order should be processed successfully");
                    schk="Fail";
             }
             else
             {
                    statusTracker(br,"Pass","Verify order processing when switching from "+from+" to "+to,"Order processing has completed successfully","Order should be processed successfully");
                    schk="Pass";
             }
              
             if(schk.equals("Pass"))
             {
              from =status1; to=status;
              focusClick(driver,driver.findElement(By.id("CFBActivatedOn")),br);
              focusClick(driver,driver.findElement(By.id("CFBStatus")),br);
             
              driver.findElement(By.id("txtAreaCode1")).clear();
     	      driver.findElement(By.id("txtAreaCode1")).sendKeys(phoneline_ac);
     	      driver.findElement(By.id("txtExchange1")).clear();
     	      driver.findElement(By.id("txtExchange1")).sendKeys(phoneline_midtn);
       	      driver.findElement(By.id("txtTelNum1")).clear();
       	      String num=randomNO(3333,6666);
       	      driver.findElement(By.id("txtTelNum1")).sendKeys(num);    
       	      Thread.sleep(5000);
              focusClick(driver,driver.findElement(By.id("mainSubmitButton")),br);             
              schk=orderprocess(driver,br); 
             
              if(schk.equals("Fail"))
              {
                     statusTracker(br,"Fail","Verify order processing when switching from "+from+" to "+to,"Order processing has failed","Order should be processed successfully");
                     schk="Fail";
              }
              else
              {
                     statusTracker(br,"Pass","Verify order processing when switching from "+from+" to "+to,"Order processing has completed successfully","Order should be processed successfully");
                     schk="Pass";
              }
             }
             
	  	   }
       else 
      	  {
             from =status1; to=status;
            // String schk=EditTN();
             focusClick(driver,driver.findElement(By.id("CFBActivatedOn")),br);
             focusClick(driver,driver.findElement(By.id("CFBStatus")),br);
           	 Thread.sleep(4000); 
           	 driver.findElement(By.id("txtAreaCode1")).clear();
    	     driver.findElement(By.id("txtAreaCode1")).sendKeys(phoneline_ac);
    	     driver.findElement(By.id("txtExchange1")).clear();
    	     driver.findElement(By.id("txtExchange1")).sendKeys(phoneline_midtn);
      	     driver.findElement(By.id("txtTelNum1")).clear();
      	    String num=randomNO(3333,6666);
      	     driver.findElement(By.id("txtTelNum1")).sendKeys(num);
      	     focusClick(driver,driver.findElement(By.id("mainSubmitButton")),br);
             schk=orderprocess(driver,br);

             if(schk.equals("Fail"))
             {
                    statusTracker(br,"Fail","Verify order processing when switching from "+from+" to "+to,"Order processing has failed","Order should be processed successfully");
                    schk="Fail";
             }
             else
             {
                    statusTracker(br,"Pass","Verify order processing when switching from "+from+" to "+to,"Order processing has completed successfully","Order should be processed successfully");
                    schk="Pass";
             }
          
             if(schk.equals("Pass"))
             {
              from =status; to=status1;
              focusClick(driver,driver.findElement(By.id("CFBActivatedOff")),br);           	 
              schk=orderprocess(driver,br);

              if(schk.equals("Fail"))
               {
                    statusTracker(br,"Fail","Verify order processing when switching from "+from+" to "+to,"Order processing has failed","Order should be processed successfully");
                    schk="Fail";
               }
              else
               {
                    statusTracker(br,"Pass","Verify order processing when switching from "+from+" to "+to,"Order processing has completed successfully","Order should be processed successfully");
                    schk="Pass";
               }
              }
           }  
	  	  return schk;
  }
   
 public String voicemail(WebDriver driver,String status,String status1,String br) throws Exception
 {
	 String schk="";
	 focusClick(driver,driver.findElement(By.id("CFBActivatedOn")),br);
	 boolean status2 = driver.findElement(By.id("CFBVMStatus")).isSelected();
     if(status2)
      {
    	 String from1=status1,to1=status;
   	     //String status3="off";
    	 focusClick(driver,driver.findElement(By.id("CFBStatus")),br);
   	     driver.findElement(By.id("txtAreaCode1")).clear();  
   	     driver.findElement(By.id("txtAreaCode1")).sendKeys(phoneline_ac);
   	     driver.findElement(By.id("txtExchange1")).clear();
   	     driver.findElement(By.id("txtExchange1")).sendKeys(phoneline_midtn);
	     driver.findElement(By.id("txtTelNum1")).clear();
	     String num=randomNO(3333,6666);
	     driver.findElement(By.id("txtTelNum1")).sendKeys(num);
	     focusClick(driver,driver.findElement(By.id("mainSubmitButton")),br);
         schk=orderprocess(driver,br);

         if(schk.equals("Fail"))
          {
               statusTracker(br,"Fail","Verify order processing when switching voicemail from "+from1+" to "+to1,"Order processing has failed","Order should be processed successfully");
               schk="Fail";
          }
        else
          {
               statusTracker(br,"Pass","Verify order processing when switching voicemail from "+from1+" to "+to1,"Order processing has completed successfully","Order should be processed successfully");
               schk="Pass";
          }
         
         if(schk.equals("Pass"))
         {
        	 from1=status;to1=status1;
        	 focusClick(driver,driver.findElement(By.id("CFBVMStatus")),br);        	 
        	 focusClick(driver,driver.findElement(By.id("mainSubmitButton")),br);
             schk=orderprocess(driver,br);

             if(schk.equals("Fail"))
              {
                   statusTracker(br,"Fail","Verify order processing when switching voicemail from "+from1+" to "+to1,"Order processing has failed","Order should be processed successfully");
                   schk="Fail";
              }
            else
              {
                   statusTracker(br,"Pass","Verify order processing when switching voicemail from "+from1+" to "+to1,"Order processing has completed successfully","Order should be processed successfully");
                   schk="Pass";
              }
         }
      }
     else
      {
    	 String from1=status,to1=status1;
    	 focusClick(driver,driver.findElement(By.id("CFBVMStatus")),br);
    	 focusClick(driver,driver.findElement(By.id("mainSubmitButton")),br);
    	 schk=orderprocess(driver,br);
    	 if(schk.equals("Fail"))
         {
              statusTracker(br,"Fail","Verify order processing when switching voicemail from "+from1+" to "+to1,"Order processing has failed","Order should be processed successfully");
              schk="Fail";
         }
       else
         {
              statusTracker(br,"Pass","Verify order processing when switching voicemail from "+from1+" to "+to1,"Order processing has completed successfully","Order should be processed successfully");
              schk="Pass";
         }
    	 
    	 if(schk.equals("Pass"))
         {
    		 from1=status1;to1=status;
    		 focusClick(driver,driver.findElement(By.id("CFBStatus")),br);
       	     driver.findElement(By.id("txtAreaCode1")).clear();  
       	     driver.findElement(By.id("txtAreaCode1")).sendKeys(phoneline_ac);
       	     driver.findElement(By.id("txtExchange1")).clear();
       	     driver.findElement(By.id("txtExchange1")).sendKeys(phoneline_midtn);
    	     driver.findElement(By.id("txtTelNum1")).clear();
    	     String num=randomNO(3333,6666);
    	     driver.findElement(By.id("txtTelNum1")).sendKeys(num);
    	     focusClick(driver,driver.findElement(By.id("mainSubmitButton")),br);
             schk=orderprocess(driver,br);

             if(schk.equals("Fail"))
              {
                   statusTracker(br,"Fail","Verify order processing when switching voicemail from "+from1+" to "+to1,"Order processing has failed","Order should be processed successfully");
                   schk="Fail";
              }
            else
              {
                   statusTracker(br,"Pass","Verify order processing when switching voicemail from "+from1+" to "+to1,"Order processing has completed successfully","Order should be processed successfully");
                   schk="Pass";
              }
         }
    	 
       }
	 return schk;
 }
  
  public String EditTN(WebDriver driver,String br) throws Exception
  {
	  //String schk="pass";
      focusClick(driver,driver.findElement(By.id("CFBActivatedOn")),br);
      focusClick(driver,driver.findElement(By.id("CFBStatus")),br);
      
        driver.findElement(By.id("txtAreaCode1")).clear();
	    driver.findElement(By.id("txtAreaCode1")).sendKeys(phoneline_ac);
	    driver.findElement(By.id("txtExchange1")).clear();
	    driver.findElement(By.id("txtExchange1")).sendKeys(phoneline_midtn);
	    driver.findElement(By.id("txtTelNum1")).clear();
	    driver.findElement(By.id("txtTelNum1")).sendKeys(randomNO(9999,1000));
	    focusClick(driver,driver.findElement(By.id("mainSubmitButton")),br);
		String schk=orderprocess(driver,br);
		if(schk.equals("Fail"))
        {
               statusTracker(br,"Fail","Verify order processing for editing TN ","Order processing has failed","Order should be processed successfully");
        }
        else
        {
               statusTracker(br,"Pass","Verify order processing for editing TN ","Order processing has completed successfully","Order should be processed successfully");
               schk="Pass";
        }
		return schk;
  }
  
  public String Drpdwn(WebDriver driver,String br) throws Exception
  {
	    focusClick(driver,driver.findElement(By.id("CFBStatusdrop")),br);
		int listcount=driver.findElements(By.xpath("//html/body/div[3]/form/div/div[3]/div[2]/div/div/div/div/div[4]/div/div/div[8]/div/div/ul/li")).size();
		System.out.println(listcount);
		String text[]=new String[listcount+1];
		String text2[]=new String[listcount+1];
		//gets all the values from the dropdown 
			for(int i=1;i<=listcount;i++)
			{
	            WebElement labelNode = driver.findElement(By.xpath("//html/body/div[3]/form/div/div[3]/div[2]/div/div/div/div/div[4]/div/div/div[8]/div/div/ul/li["+i+"]//a")); 
	            String labelNodeText = (String) ((JavascriptExecutor)driver).executeScript("return arguments[0].innerHTML",labelNode);
	            labelNodeText=labelNodeText.substring(21,labelNodeText.length()-16);
	            labelNodeText=labelNodeText.replaceAll("\\n","");
	            text[i]=labelNodeText;
	            System.out.println("String: "+labelNodeText);   
	            System.out.println("TN "+text[i]);
			}

			focusClick(driver,driver.findElement(By.id("CFBStatusdrop")),br);
			focusClick(driver,driver.findElement(By.id("ddlCFBnum")),br);
			System.out.println("yess1");
			String numtext=randomNO(listcount,1);
			System.out.println("yess2");
			int num=Integer.parseInt(numtext);
			System.out.println("yess3");
			System.out.println("link="+text[num]);
			focusClick(driver,driver.findElement(By.linkText(text[num])),br);
			
			focusClick(driver,driver.findElement(By.id("mainSubmitButton")),br);
			String schk=orderprocess(driver,br);
			if(schk.equals("Fail"))
            {
                   statusTracker(br,"Fail","Verify order processing with dropdown","Order processing has failed","Order should be processed successfully");
                   schk="Fail";
            }
            else
            {
                   statusTracker(br,"Pass","Verify order processing with dropdown","Order processing has completed successfully","Order should be processed successfully");
                   schk="Pass";
            }			
			Thread.sleep(2000);
			return schk;
  }
 
  public String TNcheck(String ac, String midtn, String lastfour, String check,WebDriver driver,String br) throws Exception
  {
         String schk="Fail";
         
         String tn;
         if(check.equals("no dropdown"))
         {
        	 focusClick(driver,driver.findElement(By.id("CFBStatusdrop")),br);
        	 Thread.sleep(1000);
        	 //selenium.click("id=ddlCFUnum");
         }
         else
         { 
        	 driver.findElement(By.id("txtAreaCode1")).clear();
        	 driver.findElement(By.id("txtAreaCode1")).sendKeys(ac);
     	     driver.findElement(By.id("txtExchange1")).clear();
     	     driver.findElement(By.id("txtExchange1")).sendKeys(midtn);
     	     driver.findElement(By.id("txtTelNum1")).clear();
     	     driver.findElement(By.id("txtTelNum1")).sendKeys(lastfour);
     	     focusClick(driver,driver.findElement(By.id("mainSubmitButton")),br);
             schk=orderprocess(driver,br);
	         Thread.sleep(1000);
         }
         focusClick(driver,driver.findElement(By.id("mainSubmitButton")),br);
         
         tn=ac+midtn+lastfour;
         Thread.sleep(6000);
     
         if(driver.findElements(By.cssSelector("span.help-block.error")).size()!=0)
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
  //TN Validation
  public String TNValidation(WebDriver driver,String br) throws Exception
  {
         String schk ="Pass";
         schk=TNcheck("022","300","4000","first digit 0",driver,br);
         schk=TNcheck("222","000","4000","fourth digit 0",driver,br);
         schk=TNcheck("122","300","4000","first digit 1",driver,br);
         schk=TNcheck("222","152","4000","fourth digit 1",driver,br);
         schk=TNcheck("900","000","4000","first digits 900",driver,br);
         schk=TNcheck("976","000","4000","first digist 976",driver,br);
         schk=TNcheck("","","","blank",driver,br);
         //schk=TNcheck(ac1,midtn1,lastfour1,"existing");
         schk=TNcheck("99","9","99","Invalid",driver,br);
         schk=TNcheck(phoneline_ac,phoneline_midtn,phoneline_lastfour,"self",driver,br);
         schk=TNcheck("","","","no dropdown",driver,br);
         
         schk="Pass";
         return schk;
  }
  
  //Executing function
  public void execute(String br, WebDriver driver, String url, int loc, String name1) throws Exception {
      
      Feature_Name="CFB";
      int tlim=3;
      //String status1="",state = "Fail";
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
    	  //selenium.open("https://voice.atgeng.timewarnercable.com/VZTTEST1");            
           // focusClick(driver,driver.findElement(By.linkText("Settings")),br);
              		      Thread.sleep(5000);
            focusClick(driver,driver.findElement(By.xpath("(//a[contains(text(),'(edit)')])[4]")),br);
            Thread.sleep(5000);
        	focusClick(driver,driver.findElement(By.xpath(".//*[@id='tab_3']/a")),br);
        	Thread.sleep(5000);
  	  
  	         focusClick(driver, driver.findElement(By.xpath(".//*[@class='card poping buttonLink']")),br);  
  	         Thread.sleep(5000);
  	     	driver.findElement(By.id("FriendlyNameDialogContactName")).clear();
			  do{
		          Thread.sleep(1000);
		          System.out.println("chk value is" +chk);
		          chk++;
		          }while( !(driver.findElements(By.id("FriendlyNameDialogContactName")).size()>0) && chk<30);
  	     	 focusClick(driver,driver.findElement(By.cssSelector("div.modal-footer > button.btn.btn-primary")),br);
  	     	Thread.sleep(2000);
			phoneline=driver.findElement(By.id("phonePullDownSelected")).getText();	
			logger.info(phoneline);
	          phoneline_ac=phoneline.substring(1,4);
	          System.out.println(phoneline_ac);
	  		  phoneline_midtn=phoneline.substring(6,9);
	  		  System.out.println(phoneline_midtn);
	  		  phoneline_lastfour=phoneline.substring(10,14);
	  		  System.out.println(phoneline_lastfour);
        
                 String status= driver.findElement(By.id("CFBActivatedOn")).getAttribute("class");
                 System.out.println("class is : "+status);
                 String status1="on";String schk="";  
                     //String status="off";
                     if(status.equals("btn active"))
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
                                                          
                    // String from="",to="";                                        
                     schk=flowrun(status, status1,driver,br);                    
                     System.out.println("one transation sucess"+schk);
                                        
                     if(schk.equals("Pass"))
                     {
                    	 System.out.println("check transation success");
                     
                    	  status= driver.findElement(By.id("CFBActivatedOn")).getAttribute("class");
                    	  System.out.println("class is : "+status);
                    	  if(status.equals("btn"))
                    	  {
                    		  status1="on";
                              status="off";
                              focusClick(driver,driver.findElement(By.id("CFBActivatedOn")),br);
                              schk=Drpdwn(driver,br);
                              if(schk.equals("Pass"))
                            	  schk=voicemail(driver,status1,status,br);
                              
                    		  if(schk.equals("Pass"))
                              {
                    			   EditTN(driver,br);
                                   statusTracker(br,"","Verify TN Validation","","");
                                   schk=TNValidation(driver,br);
                                    //statusTracker("","pass","","");
                              }
                    	  }
                    	  else
                    	  {
                    		  schk=Drpdwn(driver,br);
                              if(schk.equals("Pass"))
                            	  schk=voicemail(driver,status1,status,br);
                              
                    		  if(schk.equals("Pass"))
                              {
                    			  EditTN(driver,br);
                                  statusTracker(br,"","Verify TN Validation","","");
                                  schk=TNValidation(driver,br);
                                 // statusTracker(br,"","pass","","");
                              }
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


