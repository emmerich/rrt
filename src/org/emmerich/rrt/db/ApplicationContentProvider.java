package org.emmerich.rrt.db;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

public class ApplicationContentProvider extends ContentProvider {
	
	private ApplicationDatabaseHelper helper;
	
	@Override
	public boolean onCreate() {
		helper = new ApplicationDatabaseHelper(getContext());
		return false;
	}

	@Override
	public Cursor query(Uri arg0, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
		SQLiteDatabase db = helper.getReadableDatabase();
		SQLiteQueryBuilder builder = new SQLiteQueryBuilder();
		builder.setTables("workout");
		
		return builder.query(db, projection, selection, selectionArgs, null, null, sortOrder);
	}
	
	@Override
	public int delete(Uri arg0, String arg1, String[] arg2) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getType(Uri arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Uri insert(Uri arg0, ContentValues arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	

	@Override
	public int update(Uri arg0, ContentValues arg1, String arg2, String[] arg3) {
		// TODO Auto-generated method stub
		return 0;
	}

}
