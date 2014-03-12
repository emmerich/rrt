package org.emmerich.rrt.model;

/**
 * An exercise is a given task, on a given hold, for a number of repetitions (either time-based, count-based or to failure).
 *
 */
public class Exercise implements WorkoutEntry {
	private Task task;
	private Hold hold;
	private Repetition repetition;
	
	public Exercise(Task task, Hold hold, Repetition repetition) {
		super();
		this.task = task;
		this.hold = hold;
		this.repetition = repetition;
	}
}
