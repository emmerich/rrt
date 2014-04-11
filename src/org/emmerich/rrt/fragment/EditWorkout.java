package org.emmerich.rrt.fragment;

import org.emmerich.rrt.R;
import org.emmerich.rrt.data.Workout;
import org.emmerich.rrt.db.WorkoutCursorLoader;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class EditWorkout extends Fragment implements LoaderCallbacks<Cursor> {
	
	private int workoutId;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.edit_workout_fragment, container, false);
		getLoaderManager().initLoader(0, null, this);
		return view;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	// Called when a new Loader needs to be created
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        // Now create and return a CursorLoader that will take care of
        // creating a Cursor for the data being displayed.
        return new WorkoutCursorLoader(getActivity(), "SELECT * FROM workout WHERE _id = " + workoutId);
    }

    // Called when a previously created loader has finished loading
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        // Swap the new cursor in.  (The framework will take care of closing the
        // old cursor once we return.)
    	data.moveToFirst();
    	TextView name = (TextView) getView().findViewById(R.id.edit_workout_name);
    	int nameIndex = data.getColumnIndex(Workout.NAME);
    	name.setText(data.getString(nameIndex));
    }

    // Called when a previously created loader is reset, making the data unavailable
    public void onLoaderReset(Loader<Cursor> loader) {
        // This is called when the last Cursor provided to onLoadFinished()
        // above is about to be closed.  We need to make sure we are no
        // longer using it.
    }
	
	public void setWorkoutId(int workoutId) {
		this.workoutId = workoutId;
	}

}
