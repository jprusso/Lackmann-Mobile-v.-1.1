/*
		Lackmann Mobile Android Application
		Version 1.1
	
		Created by: John Russo, Wade Kline, Matthew Staples, and Nathan Sunseri
	
		St. John Fisher College 
		Spring 2012																	*/

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
		
		// BEGIN Method ID# 5.0
		submit.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View v) 
			{
				
				final AlertDialog.Builder alert = new AlertDialog.Builder(ctx);
				MealData = MealSwipes.getText().toString();
				DiningData = DiningDollars.getText().toString();
				GuestData = GuestPasses.getText().toString();
				
				if(MealData.trim().equals("") || DiningData.trim().equals("") || GuestData.trim().equals("") )
        		{
        			alert.setTitle("Must Enter a Number!");
        			alert.setNeutralButton("OK!", null);
        			alert.show();
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
								e.printStackTrace();
							}
							catch(ClassNotFoundException e)
							{
								e.printStackTrace();
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
