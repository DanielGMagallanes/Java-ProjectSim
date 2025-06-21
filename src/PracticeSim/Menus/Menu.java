package PracticeSim.Menus;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import PracticeSim.Game;
import PracticeSim.Game.STATE;
import PracticeSim.background.Handler;

public class Menu extends MouseAdapter{

	private Game game;
	private Handler handler;


	public Menu(Game game, Handler handler) {
		this.game = game;
		this.setHandler(handler);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();

		if(game.gameState == STATE.Menu) {
			//play button
			if(mouseOver(mx,my,440, 300, 400, 175)) {
				game.gameState = STATE.Creation;
			}

			//quit button
			if(mouseOver(mx,my,500, 550, 300, 125)) {
				System.exit(1);
			}
		}

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
		if(mx > x && mx < x + width) {
			if(my > y && my < y + height) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public void tick() {

	}

	public void render(Graphics g) {
		Font fnt = new Font("Times New Roman",1, 200);
		Font fnt2 = new Font("Times New Roman",1, 100);

		g.setFont(fnt);
		g.setColor(Color.RED);
		g.drawString("P.E.T.S.", 320, 230);

		g.setColor(Color.white);
		g.drawRect(440, 300, 400, 175);

		g.setFont(fnt2);
		g.setColor(Color.WHITE);
		g.drawString("Start", 530, 420);

		g.setColor(Color.white);
		g.drawRect(500, 550, 300, 125);

		g.setFont(fnt2);
		g.setColor(Color.WHITE);
		g.drawString("Quit", 550, 640);
	}

	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

}
