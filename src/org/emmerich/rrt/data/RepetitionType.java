package org.emmerich.rrt.data;

public enum RepetitionType {

	COUNT("count"),
	TIME("time"),
	TO_FAIL("to_fail");
	
	private String id;
	
	private RepetitionType(String id) {
		this.id = id;
	}
	
	public String getId() {
		return this.id;
	}
	
	public static RepetitionType getById(String id) {
		for(RepetitionType type : RepetitionType.values()) {
			if(type.getId().equals(id)) {
				return type;
			}
		}
		
		return null;
	}
	
}
