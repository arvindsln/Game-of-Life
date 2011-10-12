package tpavels.gol.gui.impl;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import tpavels.gol.constants.Constants;
import tpavels.gol.core.Core;
import tpavels.gol.field.Field;
import tpavels.gol.gui.GUIManager;

public class GUIManagerImpl implements Constants, GUIManager {
	
	MainGUIThread mainUI = null;
	Core game = null;
	Field field = null;
	
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
		mainUI.reDraw();
	}

	private void startMainGUI() {
		mainUI = new MainGUIThread();
		mainUI.setup(field, game);
		Thread mainUIThread = new Thread(mainUI, "mainGUI");
		mainUIThread.start();
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
