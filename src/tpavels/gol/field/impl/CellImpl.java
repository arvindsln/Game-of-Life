package tpavels.gol.field.impl;

import java.util.HashSet;
import java.util.Set;

import tpavels.gol.constants.Constants;
import tpavels.gol.field.Cell;
import tpavels.gol.field.Field;

public class CellImpl implements Constants, Cell{

	private CellState state;
	private final int row;
	private final int column;
	private int neighbours;
	
	/**
	 * Initialize new Cell
	 * @param row cell ROW on the {@link Field}
	 * @param column cell COLUMN on the {@link Field} 
	 */
	public CellImpl(final int row, final int column){
		this.state = CellState.DEAD;
		this.neighbours = 0;
		this.row = row;
		this.column = column;
	}
	
	@Override
	public void resetNeighbourCounter() {
		this.neighbours = 0;
	}

	@Override
	public void addNeighbour() {
		if (neighbours < 8){
			++neighbours;
		} else {
			System.err.println("ERRROR: too many neighbours - "+neighbours+" "+this.toString());
		}
	}

	@Override
	public void removeNeighbour() {
		if (neighbours > 0){
			--neighbours;
		} else {
			System.err.println("ERRROR: neighbours cannot be negative - "+neighbours+" "+this.toString());
		}
	}
	
	// lazy initialization
	private volatile Set<Cell> neighbourCells = null;
	@Override
	public Set<Cell> neighbourCells(){
		if (neighbourCells == null) {
			neighbourCells = new HashSet<Cell>(8);
			neighbourCells.add(new CellImpl(row-1, column));
			neighbourCells.add(new CellImpl(row-1, column+1));
			neighbourCells.add(new CellImpl(row, column+1));
			neighbourCells.add(new CellImpl(row+1, column+1));
			neighbourCells.add(new CellImpl(row+1, column));
			neighbourCells.add(new CellImpl(row+1, column-1));
			neighbourCells.add(new CellImpl(row, column-1));
			neighbourCells.add(new CellImpl(row-1, column-1));
		}
		return neighbourCells;
	}
	
	@Override
	public int getRow(){
		return row;
	}
	
	@Override
	public int getColumn(){
		return column;
	}
	
	@Override
	public CellState getState(){
		return state;
	}
	
	@Override
	public void setDead(){
		state = CellState.DEAD;
	}
	
	@Override
	public void setLife(){
		state = CellState.LIFE;
	}
	
	@Override
	public void setBorn(){
		state = CellState.toBeLIFE;
	}
	
	@Override
	public void setDying(){
		state = CellState.toBeDEAD;
	}
	
	@Override
	public boolean isAlive(){
		return state.equals(CellState.LIFE);
	}
	
	@Override
	public boolean isDead(){
		return state.equals(CellState.DEAD);
	}
	
	@Override
	public boolean isDying(){
		return state.equals(CellState.toBeDEAD);
	}
	
	@Override
	public boolean isBorn(){
		return state.equals(CellState.toBeLIFE);
	}

	@Override
	public String toString() {
		return "State: " + state.toString() + "; row: " + row + ", column: " + column; 
	}

	@Override
	public int getNeighbour() {
		return neighbours;
	}
	
	@Override
	public int hashCode() {
		final int prime = 27;
		int result = 3;
		result = prime * result + row;
		result = prime * result + column;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CellImpl other = (CellImpl) obj;
		if (this.row != other.row)
			return false;
		if (this.column != other.column)
			return false;
		return true;
	}

}
