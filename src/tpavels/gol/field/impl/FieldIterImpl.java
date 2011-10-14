package tpavels.gol.field.impl;

import tpavels.gol.constants.Constants;
import tpavels.gol.field.Cell;
import tpavels.gol.field.Field;
import tpavels.gol.field.FieldIter;



public class FieldIterImpl<E> implements FieldIter<Cell> {
	
	private Cell currentCell = null;
	private FieldImpl fieldImpl = null;
	
	private int nextX;
	private int nextY;
	
	/**
	 * Set iterator start points to {@link Constants#START_POINT}
	 * and initialize {@link Field} to work with
	 * @param fieldImpl {@link Field} object to work with
	 */
	public FieldIterImpl(FieldImpl fieldImpl) {
		this.fieldImpl = fieldImpl;
		nextX = START_POINT;
		nextY = START_POINT;
	}
	
	@Override
	public boolean hasNext() {
		boolean isBottomRight = nextY >= COLS-1 && nextX > ROWS -1;
		if (isBottomRight){
			nextX = START_POINT;
			nextY = START_POINT;
			return false;
		}
		return true;
	}

	@Override
	public boolean isEmpty() {
		return fieldImpl.getAliveCells() == 0;
	}

	@Override
	public Cell next() {
		if (hasNext()) {
			currentCell = fieldImpl.getCell(nextX, nextY);
			nextCell();
			return currentCell;
		}
			return null;
	}

	@Override
	public void remove() {
		System.err.println("iterator.remove() is not implemented");
	}
	
	@Override
	public void setDead() {
		fieldImpl.setDead(currentCell);	
	}
	
	@Override
	public void setLive() {
		fieldImpl.setLife(currentCell);
	}

	@Override
	public String toString() {
		if (currentCell == null) return "EMPTY";
		else return currentCell.toString();
	}
	
	/**
	 * Iterator helper. </br>
	 * Sets iterators next coordinates to next cell. 
	 * Goes left to right, up to down. When at the right border of field,
	 * goes down by one row and starts the row from beginning (column 0).
	 * Must be used together with {@link #hasNext()}, this method
	 * doesn't check rows number. 
	 */
	private void nextCell() {
		if (nextY != COLS-1) {
			nextY++;
		} else {
			nextX++;
			nextY = START_POINT;
		}
	}
	
	

}
