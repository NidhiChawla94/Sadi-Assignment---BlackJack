package client;

import javax.swing.SwingUtilities;

import controller.observable.EventObservable;
import controller.observable.EventObservableImpl;
import model.GameEngineImpl;
import model.interfaces.GameEngine;
import view.GameEngineCallbackGUIImpl;
import view.GameEngineCallbackImpl;
import view.MainFrame;

public class Main {

	public static void main(String [] args) {
		
		final GameEngine model = new GameEngineImpl();
		EventObservable eventObservable = new EventObservableImpl();
		model.addGameEngineCallback(new GameEngineCallbackImpl() );
		
		model.addGameEngineCallback(new GameEngineCallbackGUIImpl(eventObservable));
		
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				new MainFrame(model, eventObservable);
			}
		});
	}
}
