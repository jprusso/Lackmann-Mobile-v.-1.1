/*
		Lackmann Mobile Android Application
		Version 1.0
	
		Created by: John Russo, Wade Kline, Matthew Staples, and Nathan Sunseri
	
		St. John Fisher College 
		Fall 2012																	*/

package com.gamma.lackmann.mobile;

import android.os.Bundle;
import android.widget.TextView;
import android.app.Activity;
import android.graphics.Typeface;

public class Contact_Info extends Activity 
{
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_info);    
        setupVariables();
    }
	private void setupVariables() 
	{
        Typeface tf = Typeface.createFromAsset(getAssets(),
                "fonts/Champagne & Limousines Bold.ttf");
        TextView tv = (TextView) findViewById(R.id.contact_label);
        tv.setTypeface(tf);
        tv = (TextView) findViewById(R.id.office_label);
        tv.setTypeface(tf);
        tv = (TextView) findViewById(R.id.contact1);
        tv.setTypeface(tf);
        tv = (TextView) findViewById(R.id.email_label_1);
        tv.setTypeface(tf);
        tv = (TextView) findViewById(R.id.desk_label_1);
        tv.setTypeface(tf);
        tv = (TextView) findViewById(R.id.cell_label_1);
        tv.setTypeface(tf); 
        tv = (TextView) findViewById(R.id.contact2);
        tv.setTypeface(tf);
        tv = (TextView) findViewById(R.id.email_label_2);
        tv.setTypeface(tf);
        tv = (TextView) findViewById(R.id.cell_label_2);
        tv.setTypeface(tf);
        tv = (TextView) findViewById(R.id.contact3);
        tv.setTypeface(tf);
        tv = (TextView) findViewById(R.id.email_label_3);
        tv.setTypeface(tf);
        tv = (TextView) findViewById(R.id.cell_label_3);
        tv.setTypeface(tf); 
        tv = (TextView) findViewById(R.id.contact4);
        tv.setTypeface(tf);
        tv = (TextView) findViewById(R.id.email_label_4);
        tv.setTypeface(tf);
        tv = (TextView) findViewById(R.id.desk_label_2);
        tv.setTypeface(tf);
        tv = (TextView) findViewById(R.id.cell_label_4);
        tv.setTypeface(tf);
        tv = (TextView) findViewById(R.id.contact5);
        tv.setTypeface(tf);
        tv = (TextView) findViewById(R.id.email_label_5);
        tv.setTypeface(tf); 
        tv = (TextView) findViewById(R.id.desk_label_3);
        tv.setTypeface(tf);
        tv = (TextView) findViewById(R.id.cell_label_5);
        tv.setTypeface(tf);
        tv = (TextView) findViewById(R.id.contact6);
        tv.setTypeface(tf);
        tv = (TextView) findViewById(R.id.email_label_6);
        tv.setTypeface(tf);
        tv = (TextView) findViewById(R.id.desk_label_4);
        tv.setTypeface(tf);
        tv = (TextView) findViewById(R.id.cell_label_6);
        tv.setTypeface(tf); 
        tv = (TextView) findViewById(R.id.contact7);
        tv.setTypeface(tf);
        tv = (TextView) findViewById(R.id.desk_label_5);
        tv.setTypeface(tf); 
	} 
} 
