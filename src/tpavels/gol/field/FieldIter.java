package tpavels.gol.field;

import java.util.Iterator;
import tpavels.gol.constants.Constants;

public interface FieldIter<E> extends Iterator<Cell>,  Constants {
	
	/**
	 * Return true while there are more {@link FieldIter} to return.
	 * After it returns true, iterator will reset to start position,
	 * defined as constant: {@link Constants#START_POINT}
	 * @return true if there is at least one cell more
	 */
	public boolean hasNext();
	
	/* (non-Javadoc)
	 * @see java.util.Iterator#next()
	 */
	public Cell next();
	
	/**
	 * Sets current returned {@link Cell} with {@link #next()} to dying
	 * @see Field#setDead(Cell)
	 */
	public void setDead();
	
	/**
	 * Sets current returned {@link Cell} with {@link #next()} to born
	 * @see Field#setLife(Cell)
	 */
	public void setLive();

	/**
	 * @return true if there are only dead cells
	 */
	public boolean isEmpty();
	
}
