package com.example.ts2;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.os.SystemClock;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;


public class Tab3 extends Activity{
	
	@SuppressLint("NewApi")
	@Override
	public void onCreate(Bundle savedInstanceState){
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tab3);
		TableLayout table = (TableLayout) findViewById(R.id.table1);
		TableRow tableRow;
		
		tableRow = new TableRow(this);
		tableRow.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
	 	
		TextView date;
	 	date=createCell( "Android version: "+Build.HARDWARE+" " +Build.VERSION.RELEASE+"\n");//
	 	
	 	tableRow.addView(date);
	 	tableRow.setGravity(Gravity.CENTER);
	 	table.addView(tableRow);
	 	
	 	
	 	tableRow = new TableRow(this);
		tableRow.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
	 	
	 	date=createCell( "Device: "+Build.BRAND+" "+Build.MODEL+"\n");//
	 	
	 	tableRow.addView(date);
	 	tableRow.setGravity(Gravity.CENTER);
	 	table.addView(tableRow);
	 	
		
	    long sec = SystemClock.uptimeMillis()/1000;
	    long hours = sec / 3600 ;
	    sec = sec % 3600;
	    long min = sec / 60;
	    sec = sec % 60;	  
	    
	    tableRow = new TableRow(this);
		tableRow.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
	   
	 	date=createCell( "Uptime: "+hours+":"+min+":"+sec+"\n");
	 	
	 	tableRow.addView(date);
	 	tableRow.setGravity(Gravity.CENTER);
	 	table.addView(tableRow);
	 	
	    
	     
	    Intent batteryIntent = registerReceiver(null, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
	    int level = batteryIntent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
	    int scale = batteryIntent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
	    float percentage = (float)level / (float)scale * 100.0f; 
	    
	    tableRow = new TableRow(this);
		tableRow.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
	 	
	 	date=createCell( "Battery level: "+percentage+" %" +"\n");
	 	
	 	tableRow.addView(date);
	 	tableRow.setGravity(Gravity.CENTER);
	 	table.addView(tableRow);
	    
	    StatFs stat = new StatFs( Environment.getDataDirectory().getAbsolutePath());
	    
	    int av = stat.getAvailableBlocks()*stat.getBlockSize();
		int tot = stat.getBlockCount()*stat.getBlockSize();
	    
		//internal memory
		tableRow = new TableRow(this);
		tableRow.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
		
	 	date=createCell("Internal Memory (Available/Total): "+(int)(av/1048576)+"/" + (int) tot/1048576+"\n");
	 	
	 	tableRow.addView(date);
	 	tableRow.setGravity(Gravity.CENTER);
	 	table.addView(tableRow);
		
	 	
	 	
	    //memory
	    if (android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED)){
	        
        	
	    	stat = new StatFs(Environment.getExternalStorageDirectory().getPath());
	    	
	    	av = stat.getAvailableBlocks()*stat.getBlockSize();
		    tot = stat.getBlockCount()*stat.getBlockSize();
		    
		    
		    tableRow = new TableRow(this);
			tableRow.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
		 	
		 	date=createCell("External Memory (Available/Total): "+av/1048576+"/"+tot/1048576 +"\n");
		 
		 	tableRow.addView(date);
		 	tableRow.setGravity(Gravity.CENTER);
		 	table.addView(tableRow);
	    }
	    else 
	    {
	    	tableRow = new TableRow(this);
			tableRow.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
		 	date=createCell("There is no external memory mounted!"+"\n");
		 	
		 	tableRow.addView(date);
		 	tableRow.setGravity(Gravity.CENTER);
		 	table.addView(tableRow);
	    	
	    }
	    
	    
	    //kernel information
	    tableRow = new TableRow(this);
		tableRow.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
	 	date =createCell("Kernel Info: "+System.getProperty("os.version")+"\n" );
	 	
	 	tableRow.addView(date);
	 	tableRow.setGravity(Gravity.CENTER);
	 	table.addView(tableRow);
    	
	    
	 	
	 	//About connectivity
	    ConnectivityManager cm = (ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
	    
	    NetworkInfo ni[] = cm.getAllNetworkInfo();
	    
	    if (cm != null)
	    {
	    
	    //connectivity manager
	    tableRow = new TableRow(this);
		tableRow.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
	 	
	 	String s="Connection Information:\n"; 
	 	
	 	for(NetworkInfo n : ni)
	 	{
	 		
	 		if((n.getTypeName().equals("wifi")||n.getTypeName().equals("mobile"))){
	 			s += (n.getTypeName()+" "+n.getState()) +"\n";
	 		}
	 	}
	 	
	 	date=createCell(s);
	 	tableRow.addView(date);
	 	tableRow.setGravity(Gravity.CENTER);
	 	table.addView(tableRow);
	    }
    
}
	private TextView createCell(String text) {

		TextView view = new TextView(this);
		view.setText(String.valueOf(text));
		view.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
		view.setTextColor(Color.CYAN);
		
			view.setPadding(2, 2, 10, 2);
		

		view.setGravity(Gravity.CENTER);

		return view;

	}
}
