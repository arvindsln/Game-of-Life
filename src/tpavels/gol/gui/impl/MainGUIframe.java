package tpavels.gol.gui.impl;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import tpavels.gol.constants.Constants;
import tpavels.gol.core.Core;
import tpavels.gol.field.Field;

public class MainGUIframe implements Constants {
	
	/**
	 * @param jp Jpanel, where to add new component
	 * @param jc JComponent to add
	 * @param loc location of component on the display (GridBagConstraints: CENTER, WEST, EAST, ... )
	 * @param type type of component (GridBagConstraints: NONE, BOTH, HORIZONTAL, VERTICAL)
	 * @param decart {cell number from left, cell number from top, 
	 * 					cells to fill right, cells to fill bottom} 
	 * @param insets component border
	 */
	public static void addUIComponent(JPanel jp, JComponent jc, int loc, int type,
																		 int[] decart, Insets insets){
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = loc;
		c.fill = type;
		c.gridx = decart[0];
		c.gridy = decart[1];
		c.gridwidth = decart[2];
		c.gridheight = decart[3];
		
		/*
		 * weightx and weighty are depended on type, see below
		 * 
		 * type									{weightx, weighty}			
		 * ---------------------------------------------------------------------------					
		 * GridBagConstraints.NONE 		 = 0 == {0.0	, 0.0	 }	# 4-0 = 4 == 1|00|
		 * GridBagConstraints.BOTH 		 = 1 ==	{1.0	, 1.0	 }  # 4-1 = 3 == 0|11|
		 * GridBagConstraints.HORIZONTAL = 2 ==	{1.0	, 0.0	 } 	# 4-2 = 2 == 0|10|
		 * GridBagConstraints.VERTICAL 	 = 3 ==	{0.0	, 1.0	 }  # 4-3 = 1 == 0|01|
		 */
		c.weightx = (double)(((4 - type)& 2) >> 1);
		c.weighty = (double)((4 - type)& 1);
		
		c.insets = insets;
		jp.add(jc,c);
	}
	
	FieldPanel fieldPanel = null;

	public void createGUI(final Core game, final Field field){
		
		JPanel containerPanel = new JPanel(new GridBagLayout());
		int fieldFrameSizeW = (CELL_SIZE_COL * COLS) + BORDER*2;
		int fieldFrameSizeH = (CELL_SIZE_ROW * ROWS) + 30 + BORDER*2;
//		int fieldFrameSizeW = 800;
//		int fieldFrameSizeH = 400;
		
		JFrame frame = createMainFrame(fieldFrameSizeW, fieldFrameSizeH);
		
		createControls(containerPanel, game);
		createField(containerPanel, field);
		
		frame.setContentPane(containerPanel);
		frame.setVisible(true);
	}

	public void reDraw(){
		fieldPanel.revalidate();
		fieldPanel.repaint();
	}
	
	private void createControls(JPanel container, Core game) {
		ControlsPanel controlsPanel = new ControlsPanel(game);
		controlsPanel.createControls(container);
	}

	private void createField(JPanel container, final Field field) {
		fieldPanel = new FieldPanel(field);
		JPanel jpanel = new JPanel(){
			public void paint(Graphics g) {
				super.paintChildren(g);
				
				g.setColor(Color.PINK);
				g.fillRect(0, 0, getWidth(), getHeight());
			}
		};
		fieldPanel.createField(jpanel);
		MainGUIframe.addUIComponent(container, jpanel, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new int[] {0,1,7,1}, new Insets(0, BORDER, BORDER, BORDER));
	}
	
	private JFrame createMainFrame(int fieldFrameSizeW, int fieldFrameSizeH) {
		JFrame frame = new JFrame(FRAME_TITLE);
		frame.setSize(fieldFrameSizeW, fieldFrameSizeH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setAlwaysOnTop(true);
		frame.setResizable(true);
		frame.setUndecorated(false);
		return frame;
	}
	
}
