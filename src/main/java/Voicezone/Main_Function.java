package Voicezone;

import org.testng.SkipException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Locale;
import java.util.Properties;

import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.read.biff.BiffException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;
import org.apache.log4j.xml.DOMConfigurator;

public class Main_Function extends CommonFunctions{
                
                public int loc;
                
                @BeforeClass
                public void Reader() throws IOException, BiffException
                {
                                
                                DOMConfigurator.configure("Logger.xml");
                            
                            dir1 = new File(".");
                            spath = dir1.getCanonicalPath();
                            spath = spath.replaceAll("////", "////////");
                            logger.info(""+"Path "+spath);
                            Load_Properties_File(spath);
                
                            inputfile=spath+path_config.getProperty("inputSheet");
                            
                            outputfile=spath+path_config.getProperty("resultSheet");
                            logger.info(""+"inputfile "+inputfile+" outputfile "+outputfile);
                            data11 = new File(inputfile);
                            
                            ws11 = new WorkbookSettings();
                            ws11.setLocale(new Locale("er", "ER"));
                            wb11 = Workbook.getWorkbook(data11, ws11);
                            sheet11 = wb11.getSheet("VoiceZone");
                            gr=sheet11.getCell(3,4).getContents();
                            Grid_Status=gr;
                            Sheet_name = sheet11.getName();
                            
                            for(int c=0;c<10;c++)
                            {
                                            total[c]=0;
                                            counter_result_sheet[c]=10;
                                            counter_result_initial[c]=9;
                                            logger.info(""+"RO initialized");
                                            ro[c]=10;
                                            l_browser[c]=0;
                                            pass_br_wise[c]=0;
                                            no_run[c]=0;
                                            black[c]=0;
                                            arrcount[c]=0;
                            }
                            sheetname="VoiceZone";
                            int first_row=Row_locator_full_scan("TEST CASE",inputfile,sheetname);
                            
                            int LastRow=sheet11.getRows();
                            logger.info(""+"Last_row "+LastRow);
                            logger.info(""+"first_row "+first_row);
                            for( int im=first_row;im<LastRow;im++)
                            {
                                            String tc1= sheet11.getCell(3, im).getContents();
                                            logger.info(""+"TC1 "+tc1+" IM variable "+im+" "+sheet11.getCell(0, im).getContents());
                                            if(tc1.equalsIgnoreCase("Y"))
                                            {
                                                            first_tc=im;
                                                            logger.info("first_tc: "+first_tc);
                                                            break;
                                            }
                                            
                            }
                            
                            File Pass_a = new File(Overall_Path + "//" + "//Test_Results"+"//"+"ACR_Pass_Screenshots////");
                            deleteFolder(Pass_a);
                            File Fail_a = new File(Overall_Path + "//" + "//Test_Results"+"//"+"ACR_Fail_Screenshots////");
                            deleteFolder(Fail_a);
                            File Exception_a = new File(Overall_Path + "//"+ "//Test_Results"+"//"+"ACR_Exception_Screenshots////");
                            deleteFolder(Exception_a);
            }
            
            @DataProvider(parallel=true)
            public Object[][] getData() throws IOException, BiffException{
                            logger.info(""+"Getting inside");
                            String no_of_browsers=sheet11.getCell(4,4).getContents();
                            logger.info("no_of_browsers "+no_of_browsers);
                            if(gr.equals("Yes"))
                            {
                                            logger.info(""+"no_of_browsers : "+no_of_browsers);
                                            if(no_of_browsers.contains("1"))
                                            {
                                             final Object data[][]={{"FF"}};
                                             return data;
                                            }
                                            if(no_of_browsers.contains("2"))
                                            {
                                                            final       Object data[][]={{"FF"},{"chrome"}};
                                                            return data;
                                            }
                                            else if(no_of_browsers.contains("3"))
                                            {
                                                            final       Object data[][]={{"FF"},{"chrome"},{"IE"},{""}};
                                                            return data;
                                            }
                                            else if(no_of_browsers.contains("4"))
                                            {
                                                            final       Object data[][]={{"FF"},{"chrome"},{"IE10"},{"IE11"}};
                                                            return data;
                                            }
                                            else if(no_of_browsers.contains("5"))
                                            {
                                                            final       Object data[][]={{"FF"},{"chrome"},{"IE"},{"IE10"},{"IE11"}};
                                                            return data;
                                            }}
                            else if(gr.equals("No"))
                            {
                                            logger.info(""+"Grid option set to NO");
                                            final Object data[][]={{""}};
                                            return data;
                                            
                            }
            
            return null;
            }
            
