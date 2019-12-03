package view;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import controller.observable.EventObservable;
import model.interfaces.GameEngine;

/**
 * @author nidhi chawla
 * The main view of the application with all different GUI components added to different sections of the Border Layout.
 */
@SuppressWarnings("serial")
public class MainFrame extends JFrame {
	private BorderLayout borderLayout;
	public MainFrame(GameEngine model, EventObservable eventObservable) {

		super();
		borderLayout = new BorderLayout();
		setBounds(0, 0, 1200, 900);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(borderLayout);

		MenuBarView menuBarView = new MenuBarView(model, eventObservable);
		setJMenuBar(menuBarView);
		menuBarView.setVisible(true);

		BlackJackBoard blackJackBoard = new BlackJackBoard(model, eventObservable);
		getContentPane().add(blackJackBoard, BorderLayout.CENTER);

		SummaryPanel summaryPanel = new SummaryPanel(model, eventObservable);
		getContentPane().add(summaryPanel, BorderLayout.EAST);

		StatusBar statusBarLayout = new StatusBar(model, eventObservable);
		getContentPane().add(statusBarLayout, BorderLayout.SOUTH);

		setVisible(true);
	}

}
