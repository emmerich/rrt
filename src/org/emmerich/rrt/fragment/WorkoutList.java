package org.emmerich.rrt.fragment;

import org.emmerich.rrt.data.Workout;
import org.emmerich.rrt.view.WorkoutListCursorAdapter;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
    			Uri.parse("content://org.emmerich.rrt"),
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
	
}
