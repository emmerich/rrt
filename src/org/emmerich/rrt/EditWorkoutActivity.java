package org.emmerich.rrt;

import org.emmerich.rrt.fragment.EditWorkout;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class EditWorkoutActivity extends FragmentActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit_workout_view);
		
		int workoutId = getIntent().getExtras().getInt("WORKOUT_ID");
		EditWorkout editWorkout = new EditWorkout();
		editWorkout.setWorkoutId(workoutId);
		
		getSupportFragmentManager().beginTransaction()
			.add(R.id.edit_workout_container, editWorkout).commit();
	}

}
