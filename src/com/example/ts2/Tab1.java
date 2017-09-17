package com.example.ts2;

import java.io.File;
import java.sql.Date;
import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class Tab1 extends Activity {

	private Sqlite sqlite;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tab1);

		sqlite = new Sqlite(this);

		findViewById(R.id.scroll).setBackgroundColor(Color.BLACK);
		findViewById(R.id.app).setBackgroundColor(Color.BLACK);
		showRecords();

	}

	@TargetApi(Build.VERSION_CODES.GINGERBREAD)
	@SuppressLint("NewApi")
	private void addDailyRecord() {

		double freeBytesInternal = new File(getFilesDir().getAbsoluteFile().toString()).getFreeSpace() / 1024;
		File externalStorageDir = Environment.getExternalStorageDirectory();
		double freeBytesExternal = externalStorageDir.getFreeSpace() / 1024;

		Record rec = new Record();
		rec.setDate(System.currentTimeMillis());
		rec.setInternal(freeBytesInternal);
		rec.setExternal(freeBytesExternal);

		sqlite.addRecord(rec);

	}

	private void showRecords() {

		TableLayout table = (TableLayout) findViewById(R.id.table6);
		TableRow tableRow;
		TextView date;
		TextView internal;
		TextView external;

		ArrayList<Record> records = sqlite.getRecords();

		if (records.isEmpty()) {
			addDailyRecord();
			records = sqlite.getRecords();

		}

		else {
			Record lastRecord = records.get(records.size() - 1);
			if (System.currentTimeMillis() - lastRecord.getDate() > 60 * 60 * 1000) {
				addDailyRecord();
				records = sqlite.getRecords();
			}
		}
		table.setBackgroundColor(Color.BLACK);

		tableRow = new TableRow(this);
		tableRow.addView(createCell("    DATE", true));
		tableRow.addView(createCell("      INTERNAL", true));
		tableRow.addView(createCell("     SD CARD", false));
		table.addView(tableRow);

		for (Record rec : records) {

			Log.d("Sql", "Read record!");
			tableRow = new TableRow(this);
			tableRow.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.MATCH_PARENT));

			date = createCell(String.valueOf(new Date(rec.getDate()).toString()), true);

			internal = createCell(String.valueOf(rec.getInternal()), true);

			external = createCell(String.valueOf(rec.getExternal()), false);

			tableRow.addView(date);
			// tableRow.addView(createVerticalLine(20));
			tableRow.addView(internal);
			// tableRow.addView(createVerticalLine(20));
			tableRow.addView(external);
			tableRow.setGravity(Gravity.CENTER);
			table.addView(tableRow);
			// table.addView(createHorizontalLine(table.getWidth()));

		}
	}

	private TextView createCell(String text, boolean rightMargin) {

		TextView view = new TextView(this);
		view.setText(String.valueOf(text));
		view.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
		view.setTextColor(Color.CYAN);
		if (rightMargin)
			view.setPadding(2, 2, 30, 3);
		else
			view.setPadding(2, 2, 3, 3);

		view.setGravity(Gravity.CENTER);

		return view;

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

    
    

}
