package tpavels.gol.gui.panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSlider;
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
	
	
	/**
	 * Holds all control buttons
	 * {0:START, 1:RANDOM, 2:RESET, 3:STEP}
	 */
	private ArrayList<JButton> buttons = new ArrayList<JButton>();
	private JSlider speed = null;
	private Core game = null;
	private SliderPanel slider = null;
	
	public ControlsPanel(JPanel container, Core game) {
		this.game = game;
		addKeyPresserListener(container);
		createAllControlsButtons();
		slider = new SliderPanel(game);
		layoutComponents(container);
	}
	
	public void actionPerformed(ActionEvent e) {
		
		 			//** START **//
		if (e.getActionCommand().equals(START) || e.getActionCommand().equals(RESUME) ){
			game.start();
			
			// this button is PAUSE now
			buttons.get(START_ID).setText(PAUSE);
			buttons.get(START_ID).setMnemonic('P');
		}
		
					//** PAUSE **//
		if (e.getActionCommand().equals(PAUSE)){
			game.pause();
			
			// can resume
			buttons.get(START_ID).setText(RESUME);
			buttons.get(START_ID).setMnemonic('u');
		}
				
		 			//** RANDOM **//
		if (e.getActionCommand().equals(RANDOM)){
			game.random();
			
			// game is set to pause
			if (buttons.get(START_ID).getText().equals(PAUSE)){
				buttons.get(START_ID).setText(RESUME);
				buttons.get(START_ID).setMnemonic('u');
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
			buttons.get(START_ID).setMnemonic('S');
			buttons.get(START_ID).setEnabled(false);
			buttons.get(RESET_ID).setEnabled(false);
			buttons.get(STEP_ID).setEnabled(false);
			buttons.get(START_ID).setFocusable(false);
			buttons.get(RESET_ID).setFocusable(false);
			buttons.get(STEP_ID).setFocusable(false);
			slider.reset();
		}
		
					//** STEP **//
		if (e.getActionCommand().equals(STEP)){
			game.step();
			
			// pause the main infinity loop
			if (buttons.get(START_ID).getText().equals(PAUSE)){
				buttons.get(START_ID).setText(RESUME);
				buttons.get(START_ID).setMnemonic('u');
			}
		}
	}

	/**
	 * Handle Game Over state
	 */
	public void end() {
		buttons.get(START_ID).setEnabled(false);
		buttons.get(STEP_ID).setEnabled(false);
	}


	private void addKeyPresserListener(JPanel container) {
		Frame[] frames = Frame.getFrames();
		for (Frame frame : frames) {
			frame.addKeyListener(keyAdapter);
		}
	}
	
	private void createAllControlsButtons(){
		JButton start, step, random, reset;
		start = createButton(START);
		start.setEnabled(false);
		start.setMnemonic('S');
		buttons.add(start);
		
		random = createButton(RANDOM);
		random.setEnabled(true);
		random.setMnemonic('R');
		random.setDisplayedMnemonicIndex(0);
		buttons.add(random);
		
		reset = createButton(RESET);
		reset.setMnemonic('e');
		reset.setEnabled(false);
		buttons.add(reset);
		
		step = createButton(STEP);
		step.setMnemonic('t');
		buttons.add(step);
		step.setEnabled(false);
	}
	
	private JButton createButton(String name) {
		JButton button = new JButton(name);
		button.setVisible(true);
		button.addActionListener(this);
		button.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		button.setBorder(new EmptyBorder(BORDER, BORDER, BORDER, BORDER));
		return button;
	}
	
	private void layoutComponents(JPanel container) {
		/*
		 * Empty JPanels are needed to center others components
		 */
		
		MainGUIframe.addUIComponent(container, new JPanel(), GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
				new int[] {1,0,1,1}, new Insets(BORDER, BORDER, BORDER, BORDER));
		
		MainGUIframe.addUIComponent(container, new JPanel(), GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL,
				new int[] {2,0,1,1}, new Insets(BORDER, BORDER, BORDER, BORDER));
		
		MainGUIframe.addUIComponent(container, new JPanel(), GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL,
				new int[] {3,0,1,1}, new Insets(BORDER, BORDER, BORDER, BORDER));
		
		MainGUIframe.addUIComponent(container, buttons.get(START_ID), GridBagConstraints.WEST, GridBagConstraints.NONE,
				new int[] {4,0,1,1}, new Insets(BORDER, BORDER, BORDER, BORDER));
		MainGUIframe.addUIComponent(container, buttons.get(RANDOM_ID), GridBagConstraints.WEST, GridBagConstraints.NONE,
				new int[] {5,0,1,1}, new Insets(BORDER, BORDER, BORDER, BORDER));
		MainGUIframe.addUIComponent(container, buttons.get(RESET_ID), GridBagConstraints.WEST, GridBagConstraints.NONE,
				new int[] {6,0,1,1}, new Insets(BORDER, BORDER, BORDER, BORDER));
		MainGUIframe.addUIComponent(container, buttons.get(STEP_ID), GridBagConstraints.WEST, GridBagConstraints.NONE,
				new int[] {7,0,1,1}, new Insets(BORDER, BORDER, BORDER, BORDER));
		
		MainGUIframe.addUIComponent(container, new JPanel(), GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL,
				new int[] {8,0,1,1}, new Insets(BORDER, BORDER, BORDER, BORDER));
		
		MainGUIframe.addUIComponent(container, slider, GridBagConstraints.EAST, GridBagConstraints.NONE,
				new int[] {9,0,1,1}, new Insets(BORDER, BORDER, BORDER, BORDER));
		
		MainGUIframe.addUIComponent(container, new JPanel(), GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL,
				new int[] {10,0,1,1}, new Insets(BORDER, BORDER, BORDER, BORDER));
	}

	private KeyAdapter keyAdapter = new KeyAdapter() {
		@Override
		public void keyPressed(KeyEvent e) {
			switch (e.getKeyCode()) {
			case KeyEvent.VK_Q:
			case KeyEvent.VK_ESCAPE:
				Frame[] frames = Frame.getFrames();
				for (Frame frame : frames) {
					if (frame.isDisplayable()) {
						frame.dispose();
					}
					System.exit(0);
				}
				break;
			case KeyEvent.VK_S: // START
			case KeyEvent.VK_U: // RESUME
			case KeyEvent.VK_P: // PAUSE
				buttons.get(START_ID).doClick();
				break;
			case KeyEvent.VK_R: // RANDOM
				buttons.get(RANDOM_ID).doClick();
				break;
			case KeyEvent.VK_E: // RESET
				buttons.get(RESET_ID).doClick();
				break;
			case KeyEvent.VK_T: // STEP
				buttons.get(STEP_ID).doClick();
				break;
			case KeyEvent.VK_EQUALS:
			case KeyEvent.VK_PLUS:
			case KeyEvent.VK_ADD: // faster
				slider.changeValue(-1);
				break;
			case KeyEvent.VK_MINUS:
			case KeyEvent.VK_SUBTRACT: // slower
				slider.changeValue(+1);
				break;
			default:
				break;
			}

		}};
}
