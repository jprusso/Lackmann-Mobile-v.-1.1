/*
		Lackmann Mobile Android Application
		Version 1.1
	
		Created by: John Russo, Wade Kline, Matthew Staples, and Nathan Sunseri
	
		St. John Fisher College 
		Spring 2013																	*/

package com.gamma.lackmann.mobile;

import java.io.File;
import java.util.List;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;
import twitter4j.util.TimeSpanConverter;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Main_Menu extends Activity
{
	Context ctx = this;
	
	Button menus_button, hours_button, nutrition_button, mealplan_button,
			contact_button, baglunch_button, mealcalc_button;
	
	ImageButton twitter_button, facebook_button, promo_button;
	
	SharedPreferences someData;
	File f = new File("/data/data/com.gamma.lackmann.mobile/shared_prefs/MySharedString.xml");
	
	final String user_name = "@Lackmann_Fisher";
	
	String s;
	
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
	    setContentView(R.layout.main_menu);
	    setupVariables();
	   
	    menus_button.setOnClickListener(new View.OnClickListener() 
		{
			public void onClick(View v) 
			{
				try
				{
					Class ourClass = Class.forName("com.gamma.lackmann.mobile.Location_Select");
					Intent ourIntent = new Intent(Main_Menu.this, ourClass);
					startActivity(ourIntent);
				}
				catch(ClassNotFoundException e)
				{
					e.printStackTrace();
				} 
			}
		});
	        
	    hours_button.setOnClickListener(new View.OnClickListener() 
	    {
			public void onClick(View v) 
			{
				try
				{
					Class ourClass = Class.forName("com.gamma.lackmann.mobile.Hours_Info");
					Intent ourIntent = new Intent(Main_Menu.this, ourClass);
					startActivity(ourIntent);
				}
				catch(ClassNotFoundException e)
				{
					e.printStackTrace();
				} 
			}
		});
	                
	    mealplan_button.setOnClickListener(new View.OnClickListener() 
	    {
			public void onClick(View v) 
			{
				try
				{
					Class ourClass = Class.forName("com.gamma.lackmann.mobile.Meal_Plan_Info");
					Intent ourIntent = new Intent(Main_Menu.this, ourClass);
					startActivity(ourIntent);
				}
				catch(ClassNotFoundException e)
				{
					e.printStackTrace();
				} 
			}
		});
	        
	    contact_button.setOnClickListener(new View.OnClickListener() 
	    {
			public void onClick(View v) 
			{
				try
				{
					Class ourClass = Class.forName("com.gamma.lackmann.mobile.Contact_Info");
					Intent ourIntent = new Intent(Main_Menu.this, ourClass);
					startActivity(ourIntent);
				}
				catch(ClassNotFoundException e)
				{
					e.printStackTrace();
				} 
			}
		});
	        
	    baglunch_button.setOnClickListener(new View.OnClickListener() 
	    {
			public void onClick(View v) 
			{
				Uri uriUrl = Uri.parse("http://www.sjfc.edu/student-life/dining/bag-lunch.dot");
				Intent launch = new Intent(Intent.ACTION_VIEW, uriUrl);
				startActivity(launch);		
			}
		});
	        
	    mealcalc_button.setOnClickListener(new View.OnClickListener() 
	    {
			public void onClick(View v) 
			{
				if(f.exists())
				{
					try
					{
						Class ourClass = Class.forName("com.gamma.lackmann.mobile.Calculator");
						Intent ourIntent = new Intent(Main_Menu.this, ourClass);
						startActivity(ourIntent);
					}
					catch(ClassNotFoundException e)
					{
						e.printStackTrace();
					}
				}
				else
				{
					try
					{
						Class ourClass = Class.forName("com.gamma.lackmann.mobile.First_Time");
						Intent ourIntent = new Intent(Main_Menu.this, ourClass);
						startActivity(ourIntent);
					}
					catch(ClassNotFoundException e)
					{
						e.printStackTrace();
					}			
				}
			}
		});
	        
	    twitter_button.setOnClickListener(new View.OnClickListener() 
	    {
			public void onClick(View v) 
			{
				new twitterfeed().execute("");
			} 
		});
	    
	    //BEGIN METHOD ID# 2.0
	    facebook_button.setOnClickListener(new View.OnClickListener() 
	    {
	    	public void onClick(View v) 
			{
				Uri uriUrl = Uri.parse("https://www.facebook.com/fisherdining");
				Intent launch = new Intent(Intent.ACTION_VIEW, uriUrl);
				startActivity(launch);		
			}
		});
	    //END METHOD ID# 2.0
	    
	    //BEGIN METHOD ID# 2.1
	    promo_button.setOnClickListener(new View.OnClickListener() 
	    {
			public void onClick(View v) 
			{
				try
				{
					Class ourClass = Class.forName("com.gamma.lackmann.mobile.Promo");
					Intent ourIntent = new Intent(Main_Menu.this, ourClass);
					startActivity(ourIntent);
				}
				catch(ClassNotFoundException e)
				{
					e.printStackTrace();
				} 
			}
		});
	    //END METHOD ID# 2.1
	}
	
	//BEGIN METHOD ID# 2.2
	private class twitterfeed extends AsyncTask<String, Void, String>
    {
    	String createdAt,t;
			
		@Override
		protected String doInBackground(String... arg0) 
		{
			String consumerkey = "PbPsNkG4TlqUwuJNmVmyAA";  
		    String consumersecret = "q3II4rs0JDIyWU18FAesT6M9pNAQX7RrZCXMainpSU4";
		    String accesstoken = "983766331-sWk2cadUq6PnTMJtiPNF7G50IU3C1GAtxO05EqYO";
		    String accesstokensecret = "FalpL8B8cGEztL02pCMn9t8bBw68EeQ60O1Gh1AGY";
		        
		    ConfigurationBuilder cd = new ConfigurationBuilder();
		        
		    cd.setDebugEnabled(true)
		     .setOAuthConsumerKey(consumerkey)
		     .setOAuthConsumerSecret(consumersecret)
		     .setOAuthAccessToken(accesstoken)
		     .setOAuthAccessTokenSecret(accesstokensecret);
		        
		     TwitterFactory t1 = new TwitterFactory(cd.build());
		     Twitter twitter = t1.getInstance(); 
		     
		     TimeSpanConverter converter = new TimeSpanConverter();	    
		     
		     try 
		     {
		         List<twitter4j.Status> statuses;
		         statuses = twitter.getUserTimeline(user_name);
		         
		         for (twitter4j.Status status : statuses) 
		         {
		        	 s = status.getText();
		        	 createdAt = converter.toTimeSpanString(status.getCreatedAt());		
		             break;
		         } 
		      } 
		      catch (TwitterException te) 
		      {
		    	  s = "Failed to get timeline: " + te.getMessage();
		      }
		          
		      return s;
		}
			
    	protected void onPostExecute(String result)
    	{	
			AlertDialog.Builder twitter1 = new AlertDialog.Builder(ctx);
			twitter1.setTitle(user_name);
			twitter1.setIcon(R.drawable.lackmann_twitterpic);
				
			twitter1.setMessage(s + "\n" + createdAt);
			 
			twitter1.setPositiveButton("Tweet!", new DialogInterface.OnClickListener() {
					
				public void onClick(DialogInterface dialog,int id) 
				{
					Uri uriUrl = Uri.parse("https://twitter.com/Lackmann_Fisher");
					Intent launch = new Intent(Intent.ACTION_VIEW, uriUrl);
					startActivity(launch);
				}
			});
			 
			twitter1.setNeutralButton("Cancel", null);				
			 
			twitter1.show();
		}
    }
	//END METHOD ID# 2.2
	
	private void setupVariables() 
	{
	    menus_button = (Button) findViewById(R.id.menus_button);
	    hours_button = (Button) findViewById(R.id.hours_button);
	    mealplan_button = (Button) findViewById(R.id.mealplan_button);
	    contact_button = (Button) findViewById(R.id.contact_button);
	    baglunch_button = (Button) findViewById(R.id.baglunch_button);
	    mealcalc_button = (Button) findViewById(R.id.mealcalc_button);
	    twitter_button = (ImageButton) findViewById(R.id.twitter_button);
	    facebook_button = (ImageButton) findViewById(R.id.facebook_button);
	    promo_button = (ImageButton) findViewById(R.id.promo_button);
	}
	
	//BEGIN METHOD ID# 2.3
	public boolean isOnline()
    {
    	ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
    	NetworkInfo netinfo = cm.getActiveNetworkInfo();
    		
    	if(netinfo != null && netinfo.isConnectedOrConnecting())
    	{
    		return true;
    	}   		
    	return false;
    }
	//END METHOD ID# 2.3
}