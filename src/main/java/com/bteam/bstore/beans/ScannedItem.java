package com.bteam.bstore.beans;

public class ScannedItem implements Comparable<ScannedItem> {
	
	private final double confidence;
	private final String name;

	public ScannedItem(double confidence2, String name) {
	        this.confidence = confidence2;
	        this.name = name;
	    }

	public double getConfidence() {
		return this.confidence;
	}

	public String getName() {
		return this.name;
	}
	
	/**
	 * To sort the items in descending order based on confidence of item
	 */
	public int compareTo(ScannedItem item){  
		if(this.confidence==item.confidence)  
		return 0;  
		else if(this.confidence<item.confidence)  
		return 1;  
		else  
		return -1;  
		}  
}
