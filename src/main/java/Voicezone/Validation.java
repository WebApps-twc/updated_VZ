package Voicezone;



import java.io.File;
	//import java.io.PrintStream;
	import java.text.DecimalFormat;
import java.util.Locale;

import org.openqa.selenium.WebDriver;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

	public class Validation  extends CommonFunctions
	{
	 
		static double diff = 0.0D;
		static double diff1 = 0.0D;
		
		public void execute(String br, WebDriver driver, String url,
				int loc, String name1)
			  {
		 	  }
		public void closeWorkbook(String path, String output,String OVerall_Path) throws Exception
		{
			String name1;
			File data1 = new File(output);
			WorkbookSettings ws1 = new WorkbookSettings();
			File data;
			
			ws1.setLocale(new Locale("er", "ER"));
			Workbook wb1 = Workbook.getWorkbook(data1, ws1); 
		}
		
		
		public void print(String path, String output, String Overall_Path, boolean firstpage,String br,int loc,int Test_called)
				throws Exception {


			
			  logger.info(""+" Test_called value inside validation"+Test_called);
						logger.info(""+"^^^^^^^^^^^ Br  and loc value validation is ^^^^^^^^^" +br+" "+loc);
						logger.info(""+"ro[0] is"+ro[0]);
						logger.info(""+"ro[1] is"+ro[1]);
						String name1;
						File data1 = new File(path);
						WorkbookSettings ws1 = new WorkbookSettings();
						File data;
						
						ws1.setLocale(new Locale("er", "ER"));
						Workbook wb1 = Workbook.getWorkbook(data1, ws1); 
						Sheet sheet2 = wb1.getSheet(sheetname);
						Grid_Status=sheet2.getCell(3,4).getContents();
						
						//Grid_Status="No";
						logger.info(""+"validation called");
						WritableWorkbook workbook=null;;
						WritableWorkbook copy=null;;
						WritableSheet sheet1;
						WritableFont TableFormat;
						WritableFont TableFormat1;
						WritableFont TableFormat_summary;
						WritableFont TableFormat1_heading;
						WritableFont TableFormat1_spaceline;
						WritableCellFormat tfb;
						WritableCellFormat tfb1;
						WritableCellFormat tfb1_heading ;
						WritableCellFormat tfb_spaceline;
						WritableCellFormat tfb_spaceline_black;
						WritableCellFormat tfb1_summary ;
						Label lbl;
						

						DecimalFormat df = new DecimalFormat("#0.0");
						String notinit = "Test set was not initiated";
						long st = 0L, et = 0L;
						
			
						TableFormat = new WritableFont(WritableFont.ARIAL, 9,
								WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE, Colour.BLACK);
						
						TableFormat1 = new WritableFont(WritableFont.ARIAL, 10,
								WritableFont.BOLD, false , UnderlineStyle.NO_UNDERLINE,
								Colour.WHITE);
						 TableFormat_summary = new WritableFont(WritableFont.ARIAL, 10,
								WritableFont.NO_BOLD, false , UnderlineStyle.NO_UNDERLINE,
								Colour.RED);
						 TableFormat1_heading = new WritableFont(WritableFont.TAHOMA, 11,
								WritableFont.BOLD, false , UnderlineStyle.NO_UNDERLINE,
								Colour.WHITE);
					 TableFormat1_spaceline = new WritableFont(WritableFont.TAHOMA, 11,
								WritableFont.BOLD, false , UnderlineStyle.NO_UNDERLINE,
								Colour.WHITE);
						 tfb = new WritableCellFormat();
						 tfb1 = new WritableCellFormat();
						 tfb1_heading = new WritableCellFormat();
						 tfb_spaceline = new WritableCellFormat();
						 tfb_spaceline_black = new WritableCellFormat();
					      tfb1_summary = new WritableCellFormat();
						
						  Workbook workbook_rest_testcase;
						  //WritableWorkbook copy=null;
						logger.info(""+"testcalled value iz : "+Test_called);
				          if(Test_called<2)
						 //if(first_tc==loc || Test_called<2)
				          {
				        	  System.out.println(" output name1 "+output);
							data = new File(output);
							 workbook = Workbook.createWorkbook(data);
							sheet1 = workbook.createSheet("Results", 0);
				          }
				          else
				          {
				          	 System.out.println(" output name2 "+output);
				          	 data = new File(output);
				   			Workbook workbook1 = Workbook.getWorkbook(data);

				               copy = Workbook.createWorkbook(data, workbook1);

				               sheet1 = copy.getSheet("Results"); 
				          		
				          }
		 
		
				          TableFormat = new WritableFont(WritableFont.ARIAL, 9,
									WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE, Colour.BLACK);
							
							TableFormat1 = new WritableFont(WritableFont.ARIAL, 10,
									WritableFont.BOLD, false , UnderlineStyle.NO_UNDERLINE,
									Colour.WHITE);
							 TableFormat_summary = new WritableFont(WritableFont.ARIAL, 10,
									WritableFont.NO_BOLD, false , UnderlineStyle.NO_UNDERLINE,
									Colour.RED);
							 TableFormat1_heading = new WritableFont(WritableFont.TAHOMA, 11,
									WritableFont.BOLD, false , UnderlineStyle.NO_UNDERLINE,
									Colour.WHITE);
						 TableFormat1_spaceline = new WritableFont(WritableFont.TAHOMA, 11,
									WritableFont.BOLD, false , UnderlineStyle.NO_UNDERLINE,
									Colour.WHITE);
							 tfb = new WritableCellFormat();
							 tfb1 = new WritableCellFormat();
							 tfb1_heading = new WritableCellFormat();
							 tfb_spaceline = new WritableCellFormat();
							 tfb_spaceline_black = new WritableCellFormat();
						      tfb1_summary = new WritableCellFormat();
						      try {

									//int first_row=Row_locator_full_scan_validation("TEST CASE",path,sheetname)+1;
									//logger.info(""+"first_row "+first_row);
									logger.info(""+"firstpage "+firstpage);
									if (firstpage) {
										logger.info(""+"locvalue iz: "+loc);
										logger.info(""+"validation called3");
										//if (loc==first_tc || Test_called<2)
										//if (loc==first_tc)
										if (Test_called<2)
										{
										
											
										sheet1.setColumnView(0, 11);
										sheet1.setColumnView(1, 30);
										sheet1.setColumnView(2, 20);
										sheet1.setColumnView(4, 40);
										// sheet1.setColumnView(3, 60);
										tfb.setFont(TableFormat);
										tfb1_heading.setFont(TableFormat1);
										tfb.setAlignment(Alignment.RIGHT);
										tfb.setBorder(Border.NONE,BorderLineStyle.NONE);
										tfb1.setFont(TableFormat1);
										tfb1_summary.setFont(TableFormat);
										tfb1_summary.setBorder(Border.ALL, BorderLineStyle.MEDIUM);
										tfb1_summary.setAlignment(Alignment.CENTRE);
										tfb1.setBackground(Colour.INDIGO);
										tfb1_heading.setBackground(Colour.DARK_RED);
										tfb1_heading.setBorder(Border.ALL, BorderLineStyle.THICK);
										tfb1.setBorder(Border.ALL, BorderLineStyle.THIN);
										tfb1.setAlignment(Alignment.CENTRE);
										tfb1_heading.setAlignment(Alignment.CENTRE);
										
										tfb_spaceline.setFont(TableFormat1_spaceline);
									//	tfb_spaceline.setBorder(Border.ALL, BorderLineStyle.THICK);
										tfb_spaceline.setBackground(Colour.PALETTE_BLACK);
										tfb1_heading.setBorder(Border.RIGHT, BorderLineStyle.THICK);
										tfb1_heading.setBorder(Border.TOP, BorderLineStyle.THICK);
										tfb1_heading.setBorder(Border.BOTTOM, BorderLineStyle.THICK);
										tfb_spaceline_black.setBackground(Colour.PALETTE_BLACK);
										
										
										
									//	tfb_spaceline.setBackground(Colour.BLACK);
										
										tfb.setBackground(Colour.GREY_25_PERCENT);
										sheet1.mergeCells(0,0,4,0);
										sheet1.mergeCells(0,1,1,8);
										sheet1.mergeCells(2,1,3,1);
										sheet1.mergeCells(2,8,3,8);
										sheet1.mergeCells(4,1,4,8);
										logger.info(""+"Merging of heading part done now");
										lbl = new Label(0, 0, "Voicezone - TEST RESULTS", tfb1_heading);
										sheet1.addCell(lbl);
										lbl = new Label(2,1, "", tfb);
										sheet1.addCell(lbl);
										lbl = new Label(0,1, "", tfb);
										sheet1.addCell(lbl);
										lbl = new Label(2,8, "", tfb);
										sheet1.addCell(lbl);
										lbl = new Label(4,1, "", tfb);
										sheet1.addCell(lbl);
										
										//lbl = new Label(0,0, "", tfb1_heading);
										//sheet1.addCell(lbl);
										
										sheet1.mergeCells(2,2,3,2);
										lbl = new Label(2, 2, "Execution Summary", tfb1);
										sheet1.addCell(lbl);
										tfb.setFont(TableFormat);
										lbl = new Label(0, 9, "Test Case",
												tfb1);
										sheet1.addCell(lbl);
										lbl = new Label(1, 9, "Test Scenario", tfb1);
										sheet1.addCell(lbl);

										lbl = new Label(2, 9, "Status", tfb1);
										sheet1.addCell(lbl);
										lbl = new Label(3, 9, "Actual Result", tfb1);
										sheet1.addCell(lbl);
										lbl = new Label(4, 9, "Expected Result", tfb1);
										sheet1.addCell(lbl);
										
										}
										tfb_spaceline.setBackground(Colour.PALETTE_BLACK);
										int i = 0;
					
										String exec1;
										logger.info(""+"*******check1");
											logger.info(""+"*******check2");
										 File data11 = new File(path);
											logger.info(""+"*******check3");
											WorkbookSettings ws11 = new WorkbookSettings();
											logger.info(""+"*******check4");
											ws11.setLocale(new Locale("er", "ER"));
											logger.info(""+"*******check5");
											Workbook wb11 = Workbook.getWorkbook(data11, ws11);
										//	Sheet sheet2 = wb1.getSheet(0);
											logger.info(""+"*******check6");
											Sheet sheet11 = wb11.getSheet(sheetname);
											logger.info(""+"*******check7");
											int LastRow=sheet11.getRows();
											logger.info(""+"*******check8");
											logger.info(""+"Sep1---> number of rows "+LastRow);
										
											
											logger.info(""+"location_b");
											 data1 = new File(path);
											 ws1 = new WorkbookSettings();
											
											ws1.setLocale(new Locale("er", "ER"));
											 wb1 = Workbook.getWorkbook(data1, ws1); 
											 sheet2 = wb1.getSheet(sheetname);
											exec1 = sheet2.getCell(3, loc).getContents();
											// logger.info(""+"exec1 "+exec1);
											name1 = sheet2.getCell(0, loc).getContents();
											String pass_screenshot=sheet2.getCell(4, loc).getContents();
											logger.info(""+"location_a");
											 gr=sheet2.getCell(4,4).getContents();
											logger.info(""+"Grid status "+gr);
											if(gr.equalsIgnoreCase("No"))
											br= sheet2.getCell(2, loc).getContents();
											logger.info(""+"br valueis^^^^^^^^^:"+br);	
												if(pass_screenshot.equalsIgnoreCase("Y"))
												{
													pass_screenshot_required=true;
												}
												else
												{
													pass_screenshot_required=false;
												}
											// logger.info(""+"name 1 is :"+name1);
											// Page_Internet=name1+"_";
											// logger.info(""+Page_Internet);
											// logger.info(""+"exe11 is"+exec1);
											// logger.info(""+"loc is " +loc);
											
											
											
											// logger.info(""+"location1");
											logger.info(""+"location_C");
											logger.info(""+"exec1 "+exec1+" loc "+loc);
							
									
									logger.info(""+first_tc);
									
									logger.info(""+"location_a");
									gr=sheet2.getCell(3,4).getContents();
									logger.info(""+"Grid status "+gr);
									if(gr.equalsIgnoreCase("No"))
									br= sheet2.getCell(2, loc).getContents();
									logger.info(""+"br valueis^^^^^^^^^:"+br);	
										if(pass_screenshot.equalsIgnoreCase("Y"))
										{
											pass_screenshot_required=true;
										}
										else
										{
											pass_screenshot_required=false;
										}
							
									wb1.close();

									logger.info(""+"location_C");
									logger.info(""+"exec1 "+exec1+" loc "+loc);
									if ((exec1.equals("Y")) || (exec1.equals("y"))) {
										logger.info(""+"location_d");
										int i_browser=0;
										while(browser[i_browser]!=null)
										{
											if(browser[i_browser].equals(br))
											{
												logger.info(""+"location_2");
												counter_result_initial[i_browser]=counter_result_sheet[i_browser];
												break;
											}
											i_browser++;
										}
										
										logger.info(""+"location_f");
										
										st = System.currentTimeMillis();
										logger.info(""+"Starting execution!!");
										int pass=0;
		        		
		        		if(loc==Row_locator_full_scan_input_sheet("Nomorobo",path)) 			        			
		        		{  
		        			logger.info(""+"got inside");
		        			pass+=obj_run( new Nomorobo(path), 0,sheet1, name1,loc,br,br_specific);
		        		}
		        		else if(loc==Row_locator_full_scan_input_sheet("NotifybyEmail",path))  
		        		{  
		        			logger.info(""+"got inside");
		        			pass+=obj_run( new NotifybyEmail(path), 0,sheet1, name1,loc,br,br_specific);
		        		}
		        		else if(loc==Row_locator_full_scan_input_sheet("NotifybyText",path))  
		        		{  
		        			logger.info(""+"got inside");
		        			pass+=obj_run( new NotifybyText(path), 0,sheet1, name1,loc,br,br_specific);
		        		}
		        		else if(loc==Row_locator_full_scan_input_sheet("PinSkip",path))  
		        		{  
		        			logger.info(""+"got inside");
		        			pass+=obj_run( new PinSkip(path), 0,sheet1, name1,loc,br,br_specific);
		        		}
		        		else if(loc==Row_locator_full_scan_input_sheet("PinChange",path))  
		        		{  
		        			logger.info(""+"got inside");
		        			pass+=obj_run( new PinChange(path), 0,sheet1, name1,loc,br,br_specific);
		        		}
		        		else if(loc==Row_locator_full_scan_input_sheet("CallBlockerSelect",path))  
		        		{  
		        			logger.info(""+"got inside");
		        			pass+=obj_run( new CallBlockerSelect(path), 0,sheet1, name1,loc,br,br_specific);
		        		}
		        		else if(loc==Row_locator_full_scan_input_sheet("CallBlockerAccept",path))  
		        		{  
		        			logger.info(""+"got inside");
		        			pass+=obj_run( new SelectiveCallAcceptance(path), 0,sheet1, name1,loc,br,br_specific);
		        		}
		        		else if(loc==Row_locator_full_scan_input_sheet("VoiceToText",path))  
		        		{  
		        			logger.info(""+"got inside");
		        			pass+=obj_run( new VoicemailTranscription(path), 0,sheet1, name1,loc,br,br_specific);
		        		}	
		        		else if(loc==Row_locator_full_scan_input_sheet("AnswerAnywhere",path))  
		        		{  
		        			logger.info(""+"got inside");
		        			pass+=obj_run( new AnswerAnywhere(path), 0,sheet1, name1,loc,br,br_specific);
		        		}
		        		else if(loc==Row_locator_full_scan_input_sheet("CallForwardingBusy",path))  
		        		{  
		        			logger.info(""+"got inside");
		        			pass+=obj_run( new CallForwardingBusy(path), 0,sheet1, name1,loc,br,br_specific);
		        		}
		        		else if(loc==Row_locator_full_scan_input_sheet("CallForwardingNoAnswer",path))  
		        		{  
		        			logger.info(""+"got inside");
		        			pass+=obj_run( new CallForwardingNoAnswer(path), 0,sheet1, name1,loc,br,br_specific);
		        		}
		        		else if(loc==Row_locator_full_scan_input_sheet("SelectiveCallForwarding",path))  
		        		{  
		        			logger.info(""+"got inside");
		        			pass+=obj_run( new SelectiveCallForwarding(path), 0,sheet1, name1,loc,br,br_specific);
		        		}
		        		else if(loc==Row_locator_full_scan_input_sheet("CallForwardingUnconditional",path))  
		        		{  
		        			logger.info(""+"got inside");
		        			pass+=obj_run( new CallForwardingUnconditional(path), 0,sheet1, name1,loc,br,br_specific);
		        		}
		        		else if(loc==Row_locator_full_scan_input_sheet("CallBlockerBasicPlus",path))  
		        		{  
		        			logger.info(""+"got inside");
		        			pass+=obj_run( new CallBlockerBasicPlus(path), 0,sheet1, name1,loc,br,br_specific);
		        		}
		        		else if(loc==Row_locator_full_scan_input_sheet("CallWaiting",path))  
		        		{  
		        			logger.info(""+"got inside");
		        			pass+=obj_run( new CallWaiting(path), 0,sheet1, name1,loc,br,br_specific);
		        		}
		        		else if(loc==Row_locator_full_scan_input_sheet("DistinctiveRing",path))  
		        		{  
		        			logger.info(""+"got inside");
		        			pass+=obj_run( new DistinctiveRing(path), 0,sheet1, name1,loc,br,br_specific);
		        		}
		        		else if(loc==Row_locator_full_scan_input_sheet("ThreeWayCalling",path))  
		        		{  
		        			logger.info(""+"got inside");
		        			pass+=obj_run( new ThreeWayCalling(path), 0,sheet1, name1,loc,br,br_specific);
		        		}
		        		else if(loc==Row_locator_full_scan_input_sheet("OutBoundCallerid",path))  
		        		{  
		        			logger.info(""+"got inside");
		        			pass+=obj_run( new OutBoundCallerid(path), 0,sheet1, name1,loc,br,br_specific);
		        		}
		        		else if(loc==Row_locator_full_scan_input_sheet("SpeedDial",path))  
		        		{  
		        			logger.info(""+"got inside");
		        			pass+=obj_run( new SpeedDial(path), 0,sheet1, name1,loc,br,br_specific);
		        		}
		        		else if(loc==Row_locator_full_scan_input_sheet("VMActivation",path))  
		        		{  
		        			logger.info(""+"got inside");
		        			pass+=obj_run( new VMActivation(path), 0,sheet1, name1,loc,br,br_specific);
		        		}		        		
		        		
		        		 i_browser=0;
						  logger.info(""+"Validation******* after obj_run");
							while(browser[i_browser]!=null)
							{
								if(browser[i_browser].equals(br))
								{
									logger.info(""+"RESULT_SHEET VALUE AFTER OBJ_RUN= FINAL counter_result_sheet: "+counter_result_sheet[i_browser]);
									counter_result_final[i_browser]=counter_result_sheet[i_browser];
									counter_result_initial[i_browser]++;
									l_browser[i_browser]=0;
									//counter_result_final[i_browser]++;
									break;
								}
								i_browser++;
							}
							
							  logger.info(""+"Validation******* after obj_run2");
							
								
								  i_browser=0;
								  logger.info(""+"browser[i_browser] "+browser[i_browser]);
								  logger.info(""+"Validation******* after obj_run23");
								  i_browser=0;
								  while(browser[i_browser]!=null)
								  {
									if(browser[i_browser].equals(br))
										{
									/*	if(loc!=20)
										{
											logger.info(""+"NOT INITIAL");
											counter_result_final[i_browser]=counter_result_final[i_browser];
										}
										*/
										logger.info(""+" Merging testname cell ");
										logger.info(""+"br "+br);
										logger.info(""+"counter_result_initial[i_browser] "+counter_result_initial[i_browser]);
										logger.info(""+"counter_result_final[i_browser] "+ (counter_result_final[i_browser]));
										logger.info(""+"i_browser n loc respectively  "+i_browser+" "+loc);
										sheet1.mergeCells(0,counter_result_initial[i_browser],0,((counter_result_final[i_browser]-1)));
										}
									i_browser++;
								}
							//tfb_spaceline.setBackground(Colour.BLACK);
						
						//	lbl = new Label(0, counter_result_final, "", tfb_spaceline);
						//	sheet1.addCell(lbl);
						//	tfb1.setBackground(Colour.BLACK);
								 logger.info(""+"Validation******* after obj_run234");
								i_browser=0;
								//int black;
								while(browser[i_browser]!=null)
								{
									if(browser[i_browser].equals(br))
									{
										logger.info(""+"Validation "+i_browser+" browser "+browser[i_browser]);
										logger.info(""+"Merging 0 ,"+counter_result_final[i_browser] +",4,"+ counter_result_final[i_browser]);
									/*	else if(loc==20)
										{
											counter_result_final[i_browser]=	counter_result_final[i_browser];
										}
									*/	
											sheet1.mergeCells(0,(counter_result_final[i_browser]),4,(counter_result_final[i_browser]));
											//		black[bl]=counter_result_final[i_browser];
											//			bl++;
											//black=counter_result_final[i_browser];
											Label lbl_spaceline = new Label(0, (counter_result_final[i_browser]), "", tfb_spaceline);
											sheet1.addCell(lbl_spaceline);
											counter_result_sheet[i_browser]=counter_result_final[i_browser];
											total[i_browser]++;
											break;
									}
									i_browser++;
								}
								
								logger.info(""+"Validation******* after obj_run2345");
							
								 logger.info(""+"Validation******* after obj_run23456");
							/*	i_browser=0;
								while(browser[i_browser]!=null)
								{
									if(browser[i_browser].equals(br))
									{		
									
									//	total[i_browser]++;
										break;
									}
									i_browser++;
								}	
								*/
						et = System.currentTimeMillis();
						diff = et - st;
						diff /= 60000.0D;
						diff1 += diff;
						
						 logger.info(""+"Validation******* after obj_run27");
					}

					else {
						int i_browser=0;
						while(browser[i_browser]!=null)
						{
							if(browser[i_browser].equals(br))
							{		
							
								no_run[i_browser]++;
								break;
							}
							i_browser++;
						}
					 }
					i++;
				//	loc++;

				//} while (loc < LastRow);

				logger.info(""+"Complete");
			} else {
				logger.info(""+"Check for entering this loop");
				lbl = new Label(1, 3, notinit);
				sheet1.addCell(lbl);
			}

			{
			 if (loc==18)
			 {}
				logger.info(""+"check1");
				logger.info(""+"Test completed yes or NO inside validation is"+TestCompleted);
				//copy.write();
				//copy.close();
				//tear();
				logger.info(""+"Workbook closed: LS");
				}

			} catch (Exception e) {
			lbl = new Label(3, 0,
					"Intermediate error has occured which has stopped execution");
			sheet1.addCell(lbl);
			lbl = new Label(3, 1, e.getMessage());
			sheet1.addCell(lbl);
			} finally {

				 logger.info(""+"Validation******* after obj_run28");
				 
				lbl = new Label(2,3, "Scripts Executed:", tfb1_summary);
				sheet1.addCell(lbl);
				lbl = new Label(2,4, "Scripts Passed: ", tfb1_summary);
				sheet1.addCell(lbl);
				lbl = new Label(2,5, "Scripts Failed: ", tfb1_summary);
				sheet1.addCell(lbl);
				lbl = new Label(2,6, "Scripts Not Executed: ", tfb1_summary);
				sheet1.addCell(lbl);

				/*
				Number nmbr = new Number(3, 4, pass,tfb1_summary);
				sheet1.addCell(nmbr);
				logger.info(""+"#####################################################");
				logger.info(""+"total"+total);
				logger.info(""+"pass"+pass);
				nmbr = new Number(3, 5, total - pass,tfb1_summary);
				logger.info(""+"nmbr is "+nmbr);
				logger.info(""+"#####################################################");
				sheet1.addCell(nmbr);
				nmbr = new Number(3, 3, total,tfb1_summary);
				sheet1.addCell(nmbr);
				// no_run=26-total;
				nmbr = new Number(3, 6, no_run,tfb1_summary);
				sheet1.addCell(nmbr);
				*/
				if (diff1 != 0.0D) {
					String yash = df.format(diff1);
					diff1 = Double.parseDouble(yash);
					lbl = new Label(2,7, "Total Time: ", tfb1_summary);
					sheet1.addCell(lbl);
				int	i_browser=0;
					while(browser[i_browser]!=null)
					{
						if(browser[i_browser].equals(br))
						{
							if (total[i_browser] == 0)
								diff1 = 0.0D;
							//else
//								time[i_browser]+=diff1;
							break;
						}
						i_browser++;
					}
					lbl = new Label(3,7, diff1 + " Minute(s)",tfb1_summary);
					//lbl = new Label(3,7, time[i_browser] + " Minute(s)",tfb1_summary);
					
					sheet1.addCell(lbl);
					
					while(browser[i_browser]!=null)
					{
						if(browser[i_browser].equals(br))
						{
							logger.info(""+"#####################################################");
								Number nmbr = new Number(3, 4, pass_br_wise[i_browser],tfb1_summary);
							sheet1.addCell(nmbr);
							break;
						}
						i_browser++;
					}
					
					
					
					
					
					 logger.info(""+"Validation******* after obj_run29");
					 i_browser=0;
						while(browser[i_browser]!=null)
						{
							if(browser[i_browser].equals(br))
							{
						
						logger.info(""+"total"+total[i_browser]);
						//logger.info(""+"pass"+pass);
						Number nmbr = new Number(3, 5, total[i_browser] - pass_br_wise[i_browser],tfb1_summary);
						logger.info(""+"nmbr is "+nmbr);
						
						sheet1.addCell(nmbr);
					nmbr = new Number(3, 3, total[i_browser],tfb1_summary);
					sheet1.addCell(nmbr);
					nmbr = new Number(3, 6, no_run[i_browser],tfb1_summary);
					sheet1.addCell(nmbr);
					break;
						}
						i_browser++;
						}
						// no_run=26-total;
						
					}


					
			logger.info(""+"#####################################################");
			logger.info(""+"Workbook closed: LS");
			//if(br.equals("FF"))
			//{
				if(Test_called<2)
				{
					workbook.write();
					workbook.close();
				}
				else
				{
					copy.write();
					copy.close();
				}
			//}
			
			
			//tear();

			}
	}
} 