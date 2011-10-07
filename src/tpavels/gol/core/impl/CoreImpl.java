package tpavels.gol.core.impl;

import tpavels.gol.core.Core;
import tpavels.gol.field.Cell;
import tpavels.gol.field.Field;
import tpavels.gol.field.FieldIter;
import tpavels.gol.field.impl.FieldImpl;


public class CoreImpl implements Core, Runnable {
	
	private Field field;
	private FieldIter<Cell> fieldIter;
	private int generation = 0;
	private GameState state;
	
	@Override
	public void pause() {
		state = GameState.PAUSE;
	}
	
	@Override
	public void random() {
		field.createRandomLiveCells();
		debugPrint(PrintLevel.FULL); // debug output
	}

	@Override
	public void random(int n) {
		field.createRandomLiveCells(n);
		debugPrint(PrintLevel.FULL); // debug output
	}

	@Override
	public void reset() {
		field.clearField();
		generation = 0;
		state = GameState.PAUSE;
		debugPrint(PrintLevel.FIELD); // debug output
	}
		
	@Override
	public void run() {
		while (true) {
			switch (state) {
			case RUN:
				if (fieldIter.isEmpty()) debugPrint(PrintLevel.EMPTY);
				else debugPrint(PrintLevel.FULL); // debug output
				
				try {
					Thread.sleep(TICK);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				nextGeneration();
				break;
			case PAUSE:
				// PAUSE 
				break;
			default:
				break;
			}
		}
	}


	@Override
	public void start() {
		state = GameState.RUN;
	}

	@Override
	public void startup() {
		field = new FieldImpl();
		fieldIter = field.iterator();
		state = GameState.PAUSE;
		this.run();
	}
	
	@Override
	public void step() {
		state = GameState.PAUSE;
		nextGeneration();
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("Generation: ");
		str.append(generation);
		str.append('\n');
		str.append(field.toString());
		return str.toString();
	}

	private void finish() {
		debugPrint(PrintLevel.FULL); // debug output
		state = GameState.PAUSE;
		generation = -1;
	}

	private void nextGeneration() {
		
/* 
 * 1. Any live cell with fewer than two live neighbours dies, as if caused by under-population.
 * 2. Any live cell with two or three live neighbours lives on to the next generation.
 * 3. Any live cell with more than three live neighbours dies, as if by overcrowding.
 * 4. Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction
 */
		int neighbours = 0;
		if(!fieldIter.isEmpty()) {
			
			while(fieldIter.hasNext()){
				Cell cell = fieldIter.next();
				neighbours = field.getNeighbours(cell);
				if (cell.isDead()) {
					 if (neighbours == 3) field.setLife(cell);
				} else {
					if (neighbours < 2) field.setDead(cell);
					else if (neighbours  > 3) field.setDead(cell);
					}
				}
			
			if (!field.updateChanges()) finish();
			generation++; // generation is created
		} else {
			// everyone is dead
			state = GameState.PAUSE; 
		}
	}

	private void debugPrint(PrintLevel pLvl){
		switch (pLvl) {
		case EMPTY:
			System.out.println("To populate field with new cells, press RANDOM\n" +
					"It will create " + NUMBER_RANDOM_CELLS + " cells on the field " +
							"at random location");
			break;
		case FIELD:
			System.out.println(field.toString());	
			break;
		case FULL:
			System.out.println(this.toString());
			break;
		default:
			System.out.println("Debug Output");
			break;
		}
	}
}
