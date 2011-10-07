package tpavels.gol.field.impl;

import tpavels.gol.constants.Constants;
import tpavels.gol.field.Cell;
import tpavels.gol.field.Field;

public class CellImpl implements Constants, Cell{

	private CellState state;
	private int x;
	private int y;
	
	/**
	 * Initialize new Cell
	 * @param x cell ROW on the {@link Field}
	 * @param y cell COLUMN on the {@link Field} 
	 */
	public CellImpl(int x, int y){
		this.state = CellState.DEAD;
		this.x = x;
		this.y = y;
	}
	
	@Override
	public int getX(){
		return x;
	}
	
	@Override
	public int getY(){
		return y;
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
		return "State: " + state.toString() + "; x: " + x + ", y: " + y; 
	}
	

}
