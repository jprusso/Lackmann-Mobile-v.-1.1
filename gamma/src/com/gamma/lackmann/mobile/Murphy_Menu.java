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
import android.os.Bundle;
import android.os.Environment;
import android.text.Html;
import android.view.View;
import android.widget.TextView;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Typeface;

public class Murphy_Menu extends Activity 
{
	Context ctx = this;
	
	TextView mrpmon, mrptues, mrpwed, mrpthurs, mrpfri,
			 monday, tuesday, wednesday, thursday, friday;

	final File path = Environment.getExternalStorageDirectory(); 
	final File menu = new File(path + "/menu.xls");
	final File nutrition = new File(path + "/nutrition.xls");
	
	final String meal_name[] = new String[5];
	
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.murphy_menu);
        
        setupVariables();
        
        final AlertDialog.Builder error_box = new AlertDialog.Builder(ctx);
        
        try 
        {
        	/*	Checks to see if files exist then beings parsing	*/
        	
        	if(menu.exists() && nutrition.exists())
        	{
        		readdinner();
        	}
        	else
        	{
        		error_box.setTitle("Menu Retrieval Error");
        		error_box.setIcon(R.drawable.app_icon);
        		error_box.setNeutralButton("OK!", null);
        		error_box.setMessage("Menus are unavailable at this time.");
        		error_box.show();
        	}
		} 
        catch (IOException e) 
        {
			error_box.setTitle("Menu Retrieval Error");
			error_box.setIcon(R.drawable.app_icon);
			error_box.setNeutralButton("OK!", null);
			error_box.setMessage("Menus are unavailable at this time.");
			error_box.show();
		}      
    }
    
    //	BEGIN Method ID# 3.3
    public void readdinner() throws IOException
    {
       	final Workbook w;
    	String ss, qq, tt, date;
    	
    	try 
    	{
			w = Workbook.getWorkbook(menu);
			final Sheet sheet = w.getSheet(2);
			
			date = sheet.getCell(1,3).getContents() + "\t" + sheet.getCell(2,3).getContents();
			monday.setText(date);
			ss = sheet.getCell(1,9).getContents();
			qq = sheet.getCell(1,10).getContents();
			tt = sheet.getCell(1,11).getContents();
			meal_name[0] = ss + " " + qq + " " + tt;
			mrpmon.setText(Html.fromHtml("<font color='blue'><u>" 
						+ ss + "<br>" + qq + "<br>" + tt + "</u></font>"));
			
			mrpmon.setOnClickListener(new View.OnClickListener() 
			{
				public void onClick(View v) 
				{
					parse_nutrition(1, 15, meal_name[0]);		
				}
			}); 
			
			date = sheet.getCell(3,3).getContents() + "\t" + sheet.getCell(4,3).getContents();
			tuesday.setText(date);
			ss = sheet.getCell(3,9).getContents();
			qq = sheet.getCell(3,10).getContents();
			tt = sheet.getCell(3,11).getContents();
			meal_name[1] = ss + " " + qq + " " + tt;
			mrptues.setText(Html.fromHtml("<font color='blue'><u>" 
						+ ss + "<br>" + qq + "<br>" + tt + "</u></font>"));
			
			mrptues.setOnClickListener(new View.OnClickListener() 
			{
				public void onClick(View v) 
				{
					parse_nutrition(3, 15, meal_name[1]);		
				}
			}); 
			
			date = sheet.getCell(5,3).getContents() + "\t" + sheet.getCell(6,3).getContents();
			wednesday.setText(date);
			ss = sheet.getCell(5,9).getContents();
			qq = sheet.getCell(5,10).getContents();
			tt = sheet.getCell(5,11).getContents();
			meal_name[2] = ss + " " + qq + " " + tt;
			mrpwed.setText(Html.fromHtml("<font color='blue'><u>" 
						+ ss + "<br>" + qq + "<br>" + tt + "</u></font>"));
			
			mrpwed.setOnClickListener(new View.OnClickListener() 
			{
				public void onClick(View v) 
				{
					parse_nutrition(5, 15, meal_name[2]);		
				}
			}); 
			
			date = sheet.getCell(7,3).getContents() + "\t" + sheet.getCell(8,3).getContents();
			thursday.setText(date);
			ss = sheet.getCell(7,9).getContents();
			qq = sheet.getCell(7,10).getContents();
			tt = sheet.getCell(7,11).getContents();
			meal_name[3] = ss + " " + qq + " " + tt;
			mrpthurs.setText(Html.fromHtml("<font color='blue'><u>" 
						+ ss + "<br>" + qq + "<br>" + tt + "</u></font>"));
			
			mrpthurs.setOnClickListener(new View.OnClickListener() 
			{
				public void onClick(View v) 
				{
					parse_nutrition(7, 15, meal_name[3]);		
				}
			}); 
			
			date = sheet.getCell(9,3).getContents() + "\t" + sheet.getCell(10,3).getContents();
			friday.setText(date);
			ss = sheet.getCell(9,9).getContents();
			qq = sheet.getCell(9,10).getContents();
			tt = sheet.getCell(9,11).getContents();
			meal_name[4] = ss + " " + qq + " " + tt;
			mrpfri.setText(Html.fromHtml("<font color='blue'><u>" 
						+ ss + "<br>" + qq + "<br>" + tt + "</u></font>"));
			
			mrpfri.setOnClickListener(new View.OnClickListener() 
			{
				public void onClick(View v) 
				{
					parse_nutrition(9, 15, meal_name[4]);		
				}
			}); 	
			
			w.close();
		} 
    	catch (Exception e) 
    	{		
    		final AlertDialog.Builder error_box = new AlertDialog.Builder(ctx);
			error_box.setTitle("Menu Retrieval Error");
			error_box.setIcon(R.drawable.app_icon);
			error_box.setNeutralButton("OK!", null);
			error_box.setMessage("Menus are unavailable at this time.");
			error_box.show();
		}   	
    }
    //	END Method ID# 3.3
    
    //	BEGIN Method ID# 4.0
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
			nutrition_box.setIcon(R.drawable.app_icon);
			nutrition_box.setNeutralButton("OK!", null);
			
			if(ref_num.length() == 0)
			{
				nutrition_box.setMessage("Nutrition information unavailable at this time");	 
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
				
				if(total_cal == 0 && total_fat == 0 && total_carbs == 0 && total_protein == 0)
				{
					nutrition_box.setMessage("Nutrition information unavailable at this time");	 
					nutrition_box.show();
				}
				else
				{
					nutrition_box.setMessage("Serving Size:      1 menu item " + "\n"
							+ "Total Calories:   " + total_cal + " calories\n"
							+ "Total Fat:            " +  total_fat + " grams\n" 
							+ "Total Carbs:       " + total_carbs + " grams\n"
							+ "Total Protein:     " + total_protein + " grams");
			 	 
					nutrition_box.show();
				}
			}
		}
		catch (Exception e) 
		{
			final AlertDialog.Builder error_box = new AlertDialog.Builder(ctx);
			error_box.setTitle("Application Error");
			error_box.setIcon(R.drawable.app_icon);
			error_box.setNeutralButton("OK!", null);
			error_box.setMessage("Could not display information.\n Please try again!");
			error_box.show();
		}
    }
	//	END Method ID# 4.0
    
	// Initializes variables and gives TextView labels special font
    private void setupVariables() 
	{
    	mrpmon = (TextView) findViewById(R.id.mondinner);
        mrptues = (TextView) findViewById(R.id.tuesdinner);
        mrpwed = (TextView) findViewById(R.id.wedsdinner);
        mrpthurs = (TextView) findViewById(R.id.thursdinner);
        mrpfri = (TextView) findViewById(R.id.fridinner);
        
    	monday = (TextView) findViewById(R.id.monday);
        tuesday = (TextView) findViewById(R.id.tuesday);
        wednesday = (TextView) findViewById(R.id.wednesday);
        thursday = (TextView) findViewById(R.id.thursday);
        friday = (TextView) findViewById(R.id.friday);
        
        Typeface tf = Typeface.createFromAsset(getAssets(),
                "fonts/Champagne & Limousines Bold.ttf");
        TextView tv = (TextView) findViewById(R.id.location);
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
	}
}