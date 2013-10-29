/*
		Lackmann Mobile Android Application
		Version 1.1
	
		Created by: John Russo, Wade Kline, Matthew Staples, Robert Jones, and Nathan Sunseri
	
		St. John Fisher College 
		Spring 2013																	*/

package com.gamma.lackmann.mobile;

import java.io.File;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.FragmentActivity;
import android.util.FloatMath;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;

public class Promo extends FragmentActivity implements OnTouchListener
{ 
	Context ctx = this;
	
	final File path = Environment.getExternalStorageDirectory(); 
	final File promo_path = new File(path + "/promo.jpg");
	String promo_path1 = path + "/promo.jpg";
	
	private static final String TAG = "Touch" ;
    Matrix matrix = new Matrix();
    Matrix savedMatrix = new Matrix();
    static final int NONE = 0;
    static final int DRAG = 1;
    static final int ZOOM = 2;
    int mode = NONE;
    
    // Drag and Zoom Points
    PointF start = new PointF();
    PointF mid = new PointF();
    float oldDist = 1f;
    
	@Override
	public void onCreate(Bundle savedInstanceState)  
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_promo);
        
        ImageView promo = (ImageView) findViewById(R.id.promo);
        BitmapFactory.Options options = new BitmapFactory.Options();
        Bitmap bm = BitmapFactory.decodeFile(promo_path1, options);
        promo.setImageBitmap(bm); 
        promo.setOnTouchListener(this);
        
        final AlertDialog.Builder alert_box = new AlertDialog.Builder(ctx);
		alert_box.setTitle("Promo!");
		alert_box.setIcon(R.drawable.app_icon);
		alert_box.setNeutralButton("OK!", null);
		alert_box.setMessage("Click to Drag and Zoom!");
		alert_box.show();
    }

	/* Method to handle user touch, allowing for DRAG/ZOOM */
	public boolean onTouch(View v, MotionEvent event) 
	{
		ImageView view = (ImageView) v;
		view.setScaleType(ImageView.ScaleType.MATRIX);
		dumpEvent(event);

		switch (event.getAction() & MotionEvent.ACTION_MASK) 
		{
	    		case MotionEvent.ACTION_DOWN:
	    				savedMatrix.set(matrix);
	    				start.set(event.getX(), event.getY());
	    				Log.d(TAG, "mode=DRAG");
	    				mode = DRAG;
	    				break;
	    				
	    		case MotionEvent.ACTION_POINTER_DOWN:
	    				oldDist = spacing(event);
	    				Log.d(TAG, "oldDist=" + oldDist);
	    				if (oldDist > 10f) 
	    				{
	    					savedMatrix.set(matrix);
	    					midPoint(mid, event);
	    					mode = ZOOM;
	    					Log.d(TAG, "mode=ZOOM");
	    				}
	    				break;
	    				
	    		case MotionEvent.ACTION_UP:
	    			
	    		case MotionEvent.ACTION_POINTER_UP:
	    				mode = NONE;
	    				Log.d(TAG, "mode=NONE");
	    				break;
	    				
	    		case MotionEvent.ACTION_MOVE:
	    				if (mode == DRAG) 
	    				{
	    					matrix.set(savedMatrix);
	    					matrix.postTranslate(event.getX() - start.x,
	    					event.getY() - start.y);
	    				}
	    				else if (mode == ZOOM) 
	    				{
	    					float newDist = spacing(event);
	    					Log.d(TAG, "newDist=" + newDist);
	    					if (newDist > 10f) 
	    					{
	    						matrix.set(savedMatrix);
	    						float scale = newDist / oldDist;
	    						matrix.postScale(scale, scale, mid.x, mid.y);
	    					}
	    				}
	    				break;
	      }

		view.setImageMatrix(matrix);
		return true;
	}
	
	private void dumpEvent(MotionEvent event) 
	{
		String names[] = { "DOWN" , "UP" , "MOVE" , "CANCEL" , "OUTSIDE" ,
		"POINTER_DOWN" , "POINTER_UP" , "7?" , "8?" , "9?" };
		StringBuilder sb = new StringBuilder();
		int action = event.getAction();
		int actionCode = action & MotionEvent.ACTION_MASK;
		sb.append("event ACTION_" ).append(names[actionCode]);
		
		if (actionCode == MotionEvent.ACTION_POINTER_DOWN || actionCode == MotionEvent.ACTION_POINTER_UP) 
		{
			sb.append("(pid " ).append(
			action >> MotionEvent.ACTION_POINTER_ID_SHIFT);
			sb.append(")" );
		}
		sb.append("[" );
		
		for (int i = 0; i < event.getPointerCount(); i++) 
		{
			sb.append("#" ).append(i);
			sb.append("(pid " ).append(event.getPointerId(i));
			sb.append(")=" ).append((int) event.getX(i));
			sb.append("," ).append((int) event.getY(i));
			
			if (i + 1 < event.getPointerCount())
				sb.append(";" );
		}
		sb.append("]" );
		Log.d(TAG, sb.toString());
	}

	private float spacing(MotionEvent event) 
	{
		float x = event.getX(0) - event.getX(1);
	    float y = event.getY(0) - event.getY(1);
	    return FloatMath.sqrt(x * x + y * y);
	}

	private void midPoint(PointF point, MotionEvent event) 
	{
		float x = event.getX(0) + event.getX(1);
	    float y = event.getY(0) + event.getY(1);
	    point.set(x / 2, y / 2);
	}
}
