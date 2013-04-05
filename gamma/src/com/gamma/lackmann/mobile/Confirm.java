/*
		Lackmann Mobile Android Application
		Version 1.1
	
		Created by: John Russo, Wade Kline, Matthew Staples, and Nathan Sunseri
	
		St. John Fisher College 
		Spring 2012																	*/

package com.gamma.lackmann.mobile;


import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Confirm extends Activity 
{
	Context ctx = this;
	
	TextView MealSwipes, GuestPasses, DiningDollars;
	Button yes, no;
	
	final String filename = "MySharedString";
	String mealData, diningData, guestData, direction;
		
	SharedPreferences someData;
	
	// BEGIN Method ID# 5.3
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.confirm);
		
		someData = getSharedPreferences(filename, 0);		
        
        setupVariables();
               		        
		yes.setOnClickListener(new View.OnClickListener() 
		{		
			public void onClick(View v) 
			{
				try
				{
					SharedPreferences.Editor editor = someData.edit();
					
					editor.putString("Meal", mealData);
					editor.putString("Dining", diningData);
					editor.putString("Guest", guestData);
					
					editor.commit();
					
					if(direction.equals("calculator"))
					{
						Class ourClass = Class.forName("com.gamma.lackmann.mobile.Main_Menu");
						Intent ourIntent = new Intent(ctx, ourClass);
						startActivity(ourIntent);
					}
					else if(direction.equals("firsttime"))
					{
						Class ourClass = Class.forName("com.gamma.lackmann.mobile.Calculator");
						Intent ourIntent = new Intent(ctx, ourClass);
						startActivity(ourIntent);
					}
					else if(direction.equals("editscreen"))
					{
						Class ourClass = Class.forName("com.gamma.lackmann.mobile.Calculator");
						Intent ourIntent = new Intent(ctx, ourClass);
						startActivity(ourIntent);
					}
					
					
				}
				catch(ClassNotFoundException e)
				{
					e.printStackTrace();
				} 					
			}
		});
		
		no.setOnClickListener(new View.OnClickListener() 
		{			
			public void onClick(View v) 
			{
				try
				{					
					if(direction.equals("calculator"))
					{
						Class ourClass = Class.forName("com.gamma.lackmann.mobile.Calculator");
						Intent ourIntent = new Intent(ctx, ourClass);
						startActivity(ourIntent);
					}
					else if(direction.equals("firsttime"))
					{
						Class ourClass = Class.forName("com.gamma.lackmann.mobile.firsttime");
						Intent ourIntent = new Intent(ctx, ourClass);
						startActivity(ourIntent);
					}
					else if(direction.equals("editscreen"))
					{
						Class ourClass = Class.forName("com.gamma.lackmann.mobile.editscreen");
						Intent ourIntent = new Intent(ctx, ourClass);
						ourIntent.putExtra("mealswipes", mealData);
						ourIntent.putExtra("flexdollars", diningData);
						ourIntent.putExtra("guestpasses", guestData);
						startActivity(ourIntent);
					}
				}
				catch(ClassNotFoundException e)
				{
					e.printStackTrace();
				} 					
			}
		});
		
	}
	// END Method ID# 5.3
	
	// Initializes variables and gives TextView labels special font
	private void setupVariables() 
	{	
		yes = (Button) findViewById(R.id.doit);
		no = (Button) findViewById(R.id.nobut);
		MealSwipes = (TextView) findViewById(R.id.tvMealSwipes);
		DiningDollars = (TextView) findViewById(R.id.tvDiningDollars);
		GuestPasses = (TextView) findViewById(R.id.tvGuestPasses);
		
		Intent intent = getIntent();
		
		direction = intent.getStringExtra("direction");
		mealData = intent.getStringExtra("mealswipes");
		diningData = intent.getStringExtra("flexdollars");
		guestData = intent.getStringExtra("guestpasses");
		
		MealSwipes.setText(mealData);
		DiningDollars.setText(diningData);
		GuestPasses.setText(guestData);
		
		someData = getSharedPreferences(filename, 0);		
		
        Typeface tf = Typeface.createFromAsset(getAssets(),
                "fonts/Champagne & Limousines Bold.ttf");
        TextView tv = (TextView) findViewById(R.id.title_label);
        tv.setTypeface(tf);
        tv = (TextView) findViewById(R.id.meal_swipes_label);
        tv.setTypeface(tf);
        tv = (TextView) findViewById(R.id.flex_dollars_label);
        tv.setTypeface(tf);
        tv = (TextView) findViewById(R.id.guest_passes_label);
        tv.setTypeface(tf);
	} 
}
