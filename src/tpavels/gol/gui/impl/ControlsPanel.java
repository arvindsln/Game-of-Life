package tpavels.gol.gui.impl;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import tpavels.gol.constants.Constants;
import tpavels.gol.core.Core;

public class ControlsPanel implements ActionListener, Constants{
	
	private Core game = null;
	
	public ControlsPanel(Core game) {
		this.game = game;
	}

	public void createControls(JPanel container){
		
		JButton start, pause, random, reset;
		start = createButton("START");
		pause = createButton("PAUSE");
		random = createButton("RANDOM");
		reset = createButton("RESET");
		
		MainGUIframe.addUIComponent(container, new JPanel(), GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
				new int[] {0,0,1,1}, new Insets(BORDER, BORDER, BORDER, BORDER));
		
		MainGUIframe.addUIComponent(container, start, GridBagConstraints.WEST, GridBagConstraints.NONE,
				new int[] {1,0,1,1}, new Insets(BORDER, BORDER, BORDER, BORDER));
		MainGUIframe.addUIComponent(container, pause, GridBagConstraints.WEST, GridBagConstraints.NONE,
				new int[] {2,0,1,1}, new Insets(BORDER, BORDER, BORDER, BORDER));
		MainGUIframe.addUIComponent(container, random, GridBagConstraints.WEST, GridBagConstraints.NONE,
				new int[] {3,0,1,1}, new Insets(BORDER, BORDER, BORDER, BORDER));
		MainGUIframe.addUIComponent(container, reset, GridBagConstraints.WEST, GridBagConstraints.NONE,
				new int[] {4,0,1,1}, new Insets(BORDER, BORDER, BORDER, BORDER));
		
		MainGUIframe.addUIComponent(container, new JPanel(), GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL,
				new int[] {5,0,1,1}, new Insets(BORDER, BORDER, BORDER, BORDER));
		
		for (int i = 0; i < container.getComponentCount(); i++) {
			JComponent c = (JComponent) container.getComponents()[i];
			System.out.println(c.getPreferredSize().width + " " + c.getPreferredSize().height);
			
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("START")){
			// game.start();
		}
		if (e.getActionCommand().equals("PAUSE")){
			game.pause();
		}
		if (e.getActionCommand().equals("RANDOM")){
			game.random();
		}
		if (e.getActionCommand().equals("RESET")){
			game.reset();
		}
	}

	private JButton createButton(String name) {
		JButton button = new JButton(name);
		button.setVisible(true);
		button.addActionListener(this);
		button.setPreferredSize(new Dimension(100, 30));
		button.setBorder(new EmptyBorder(BORDER, BORDER, BORDER, BORDER));
		return button;
	}
}
