package com.example.ts2;
import android.app.ActivityGroup;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TabHost;
import android.widget.TextView;

@SuppressWarnings("deprecation")
public class MainActivity extends ActivityGroup {
  @Override
  public void onCreate(Bundle savedInstanceState) {
	  super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    Intent intent;
    TabHost tabs=(TabHost)findViewById(R.id.tabhost);
   
   
    tabs.setup(getLocalActivityManager());
    intent =new Intent(this,Tab1.class);
    TabHost.TabSpec spec=tabs.newTabSpec("tag1");
    spec.setContent(intent);
    spec.setIndicator("SQL");
    tabs.addTab(spec);
   
    

    intent =new Intent(this,Tab2.class);
    spec=tabs.newTabSpec("tag2");
    spec.setContent(intent);
    spec.setIndicator("Processes");
    
    
    
    tabs.addTab(spec);
    

    intent =new Intent(this,Tab3.class);
    spec=tabs.newTabSpec("tag3");
    spec.setContent(intent);
    spec.setIndicator("Device Info");
    tabs.addTab(spec);
    tabs.setBackgroundColor(Color.BLACK);
    for (int tabIndex = 0 ; tabIndex < tabs.getTabWidget().getTabCount() ; tabIndex ++) {
        View tab = tabs.getTabWidget().getChildTabViewAt(tabIndex);
        TextView t = (TextView)tab.findViewById(android.R.id.title);
        t.setTextColor(Color.CYAN);
    }
  }
}