            @Test(dataProvider="getData",priority=4)
            public  void CallBlockerSelect( String br) throws Exception {
                            loc=8;
                            logger.info(""+"CallBlockerSelect is called");
                            
                            String exec1 = sheet11.getCell(3, loc).getContents();
                            if(exec1.equalsIgnoreCase("N")) {
                                            throw new SkipException("Skipping tests because value is set has N.");
                            }
                            else{
                                            
                                            Validation a = new Validation();
                            
                                            try {
                                                            logger.info(""+"Browser and excel location of test1  is "+br+" and "+loc);
                                                            
                                                            if(gr.equalsIgnoreCase("Yes") && !br.equals(""))
                                                            {                                              
                                                                            Test_called++;
                                                                            logger.info(""+"Test_called in main  is"+Test_called );
                                                                            
                                                                            a.print(inputfile,outputfile+"_"+br+".xls", spath, true,br,loc,Test_called);
                                                                            
                                                            
                                                            }
                                                            else if(gr.equals("No") && br.equals("") && !br.equals("NA"))
                                                            {
                                                                            br=sheet11.getCell(2, loc).getContents();
                                                             logger.info(""+"GRID IS NOT In MAIN!");
                                                             a.print(inputfile,outputfile+"_"+br+".xls",spath, true,br,loc,browserSwitch(br));

                                                                            
                                                            }
                                                            logger.info(""+"Excel location value in test1 is "+loc);
                                                            
                                                            TestCompleted="Yes";
                                                            logger.info(""+"Test completed yes or NO"+TestCompleted);
                                                            
                                                   }
                                            catch (Exception e) {
                                                                        System.out.println("Error: There are issues regarding the IO files. Please verify and try again");
                                                                        logger.info(""+e);
                                                                        exceptionHandler(br,e);
                                                                 }
                            }
            }
            
            @Test(dataProvider="getData",priority=1)
            public  void CallBlockerBasicPlus( String br) throws Exception {
                            loc=9;
                            logger.info(""+"CallBlockerBasicPlus is called");
                            
                            String exec1 = sheet11.getCell(3, loc).getContents();
                            if(exec1.equalsIgnoreCase("N")) {
                                            throw new SkipException("Skipping tests because value is set has N.");
                            }
                            else{
                                            
                                            Validation a = new Validation();
                            
                                            try {
                                                            logger.info(""+"Browser and excel location of test1  is "+br+" and "+loc);
                                                            
                                                            if(gr.equalsIgnoreCase("Yes") && !br.equals(""))
                                                            {                                              
                                                                            Test_called++;
                                                                            logger.info(""+"Test_called in main  is"+Test_called );
                                                                            
                                                                            a.print(inputfile,outputfile+"_"+br+".xls", spath, true,br,loc,Test_called);
                                                                            
                                                            
                                                            }
                                                            else if(gr.equals("No") && br.equals("") && !br.equals("NA"))
                                                            {
                                                                            br=sheet11.getCell(2, loc).getContents();
                                                             logger.info(""+"GRID IS NOT In MAIN!");
                                                             a.print(inputfile,outputfile+"_"+br+".xls",spath, true,br,loc,browserSwitch(br));

                                                                            
                                                            }
                                                            logger.info(""+"Excel location value in test1 is "+loc);
                                                            
                                                            TestCompleted="Yes";
                                                            logger.info(""+"Test completed yes or NO"+TestCompleted);
                                                            
                                                   }
                                            catch (Exception e) {
                                                                        System.out.println("Error: There are issues regarding the IO files. Please verify and try again");
                                                                        logger.info(""+e);
                                                                        exceptionHandler(br,e);
                                                                 }
                            }
            }
            
