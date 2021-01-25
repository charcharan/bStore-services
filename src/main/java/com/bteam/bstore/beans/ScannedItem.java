package com.bteam.bstore.beans;

public class ScannedItem {
	private final long id;
	private final String name;

	public ScannedItem(Long id, String name) {
	        this.id = id;
	        this.name = name;
	    }

	public long getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}
}
