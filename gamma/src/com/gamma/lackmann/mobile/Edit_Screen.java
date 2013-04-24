/*
		Lackmann Mobile Android Application
		Version 1.1
	
		Created by: John Russo, Wade Kline, Matthew Staples, and Nathan Sunseri
	
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

public class Edit_Screen extends Activity 
{
	Context ctx = this;
	
	EditText MealSwipes, DiningDollars, GuestPasses;
	TextView dataResults;
	Button submit;
	
	String mealData, diningData, guestData;
	public static String filename = "MySharedString";
	
	SharedPreferences someData;
		
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit_screen);
		
        //Makes sure keyboard does not automatically open
        getWindow().setSoftInputMode(
        	    WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		
		setupVariables();
			
		// BEGIN Method ID# 5.1
		submit.setOnClickListener(new View.OnClickListener()
		{

			public void onClick(View v) 
			{
				AlertDialog.Builder error_box = new AlertDialog.Builder(ctx);
				
				String MealData = MealSwipes.getText().toString();
				String DiningData = DiningDollars.getText().toString();
				String GuestData = GuestPasses.getText().toString();
				
				switch (v.getId()) 
				{
					case R.id.ftSubmit:
							
						if(MealData.trim().equals(""))
						{
							MealData = mealData;
							MealSwipes.setText(mealData);							
						}
						
						if(DiningData.trim().equals(""))
						{
							DiningData = diningData;
							DiningDollars.setText(diningData);
						}
						
						if(GuestData.trim().equals(""))
						{
							GuestData = guestData;
							GuestPasses.setText(guestData);
						}
												
						try 
						{
							String direction = "editscreen";
							Class ourClass = Class.forName("com.gamma.lackmann.mobile.Confirm");
							Intent intent = new Intent(ctx, ourClass);
							
							intent.putExtra("direction", direction);
							intent.putExtra("mealswipes", MealData);
							intent.putExtra("flexdollars", DiningData);
							intent.putExtra("guestpasses", GuestData);
							
							startActivity(intent);
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
		});
	}
	// END Method ID# 5.1
	
	private void setupVariables() 
	{
		MealSwipes = (EditText) findViewById(R.id.etMealSwipes);
		DiningDollars = (EditText) findViewById(R.id.etDiningDollars);
		GuestPasses = (EditText) findViewById(R.id.etGuestPasses);	
		
		someData = this.getSharedPreferences(filename, MODE_WORLD_READABLE);
		submit = (Button) findViewById(R.id.ftSubmit);
		
		Intent intent = getIntent();
		
		mealData = intent.getStringExtra("mealswipes");
		diningData = intent.getStringExtra("flexdollars");
		guestData = intent.getStringExtra("guestpasses");;
		
		MealSwipes.setHint(mealData);
		DiningDollars.setHint(diningData);
		GuestPasses.setHint(guestData);
		
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
	}
}
