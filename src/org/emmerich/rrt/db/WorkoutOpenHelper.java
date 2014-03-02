package org.emmerich.rrt.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class WorkoutOpenHelper extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = "rrt.db";
	private static final int DATABASE_VERSION = 2;

	private static final String WORKOUT_TABLE_NAME = "workout";
	private static final String WORKOUT_TABLE_CREATE = "CREATE TABLE workout (_id INTEGER PRIMARY KEY, name VARCHAR)";

	public WorkoutOpenHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(WORKOUT_TABLE_CREATE);
		db.execSQL("INSERT INTO workout (_id, name) VALUES (0, 'hello')");
		db.execSQL("INSERT INTO workout (_id, name) VALUES (1, 'hi')");
		db.execSQL("INSERT INTO workout (_id, name) VALUES (2, 'bonjour')");
		db.execSQL("INSERT INTO workout (_id, name) VALUES (3, 'hallo')");
		db.execSQL("INSERT INTO workout (_id, name) VALUES (4, 'guten tag')");
		db.execSQL("INSERT INTO workout (_id, name) VALUES (5, 'hej')");
		db.execSQL("INSERT INTO workout (_id, name) VALUES (6, 'ni hao')");
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}

}
