package PracticeSim.background;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter{

	@SuppressWarnings("unused")
	private Handler handler;

	public KeyInput(Handler handler) {
		this.handler = handler;
	}


	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_ESCAPE) {
			System.exit(1);
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		@SuppressWarnings("unused")
		int key = e.getKeyCode();
	}


}
