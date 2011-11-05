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
		mainUIframe.reDraw(field);
	}
	
	private void startMainGUI() {
		mainUIframe = new MainGUIframe();
		mainUIframe.createGUI(game, field);
		reDraw();
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
