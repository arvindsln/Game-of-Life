package tpavels.gol;

import tpavels.gol.core.Core;
import tpavels.gol.core.impl.CoreImpl;


/**
 * <h1>Conway's Game of Life</h1>
 * 
 * @author Pavels Trifonovs
 *
 */
public class GameOfLife {

	public static void main(String[] args) {
		
		final Core game = new CoreImpl();
		game.startup();
		
	}
}
