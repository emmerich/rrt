package org.emmerich.rrt.view;

import org.emmerich.rrt.R;
import org.emmerich.rrt.model.Workout;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class WorkoutListCursorAdapter extends CursorAdapter {
	
	private LayoutInflater inflater;

	public WorkoutListCursorAdapter(Context context, Cursor cursor) {
		super(context, cursor, 0);
		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public void bindView(View arg0, Context arg1, Cursor arg2) {
		TextView content = (TextView) arg0.findViewById(R.id.workout_name);
		content.setText(arg2.getString(arg2.getColumnIndex(Workout.DATA.NAME)));
		
		ImageButton button = (ImageButton) arg0.findViewById(R.id.workout_list_settings);
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				System.out.println("View clicked: " + v.toString());
				
			}
		});
	}

	@Override
	public View newView(Context arg0, Cursor arg1, ViewGroup arg2) {
		return inflater.inflate(R.layout.workout_list_item, arg2, false);
	}

}
