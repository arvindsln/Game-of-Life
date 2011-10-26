package tpavels.gol.gui.panels;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.Image;
import java.awt.Insets;
import java.awt.geom.Rectangle2D;
import java.util.Iterator;

import javax.swing.JPanel;

import tpavels.gol.constants.Constants;
import tpavels.gol.field.Cell;
import tpavels.gol.field.Field;
import tpavels.gol.gui.impl.MainGUIframe;

public class FieldPanel extends JPanel implements Constants {

	public void createField(JPanel container){
		MainGUIframe.addUIComponent(container, this, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new int[] {0,1,7,1}, new Insets(0, BORDER, BORDER, BORDER));
	}
	
	private Field field;
	public FieldPanel(final Field field) {
		this.field = field;
	}
	
	private boolean showStats = true; // show\hide FPS
	private long nextSecond = System.currentTimeMillis() + 1000;
	private int frameInLastSecond = 0;
	private int framesInCurrentSecond = 0;
	
	private Image fieldImage = null;
	private Graphics2D gfx2D = (Graphics2D) this.getGraphics();
	public void gameRender() {
		if (fieldImage == null) { 
			fieldImage = createImage(IMAGE_WIDTH, IMAGE_HEIGHT);
			if (fieldImage == null) {
				System.err.println("Field Image is null");
				return;
			} else
				gfx2D = (Graphics2D) fieldImage.getGraphics();
		}
		gfx2D.setPaint(DEAD_COLOUR);
		gfx2D.fillRect(0, 0, IMAGE_WIDTH, IMAGE_HEIGHT);

		Iterator<Cell> cellsToDraw = field.getAliveCells().iterator();
		while(cellsToDraw.hasNext()){
			Cell cell = cellsToDraw.next();
			switch (cell.getState()) {
			case LIFE:
				gfx2D.setPaint(LIFE_COLOUR);
				break;
			default:

			   break;
			}
			int cols = (cell.getColumn() * CELL_SIZE);
			int rows = (cell.getRow() * CELL_SIZE);
			gfx2D.fill(new Rectangle2D.Double(cols, rows, CELL_SIZE, CELL_SIZE));
			
		}
		
		// FPS
		long currentTime = System.currentTimeMillis();
		if (currentTime > nextSecond) {
			nextSecond += 1000;
			frameInLastSecond = framesInCurrentSecond;
			framesInCurrentSecond = 0;
		}
		framesInCurrentSecond++;
		if (showStats){
			gfx2D.setPaint(Color.WHITE);
			gfx2D.fillRect(17, 7, 46, 20);
			gfx2D.setColor(Color.BLACK);
			gfx2D.drawString(frameInLastSecond + " fps", 20, 20);
		}
	}

	public void paintScreen() {
		Graphics g;
		try {
			g = this.getGraphics();
			if ((g != null) && (fieldImage != null)) {
				g.drawImage(fieldImage, START_POINT, START_POINT, null);
				g.dispose();
			}
		} catch (Exception e) {
			System.err.println("Graphics context error: " + e);
		}
	}
	
	@Override
	public void paint(Graphics g) {
		/*  
		 * needs to be overridden for cases when
		 * main frame loses focus, hidden, moved or others.
		 */ 
		paintComponents(g);
	}
	
	@Override
	public void paintComponents(Graphics g) {
		super.paintComponents(g);
		if (fieldImage != null) {
			g.drawImage(fieldImage, START_POINT, START_POINT, null);
		}
	}

	private static final long serialVersionUID = -6629202213300350819L;
}
