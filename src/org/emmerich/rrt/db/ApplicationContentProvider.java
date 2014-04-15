package org.emmerich.rrt.db;

import org.emmerich.rrt.data.Workout;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

public class ApplicationContentProvider extends ContentProvider {
	
	private ApplicationDatabaseHelper helper;
	
	private static final int WORKOUTS_MATCH = 1;
	private static final int WORKOUT_BY_ID_MATCH = 2;
	private static final int EXERCISES_BY_WORKOUT_MATCH = 3;
	
	private static final String AUTHORITY = "org.emmerich.rrt";	
	public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY);
	
	private static final UriMatcher sURIMatcher = new UriMatcher(UriMatcher.NO_MATCH);
	static {
		sURIMatcher.addURI(AUTHORITY, "workouts", WORKOUTS_MATCH);
		sURIMatcher.addURI(AUTHORITY, "workouts/#", WORKOUT_BY_ID_MATCH);
		sURIMatcher.addURI(AUTHORITY, "workouts/#/exercises", EXERCISES_BY_WORKOUT_MATCH);
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
			case WORKOUTS_MATCH:
				builder.setTables("workout");
				return builder.query(db, projection, selection, selectionArgs, null, null, sortOrder);
			case WORKOUT_BY_ID_MATCH:
				builder.setTables("workout");
				String workoutId = arg0.getLastPathSegment();
				return builder.query(db, projection, Workout.ID + " = " + workoutId, selectionArgs, null, null, sortOrder);
			case EXERCISES_BY_WORKOUT_MATCH:
				System.out.println(arg0.getPathSegments());
				String query = 
						"SELECT exercise._id, exercise.exercise_index, hold.hold_name, task.task_name, repetition.repetition_type, repetition.repetition_count " +
						"FROM exercise JOIN hold JOIN task JOIN repetition " +
						"WHERE exercise.workout_id = 0";
				return db.rawQuery(query, null);
			default:
				return null;
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
