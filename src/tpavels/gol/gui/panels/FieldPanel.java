package tpavels.gol.gui.panels;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.Image;
import java.awt.Insets;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

import tpavels.gol.constants.Constants;
import tpavels.gol.field.Cell;
import tpavels.gol.field.Field;
import tpavels.gol.field.FieldIter;
import tpavels.gol.gui.impl.MainGUIframe;

public class FieldPanel extends JPanel implements Constants {
	

	@Override
	public void paint(Graphics g) {
		/*  
		 * needs to be overridden for cases when
		 * main frame loses focus, hidden, moved or others.
		 */ 
		paintComponents(g);
	}

	private Image fieldImage = null;

	public void createField(JPanel container){
		MainGUIframe.addUIComponent(container, this, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new int[] {0,1,7,1}, new Insets(0, BORDER, BORDER, BORDER));
	}
	
	private Field field;

	public FieldPanel(final Field field) {
		this.field = field;
	}
	
	 //TODO Maybe its better to draw only alive cells. 
	Graphics2D gfx2D = (Graphics2D) this.getGraphics();
	public void gameRender() {
		if (fieldImage == null) { 
			fieldImage = createImage(IMAGE_WIDTH, IMAGE_HEIGHT);
			if (fieldImage == null) {
				System.err.println("fieldImage is null");
				return;
			} else
				gfx2D = (Graphics2D) fieldImage.getGraphics();
		}

		FieldIter<Cell> fieldIter = field.iterator();
		while (fieldIter.hasNext()) {
			Cell cell = fieldIter.next();

			switch (cell.getState()) {
			case LIFE:
				gfx2D.setPaint(LIFE_COLOUR);
				break;
			case DEAD:
				gfx2D.setPaint(DEAD_COLOUR);
				break;
			default:
				System.err.println("Incorrect cell(X:"+cell.getX()+" Y:"+cell.getY()
																		+") state: "+cell.getState());
				break;
			}

			int cols = (cell.getY() * CELL_SIZE);
			int rows = (cell.getX() * CELL_SIZE);
			gfx2D.fill(new Rectangle2D.Double(cols, rows, CELL_SIZE, CELL_SIZE));
		}
	}

	public void paintScreen() {
		Graphics g;
		try {
			g = this.getGraphics();
			if ((g != null) && (fieldImage != null))
				g.drawImage(fieldImage, START_POINT, START_POINT, null);
			g.dispose();
		} catch (Exception e) {
			System.err.println("Graphics context error: " + e);
		}
	}
	
	@Override
	public void paintComponents(Graphics g) {
		super.paintComponents(g);
		if (fieldImage != null)
			g.drawImage(fieldImage, START_POINT, START_POINT, null);
	}

	private static final long serialVersionUID = -6629202213300350819L;
}
