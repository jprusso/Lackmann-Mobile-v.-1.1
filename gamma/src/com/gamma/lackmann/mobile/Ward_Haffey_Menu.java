/*
		Lackmann Mobile Android Application
		Version 1.1
	
		Created by: John Russo, Wade Kline, Matthew Staples, and Nathan Sunseri
	
		St. John Fisher College 
		Spring 2013																	*/

package com.gamma.lackmann.mobile;

import java.io.File;
import java.io.IOException;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import android.os.Bundle;
import android.os.Environment;
import android.text.Html;
import android.view.View;
import android.widget.TextView;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Typeface;

public class Ward_Haffey_Menu extends Activity 
{
	Context ctx = this;
	
	TextView lwhsun, lwhmon, lwhtues, lwhwed,lwhthurs,
				lwhfri,lwhsat,dwhsun,dwhmon,dwhtues,
				dwhwed,dwhthurs,dwhfri,dwhsat, sunday,
				monday, tuesday, wednesday, thursday, 
				friday, saturday;

	final File path = Environment.getExternalStorageDirectory(); 
	final File menu = new File(path + "/menu.xls");
	final File nutrition = new File(path + "/nutrition.xls");
	
	final String meal_name[] = new String[14];

    public void onCreate(Bundle savedInstanceState) 
    {
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.ward_haffey_menu);
          	
        setupVariables();
        
		try 
		{	
			/*	Checks to see if files exist then beings parsing	*/
			
			if(menu.exists() && nutrition.exists())
			{
				readlunch();
				readdinner();
			}
			else
			{
	        	AlertDialog.Builder error_box = new AlertDialog.Builder(ctx);
				error_box.setTitle("Menu Retrieval Error");
				error_box.setIcon(R.drawable.lackmann_twitterpic);
				error_box.setNeutralButton("OK!", null);
				error_box.setMessage("Menus are unavailable at this time.");
				error_box.show();
			}
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
    }
    
