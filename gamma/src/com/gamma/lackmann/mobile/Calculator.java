/*
		Lackmann Mobile Android Application
		Version 1.1
	
		Created by: John Russo, Wade Kline, Matthew Staples, and Nathan Sunseri
	
		St. John Fisher College 
		Spring 2013																	*/

package com.gamma.lackmann.mobile;

import java.io.File;
import java.text.DecimalFormat;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Calculator extends Activity 
{
	Context ctx = this;
	
	int ms_int, gp_int = 0;
	double fd_used, fd_total = 0;
	String  mealData, diningData, guestData;
    
	SharedPreferences someData;
	File f = new File("/data/data/com.test.jp/shared_prefs/MySharedString.xml");
	
	Button meal_swipes, flex_dollars, guest_passes, submit_button,
				edit_button, cancel_button; 
	
	DecimalFormat df = new DecimalFormat("#.##");
	
	// BEGIN Method ID# 5.2
    @Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculator);
        
        final AlertDialog.Builder error_box = new AlertDialog.Builder(ctx);
                
        //Makes sure keyboard does not automatically open
        getWindow().setSoftInputMode(
        	    WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        
        setupVariables(); 
                        
        meal_swipes.setOnClickListener(new View.OnClickListener() 
		{		
			public void onClick(View v) 
			{
				try
				{					
					if(ms_int == 0)
					{
						ms_int = 0;
					}
					else
					{
						ms_int--;
					}
					
					mealData = Integer.toString(ms_int);
					meal_swipes.setText(mealData);
				}
				catch(NumberFormatException e)
				{
					meal_swipes.setText("Error");
				}			
			}
		});
        
        flex_dollars.setOnClickListener(new View.OnClickListener()
		{       	
        	public void onClick(View v)
			{        		
        		EditText f_dollars_used = ((EditText) findViewById(R.id.editText1));
        		Button f_dollars_total = ((Button) findViewById(R.id.flex_dollars));
        		
        		if(f_dollars_used.getText().toString().trim().equals(""))
        		{
        			flex_dollars.setText(String.valueOf(df.format(fd_total)));
        		}
        		else
        		{
        			fd_used = Double.parseDouble(f_dollars_used.getText().toString());
        			fd_total = Double.parseDouble(f_dollars_total.getText().toString());
				
        			if(fd_total >= fd_used)
        			{
        				fd_total = fd_total - fd_used;
        				flex_dollars.setText(String.valueOf(df.format(fd_total)));
        			}
        			else if(fd_total < fd_used)
        			{      		
        				error_box.setTitle("Data Error");
            			error_box.setIcon(R.drawable.app_icon);
            			error_box.setMessage("Not enough $$!");
            			error_box.setNeutralButton("OK!", null);
            			error_box.show();
        			}
        			else
        			{				
        				error_box.setTitle("Data Error");
            			error_box.setIcon(R.drawable.app_icon);
            			error_box.setMessage("Invalid Input!");
            			error_box.setNeutralButton("OK!", null);
            			error_box.show();
        			}      		
        	}}       	
        });
        
        guest_passes.setOnClickListener(new View.OnClickListener() 
        {
			public void onClick(View v) 
			{
				try
				{
									
					if(gp_int == 0)
					{
						gp_int = 0;
					}
					else
					{
						gp_int--;
					}
					
					guestData = Integer.toString(gp_int);
					guest_passes.setText(guestData);
				} 
				catch(NumberFormatException e)
				{
					guest_passes.setText("Error");
				}
			}
		}); 
        
        submit_button.setOnClickListener(new View.OnClickListener() 
        {			
			public void onClick(View v) 
			{
				String direction = "calculator";
				mealData = meal_swipes.getText().toString();
				diningData = flex_dollars.getText().toString();
				guestData = guest_passes.getText().toString();
				
				try
				{
					Class ourClass = Class.forName("com.gamma.lackmann.mobile.Confirm");
					Intent ourIntent = new Intent(Calculator.this, ourClass);
					
					ourIntent.putExtra("direction", direction);
					ourIntent.putExtra("mealswipes", mealData);
					ourIntent.putExtra("flexdollars", diningData);
					ourIntent.putExtra("guestpasses", guestData);
					
					startActivity(ourIntent);
				}
				catch(ClassNotFoundException e)
				{
					error_box.setTitle("Application Error");
					error_box.setIcon(R.drawable.app_icon);
					error_box.setNeutralButton("OK!", null);
					error_box.setMessage("Could not direct to desired page.\nPlease try again!");
					error_box.show();
				}
			}
		});
        
        edit_button.setOnClickListener(new View.OnClickListener() 
        {						
			public void onClick(View v) 
			{
				mealData = meal_swipes.getText().toString();
				diningData = flex_dollars.getText().toString();
				guestData = guest_passes.getText().toString();
				
				try
				{
					Class ourClass = Class.forName("com.gamma.lackmann.mobile.Edit_Screen");
					Intent ourIntent = new Intent(Calculator.this, ourClass);
					
					ourIntent.putExtra("mealswipes", mealData);
					ourIntent.putExtra("flexdollars", diningData);
					ourIntent.putExtra("guestpasses", guestData);
					
					startActivity(ourIntent);
				}
				catch(ClassNotFoundException e)
				{
					error_box.setTitle("Application Error");
					error_box.setIcon(R.drawable.app_icon);
					error_box.setNeutralButton("OK!", null);
					error_box.setMessage("Could not direct to desired page.\nPlease try again!");
					error_box.show();
				} 		
			}
		});
        
        cancel_button.setOnClickListener(new View.OnClickListener() 
        {			
			public void onClick(View v) 
			{	    	
				try
				{
					Class ourClass = Class.forName("com.gamma.lackmann.mobile.Main_Menu");
					Intent ourIntent = new Intent(Calculator.this, ourClass);
					
					startActivity(ourIntent);
				}
				catch(ClassNotFoundException e)
				{
					error_box.setTitle("Application Error");
					error_box.setIcon(R.drawable.app_icon);
					error_box.setNeutralButton("OK!", null);
					error_box.setMessage("Could not direct to desired page.\nPlease try again!");
					error_box.show();
				} 		
			}
		});
    }
    // END Method ID# 5.2
    
    // Initializes variables and gives TextView labels special font
	private void setupVariables() 
	{
		  meal_swipes = (Button) findViewById(R.id.meal_swipes);
	      flex_dollars = (Button) findViewById (R.id.flex_dollars);
	      guest_passes = (Button) findViewById(R.id.guest_passes);
	      submit_button = (Button) findViewById(R.id.submit);
	      edit_button = (Button) findViewById(R.id.edit);
	      cancel_button = (Button) findViewById(R.id.cancel);
	        
	      someData = getSharedPreferences("MySharedString",0);
	      mealData = someData.getString("Meal", "Couldn't load data");
	      diningData = someData.getString("Dining", "Couldn't load data");
	      guestData = someData.getString("Guest", "Couldn't load data");
	    		
	      ms_int = Integer.parseInt(mealData);
	      fd_total = Double.parseDouble(diningData);
	      gp_int = Integer.parseInt(guestData);
	    		
	      meal_swipes.setText(mealData);
	      flex_dollars.setText(diningData);
	      guest_passes.setText(guestData);
	      
	      Typeface tf = Typeface.createFromAsset(getAssets(),
	                "fonts/Champagne & Limousines Bold.ttf");
	      TextView t1 = (TextView) findViewById(R.id.meal_swipes_label);
	      TextView t2 = (TextView) findViewById(R.id.flex_dollars_label);
	      TextView t3 = (TextView) findViewById(R.id.guest_passes_label);
	      t1.setTypeface(tf);
	      t2.setTypeface(tf);
	      t3.setTypeface(tf);
	} 
}