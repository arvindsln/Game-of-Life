package tpavels.gol.gui.panels;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import tpavels.gol.constants.Constants;
import tpavels.gol.field.Field;

public class InfoPanel extends JPanel implements Constants{


	private static final int FONT_SIZE = 12;
	private static final int LABEL_TEXT_SPACE = 30;

	private static final String NUMER_LABEL = "###";

	private static final String TOTTAL_CELLS_LABEL = "Total cells: ";
	private static final String ALIVE_CELLS_LABEL = "Alive cells: ";
	private static final String GENERATION_LABEL = "Generation: ";

	// to access label value in the list
	private static final int TOTAL_CELLS_ID = 3;
	private static final int ALIVE_CELLS_ID = 4;
	private static final int GENERATION_ID = 5;

	private List<JLabel> labels = null;

	public InfoPanel(Field field) {
		this.setBackground(CONTROL_PANEL_COLOUR);
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		this.labels = new ArrayList<JLabel>(4);

		// labels descriptions
		JPanel left = createPanel(CONTROL_PANEL_COLOUR, BoxLayout.Y_AXIS);
		JLabel totalCellsLabel = createLabel(TOTTAL_CELLS_LABEL, Component.RIGHT_ALIGNMENT);
		left.add(totalCellsLabel);
		JLabel aliveLabel = createLabel(ALIVE_CELLS_LABEL, Component.RIGHT_ALIGNMENT);
		left.add(aliveLabel);
		JLabel generationLabel = createLabel(GENERATION_LABEL, Component.RIGHT_ALIGNMENT);
		left.add(generationLabel);

		// labels values
		JPanel right = createPanel(CONTROL_PANEL_COLOUR, BoxLayout.Y_AXIS);
		JLabel totalCells = createLabel(String.valueOf(ROWS*COLS), Component.LEFT_ALIGNMENT);
		right.add(totalCells);
		JLabel aliveCells = createLabel(NUMER_LABEL, Component.LEFT_ALIGNMENT);
		right.add(aliveCells);
		JLabel generation = createLabel(NUMER_LABEL, Component.LEFT_ALIGNMENT);
		right.add(generation);

		this.add(left);
		this.add(right);

		setLabelsFont(labels);
	}

	private JPanel createPanel(Color colour, int axis){
		JPanel panel = new JPanel();
		panel.setBackground(colour);
		panel.setLayout(new BoxLayout(panel, axis));
		return panel;
	}

	private JLabel createLabel(final String text, final float aligment){
		JLabel label = new JLabel(text);
		label.setAlignmentX(aligment);
		Dimension d = label.getPreferredSize();  // ensures "fixed width" for label value
		label.setPreferredSize(new Dimension(d.width+LABEL_TEXT_SPACE,d.height));
		labels.add(label);
		return label;
	}

	private void setLabelsFont(final List<JLabel> labels) {
		Font font = new Font(FONT, Font.PLAIN, FONT_SIZE);
		for (JLabel label : labels) {
			label.setFont(font);
			label.setForeground(TEXT_COLOUR);
		}
	}

	public void updateLabels(Field field) {
		if (field != null) {
			labels.get(GENERATION_ID).setText(String.valueOf(field.getGeneration()));
			labels.get(ALIVE_CELLS_ID).setText(String.valueOf(field.getNumberOfAliveCells()));
		} else {
			System.err.println("No info for labels, field is null!");
		}
	}

	private static final long serialVersionUID = -3969657731488685081L;
}
