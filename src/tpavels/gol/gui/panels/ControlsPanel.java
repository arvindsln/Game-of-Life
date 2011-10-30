package tpavels.gol.gui.panels;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import tpavels.gol.constants.Constants;
import tpavels.gol.core.Core;
import tpavels.gol.field.Field;
import tpavels.gol.gui.impl.MainGUIframe;

public class ControlsPanel implements ActionListener, Constants{
	
	
	// four control button, ID is used to address them in the buttons list
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
	private List<JButton> buttons = null;
	private Core game = null;
	private SliderPanel slider = null;
	private InfoPanel infoPanel = null;
	
	public ControlsPanel(final JPanel container, final Core game, final Field field) {
		this.game = game;
		this.slider = new SliderPanel(game);
		this.infoPanel = new InfoPanel(field);
		this.buttons = new ArrayList<JButton>();
		
		addKeyPresserListener(container);
		createAllControlsButtons();
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
			}
		}
		
					//** RESET **//
		if (e.getActionCommand().equals(RESET)){
			game.reset();
			slider.reset();
			
			// reset buttons at initial state
			buttons.get(START_ID).setText(START);
			buttons.get(START_ID).setMnemonic('S');
			for (JButton bttn : buttons) {
				if (!RANDOM.equals(bttn.getText())) {
					bttn.setEnabled(false);
				}
			}
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
	
	
	/**
	 * Update information on top of the control panels,
	 * it will update two numbers: generation and alive cells
	 * @param field 
	 */
	public void updateInfo(Field field) {
		infoPanel.updateLabels(field);
	}


	private void addKeyPresserListener(final JPanel container) {
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
		buttons.add(random);
		
		reset = createButton(RESET);
		reset.setMnemonic('e');
		reset.setEnabled(false);
		buttons.add(reset);
		
		step = createButton(STEP);
		step.setMnemonic('t');
		buttons.add(step);
		step.setEnabled(false);
		
		for (JButton bttn : buttons) {
			/*
			 * Done to enable shortcuts
			 * When a button is in focus shortcuts aren't working
			 */
			bttn.setFocusable(false); 
			bttn.setBackground(CONTROL_PANEL_COLOUR);
		}
	}
	
	private JButton createButton(final String name) {
		JButton button = new JButton(name);
		button.setVisible(true);
		button.addActionListener(this);
		button.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		button.setBorder(new EmptyBorder(BORDER, BORDER, BORDER, BORDER));
		return button;
	}
	
	private void layoutComponents(final JPanel container) {
		
		 // Empty JPanel is needed to center(?) others components
		JPanel emptyPanel = new JPanel();
		emptyPanel.setForeground(CONTROL_PANEL_COLOUR);
		emptyPanel.setBackground(CONTROL_PANEL_COLOUR);
		
		MainGUIframe.addUIComponent(container, emptyPanel, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
				new int[] {1,0,1,1}, new Insets(0, BORDER, 0, BORDER));
		
		MainGUIframe.addUIComponent(container, infoPanel, GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL,
				new int[] {2,0,1,1}, new Insets(0, BORDER, 0, BORDER));
		
		MainGUIframe.addUIComponent(container, slider, GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL,
				new int[] {3,0,1,1}, new Insets(0, BORDER, 0, BORDER));
		
		MainGUIframe.addUIComponent(container, buttons.get(START_ID), GridBagConstraints.WEST, GridBagConstraints.NONE,
				new int[] {4,0,1,1}, new Insets(0, BORDER, 0, BORDER));
		MainGUIframe.addUIComponent(container, buttons.get(RANDOM_ID), GridBagConstraints.WEST, GridBagConstraints.NONE,
				new int[] {5,0,1,1}, new Insets(0, BORDER, 0, BORDER));
		MainGUIframe.addUIComponent(container, buttons.get(RESET_ID), GridBagConstraints.WEST, GridBagConstraints.NONE,
				new int[] {6,0,1,1}, new Insets(0, BORDER, 0, BORDER));
		MainGUIframe.addUIComponent(container, buttons.get(STEP_ID), GridBagConstraints.WEST, GridBagConstraints.NONE,
				new int[] {7,0,1,1}, new Insets(0, BORDER, 0, BORDER));
		
		MainGUIframe.addUIComponent(container, emptyPanel, GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL,
				new int[] {8,0,1,1}, new Insets(0, BORDER, 0, BORDER));
		
		MainGUIframe.addUIComponent(container, infoPanel, GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL,
				new int[] {9,0,2,1}, new Insets(0, BORDER, 0, BORDER));
		
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
