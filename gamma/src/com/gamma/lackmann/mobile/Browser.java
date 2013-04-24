/*
		Lackmann Mobile Android Application
		Version 1.1
	
		Created by: John Russo, Wade Kline, Matthew Staples, and Nathan Sunseri
	
		St. John Fisher College 
		Spring 2013																	*/

package com.gamma.lackmann.mobile;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.webkit.WebView;

public class Browser extends Activity
{
	Context ctx = this;
	
	final String filename = "MySharedString";
	SharedPreferences someData;
	String s;
	
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.browser);
		
		someData = getSharedPreferences(filename, 0);
		Intent intent = getIntent();
		s = intent.getStringExtra("direction");

		WebView webview;
		webview = (WebView) findViewById(R.id.webview);
		webview.getSettings().setJavaScriptEnabled(true);

		// Depending on value, will direct to desired Lackmann page from SJFC website
		// Using JRE 6, therefore used if statements instead of switch statement
		if(s.equals("hours"))
		{
	        webview.loadUrl("http://www.sjfc.edu/student-life/dining/hours-locations.dot");
		}
		else if(s.equals("contact"))
		{
	        webview.loadUrl("http://www.sjfc.edu/student-life/dining/contact.dot");
		}
		else if(s.equals("baglunch"))
		{
	        webview.loadUrl("http://www.sjfc.edu/student-life/dining/bag-lunch.dot");
		}
		else if(s.equals("meal_options"))
		{
	        webview.loadUrl("http://www.sjfc.edu/student-life/dining/plans.dot");
		}
		else if(s.equals("meal_costs"))
		{
	        webview.loadUrl("http://www.sjfc.edu/campus-services/bursar/undergrad-costs.dot");
		}
		else
		{
			webview.loadUrl("http://www.sjfc.edu/student-life/dining/");
		}

	}
}
