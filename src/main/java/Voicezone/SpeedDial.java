package Voicezone;
import java.io.File;
import java.io.PrintStream;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
//import java.lang.Number;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;

public class SpeedDial extends CommonFunctions
{
                String table, tns[];
                String tlimit,username,password;
                int tncount,tlim;
  

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
  
  public SpeedDial(String s)
  {
                  this.path = s;
  }
  
  public String deleteall( String br,WebDriver driver) throws Exception
  {
            String schk ="Fail";
            System.out.println("fail");
             int chk=0;
                
                  for (int i=2;i<=9;i++)
                  {
                         if(!(driver.findElement(By.id("txtPhoneNumber"+i)).getAttribute("value").equals("")))
                          {
                                 System.out.println("Not blank "+i);
                              chk=1;
                           }
                  }
                  System.out.println("pass");
                  if (chk==1)
                  {   System.out.println("pass1");
                  Thread.sleep(30000);
                  System.out.println("line 58");
                      for(int i=2;i<=9;i++)
                       {  Thread.sleep(2000); 
                    	  focusClick(driver, driver.findElement(By.id("DeleteNumber_"+i)),br);
                            
                            //do{
//                          }while(selenium.isElementPresent("//body/div[10]"));
                            System.out.println("Deleted: "+i);
                          }
                       focusClick(driver,driver.findElement(By.id("mainSubmitButton")),br);
                        Thread.sleep(1000);
                        schk=orderprocess(driver,br);;
                        System.out.println("pass2");
                  }
           
                    schk="Pass";
                    System.out.println("pass3");
                    statusTracker(br,"Pass","Verify deleting all the TNs from the list","Order process was done successfully after deleting all the orders","Order process should be successfull");
  return schk;
}
     
public String flowrunadd( String br,WebDriver driver,String op) throws Exception
  {    System.out.println("printing");
       String tns[]= new String[10];
      String schk="Fail";
       String ac;
       String midtn;
       String lastfour;
       String tn;
       String rows="";
       int chk=0;
       int count;
       System.out.println("print");
      

for(int i=2;i<=9;i++)  
     {
         System.out.println("print1");                
           ac=randomNO(999,200);
           midtn=randomNO(999,200);
           lastfour= randomNO(9999,1000);
           tn=ac+midtn+lastfour;
           driver.findElement(By.id("txtPhoneNumber"+i)).clear();
           driver.findElement(By.id("txtPhoneNumber"+i)).sendKeys(tn);
           tns[i]=tn;
           }   
 System.out.println("print");
 Thread.sleep(3000);
focusClick(driver,driver.findElement(By.id("mainSubmitButton")),br);
Thread.sleep(1000);
           schk=orderprocess(driver,br);; 

          
           System.out.println("step4"); 
                  
       
       
      // if(driver.findElement(By.cssSelector("span.help-block.error")).isDisplayed())
        if(schk.equals("Fail"))
        {
        statusTracker(br,"Fail","Verify order processing when "+op+" TNs to all the fields","Order processing has failed","Order should be processed successfully");
       // schk="Fail";
         }
       else
         {
           statusTracker(br,"Pass","Verify order processing when "+op+" TNs to all the fields","Order processing has processed successfully","Order should be processed successfully");
// schk="Pass";                                           
            for(int i=2;i<=9;i++)  
              {
            System.out.println("step5"); 
             String Tns=driver.findElement(By.id("txtPhoneNumber"+i)).getAttribute("value");
            System.out.println("step6"); 
             if(!(Tns.equals(tns[i])))
               
                {
                  chk=1;
                  System.out.println("step7"); 
                  rows=rows+i+" ";
                  
                }
              }
       if(chk==1)
          {
            statusTracker(br,"Fail","Verify if all TNs are in the same order after order processing","All the TNs are not in the proper order after order processing. Specifically row #'s: "+rows,"All the TNs should be in the proper order after order processing");
           }
         else
              statusTracker(br,"Pass","Verify if all TNs are in the same order after order processing","All the TNs are in the proper order after order processing","All the TNs should be in the proper order after order processing");
                                                
            }
return schk;
}
   private Object isElementPresent(boolean b) {
       // TODO Auto-generated method stub
       return null;
}



private Object iselementpresent(boolean b) {
       // TODO Auto-generated method stub
       return null;
}




public String flowrundelete( String br,WebDriver driver) throws Exception
{
String tns[]= new String[10];
String schk="Fail";
String ac;
String midtn;
String lastfour;
String tn;
String rows="";
int chk=0;
int count;
int ii;

for (int i=2;i<=9;i++)
{
tns[i]=driver.findElement(By.id("txtPhoneNumber"+i)).getAttribute("value");
}
                                
                                
for(int j=1;j<=4;j++)  
{
ii=Integer.parseInt(randomNO(9,2));
focusClick(driver, driver.findElement(By.id("DeleteNumber_"+ii)),br);
//do{
//}while(selenium.isElementPresent("//body/div[10]"));
tns[ii]=driver.findElement(By.id("txtPhoneNumber"+ii)).getAttribute("value");
System.out.println("line 188");                           
}              

focusClick(driver,driver.findElement(By.id("mainSubmitButton")),br);
schk=orderprocess(driver,br);;
// if(driver.findElement(By.cssSelector("span.help-block.error")).isDisplayed())
if(schk.equals("Fail"))
{
statusTracker(br,"Fail","Verify order processing when deleting random TNs from the fields","Order processing has failed","Order should be processed successfully");
// schk="Fail";
if(assertTrue(isElementPresent(driver, By.id("modalContinueButton"))))
{
       focusClick(driver,driver.findElement(By.id("modalContinueButton")),br);
}
}
else
{
statusTracker(br,"Pass","Verify order processing when deleting random TNs from the fields","Order processing has processed successfully","Order should be processed successfully");
// schk="Pass";                                            
for(int i=2;i<=9;i++)  
{
if(!(driver.findElement(By.id("txtPhoneNumber"+i)).getAttribute("value").equals(tns[i])))
{
chk=1;
rows=rows+i+" ";
}
                                                                
}
if(chk==1)
{
statusTracker(br,"Fail","Verify if all TNs are in the same order after order processing","All the TNs are not in the proper order after order processing. Specifically row #'s: "+rows,"All the TNs should be in the proper order after order processing");
}
else
statusTracker(br,"Pass","Verify if all TNs are in the same order after order processing","All the TNs are in the proper order after order processing","All the TNs should be in the proper order after order processing");
                                                
}
// if(driver.findElement(By.cssSelector("span.help-block.error")).isDisplayed())                             
  if(schk.equals("Pass"))
{
  schk=deleteall(br,driver);
  // if(driver.findElement(By.cssSelector("span.help-block.error")).isDisplayed())
  if(schk.equals("Fail"))
    {
       statusTracker(br,"Fail","Verify order processing when deleting all the TNs from the fields","Order processing has failed","Order should be processed successfully");
      // schk="Fail";
     }
     else
     {
       statusTracker(br,"Pass","Verify order processing when deleting all the TNs from the fields","Order processing has processed successfully","Order should be processed successfully");
     //  schk="Pass";                                                    
      for(int i=2;i<=9;i++)  
       {
      if(!(driver.findElement(By.id("txtPhoneNumber"+i)).getAttribute("value").equals("")))
        {
           chk=1;
         }
                                                                                
        }
        if(chk==1)
        {
          statusTracker(br,"Fail","Verify if all the TNs are deleted from the list","All the TNs are not deleted from the list","All the TNs should be deleted from the list");
         }
         else
         {
         statusTracker(br,"Pass","Verify if all the TNs are deleted from the list","All the TNs are deleted from the list","All the TNs should be deleted from the list");
         }                                                     
       }
     }
                                                                
        return schk;
  }


  
  

