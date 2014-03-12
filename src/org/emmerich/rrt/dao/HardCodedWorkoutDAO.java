package org.emmerich.rrt.dao;

import java.util.ArrayList;
import java.util.List;

import org.emmerich.rrt.model.Exercise;
import org.emmerich.rrt.model.Hold;
import org.emmerich.rrt.model.Rest;
import org.emmerich.rrt.model.Task;
import org.emmerich.rrt.model.Workout;
import org.emmerich.rrt.model.WorkoutEntry;
import org.emmerich.rrt.model.repetition.CountBased;
import org.emmerich.rrt.model.repetition.TimeBased;

public class HardCodedWorkoutDAO implements WorkoutDAO {

	@Override
	public List<Workout> getWorkouts() {
		List<Workout> workouts = new ArrayList<Workout>();
		
		List<WorkoutEntry> exercises = new ArrayList<WorkoutEntry>();
		exercises.add(new Exercise(new Task("Pull ups"), new Hold("Jug"), new CountBased(10)));
		exercises.add(new Rest(new TimeBased(60)));
		exercises.add(new Exercise(new Task("Pull ups"), new Hold("2 fingers"), new CountBased(7)));
		
		Workout workout = new Workout("Test", exercises);
		workouts.add(workout);
		return workouts;
	}

}
