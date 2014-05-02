package org.emmerich.rrt;

import org.emmerich.rrt.data.Workout;
import org.emmerich.rrt.db.ApplicationContentProvider;
import org.emmerich.rrt.fragment.ExerciseList;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.View;
import android.widget.TextView;

public class DoExerciseActivity extends FragmentActivity implements LoaderCallbacks<Cursor> {
	
	private int exerciseId;
	private int nextExerciseId;
	
	@Override
	public void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		exerciseId = getIntent().getExtras().getInt("EXERCISE_ID");
		nextExerciseId = getIntent().getExtras().getInt("NEXT_EXERCISE_ID");
		
		
		
		setContentView(R.layout.do_workout_view);
		
		ExerciseList exerciseList = new ExerciseList();
		exerciseList.setWorkoutId(workoutId);
		
		getSupportFragmentManager().beginTransaction().replace(R.id.list_fragment, exerciseList).commit();
		getSupportLoaderManager().initLoader(0, null, this);
	}
	
	public void handleBeginClick(View view) {
		
	}
	
	@Override
	public Loader<Cursor> onCreateLoader(int arg0, Bundle arg1) {
		return new CursorLoader(this,
    			ApplicationContentProvider.CONTENT_URI.buildUpon().appendPath("workouts").appendPath(Integer.toString(workoutId)).build(),
    			new String[] { Workout.NAME }, null, null, null);
	}

	@Override
	public void onLoadFinished(Loader<Cursor> arg0, Cursor arg1) {
		arg1.moveToFirst();
    	
    	TextView workoutName = (TextView) findViewById(R.id.do_workout_name);
    	workoutName.setText(arg1.getString(arg1.getColumnIndex(Workout.NAME)));
	}

	@Override
	public void onLoaderReset(Loader<Cursor> arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
