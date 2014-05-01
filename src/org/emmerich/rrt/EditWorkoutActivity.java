package org.emmerich.rrt;

import org.emmerich.rrt.data.Workout;
import org.emmerich.rrt.db.ApplicationContentProvider;
import org.emmerich.rrt.fragment.EditableExerciseList;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.widget.TextView;

public class EditWorkoutActivity extends FragmentActivity implements LoaderCallbacks<Cursor> {

	private int workoutId;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		workoutId = getIntent().getExtras().getInt("WORKOUT_ID");
		setContentView(R.layout.edit_workout_view);
		
		EditableExerciseList editableExerciseList = new EditableExerciseList();
		editableExerciseList.setWorkoutId(workoutId);
		
		getSupportFragmentManager().beginTransaction().add(R.id.edit_workout_container, editableExerciseList).commit();
		getSupportLoaderManager().initLoader(0, null, this);
	}
	
	// Called when a new Loader needs to be created
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        // Now create and return a CursorLoader that will take care of
        // creating a Cursor for the data being displayed.
    	return new CursorLoader(this,
    			ApplicationContentProvider.CONTENT_URI.buildUpon().appendPath("workouts").appendPath(Integer.toString(workoutId)).build(),
    			new String[] { Workout.NAME }, null, null, null);
    }

    // Called when a previously created loader has finished loading
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        // Swap the new cursor in.  (The framework will take care of closing the
        // old cursor once we return.)
    	data.moveToFirst();
    	
    	TextView workoutName = (TextView) findViewById(R.id.edit_workout_name);
    	workoutName.setText(data.getString(data.getColumnIndex(Workout.NAME)));
    }

    // Called when a previously created loader is reset, making the data unavailable
    public void onLoaderReset(Loader<Cursor> loader) {
        // This is called when the last Cursor provided to onLoadFinished()
        // above is about to be closed.  We need to make sure we are no
        // longer using it.
    }

}
