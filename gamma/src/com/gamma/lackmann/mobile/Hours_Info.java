/*
		Lackmann Mobile Android Application
		Version 1.0
	
		Created by: John Russo, Wade Kline, Matthew Staples, and Nathan Sunseri
	
		St. John Fisher College 
		Fall 2012																	*/

package com.gamma.lackmann.mobile;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;


public class Hours_Info extends Activity 
{
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hours_info);    
        setupVariables();
    }
    
	private void setupVariables() 
	{
        Typeface tf = Typeface.createFromAsset(getAssets(),
                "fonts/Champagne & Limousines Bold.ttf");
        TextView tv = (TextView) findViewById(R.id.ward_haffey_label);
        tv.setTypeface(tf);
        tv = (TextView) findViewById(R.id.murphy_label);
        tv.setTypeface(tf);
        tv = (TextView) findViewById(R.id.cyber_label);
        tv.setTypeface(tf);
        tv = (TextView) findViewById(R.id.cardinal_label);
        tv.setTypeface(tf);
        tv = (TextView) findViewById(R.id.fishbowl_label);
        tv.setTypeface(tf);
        tv = (TextView) findViewById(R.id.pioch_label);
        tv.setTypeface(tf);
	} 
}