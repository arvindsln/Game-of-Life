package tpavels.gol.gui.panels;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

import tpavels.gol.constants.Constants;
import tpavels.gol.core.Core;
import tpavels.gol.field.Cell;
import tpavels.gol.field.Field;
import tpavels.gol.gui.impl.MainGUIframe;

public class FieldPanel extends JPanel implements Constants {

	private static final int FPS_Y = 20;
	private static final int FPS_X = 5;
	private static final int FONT_SIZE = 20;
	private boolean showFPS = true; 
	
	private Field field;
	private Core game;
	private List<JButton> buttons;
	
	public FieldPanel(JPanel container, Core game, final Field field, List<JButton> buttons) {
		this.field = field;
		this.game = game;
		this.buttons = buttons;
		MainGUIframe.addUIComponent(container, this, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new int[] {0,1,10,1}, new Insets(0, BORDER, BORDER, BORDER));
		addMouseListener(mouseHandler);
		addMouseMotionListener(mouseHandler);
	}
	
	
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
		
		drawBackgound(DEAD_COLOUR);

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
			int cols = (cell.getColumn() * CELL_SIZE_INT);
			int rows = (cell.getRow() * CELL_SIZE_INT);
			gfx2D.fill(new Rectangle2D.Double(cols, rows, CELL_SIZE_DRAW, CELL_SIZE_DRAW));
		}
		
		drawFPS();
	}

	private void drawBackgound(Color colour) {
		gfx2D.setPaint(colour);
		gfx2D.fillRect(0, 0, IMAGE_WIDTH, IMAGE_HEIGHT);
	}

	private long nextSecond = System.currentTimeMillis() + 1000;
	private int frameInLastSecond = 0;
	private int framesInCurrentSecond = 0;
	private void drawFPS() {
		long currentTime = System.currentTimeMillis();
		if (currentTime > nextSecond) {
			nextSecond += 1000;
			frameInLastSecond = framesInCurrentSecond;
			framesInCurrentSecond = 0;
		}
		framesInCurrentSecond++;
		if (showFPS) {
			gfx2D.setColor(TEXT_COLOUR);
			gfx2D.setFont(new Font(FONT, Font.BOLD, FONT_SIZE));
			gfx2D.drawString(frameInLastSecond + " FPS", FPS_X, FPS_Y);
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
	
	private boolean alive = false;
	private MouseAdapter mouseHandler = new MouseAdapter() {
		
		@Override
		public void mouseReleased(MouseEvent e) {
			alive = false;
		}

		@Override
		public void mousePressed(MouseEvent e) {
			pauseGame();
			if (isAlive(e)){
				alive = true;
			}
			drawCellWithMouse(e);
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			drawCellWithMouse(e);
		}

	};
	
	private boolean isField(MouseEvent e) {
		int rowY = e.getY();
		int columnX = e.getX();
		return (rowY >= START_POINT && rowY < IMAGE_HEIGHT) && 
				(columnX < IMAGE_WIDTH && columnX >= START_POINT);
	}

	private void pauseGame() {
		game.pause();
		//FIXME do it more correctly (?)
		// change button functional name 
		if (buttons.get(ControlsPanel.START_ID).getText().equals(ControlsPanel.PAUSE)){
			buttons.get(ControlsPanel.START_ID).setText(ControlsPanel.RESUME);
		}
	}
	
	private boolean isAlive(MouseEvent e) {
		if (isField(e)) {
			int row = (int) (e.getY() / CELL_SIZE_INT);
			int column = (int) (e.getX() / CELL_SIZE_INT);
			if (field.getCell(row, column).isAlive()) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	private void drawCellWithMouse(MouseEvent e) {
		if (isField(e)) {
			int row = (int) (e.getY() / CELL_SIZE_INT);
			int column = (int) (e.getX() / CELL_SIZE_INT);
			if (!alive && field.getCell(row, column).isDead()) {
				field.addAliveCell(row, column);
				gameRender();
				paintScreen();
			} else if (alive && field.getCell(row, column).isAlive()){
				field.removeAliveCell(row, column);
				gameRender();
				paintScreen();
			}
		}
	}
}
