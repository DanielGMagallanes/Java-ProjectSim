package PracticeSim.Menus;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import PracticeSim.Game;
import PracticeSim.Game.STATE;
import PracticeSim.Assets.Assets;

public class deathState extends MouseAdapter{

	private Game game;
	public deathState(Game game) {
		this.game = game;
	}
	@Override
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();

		if(game.gameState == STATE.Death) {
			//play button
			if(mouseOver(mx,my,135, 750, 200, 755)) {
				game.reset();
			}

			//quit button
			if(mouseOver(mx,my,25, 750, 100, 75)) {
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
		Font fnt = new Font("Times New Roman",1, 60);
		Font fnt2 = new Font("Times New Roman",1, 45);
		g.drawImage(Assets.death, 700, 70, null);

		g.setFont(fnt);
		g.setColor(Color.RED);
		g.drawString("You have come to Death.", 10, 50);
		g.drawString("Your actions have", 10, 100);
		g.drawString("brought you into Deaths", 10, 150);
		g.drawString("domain but Death says,", 10, 200);
		g.drawString("'It is not your time", 10, 250);
		g.drawString("to die but be warned. If ", 10, 300);
		g.drawString("you keep treating animals", 10, 350);
		g.drawString("the way you have, then ", 10, 400);
		g.drawString("I may have to come ", 10, 450);
		g.drawString("get you before your time", 10, 500);
		g.drawString(" is up' Death turns.", 10, 550);
		g.drawString("Death is turning you away", 10, 600);
		g.drawString("take this chance to ", 10, 650);
		g.drawString("live in peace with animals", 10, 700);


		g.setColor(Color.white);
		g.drawRect(135, 750, 200, 75);

		g.setFont(fnt2);
		g.setColor(Color.WHITE);
		g.drawString("Restart", 150, 800);

		g.setColor(Color.white);
		g.drawRect(25, 750, 100, 75);

		g.setFont(fnt2);
		g.setColor(Color.WHITE);
		g.drawString("Quit", 30, 800);
	}


}
