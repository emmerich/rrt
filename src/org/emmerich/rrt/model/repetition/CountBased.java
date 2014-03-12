package org.emmerich.rrt.model.repetition;

import org.emmerich.rrt.model.Repetition;

public class CountBased implements Repetition {
	private int count;

	public CountBased(int count) {
		super();
		this.count = count;
	}
}