    //	BEGIN Method ID# 3.2
    public void readlunch() throws IOException
    {
    	final Workbook w;
    	String ss, qq, tt, date; 
  	    	
    	try 
    	{ 		
			w = Workbook.getWorkbook(menu);
			final Sheet sheet = w.getSheet(0);
					     
			date = sheet.getCell(1,3).getContents() + "\t" + sheet.getCell(2,3).getContents();
			sunday.setText(date);
			ss = sheet.getCell(1,13).getContents();
			qq = sheet.getCell(1,14).getContents();
			tt = sheet.getCell(1,15).getContents();
			meal_name[0] = ss + " " + qq + " " + tt;	
			lwhsun.setText(Html.fromHtml("<font color='blue'><u>" 
						+ ss + "<br>" + qq + "<br>" + tt + "</u></font>"));
			lwhsun.setOnClickListener(new View.OnClickListener() 
			{
				public void onClick(View v) 
				{
					parse_nutrition(1, 8, meal_name[0]);
				}
			}); 
					
			date = sheet.getCell(3,3).getContents() + "\t" + sheet.getCell(4,3).getContents();
			monday.setText(date);
			ss = sheet.getCell(3,13).getContents();
			qq = sheet.getCell(3,14).getContents();
			tt = sheet.getCell(3,15).getContents();
			meal_name[1] = ss + " " + qq + " " + tt;
			lwhmon.setText(Html.fromHtml("<font color='blue'><u>" 
								+ ss + "<br>" + qq + "<br>" + tt + "</u></font>"));
			
			lwhmon.setOnClickListener(new View.OnClickListener() 
			{
				public void onClick(View v) 
				{						
					parse_nutrition(3, 8, meal_name[1]);		
				}
			});
			
			date = sheet.getCell(5,3).getContents() + "\t" + sheet.getCell(6,3).getContents();
			tuesday.setText(date);
			ss = sheet.getCell(5,13).getContents();
			qq = sheet.getCell(5,14).getContents();
			tt = sheet.getCell(5,15).getContents();
			meal_name[2] = ss + " " + qq + " " + tt;
			lwhtues.setText(Html.fromHtml("<font color='blue'><u>" 
								+ ss + "<br>" + qq + "<br>" + tt + "</u></font>"));
			
			lwhtues.setOnClickListener(new View.OnClickListener() 
			{
				public void onClick(View v) 
				{						
					parse_nutrition(5, 8, meal_name[2]);		
				}
			});
			
			date = sheet.getCell(7,3).getContents() + "\t" + sheet.getCell(8,3).getContents();
			wednesday.setText(date);
			ss = sheet.getCell(7,13).getContents();
			qq = sheet.getCell(7,14).getContents();
			tt = sheet.getCell(7,15).getContents();
			meal_name[3] = ss + " " + qq + " " + tt;
			lwhwed.setText(Html.fromHtml("<font color='blue'><u>" 
								+ ss + "<br>" + qq + "<br>" + tt + "</u></font>"));
			lwhwed.setOnClickListener(new View.OnClickListener() 
			{
				public void onClick(View v) 
				{						
					parse_nutrition(7, 8, meal_name[3]);		
				}
			});
			
			date = sheet.getCell(9,3).getContents() + "\t" + sheet.getCell(10,3).getContents();
			thursday.setText(date);
			ss = sheet.getCell(9,13).getContents();
			qq = sheet.getCell(9,14).getContents();
			tt = sheet.getCell(9,15).getContents();
			meal_name[4] = ss + " " + qq + " " + tt;
			lwhthurs.setText(Html.fromHtml("<font color='blue'><u>" 
								+ ss + "<br>" + qq + "<br>" + tt + "</u></font>"));
			lwhthurs.setOnClickListener(new View.OnClickListener() 
			{
				public void onClick(View v) 
				{						
					parse_nutrition(9, 8, meal_name[4]);		
				}
			});
			
			date = sheet.getCell(11,3).getContents() + "\t" + sheet.getCell(12,3).getContents();
			friday.setText(date);
			ss = sheet.getCell(11,13).getContents();
			qq = sheet.getCell(11,14).getContents();
			tt = sheet.getCell(11,15).getContents();
			meal_name[5] = ss + " " + qq + " " + tt;
			lwhfri.setText(Html.fromHtml("<font color='blue'><u>" 
								+ ss + "<br>" + qq + "<br>" + tt + "</u></font>"));
			lwhfri.setOnClickListener(new View.OnClickListener() 
			{
				public void onClick(View v) 
				{						
					parse_nutrition(11, 8, meal_name[5]);		
				}
			});
			
			date = sheet.getCell(13,3).getContents() + "\t" + sheet.getCell(14,3).getContents();
			saturday.setText(date);
			ss = sheet.getCell(13,13).getContents();
			qq = sheet.getCell(13,14).getContents();
			tt = sheet.getCell(13,15).getContents();
			meal_name[6] = ss + " " + qq + " " + tt;
			lwhsat.setText(Html.fromHtml("<font color='blue'><u>" 
								+ ss + "<br>" + qq + "<br>" + tt + "</u></font>"));
			lwhsat.setOnClickListener(new View.OnClickListener() 
			{
				public void onClick(View v) 
				{						
					parse_nutrition(13, 8, meal_name[6]);		
				}
			});
			
			w.close();
		} 
    	catch (BiffException e) 
    	{		
			e.printStackTrace();
		}   	
    }
    //	END Method ID# 3.2
    
