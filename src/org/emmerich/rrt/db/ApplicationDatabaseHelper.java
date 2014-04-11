package org.emmerich.rrt.db;

import org.emmerich.rrt.data.Exercise;
import org.emmerich.rrt.data.Hold;
import org.emmerich.rrt.data.Repetition;
import org.emmerich.rrt.data.Task;
import org.emmerich.rrt.data.Workout;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ApplicationDatabaseHelper extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = "rrt.db";
	private static final int DATABASE_VERSION = 2;

	private static final String WORKOUT_TABLE_NAME = "workout";
	private static final String EXERCISE_TABLE_NAME = "exercise";
//	private static final String REST_TABLE_NAME = "rest";
	private static final String HOLD_TABLE_NAME = "hold";
	private static final String TASK_TABLE_NAME = "task";
	private static final String REPETITION_TABLE_NAME = "repetition";
	
	private static final String WORKOUT_TABLE_CREATE = "CREATE TABLE " + WORKOUT_TABLE_NAME + " (" + Workout.ID + " INTEGER PRIMARY KEY, " + Workout.NAME + " VARCHAR)"; 
	private static final String EXERCISE_TABLE_CREATE = "CREATE TABLE " + EXERCISE_TABLE_NAME + " (" +
			Exercise.ID + " INTEGER PRIMARY KEY, "+
			Exercise.INDEX + " INTEGER, " +
			Exercise.WORKOUT_ID + " INTEGER, " +
			Exercise.HOLD_ID + " INTEGER, " +
			Exercise.TASK_ID + " INTEGER, " + 
			Exercise.REPETITION_ID + " INTEGER, " +
			// Foreign keys
			"FOREIGN KEY(" + Exercise.WORKOUT_ID + ") REFERENCES " + WORKOUT_TABLE_NAME + " (" + Workout.ID + "), " +
			"FOREIGN KEY(" + Exercise.HOLD_ID + ") REFERENCES " + HOLD_TABLE_NAME + "(" + Hold.ID + "), " + 
			"FOREIGN KEY(" + Exercise.TASK_ID + ") REFERENCES " + TASK_TABLE_NAME + "(" + Hold.ID + "), " + 
			"FOREIGN KEY(" + Exercise.REPETITION_ID + ") REFERENCES " + REPETITION_TABLE_NAME + "(" + Repetition.ID + "))";
	
//	private static final String REST_TABLE_CREATE = "CREATE TABLE " + REST_TABLE_NAME + " (" +
//			Rest.ID + " INTEGER PRIMARY KEY, entry_index INTEGER, duration INTEGER, workout_id INTEGER, " +
//			"FOREIGN KEY(workout_id) REFERENCES " + WORKOUT_TABLE_NAME + " (_id))"; 
	private static final String HOLD_TABLE_CREATE = "CREATE TABLE " + HOLD_TABLE_NAME + " (" + 
			Hold.ID + " INTEGER PRIMARY KEY, " +
			Hold.NAME + " VARCHAR)";
	
	private static final String TASK_TABLE_CREATE = "CREATE TABLE " + TASK_TABLE_NAME + " (" +
			Task.ID + " INTEGER PRIMARY KEY, " + 
			Task.NAME + " VARCHAR)"; 
	private static final String REPETITION_TABLE_CREATE = "CREATE TABLE " + REPETITION_TABLE_NAME + " (" +
			Repetition.ID + " INTEGER PRIMARY KEY, " +
			Repetition.TYPE + " VARCHAR, " +
			Repetition.COUNT + " INTEGER)"; 

	public ApplicationDatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(WORKOUT_TABLE_CREATE);
		db.execSQL(EXERCISE_TABLE_CREATE);
//		db.execSQL(REST_TABLE_CREATE);
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
		insertMockHolds(db);
		insertMockTasks(db);
		insertMockRepetitions(db);
		insertMockWorkouts(db);
		
		insertMockExercises(db);
		insertMockRests(db);
	}

	private void insertMockRests(SQLiteDatabase db) {
//		db.execSQL("INSERT INTO rest (_id, entry_index, duration, workout_id) VALUES (0, 1, 60, 0)");
	}

	private void insertMockExercises(SQLiteDatabase db) {
		db.execSQL("INSERT INTO exercise (" + Exercise.ID + "," + Exercise.INDEX + "," + Exercise.WORKOUT_ID + "," + Exercise.HOLD_ID + "," + Exercise.TASK_ID + "," + Exercise.REPETITION_ID + ") VALUES (0, 0, 0, 1, 3, 0)");
	}

	private void insertMockWorkouts(SQLiteDatabase db) {
		db.execSQL("INSERT INTO workout (" + Workout.ID + ", " + Workout.NAME + ") VALUES (0, 'Test Workout')");
		db.execSQL("INSERT INTO workout (" + Workout.ID + ", " + Workout.NAME + ") VALUES (1, 'Empty Workout')");
	}

	private void insertMockRepetitions(SQLiteDatabase db) {
		db.execSQL("INSERT INTO repetition (" + Repetition.ID + ", " + Repetition.TYPE + ", " + Repetition.COUNT + ") VALUES (0, 'count', 10)");
		db.execSQL("INSERT INTO repetition (" + Repetition.ID + ", " + Repetition.TYPE + ", " + Repetition.COUNT + ") VALUES (1, 'time', 60)");
	}

	private void insertMockTasks(SQLiteDatabase db) {
		db.execSQL("INSERT INTO task (" + Task.ID + ", " + Task.NAME + ") VALUES (0, 'Dead Hang')");
		db.execSQL("INSERT INTO task (" + Task.ID + ", " + Task.NAME + ") VALUES (1, 'Bent Arm Hang')");
		db.execSQL("INSERT INTO task (" + Task.ID + ", " + Task.NAME + ") VALUES (2, 'Offset Hang')");
		db.execSQL("INSERT INTO task (" + Task.ID + ", " + Task.NAME + ") VALUES (3, 'Pull-up')");
		db.execSQL("INSERT INTO task (" + Task.ID + ", " + Task.NAME + ") VALUES (4, 'Offset pull-up')");
		db.execSQL("INSERT INTO task (" + Task.ID + ", " + Task.NAME + ") VALUES (5, 'One-arm pull-up')");
		db.execSQL("INSERT INTO task (" + Task.ID + ", " + Task.NAME + ") VALUES (6, 'L-Hang')");
		db.execSQL("INSERT INTO task (" + Task.ID + ", " + Task.NAME + ") VALUES (7, 'Front Lever')");
	}

	private void insertMockHolds(SQLiteDatabase db) {
		db.execSQL("INSERT INTO hold (" + Hold.ID + ", " + Hold.NAME + ") VALUES (0, 'Jug')");
		db.execSQL("INSERT INTO hold (" + Hold.ID + ", " + Hold.NAME + ") VALUES (1, '4-finger Edge')");
		db.execSQL("INSERT INTO hold (" + Hold.ID + ", " + Hold.NAME + ") VALUES (2, '3-finger Pocket')");
		db.execSQL("INSERT INTO hold (" + Hold.ID + ", " + Hold.NAME + ") VALUES (3, '2-finger Pocket')");
	}
	

}