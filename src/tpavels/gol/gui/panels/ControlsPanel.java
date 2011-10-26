package tpavels.gol.gui.panels;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import tpavels.gol.constants.Constants;
import tpavels.gol.core.Core;
import tpavels.gol.gui.impl.MainGUIframe;

public class ControlsPanel implements ActionListener, Constants{
	
	private static final int START_ID = 0;
	private static final String START = "START";
	private static final String PAUSE = "PAUSE";
	private static final String RESUME = "RESUME";
	
	private static final int RANDOM_ID = 1;
	private static final String RANDOM = "RANDOM("+NUMBER_RANDOM_CELLS+")";
	
	private static final int RESET_ID = 2;
	private static final String RESET = "RESET";
	
	private static final int STEP_ID = 3;
	private static final String STEP = "STEP";
	

	private Core game = null;
	
	/**
	 * Holds all control buttons
	 * {0:START, 1:RANDOM, 2:RESET, 3:STEP}
	 */
	ArrayList<JButton> buttons = new ArrayList<JButton>();
	
	public ControlsPanel(Core game) {
		this.game = game;
	}

	public void createControls(JPanel container){
		
		JButton start, step, random, reset;
		start = createButton(START);
		start.setEnabled(false);
		buttons.add(start);
		
		random = createButton(RANDOM);
		random.setEnabled(true);
		buttons.add(random);
		
		reset = createButton(RESET);
		reset.setEnabled(false);
		buttons.add(reset);
		
		step = createButton(STEP);
		buttons.add(step);
		step.setEnabled(false);
		
		/*
		 * first and last empty JPanel are needed to center the control buttons
		 */
		MainGUIframe.addUIComponent(container, new JPanel(), GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
				new int[] {0,0,1,1}, new Insets(BORDER, BORDER, BORDER, BORDER));
		
		MainGUIframe.addUIComponent(container, start, GridBagConstraints.WEST, GridBagConstraints.NONE,
				new int[] {1,0,1,1}, new Insets(BORDER, BORDER, BORDER, BORDER));
		MainGUIframe.addUIComponent(container, random, GridBagConstraints.WEST, GridBagConstraints.NONE,
				new int[] {2,0,1,1}, new Insets(BORDER, BORDER, BORDER, BORDER));
		MainGUIframe.addUIComponent(container, reset, GridBagConstraints.WEST, GridBagConstraints.NONE,
				new int[] {3,0,1,1}, new Insets(BORDER, BORDER, BORDER, BORDER));
		MainGUIframe.addUIComponent(container, step, GridBagConstraints.WEST, GridBagConstraints.NONE,
				new int[] {4,0,1,1}, new Insets(BORDER, BORDER, BORDER, BORDER));
		
		MainGUIframe.addUIComponent(container, new JPanel(), GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL,
				new int[] {5,0,1,1}, new Insets(BORDER, BORDER, BORDER, BORDER));
		
	}
	
	public void actionPerformed(ActionEvent e) {
		
		 			//** START **//
		if (e.getActionCommand().equals(START) || e.getActionCommand().equals(RESUME) ){
			game.start();
			
			// this button is PAUSE now
			buttons.get(START_ID).setText(PAUSE);
		}
		
					//** PAUSE **//
		if (e.getActionCommand().equals(PAUSE)){
			game.pause();
			
			// can resume
			buttons.get(START_ID).setText(RESUME);
		}
				
		 			//** RANDOM **//
		if (e.getActionCommand().equals(RANDOM)){
			game.random();
			
			// game is set to pause
			if (buttons.get(START_ID).getText().equals(PAUSE)){
				buttons.get(START_ID).setText(RESUME);
			}
			// there are cell to interact
			for (JButton bttn : buttons) {
				bttn.setEnabled(true);
				bttn.setFocusable(true);
			}
		}
		
					//** RESET **//
		if (e.getActionCommand().equals(RESET)){
			game.reset();
			
			// reset buttons at initial state
			buttons.get(START_ID).setText(START);
			buttons.get(START_ID).setEnabled(false);
			buttons.get(RESET_ID).setEnabled(false);
			buttons.get(STEP_ID).setEnabled(false);
			buttons.get(START_ID).setFocusable(false);
			buttons.get(RESET_ID).setFocusable(false);
			buttons.get(STEP_ID).setFocusable(false);
		}
		
					//** STEP **//
		if (e.getActionCommand().equals(STEP)){
			game.step();
			
			// pause the main infinity loop
			if (buttons.get(START_ID).getText().equals(PAUSE)){
				buttons.get(START_ID).setText(RESUME);
			}
		}
	}

	private JButton createButton(String name) {
		JButton button = new JButton(name);
		button.setVisible(true);
		button.addActionListener(this);
		button.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		button.setBorder(new EmptyBorder(BORDER, BORDER, BORDER, BORDER));
		return button;
	}

	/**
	 * Handle Game Over state
	 */
	public void end() {
		buttons.get(START_ID).setEnabled(false);
		buttons.get(STEP_ID).setEnabled(false);
	}
}