            @Test(dataProvider="getData",priority=2)
            public  void Nomorobo( String br) throws Exception {
                            loc=10;
                            logger.info(""+"Nomorobo is called");
                            
                            String exec1 = sheet11.getCell(3, loc).getContents();
                            if(exec1.equalsIgnoreCase("N")) {
                                            throw new SkipException("Skipping tests because value is set has N.");
                            }
                            
                            else{
                                            
                                            Validation a = new Validation();
                            
                                            try {
                                                            logger.info(""+"Browser and excel location of test1  is "+br+" and "+loc);
                                                            
                                                            if(gr.equalsIgnoreCase("Yes") && !br.equals(""))
                                                            {                                              
                                                                            Test_called++;
                                                                            logger.info(""+"Test_called in main  is"+Test_called );
                                                                            
                                                                            a.print(inputfile,outputfile+"_"+br+".xls", spath, true,br,loc,Test_called);
                                                                            
                                                            
                                                            }
                                                            else if(gr.equals("No") && br.equals("") && !br.equals("NA"))
                                                            {
                                                                            br=sheet11.getCell(2, loc).getContents();
                                                             logger.info(""+"GRID IS NOT In MAIN!");
                                                             a.print(inputfile,outputfile+"_"+br+".xls",spath, true,br,loc,browserSwitch(br));

                                                                            
                                                            }
                                                            logger.info(""+"Excel location value in test1 is "+loc);
                                                            
                                                            TestCompleted="Yes";
                                                            logger.info(""+"Test completed yes or NO"+TestCompleted);
                                                            
                                                   }
                                            catch (Exception e) {
                                                                        System.out.println("Error: There are issues regarding the IO files. Please verify and try again");
                                                                        logger.info(""+e);
                                                                        exceptionHandler(br,e);
                                                                 }
                            }
            }
            @Test(dataProvider="getData",priority=5)
            public  void SelectiveCallAcceptance( String br) throws Exception {
                            loc=11;
                            logger.info(""+"CallBlockerAccept is called");
                            
                            String exec1 = sheet11.getCell(3, loc).getContents();
                            if(exec1.equalsIgnoreCase("N")) {
                                            throw new SkipException("Skipping tests because value is set has N.");
                            }
                            
                            else{
                                            
                                            Validation a = new Validation();
                            
                                            try {
                                                            logger.info(""+"Browser and excel location of test1  is "+br+" and "+loc);
                                                            
                                                            if(gr.equalsIgnoreCase("Yes") && !br.equals(""))
                                                            {                                              
                                                                            Test_called++;
                                                                            logger.info(""+"Test_called in main  is"+Test_called );
                                                                            
                                                                            a.print(inputfile,outputfile+"_"+br+".xls", spath, true,br,loc,Test_called);
                                                                            
                                                            
                                                            }
                                                            else if(gr.equals("No") && br.equals("") && !br.equals("NA"))
                                                            {
                                                                            br=sheet11.getCell(2, loc).getContents();
                                                             logger.info(""+"GRID IS NOT In MAIN!");
                                                             a.print(inputfile,outputfile+"_"+br+".xls",spath, true,br,loc,browserSwitch(br));

                                                                            
                                                            }
                                                            logger.info(""+"Excel location value in test1 is "+loc);
                                                            
                                                            TestCompleted="Yes";
                                                            logger.info(""+"Test completed yes or NO"+TestCompleted);
                                                            
                                                   }
                                            catch (Exception e) {
                                                                        System.out.println("Error: There are issues regarding the IO files. Please verify and try again");
                                                                        logger.info(""+e);
                                                                        exceptionHandler(br,e);
                                                                 }
                            }
            }
            @Test(dataProvider="getData",priority=6)
            public  void VoicemailTranscription( String br) throws Exception {
                            loc=13;
                            logger.info(""+"VoicetotoText is called");
                            
                            String exec1 = sheet11.getCell(3, loc).getContents();
                            if(exec1.equalsIgnoreCase("N")) {
                                            throw new SkipException("Skipping tests because value is set has N.");
                            }
                            
                            else{
                                            
                                            Validation a = new Validation();
                            
                                            try {
                                                            logger.info(""+"Browser and excel location of test1  is "+br+" and "+loc);
                                                            
                                                            if(gr.equalsIgnoreCase("Yes") && !br.equals(""))
                                                            {                                              
                                                                            Test_called++;
                                                                            logger.info(""+"Test_called in main  is"+Test_called );
                                                                            
                                                                            a.print(inputfile,outputfile+"_"+br+".xls", spath, true,br,loc,Test_called);
                                                                            
                                                            
                                                            }
                                                            else if(gr.equals("No") && br.equals("") && !br.equals("NA"))
                                                            {
                                                                            br=sheet11.getCell(2, loc).getContents();
                                                             logger.info(""+"GRID IS NOT In MAIN!");
                                                             a.print(inputfile,outputfile+"_"+br+".xls",spath, true,br,loc,browserSwitch(br));

                                                                            
                                                            }
                                                            logger.info(""+"Excel location value in test1 is "+loc);
                                                            
                                                            TestCompleted="Yes";
                                                            logger.info(""+"Test completed yes or NO"+TestCompleted);
                                                            
                                                   }
                                            catch (Exception e) {
                                                                        System.out.println("Error: There are issues regarding the IO files. Please verify and try again");
                                                                        logger.info(""+e);
                                                                        exceptionHandler(br,e);
                                                                 }
                            }
            }
            
