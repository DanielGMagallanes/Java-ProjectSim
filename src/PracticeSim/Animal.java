
package PracticeSim;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import PracticeSim.Assets.Assets;
import PracticeSim.background.GameObject;
import PracticeSim.background.ID;


public class Animal extends GameObject implements AnimalActions{

	private int emotion=10;
	private int size =25;
	protected String type;
	protected String name;
	protected String breed;
	private String action;
	//IDs are for counts
	protected final int dogID =0;
	protected final int catID =1;
	protected final int birdID =2;
	protected int ID=4;
	//actions when away and keeping track of user pets
	private boolean IsUserPet= false;
	private boolean IsNotActive = false;

	//for wild
	private boolean wild=false;

	private BufferedImage image;


	//there needs work to make sure the pets gets added to the background objects
	public Animal(String type,String breed,String name,ID id,int x, int y, BufferedImage img) {
		super(x,y,id);
		this.type = type;
		this.name= name;
		this.breed = breed;
		this.image = img;
		if(breed == "Dog") {
			ID = dogID;
		}
		else if(type == "Cat") {
			ID = catID;
		}
		else if(type == "Bird") {
			ID = birdID;
		}
		velX = 15;
		velY = 15;
	}
	//This is for wild animals
	public Animal(ID id,int x, int y) {
		super(x,y,id);
		wild = true;
		name="Squirrel";
		type="Squirrel";
		breed="Squirrel";

		velX = 15;
		velY = 15;

		image = Assets.squirrel;

	}
	public void stayedHome() {
		if(emotion <= 10 && emotion > 0) {
			emotion -= 1;
		}
		else {
			emotion=0;
		}

	}
	public void WentToPark() {
		if(emotion < 10 && emotion >= 0) {
			emotion += 1;
		}
		else {
			emotion=10;
		}
	}
	public void Hit() {
		if(emotion <= 10 && emotion > 0) {
			emotion -= 1;
		}
		else {
			emotion=0;
		}
	}
	public Integer getEmotion() {
		return emotion;
	}
	public void setEmotion(Integer emotion) {
		this.emotion = emotion;
	}
	public String getType() {
		return type;
	}
	public BufferedImage getAsset() {
		return image;
	}
	@Override
	public String getName() {
		return name;
	}
	public String getBreed() {
		return breed;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String act) {
		this.action = act;
	}
	@Override
	public boolean isFighting() {
		return fighting;
	}
	@Override
	public void setFighting(boolean fighting) {
		this.fighting = fighting;
	}
	public boolean isIsUserPet() {
		return IsUserPet;
	}
	public void setIsUserPet(boolean isUserPet) {
		IsUserPet = isUserPet;
	}
	public boolean isIsNotActive() {
		return IsNotActive;
	}
	public void setIsNotActive(boolean isNotActive) {
		IsNotActive = isNotActive;
	}
	public void GoodJob() {

		if(emotion < 10) {
			emotion += 1;
		}else {
			emotion = 10;
		}

	}

	@Override
	public void Fly() {

		if(emotion<=3) {
			action = getName() + " seems to be ignoring you";
		}
		else if(getType() == "Dog" || getType() == "Cat"){
			action= getName()+" looks at you Funny!";
		}
		else if(getType() == "Bird") {
			action = getName()+" took off and is flying around";
		}
		setAction(action);
	}

	@Override
	public void Catch() {
		if(emotion<=3) {
			action = getName() + " seems to be ignoring you";
		}
		else if(getType() == "Dog"){
			action= getName()+" trys to catch a squirrel.";
		}
		else if(getType() == "Bird") {
			action = getName()+" trys to catch something";
		}
		else if(getType() == "Cat") {
			action = getName() + " trys to catch a bird.";

		}
		setAction(action);
	}
	@Override
	public void Speak() {
		if(emotion<=3) {
			action = getName() + " seems to be ignoring you";
		}
		else if(getType() == "Dog"){
			action= getName()+" BARKS!!";
		}
		else if(getType() == "Bird") {
			action = getName()+" makes a noise";
		}
		else if(getType() == "Cat") {
			action = getName() + " MEOW!!";

		}
		setAction(action);
	}
	@Override
	public void Chase() {
		if(emotion<=3) {
			action = getName() + " seems to be ignoring you";
		}
		else if(getType() == "Bird" || getType() == "Cat"){
			action= getName()+" ignores you!";
		}
		else if(getType() == "Dog") {
			action = getName()+" catches a squirrel!";
		}
		setAction(action);
	}
	@Override
	public void Flee() {
		if(emotion >3){
			action= getName()+" does nothing.";
		}
		else {
			action = getName()+" runs aways and slowly comes back with head down.";
		}
		setAction(action);
	}
	@Override
	public void Bite() {
		if(emotion >3){
			action= getName()+" does nothing.";
		}
		else {
			action = getName()+" bites owner";
		}
		setAction(action);
	}
	@Override
	public void Play() {
		if(emotion<=3) {
			action = getName() + " seems to be ignoring you";
		}
		else {
			action= getName() + " is playing.";
		}
		setAction(action);
	}
	@Override
	public void Fetch() {
		if(emotion<=3) {
			action = getName() + " seems to be ignoring you";
		}
		else if(getType() == "Dog" || getType() == "Cat"){
			action= getName()+" looks at the directions and runs away from you.\nComes back with nothing";
		}
		else if(getType() == "Bird") {
			action = getName()+" flys away and circles.\nReturns to you.";
		}
		setAction(action);
	}
	@Override
	public void Lick() {
		if(emotion<=3) {
			action = getName() + " seems to be ignoring you";
		}
		else if(getType() == "Dog" || getType() == "Cat"){
			action= getName()+" is trying to lick your face!";
		}
		else if(getType() == "Bird") {
			action = getName()+" tongue isnt long enough.";
		}
		setAction(action);
	}
	@Override
	public void Rest() {
		if(emotion<=3) {
			action = getName() + " seems to be ignoring you";
		}
		else {
			action = getName()+" is resting";
		}
		setAction(action);
	}


	// for background boxes
	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, size, size);
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
		g.setColor(Color.red);
		g.fillRect(x, y, size, size);
	}
	@Override
	public void awayAction() {

		if(emotion >= 7) {
			setAwayAction(getName() + " is playing with ");
		}
		else if(emotion >=3 && emotion<7) {
			setAwayAction(getName() + " is walking slowly around with ");
			setFighting(false);
		}
		else if(emotion < 3) {
			setAwayAction(getName() + " is fighting with ");
			setFighting(true);
		}
		if(wild) {
			setAwayAction("The " + getName() + " is playing with ");
		}

	}
}