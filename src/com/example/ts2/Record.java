package com.example.ts2;


public class Record {

	private long date;
	private double internal;
	private double external;

	public long getDate() {
		return date;
	}

	public void setDate(long dateString) {
		this.date = dateString;
	}

	public double getInternal() {
		return internal;
	}

	public void setInternal(double internal) {
		this.internal = internal;
	}

	public double getExternal() {
		return external;
	}

	public void setExternal(double external) {
		this.external = external;
	}

}
