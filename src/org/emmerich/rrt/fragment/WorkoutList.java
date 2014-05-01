package org.emmerich.rrt.fragment;

import org.emmerich.rrt.EditWorkoutActivity;
import org.emmerich.rrt.R;
import org.emmerich.rrt.data.Workout;
import org.emmerich.rrt.db.ApplicationContentProvider;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
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

public class WorkoutList extends ListFragment implements LoaderCallbacks<Cursor> {
	
	// This is the Adapter being used to display the list's data
    private WorkoutListCursorAdapter mAdapter;
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
    		Bundle savedInstanceState) {
    	View view = super.onCreateView(inflater, container, savedInstanceState);
    	mAdapter = new WorkoutListCursorAdapter(getActivity(), null);
        getLoaderManager().initLoader(0, null, this);
        setListAdapter(mAdapter);
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
    			ApplicationContentProvider.CONTENT_URI.buildUpon().appendPath("workouts").build(),
    			new String[] { Workout.ID, Workout.NAME },
    			null, null, null);
    }

    // Called when a previously created loader has finished loading
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        // Swap the new cursor in.  (The framework will take care of closing the
        // old cursor once we return.)
        mAdapter.swapCursor(data);
    }

    // Called when a previously created loader is reset, making the data unavailable
    public void onLoaderReset(Loader<Cursor> loader) {
        // This is called when the last Cursor provided to onLoadFinished()
        // above is about to be closed.  We need to make sure we are no
        // longer using it.
        mAdapter.swapCursor(null);
    }
    
    private class WorkoutListCursorAdapter extends CursorAdapter {
    	
    	private LayoutInflater inflater;

    	public WorkoutListCursorAdapter(Context context, Cursor cursor) {
    		super(context, cursor, 0);
    		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    	}

    	@Override
    	public void bindView(View arg0, final Context arg1, Cursor arg2) {
    		TextView content = (TextView) arg0.findViewById(R.id.workout_name);
    		content.setText(arg2.getString(arg2.getColumnIndex(Workout.NAME)));
    		
    		final int workout_id = arg2.getInt(arg2.getColumnIndex(Workout.ID));
    		
    		ImageButton button = (ImageButton) arg0.findViewById(R.id.workout_list_settings);
    		button.setOnClickListener(new OnClickListener() {
    			
    			@Override
    			public void onClick(View v) {
    				Intent intent = new Intent(arg1, EditWorkoutActivity.class);
    				intent.putExtra("WORKOUT_ID", workout_id);
    				arg1.startActivity(intent);
    			}
    		});
    	}

    	@Override
    	public View newView(Context arg0, Cursor arg1, ViewGroup arg2) {
    		return inflater.inflate(R.layout.workout_list_item, arg2, false);
    	}

    }
	
}
