/*
		Lackmann Mobile Android Application
		Version 1.1
	
		Created by: John Russo, Wade Kline, Matthew Staples, and Nathan Sunseri
	
		St. John Fisher College 
		Spring 2012																	*/

package com.gamma.lackmann.mobile;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;

public class Splash_Screen extends Activity 
{
	Context ctx = this;
	
	final File path = Environment.getExternalStorageDirectory(); 
	final File menu = new File(path + "/menu.xls");
	final File nutrition = new File(path + "/nutrition.xls");
	
	URL url = null;
	URLConnection con = null;
	String fileName;
	
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_promo);
        
        if(isOnline() == true)
	    {
	         new downloadfile().execute("");
	    }
        else
        {
        	AlertDialog.Builder error_box = new AlertDialog.Builder(ctx);
			error_box.setTitle("Internet Connection Error");
			error_box.setIcon(R.drawable.lackmann_twitterpic);
			error_box.setNeutralButton("OK!", null);
			error_box.setMessage("No internet connection detected.\n\n" +
					                   "**Menus may be unavailable or out-of-date.");
			error_box.show();
        }
        
        // BEGIN Method ID# 1.0
		Thread timer = new Thread() 
		{
			public void run()
			{
				try
				{
					sleep(4000);
				} 
				catch(InterruptedException e) 
				{
					e.printStackTrace();
				} 
				finally
				{
					Intent intent = new Intent("com.gamma.lackmann.mobile.Main_Menu");	
					startActivity(intent);
				}		
			}			
		};
		// END Method ID# 1.0
		
		timer.start();
	}
	
	protected void onPause() 
	{
		super.onPause();
		finish();
	}
	
	// BEGIN Method ID# 1.1
	private class downloadfile extends AsyncTask<String, Void, String>
	{
		String s;
		
		@Override
		protected String doInBackground(String... arg0) 
		{
			try 
	        {
			    BufferedInputStream inStream;
			    BufferedOutputStream outStream;
			    FileOutputStream fileStream;
			    int lastSlash;
	    		
				url = new URL("http://monroe.sjfc.edu/lm-xml/menu.xls");
				con = url.openConnection();		
				
			    lastSlash = url.toString().lastIndexOf('/');
			    fileName = "menu.xls";
			    
			    if(lastSlash >=0)
			    {
			        fileName = url.toString().substring(lastSlash + 1);
			    }
			    if(fileName.equals(""))
			    {
			        fileName = "menu.xls";
			    }
			    
			    inStream = new BufferedInputStream(con.getInputStream());
			    fileStream = new FileOutputStream(menu);
			    outStream = new BufferedOutputStream(fileStream, 1024);
			    
			    byte[] data = new byte[1024];
			    int bytesRead = 0;
			    
			    while((bytesRead = inStream.read(data, 0, data.length)) >= 0)
		        {
		            outStream.write(data, 0, bytesRead);
		        }
			    outStream.flush();
			    outStream.close();
		        fileStream.close();
		        inStream.close();
		        
		        url = new URL("http://monroe.sjfc.edu/lm-xml/nutrition.xls");
				con = url.openConnection();		    
			    	       
			    lastSlash = url.toString().lastIndexOf('/');
			    fileName = "nutrition.xls";
			    
			    if(lastSlash >=0)
			    {
			        fileName = url.toString().substring(lastSlash + 1);
			    }
			    if(fileName.equals(""))
			    {
			        fileName = "nutrition.xls";
			    }
			    
			    inStream = new BufferedInputStream(con.getInputStream());
			    fileStream = new FileOutputStream(nutrition);
			    outStream = new BufferedOutputStream(fileStream, 1024);
			    
			    byte[] data2 = new byte[1024];
			    bytesRead = 0;
			    
			    while((bytesRead = inStream.read(data2, 0, data2.length)) >= 0)
		        {
		            outStream.write(data2, 0, bytesRead);
		        }
			    outStream.flush();
			    outStream.close();
		        fileStream.close();
		        inStream.close();
		   	        
			} 
	        catch (MalformedURLException e) 
			{
				e.printStackTrace();
			} 
	        catch (IOException e) 
	        {
	        	AlertDialog.Builder error_box = new AlertDialog.Builder(ctx);
				error_box.setTitle("Server Connection Error");
				error_box.setIcon(R.drawable.lackmann_twitterpic);
				error_box.setNeutralButton("OK!", null);
				error_box.setMessage("Server is unavailable.\n\n" +
						                   "**Menus may be unavailable or out-of-date.");
				error_box.show();
			}   	
 
			return s;
		}

	}
	// END Method ID# 1.1
	
	// BEGIN Method ID# 1.2
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
    // END Method ID# 1.2
}