            @Test(dataProvider="getData",priority=7)
            public  void NotifybyEmail( String br) throws Exception {
                            loc=14;
                            logger.info(""+"NotifybyEmail is called");
                            
                            String exec1 = sheet11.getCell(3, loc).getContents();
                            if(exec1.equalsIgnoreCase("N")) {
                                            throw new SkipException("Skipping tests because value is set has N.");
                            }
                            
                            else{
                                            
                                            Validation a = new Validation();
                            
                                            try {
                                                            logger.info(""+"Browser and excel location of test1  is "+br+" and "+loc);
                                                            
                                                            if(gr.equalsIgnoreCase("Yes") && !br.equals(""))
                                                            {                                              
                                                                            Test_called++;
                                                                            logger.info(""+"Test_called in main  is"+Test_called );
                                                                            
                                                                            a.print(inputfile,outputfile+"_"+br+".xls", spath, true,br,loc,Test_called);
                                                                            
                                                            
                                                            }
                                                            else if(gr.equals("No") && br.equals("") && !br.equals("NA"))
                                                            {
                                                                            br=sheet11.getCell(2, loc).getContents();
                                                             logger.info(""+"GRID IS NOT In MAIN!");
                                                             a.print(inputfile,outputfile+"_"+br+".xls",spath, true,br,loc,browserSwitch(br));

                                                                            
                                                            }
                                                            logger.info(""+"Excel location value in test1 is "+loc);
                                                            
                                                            TestCompleted="Yes";
                                                            logger.info(""+"Test completed yes or NO"+TestCompleted);
                                                            
                                                   }
                                            catch (Exception e) {
                                                                        System.out.println("Error: There are issues regarding the IO files. Please verify and try again");
                                                                        logger.info(""+e);
                                                                        exceptionHandler(br,e);
                                                                 }
                            }
            }
            @Test(dataProvider="getData",priority=8)
            public  void NotifybyText( String br) throws Exception {
                            loc=15;
                            logger.info(""+"NotifybyText is called");
                            
                            String exec1 = sheet11.getCell(3, loc).getContents();
                            if(exec1.equalsIgnoreCase("N")) {
                                            throw new SkipException("Skipping tests because value is set has N.");
                            }
                            
                            else{
                                            
                                            Validation a = new Validation();
                            
                                            try {
                                                            logger.info(""+"Browser and excel location of test1  is "+br+" and "+loc);
                                                            
                                                            if(gr.equalsIgnoreCase("Yes") && !br.equals(""))
                                                            {                                              
                                                                            Test_called++;
                                                                            logger.info(""+"Test_called in main  is"+Test_called );
                                                                            
                                                                            a.print(inputfile,outputfile+"_"+br+".xls", spath, true,br,loc,Test_called);
                                                                            
                                                            
                                                            }
                                                            else if(gr.equals("No") && br.equals("") && !br.equals("NA"))
                                                            {
                                                                            br=sheet11.getCell(2, loc).getContents();
                                                             logger.info(""+"GRID IS NOT In MAIN!");
                                                             a.print(inputfile,outputfile+"_"+br+".xls",spath, true,br,loc,browserSwitch(br));

                                                                            
                                                            }
                                                            logger.info(""+"Excel location value in test1 is "+loc);
                                                            
                                                            TestCompleted="Yes";
                                                            logger.info(""+"Test completed yes or NO"+TestCompleted);
                                                            
                                                   }
                                            catch (Exception e) {
                                                                        System.out.println("Error: There are issues regarding the IO files. Please verify and try again");
                                                                        logger.info(""+e);
                                                                        exceptionHandler(br,e);
                                                                 }
                            }
            }
            @Test(dataProvider="getData",priority=9)
            public  void PinChange( String br) throws Exception {
                            loc=16;
                            logger.info(""+"PinChange is called");
                            
                            String exec1 = sheet11.getCell(3, loc).getContents();
                            if(exec1.equalsIgnoreCase("N")) {
                                            throw new SkipException("Skipping tests because value is set has N.");
                            }
                            
                            else{
                                            
                                            Validation a = new Validation();
                            
                                            try {
                                                            logger.info(""+"Browser and excel location of test1  is "+br+" and "+loc);
                                                            
                                                            if(gr.equalsIgnoreCase("Yes") && !br.equals(""))
                                                            {                                              
                                                                            Test_called++;
                                                                            logger.info(""+"Test_called in main  is"+Test_called );
                                                                            
                                                                            a.print(inputfile,outputfile+"_"+br+".xls", spath, true,br,loc,Test_called);
                                                                            
                                                            
                                                            }
                                                            else if(gr.equals("No") && br.equals("") && !br.equals("NA"))
                                                            {
                                                                            br=sheet11.getCell(2, loc).getContents();
                                                             logger.info(""+"GRID IS NOT In MAIN!");
                                                             a.print(inputfile,outputfile+"_"+br+".xls",spath, true,br,loc,browserSwitch(br));

                                                                            
                                                            }
                                                            logger.info(""+"Excel location value in test1 is "+loc);
                                                            
                                                            TestCompleted="Yes";
                                                            logger.info(""+"Test completed yes or NO"+TestCompleted);
                                                            
                                                   }
                                            catch (Exception e) {
                                                                        System.out.println("Error: There are issues regarding the IO files. Please verify and try again");
                                                                        logger.info(""+e);
                                                                        exceptionHandler(br,e);
                                                                 }
                            }
            }
            
