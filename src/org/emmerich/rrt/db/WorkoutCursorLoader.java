package org.emmerich.rrt.db;

import org.emmerich.rrt.lib.SimpleCursorLoader;

import android.content.Context;
import android.database.Cursor;

public class WorkoutCursorLoader extends SimpleCursorLoader {

	String query;
	WorkoutOpenHelper helper;
	
	public WorkoutCursorLoader(Context context, String query) {
		super(context);
		this.query = query;
		helper = new WorkoutOpenHelper(context);
	}

	@Override
	public Cursor loadInBackground() {
		return helper.getReadableDatabase().rawQuery(query, null);
	}

}
