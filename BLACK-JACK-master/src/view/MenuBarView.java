package view;

import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import controller.AddPlayerMenuItemListener;
import controller.QuitMenuItemListner;
import controller.observable.EventObservable;
import model.interfaces.GameEngine;

/**
 * @author nidhi chawla
 *
 *Menu bar of the application added to the north of Main Frame
 */
@SuppressWarnings("serial")
public class MenuBarView extends JMenuBar {
	public MenuBarView(GameEngine model, EventObservable modelObservable) {
		
		JMenu menu = new JMenu("Menu");
		menu.setMnemonic(KeyEvent.VK_O);
		JMenuItem addNewPlayer = new JMenuItem("Add New Player");
		addNewPlayer.addActionListener(new AddPlayerMenuItemListener(model, modelObservable));
		menu.add(addNewPlayer);
		
		JMenuItem quit = new JMenuItem("Quit");
		quit.addActionListener(new QuitMenuItemListner());
		menu.add(quit);
		add(menu);

	}
}
