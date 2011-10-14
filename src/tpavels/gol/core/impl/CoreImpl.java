package tpavels.gol.core.impl;

import tpavels.gol.core.Core;
import tpavels.gol.field.Cell;
import tpavels.gol.field.Field;
import tpavels.gol.field.FieldIter;
import tpavels.gol.field.impl.FieldImpl;
import tpavels.gol.gui.GUIManager;
import tpavels.gol.gui.impl.GUIManagerImpl;


public class CoreImpl implements Core {
	
	private Field field = null;
	private FieldIter<Cell> fieldIter = null;
	private GUIManager guiManager = null;
	private int generation = 0;
	private GameState state = GameState.PAUSE;
	
	@Override
	public void pause() {
		state = GameState.PAUSE;
	}
	
	@Override
	public void random() {
		state = GameState.RANDOM;
	}

	@Override
	public void random(int n) {
		field.createRandomLiveCells(n);
	}

	@Override
	public void reset() {
		field.clearField();
		generation = 0;
		state = GameState.PAUSE;
		guiManager.reDraw();
	}

	public void lifeLoop() {
		while (true) {
			switch (state) {
			case RANDOM:
				field.createRandomLiveCells();
				guiManager.reDraw();
				pause();
				break;
			case RUN:
				nextGeneration();
				guiManager.reDraw();
				try {
					Thread.sleep(TICK);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				break;
			case PAUSE:
				// PAUSE
				break;
			case STEP:
				nextGeneration();
				guiManager.reDraw();
				pause();
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
		renderGUI();
		lifeLoop();
	}
	
	private void renderGUI() {
		this.guiManager = new GUIManagerImpl(this, field);
		guiManager.show();
	}

	@Override
	public void step() {
		state = GameState.STEP;
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
				boolean isNewGeneration = field.updateChanges();
				if (isNewGeneration){
					generation++; // generation is created
				} else {
					guiManager.end();
				}
			} else {
				// everyone is dead
				guiManager.reDraw();
				guiManager.end();
				pause();
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
