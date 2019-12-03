package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author nidhi chawla
 *
 */
public class QuitMenuItemListner implements ActionListener {

	/**
	 * Closes the frame 
	 * */
	@Override
	public void actionPerformed(ActionEvent e) {
		System.exit(0);
	}

}