            @Test(dataProvider="getData",priority=10)
            public  void PinSkip( String br) throws Exception {
                            loc=17;
                            logger.info(""+"PinSkip is called");
                            
                            String exec1 = sheet11.getCell(3, loc).getContents();
                            if(exec1.equalsIgnoreCase("N")) {
                                            throw new SkipException("Skipping tests because value is set has N.");
                            }
                            
                            else{
                                            
                                            Validation a = new Validation();
                            
                                            try {
                                                            logger.info(""+"Browser and excel location of test1  is "+br+" and "+loc);
                                                            
                                                            if(gr.equalsIgnoreCase("Yes") && !br.equals(""))
                                                            {                                              
                                                                            Test_called++;
                                                                            logger.info(""+"Test_called in main  is"+Test_called );
                                                                            
                                                                            a.print(inputfile,outputfile+"_"+br+".xls", spath, true,br,loc,Test_called);
                                                                            
                                                            
                                                            }
                                                            else if(gr.equals("No") && br.equals("") && !br.equals("NA"))
                                                            {
                                                                            br=sheet11.getCell(2, loc).getContents();
                                                             logger.info(""+"GRID IS NOT In MAIN!");
                                                             a.print(inputfile,outputfile+"_"+br+".xls",spath, true,br,loc,browserSwitch(br));

                                                                            
                                                            }
                                                            logger.info(""+"Excel location value in test1 is "+loc);
                                                            
                                                            TestCompleted="Yes";
                                                            logger.info(""+"Test completed yes or NO"+TestCompleted);
                                                            
                                                   }
                                            catch (Exception e) {
                                                                        System.out.println("Error: There are issues regarding the IO files. Please verify and try again");
                                                                        logger.info(""+e);
                                                                        exceptionHandler(br,e);
                                                                 }
                            }
            }
            @Test(dataProvider="getData",priority=11)
            public  void AnswerAnywhere( String br) throws Exception {
                            loc=18;
                            logger.info(""+"AnswerAnywhere is called");
                            
                            String exec1 = sheet11.getCell(3, loc).getContents();
                            if(exec1.equalsIgnoreCase("N")) {
                                            throw new SkipException("Skipping tests because value is set has N.");
                            }
                            
                            else{
                                            
                                            Validation a = new Validation();
                            
                                            try {
                                                            logger.info(""+"Browser and excel location of test1  is "+br+" and "+loc);
                                                            
                                                            if(gr.equalsIgnoreCase("Yes") && !br.equals(""))
                                                            {                                              
                                                                            Test_called++;
                                                                            logger.info(""+"Test_called in main  is"+Test_called );
                                                                            
                                                                            a.print(inputfile,outputfile+"_"+br+".xls", spath, true,br,loc,Test_called);
                                                                            
                                                            
                                                            }
                                                            else if(gr.equals("No") && br.equals("") && !br.equals("NA"))
                                                            {
                                                                            br=sheet11.getCell(2, loc).getContents();
                                                             logger.info(""+"GRID IS NOT In MAIN!");
                                                             a.print(inputfile,outputfile+"_"+br+".xls",spath, true,br,loc,browserSwitch(br));

                                                                            
                                                            }
                                                            logger.info(""+"Excel location value in test1 is "+loc);
                                                            
                                                            TestCompleted="Yes";
                                                            logger.info(""+"Test completed yes or NO"+TestCompleted);
                                                            
                                                   }
                                            catch (Exception e) {
                                                                        System.out.println("Error: There are issues regarding the IO files. Please verify and try again");
                                                                        logger.info(""+e);
                                                                        exceptionHandler(br,e);
                                                                 }
                            }
            }
            
            @Test(dataProvider="getData",priority=12)
            public  void CallForwardingBusy( String br) throws Exception {
                            loc=19;
                            logger.info(""+"CallForwardingBusy is called");
                            
                            String exec1 = sheet11.getCell(3, loc).getContents();
                            if(exec1.equalsIgnoreCase("N")) {
                                            throw new SkipException("Skipping tests because value is set has N.");
                            }
                            
                            else{
                                            
                                            Validation a = new Validation();
                            
                                            try {
                                                            logger.info(""+"Browser and excel location of test1  is "+br+" and "+loc);
                                                            
                                                            if(gr.equalsIgnoreCase("Yes") && !br.equals(""))
                                                            {                                              
                                                                            Test_called++;
                                                                            logger.info(""+"Test_called in main  is"+Test_called );
                                                                            
                                                                            a.print(inputfile,outputfile+"_"+br+".xls", spath, true,br,loc,Test_called);
                                                                            
                                                            
                                                            }
                                                            else if(gr.equals("No") && br.equals("") && !br.equals("NA"))
                                                            {
                                                                            br=sheet11.getCell(2, loc).getContents();
                                                             logger.info(""+"GRID IS NOT In MAIN!");
                                                             a.print(inputfile,outputfile+"_"+br+".xls",spath, true,br,loc,browserSwitch(br));

                                                                            
                                                            }
                                                            logger.info(""+"Excel location value in test1 is "+loc);
                                                            
                                                            TestCompleted="Yes";
                                                            logger.info(""+"Test completed yes or NO"+TestCompleted);
                                                            
                                                   }
                                            catch (Exception e) {
                                                                        System.out.println("Error: There are issues regarding the IO files. Please verify and try again");
                                                                        logger.info(""+e);
                                                                        exceptionHandler(br,e);
                                                                 }
                            }
            }
            
