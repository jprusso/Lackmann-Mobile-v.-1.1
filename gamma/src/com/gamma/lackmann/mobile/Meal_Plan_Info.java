/*
		Lackmann Mobile Android Application
		Version 1.1
	
		Created by: John Russo, Wade Kline, Matthew Staples, and Nathan Sunseri
	
		St. John Fisher College 
		Spring 2012																	*/

package com.gamma.lackmann.mobile;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Meal_Plan_Info extends Activity
{
	Button meal_options, meal_costs;
	
	public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.meal_plan_info);    
        setupVariables();
        
        meal_options.setOnClickListener(new View.OnClickListener() 
        {			
			public void onClick(View v) 
			{
				Uri uriUrl = Uri.parse("http://www.sjfc.edu/student-life/dining/plans.dot");
				Intent launch = new Intent(Intent.ACTION_VIEW, uriUrl);
				startActivity(launch);
			
			}
        });
        
        meal_costs.setOnClickListener(new View.OnClickListener() 
        {			
			public void onClick(View v) 
			{
				Uri uriUrl = Uri.parse("http://www.sjfc.edu/campus-services/bursar/undergrad-costs.dot");
				Intent launch = new Intent(Intent.ACTION_VIEW, uriUrl);
				startActivity(launch);
			
			}
        });
    }
	 
	private void setupVariables() 
	{
		meal_options = (Button) findViewById(R.id.meal_options);
		meal_costs = (Button) findViewById (R.id.meal_costs);
	}
}
