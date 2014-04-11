package org.emmerich.rrt.view;

import org.emmerich.rrt.EditWorkoutActivity;
import org.emmerich.rrt.R;
import org.emmerich.rrt.data.Workout;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

public class WorkoutListCursorAdapter extends CursorAdapter {
	
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
