package org.emmerich.rrt.fragment;

import org.emmerich.rrt.R;
import org.emmerich.rrt.data.Exercise;
import org.emmerich.rrt.data.Hold;
import org.emmerich.rrt.data.Repetition;
import org.emmerich.rrt.data.RepetitionType;
import org.emmerich.rrt.data.Task;
import org.emmerich.rrt.db.ApplicationContentProvider;
import org.emmerich.rrt.view.RepetitionView;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

public class ExerciseList extends ListFragment implements LoaderCallbacks<Cursor> {
	
	private int workoutId;
	private ExerciseListCursorAdapter adapter; 

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = super.onCreateView(inflater, container, savedInstanceState);
		adapter = new ExerciseListCursorAdapter(getActivity(), null);
		
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
	
	private class ExerciseListCursorAdapter extends CursorAdapter {
		private LayoutInflater inflater;

		public ExerciseListCursorAdapter(Context context, Cursor cursor) {
			super(context, cursor, 0);
			inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		}

		@Override
		public void bindView(View arg0, final Context arg1, Cursor arg2) {
			final int exerciseId = arg2.getInt(arg2.getColumnIndex(Exercise.ID));
			
			TextView hold = (TextView) arg0.findViewById(R.id.exercise_hold);
			hold.setText(arg2.getString(arg2.getColumnIndex(Hold.NAME)));
			
			TextView task = (TextView) arg0.findViewById(R.id.exercise_task);
			task.setText(arg2.getString(arg2.getColumnIndex(Task.NAME)));
			
			String repetitionType = arg2.getString(arg2.getColumnIndex(Repetition.TYPE));
			RepetitionView repetition = (RepetitionView) arg0.findViewById(R.id.exercise_repetition);
			repetition.setRepetitionType(RepetitionType.getById(repetitionType));
			repetition.setText(arg2.getString(arg2.getColumnIndex(Repetition.COUNT)));
		}

		@Override
		public View newView(Context arg0, Cursor arg1, ViewGroup arg2) {
			return inflater.inflate(R.layout.exercise_list_item, arg2, false);
		}
	}

}
