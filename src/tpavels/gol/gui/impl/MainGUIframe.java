package tpavels.gol.gui.impl;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import tpavels.gol.constants.Constants;
import tpavels.gol.core.Core;
import tpavels.gol.field.Field;
import tpavels.gol.gui.panels.ControlsPanel;
import tpavels.gol.gui.panels.FieldPanel;

public class MainGUIframe implements Constants {
	
	/**
	 * @param jp JPanel, where to add new component
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
	ControlsPanel controlsPanel = null;

	public void createGUI(final Core game, final Field field){
		
		final JPanel containerPanel = new JPanel(new GridBagLayout());
		int fieldFrameSizeW = FRAME_WIDTH;
		int fieldFrameSizeH = FRAME_HEIGHT;
		
		final JFrame frame = createMainFrame(fieldFrameSizeW, fieldFrameSizeH);
		controlsPanel = new ControlsPanel(containerPanel, game);
		fieldPanel = new FieldPanel(containerPanel, field);
		
		frame.setFocusable(true);
		frame.setContentPane(containerPanel);
		frame.setVisible(true);
	}

	public void reDraw(){
		fieldPanel.gameRender();
		fieldPanel.paintScreen();
	}
	
	private JFrame createMainFrame(int fieldFrameSizeW, int fieldFrameSizeH) {
		JFrame frame = new JFrame(FRAME_TITLE);
		frame.setSize(fieldFrameSizeW, fieldFrameSizeH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setAlwaysOnTop(false);
		frame.setResizable(false);
		frame.setUndecorated(false);
		return frame;
	}
	
	public void end() {
		controlsPanel.end();
	}
}
