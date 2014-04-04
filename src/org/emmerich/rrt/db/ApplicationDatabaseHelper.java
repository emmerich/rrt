package org.emmerich.rrt.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class ApplicationDatabaseHelper extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = "rrt.db";
	private static final int DATABASE_VERSION = 2;

	private static final String WORKOUT_TABLE_NAME = "workout";
	private static final String EXERCISE_TABLE_NAME = "exercise";
	private static final String REST_TABLE_NAME = "rest";
	private static final String HOLD_TABLE_NAME = "hold";
	private static final String TASK_TABLE_NAME = "task";
	private static final String REPETITION_TABLE_NAME = "repetition";
	
	private static final String WORKOUT_TABLE_CREATE = "CREATE TABLE " + WORKOUT_TABLE_NAME + " (_id INTEGER PRIMARY KEY, name VARCHAR)"; 
	private static final String EXERCISE_TABLE_CREATE = "CREATE TABLE " + EXERCISE_TABLE_NAME + " (_id INTEGER PRIMARY KEY, entry_index INTEGER, workout_id INTEGER, hold_id INTEGER, task_id INTEGER, repetition_id INTEGER, " +
			"FOREIGN KEY(workout_id) REFERENCES " + WORKOUT_TABLE_NAME + " (_id), " +
			"FOREIGN KEY(hold_id) REFERENCES " + HOLD_TABLE_NAME + "(_id), " + 
			"FOREIGN KEY(task_id) REFERENCES " + TASK_TABLE_NAME + "(_id), " + 
			"FOREIGN KEY(repetition_id) REFERENCES " + REPETITION_TABLE_NAME + "(_id))"; 
	private static final String REST_TABLE_CREATE = "CREATE TABLE " + REST_TABLE_NAME + " (_id INTEGER PRIMARY KEY, entry_index INTEGER, duration INTEGER, workout_id INTEGER, " +
			"FOREIGN KEY(workout_id) REFERENCES " + WORKOUT_TABLE_NAME + " (_id))"; 
	private static final String HOLD_TABLE_CREATE = "CREATE TABLE " + HOLD_TABLE_NAME + " (_id INTEGER PRIMARY KEY, name VARCHAR)"; 
	private static final String TASK_TABLE_CREATE = "CREATE TABLE " + TASK_TABLE_NAME + " (_id INTEGER PRIMARY KEY, name VARCHAR)"; 
	private static final String REPETITION_TABLE_CREATE = "CREATE TABLE " + REPETITION_TABLE_NAME + " (_id INTEGER PRIMARY KEY, type VARCHAR, count INTEGER)"; 

	public ApplicationDatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(WORKOUT_TABLE_CREATE);
		db.execSQL(EXERCISE_TABLE_CREATE);
		db.execSQL(REST_TABLE_CREATE);
		db.execSQL(HOLD_TABLE_CREATE);
		db.execSQL(TASK_TABLE_CREATE);
		db.execSQL(REPETITION_TABLE_CREATE);
		
		insertMocks(db);
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}
	
	private void insertMocks(SQLiteDatabase db)
	{
		
	}
	

}
