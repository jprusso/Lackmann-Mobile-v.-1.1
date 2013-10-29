/*
		Lackmann Mobile Android Application
		Version 1.1
	
		Created by: John Russo, Wade Kline, Matthew Staples, Robert Jones, and Nathan Sunseri
	
		St. John Fisher College 
		Spring 2013																	*/

package com.gamma.lackmann.mobile;

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

public class First_Time extends Activity 
{	
	Context ctx = this;
	
	EditText MealSwipes, DiningDollars, GuestPasses;
	TextView dataResults;
	Button submit;
	
	String MealData, DiningData, GuestData;
	
	final static String filename = "MySharedString";
	SharedPreferences someData;
	
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.first_time);
		
		//Makes sure keyboard does not automatically open
        getWindow().setSoftInputMode(
        	    WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		
		setupVariables();	
		
		final AlertDialog.Builder error_box = new AlertDialog.Builder(ctx);
		
		// BEGIN Method ID# 5.0
		submit.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View v) 
			{		
				MealData = MealSwipes.getText().toString();
				DiningData = DiningDollars.getText().toString();
				GuestData = GuestPasses.getText().toString();
				
				if(MealData.trim().equals("") || DiningData.trim().equals("") || GuestData.trim().equals("") )
        		{
        			error_box.setTitle("Data Error");
        			error_box.setIcon(R.drawable.app_icon);
        			error_box.setMessage("Must Enter a Number!");
        			error_box.setNeutralButton("OK!", null);
        			error_box.show();
        		}
				else
				{
					switch (v.getId()) 
					{
						case R.id.ftSubmit:
							
							try 
							{
								String direction = "firsttime";
								Class ourClass = Class.forName("com.gamma.lackmann.mobile.Confirm");
								Intent ourIntent = new Intent(ctx, ourClass);
								
								ourIntent.putExtra("direction", direction);
								ourIntent.putExtra("mealswipes", MealData);
								ourIntent.putExtra("flexdollars", DiningData);
								ourIntent.putExtra("guestpasses", GuestData);
								
								startActivity(ourIntent);
							}
							catch(NullPointerException e)
							{
								error_box.setTitle("Data Retrieval Error");
								error_box.setIcon(R.drawable.app_icon);
								error_box.setNeutralButton("OK!", null);
								error_box.setMessage("Could not find saved data.\n Please try again!");
								error_box.show();
							}
							catch(ClassNotFoundException e)
							{
								error_box.setTitle("Application Error");
								error_box.setIcon(R.drawable.app_icon);
								error_box.setNeutralButton("OK!", null);
								error_box.setMessage("Could not direct to desired page.\nPlease try again!");
								error_box.show();
							} 
					
							break; 			
						}
				}		
			}
		});
	}
	// END Method ID# 5.0
	
	private void setupVariables() 
	{
		MealSwipes = (EditText) findViewById(R.id.etMealSwipes);
		DiningDollars = (EditText) findViewById(R.id.etDiningDollars);
		GuestPasses = (EditText) findViewById(R.id.etGuestPasses);
		
		submit = (Button) findViewById(R.id.ftSubmit);
		
        Typeface tf = Typeface.createFromAsset(getAssets(),
                "fonts/Champagne & Limousines Bold.ttf");
        TextView tv = (TextView) findViewById(R.id.enter_info_label);
        tv.setTypeface(tf);
        tv = (TextView) findViewById(R.id.meal_swipes_label);
        tv.setTypeface(tf);
        tv = (TextView) findViewById(R.id.flex_dollars_label);
        tv.setTypeface(tf);
        tv = (TextView) findViewById(R.id.guest_passes_label);
        tv.setTypeface(tf);
        
        someData = this.getSharedPreferences(filename, MODE_WORLD_READABLE);
	}
}
