package tpavels.gol.gui.impl;

import tpavels.gol.core.Core;
import tpavels.gol.field.Field;

public class MainGUIThread implements Runnable {
	
	private Field field = null;
	private MainGUIframe mainUIframe = null;
	private Core game;
	
	public void setup(final Field field, final Core game) {
		this.field = field;
		this.game = game;
	}
	
	public void reDraw(){
		mainUIframe.reDraw();
	}

	@Override
	public void run() {
		this.mainUIframe = new MainGUIframe();
		this.mainUIframe.createGUI(game, field);
	}


}
