package org.emmerich.rrt.model.repetition;

import org.emmerich.rrt.model.Repetition;

public class TimeBased implements Repetition {
	private int seconds;

	public TimeBased(int seconds) {
		super();
		this.seconds = seconds;
	}
}
