/*
		Lackmann Mobile Android Application
		Version 1.1
	
		Created by: John Russo, Wade Kline, Matthew Staples, and Nathan Sunseri
	
		St. John Fisher College 
		Spring 2013																	*/

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
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.widget.ImageView;

public class Splash_Screen extends Activity 
{
	Context ctx = this;
	
	final File path = Environment.getExternalStorageDirectory(); 
	final File menu = new File(path + "/menu.xls");
	final File nutrition = new File(path + "/nutrition.xls");
	final File promo = new File(path + "/promo.jpg");
	String promo_path = path + "/promo.jpg";
	
	URL url = null;
	URLConnection con = null;
	String fileName;
	
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        
        final AlertDialog.Builder error_box = new AlertDialog.Builder(ctx);
        
        if(isOnline() == true)
	    {
	         new downloadfile().execute("");
	         setContentView(R.layout.splash_promo);
	    }
        else
        {
			error_box.setTitle("Internet Connection Error");
			error_box.setIcon(R.drawable.app_icon);
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
					sleep(5000);
				} 
				catch(InterruptedException e) 
				{
					error_box.setTitle("Application Error");
					error_box.setIcon(R.drawable.app_icon);
					error_box.setNeutralButton("OK!", null);
					error_box.setMessage("Could not direct to desired page.\nPlease try again!");
					error_box.show();
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
	private class downloadfile extends AsyncTask<String, String, String>
	{
		String s;
		ProgressDialog mDialog;
		
		@Override
		protected void onPreExecute() 
		{
			mDialog = new ProgressDialog(ctx);
            mDialog.setMessage("Loading Promo...");
            mDialog.setCancelable(false);
            mDialog.show();			 
        }

		
		@Override
		protected String doInBackground(String... arg0) 
		{
			final AlertDialog.Builder error_box = new AlertDialog.Builder(ctx);
			
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
		        
				url = new URL("http://monroe.sjfc.edu/lm-xml/promo.jpg");
				con = url.openConnection();		
		        fileName = "promo.jpg";
		        
		        inStream = new BufferedInputStream(con.getInputStream());
			    fileStream = new FileOutputStream(promo);
			    outStream = new BufferedOutputStream(fileStream, 1024);
			    
			    byte[] data3 = new byte[1024];
			    bytesRead = 0;
			    
			    while((bytesRead = inStream.read(data3, 0, data3.length)) >= 0)
		        {
		            outStream.write(data3, 0, bytesRead);
		        }
			    
			    outStream.flush();
			    outStream.close();
		        fileStream.close();
		        inStream.close(); 		    							    
			} 
	        catch (MalformedURLException e) 
			{
				error_box.setTitle("Server Connection Error");
				error_box.setIcon(R.drawable.app_icon);
				error_box.setNeutralButton("OK!", null);
				error_box.setMessage("Server is unavailable.\n\n" +
						                   "**Menus may be unavailable or out-of-date.");
				error_box.show();
			} 
	        catch (IOException e) 
	        {
				error_box.setTitle("Server Connection Error");
				error_box.setIcon(R.drawable.app_icon);
				error_box.setNeutralButton("OK!", null);
				error_box.setMessage("Server is unavailable.\n\n" +
						                   "**Menus may be unavailable or out-of-date.");
				error_box.show();
			}   	
 
			return s;
		}
		
		@Override
		protected void onPostExecute(String result) 
		{
			try
			{
				mDialog.dismiss();
				ImageView promo1 = (ImageView) findViewById(R.id.promo);
				BitmapFactory.Options options = new BitmapFactory.Options();
				Bitmap bm = BitmapFactory.decodeFile(promo_path, options);
				promo1.setImageBitmap(bm);
			}
			catch(Exception e)
			{
				final AlertDialog.Builder error_box = new AlertDialog.Builder(ctx);
				error_box.setTitle("Promotional Error");
				error_box.setIcon(R.drawable.app_icon);
				error_box.setNeutralButton("OK!", null);
				error_box.setMessage("Promotion is unavailable.\n");
				error_box.show();
			}
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


