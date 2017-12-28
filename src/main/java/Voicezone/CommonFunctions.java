package Voicezone;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.text.*;
import java.util.*;
import java.util.NoSuchElementException;

import jxl.read.biff.BiffException;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.format.*;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.Label;
import jxl.write.WritableSheet;



import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.ui.Select;
//import org.apache.bcel.generic.Select;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.openqa.selenium.interactions.Actions;

public abstract class CommonFunctions {
	
	public String previousURL="";
	public static int mode_config;
	public static String parameter1;
	public static String parameter2;
	
	public static Logger logger = LoggerFactory.getLogger(CommonFunctions.class);
	public static String Grid_Status;
	public static int bl,first_tc;
	public static int l_browser[] = new int[10];
	public static String browser[] = new String[20];
	public static Double time[] = new Double[20];
	public static String result_array[][] = new String[100][10000];
	public static int counter_result_sheet[] = new int[10];
	public static int arrcount[] = new int[10];
	public static int ro[]=new int [10];
	public static int pass_br_wise[] = new int[10];
	ArrayList<Result_bean> a = new ArrayList<Result_bean>();
	public static Map br_specific = new HashMap<String, List<Result_bean>>();
	public static int counter_browser = 0, k_browser = 0;
	public static String input_sheet="Input_Sheet.xls";
	public static String gr,Sheet_name;
	public static int counter_result_initial[] = new int[10];
	public static int counter_result_final[] = new int[10];
	public static int total[] = new int[10];
	public static int no_run[] = new int[10];
	public String TestCompleted = "No";
	public static File dir1,dir_offer1,dir_offer2,Offer1,Offer2;
	public static String spath,fname,offer_rel1,offer_com ;
	public static Properties path_config;
	public static String inputfile;
	public static String outputfile;
	public static File data11;
	public static File data;
	public static WorkbookSettings ws11;
	public static Workbook wb11;
	public static Sheet sheet11 ;
	public static int black[] = new int[10];
	public static int Test_called = 0;
	public static int Test_called_ff = 0;
	public static int Test_called_chrome = 0;
	public static int Test_called_ie = 0;
	public static int Test_called_sf = 0;
	public static String current_browser="";
	public static String name1;
	public static String exec1;
	public static String sheetname;
	
	public String browserused, reg;
	public static String region;
	
	String table, tns[];
    String tlimit,username,pwd;		
    int tncount;
    public static int envvalue =0;
	
	public static boolean pass_screenshot_required;
	
	public static String result[] = new String[150];
	public static String result_scenario[] = new String[8000];
	public static String result_actual[] = new String[8000];
	public static String result_expected[] = new String[8000];
	public static int first = 0;
	public String path;
	public static WebDriver drive;
	public String path1;
	public static int screenshot = 1;
	public static int int_screenshot = 1;

	DecimalFormat df = new DecimalFormat("#0.00");
	public String Feature_Name;
	public String path2;
	int z=0;
    int f=0;

	public String Overall_Path ="";
	
	//***********************Properties file Variables**************************
	public static FileInputStream file_Stream;
	public static Properties ACR;
	public static Properties GPS;
	//public static Properties CFU;
	
	 public void Frndlyname(WebDriver driver,String but,String sts,String fld1,String fld2,String fld3,String icn,String nme,String tn1,String tn2,String tn3,String br) throws Exception
	  {      
		 focusClick(driver,driver.findElement(By.id(but)),br);
		 focusClick(driver,driver.findElement(By.id(sts)),br);
	           
	             driver.findElement(By.id(fld1)).sendKeys(tn1);
	             driver.findElement(By.id(fld2)).sendKeys(tn2);
	             driver.findElement(By.id(fld3)).sendKeys(tn3);
	             focusClick(driver,driver.findElement(By.id("mainSubmitButton")),br);
	              orderprocess(driver,br);
		        
		         Thread.sleep(1000);           
	              String name="Home";    
	              focusClick(driver,driver.findElement(By.xpath(icn)),br);
	              
	              do
	          	{Thread.sleep(1000);
	          	 	
	          	}while(!(isElementPresent(driver,By.xpath("//html/body/div[4]/div")) && driver.findElement(By.xpath("//html/body/div[4]/div")).isDisplayed()));
	              
	              
	              driver.findElement(By.id("FriendlyNameDialogContactName")).clear();
	              driver.findElement(By.id("FriendlyNameDialogContactName")).sendKeys(name);
	              focusClick(driver,driver.findElement(By.cssSelector("div.modal-footer > button.btn.btn-primary")),br);
	              
	              do{
	              }while(drive.findElements(By.xpath("//body/div[10]")).size()!=0);
	              
	              String name1=driver.findElement(By.xpath(nme)).getText();
	              System.out.println("name:"+name+"name1:"+name1);
	              
	              if(name.equals(name1))
	              {
	              statusTracker(br,"Pass","Verify if the friendly name updated properly:"+name,"The friendly name updated correctly: "+name1,"The name should update correctly");                   
	              }
	              else
	              {
	              statusTracker(br,"Fail","Verify if the friendly name updated properly:"+name,"The friendly name not updated correctly: "+name1,"The name should update correctly");
	              }
	  }
	
	private boolean isElementPresent(WebDriver driver, By xpath) {
		// TODO Auto-generated method stub
		return false;
	}

	public String phonenum(WebDriver driver,String br) throws Exception
	{
		String TN = "";
		focusClick(driver,driver.findElement(By.xpath("//html/body/div[3]/form/div/div[2]/a")),br);
		do{
			
		}while(driver.findElements(By.xpath("//html/body/div[4]/div")).size()==0);
		if(driver.findElements(By.xpath("//html/body/div[4]/div")).size()!=0){
		   TN=driver.findElement(By.xpath(".//*[@id='friendlyNameDialog']/div[2]/form/div/span[1]/span")).getText();
		   focusClick(driver,driver.findElement(By.xpath(".//*[@id='friendlyNameDialog']/div[3]/a")),br);
		}
				
		return TN;		
	}
	
	public void focusClick(WebDriver driver,WebElement elementToClick,String br) throws Exception
	{
		System.out.println("element to click"  +elementToClick);
		
		
		Actions actions = new Actions(driver);
		if(br.equals("chrome"))
		{
				//actions.moveToElement(elementToClick).click().perform();
								
				try{
				Thread.sleep(500);
				((JavascriptExecutor) driver).executeScript("window.scrollTo(0,"+elementToClick.getLocation().y+")");
				}
				catch(Exception e){}
				elementToClick.click();
		}
		else if(br.equals("IE"))
		{
			
			//previousURL = driver.getCurrentUrl();
			actions.moveToElement(elementToClick);
			System.out.println("Here2");
			elementToClick.click();
/*			if(waityesno)
			{
				
				WebDriverWait wait = new WebDriverWait(driver, 30);
			    ExpectedCondition e = new ExpectedCondition<Boolean>() {
			          public Boolean apply(WebDriver d) {
			        	  
			        	if(previousURL.equals(d.getCurrentUrl()))
			        	{
			        		System.out.println("here here");
			        		  return false;
			        	}
			        	else
			        	{
			        		System.out.println("not here here");
			        		return true;
			        	}
			        	
			          }
			        };

			    wait.until(e);
			    String currentURL = driver.getCurrentUrl();
			    System.out.println(currentURL);
			}*/
		}
		else
		{
			System.out.println("Here1");
			actions.moveToElement(elementToClick);
			System.out.println("Here2");
			
			elementToClick.click();
			System.out.println("Here3");
		}
	}
	
	/*public void focusClick(WebDriver driver,WebElement elementToClick, WebElement elementToWait, String br)
	{
		Actions actions = new Actions(driver);
		if(br.equals("chrome"))
		//if(br.equals("chrome"))
		{
				actions.moveToElement(elementToClick).click().perform();
				//System.out.println("Here1");
				//actions.moveToElement(elementToClick);
				//System.out.println("Here2");
				//elementToClick.click();
				//System.out.println("Here3");
		}
		else if(br.equals("IE"))
		{
			actions.moveToElement(elementToClick).click().perform();
			WebDriverWait wait = new WebDriverWait(driver, 30);
			
			//wait.until((driver.getCurrentURL()).contains("a"));
			
			    final String previousURL = driver.getCurrentUrl();
			    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

			    ExpectedCondition e = new ExpectedCondition<Boolean>() {
			          public Boolean apply(WebDriver d) {
			            return (d.getCurrentUrl() != previousURL);
			          }
			        };

			    wait.until(e);
			    String currentURL = driver.getCurrentUrl();
			    System.out.println(currentURL);
 
			
//			Boolean a=wait.until(ExpectedConditions.urlContains(br));
		}
		else
		{
			System.out.println("Here1");
			actions.moveToElement(elementToClick);
			System.out.println("Here2");
			elementToClick.click();
			System.out.println("Here3");
		}
	}*/
	

public Boolean selectFromDropDown(WebDriver driver,String locator, String value) {
    try {
        new Select(driver.findElement(By.xpath(locator))).selectByVisibleText(value);
        return true;
    }
     catch (Exception e) {
            
            System.out.println("Could not find element");
            return false;
        }
}

	
	public Boolean focusSearch(WebDriver driver,WebElement elementToSearch,String br)
	{
		Actions actions = new Actions(driver);
		if(br.equals("chrome") || br.equals("IE"))
		{
			try{
				actions.moveToElement(elementToSearch);
				return elementToSearch.isDisplayed();
			}
			catch(Exception e)
			{
				return false;
			}

		}
		else
		{
			return elementToSearch.isDisplayed();
		}
	}
	
	public Boolean focusSelected(WebDriver driver,WebElement elementToSearch,String br)
	{
		Actions actions = new Actions(driver);
		if(br.equals("chrome") || br.equals("IE"))
		{
			try{
				actions.moveToElement(elementToSearch);
				return elementToSearch.isSelected();
			}
			catch(Exception e)
			{
				return false;
			}

		}
		else
		{
			return elementToSearch.isSelected();
		}
	}
	
