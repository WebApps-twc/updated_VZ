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

public class VoicemailTranscription extends CommonFunctions {
                
                String table, tns[];
    String tlimit,username,pwd;
    int tncount;                                                    
                                
    String phoneline,phoneline_ac,Acccode;
                int rank[]= new int[50];
                

                int c_sequence;
                String name_for_rank[]= new String[40];
                String price, rank_channels;

                public VoicemailTranscription(String path) {
                                this.path = path;
                }
                

  public String flowrun(WebDriver driver, String br) throws Exception
  {
                  Boolean status = driver.findElement(By.id("VoicemailToText")).isSelected();
                  System.out.println("Iam running");
                  System.out.println("Initial state: "+status);
                  Boolean status1=false;
                  String schk="Fail";
                  if (status.equals(false)) { 
                  status1=true;
                  System.out.println("after state: "+status1);
                  System.out.println("yessssssssssssssssssssssssssssssssssssssssssssssssssssssssssss");
                  driver.findElement(By.id("VoicemailToText")).click();
                  System.out.println("after state status: "+status);
                  System.out.println("Before state status1: "+status1);
                  driver.findElement(By.id("mainSubmitButton")).click();
                  schk=orderprocess(driver,br);
                  }
                  
                  if (status.equals(true)) {
                      status1=false;
        System.out.println("after state: "+status1);
        System.out.println("yessssssssssssssssssssssssssssssssssssssssssssssssssssssssssss");
        driver.findElement(By.id("VoicemailToText")).click();
        driver.findElement(By.id("mainSubmitButton")).click();
        schk=orderprocess(driver,br);
        System.out.println("after state status: "+status);
        System.out.println("Before state status1: "+status1);
        }
                  
               
                             /*     if((driver.findElements(By.cssSelector("tbody > tr > td")).size()== 0))
                                  {
                                                  System.out.println("aaaaa");
                                                  driver.findElement(By.id(("txtEmailAddress"))).sendKeys("test@test.tst");
                                                  driver.findElement(By.id("btnAddtolist")).click();
                                                
                                                  System.out.println("check1");
                                  }
                                
                                  
                  if(driver.findElements(By.cssSelector("#MobileGridRefresh > div.TNtableAAMember > table.table.table-striped > tbody > tr > td")).size()== 0)
                                  {
                                                  System.out.println("bbbbbbbb");
                                                  driver.findElement(By.id("txtAreaCode")).sendKeys("999");
                                                  driver.findElement(By.id("txtExchange")).sendKeys("999");
                                                  driver.findElement(By.id("txtTelNum")).sendKeys("9999");
                                                  driver.findElement(By.id("SelectMobileProvider")).click();
                                                  driver.findElement(By.linkText("AT&T")).click();
                                                  Thread.sleep(3000);
                                                  System.out.println("check2");
                                                  System.out.println("yhdytuyyu");
                                  }
                                  if((driver.findElements(By.cssSelector("tbody > tr > td")).size()!= 0) && (driver.findElements(By.cssSelector("#MobileGridRefresh > div.TNtableAAMember > table.table.table-striped > tbody > tr > td")).size()!= 0))
                                  {
                                                  Boolean stat1 = driver.findElement(By.xpath("(//input[@type='checkbox'])[3]")).isEnabled();
                                                  System.out.println("stat1: "+stat1);
                                                  Boolean stat2 = driver.findElement(By.xpath("/html/body/div[3]/form/div/div[3]/div[2]/div/div/div/div/div/div[3]/div/div[2]/table/tbody/tr[2]/td[6]/label/input")).isEnabled();
                                                  System.out.println("stat2: "+stat2);
                                                  if(!status1)
                                                                  if(stat1 || stat2)
                                                                  {
                                                                                  statusTracker(br,"Fail","Verify whether the checkboxes are disabled","Transcription checkbox is enabled","Transcription checkbox should be disabled");
                                                                                  schk="Fail";
                                                                  }
                                                                  else
                                                                  {
                                                                                  statusTracker(br,"Pass","Verify whether the checkboxes are disabled","Transcription checkbox is disabled","Transcription checkbox should be disabled");
                                                                                  schk="Pass";
                                                                  }
                                                  if(status1)
                                                                  if(!(stat1) || !(stat2))
                                                                  {
                                                                                  statusTracker(br,"Fail","Verify whether the checkboxes are enabled","Transcription checkbox is disabled","Transcription checkbox should be enabled");
                                                                                  schk="Fail";
                                                                  }
                                                                  else
                                                                  {
                                                                                  statusTracker(br,"Pass","Verify whether the checkboxes are enabled","Transcription checkbox is enabled","Transcription checkbox should be enabled");
                                                                                  schk="Pass";
                                                                  }
                                  }
                                  System.out.println("check3");
                                  Thread.sleep(2000);
                                  driver.findElement(By.id("mainSubmitButton")).click();
                                  schk=orderprocess(driver,br);
                                  Thread.sleep(2000);
                                  if(schk.equals("Pass"))
                                  {
                                                  Boolean stat1 = driver.findElement(By.xpath("(//input[@type='checkbox'])[3]")).isEnabled();
                                                  System.out.println("stat1: "+stat1);
                                                  Thread.sleep(2000);
                                                  
                                                
                                  Boolean stat2 = driver.findElement(By.xpath("/html/body/div[3]/form/div/div[3]/div[2]/div/div/div/div/div/div[3]/div/div[2]/table/tbody/tr[2]/td[6]/label/input")).isEnabled();
                                                  System.out.println("stat2: "+stat2);
                                                  //Boolean stat1 = selenium.isEditable("id=TranscribeCheck0");
                                                  //Boolean stat2 = selenium.isEditable("id=TranscribeCheckMobile0");
                                                  if(!status1)
                                                                  if(stat1 || stat2)
                                                                  {
                                                                                 statusTracker(br,"Fail","Verify whether the checkboxes are disabled after order processing","Transcription checkbox is enabled","Transcription checkbox should be disabled");
                                                                                  schk="Fail";
                                                                  }
                                                                  else
                                                                  {
                                                                                  statusTracker(br,"Pass","Verify whether the checkboxes are disabled after order processing","Transcription checkbox is disabled","Transcription checkbox should be disabled");
                                                                                  schk="Pass";
                                                                  }
                                                  if(status1)
                                                                  if(!(stat1) || !(stat2))
                                                                  {
                                                                                  statusTracker(br,"Fail","Verify whether the checkboxes are enabled after order processing","Transcription checkbox is disabled","Transcription checkbox should be enabled");
                                                                                  schk="Fail";
                                                                  }
                                                                  else
                                                                  {
                                                                                  statusTracker(br,"Pass","Verify whether the checkboxes are enabled after order processing","Transcription checkbox is enabled","Transcription checkbox should be enabled");
                                                                                  schk="Pass";
                                                                  }
                                  }
                                  else
                                  {
                                                  statusTracker(br,"Fail","Verify the order processing","Order processing has failed","Order processing should pass");
                                                  schk="Fail";
                                  }
  */
                  return schk;
  }
  
  
  

private void sendKeys(String string) {
                // TODO Auto-generated method stub
                
}



private Object isElementPresent(String string) {
                // TODO Auto-generated method stub
                return null;
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
                    
                                //driver.findElement(By.linkText("Settings")).click();
                                
                    driver.findElement(By.xpath("(//a[contains(text(),'(edit)')])[2]")).click();
                
                                                Thread.sleep(2000);
                                                do{
                                                }while(assertTrue(isElementPresent("//body/div[11]")));
                                                Thread.sleep(2000);
                                                
                                                //selenium.click("link=VoiceMail Non-Pin Settings");
                                                //selenium.waitForPageToLoad(tlimit);
                                                
                                                //String status = selenium.getValue("id=Activated");
                                                String schk = "Pass";
                                                System.out.println("run");
                                                String status = driver.findElement(By.id("VoicemailToText")).getAttribute("value");
                                                System.out.println("icame");
                                                schk=flowrun(driver,br);
                                                System.out.println("done");
                                                if(schk.equals("Pass"))
                                                                schk=flowrun(driver,br);
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

