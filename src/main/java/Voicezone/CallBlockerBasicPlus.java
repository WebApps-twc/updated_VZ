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

public class CallBlockerBasicPlus extends CommonFunctions {
                
                String table, tns[];
    String tlimit,username,pwd;
    int tncount,tlim;                                                    
                                
    String phoneline,phoneline_ac,Acccode;
                int rank[]= new int[50];
                

                int c_sequence;
                String name_for_rank[]= new String[40];
                String price, rank_channels;

                public CallBlockerBasicPlus(String path) {
                                this.path = path;
                }

   
                
    	    public String ACRValidation( String br,WebDriver driver) throws Exception
    	    {
    	    	String schk="Fail";
    	    	  Boolean status = driver.findElement(By.id("ACROn")).isSelected();
        		  System.out.println("Initial state: "+status);
        		  Boolean status1=false;
            if (status.equals(false))
             {
        		 status1=true;
        		  
        		  focusClick(driver, driver.findElement(By.id("ACROn")),br);
        		  
        		  focusClick(driver,driver.findElement(By.id("mainSubmitButton")),br);
    				
    			  schk=orderprocess(driver,br);;
        		  
        			  if(schk.equals("Pass"))
        			  
        				  statusTracker(br,"Pass","Verify if enabling ACR is processed successfully","Order is successfully processed from "+ status +" to "+status1,"Order should be successfully processed");
        			  
        			  else
        			  
        				  statusTracker(br,"Fail","Verify if enabling ACR is processed successfully","Order is not successfully processed","Order should be successfully processed");
        			  
        		  
        		  
        			 
        				//  statusTracker("Pass","Verify if cancel button does not retain the changes made","Cancel button is not retaining the changes made","Cancel button should not retain the changes made");
        				  status = driver.findElement(By.id("ACROn")).isSelected();
        				  System.out.println(status);
        				  System.out.println("m here");
        				  if ((status==true)&&(schk=="Pass"))
        				  {
        					  focusClick(driver, driver.findElement(By.id("ACREACROff")),br);
        					 
        				  
        				  focusClick(driver,driver.findElement(By.id("mainSubmitButton")),br);
          				
        				  schk=orderprocess(driver,br);;
        				  System.out.println(status);
        				  status1=false;
        				  }
        				
        				  if(schk.equals("Pass"))
        				  {
        					  statusTracker(br,"Pass","Verify if disabling ACR is processed successfully","Order is successfully processed from "+ status +" to "+status1,"Order should be successfully processed");
        				  }
        				  else
        				  {
        					  statusTracker(br,"Fail","Verify if disabling ACR is processed successfully","Order is not successfully processed","Order should be successfully processed");
        				  }  
        		  }
        		  else
        		  {
        			  
        			
    				  System.out.println("m here");
    				  if ((status==true)&&(schk=="Pass"))
    				    {
    					  focusClick(driver, driver.findElement(By.id("ACREACROff")),br);
    					 
    				  
	    				  focusClick(driver,driver.findElement(By.id("mainSubmitButton")),br);
	      				
	    				  schk=orderprocess(driver,br);;
	    				  System.out.println(status);
	    				  status1=false;
	    				  }
	    				
	    				  if(schk.equals("Pass"))
	    				  {
	    					  statusTracker(br,"Pass","Verify if disabling ACR is processed successfully","Order is successfully processed from "+ status +" to "+status1,"Order should be successfully processed");
	    				  }
	    				  else
	    				  {
	    					  statusTracker(br,"Fail","Verify if disabling ACR is processed successfully","Order is not successfully processed","Order should be successfully processed");
	    				  }  
	    				  status1=true;
	            		  
	            		  focusClick(driver, driver.findElement(By.id("ACROn")),br);
	            		  
	            		  focusClick(driver,driver.findElement(By.id("mainSubmitButton")),br);
	        				
	        			  schk=orderprocess(driver,br);;
	            		  
	            			  if(schk.equals("Pass"))
	            			  
	            				  statusTracker(br,"Pass","Verify if enabling ACR is processed successfully","Order is successfully processed from "+ status +" to "+status1,"Order should be successfully processed");
	            			  
	            			  else
	            			  
	            				  statusTracker(br,"Fail","Verify if enabling ACR is processed successfully","Order is not successfully processed","Order should be successfully processed");
	            		
	            		     
        		  }
         return schk;		  
    	    }  
    	    
