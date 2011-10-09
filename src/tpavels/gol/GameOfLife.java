package tpavels.gol;

import tpavels.gol.core.Core;
import tpavels.gol.core.impl.CoreImpl;
import tpavels.gol.gui.Controls;


/**
 * <h1>Conway's Game of Life</h1>
 * <p>Source: <a href="http://en.wikipedia.org/wiki/Conway's_Game_of_Life">Conway's Game of Life</a></p>
 * 
 * @version 0.1
 * @author Pavels Trifonovs
 *
 */
public class GameOfLife {

	public static void main(String[] args) {
		
		final Core game = new CoreImpl();
		
		Thread controlsThread = new Thread(){
			public void run() {   
                Controls controls = new Controls();
                controls.startup(game);
			};
		};
		controlsThread.start();
		game.startup();
	}
}
