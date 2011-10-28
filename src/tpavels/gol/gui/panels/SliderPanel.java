package tpavels.gol.gui.panels;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import tpavels.gol.constants.Constants;
import tpavels.gol.core.Core;

public class SliderPanel extends JPanel implements ChangeListener , Constants {

	private static final int SPEED_INIT = (int) (TICK/10);
	private static final int SPEED_MAX = 50;
	private static final int SPEED_MIN = 0;
	private Core game = null;
	private JSlider speed = null;
	
	public SliderPanel(Core game) {
		this.game = game;
        speed = new JSlider(JSlider.HORIZONTAL, SPEED_MIN, SPEED_MAX, SPEED_INIT);
        speed.setPreferredSize(new Dimension(200, BUTTON_HEIGHT));
        speed.setMajorTickSpacing(10);
        speed.setPaintTicks(true);
        speed.addChangeListener(this);
        add(speed);
        speed.setFocusable(false);
	}
	
	public void reset() {
		speed.setValue(SPEED_INIT);
	}
	
	public void changeValue(int value){
		value = speed.getValue() + value;
		if (value >= SPEED_MIN && value <= SPEED_MAX ){
			speed.setValue(value);
		}
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		JSlider source = (JSlider)e.getSource();
		if (!source.getValueIsAdjusting()) {
			int delay = (int)source.getValue();
			if (delay == 0) delay = 1;
			long multipler = (long) (delay * 10); 
			game.setDelay(multipler);
		}

	}

	private static final long serialVersionUID = 7096663183655983942L;
}