    	    public String flowrun( String br,WebDriver driver,String id) throws Exception
    	    {
    	      String schk="Fail";
    	      
    	      
    	    boolean statusdisabled = driver.findElement(By.id("ACREACROff")).isSelected();
  			System.out.println("val" +statusdisabled);
  			boolean statuscw = driver.findElement(By.id("ACROn")).isSelected();
  			System.out.println(statuscw);
  			boolean statuscwcid = driver.findElement(By.id("EACRActivated")).isSelected();
  			System.out.println(statuscwcid);
  			
        		String from,to;
        		if(statusdisabled)
        			from = "Disabled";
        		else if (statuscw)
        			from = "ACR";
        		else
        			from = "EACR";
        			 
        		System.out.println("after selection");
        		to=id;
        		if(to.contains("Off"))
        			to="Disabled";
        		
        		else if(to.contains("On"))
        			to="ACR";
        		else
        			to="EACR";
        		System.out.println("xpath assignment");
        		
        		driver.findElement(By.id(id)).click();
        		 focusClick(driver,driver.findElement(By.id("mainSubmitButton")),br);
        	  	schk=orderprocess(driver,br);;
        	  	
        	  	if(schk.equals("Fail"))
          		{
          			statusTracker(br,"Fail","Verify order processing when switching from "+from+" to "+to,"Order processing has failed","Order should be processed successfully");
          			
          		}
          		else
          		{
          			System.out.println("Mpass");
          			statusTracker(br,"Pass","Verify order processing when switching from "+from+" to "+to,"Order processing has completed successfully","Order should be processed successfully");
          		}
      		 
      		  
      			  
      		  
    	    return schk;	
    	    }
    	    public String EACRValidation( String br,WebDriver driver) throws Exception
    	    {
    	    	System.out.println("In EACR Validation1");
    	    	String schk="Fail";
    	    	boolean statusdisabled = driver.findElement(By.id("ACREACROff")).isSelected();
    			System.out.println(statusdisabled);
    			boolean statusacr = driver.findElement(By.id("ACROn")).isSelected();
    			System.out.println(statusacr);
    			boolean statuseacr = driver.findElement(By.id("EACRActivated")).isSelected();
    			System.out.println(statuseacr);
    			
    			
    			if(statusdisabled)
    			{
    				driver.findElement(By.id("ACROn")).click();
    				 focusClick(driver,driver.findElement(By.id("mainSubmitButton")),br);
    				
    				schk=orderprocess(driver,br);;
    				
    			}
    		try
   	          {
   	          if( driver.findElement(By.id("PlayAudio")).isDisplayed())
   	          {
   	        	System.out.println("In EACR Validation with VM"); 
   	        	schk =flowrun(br,driver, "ACREACROff");
				if(schk.equals("Pass"))
					schk =flowrun(br,driver, "ACROn");
				if(schk.equals("Pass"))
					schk =flowrun(br,driver,"EACRActivated");
				if(schk.equals("Pass"))
					schk =flowrun(br,driver,"PlayAudio");
				if(schk.equals("Pass"))
					schk =flowrun(br,driver, "ACROn");
				if(schk.equals("Pass"))
					schk =flowrun(br,driver, "EACRActivated");
				if(schk.equals("Pass"))
					schk =flowrun(br,driver,"PlayAudio");
				if(schk.equals("Pass"))
					schk =flowrun(br,driver, "EACRActivated");
				if(schk.equals("Pass"))
				schk =flowrun(br,driver, "ACREACROff");
				
				if(schk.equals("Pass"))
					schk =flowrun(br,driver, "EACRActivated");
				if(schk.equals("Pass"))
					schk =flowrun(br,driver,"PlayAudio");
				if(schk.equals("Pass"))
					schk =flowrun(br,driver, "ACROn");
				if(schk.equals("Pass"))
					schk =flowrun(br,driver, "ACREACROff");
   	          }
   	         
   	          else
   	          {
   	        	System.out.println("In EACR Validation without VM"); 
   	        	schk =flowrun(br,driver, "ACREACROff");
				if(schk.equals("Pass"))
					schk =flowrun(br,driver, "ACROn");
				if(schk.equals("Pass"))
					schk =flowrun(br,driver,"EACRActivated");
				if(schk.equals("Pass"))
					schk =flowrun(br,driver,"ACREACROff");
				if(schk.equals("Pass"))
					schk =flowrun(br,driver,"ACROn");
				if(schk.equals("Pass"))
					schk =flowrun(br,driver,"EACRActivated");
				if(schk.equals("Pass"))
					schk =flowrun(br,driver,"ACREACROff");
				if(schk.equals("Pass"))
					schk =flowrun(br,driver,"EACRActivated");
				if(schk.equals("Pass"))
					schk =flowrun(br,driver,"ACROn");
				if(schk.equals("Pass"))
					schk =flowrun(br,driver,"ACREACROff");
   	          }
   	          
   	          }
   	          catch(Exception e)
   	          {
   	        	  statusTracker(br,"Pass","This is not VM Account","","");
   	        	  
   	          }
   	                
    	    	  
    			  
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
    	       // wb.close();

    	     driver.manage().timeouts().implicitlyWait(tlim,TimeUnit.SECONDS); 
    	                            //TimeUnit.SECONDS);
    	     System.out.println("qtest1");
    	        //setUp();
    	        try {
    	    
    	      	  if(first==0)
    	            {
    	      		  login(driver,username,pwd);
    	            }
    	      	  else
    	      	  {
    	      		 focusClick(driver,driver.findElement(By.id("settings-summary")),br); 
    	      	  }
    	      	Thread.sleep(10000);
               
              	int chk=0;
          	      do{
          	           Thread.sleep(1000);       
          	          chk++;
          	          System.out.println(chk);
          	                }
          	      while(driver.findElement(By.xpath("//*[@id='progress']")).isDisplayed());
          	      Thread.sleep(5000);
    	      	focusClick(driver, driver.findElement(By.xpath("(//a[contains(text(),'(edit)')])[1]")),br);
    	        Thread.sleep(5000);
    	        driver.findElement(By.xpath("//div[@id='ContentRefresh']/ul/li[2]/a")).click();
    	                    	    
    	        Thread.sleep(5000);
    	          try
    	          {
    	          if( driver.findElement(By.id("EACRActivated")).isDisplayed())
    	          
    	         EACRValidation(br,driver);
    	          else
    	           ACRValidation(br,driver);
    	          }
    	          catch(Exception e)
    	          {
    	        	  statusTracker(br,"Pass","EACR Feature not present","","");
    	        	  
    	          }
    	                    	
    	       
    	          focusClick(driver,driver.findElement(By.id("mainCancelButton")),br);
    	 first=1;      
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