	public void focusDropdown(WebDriver driver,String DPxpath,String data, String br)
	{ 
		System.out.println("element to Select"  +DPxpath);
	       Actions actions = new Actions(driver); 
	        
	       if(br.equals("IE"))	  
	       {
	    	   DPxpath=DPxpath+"/DIV/INPUT";	
	    	   driver.findElement(By.xpath(DPxpath)).click();
	    	   driver.findElement(By.xpath(DPxpath)).sendKeys(data);
	    	   actions.moveToElement(driver.findElement(By.xpath(DPxpath))).sendKeys(Keys.TAB).sendKeys(Keys.ENTER).build().perform();
	       }
	       else
	       {
	    	   DPxpath=DPxpath+"/select";
	    	   new Select(driver.findElement(By.xpath(DPxpath))).selectByVisibleText(data);
	       }	    	       	   	               
	 }
	
	public void focusSendKeys(WebDriver driver,WebElement elementToEnter,String data, String br)
	{
		Actions actions = new Actions(driver);
		if(br.equals("chrome") || br.equals("IE"))
				actions.moveToElement(elementToEnter).sendKeys(data).perform();
		else
		{
			actions.moveToElement(elementToEnter);
			elementToEnter.sendKeys(data);
		}
	}
	
	
	public int browserSwitch(String br)
	{
		//Test_called++;
		//return Test_called;
		if(br.equals("chrome"))
		{
			Test_called_chrome++;
			return Test_called_chrome;
		}
		else if (br.equals("IE"))
		{
			Test_called_ie++;
			return Test_called_ie;
		}
		else
		{
			Test_called_ff++;
			System.out.println("FF reached: "+Test_called_ff);
			return Test_called_ff;
		}
	}
	
	public void switchTo(WebDriver driver, String text,int tlim, String br)  
	{
		//driver.navigate().refresh();
		driver.manage().timeouts().implicitlyWait(tlim,TimeUnit.SECONDS);
		System.out.println("yup0");
		System.out.println("text value "+text);
		
		if(text.equals("Admin") || text.equals("admin"))
		{
			try{
				
			if(driver.findElement(By.linkText("Switch to Admin mode")).isDisplayed())
	  	  	{
				System.out.println("yup1");
	  		  	focusClick(driver,driver.findElement(By.linkText("Switch to Admin mode")),br);
	  		  	System.out.println("yup2");
	  			if(focusSearch(driver,driver.findElement(By.cssSelector("#modal-save > div.modal-container > div.modal-footer")),br))
	  			{
	  				driver.navigate().refresh();
	  				focusClick(driver,driver.findElement(By.linkText("Switch to Admin mode")),br);
	  			}
	  	  	}
			}
			catch(Exception e)
			{
				System.out.println("Exception caught");
			}
		}
		System.out.println("yup-");
		if(text.equals("User") || text.equals("user"))
		{
			try{
			if(driver.findElement(By.linkText("Switch to User mode")).isDisplayed())
	  	  	{
				System.out.println("yup3");
				focusClick(driver,driver.findElement(By.linkText("Switch to User mode")),br);
		  		System.out.println("yup4");
		  		if(focusSearch(driver,driver.findElement(By.cssSelector("#modal-save > div.modal-container > div.modal-footer")),br))
	  			{
	  				driver.navigate().refresh();
	  				focusClick(driver,driver.findElement(By.linkText("Switch to User mode")),br);
	  			}
	  	  	}
			}
			catch(Exception e)
			{
				System.out.println("Exception caught");
			}
		}
		driver.manage().timeouts().implicitlyWait(tlim,TimeUnit.SECONDS);
		
	}	
	
	public int FeatureListIncoming(WebDriver driver,int count1, String featureName)
	  {
		  String list="";
		  int j=0,featureOrder=-1;
		  for(int i=0;i<=count1;i++)
		  {
			  System.out.println("in1");		  
			  if(driver.findElements(By.id("collapseFeature"+i)).size()>0)
			  {
	    			  System.out.println("in3");

	    			  if(driver.findElement(By.id("collapseFeature"+i)).getAttribute("ng-repeat").contains("outgoingCall") || !(driver.findElement(By.id("collapseFeature"+i)).getAttribute("ng-if").contains("==")))
	    			  {
	    				
	    				list=driver.findElement(By.cssSelector("#collapseFeature"+i+" > div.accordian-header > h3.ng-binding")).getText();
	    				System.out.println("Features searched: "+list);
	    				if(list.equals(featureName))
	    				{
	    					featureOrder=j;
	    					i=100;
	    				}
	    					    				
	    				j++;
	    				    				
	    			  }
	    			  else
	    			  {
	    				  System.out.println("Features present: "+driver.findElement(By.cssSelector("#collapseFeature"+i+" > div.accordian-header > h3.ng-binding")).getText() + " Not available for purchase : "+i);
	      				    				
	    			  }
				  
			  }
		  }
		  return (featureOrder);
	  }

	  public void turnOnOff(int featureOrder, WebDriver driver, String br,int tab)
	  {
		  	  Boolean onoff=driver.findElement(By.id("lines"+featureOrder)).isSelected();
		  	  String on="OFF";
		  	  String not="ON";
		  	  if (onoff)
		  	  {
		  		  on="ON";
		  		  not="OFF";
		  	  }
				  
		  	  int val=11;
		  	  if(envvalue==0)
		  		  val=17;
		  	  
			  Boolean r=driver.findElement(By.id("lines"+featureOrder)).isSelected();
			  System.out.println("bool : "+r);
			  if(tab==1)
				  driver.findElement(By.xpath(".//*[@id='collapseFeature"+featureOrder+"']/div[2]/form/div[4]/div/label")).click();
			  else
				  driver.findElement(By.xpath(".//*[@id='collapseFeature"+featureOrder+"']/div[2]/form/div[4]/label")).click();
				  
			  for(int i=1;i<100;i++){}
				  	  
			  driver.findElement(By.xpath(".//*[@id='collapseFeature"+featureOrder+"']/div[2]/form/div["+val+"]/button[2]")).click();
			  
			  if(driver.findElements(By.cssSelector("div.modal-body-inner.ng-scope > div.ng-scope")).size()>0)
			  {
				  if(driver.findElement(By.cssSelector("div.modal-body-inner.ng-scope > div.ng-scope")).getText().contains("warning"))
				  {
					  System.out.println("Warning message is displayed hence proceeding. Warning message: " + driver.findElement(By.cssSelector("div.modal-body-inner.ng-scope > div.ng-scope")).getText());
					  driver.findElement(By.xpath("//div[@id='modal-warning']/div/div[2]/span[2]")).click();
				  }
			  }
			  		
			  int chk=0;
			  do{
				 System.out.println("Processing!" +chk);
				 chk++;
			  }while(driver.findElement(By.cssSelector("img[alt='icon-loading.gif']")).isDisplayed());
			  
			  if(driver.findElements(By.id("dataSaveSucess")).size()>0 && driver.findElement(By.id("dataSaveSucess")).isDisplayed())
			  {
				 System.out.println("Success");
				 statusTracker(br,"Pass","Verify switching feature from "+ on+" to "+ not,"Successfully switched feature","Successfully able to switch feature");
			  }
			  else
			  {
				 System.out.println("Fail");
				 statusTracker(br,"Fail","Verify switching feature from "+ on+" to "+ not,"Unsuccessfully switched feature","Successfully able to switch feature");
				 
				 driver.navigate().refresh();
				 driver.findElement(By.cssSelector("#collapseFeature"+featureOrder+" > div.accordian-header > div.header-right")).click();
			  }	  	  		  
	  }
	  
