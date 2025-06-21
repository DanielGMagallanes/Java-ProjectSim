package PracticeSim;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import PracticeSim.background.GameObject;
import PracticeSim.background.Handler;
import PracticeSim.background.ID;

public class Player extends GameObject{

	Handler handler;
	private String ComName;
	public ArrayList<Animal> Cpets;
	private int size =25;

	public Player(int x, int y, ID id,String name,ArrayList<Animal> pets) {
		super(x, y, id);
		this.setComName(name);
		this.Cpets = pets;
		setCpetsSize(pets.size());
		velX = 15;
		velY = 15;
	}

	@Override
	public void tick() {
		x += velX;
		y += velY;

		if (y <= 0 || y >= Game.HEIGHT - 38) {
			velY *= -1;
		}
		if (x <= 0 || x >= Game.WIDTH - 300) {
			velX *= -1;
		}
	}


	@Override
	public void render(Graphics g) {
		g.setColor(Color.pink);
		g.fillRect(x, y, size, size);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x,y,size,size);
	}

	@Override
	public void awayAction() {
		setAwayAction("I am MAN and i shall play with you ");
	}

	@Override
	public String getName() {
		return ComName;
	}

	@Override
	public void setFighting(boolean e) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isFighting() {
		// TODO Auto-generated method stub
		return false;
	}

	public void setComName(String comName) {
		ComName = comName;
	}

}