    //	BEGIN Method ID# 3.3
    public void readdinner() throws IOException
    { 	
       	final Workbook w;
    	String ss, qq, tt;
    	
    	try 
    	{		
			w = Workbook.getWorkbook(menu);
			Sheet sheet = w.getSheet(1);
			
			ss = sheet.getCell(1,13).getContents();
			qq = sheet.getCell(1,14).getContents();
			tt = sheet.getCell(1,15).getContents();
			meal_name[7] = ss + " " + qq + " " + tt;
			dwhsun.setText(Html.fromHtml("<font color='blue'><u>" 
								+ ss + "<br>" + qq + "<br>" + tt + "</u></font>"));
			dwhsun.setOnClickListener(new View.OnClickListener() 
			{
				public void onClick(View v) 
				{						
					parse_nutrition(1, 11, meal_name[7]);		
				}
			});
			
			ss = sheet.getCell(3,13).getContents();
			qq = sheet.getCell(3,14).getContents();
			tt = sheet.getCell(3,15).getContents();
			meal_name[8] = ss + " " + qq + " " + tt;
			dwhmon.setText(Html.fromHtml("<font color='blue'><u>" 
								+ ss + "<br>" + qq + "<br>" + tt + "</u></font>"));
			dwhmon.setOnClickListener(new View.OnClickListener() 
			{
				public void onClick(View v) 
				{						
					parse_nutrition(3, 11, meal_name[8]);		
				}
			});
			
			ss = sheet.getCell(5,13).getContents();
			qq = sheet.getCell(5,14).getContents();
			tt = sheet.getCell(5,15).getContents();
			meal_name[9] = ss + " " + qq + " " + tt;
			dwhtues.setText(Html.fromHtml("<font color='blue'><u>" 
								+ ss + "<br>" + qq + "<br>" + tt + "</u></font>"));
			dwhtues.setOnClickListener(new View.OnClickListener() 
			{
				public void onClick(View v) 
				{						
					parse_nutrition(5, 11, meal_name[9]);		
				}
			});
			
			ss = sheet.getCell(7,13).getContents();
			qq = sheet.getCell(7,14).getContents();
			tt = sheet.getCell(7,15).getContents();
			meal_name[10] = ss + " " + qq + " " + tt;
			dwhwed.setText(Html.fromHtml("<font color='blue'><u>" 
								+ ss + "<br>" + qq + "<br>" + tt + "</u></font>"));
			dwhwed.setOnClickListener(new View.OnClickListener() 
			{
				public void onClick(View v) 
				{						
					parse_nutrition(7, 11, meal_name[10]);		
				}
			});
			
			ss = sheet.getCell(9,13).getContents();
			qq = sheet.getCell(9,14).getContents();
			tt = sheet.getCell(9,15).getContents();
			meal_name[11] = ss + " " + qq + " " + tt;
			dwhthurs.setText(Html.fromHtml("<font color='blue'><u>" 
								+ ss + "<br>" + qq + "<br>" + tt + "</u></font>"));
			dwhthurs.setOnClickListener(new View.OnClickListener() 
			{
				public void onClick(View v) 
				{						
					parse_nutrition(9, 11, meal_name[11]);		
				}
			});
					
			ss = sheet.getCell(11,13).getContents();
			qq = sheet.getCell(11,14).getContents();
			tt = sheet.getCell(11,15).getContents();
			meal_name[12] = ss + " " + qq + " " + tt;
			dwhfri.setText(Html.fromHtml("<font color='blue'><u>" 
								+ ss + "<br>" + qq + "<br>" + tt + "</u></font>"));
			dwhfri.setOnClickListener(new View.OnClickListener() 
			{
				public void onClick(View v) 
				{						
					parse_nutrition(11, 11, meal_name[12]);		
				}
			});
				
			ss = sheet.getCell(13,13).getContents();
			qq = sheet.getCell(13,14).getContents();
			tt = sheet.getCell(13,15).getContents();
			meal_name[13] = ss + " " + qq + " " + tt;
			dwhsat.setText(Html.fromHtml("<font color='blue'><u>" 
								+ ss + "<br>" + qq + "<br>" + tt + "</u></font>"));
			dwhsat.setOnClickListener(new View.OnClickListener() 
			{
				public void onClick(View v) 
				{						
					parse_nutrition(13, 11, meal_name[13]);		
				}
			});
			
			w.close();
		} 
    	catch (BiffException e) 
    	{			
			e.printStackTrace();
		}   		
    }
    //	END Method ID# 3.3
    