	  public void turnOnOffSelected(int featureOrder, String featureName,WebDriver driver, String br, int numSuspended,int tab,String[] tnSuspendedStatus)
	  {
	  	  Boolean onoff=driver.findElement(By.id("lines"+featureOrder)).isSelected();
	  	  String on="OFF";
	  	  String not="ON";
	  	  if (onoff)
	  	  {
	  		  on="ON";
	  		  not="OFF";
	  	  }
			  
		  Boolean r=driver.findElement(By.id("lines"+featureOrder)).isSelected();
		  System.out.println("bool : "+r);
		  		  		  
		  int tncount=countNumberTns(featureOrder,featureName,driver,tab);
		  System.out.println("tncount : "+tncount);
		  int rand=Integer.parseInt(randomNO(0,tncount-1));
		  int validTns = tncount-numSuspended;
		  int rand1=0;
		  if(validTns > 2)
				  rand1=Integer.parseInt(randomNO(0,tncount-1));
	  
		  for(int i=1;i<1000;i++){}
		  System.out.println("ok1");

		  int newcount=rand+5;
		  int newcount1=rand1+5;
		  System.out.println("Ha in 0");
		  
		  while(tnSuspendedStatus[rand].equals("yes")){

				  rand=Integer.parseInt(randomNO(0,tncount-1));
				  newcount=rand+5;
				  System.out.println("Ha in 1");
		  }
		  if(validTns > 2)
			  while(tnSuspendedStatus[rand1].equals("yes") || rand==rand1){
				  System.out.println("Ha in 2: "+rand1);
				  rand1=Integer.parseInt(randomNO(0,tncount-1));
				  newcount1=rand1+5;
				  System.out.println("Ha in 2");
			  }  
		  
		  	System.out.println("data "+newcount+" "+newcount1);
		  if(tab==1)
		  {
		   if(driver.findElements(By.xpath("//div[@id='collapseFeature"+featureOrder+"']/div[2]/form/div["+newcount+"]/div/label")).size() >0)
		   {
			  System.out.println("Here now1 check"+featureName+rand);
			  driver.findElement(By.xpath("//div[@id='collapseFeature"+featureOrder+"']/div[2]/form/div["+newcount+"]/div/label")).click();
		   }
		   else
		   {
			  System.out.println("Here now2");
			  driver.findElement(By.id("check"+featureName+rand)).click();
		   }
		  
		   System.out.println("ok2");
		  
		   if(validTns > 2){
		   if(driver.findElements(By.xpath("//div[@id='collapseFeature"+featureOrder+"']/div[2]/form/div["+newcount1+"]/div/label")).size() >0)
		   {
			  System.out.println("Here now1 check"+featureName+rand1);
			  driver.findElement(By.xpath("//div[@id='collapseFeature"+featureOrder+"']/div[2]/form/div["+newcount1+"]/div/label")).click();
		   }
		   else
		   {
			  System.out.println("Here now2");
			  driver.findElement(By.id("check"+featureName+rand1)).click();
		   }
		   }
		  }
		  else{
			  if(driver.findElements(By.xpath("//div[@id='collapseFeature"+featureOrder+"']/div[2]/form/div["+newcount+"]/label")).size() >0)
			   {
				  System.out.println("Here now1 check"+featureName+rand);
				  driver.findElement(By.xpath("//div[@id='collapseFeature"+featureOrder+"']/div[2]/form/div["+newcount+"]/label")).click();
			   }
			   else
			   {
				  System.out.println("Here now2");
				  driver.findElement(By.id("check"+featureName+rand)).click();
			   }
			  
			   System.out.println("ok2");
			  
			   if(validTns > 2){
			   if(driver.findElements(By.xpath("//div[@id='collapseFeature"+featureOrder+"']/div[2]/form/div["+newcount1+"]/label")).size() >0)
			   {
				  System.out.println("Here now1 check"+featureName+rand1);
				  driver.findElement(By.xpath("//div[@id='collapseFeature"+featureOrder+"']/div[2]/form/div["+newcount1+"]/label")).click();
			   }
			   else
			   {
				  System.out.println("Here now2");
				  driver.findElement(By.id("check"+featureName+rand1)).click();
			   }
			   }
		  }
		  System.out.println("ok4");
		  
		  for(int i=1;i<100;i++){}
		  int val=11;
	  	  if(envvalue==0)
	  		  val=17;
		  driver.findElement(By.xpath(".//*[@id='collapseFeature"+featureOrder+"']/div[2]/form/div["+val+"]/button[2]")).click();
			  			  
		  if(driver.findElements(By.cssSelector("div.modal-body-inner.ng-scope > div.ng-scope")).size()>0)
		  {
			  if(driver.findElement(By.cssSelector("div.modal-body-inner.ng-scope > div.ng-scope")).getText().contains("warning"))
			  {
				  System.out.println("Warning message is displayed hence proceeding. Warning message: " + driver.findElement(By.cssSelector("div.modal-body-inner.ng-scope > div.ng-scope")).getText());
				  driver.findElement(By.xpath("//div[@id='modal-warning']/div/div[2]/span[2]")).click();
			  }
		  }
		  
		  int chk=0;
		  do{
			 System.out.println("Processing!" +chk);
			 chk++;
		  }while(driver.findElement(By.cssSelector("img[alt='icon-loading.gif']")).isDisplayed());
		  
		  if(driver.findElements(By.id("dataSaveSucess")).size()>0)
		  {
			 System.out.println("Success");
			 statusTracker(br,"Pass","Verify order process when some lines are selected","Successfully processed order","Successfully be able to process order");
		  }
		  else
		  {
			 System.out.println("Fail");
			 statusTracker(br,"Pass","Verify order process when some lines are selected","Unsuccessfully processed order","Successfully be able to process order");
		  }
	}
	  
	  public int countNumberTns(int featureOrder, String featureName, WebDriver driver,int tab)
	  {
		  int numberOfTns=0;
		  for(int i=0;i<100;i++)
		  {
				  //if(driver.findElements(By.id("check"+featureName+i)).size()>0)
			  if(tab==1)
			  {
			  try{
				 if(driver.findElement(By.xpath("//div[@id='collapseFeature"+featureOrder+"']/div[2]/form/div["+(5+i)+"]/div/label")).isDisplayed())
				 {
					 System.out.println("in11");
					  numberOfTns++;
				 }
				 else
				 {
					 System.out.println("in22");
					  i=101;
				 }
				}
			  catch(Exception e)
			  {
				  i=101;
			  }
			  }
			  else
			  {
				  try{
					  if(driver.findElement(By.xpath("//div[@id='collapseFeature"+featureOrder+"']/div[2]/form/div["+(5+i)+"]/label")).isDisplayed())
							 
						 {
							 System.out.println("in11");
							  numberOfTns++;
						 }
						 else
						 {
							 System.out.println("in22");
							  i=101;
						 }
						}
					  catch(Exception e)
					  {
						  i=101;
					  }
			  }
		  }
		 		  		  
		  return numberOfTns;
	  }
	  	  
	  public int countNumberTns(int featureOrder, String featureName, WebDriver driver,String br)
	  {
		  int numberOfTns=0;
		  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		  for(int i=0;i<50;i++)
		  {
				  //if(driver.findElements(By.id("check"+featureName+i)).size()>0)
			  try{
				System.out.println("H");
				
				if(driver.findElement(By.xpath("//div[@id='collapseFeature"+featureOrder+"']/div[2]/form/div["+(5+i)+"]/div/label")).isDisplayed())
				{
					System.out.println("in11");
					numberOfTns++;
				}
				 else
				 {
					 System.out.println("in22");
					  i=101;
				 }
				}
			  catch(Exception e)
			  {
				  i=51;
			  }
		  }
		 
		  
		  
		  return numberOfTns;
	  }
	public String Select_TN(WebDriver driver,String Featurename,int count,String br)
	{
			if(Featurename=="Forward All Calls")
			{
				 if(assertTrue(isElementPresent(By.id("modal-warning"))))
		         {
		       	  driver.findElement(By.xpath("//html/body/div[6]/div/div[2]/span[2]")).click();
		       	  logger.info("PopUp");
		         }
		         
			}
		int numberOfTns=countNumberTns(count,Featurename,driver,br);
		System.out.println(numberOfTns);
		String TN="";
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		for(int j=0;j<numberOfTns;j++)
		{
			try
			{
			
		if(focusSearch(driver,driver.findElement(By.xpath("//html/body/section/div["+(4+count)+"]/div[2]/form/div["+(5+j)+"]/div/i")),br))
			{
				System .out.println("TN is suspended");
				continue;
			}
			else if(driver.findElement(By.id("check"+Featurename+j)).isSelected())
			{ 
				System.out.println("tn2");
				TN=driver.findElement(By.xpath("//html/body/section/div["+(4+count)+"]/div[2]/form/div["+(5+j)+"]/div/label")).getText();
				break;
			}
			else
			{
				
				driver.findElement(By.xpath("//html/body/section/div["+(4+count)+"]/div[2]/form/div["+(5+j)+"]/div/label")).click();
				driver.findElement(By.cssSelector("#collapseFeature"+count+" > div.accordian-body.in > form[name=\"featureForm\"] > div.accordian-actions.ng-scope > button.btn.btn-primary")).click();
			   int chk=0;
				do{
                 
					
                 chk++;
               }while(driver.findElement(By.cssSelector("img[alt='icon-loading.gif']")).isDisplayed());
             if(driver.findElement(By.id("dataSaveSucess")).isDisplayed())
             {
             	TN=driver.findElement(By.xpath("//html/body/section/div["+(4+count)+"]/div[2]/form/div["+(5+j)+"]/div/label")).getText();    
            break;
             }
             else
           	  {
           	  System.out.println("This feature cannot be  Enabled because "+driver.findElement(By.cssSelector("div.ng-scope > ul > li.ng-scope.ng-binding")).getText());   
           	 statusTracker(br,"Fail","","This feature cannot be  Enabled because "+driver.findElement(By.cssSelector("div.ng-scope > ul > li.ng-scope.ng-binding")).getText(),"");  
           	  TN=null;
           	  }
				
			}
			}
			catch(Exception e)
			  {
				numberOfTns++;
			  }
			
		}
	  return TN;	
	}
	  
@SuppressWarnings("finally")
	public boolean InternalException(WebDriver driver,String br)throws Exception
	{ boolean Echk=false;
		 try
       {
			 System.out.println("Exception1");
          if(driver.findElement(By.xpath("//html/body/section/div[2]/hgroup")).isDisplayed())
            {	statusTracker(br,"Fail","","Portal is down due to "+driver.findElement(By.xpath("//html/body/section/div[2]/hgroup/p")).getText(),"");   
       	    System.out.println("Exception caught");
          	Echk=true;
            }
          	
       }   
		 catch( Exception e)
       {
			 System.out.println("Exception2"); 
			 return Echk;
       } 
		 finally {
			 System.out.println("Exception3"); 
			 return Echk;
                } 
		
	}

	  protected boolean assertTrue(Object elementPresent) {
		// TODO Auto-generated method stub
		return false;
	}

	protected Object isElementPresent(By id) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isAttributePresent(WebElement element, String attribute) {
		    Boolean result = false;
		    try {
		        String value = element.getAttribute(attribute);
		        if (value != null){
		            result = true;
		        }
		    } catch (Exception e) {}

		    
		    return result;
		}
	  
	public String[] suspendedStatus(int numberOfTns, int featureOrder,String featureName, WebDriver driver,int tab)
	  {
		  String []a=new String[numberOfTns];
		  
		  for(int i=0;i<numberOfTns;i++)
		  {
			  if(tab==1)
			  {
			  if(isAttributePresent(driver.findElement(By.xpath("//div[@id='collapseFeature"+featureOrder+"']/div[2]/form/div["+(5+i)+"]/div/input")),"disabled"))
			  {
				  a[i]="yes";
				  System.out.println("yes");
			  }
			  else
			  {
				  a[i]="no";
				  System.out.println("no");	  
			  }
			  }
			  else{
				  if(isAttributePresent(driver.findElement(By.xpath("//div[@id='collapseFeature"+featureOrder+"']/div[2]/form/div["+(5+i)+"]/input")),"disabled"))
				  {
					  a[i]="yes";
					  System.out.println("yes");
				  }
				  else
				  {
					  a[i]="no";
					  System.out.println("no");	  
				  }
			  }
		
		  
		  }
		  return a;
	  }
	     
