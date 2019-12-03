package controller;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;

import controller.observable.EventObservable;
import model.interfaces.Player;

public class SelectedPlayerChangeListener implements ItemListener{

	private JComboBox<Player> playerIdcomboBox;
	private EventObservable viewModel;
	public SelectedPlayerChangeListener(JComboBox<Player> playerIdcomboBox, EventObservable viewModel) {
		this.playerIdcomboBox = playerIdcomboBox;
		this.viewModel = viewModel;
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		Player selectedPlayer = playerIdcomboBox.getItemAt(playerIdcomboBox.getSelectedIndex());
		viewModel.updateSelectedPlayerObserver(selectedPlayer);
	}
}