    // BEGIN Method ID #4.0
    private void parse_nutrition(int x, int y, String meal_name)
    {
    	int counter, columns, rows, ref_length;
        double total_cal,total_fat,total_carbs,total_protein;
    	String contents, ref_num;
    	String ss[];
    	
    	counter = 0;
		total_cal = total_fat = total_carbs = total_protein = 0;
	    	
		try 
		{
			final Workbook menu_wb = Workbook.getWorkbook(menu);
			Sheet ref_sheet = menu_wb.getSheet(5);
			
			final Workbook nutrit_wb = Workbook.getWorkbook(nutrition);
			Sheet nutrit_sheet = nutrit_wb.getSheet(0);
			
			ref_num = ref_sheet.getCell(x,y).getContents();
			
			AlertDialog.Builder nutrition_box = new AlertDialog.Builder(ctx);
			nutrition_box.setTitle(meal_name);
			nutrition_box.setIcon(R.drawable.lackmann_twitterpic);
			nutrition_box.setNeutralButton("OK!", null);
			
			if(ref_num.length() == 0)
			{
				nutrition_box.setMessage("Sorry nutrition information is not available");	 
				nutrition_box.show();
			}
			else
			{	
				ss = ref_num.split("/");
			
				columns = nutrit_sheet.getColumns();
				rows = nutrit_sheet.getRows();
			
				
				while(counter < rows)
				{
					contents = nutrit_sheet.getCell(1,counter).getContents();
				
					for(int i = 0; i < ss.length; i++)
					{
						/* Checks to see if reference number matches and is valid
						 * 	No reference number is greater than 8 characters. */
						if(contents.equals(ss[i]) && contents.length() < 10)
						{
							total_cal = total_cal + Double.parseDouble(nutrit_sheet.getCell(6,counter).getContents());
							total_fat = total_fat + Double.parseDouble(nutrit_sheet.getCell(8,counter).getContents());
							total_carbs = total_carbs + Double.parseDouble(nutrit_sheet.getCell(13,counter).getContents());
							
							if(nutrit_sheet.getCell(16,counter).getContents().equals("less than 1"))
							{
								total_protein++;
							}
							else
							{
								total_protein = total_protein + Double.parseDouble(nutrit_sheet.getCell(16,counter).getContents());
							}
						}
					}
				
					counter++;
				}
			
				nutrition_box.setMessage("Serving Size:      1 menu item " + "\n"
						+ "Total Calories:   " + total_cal + " calories\n"
						+ "Total Fat:            " +  total_fat + " grams\n" 
						+ "Total Carbs:       " + total_carbs + " grams\n"
						+ "Total Protein:     " + total_protein + " grams");
			 	 
				nutrition_box.show();
			}
		}
		catch (BiffException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}   	
    }
    // END Method ID #4.0
    
    // Initializes variables and gives TextView labels special font
    private void setupVariables() 
	{
    	lwhsun = (TextView) findViewById(R.id.sunlunch);
        lwhmon = (TextView) findViewById(R.id.monlunch);
        lwhtues = (TextView) findViewById(R.id.tueslunch);
        lwhwed = (TextView) findViewById(R.id.wedslunch);
        lwhthurs = (TextView) findViewById(R.id.thurslunch);
        lwhfri = (TextView) findViewById(R.id.frilunch);
        lwhsat = (TextView) findViewById(R.id.satlunch);
        
        dwhsun = (TextView) findViewById(R.id.sundinner);
        dwhmon = (TextView) findViewById(R.id.mondinner);
        dwhtues = (TextView) findViewById(R.id.tuesdinner);
        dwhwed = (TextView) findViewById(R.id.wedsdinner);
        dwhthurs = (TextView) findViewById(R.id.thursdinner);
        dwhfri = (TextView) findViewById(R.id.fridinner);
        dwhsat = (TextView) findViewById(R.id.satdinner);
        
        sunday = (TextView) findViewById(R.id.sunday);
        monday = (TextView) findViewById(R.id.monday);
        tuesday = (TextView) findViewById(R.id.tuesday);
        wednesday = (TextView) findViewById(R.id.wednesday);
        thursday = (TextView) findViewById(R.id.thursday);
        friday = (TextView) findViewById(R.id.friday);
        saturday = (TextView) findViewById(R.id.saturday);
        
        Typeface tf = Typeface.createFromAsset(getAssets(),
                "fonts/Champagne & Limousines Bold.ttf");
        TextView tv = (TextView) findViewById(R.id.location);
        tv.setTypeface(tf);
        tv = (TextView) findViewById(R.id.sunday);
        tv.setTypeface(tf);
        tv = (TextView) findViewById(R.id.monday);
        tv.setTypeface(tf);
        tv = (TextView) findViewById(R.id.tuesday);
        tv.setTypeface(tf);
        tv = (TextView) findViewById(R.id.wednesday);
        tv.setTypeface(tf);
        tv = (TextView) findViewById(R.id.thursday);
        tv.setTypeface(tf);
        tv = (TextView) findViewById(R.id.friday);
        tv.setTypeface(tf);
        tv = (TextView) findViewById(R.id.saturday);
        tv.setTypeface(tf);
	} 
}