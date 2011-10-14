package tpavels.gol.gui.impl;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import tpavels.gol.constants.Constants;
import tpavels.gol.core.Core;
import tpavels.gol.field.Field;
import tpavels.gol.gui.GUIManager;

public class GUIManagerImpl implements Constants, GUIManager {
	
	private Core game = null;
	private Field field = null;
	private MainGUIframe mainUIframe = null;
	
	public GUIManagerImpl(Core game, Field field) {
		this.game = game;
		this.field = field;
	}
	
	@Override
	public void show() {
		setupLookAndFeel();
		startMainGUI();
	}
	
	@Override
	public void reDraw(){
		mainUIframe.reDraw();
	}
	
	@Override
	public void end() {
		mainUIframe.end();
	}

	private void startMainGUI() {
		this.mainUIframe = new MainGUIframe();
		this.mainUIframe.createGUI(game, field);
		this.mainUIframe.reDraw();
	}
	
	private void setupLookAndFeel() {
		String nativeLF = UIManager.getSystemLookAndFeelClassName();

	    try {
			UIManager.setLookAndFeel(nativeLF);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
	}
}