	public boolean suspended(String a[], WebDriver driver)
	  {
		  Boolean result=false;
		  System.out.println("inside loop1");
		  for(int i=0;i<a.length;i++)
		  {
			  System.out.println("inside loop2");
			  if(a[i].equals("yes"))
			  {
				  System.out.println("inside loop3");
				  result=true;
			  }
		  }
		  return result;
	  }
	  
	public int getNumSuspended(String a[], WebDriver driver)
	  {
		  int result=0;
		  for(int i=0;i<a.length;i++)
		  {
			  if(a[i].equals("yes"))
				  result++;
		  }
		  return result;
	  }
	  
	public void Unsavedpopup(String br, WebDriver driver, int featureOrder,int tab)
	  {
		  logger.info("Unsavedpopup");
		
		  //driver.findElement(By.xpath(xpath_ACRunsavedpopup_xpath1)).click();
		  if(tab==1)
			  driver.findElement(By.xpath("//div[@id='collapseFeature"+featureOrder+"']/div[2]/form/div[4]/div/label")).click();
		  else			  
			  driver.findElement(By.xpath("//div[@id='collapseFeature"+featureOrder+"']/div[2]/form/div[4]/label")).click();
		  
		  logger.info("TurnOFF/ON");
		  driver.findElement(By.linkText("Home")).click();
		  Boolean CH= driver.findElement(By.cssSelector("#modal-save > div.modal-container > div.modal-container-inner.modal-message > div.modal-body > div.modal-body-inner")).isDisplayed();
		  logger.info("pop up display");
		  if(CH==true)
		  {
			  System.out.println("In here 1");
			  driver.findElement(By.id("cancelSaveFeature")).click();
			  System.out.println("In here 2");
			  logger.info("pop up display1");
			  if(driver.findElements(By.xpath("//html/body/section/div[4]/div[2]")).size()>0)
			  {
				  logger.info("Success");
				  statusTracker(br,"Pass","Verify if clicking on cancel navigating to Feature Page","Successfully navigate back to Feature Page on clicking cancel","Success");
			  }
			  else
			  {
				  logger.info("Fail");
				  statusTracker(br,"Fail","Cancel did not work","Unsuccessful","Unable to process successfully");
			  }
		  }     
		  
		  if(tab==1)
			  driver.findElement(By.xpath("//div[@id='collapseFeature"+featureOrder+"']/div[2]/form/div[4]/div/label")).click();
		  else
			  driver.findElement(By.xpath("//div[@id='collapseFeature"+featureOrder+"']/div[2]/form/div[4]/label")).click();
			  
		  logger.info("TurnON");
		  if(tab==1)
			  driver.findElement(By.linkText("Outgoing Calls")).click();
		  else
			  driver.findElement(By.linkText("Incoming Calls")).click();  
			  
		  driver.findElement(By.xpath("//a[@id='unsavedFeature']/span")).click();
		  logger.info("OK");

		  if(driver.findElements(By.xpath("//html/body/section/div[4]/div[1]/div")).size()>0)
		  {
			  statusTracker(br,"Pass","Verify if clicking on OK navigating to Feature Page","Successfully navigated to Outgoing calls Page on clicking OK","Success");
			//  state="Pass";
		  }
		  else
		  {
			  logger.info("Fail");
			  statusTracker(br,"Fail","OK dint work in Unsaved Pop Up","Unsuccessful","Unable to process successfully");
		  }
		 // return state;
	  }
	  
	public void Cancel(String br,WebDriver driver, int featureOrder,int tab)
	  {
	      logger.info("Cancel");
	      if(tab==1)
	    	  driver.findElement(By.linkText("Incoming Calls")).click();
	      else
	    	  driver.findElement(By.linkText("Outgoing Calls")).click();
	    	  
	      driver.findElement(By.cssSelector("#collapseFeature"+featureOrder+" > div.accordian-header > div.header-right")).click();
	      boolean b= driver.findElement(By.id("lines"+featureOrder)).isSelected();
	      
	      if(tab==1)
	    	  driver.findElement(By.xpath("//div[@id='collapseFeature"+featureOrder+"']/div[2]/form/div[4]/div/label")).click();
	      else
	    	  driver.findElement(By.xpath("//div[@id='collapseFeature"+featureOrder+"']/div[2]/form/div[4]/label")).click();
	    	
	      int val=11;
	  	  if(envvalue==0)
	  		  val=17;
	      driver.findElement(By.xpath(".//*[@id='collapseFeature"+featureOrder+"']/div[2]/form/div["+val+"]/button[1]")).click();
	      
	      int chk=0;
		  do{
			 System.out.println("Processing!" +chk);
			 chk++;
		  }while(driver.findElement(By.cssSelector("img[alt='icon-loading.gif']")).isDisplayed());
	      
		  driver.findElement(By.cssSelector("#collapseFeature"+featureOrder+" > div.accordian-header > div.header-right")).click();
		  
	      if(b==driver.findElement(By.id("lines"+featureOrder)).isSelected())
	      {
	    	  logger.info("Success");
	    	 // state="Pass";
	    	  statusTracker(br,"Pass","Verify if the user is able to cancel the changes made","Successfully be able to cancel order","Should be able to successfully cancel the order");
	      }
	      else
	      {
	    	  logger.info("Fail");
	    	  statusTracker(br,"Fail","Verify if the user is able to cancel the changes made","Cancelling the order did not revert the changes made","Should be able to successfully cancel the order");
	      }
	      //return state;
	  }
	
	public void unsave(WebDriver driver,String br, String canbut, String Savbut) throws Exception
		{
		  
		  focusClick(driver,driver.findElement(By.xpath("//html/body/header/div[4]/div[2]/nav/ul/li[1]/a")),br);
		  Thread.sleep(2000);
		   if(driver.findElements(By.xpath("//html/body/div[2]/div")).size()>0)
		   {
			   statusTracker(br, "","","The Unsaved pop up is displayed","");
		   }
		   Thread.sleep(2000);
		   focusClick(driver,driver.findElement(By.xpath(canbut)),br);
		   
		    if(driver.findElement(By.xpath("//html/body/header/div[4]/div[2]/nav/ul/li[2]/a")).isDisplayed())
		    {
			   statusTracker(br, "Pass","Verify if the cancel in unsaved pop up is closes pop up","The cancel closes the unsaved pop up","The cancel should close the pop up");
		    }
		    else
		    {
			   statusTracker(br,"Fail","Verify id the cancel in unsaved pop up is closes pop up","The cancel is not closes the unsaved pop up","The cancel should close the pop up");
		    }
		    
		    focusClick(driver,driver.findElement(By.xpath("//html/body/header/div[4]/div[2]/nav/ul/li[1]/a")),br);
		    Thread.sleep(2000);
		    
		    focusClick(driver,driver.findElement(By.xpath(Savbut)),br);
		    do{
		      }while(driver.findElement(By.cssSelector("img[alt='icon-loading.gif']")).isDisplayed());
		     if(driver.findElement(By.xpath("//html/body/header/div[4]/div[2]/nav/ul/li[2]/a")).isDisplayed())
		     {
			   statusTracker(br, "Pass","Verifiy if the save in unsaved pop up Redirects to Home","The save Redirects to Home","The save should Redirects to Home");
		     }
		     else
		     {
			   statusTracker(br, "Fail","Verifiy if the save in unsaved pop up Redirects to Home","The save is not Redirected to Home","The save should Redirects to Home");
		     }		     
		  }
	  
	public void login(WebDriver driver,String username, String password) throws InterruptedException
	  {
		driver.get("http://voicezone.timewarnercable.com");
		
		envvalue=1;
		  System.out.println("qtest1");
		  //explicitWait("Ecom_User_ID");
		  driver.findElement(By.id("cc-username")).sendKeys(username);
		  System.out.println("qtest1");
		  driver.findElement(By.id("cc-user-password")).sendKeys(password);
		  Thread.sleep(10000);
		  driver.findElement(By.id("login-form-button")).click();
		  Thread.sleep(10000);
		  
	  }
	
	public abstract void execute(String br, WebDriver driver, String url, int loc, String name1)
			throws Exception;

	public String getStatus() {
		String b = "Fail";
		if (this.result.equals("Pass"))
			b = "Pass";
		return b;
	}

	public String randomNO(int max, int min) {
		int Max = max;
		int Min = min;
		double random1 = Min + (int) (Math.random() * ((Max - Min) + 1));
		int random2 = (int) random1;
		String s1 = new Integer(random2).toString();
		return (s1);

	}

	public boolean Button_enabled(String fxp) {

		return (drive.findElement(By.xpath(fxp)).isEnabled());

	}

	public boolean isElementPresent(String xp, WebDriver driver) {

		try {
			if (driver.findElement(By.xpath(xp)).isDisplayed())
				;
			return true;
		} catch (Exception e) {
			logger.info("I am inside exception");
			return false;
		}
	}

	public boolean isElementPresent_1(String xp, WebDriver driver) {

		try {
			driver.findElement(By.xpath(xp));
			return true;
		} catch (Exception e) {
			logger.info("I am inside exception");
			return false;
		}
	}

	public int return_size(String fxp, WebDriver driver) {
		List<WebElement> myli = driver.findElements(By.xpath(fxp));
		return myli.size();
	}

	public void click_CSS_Element(String fxp, WebDriver driver) {
		driver.findElement(By.cssSelector(fxp)).click();
	}

	public void return_text_CSS_Element(String fxp, WebDriver driver) {
		driver.findElement(By.cssSelector(fxp)).getText();
	}

	public void select_list_CSS(String fxp, WebDriver driver, String index) {
		driver.findElement(By.cssSelector(fxp)).sendKeys(index);
	}

	public void select_list_xpath(String fxp, WebDriver driver, String index) {
		driver.findElement(By.xpath(fxp)).sendKeys(index);
	}

