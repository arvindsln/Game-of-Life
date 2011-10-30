package tpavels.gol.core.impl;

import java.util.concurrent.TimeUnit;

import tpavels.gol.core.Core;
import tpavels.gol.field.Field;
import tpavels.gol.field.impl.FieldImpl;
import tpavels.gol.gui.GUIManager;
import tpavels.gol.gui.impl.GUIManagerImpl;


public class CoreImpl implements Core {
	
	private Field field = new FieldImpl();
	private GUIManager guiManager = null;
	private GameState state = null;
	private long delay = TICK;
	
	@Override
	public void startup() {
		field = new FieldImpl();
		state = GameState.PAUSE;
		renderGUI();
		lifeLoop();
	}
	
	@Override
	public void start() {
		state = GameState.RUN;
	}
	
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
		field.createRandomLifeCells(n);
	}

	@Override
	public void reset() {
		field.clearField();
		state = GameState.PAUSE;
		guiManager.reDraw();
	}

	@Override
	public void step() {
		state = GameState.STEP;
	}
	
	@Override
	public void setDelay(long delay) {
		this.delay = delay;
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("Game state: " + state);
		str.append(field.toString());
		return str.toString();
	}
	
	private void renderGUI() {
		guiManager = new GUIManagerImpl(this, field);
		guiManager.show();
	}

	private void lifeLoop() {
		while (true) {
			switch (state) {
			case RANDOM:
				doRandom();
				break;
			case RUN:
				doRun();
				break;
			case PAUSE:
				doPause();
				break;
			case STEP:
				doStep();
				break;
			default:
				System.err.println("Unknown Game State: " + state);
				break;
			}
		}
	}

	private void doRandom() {
		field.createRandomLifeCells();
		guiManager.reDraw();
		pause();
	}
	
	private void doRun() {
		boolean isNextGeneration = field.nextGeneration();
		if (!isNextGeneration) doEnd();
		guiManager.reDraw();
		try {
			TimeUnit.MILLISECONDS.sleep(delay);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void doPause() {
		// PAUSE do nothing
	}

	private void doStep() {
		boolean isNextGeneration = field.nextGeneration();
		if (!isNextGeneration) doEnd();
		guiManager.reDraw();
		pause();
	}

	private void doEnd() {
		guiManager.reDraw();
		guiManager.end();
		pause();		
	}
	
	
}