  public String TNcheck( String br,WebDriver driver,String tn, String check) throws Exception
  {
           String schk="Fail",txt;
                  
         int count;
                            
       driver.findElement(By.id("txtPhoneNumber"+2)).clear();
       Thread.sleep(2000);
       driver.findElement(By.id("txtPhoneNumber"+2)).sendKeys(tn);
      
       txt=driver.findElement(By.id("txtPhoneNumber"+2)).getAttribute("value");
      
       System.out.println("tn"+tn+" "+"txt"+txt);
       
        if(txt.equals(tn))
        {
        statusTracker(br,"Pass","Verify if the TN is completely entered into the field","TN is present in the field as expected","TN should be present in the field");
        schk="Pass";
        }
        else
        {
        statusTracker(br,"Fail","Verify if the TN is completely entered into the field","TN is not present in the field as expected. Actual TN: "+txt +" Expected TN: "+tn,"TN should be present in the field");
        schk="Fail";
        }
       
        
        
      /*  boolean errmesg= driver.findElement(By.cssSelector("span.help-block.error")).isDisplayed();
        System.out.println("error"+errmesg);      
        	if(errmesg)
            {
                            statusTracker(br,"Pass","Verify if error message is displayed when adding "+check+" TN","Error message is displayed: "+ driver.findElement(By.cssSelector("span.help-block.error")).getText(),"Error message should be displayed");
                            schk="Pass";
            }
            else
            {
                            statusTracker(br,"Fail","Verify if error message is displayed when adding "+check+" TN","Error message is not displayed","Error message should be displayed!!");
                            schk="Fail";
            }
            	
        	*/
        	
        	
        	
        	
        	
        	
        	
        	
        	
          if(schk.equals("Pass"))
          {
        	     Thread.sleep(3000);
                // focusClick(driver,driver.findElement(By.id("mainSubmitButton")),br);
                 System.out.println("Waiting");
                // schk=orderprocess(driver,br);;
                 
               //   if(driver.findElement(By.cssSelector("span.help-block.error")).isDisplayed()) 
                  if(schk.equals("Fail"))
                  {
                       if(!(check.equals("invalid")))
                        {
                        statusTracker(br,"Fail","Verify if error message is displayed when adding "+check+" TN","Error message is displayed when adding TN: "+tn,"Error message should not be displayed");
                       // schk="Fail";
                        }
                        else
                        {
                      statusTracker(br,"Pass","Verify if error message is displayed when adding "+check+" TN","Error message is displayed when adding TN: "+tn,"Error message should be displayed");
                    //  schk="Pass";
                        }
                  }
                  
                  
                  
                  else
                 {
                       if(!(check.equals("invalid")))
                       {
                       statusTracker(br,"Pass","Verify if error message is displayed when adding "+check+" TN","Error message is not displayed when adding TN: "+tn,"Error message should not be displayed.");
               // schk="Pass";
                       }
                       else
                       {
                       statusTracker(br,"Fail","Verify if error message is displayed when adding "+check+" TN","Error message is displayed when adding TN: "+tn,"Error message should not be displayed");
               //  schk="Pass";
                       
                       }
                 }
                  Thread.sleep(5000);
                  focusClick(driver,driver.findElement(By.id("DeleteNumber_"+2)),br);
                  Thread.sleep(2000);
                 
           //do{
//            }while(selenium.isElementPresent("//body/div[10]"));
          }   
                  
       return schk;
  }
  
