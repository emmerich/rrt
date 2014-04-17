package org.emmerich.rrt.view;

import org.emmerich.rrt.EditWorkoutActivity;
import org.emmerich.rrt.R;
import org.emmerich.rrt.data.Exercise;
import org.emmerich.rrt.data.Hold;
import org.emmerich.rrt.data.Repetition;
import org.emmerich.rrt.data.RepetitionType;
import org.emmerich.rrt.data.Task;
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

public class EditWorkoutExerciseListCursorAdapter extends CursorAdapter {
	
	private LayoutInflater inflater;

	public EditWorkoutExerciseListCursorAdapter(Context context, Cursor cursor) {
		super(context, cursor, 0);
		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public void bindView(View arg0, final Context arg1, Cursor arg2) {
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
		return inflater.inflate(R.layout.edit_workout_exercise_list_item, arg2, false);
	}

}
