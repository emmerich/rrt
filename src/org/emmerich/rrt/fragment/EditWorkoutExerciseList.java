package org.emmerich.rrt.fragment;

import org.emmerich.rrt.R;
import org.emmerich.rrt.data.Exercise;
import org.emmerich.rrt.data.Hold;
import org.emmerich.rrt.data.Repetition;
import org.emmerich.rrt.data.Task;
import org.emmerich.rrt.data.Workout;
import org.emmerich.rrt.db.ApplicationContentProvider;
import org.emmerich.rrt.view.EditWorkoutExerciseListCursorAdapter;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class EditWorkoutExerciseList extends ListFragment implements LoaderCallbacks<Cursor> {
	
	private int workoutId;
	private EditWorkoutExerciseListCursorAdapter adapter; 

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = super.onCreateView(inflater, container, savedInstanceState);//inflater.inflate(R.layout.edit_workout_exercise_list, container, false);
		adapter = new EditWorkoutExerciseListCursorAdapter(getActivity(), null);
		
		getLoaderManager().initLoader(0, null, this);
		setListAdapter(adapter);
		
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
    	return new CursorLoader(getActivity(),
    			ApplicationContentProvider.CONTENT_URI.buildUpon()
    				.appendPath("workouts").appendPath(Integer.toString(workoutId)).appendPath("exercises").build(),
    			new String[] { Exercise.ID, Exercise.INDEX, Hold.NAME, Task.NAME, Repetition.TYPE, Repetition.COUNT },
    			null, null, null);
    }

    // Called when a previously created loader has finished loading
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        // Swap the new cursor in.  (The framework will take care of closing the
        // old cursor once we return.)
    	adapter.swapCursor(data);
    }

    // Called when a previously created loader is reset, making the data unavailable
    public void onLoaderReset(Loader<Cursor> loader) {
        // This is called when the last Cursor provided to onLoadFinished()
        // above is about to be closed.  We need to make sure we are no
        // longer using it.
    	adapter.swapCursor(null);
    }
	
	public void setWorkoutId(int workoutId) {
		this.workoutId = workoutId;
	}

}