            @Test(dataProvider="getData",priority=13)
            public  void CallForwardingNoAnswer( String br) throws Exception {
                            loc=20;
                            logger.info(""+"CallForwardingNoAnswer is called");
                            
                            String exec1 = sheet11.getCell(3, loc).getContents();
                            if(exec1.equalsIgnoreCase("N")) {
                                            throw new SkipException("Skipping tests because value is set has N.");
                            }
                            
                            else{
                                            
                                            Validation a = new Validation();
                            
                                            try {
                                                            logger.info(""+"Browser and excel location of test1  is "+br+" and "+loc);
                                                            
                                                            if(gr.equalsIgnoreCase("Yes") && !br.equals(""))
                                                            {                                              
                                                                            Test_called++;
                                                                            logger.info(""+"Test_called in main  is"+Test_called );
                                                                            
                                                                            a.print(inputfile,outputfile+"_"+br+".xls", spath, true,br,loc,Test_called);
                                                                            
                                                            
                                                            }
                                                            else if(gr.equals("No") && br.equals("") && !br.equals("NA"))
                                                            {
                                                                            br=sheet11.getCell(2, loc).getContents();
                                                             logger.info(""+"GRID IS NOT In MAIN!");
                                                             a.print(inputfile,outputfile+"_"+br+".xls",spath, true,br,loc,browserSwitch(br));

                                                                            
                                                            }
                                                            logger.info(""+"Excel location value in test1 is "+loc);
                                                            
                                                            TestCompleted="Yes";
                                                            logger.info(""+"Test completed yes or NO"+TestCompleted);
                                                            
                                                   }
                                            catch (Exception e) {
                                                                        System.out.println("Error: There are issues regarding the IO files. Please verify and try again");
                                                                        logger.info(""+e);
                                                                        exceptionHandler(br,e);
                                                                 }
                            }
            }
            
            @Test(dataProvider="getData",priority=14)
            public  void SelectiveCallForwarding( String br) throws Exception {
                            loc=21;
                            logger.info(""+"SelectiveCallForwarding is called");
                            
                            String exec1 = sheet11.getCell(3, loc).getContents();
                            if(exec1.equalsIgnoreCase("N")) {
                                            throw new SkipException("Skipping tests because value is set has N.");
                            }
                            
                            else{
                                            
                                            Validation a = new Validation();
                            
                                            try {
                                                            logger.info(""+"Browser and excel location of test1  is "+br+" and "+loc);
                                                            
                                                            if(gr.equalsIgnoreCase("Yes") && !br.equals(""))
                                                            {                                              
                                                                            Test_called++;
                                                                            logger.info(""+"Test_called in main  is"+Test_called );
                                                                            
                                                                            a.print(inputfile,outputfile+"_"+br+".xls", spath, true,br,loc,Test_called);
                                                                            
                                                            
                                                            }
                                                            else if(gr.equals("No") && br.equals("") && !br.equals("NA"))
                                                            {
                                                                            br=sheet11.getCell(2, loc).getContents();
                                                             logger.info(""+"GRID IS NOT In MAIN!");
                                                             a.print(inputfile,outputfile+"_"+br+".xls",spath, true,br,loc,browserSwitch(br));

                                                                            
                                                            }
                                                            logger.info(""+"Excel location value in test1 is "+loc);
                                                            
                                                            TestCompleted="Yes";
                                                            logger.info(""+"Test completed yes or NO"+TestCompleted);
                                                            
                                                   }
                                            catch (Exception e) {
                                                                        System.out.println("Error: There are issues regarding the IO files. Please verify and try again");
                                                                        logger.info(""+e);
                                                                        exceptionHandler(br,e);
                                                                 }
                            }
            }
            
            @Test(dataProvider="getData",priority=15)
            public  void CallForwardingUnconditional( String br) throws Exception {
                            loc=22;
                            logger.info(""+"CallForwardingUnconditional is called");
                            
                            String exec1 = sheet11.getCell(3, loc).getContents();
                            if(exec1.equalsIgnoreCase("N")) {
                                            throw new SkipException("Skipping tests because value is set has N.");
                            }
                            
                            else{
                                            
                                            Validation a = new Validation();
                            
                                            try {
                                                            logger.info(""+"Browser and excel location of test1  is "+br+" and "+loc);
                                                            
                                                            if(gr.equalsIgnoreCase("Yes") && !br.equals(""))
                                                            {                                              
                                                                            Test_called++;
                                                                            logger.info(""+"Test_called in main  is"+Test_called );
                                                                            
                                                                            a.print(inputfile,outputfile+"_"+br+".xls", spath, true,br,loc,Test_called);
                                                                            
                                                            
                                                            }
                                                            else if(gr.equals("No") && br.equals("") && !br.equals("NA"))
                                                            {
                                                                            br=sheet11.getCell(2, loc).getContents();
                                                             logger.info(""+"GRID IS NOT In MAIN!");
                                                             a.print(inputfile,outputfile+"_"+br+".xls",spath, true,br,loc,browserSwitch(br));

                                                                            
                                                            }
                                                            logger.info(""+"Excel location value in test1 is "+loc);
                                                            
                                                            TestCompleted="Yes";
                                                            logger.info(""+"Test completed yes or NO"+TestCompleted);
                                                            
                                                   }
                                            catch (Exception e) {
                                                                        System.out.println("Error: There are issues regarding the IO files. Please verify and try again");
                                                                        logger.info(""+e);
                                                                        exceptionHandler(br,e);
                                                                 }
                            }
            }
            
