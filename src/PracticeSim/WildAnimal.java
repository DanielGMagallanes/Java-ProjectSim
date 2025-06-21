package PracticeSim;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import PracticeSim.Assets.Assets;
import PracticeSim.background.GameObject;
import PracticeSim.background.Handler;
import PracticeSim.background.ID;

public class WildAnimal extends GameObject{

	Handler handler;
	private int size= 30;
	public String name = "Squirrel";
	private BufferedImage image;
	private String action;

	public WildAnimal(int x, int y, ID id,Handler handler) {
		super(x, y, id);
		this.handler = handler;

		velX = 15;
		velY = 15;

		setImage(Assets.squirrel);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x,y,size,size);
	}

	@Override
	public void tick() {
		x += velX;
		y += velY;

		if(y <= 0 || y >= Game.HEIGHT-38) {
			velY *= -1;
		}
		if(x <= 0 || x >= Game.WIDTH-300) {
			velX *= -1;
		}
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect(x, y, size, size);
	}

	public void takeAction() {
		action = "exploring area.";
		setAction(action);
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	@Override
	public void awayAction() {
		setAwayAction("the "+getName() + " is play with another");
	}

	@Override
	public String getName() {
		return name;
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
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



}
