package view;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import model.interfaces.Player;

/**
 * @author nidhi chawla
 *	JCombobox renderer for the players in toolbar and place bet dialog
 */
@SuppressWarnings("serial")
public class PlayerListCellRenderer extends JLabel implements ListCellRenderer<Player> {

	private Color defaultBackground;
	
	public PlayerListCellRenderer() {
		defaultBackground = getBackground();
		setOpaque(true);
	}
	@Override
	public Component getListCellRendererComponent(JList<? extends Player> list, Player value, int index,
			boolean isSelected, boolean cellHasFocus) {
		setText(value.getPlayerName());
		setBackground(isSelected ? Color.LIGHT_GRAY : defaultBackground);
		return this;
	}

}