            @Test(dataProvider="getData",priority=16)
            public  void CallWaiting( String br) throws Exception {
                            loc=23;
                            logger.info(""+"CallWaiting is called");
                            
                            String exec1 = sheet11.getCell(3, loc).getContents();
                            if(exec1.equalsIgnoreCase("N")) {
                                            throw new SkipException("Skipping tests because value is set has N.");
                            }
                            
                            else{
                                            
                                            Validation a = new Validation();
                            
                                            try {
                                                            logger.info(""+"Browser and excel location of test1  is "+br+" and "+loc);
                                                            
                                                            if(gr.equalsIgnoreCase("Yes") && !br.equals(""))
                                                            {                                              
                                                                            Test_called++;
                                                                            logger.info(""+"Test_called in main  is"+Test_called );
                                                                            
                                                                            a.print(inputfile,outputfile+"_"+br+".xls", spath, true,br,loc,Test_called);
                                                                            
                                                            
                                                            }
                                                            else if(gr.equals("No") && br.equals("") && !br.equals("NA"))
                                                            {
                                                                            br=sheet11.getCell(2, loc).getContents();
                                                             logger.info(""+"GRID IS NOT In MAIN!");
                                                             a.print(inputfile,outputfile+"_"+br+".xls",spath, true,br,loc,browserSwitch(br));

                                                                            
                                                            }
                                                            logger.info(""+"Excel location value in test1 is "+loc);
                                                            
                                                            TestCompleted="Yes";
                                                            logger.info(""+"Test completed yes or NO"+TestCompleted);
                                                            
                                                   }
                                            catch (Exception e) {
                                                                        System.out.println("Error: There are issues regarding the IO files. Please verify and try again");
                                                                        logger.info(""+e);
                                                                        exceptionHandler(br,e);
                                                                 }
                            }
            }
            
            @Test(dataProvider="getData",priority=17)
            public  void DistinctiveRing( String br) throws Exception {
                            loc=24;
                            logger.info(""+"DistinctiveRing is called");
                            
                            String exec1 = sheet11.getCell(3, loc).getContents();
                            if(exec1.equalsIgnoreCase("N")) {
                                            throw new SkipException("Skipping tests because value is set has N.");
                            }
                            
                            else{
                                            
                                            Validation a = new Validation();
                            
                                            try {
                                                            logger.info(""+"Browser and excel location of test1  is "+br+" and "+loc);
                                                            
                                                            if(gr.equalsIgnoreCase("Yes") && !br.equals(""))
                                                            {                                              
                                                                            Test_called++;
                                                                            logger.info(""+"Test_called in main  is"+Test_called );
                                                                            
                                                                            a.print(inputfile,outputfile+"_"+br+".xls", spath, true,br,loc,Test_called);
                                                                            
                                                            
                                                            }
                                                            else if(gr.equals("No") && br.equals("") && !br.equals("NA"))
                                                            {
                                                                            br=sheet11.getCell(2, loc).getContents();
                                                             logger.info(""+"GRID IS NOT In MAIN!");
                                                             a.print(inputfile,outputfile+"_"+br+".xls",spath, true,br,loc,browserSwitch(br));

                                                                            
                                                            }
                                                            logger.info(""+"Excel location value in test1 is "+loc);
                                                            
                                                            TestCompleted="Yes";
                                                            logger.info(""+"Test completed yes or NO"+TestCompleted);
                                                            
                                                   }
                                            catch (Exception e) {
                                                                        System.out.println("Error: There are issues regarding the IO files. Please verify and try again");
                                                                        logger.info(""+e);
                                                                        exceptionHandler(br,e);
                                                                 }
                            }
            }
            
