package tpavels.gol.gui;

public interface GUIManager {

	/**
	 * Show all GUI components
	 */
	public void show();

	/**
	 * Refresh GUI
	 */
	public void reDraw();

	
	/**
	 * The end. This generation is doomed.
	 */
	public void end();

}