	public String orderprocess(WebDriver driver,String br) throws Exception {
		
		  driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		int chk=0;
	      do{
	           Thread.sleep(1000);       
	          chk++;
	          System.out.println(chk);
	                }
	      while(driver.findElement(By.xpath("//*[@id='progress']")).isDisplayed());
		
		
		if ((drive.findElements(By.id("modalContinueButton")).size() != 0)
				&& (drive.findElement(By.id("modalContinueButton"))
						.isDisplayed())) {
			focusClick(driver,driver.findElement(By.id("modalContinueButton")),br);
			return "Fail";
					} 
		else if (!((drive.findElements(
				By.cssSelector("div.validation-summary-errors > ul > li")).size() != 0))) {
			// selenium.click("id=modalContinueButton");
			logger.info("Checkpoint in orderprocessing");
			return "Pass";
		} 
		else {
			return "Fail";
		}
		
	}

	public String orderprocess(String error_region) throws Exception {
		Thread.sleep(1000);
		do {

		} while ((drive.findElements(By.xpath("//div[9]")).size() != 0));
		// while(selenium.isVisible("css=#progress > div > p"));
		if ((drive.findElements(By.id("modalContinueButton")).size() != 0)
				&& (drive.findElement(By.id("modalContinueButton"))
						.isDisplayed())) {
			return "Fail";
		} else if (!((drive.findElements(By.id(error_region)).size() != 0))) {
			// selenium.click("id=modalContinueButton");
			logger.info("Checkpoint in orderprocessing with error");
			return "Pass";
		} else {
			return "Fail";
		}
	}

	public void deleteFolder(File folder)
 	{
	    File[] files = folder.listFiles();
	    if(files!=null) { //some JVMs return null for empty dirs
	        for(File f: files) {
	            if(f.isDirectory())
	            {
	                deleteFolder(f);
	            } else {
	                f.delete();
	            }
	        }
	    }
	    folder.delete();
	}
	
	
	public void statusTracker(String br, String r, String rscenario,
			String ractual, String rexpected) {
		Result_bean rb = new Result_bean();
		rb.setResult(r);
		rb.setResult_actual(ractual);
		rb.setResult_expected(rexpected);
		rb.setResult_scenario(rscenario);

		logger.info(""+"Get results" + rb.getResult());

		// Storing browser
		// names****************************************************
		int flag = 1;
		if (counter_browser == 0) {
			browser[counter_browser] = br;
			counter_browser++;
		} else {

			int ij = 0;
			logger.info(""+"browser[i] " + browser[ij]);
			while (browser[ij] != null) {
				logger.info(""+"browser[ij]!=null");
				if (browser[ij].equals(br)) {
					flag = 0;
					break;
				}
				ij++;
			}
			if (flag == 1) {
				browser[ij] = br;
				counter_browser++;
			}

		}
		// Storing browser
		// names****************************************************

		int i_browser = 0;
		while (browser[i_browser] != null) {
			if (browser[i_browser].equals(br)) {
				counter_result_sheet[i_browser]++;
				logger.info(""+"COUNTER RESULT SHEET VALUE ******** "+counter_result_sheet[i_browser]+"br "+br);
				break;
			}
			i_browser++;
		}
		
		
		 
		
		i_browser = 0;
		while (browser[i_browser] != null) {
			if (browser[i_browser].equals(br)) {
		String imagename = path;
		// logger.info(""+ "path image name1 is "+imagename);
		// String imagename = "_Int_Exception";
		// imagename=imagenamr.str
		imagename = imagename.replaceAll(input_sheet, "//Test_Results" + "//"+ "Fail_Screenshots////" 
		+ Feature_Name	+ "_Int_Fail");

		// logger.info(""+ "path image name2 is "+imagename);
		// imagename=imagename+"\\"+"Fail_Screenshots";
		/*
		 * String imagename = path; int i=imagename.length();
		 * logger.info(""+"reached1: "+i); for
		 * (;imagename.charAt(i-1)=='\\';i--) { logger.info(""+i+":"); }
		 * logger.info(""+"reached2: "+imagename +"  " +i);
		 * imagename=imagename.substring(0,i+2)+"ScreenCapture";
		 * logger.info(""+"reached3"); imagename=imagename+"_Int_Exception";
		 */
		// String imagename = "ScreenCap_Int_Exception";
		Date dNow;
		SimpleDateFormat ft = new SimpleDateFormat("'_'MMMM dd'_'hh.mma");
		result[arrcount[i_browser]] = r;
		if (!(r.equals("Fail")))
			result_actual[arrcount[i_browser]] = ractual;
		result_expected[arrcount[i_browser]] = rexpected;
		result_scenario[arrcount[i_browser]] = rscenario;
		if (r.equals("Fail")) {
			// ||r.equals("refer_screenshot")
			dNow = new Date();
			logger.info(""+ft.format(dNow));
			imagename = imagename + int_screenshot + ft.format(dNow) + ".png";
			int_screenshot++;
			try {
				File scrFile1 = ((TakesScreenshot) drive)
						.getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(scrFile1, new File(imagename));
				// selenium.captureEntirePageScreenshot(imagename, "");
			} catch (Exception e) {
				imagename = "Unable to capture screenshot";
			}
			result_actual[arrcount[i_browser]] = ractual + " Refer screenshot: "
					+ imagename;
		}

		// //added new function

		if (r.equalsIgnoreCase("pass")) {

			if (pass_screenshot_required == true) {
				String imagename_1 = path;
				// logger.info(""+ "path image name1 is "+imagename_1);
				// String imagename = "_Int_Exception";
				// imagename=imagenamr.str
				imagename_1 = imagename_1.replaceAll(input_sheet,
						"//Test_Results" + "//"	+ "Pass_Screenshots////"
								+ Feature_Name+"_Int_Pass");

				// logger.info(""+ "path image name2 is "+imagename_1);

				dNow = new Date();
				logger.info(""+ft.format(dNow));
				imagename_1 = imagename_1 + int_screenshot + ft.format(dNow)
						+ ".png";
				int_screenshot++;
				try {
					File scrFile1 = ((TakesScreenshot) drive)
							.getScreenshotAs(OutputType.FILE);
					FileUtils.copyFile(scrFile1, new File(imagename_1));
					// selenium.captureEntirePageScreenshot(imagename, "");
				} catch (Exception e) {
					imagename_1 = "Unable to capture screenshot";
				}
				result_actual[arrcount[i_browser]] = ractual + " Refer screenshot: "
						+ imagename_1;
			} else {
				result_actual[arrcount[i_browser]] = ractual
						+ " : No screenshot required ";
			}
		}
		logger.info(""+"storing done******");
		break;
			}
			i_browser++;
		}
		
		i_browser = 0;
		while (browser[i_browser] != null) {
			if (browser[i_browser].equals(br)) {
				logger.info(""+"COUNTER RESULT SHEET VALUE ******** "+counter_result_sheet[i_browser]+"br "+br);	
			logger.info(""+	"result_actual"+result_actual[arrcount[i_browser]] );
			logger.info(""+	"result_expected" +result_expected[arrcount[i_browser]]  );
			logger.info(""+	 "result_scenario"+ result_scenario[arrcount[i_browser]] ); 
			logger.info(""+"result "+	result[arrcount[i_browser]] );
				break;
			}
			i_browser++;
		}
		
		
		// br_specific.put(br, rb.getResult());
		// br_specific.put(br, rb.getResult_actual());
		// br_specific.put(br, rb.getResult_expected());
		// br_specific.put(br, rb.getResult_scenario());
		logger.info(""+"RB ... if..." + br_specific);
		// logger.info(""+"br value "+br);

		if (br_specific.containsKey(br)) {
			logger.info(""+"Browser key added***** " + br);
			;
			logger.info(""+"RB ... if..." + br_specific);
			/*
			 * if(br.equals("firefox")) { a.add(rb); br_specific.put(br,a); }
			 * else { br_specific.put(br,b); b.add(rb);
			 * 
			 * }
			 */
			System.out
					.println("*******************Firefox*****************************");
			

		} else {
			
			a.add(rb);
			

		}

		// storing values
		i_browser = 0;
		while (browser[i_browser] != null) {
			if (browser[i_browser].equals(br)) {
				logger.info(""+"COUNTER RESULT SHEET VALUE ******** "+counter_result_sheet[i_browser]+"br "+br+" arrcount "+arrcount);	
				result_array[i_browser * 4][l_browser[i_browser]] = a.get(
						arrcount[i_browser]).getResult();
				logger.info(""+result_array[i_browser * 4][l_browser[i_browser]]);
				result_array[(i_browser * 4) + 1][l_browser[i_browser]] = a
						.get(arrcount[i_browser]).getResult_scenario();
				logger.info(""+result_array[(i_browser * 4) + 1][l_browser[i_browser]]);
				result_array[(i_browser * 4) + 2][l_browser[i_browser]] = a
						.get(arrcount[i_browser]).getResult_actual();
				logger.info(""+result_array[(i_browser * 4) + 2][l_browser[i_browser]]);
				result_array[(i_browser * 4) + 3][l_browser[i_browser]] = a
						.get(arrcount[i_browser]).getResult_expected();
				logger.info(""+result_array[(i_browser * 4) + 3][l_browser[i_browser]]);
				l_browser[i_browser]++;
				break;
			}

			i_browser++;

		}

		// storing values

		/*
		 * if(br.equals(browser[0])) {
		 * result_array[0][l_browser]=a.get(arrcount).getResult();
		 * result_array[1][l_browser]=a.get(arrcount).getResult_scenario();
		 * result_array[2][l_browser]=a.get(arrcount).getResult_actual();
		 * result_array[3][l_browser]=a.get(arrcount).getResult_expected();
		 * l_browser++; } if(br.equals(browser[1])) {
		 * result_array[4][k_browser]=a.get(arrcount).getResult();
		 * result_array[5][k_browser]=a.get(arrcount).getResult_scenario();
		 * result_array[6][k_browser]=a.get(arrcount).getResult_actual();
		 * result_array[7][k_browser]=a.get(arrcount).getResult_expected();
		 * k_browser++; }
		 */

		logger.info(""+"The set of keys are!@#$%^&* "
				+ br_specific.keySet());
		Set<String> keys = br_specific.keySet();
		logger.info(""+"Values **** rb.getResult()**= "
				+ br_specific.get(rb.getResult()) + "n");
		logger.info(""+rb.getResult());
		/*
		for (String key : keys) {
			logger.info(""+"Key = " + key);
			// logger.info(""+"Values = " + multiMap.get(key) + "n");
			// logger.info(""+"Result inside************"+br_specific.get(key));
			// logger.info(""+"Values from d Key Value pair = " +
			// br_specific
		}*/
		
		i_browser = 0;
		while (browser[i_browser] != null) {
			if (browser[i_browser].equals(br)) {
		arrcount[i_browser]++;
		
		
		logger.info(""+"STATUS TRACKER OVER**************");
		logger.info(""+"arrcount is " + arrcount+" br"+br);
		
		
		break;
			}

			i_browser++;

		}
		// logger.info(""+"result_actual[arrcount]  is "+b.get(arrcount));
		// logger.info(""+"result_expected[arrcount]  is "+result_expected[arrcount]);
		// logger.info(""+"result_scenario[arrcount]  is "+result_scenario[arrcount]);

	}
	
