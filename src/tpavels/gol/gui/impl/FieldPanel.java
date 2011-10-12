package tpavels.gol.gui.impl;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

import tpavels.gol.constants.Constants;
import tpavels.gol.field.Cell;
import tpavels.gol.field.Field;
import tpavels.gol.field.FieldIter;

public class FieldPanel extends JPanel implements Constants {

	private Field field;

	public FieldPanel(final Field field) {
		this.field = field;
	}
	
	public void createField(JPanel jpanel){
		MainGUIframe.addUIComponent(jpanel, this, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new int[] {0,0,1,1}, new Insets(BORDER, BORDER, BORDER, BORDER));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.JComponent#paint(java.awt.Graphics)
	 */
	@Override
	public void paint(Graphics g) {
		
		g.setColor(Color.GREEN);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		int height2 = this.getHeight();
		int width2 = this.getWidth();
		int size = Math.min(height2, width2);
		
		super.paintChildren(g);
//		int cellSize = size / ROWS;
//		Graphics2D gfx2D = (Graphics2D) g;
//		FieldIter<Cell> fieldIter = field.iterator();
////		System.err.println("#######panel");
//		while (fieldIter.hasNext()) {
//			Cell cell = fieldIter.next();
//
//			switch (cell.getState()) {
//			case LIFE:
//				gfx2D.setPaint(Color.GREEN);
////				System.err.println("Green cell(X:"+cell.getX()+" Y:"+cell.getY()
////						+") state: "+cell.getState());
//				break;
//			case DEAD:
//				gfx2D.setPaint(Color.BLACK);
////				System.err.println("Black cell(X:"+cell.getX()+" Y:"+cell.getY()
////						+") state: "+cell.getState());
//				break;
//			default:
//				//gfx2D.setPaint(Color.PINK);
//				System.err.println("Incorrect cell(X:"+cell.getX()+" Y:"+cell.getY()
//									+") state: "+cell.getState());
//				break;
//			}
//
//			int cols = (cell.getY() * CELL_SIZE_ROW);
//			int rows = (cell.getX() * CELL_SIZE_COL);
////			int cols = (cell.getY() * cellSize);
////			int rows = (cell.getX() * cellSize);
//			gfx2D.fill(new Rectangle2D.Double(cols, rows, CELL_SIZE_ROW, CELL_SIZE_COL));
////			gfx2D.fill(new Rectangle2D.Double(cols, rows, cellSize, cellSize));
//		}
//		System.err.println("#######panel-end");
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -6629202213300350819L;
}
