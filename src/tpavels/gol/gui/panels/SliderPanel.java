package tpavels.gol.gui.panels;

import java.awt.Dimension;
import java.util.Hashtable;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import tpavels.gol.constants.Constants;
import tpavels.gol.core.Core;

public class SliderPanel extends JPanel implements ChangeListener , Constants {

	private static final String MAX_LABEL = "Slow";
	private static final String MIN_LABEL = "Fast";

	private static final int SPEED_SLIDER_WIDTH = 200;
	
	private static final int SCALE = 10;
	private static final int SPEED_MAX = 50;
	private static final int SPEED_MIN = 2;
	private static final int SPEED_INIT = (int) (TICK/SCALE);
	
	private Core game = null;
	private JSlider speedSlider = null;
	
	public SliderPanel(Core game) {
		this.game = game;
        speedSlider = new JSlider(JSlider.HORIZONTAL, SPEED_MIN, SPEED_MAX, SPEED_INIT);
        speedSlider.setPreferredSize(new Dimension(SPEED_SLIDER_WIDTH, BUTTON_HEIGHT));
        speedSlider.addChangeListener(this);
        speedSlider.setInverted(true);
        speedSlider.setFocusable(false);
        speedSlider.setBackground(CONTROL_PANEL_COLOUR);
        this.setBackground(CONTROL_PANEL_COLOUR);
        customLabels(speedSlider);
        this.add(speedSlider);
	}

	private void customLabels(JSlider slider) {
		Hashtable<Integer, JLabel> labelTable = new Hashtable<Integer, JLabel>();
        JLabel minLabel = new JLabel(MIN_LABEL);
        minLabel.setForeground(TEXT_COLOUR);
        JLabel maxLabel = new JLabel(MAX_LABEL);
        maxLabel.setForeground(TEXT_COLOUR);
        
		labelTable.put(Integer.valueOf( SPEED_MIN ), minLabel);
		labelTable.put(Integer.valueOf( SPEED_MAX ), maxLabel);
        slider.setLabelTable( labelTable );
        slider.setPaintLabels(true);
	}
	
	public void reset() {
		speedSlider.setValue(SPEED_INIT);
	}
	
	/**
	 * Set new delay value, 
	 * from {@link #SPEED_MIN} to {@link #SPEED_MAX}, including.
	 * @param value to set
	 */
	public void changeValue(int value){
		value = speedSlider.getValue() + value;
		if (value >= SPEED_MIN && value <= SPEED_MAX ){
			speedSlider.setValue(value);
		}
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		JSlider source = (JSlider)e.getSource();
		if (!source.getValueIsAdjusting()) {
			int delay = (int)source.getValue();
			if (delay == 0) delay = 1; // minimum is 10ms delay
			long multipler = (long) (delay * SCALE); 
			game.setDelay(multipler);
		}

	}

	private static final long serialVersionUID = 7096663183655983942L;
}