	public String exceptionHandler(String br,Exception ex) throws IOException {
		String s = "";
		String name = "image";
		
		
		if (ex.getLocalizedMessage().contains("being used by another process"))
		{
				
					logger.info(""+"Please try again after closing the result sheet");
					statusTracker(br,   "Fail","", ex.getMessage(),
							"Result Sheet Not accessible");
					s="Test case not executed as IO file is not accessible - " + ex.getMessage();
				
			 
		}
		else if (ex.getLocalizedMessage().contains("The system cannot find the file specified"))
		{
			logger.info(""+"Please check the input sheet");
			statusTracker(br,   "Fail","", ex.getMessage(),
					"Input Sheet Not accessible");
			s="Test case not executed as IO file is not accessible - " + ex.getMessage();
		}
		
		return s;
	}
	
	public String exceptionHandler(String br,Exception ex, WebDriver driver) throws IOException {
		String s = "";
		String name = "image";
		
		
		if (ex.getLocalizedMessage().contains("being used by another process"))
		{
				
					logger.info(""+"Please try again after closing the result sheet");
					statusTracker(br,   "Fail","", ex.getMessage(),
							"Result Sheet Not accessible");
					s="Test case not executed as IO file is not accessible - " + ex.getMessage();
				
			 
		}
		else if (ex.getLocalizedMessage().contains("The system cannot find the file specified"))
		{
			logger.info(""+"Please check the input sheet");
			statusTracker(br,   "Fail","", ex.getMessage(),
					"Input Sheet Not accessible");
			s="Test case not executed as IO file is not accessible - " + ex.getMessage();
		}
		else
		try {
			throw ex;
		} catch (NullPointerException e) {
			s = "Selenium is trying to access an object which is not present";
			statusTracker(br, "Fail", " ", s, "");
		} catch (Exception e) {

			String imagename = path;
			imagename = imagename.replaceAll("Input_Sheet.xls",
					"//Test_Results"+"//"+"Exception_Screenshots////" + Feature_Name
							+ "_Ext_Exception");
			
			SimpleDateFormat ft = new SimpleDateFormat("'_'MMMM dd'_'hh.mma");
			Date dNow = new Date();
			imagename = imagename + screenshot + ft.format(dNow) + ".png";
			// imagename=imagename+screenshot+".png";
			screenshot++;

			try {
				File scrFile1 = ((TakesScreenshot) driver)
						.getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(scrFile1, new File(imagename));
				// seleniu.captureEntirePageScreenshot(imagename, "");
			} catch (Exception f) {
				imagename = "Unable to capture a screenshot";
			}

			if (e.getMessage().contains("ids.eng.rr.com")) {
				logger.info("loop1_exception");
				s = "IDMS issue";
			} else if (e.getMessage().contains("sso-hrndva")) {
				logger.info("loop1a_exception");
				s = "SSO Gateway issue";
			}

			else if (e.getMessage().contains("not found")) {
				// logger.info("loop2_exception");
				// logger.info("I called excption handler");
				// logger.info(ex.getMessage());
				statusTracker(br, "Fail", "", e.getMessage(),
						"Element should be displayed");
				s = "Selenium has stopped because an element was not found - "
						+ e.getMessage();
				// driver.quit();
				first = 0;
			} else if (e.getMessage().contains("terminal")
					|| driver.getCurrentUrl().contains("Terminal")) {
				// logger.info("loop3_exception");
				s = "Terminal error page has stopped execution";
				driver.quit();
				first = 0;
			} else if (driver.getCurrentUrl().contains("CLAReconcile.aspx")) {
				// logger.info("loop4_exception");
				s = "There was an error in the Reconciliation. Please retry";
				driver.quit();
				first = 0;
			} else if (driver.getCurrentUrl().contains("ids.eng.rr.com")) {
				// logger.info("loop5_exception");
				s = "IDMS issue has prevented the page from proceeding further";
				driver.quit();
				first = 0;
			} else if (driver.getCurrentUrl().contains("erminal")) {
				// logger.info("loop6_exception");
				s = "Terminal error page has prevented the automation from proceeding further";
				driver.quit();
				first = 0;
			} else if ((driver.findElements(By.id("css=h1")).size() != 0)
					&& (driver.findElements(By.id("id=imgLogo")).size() != 0)) {
				// logger.info("loop7_exception");
				s = "Top Error has prevented the page from proceeding further";
				driver.quit();
				first = 0;
			} else {
				// logger.info("loop8_exception");
				s = "The browser has taken too long to respond or an intermediate error has occurred "
						+ e.getMessage();

				// logger.info(e.getMessage());
				// driver.quit();
				// first=0;
				statusTracker(br, "Fail", " ", s, " ");

			}
			// s=s+" Refer to screenshot: "+imagename;
			// statusTracker(br, "Fail"," ",s," ");
		}
		name = name + screenshot;
		screenshot += 1;
		return s;
	}
	
	public String return_text(String fxp, WebDriver driver) {
		String str = driver.findElement(By.xpath(fxp)).getText();
		return str;
	}

	public String return_text_CSS(String fxp, WebDriver driver) {
		String str = driver.findElement(By.cssSelector(fxp)).getText();
		return str;
	}

	public void click_element(String fxp, WebDriver driver) {
		driver.findElement(By.xpath(fxp)).click();

	}

	public void setUp(int loc, String br, String name1) throws Exception {

		
		String url="";
		try {
			if (first == 0 || !(current_browser.equals(br))) {
				
				if(!(current_browser.equals(br)))
				{
					tear();
					first=0;
				}
				logger.info(""+"set up is called");
				WebDriver driver=null;
				File URL = new File(this.path);		
				WorkbookSettings ws = new WorkbookSettings();
				ws.setLocale(new Locale("er", "ER"));
				Workbook wb_url = Workbook.getWorkbook(URL, ws);
				Sheet sheet_url = wb_url.getSheet(sheetname);
				logger.info(""+"set up is called");
				
				// **************************************************

				url = sheet_url.getCell(0,4).getContents();
				logger.info(""+"name1 in setup "+name1);
			
				if (Grid_Status.equalsIgnoreCase("No")) {
					logger.info(""+"Grid is NO and inside setup "+loc);
					br = sheet_url.getCell(2, loc).getContents();
					logger.info(""+"Grid is NO and inside setup");
				}
				logger.info(""+"Grid_Status " + Grid_Status + " br " + br);
				// WebDriver driver;
				logger.info(""+url);
				wb_url.close();
				// this.browserused = b;
				// logger.info(""+"Selected Browser : " + b);
			//	String browser;
				DesiredCapabilities capabilities = null;

				if (br.equalsIgnoreCase("IE")) {

					String currentdir = System.getProperty("user.dir");
					File dir1 = new File(".");

					logger.info(""+"Current dir : "
							+ dir1.getCanonicalPath());
					
					logger.info(""+"************IE********************");
					logger.info(""+"Current dir : "
							+ dir1.getCanonicalPath());
					String spath = dir1.getCanonicalPath();
					spath = spath.replaceAll("\\\\", "\\\\\\\\");
					spath = spath + "\\" + "Resource" + "\\"
							+ "Browser_Drivers";
					logger.info(""+"spath is ---" + spath);

					logger.info(""+"************IE**************");

					//File file = new File(spath + "\\IEDriverServer.exe");
					File file = new File(spath + "\\IEDriverServer_64.exe");
					System.setProperty("webdriver.ie.driver",
							file.getAbsolutePath());
					capabilities = DesiredCapabilities.internetExplorer();
					capabilities.setPlatform(Platform.ANY);
					capabilities.setBrowserName("iexplore");

					capabilities.setCapability("browserstack.ie.enablePopups",
							"false");
					capabilities.setCapability("ignoreZoomSetting", true);

					capabilities.setCapability("nativeEvents",false);


					System.out
							.println("************capabilities**************");
					if (Grid_Status.equalsIgnoreCase("No")) {
						
						driver = new InternetExplorerDriver(capabilities);
						logger.info(""+"************IE**************");
					}

				
				} else if (br.equalsIgnoreCase("SF")) {
					if (Grid_Status.equalsIgnoreCase("No")) {
						driver = new SafariDriver();
					}
				} else if (br.equalsIgnoreCase("chrome")) {
					logger.info(""+"went inside chrome");
					
					logger.info(""+"i went inside this loop of chrome");
					String currentdir = System.getProperty("user.dir");
					File dir1 = new File(".");

					capabilities = DesiredCapabilities.chrome();
					capabilities.setBrowserName("chrome");
					capabilities.setPlatform(Platform.ANY);
					ChromeOptions options = new ChromeOptions();

					options.addArguments("--disable-popup-blocking");
					capabilities.setCapability(ChromeOptions.CAPABILITY,
							options);
					options.addArguments("test-type");
					logger.info(""+"last loop of chrome");
					
					if (Grid_Status.equalsIgnoreCase("No")) {
						logger.info(""+"Current dir : "
								+ dir1.getCanonicalPath());
						String spath = dir1.getCanonicalPath();
						spath = spath.replaceAll("\\\\", "\\\\\\\\");
						spath = spath + "\\" + "Resource" + "\\"
								+ "Browser_Drivers";
						logger.info(""+"spath is ---" + spath);
						System.setProperty("webdriver.chrome.driver", spath
								+ "\\chromedriver.exe");
						capabilities = DesiredCapabilities.chrome();
						driver = new ChromeDriver(capabilities);
					}

				} else {
					logger.info(""+"Fire fox browser started");
					logger.info(""+"i went inside this loop of firefox");
					capabilities = DesiredCapabilities.firefox();
					capabilities.setBrowserName("firefox");
					capabilities.setPlatform(Platform.ANY);

					FirefoxProfile profile = new FirefoxProfile();
					profile.setPreference("plugin.state.flash", 0);
					capabilities.setCapability(FirefoxDriver.PROFILE, profile);

					if (Grid_Status.equalsIgnoreCase("No")
							) {
						System.out
								.println("In loop of firefox driver and grid status false");
						
						logger.info(""+"Current dir : "
                                + dir1.getCanonicalPath());
						String spath = dir1.getCanonicalPath();
						spath = spath.replaceAll("\\\\", "\\\\\\\\");
						spath = spath + "\\" + "Resource" + "\\"
                                	+ "Browser_Drivers";
						logger.info(""+"spath is ---" + spath);
						System.setProperty("webdriver.gecko.driver", spath
                                + "\\geckodriver.exe");

												driver = new FirefoxDriver();
					}
				}
				logger.info(""+"Before grid remote");
				if (Grid_Status.equalsIgnoreCase("Yes")) {
					logger.info(""+"grid id true ---");
					logger.info(""+"br : " + br);
					driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
					logger.info(""+"br : " + br);

					logger.info(""+"grid id true 2---");
				}

				logger.info(""+"helo");
				wb_url.close();
				
				//EventFiringWebDriver eventDriver = new EventFiringWebDriver(driver);
				//EventHandler handler = new EventHandler(br);
          		//eventDriver.register(handler);
          		//drive = eventDriver;

				
				drive = driver;
				drive.manage().window().maximize();
				logger.info(""+"Just reached end of setup");

				//execute(br, drive, url, loc, name1);
				//logger.info(""+name1 +" over " +br+" cF");
			
			}

		} catch (Exception e) {
			drive.quit();
		}
		
		execute(br, drive, url, loc, name1);
		current_browser=br;
		logger.info(""+name1 +" over " +br+" cF");
	}
	