            @Test(dataProvider="getData",priority=3)
            public  void OutBoundCallerid( String br) throws Exception {
                            loc=12;
                            logger.info(""+"OutBoundCallerid is called");
                            
                            String exec1 = sheet11.getCell(3, loc).getContents();
                            if(exec1.equalsIgnoreCase("N")) {
                                            throw new SkipException("Skipping tests because value is set has N.");
                            }
                            
                            else{
                                            
                                            Validation a = new Validation();
                            
                                            try {
                                                            logger.info(""+"Browser and excel location of test1  is "+br+" and "+loc);
                                                            
                                                            if(gr.equalsIgnoreCase("Yes") && !br.equals(""))
                                                            {                                              
                                                                            Test_called++;
                                                                            logger.info(""+"Test_called in main  is"+Test_called );
                                                                            
                                                                            a.print(inputfile,outputfile+"_"+br+".xls", spath, true,br,loc,Test_called);
                                                                            
                                                            
                                                            }
                                                            else if(gr.equals("No") && br.equals("") && !br.equals("NA"))
                                                            {
                                                                            br=sheet11.getCell(2, loc).getContents();
                                                             logger.info(""+"GRID IS NOT In MAIN!");
                                                             a.print(inputfile,outputfile+"_"+br+".xls",spath, true,br,loc,browserSwitch(br));

                                                                            
                                                            }
                                                            logger.info(""+"Excel location value in test1 is "+loc);
                                                            
                                                            TestCompleted="Yes";
                                                            logger.info(""+"Test completed yes or NO"+TestCompleted);
                                                            
                                                   }
                                            catch (Exception e) {
                                                                        System.out.println("Error: There are issues regarding the IO files. Please verify and try again");
                                                                        logger.info(""+e);
                                                                        exceptionHandler(br,e);
                                                                 }
                            }
            }
            
            @Test(dataProvider="getData",priority=19)
            public  void ThreeWayCalling( String br) throws Exception {
                            loc=25;
                            logger.info(""+"ThreeWayCalling is called");
                            
                            String exec1 = sheet11.getCell(3, loc).getContents();
                            if(exec1.equalsIgnoreCase("N")) {
                                            throw new SkipException("Skipping tests because value is set has N.");
                            }
                            
                            else{
                                            
                                            Validation a = new Validation();
                            
                                            try {
                                                            logger.info(""+"Browser and excel location of test1  is "+br+" and "+loc);
                                                            
                                                            if(gr.equalsIgnoreCase("Yes") && !br.equals(""))
                                                            {                                              
                                                                            Test_called++;
                                                                            logger.info(""+"Test_called in main  is"+Test_called );
                                                                            
                                                                            a.print(inputfile,outputfile+"_"+br+".xls", spath, true,br,loc,Test_called);
                                                                            
                                                            
                                                            }
                                                            else if(gr.equals("No") && br.equals("") && !br.equals("NA"))
                                                            {
                                                                            br=sheet11.getCell(2, loc).getContents();
                                                             logger.info(""+"GRID IS NOT In MAIN!");
                                                             a.print(inputfile,outputfile+"_"+br+".xls",spath, true,br,loc,browserSwitch(br));

                                                                            
                                                            }
                                                            logger.info(""+"Excel location value in test1 is "+loc);
                                                            
                                                            TestCompleted="Yes";
                                                            logger.info(""+"Test completed yes or NO"+TestCompleted);
                                                            
                                                   }
                                            catch (Exception e) {
                                                                        System.out.println("Error: There are issues regarding the IO files. Please verify and try again");
                                                                        logger.info(""+e);
                                                                        exceptionHandler(br,e);
                                                                 }
                            }
            }
            @Test(dataProvider="getData",priority=20)
            public  void SpeedDial( String br) throws Exception {
                            loc=26;
                            logger.info(""+"SpeedDial is called");
                            
                            String exec1 = sheet11.getCell(3, loc).getContents();
                            if(exec1.equalsIgnoreCase("N")) {
                                            throw new SkipException("Skipping tests because value is set has N.");
                            }
                            
                            else{
                                            
                                            Validation a = new Validation();
                            
                                            try {
                                                            logger.info(""+"Browser and excel location of test1  is "+br+" and "+loc);
                                                            
                                                            if(gr.equalsIgnoreCase("Yes") && !br.equals(""))
                                                            {                                              
                                                                            Test_called++;
                                                                            logger.info(""+"Test_called in main  is"+Test_called );
                                                                            
                                                                            a.print(inputfile,outputfile+"_"+br+".xls", spath, true,br,loc,Test_called);
                                                                            
                                                            
                                                            }
                                                            else if(gr.equals("No") && br.equals("") && !br.equals("NA"))
                                                            {
                                                                            br=sheet11.getCell(2, loc).getContents();
                                                             logger.info(""+"GRID IS NOT In MAIN!");
                                                             a.print(inputfile,outputfile+"_"+br+".xls",spath, true,br,loc,browserSwitch(br));

                                                                            
                                                            }
                                                            logger.info(""+"Excel location value in test1 is "+loc);
                                                            
                                                            TestCompleted="Yes";
                                                            logger.info(""+"Test completed yes or NO"+TestCompleted);
                                                            
                                                   }
                                            catch (Exception e) {
                                                                        System.out.println("Error: There are issues regarding the IO files. Please verify and try again");
                                                                        logger.info(""+e);
                                                                        exceptionHandler(br,e);
                                                                 }
                            }  
            }
/*@AfterTest
public void tear() {
	try {
		//drive.quit();
		drive.close();
	} catch (Exception e) {
		logger.info("Browser has already been closed");
		// logger.info(e);
	}
}*/
            @Override
            public void execute(String br, WebDriver paramDriver, String url, int loc, String name1)
                                            throws Exception {
                            // TODO Auto-generated method stub
                            
            }
           
            }
