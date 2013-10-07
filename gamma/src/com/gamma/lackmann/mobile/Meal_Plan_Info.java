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
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Meal_Plan_Info extends Activity
{
	Context ctx = this;
	Button meal_options, meal_costs;
	
	String direction;
	
	public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.meal_plan_info); 
        
        setupVariables();
        
        final AlertDialog.Builder error_box = new AlertDialog.Builder(ctx);
        
        meal_options.setOnClickListener(new View.OnClickListener() 
        {			
			public void onClick(View v) 
			{
				try
				{
					direction = "meal_options";
					Class ourClass = Class.forName("com.gamma.lackmann.mobile.Browser");
					Intent ourIntent = new Intent(ctx, ourClass);
					ourIntent.putExtra("direction", direction);
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
        
        meal_costs.setOnClickListener(new View.OnClickListener() 
        {		
			public void onClick(View v) 
			{
				try
				{
					direction = "meal_costs";
					Class ourClass = Class.forName("com.gamma.lackmann.mobile.Browser");
					Intent ourIntent = new Intent(ctx, ourClass);
					ourIntent.putExtra("direction", direction);
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
	 
	private void setupVariables() 
	{
		meal_options = (Button) findViewById(R.id.meal_options);
		meal_costs = (Button) findViewById (R.id.meal_costs);
	}
}
