package org.emmerich.rrt.db;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

public class ApplicationContentProvider extends ContentProvider {
	
	private ApplicationDatabaseHelper helper;
	
	private static final int EXERCISE_ID = 1;
	
	private static final String AUTHORITY = "org.emmerich.rrt";
	private static final String EXERCISE = "exercise";
	
	public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY);
	
	private static final UriMatcher sURIMatcher = new UriMatcher(UriMatcher.NO_MATCH);
	static {
		sURIMatcher.addURI(AUTHORITY, EXERCISE, EXERCISE_ID);
	}
	
	@Override
	public boolean onCreate() {
		helper = new ApplicationDatabaseHelper(getContext());
		return false;
	}

	@Override
	public Cursor query(Uri arg0, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
		SQLiteDatabase db = helper.getReadableDatabase();
		SQLiteQueryBuilder builder = new SQLiteQueryBuilder();
		
		int uriType = sURIMatcher.match(arg0);
		
		switch(uriType)
		{
			case EXERCISE_ID:
				builder.setTables("exercise");
				// SELECT E.id AND H.name AND T.name AND R.type AND R.count 
				// FROM exercise AS E JOIN hold AS H JOIN task AS T JOIN repetition AS R
				// WHERE E.exerciseId = id
				
				// JOIN 
//				builder.q
				//foo LEFT OUTER JOIN bar ON (foo.id = bar.foo_id)
				return null;
			default:
				builder.setTables("workout");
				return builder.query(db, projection, selection, selectionArgs, null, null, sortOrder);
		}
		
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
