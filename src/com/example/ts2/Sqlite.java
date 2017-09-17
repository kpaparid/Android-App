package com.example.ts2;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Sqlite extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = "StorageRecords";
	private static final String TABLE = "records";
	private static final String DATE = "date";
	private static final String INTERNAL = "internal";
	private static final String EXTERNAL = "external";

	public Sqlite(Context context) {
		super(context, DATABASE_NAME, null, 4);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String create = "CREATE TABLE " + TABLE + "( id INTEGER PRIMARY KEY AUTOINCREMENT," + DATE + " LONG," + INTERNAL + " DOUBLE," + EXTERNAL + " DOUBLE)";
		db.execSQL(create);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE);
		onCreate(db);
	}

	void addRecord(Record record) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(DATE, record.getDate());
		values.put(INTERNAL, record.getInternal());
		values.put(EXTERNAL, record.getExternal());

		db.insert(TABLE, null, values);
		db.close();
	}

	public ArrayList<Record> getRecords() {
		ArrayList<Record> recList = new ArrayList<Record>();
		Record rec;

		String selectQuery = "SELECT " + DATE + "," + INTERNAL + "," + EXTERNAL + " FROM " + TABLE;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor.moveToFirst())
			do {

				rec = new Record();

				rec.setDate(cursor.getLong(0));
				rec.setInternal(cursor.getDouble(1));
				rec.setExternal(cursor.getDouble(2));

				recList.add(rec);

			} while (cursor.moveToNext());

		return recList;
	}

	// // Getting contacts Count
	// public int getContactsCount() {
	// String countQuery = "SELECT  * FROM " + TABLE;
	// SQLiteDatabase db = this.getReadableDatabase();
	// Cursor cursor = db.rawQuery(countQuery, null);
	// cursor.close();
	//
	// // return count
	// return cursor.getCount();
	// }

}