package org.emmerich.rrt.model;

import java.util.List;

/**
 * A Workout is an ordered list of WorkoutEntries. A WorkoutEntry is either an Exercise or a Rest.
 */
public class Workout {
	private String name;
	private List<WorkoutEntry> exercises;
	
	public Workout(String name, List<WorkoutEntry> exercises) {
		super();
		this.name = name;
		this.exercises = exercises;
	}

	public String getName() {
		return name;
	}
}
