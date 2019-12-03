package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import controller.observable.EventObservable;
import model.interfaces.GameEngine;

/**
 * @author nidhi chawla
 *
 */
@SuppressWarnings("serial")
public class SummaryPanel extends JPanel {
	private GameEngine model;
	private EventObservable modelObservable;

	/**
	 * @param model - instance of model
	 * @param modelObservable - instance of the observable 
	 */
	public SummaryPanel(GameEngine model, EventObservable modelObservable) {
		this.model = model;
		this.modelObservable = modelObservable;
		setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		setBounds(613, 0, 291, 631);
		setLayout(new BorderLayout());
		populateUI();

	}

	/**
	 * Create panel for the game summary and player summary.
	 */
	private void populateUI() {

		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		JLabel summaryLabel = new JLabel("Summary");
		summaryLabel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
		panel.add(summaryLabel);

		JPanel summary = new JPanel();
		add(summary, BorderLayout.CENTER);
		summary.setLayout(new BoxLayout(summary, BoxLayout.Y_AXIS));

		JPanel gameSummaryPanel = new JPanel();
		summary.add(gameSummaryPanel);
		gameSummaryPanel.add(new GameSummary(modelObservable));

		
		JPanel playerSummaryPanel = new JPanel();
		summary.add(playerSummaryPanel);
		playerSummaryPanel.add(new PlayersSummary(model, modelObservable));
		revalidate();
	}
}
