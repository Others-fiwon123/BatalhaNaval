package br.com.ifsp.batalhanaval.gameobjects;

public abstract class Ship {

	protected int id;
	protected int size;
	protected boolean isHorizontal;
	protected Part[] parts;
	
	int firstPositionRow, firstPositionColumn;
	
	public int getSize() {
		return size;
	}
	
	public Part[] getParts() {
		return parts;
	}
	
	public int getId() {
		return id;
	}
	
	public void setHorizontal(boolean flag) {
		isHorizontal = flag;
	}
	
	public boolean getHorizontal() {
		return isHorizontal;
	}
	
	public void setFistPositionRow(int i) {
		firstPositionRow = i;
	}
	
	public void setFistPositionColumn(int j) {
		firstPositionColumn = j;
	}
	
	public int getFistPositionRow() {
		return firstPositionRow;
	}
	
	public int getFistPositionColumn() {
		return firstPositionColumn;
	}
}
