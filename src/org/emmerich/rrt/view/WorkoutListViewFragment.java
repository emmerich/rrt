package org.emmerich.rrt.view;

import org.emmerich.rrt.R;
import org.emmerich.rrt.db.WorkoutCursorLoader;
import org.emmerich.rrt.db.WorkoutOpenHelper;
import org.emmerich.rrt.model.Workout;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.Loader;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

public class WorkoutListViewFragment extends ListFragment implements LoaderCallbacks<Cursor> {
	
	// This is the Adapter being used to display the list's data
    private WorkoutListCursorAdapter mAdapter;
    SQLiteDatabase db;
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
    		Bundle savedInstanceState) {
    	View view = super.onCreateView(inflater, container, savedInstanceState);
    	
    	db = new WorkoutOpenHelper(getActivity()).getReadableDatabase();

        // For the cursor adapter, specify which columns go into which views
        String[] fromColumns = {Workout.DATA.NAME};
        int[] toViews = {R.id.workout_name}; // The TextView in simple_list_item_1

        // Create an empty adapter we will use to display the loaded data.
        // We pass null for the cursor, then update it in onLoadFinished()
        mAdapter = new WorkoutListCursorAdapter(getActivity(), null);
//                R.layout.workout_list_item, null,
//                fromColumns, toViews, 0);
        
        getLoaderManager().initLoader(0, null, this);
        
//        mAdapter.setViewBinder(new SimpleCursorAdapter.ViewBinder() {
//			
//			@Override
//			public boolean setViewValue(View arg0, Cursor arg1, int arg2) {
//				System.out.println("setViewValue index: " + arg2 + " position: " + arg1.getPosition() + " view: " + arg0.toString());
//				return false;
////				Button button = (Button) arg0.findViewById(R.id.workout_list_settings);
//				
////				button.setOnClickListener(new View.OnClickListener() {
////					
////					@Override
////					public void onClick(View v) {
////						System.out.println("Click!");
////						
////					}
////				});
//			}
//			
//		});
        
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
        return new WorkoutCursorLoader(getActivity(), "SELECT * FROM workout");
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