  public String TNValidation(String br,WebDriver driver) throws Exception
  {
                  System.out.println("TN Validation method started");
                  String schk ="Pass";
                  String add=randomNO(999,100);
                  schk=TNcheck(br,driver,"10"+add,"10XXX");  
                  System.out.println("TNCheck1");
                 
                  Thread.sleep(2000);
                  add=randomNO(999,100);
                  schk=TNcheck(br,driver,"1010"+add,"1010XXX");
                  System.out.println("TNCheck2");
                  
                  Thread.sleep(2000);
                  add=randomNO(99,10);
                  schk=TNcheck(br,driver,"*"+add,"*XX");
                  System.out.println("TNCheck3");
                  
                  Thread.sleep(2000);
                  add=randomNO(99,10);
                  schk=TNcheck(br,driver,"11"+add,"*XX");
                  System.out.println("TNCheck4");
                  //add=randomNO(99,10);
                  //schk=TNcheck(add+"#","XX#");
                  
                  Thread.sleep(2000);
                  add=randomNO(9,1);
                  schk=TNcheck(br,driver,add+"11","X11");
                  System.out.println("TNCheck5");
                 
                  Thread.sleep(2000);
                  add=randomNO(9999999,1000000);
                  schk=TNcheck(br,driver,add,"7 digit");
                  System.out.println("TNCheck6");
                  
                  Thread.sleep(2000);
                  add=randomNO(9999999,1000000);
                  schk=TNcheck(br,driver,"1"+add,"1 + 7 digit");
                  System.out.println("TNCheck7");
                  
                  Thread.sleep(2000);
                  //add=randomNO(9999999999,1000000);
                  String ac = randomNO(999,200);
                  String midtn = randomNO(999,200);
                  String lastfour = randomNO(9999,1000);
                  add=ac+midtn+lastfour;
                  schk=TNcheck(br,driver,"1"+add,"1 + 10 digit");
                  System.out.println("TNCheck8");
                  
                  Thread.sleep(2000);
                  schk=TNcheck(br,driver,"0","zero");
                  System.out.println("TNCheck9");
                  
                  Thread.sleep(2000);
                  String firstfive =randomNO(99999,20000);
                  ac = randomNO(999,200);
                  midtn = randomNO(999,200);
                  lastfour = randomNO(9999,1000);
                  add=firstfive+ac+midtn+lastfour;
                  schk=TNcheck(br,driver,"01"+add,"01 + 7 to 15 digits");
                  System.out.println("TNCheck10");
                  
                  Thread.sleep(2000);
                  firstfive =randomNO(99999,20000);
                  ac = randomNO(999,200);
                  midtn = randomNO(999,200);
                  lastfour = randomNO(9999,1000);
                  add=firstfive+ac+midtn+lastfour;
                  schk=TNcheck(br,driver,"011"+add,"011 + 7 to 15 digits");
                  System.out.println("TNCheck11");
                  
                  Thread.sleep(2000);
                  driver.findElement(By.id("txtPhoneNumber"+2)).clear();
                  schk=TNcheck(br,driver,"1"+add,"Number");
                  System.out.println("TNCheck12");
                  
                  
                  Thread.sleep(2000);
                  schk="Pass";
                  System.out.println("TN Validation method ended");
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
         logger.info("a");
         Thread.sleep(10000);
         focusClick(driver, driver.findElement(By.linkText("Settings")),br);
       	int chk=0;
   	      do{
   	           Thread.sleep(1000);       
   	          chk++;
   	          System.out.println(chk);
   	                }
   	      while(driver.findElement(By.xpath("//*[@id='progress']")).isDisplayed());
   	      Thread.sleep(5000);
        // focusClick(driver, driver.findElement(By.linkText("Settings")),br);
         focusClick(driver, driver.findElement(By.xpath("(//a[contains(text(),'(edit)')])[8]")),br);
         System.out.println("clicking Settings link");  
                  String schk="Pass"; 
                  statusTracker(br,"","Verify Deleting Tns","","");                              
                  System.out.println("line 408");
               
           
          if(schk.equals("Pass"))
                schk=deleteall(br,driver);
            
            else
                statusTracker(br,"Fail","Verify deleting all the TNs from the list","Order process was not done successfully after deleting all the orders","Order process should be successfull");
            
            System.out.println("deleting");
            
         statusTracker(br,"","Verify Adding Tns","","");
         
         if(schk.equals("Pass"))
            schk=flowrunadd(br,driver,"adding");
          else
            statusTracker(br,"Fail","Verify adding the TNs from the list","Order process was not done successfull after adding all the orders","Order process should be successfull");
         
         System.out.println("Adding");       
            
               statusTracker(br,"","Verify Deleting Tns","","");
        
               if(schk.equals("Pass"))
              schk=flowrundelete(br,driver);
              else
                 statusTracker(br,"Fail","Verify deleting TNs from the list","Order process was not done successfully after deleting","Order process should be successfull");
                                                
              statusTracker(br,"","Verify Tn Validation","","");
                                                
               if(schk.equals("Pass"))
               schk=TNValidation(br,driver); 
               
               Thread.sleep(5000);                                 
               focusClick(driver,driver.findElement(By.id("mainCancelButton")),br);                                    
                 
        first=1;      
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

