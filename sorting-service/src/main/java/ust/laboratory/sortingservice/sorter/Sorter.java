package ust.laboratory.sortingservice.sorter;

public abstract class Sorter {

	private String name;
	private int type;
	
	protected static final int MERGESORT=1;
	protected static final int OTHER=99;
	
	public Sorter(String name, int type) {
		this.name=name;
		this.type=type;
	}
	
	public abstract int[] sort(int[] input);

	protected String getName() {
		return name;
	}

	protected void setName(String name) {
		this.name = name;
	}

	protected int getType() {
		return type;
	}

	protected void setType(int type) {
		this.type = type;
	}
		
}
