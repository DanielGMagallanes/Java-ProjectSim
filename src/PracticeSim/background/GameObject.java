package PracticeSim.background;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject {

	protected int x, y;
	protected ID id;
	protected int velX, velY;
	public String awayaction;
	protected boolean fighting;
	private int CpetsSize;

	public GameObject(int x, int y,ID id) {
		this.x = x;
		this.y = y;
		this.id = id;
		awayaction="";
		fighting = false;
	}

	public abstract void tick();
	public abstract void render(Graphics g);
	public abstract Rectangle getBounds();
	public abstract void awayAction();
	public abstract String getName();
	public abstract void setFighting(boolean e);
	public abstract boolean isFighting();

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getVelX() {
		return velX;
	}

	public void setVelX(int velX) {
		this.velX = velX;
	}

	public int getVelY() {
		return velY;
	}

	public void setVelY(int velY) {
		this.velY = velY;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public ID getId() {
		return id;
	}

	public void setId(ID id) {
		this.id = id;
	}
	public String getAwayAction() {
		return awayaction;
	}
	public void setAwayAction(String action) {
		this.awayaction = action;
	}

	public int getCpetsSize() {
		return CpetsSize;
	}

	public void setCpetsSize(int cpetsSize) {
		CpetsSize = cpetsSize;
	}
}
