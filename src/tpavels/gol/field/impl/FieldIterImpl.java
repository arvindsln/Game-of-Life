package tpavels.gol.field.impl;

import tpavels.gol.constants.Constants;
import tpavels.gol.field.Cell;
import tpavels.gol.field.Field;
import tpavels.gol.field.FieldIter;



public class FieldIterImpl<E> implements FieldIter<Cell> {

	private Cell currentCell = null;
	private FieldImpl fieldImpl = null;

	private int nextRow;
	private int nextColumn;

	/**
	 * Set iterator start points to {@link Constants#START_POINT}
	 * and initialize {@link Field} to work with
	 * @param fieldImpl {@link Field} object to work with
	 */
	public FieldIterImpl(FieldImpl fieldImpl) {
		this.fieldImpl = fieldImpl;
		nextRow = START_POINT;
		nextColumn = START_POINT;
	}

	@Override
	public boolean hasNext() {
		boolean isBottomRight = ((nextColumn >= COLS-1) && (nextRow >= ROWS) || (nextRow >= ROWS));
		if (isBottomRight){
			nextRow = START_POINT;
			nextColumn = START_POINT;
			return false;
		}
		return true;
	}

	@Override
	public boolean isEmpty() {
		return fieldImpl.getNumberOfAliveCells() == 0;
	}

	@Override
	public Cell next() {
		if (hasNext()) {
			currentCell = fieldImpl.getCell(nextRow, nextColumn);
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
	public void setLife() {
		fieldImpl.setLife(currentCell);
	}

	@Override
	public String toString() {
		if (currentCell == null) {
			return "EMPTY";
		} else {
			return currentCell.toString();
		}
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
		if (nextColumn != COLS-1) {
			nextColumn++;
		} else {
			nextRow++;
			nextColumn = START_POINT;
		}
	}



}