	public void tear() {
		try {
			//drive.quit();
			drive.close();
		} catch (Exception e) {
			logger.info("Browser has already been closed");
			// logger.info(e);
		}
	}

	public static void printarray(String[] list, String name) {
		System.out.print("now printing " + name);
		for (int i = 0; i < list.length; i++) {
			logger.info(list[i]);
		}
	}

	
	public void Select_DropDown(WebDriver driver, String fxp, String option)
			throws InterruptedException

	{
		WebElement dropDownListBox = driver.findElement(By.xpath(fxp));
		Select clickThis = new Select(dropDownListBox);
		Thread.sleep(2000);
		clickThis.selectByVisibleText(option);
		Thread.sleep(2000);
	}
	
	public int obj_run(CommonFunctions r, int c, WritableSheet sheet1, String testname, int loc, String br, Map br_specific)
			throws Exception {
						logger.info(""+"Obj run is called");
						sheet1.setColumnView(3, 73);
						WritableFont TableFormat2 = new WritableFont(WritableFont.ARIAL, 10,
								WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,
								Colour.RED);
						WritableFont TableFormat3 = new WritableFont(WritableFont.ARIAL, 10,
								WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE,
								Colour.BLACK);
						WritableFont TableFormat4 = new WritableFont(WritableFont.ARIAL, 9,
								WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,
								Colour.BLACK);
						WritableFont TableFormat_status = new WritableFont(WritableFont.ARIAL,
								10, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,
								Colour.WHITE);

						WritableCellFormat tableFormatBackground3 = new WritableCellFormat();
						WritableCellFormat tableFormatBackground2 = new WritableCellFormat();
						WritableCellFormat tableFormatBackground4 = new WritableCellFormat();
						WritableCellFormat tableFormatBackground_status_pass = new WritableCellFormat();
						WritableCellFormat tableFormatBackground_status_fail = new WritableCellFormat();
						WritableCellFormat tableFormatBackground_testname = new WritableCellFormat();
						// WritableCellFormat tableFormatBackground_status_exception = new
						// WritableCellFormat();
						tableFormatBackground2.setFont(TableFormat2);
						tableFormatBackground3.setFont(TableFormat3);
						tableFormatBackground4.setFont(TableFormat4);
						tableFormatBackground_testname.setFont(TableFormat4);
						tableFormatBackground_testname
								.setVerticalAlignment(VerticalAlignment.CENTRE);
						tableFormatBackground_status_fail
								.setVerticalAlignment(VerticalAlignment.CENTRE);
						tableFormatBackground_status_pass
								.setVerticalAlignment(VerticalAlignment.CENTRE);

						// tableFormatBackground_testname.setAlignment(Alignment.JUSTIFY);
						tableFormatBackground_status_pass.setFont(TableFormat_status);
						tableFormatBackground_status_pass.setAlignment(Alignment.CENTRE);
						tableFormatBackground_status_pass.setBackground(Colour.GREEN);
						tableFormatBackground_status_fail.setFont(TableFormat_status);
						tableFormatBackground_status_fail.setBackground(Colour.RED);
						tableFormatBackground_status_fail.setAlignment(Alignment.CENTRE);
						tableFormatBackground_status_pass.setBorder(Border.ALL,
								BorderLineStyle.THIN);
						tableFormatBackground_status_fail.setBorder(Border.ALL,
								BorderLineStyle.THIN);

						tableFormatBackground3.setWrap(true);
						tableFormatBackground2.setWrap(true);
						tableFormatBackground4.setWrap(true);
						tableFormatBackground_testname.setWrap(true);
						tableFormatBackground2.setBorder(Border.ALL, BorderLineStyle.THIN);
						tableFormatBackground3.setBorder(Border.ALL, BorderLineStyle.THIN);
						tableFormatBackground4.setBorder(Border.ALL, BorderLineStyle.THIN);
						tableFormatBackground_status_pass.setBorder(Border.ALL,
								BorderLineStyle.THIN);
						tableFormatBackground_testname.setBorder(Border.ALL,
								BorderLineStyle.THIN);
						tableFormatBackground4.setBackground(Colour.LIGHT_TURQUOISE);
						DecimalFormat df = new DecimalFormat("#0.00");
						// int j=getarrcount();
						long st = System.currentTimeMillis();
						int pass;
						r.setUp(loc, br, testname);
						logger.info(""+"Set Up over!!********************");
						long et = System.currentTimeMillis();
						double diff = et - st;
						diff /= 1000.0D;
						Label lbl;
						// sheet1.addCell(lbl);
						String yash = df.format(diff);
						diff = Double.parseDouble(yash);
						logger.info(""+arrcount);

						// *****************VALUES fetched in
						// OBJ_RUN*******************************
						// *****************VALUES fetched in
						// OBJ_RUN*******************************
						
						logger.info(""+"**VALUES fetched in OBJ_RUN***");

						// values

						int i_browser = 0;
						while (browser[i_browser] != null) {
							if (browser[i_browser].equals(br)) {
								for (int i = 0; i < l_browser[i_browser]; i++) {
									// logger.info(""+"Value of Browser 1 "
									// + browser[i_browser]);
									// logger.info(""+"result "
									// + result_array[i_browser * 4][i]);
									// logger.info(""+"result scenerio "
									// + result_array[(i_browser * 4) + 1][i]);
									// logger.info(""+"result actual "
									// + result_array[(i_browser * 4) + 2][i]);
									// logger.info(""+"result expected "
									// + result_array[(i_browser * 4) + 3][i]);
								}
								break;
							}
							i_browser++;
						}

						// values

						// ***************retrieving values****************************

						// **********storing values*****************************

						i_browser = 0;
						while (browser[i_browser] != null) {
							logger.info(""+"Inside while obj_run");
							if (browser[i_browser].equals(br)) {
								logger.info(""+"Inside obj_run If");
								pass = 1;
								logger.info(""+"browser[" + (i_browser + 1)
										+ "] in OBJ_RUN " + browser[i_browser]);
								System.out
										.println("OBJ_res Start  $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ Start OBJ_res");
								logger.info(""+"br in OBJ_RUN " + br);
								logger.info(""+"l_browser" + l_browser[i_browser]);
								logger.info(""+"i_browser "+i_browser);
								//ro[i_browser]++;
								logger.info(""+"RO****************************ro is "+ro[i_browser]);
								for (int jo = 0; jo < l_browser[i_browser]; jo++) {
									logger.info(""+"Iteration number "+jo);
									logger.info(""+"Browser : "+br);
									logger.info(""+"result_array[0][jo] "
											+ result_array[i_browser * 4][jo]);
									logger.info(""+"result_array[1][jo] "
											+ result_array[(i_browser * 4) + 1][jo]);
									logger.info(""+"result_array[2][jo] "
											+ result_array[(i_browser * 4) + 2][jo]);
									logger.info(""+"result_array[3][jo] "
											+ result_array[(i_browser * 4) + 3][jo]);
								/*	
									if(Test_called>3)
									{
										lbl = new Label(c, ro[i_browser] + jo, "",
												tableFormatBackground_testname);
										sheet1.addCell(lbl);
										lbl = new Label(c, ro[i_browser]+ jo, "",
												tableFormatBackground_testname);
										sheet1.addCell(lbl);
										lbl = new Label(c, ro[i_browser]+ jo, "",
												tableFormatBackground_testname);
										sheet1.addCell(lbl);
										lbl = new Label(c, ro[i_browser]+ jo, "",
												tableFormatBackground_testname);
										sheet1.addCell(lbl);
									}
									*/
									

									if (result_array[(i_browser * 4) + 0][jo].equals("Fail")) {
										pass= 0;

										if (jo == 0) {
											lbl = new Label(c, ro[i_browser]+ jo, testname,
													tableFormatBackground_testname);
											sheet1.addCell(lbl);
										}
										logger.info(""+"Print stuff");
										lbl = new Label(c + 1, ro[i_browser]+ jo,
												result_array[(i_browser * 4) + 1][jo]);
										logger.info(""+"Print stuff1");
										sheet1.addCell(lbl);
										logger.info(""+"Print stuff2");
										logger.info(""+result_array[1][jo]);
										lbl = new Label(c + 2, ro[i_browser]+ jo,
												result_array[(i_browser * 4) + 0][jo]/*+ro[i_browser]+" loc"+loc*/,
												tableFormatBackground_status_fail);
										sheet1.addCell(lbl);
										logger.info(""+result_array[2][jo]);
										lbl = new Label(c + 3, ro[i_browser]+ jo,
												result_array[(i_browser * 4) + 2][jo],
												tableFormatBackground2);
										sheet1.addCell(lbl);
										lbl = new Label(c + 4, ro[i_browser]+ jo,
												result_array[(i_browser * 4) + 3][jo],
												tableFormatBackground3);

										sheet1.addCell(lbl);
										logger.info(""+result_array[3][jo]);
									} else if (result_array[(i_browser * 4) + 0][jo].equals("")) {
										if (jo == 0) {
											lbl = new Label(c, ro[i_browser]+ jo, testname,
													tableFormatBackground_testname);
											sheet1.addCell(lbl);
										}
										lbl = new Label(c + 1, ro[i_browser]+ jo,
												result_array[(i_browser * 4) + 1][jo]/*+ro[i_browser]+" loc"+loc*/,
												tableFormatBackground4);
										sheet1.addCell(lbl);
										lbl = new Label(c + 2, ro[i_browser]+ jo,
												result_array[(i_browser * 4) + 0][jo],
												tableFormatBackground4);
										sheet1.addCell(lbl);
										lbl = new Label(c + 3, ro[i_browser]+ jo,
												result_array[(i_browser * 4) + 2][jo],
												tableFormatBackground4);
										sheet1.addCell(lbl);
										lbl = new Label(c + 4, ro[i_browser]+ jo,
												result_array[(i_browser * 4) + 3][jo],
												tableFormatBackground4);
										sheet1.addCell(lbl);
									} else {

										if (jo == 0) {
											lbl = new Label(c, ro[i_browser]+ jo, testname,
													tableFormatBackground_testname);
											sheet1.addCell(lbl);

										}
										// logger.info(""+testname);
										lbl = new Label(c + 1, ro[i_browser]+ jo,
												result_array[(i_browser * 4) + 1][jo],
												tableFormatBackground3);
										sheet1.addCell(lbl);
										// logger.info(""+result_scenario[jo]);
										lbl = new Label(c + 2, ro[i_browser]+ jo,
												result_array[(i_browser * 4) + 0][jo]/*+ro[i_browser]+" loc"+loc*/,
												tableFormatBackground_status_pass);
										sheet1.addCell(lbl);
										// logger.info(""+result_actual[jo]);
										lbl = new Label(c + 3, ro[i_browser]+ jo,
												result_array[(i_browser * 4) + 2][jo],
												tableFormatBackground3);
										sheet1.addCell(lbl);
										lbl = new Label(c + 4, ro[i_browser]+ jo,
												result_array[(i_browser * 4) + 3][jo],
												tableFormatBackground3);
										sheet1.addCell(lbl);
										// logger.info(""+result[jo]);

									}
									System.out
											.println("OBJ_res EBD $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ END OBJ_res");

								}
								
								pass_br_wise[i_browser]+=pass;
								lbl = new Label(c, ro[i_browser]+ l_browser[i_browser], "",
										tableFormatBackground_testname);
								sheet1.addCell(lbl);
								//ro[i_browser] =ro[i_browser]+ (arrcount +3);
								logger.info(""+"arrcount end of obj run "+arrcount);
								//ro[i_browser]+=arrcount+1;
								ro[i_browser] =ro[i_browser]+ l_browser[i_browser]+1;
								logger.info(""+"RO wen obj run ends : +br :"+ro[i_browser]+" "+br);
								if(loc!=first_tc)
								{
									ro[i_browser] =ro[i_browser]+ 0;	
									counter_result_sheet[i_browser]++;
									
								}
											break;
							}
							// **********storing values*****************************

							i_browser++;

						}

						// obj duped

						// lbl = new Label(c + 1,ro+arrcount,
						// "The total time for completing this operation: " + diff +
						// " seconds");

						/*
						 * if (r.getStatus() == "Fail") { lbl = new Label(c + 2, ro,
						 * r.getFailMsg()); sheet1.addCell(lbl); lbl = new Label(c, ro,
						 * testname); sheet1.addCell(lbl); lbl = new Label(c + 3, ro,
						 * r.getPassMsg()); sheet1.addCell(lbl); return 0; }
						 */

						// lbl = new Label(c + 1,ro+arrcount,
						// "The total time for completing this operation: " + diff +
						// " seconds");
					

				// values

				// ***************retrieving values****************************

				// **********storing values*****************************

				i_browser = 0;
				while (browser[i_browser] != null) {
				logger.info(""+"Inside while obj_run");
				if (browser[i_browser].equals(br))
						{
						arrcount[i_browser] = 0;
						
						break;
						}
					i_browser++;
					}
						logger.info(""+"Returning value in obj_run start");
						
						int return_value = 0;
						
						/*while (browser[i_browser] != null) {
							if (browser[i_browser].equals(br)) {
								return_value = pass[i_browser];
								break;
							}
							i_browser++;
						}
				*/
						logger.info(""+"Returning value in obj_run **" + return_value);
						return return_value;
					}


