package tpavels.gol.gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import tpavels.gol.core.Core;

public class Controls implements ActionListener {
	
	Core game = null;

	/**
	 * Creates main controls, initialize frame with button
	 * @param game object of {@link Core} to operate
	 */
	public void startup(Core game){
		this.game = game;
		
		setupLookAndFeel();
		
		JFrame frame = new JFrame("Game of Live Controls");
		frame.setSize(360, 85);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setAlwaysOnTop(true);
		frame.setResizable(false);
		frame.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 12));
		
		JButton start, pause, random, reset;
		start = createButton("START");
		pause = createButton("PAUSE");
		random = createButton("RANDOM");
		reset = createButton("RESET");

		frame.add(start);
		frame.add(pause);
		frame.add(random);
		frame.add(reset);
		
		frame.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("START")){
			game.start();
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
		return button;
	}
	
	private void setupLookAndFeel() {
		String nativeLF = UIManager.getSystemLookAndFeelClassName();

	    try {
			UIManager.setLookAndFeel(nativeLF);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
	}

}
