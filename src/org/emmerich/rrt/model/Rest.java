package org.emmerich.rrt.model;

import org.emmerich.rrt.model.repetition.TimeBased;

public class Rest implements WorkoutEntry {
	private TimeBased duration;

	public Rest(TimeBased duration) {
		super();
		this.duration = duration;
	}
}