	public static int Row_locator_full_scan_input_sheet(String Keyword, String f_file1) throws IOException,
		BiffException {
	//String Sum_onetime_ChargesOption[] = new String[10];
	File ex = new File(f_file1);
	Workbook w = Workbook.getWorkbook(ex);

       Sheet s = w.getSheet(sheetname);

	int totalcount = 0;
	String dummy = "a";
	int ij = 1;
	int cn = 0;
	do {
		if (dummy != null && dummy.length() > 0) {
			dummy = s.getCell(0, ij).getContents();
			ij++;
			try {
				dummy = s.getCell(0, ij).getContents();
			} catch (Exception e) {
				cn = 1;
			}
			totalcount++;
		} else {
			cn = 1;
		}
	} while (cn == 0);

	logger.info("total number of rows--------------->" + totalcount);

	int rowNum = s.getRows();
	// logger.info("***********" +rowNum);
	int colNum = s.getColumns();

	String[][] data = new String[colNum][rowNum];
	int row_index = 0;

	for(int i=0;i<colNum;i++)
	{
	for (int j = 0; j < rowNum; j++) {

		Cell cell = s.getCell(i, j);
		String value = cell.getContents();
		data[i][j] = value;
		if (Keyword.trim().contentEquals(value.trim())) {
			row_index = j;

			int rowval = j + 1;
			row_index = rowval-1;
			logger.info("Row Keyword ---> " + Keyword);
			logger.info("Row Locator ---> " + row_index);
			// return row_index;

		}

	}}
	return row_index;

}

	public static int Row_locator_full_scan(String Keyword, String f_file1, String sheet_name) throws IOException,
			BiffException {
		//String Sum_onetime_ChargesOption[] = new String[10];
		File ex = new File(f_file1);
		Workbook w = Workbook.getWorkbook(ex);
		Sheet s = w.getSheet(sheet_name);

		int totalcount = 0;
		String dummy = "a";
		int ij = 0;
		int cn = 0;
		do {
			if (dummy != null && dummy.length() > 0) {
				dummy = s.getCell(0, ij).getContents();
				ij++;
				try {
					dummy = s.getCell(0, ij).getContents();
				} catch (Exception e) {
					cn = 1;
				}
				totalcount++;
			} else {
				cn = 1;
			}
		} while (cn == 0);

		logger.info("total number of rows--------------->" + totalcount);

		int rowNum = s.getRows();
		// logger.info("***********" +rowNum);
		int colNum = s.getColumns();

		String[][] data = new String[colNum][rowNum];
		int row_index = 0;

		for(int i=0;i<colNum;i++)
		{
		for (int j = 0; j < rowNum; j++) {

			Cell cell = s.getCell(i, j);
			String value = cell.getContents();
			data[i][j] = value;
			if (Keyword.trim().contentEquals(value.trim())) {
				row_index = j;

				int rowval = j + 1;
				row_index = rowval;
				logger.info("Row Keyword ---> " + Keyword);
				logger.info("Row Locator ---> " + rowval);
				// return row_index;

			}

		}}
		return row_index;

	}


	public void Load_Properties_File(String spath) throws IOException
		    {
			 /*
		   File dir1 = new File(".");
			logger.info("Current dir : " + dir1.getCanonicalPath());
			String spath=dir1.getCanonicalPath();
			spath=spath.replaceAll("////","////////"); 
			
			spath=spath+"//"+"Resource//"+"Properties_Files//";
			*/
			spath = spath + "//" + "Resource//" + "Properties_Files";
		 	 path_config = new Properties();
		 	 file_Stream = new FileInputStream(spath + "//properties_Path//"
		 				+ "path_config.properties");
		 	 path_config.load(file_Stream);
			 
		 	ACR= new Properties();
			//file_Stream= new FileInputStream(System.getProperty("user.dir")+"/src/Manage_properties/OR_Car.properties");
			file_Stream=new FileInputStream(spath+"//"+"properties_User//"+"ACR.properties");
			ACR.load(file_Stream);
			
			GPS= new Properties();
			//file_Stream= new FileInputStream(System.getProperty("user.dir")+"/src/Manage_properties/OR_Car.properties");
			file_Stream=new FileInputStream(spath+"//"+"properties_Admin//"+"GPS.properties");
			GPS.load(file_Stream);
		 	 
		 	 
	
				
		    }
	
}