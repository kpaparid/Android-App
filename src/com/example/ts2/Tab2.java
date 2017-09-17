package com.example.ts2;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class Tab2 extends Activity {

	String process;
	int id;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.tab2);
		
		TableLayout table = (TableLayout) findViewById(R.id.table5);
		TextView P_ID;
		TextView memory1;
		TextView memory2;
		TextView memory3;
		TextView name;
		TableRow tableRow;
		tableRow = new TableRow(this);
		
	 	P_ID=createCell("PID");
	 	name =createCell("PROCESS");
	 	memory1 = createCell("Private Dirty");
	 	memory2= createCell("PSS");
	 	memory3=createCell("Shared Dirty");
	 	
		
		tableRow.addView(P_ID);
		tableRow.addView(name);
	 	tableRow.addView(memory1);
	 	tableRow.addView(memory2);
	 	tableRow.addView(memory3);
	 	
	 	
	 	tableRow.setGravity(Gravity.CENTER);
	 	table.addView(tableRow);
	 	
	 	ActivityManager activityManager = (ActivityManager)getSystemService(Context.ACTIVITY_SERVICE);
		android.app.ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
		activityManager.getMemoryInfo(memoryInfo);
		List<RunningAppProcessInfo> runningAppProcesses = activityManager.getRunningAppProcesses();

		Map<Integer, String> pidMap = new TreeMap<Integer, String>();
		for (RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses)
		{
		    pidMap.put(runningAppProcessInfo.pid, runningAppProcessInfo.processName);
		}

		Collection<Integer> keys = pidMap.keySet();

		for(int key : keys)
		{
		    int pids[] = new int[1];
		    pids[0] = key;
		    android.os.Debug.MemoryInfo[] memoryInfoArray = activityManager.getProcessMemoryInfo(pids);
		    for(android.os.Debug.MemoryInfo pidMemoryInfo: memoryInfoArray)
		    {
		        process = pidMap.get(pids[0]);
				id = pids[0];
				String pid = String.valueOf(id);
				int mem1 = pidMemoryInfo.getTotalPrivateDirty();
				String memo1=String.valueOf(mem1);
				int mem2 = pidMemoryInfo.getTotalPss();
				String memo2=String.valueOf(mem2);
				int mem3 = pidMemoryInfo.getTotalSharedDirty();
				String memo3=String.valueOf(mem3);
				tableRow = new TableRow(this);
				tableRow.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
				P_ID=createCell(pid);
			 	name =createCell(process);
			 	memory1 = createCell(memo1);
			 	memory2= createCell(memo2);
			 	memory3=createCell(memo3);
			 
			 	tableRow.addView(P_ID);
			 	tableRow.addView(name);
			 	tableRow.addView(memory1);
			 	tableRow.addView(memory2);
			 	tableRow.addView(memory3);
			 
			 	
			 	tableRow.setGravity(Gravity.CENTER);
			 	table.addView(tableRow);
		    }
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
