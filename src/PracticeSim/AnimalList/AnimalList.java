package PracticeSim.AnimalList;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import PracticeSim.Animal;
import PracticeSim.Assets.Assets;
import PracticeSim.background.Handler;
import PracticeSim.background.ID;;


public class AnimalList {

	private Handler handler;
	private ArrayList<Animal> animalsInPark;

	private int countDog=0;
	private int countCat=0;
	private int countBird=0;
	private int countWild=0;
	private Font font= new Font("Times New Roman", 10, 25);
	private Color c = Color.white;
	private Color c2 = Color.blue;
	private Color c3 = Color.green;

	private int alcx = 900 + 70,
			alcy = 235 + 70,
			spacing = 25;
	private int AImageX = 1100,
			AImageWidth =  AImageX,
					AImageHeight = 17;
	private int ACountX = 1070, ACountY = 25;

	private int selectedAnimal = 0;

	public AnimalList(Handler handler) {
		this.handler = handler;
		animalsInPark = new ArrayList<>();

	}

	public void tick() {

		if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_W)) {
			selectedAnimal--;
		}
		if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_S)) {
			selectedAnimal++;
		}

		if (selectedAnimal < 0) {
			selectedAnimal = animalsInPark.size() - 1;
		}
		else if (selectedAnimal >= animalsInPark.size()) {
			selectedAnimal = 0;
		}

	}
	public void render(Graphics g) {
		g.drawImage(Assets.list, 895,0,300,600, null);

		Text.drawString(g, "Total Animals", alcx, 25, true, c, font);
		Text.drawString(g, "Dog Count", alcx-12, 55, true, c, font);
		Text.drawString(g, "Cat Count", alcx-16, 85, true, c, font);
		Text.drawString(g, "Bird Count", alcx-12, 115, true, c, font);
		Text.drawString(g, "Squirrels", alcx-24, 145, true, c, font);

		int len = animalsInPark.size();
		if(len == 0) {
			return;
		}

		for(int i = -5; i< 10; i++)
		{
			if(selectedAnimal + i <0 || selectedAnimal +i >= len) {
				continue;
			}
			if(i == 0) {
				Text.drawString(g,"->" + animalsInPark.get(selectedAnimal + i).getType(), alcx, alcy + i * spacing, true, c2, font);
			}else {
				Text.drawString(g, animalsInPark.get(selectedAnimal + i).getType(), alcx, alcy + i * spacing, true, c, font);

			}
		}


		Animal animal = animalsInPark.get(selectedAnimal);
		if(animal.getEmotion() >= 7) {
			c3 = Color.green;
		}else if(animal.getEmotion() > 3 && animal.getEmotion() < 7) {
			c3 = Color.DARK_GRAY;
		}
		else if(animal.getEmotion()>=0 && animal.getEmotion() <= 3) {
			c3 = Color.red;
		}
		g.drawImage(animal.getAsset(), AImageWidth, AImageHeight, null);
		Text.drawString(g,"\u2191 \u2191 \u2191 \u2191", ACountX+60, 95, true, c, font);
		Text.drawString(g,"Selected", ACountX+60, 125, true, c, font);
		Text.drawString(g, "E L",ACountX+65, 185, true, c, font);
		Text.drawString(g, animal.getEmotion().toString(), ACountX+62, 215, true, c, font);
		Text.drawString(g, animal.getName(), ACountX+60, 155, true, c, font);
		Text.drawString(g, Integer.toString(animalsInPark.size()), ACountX, ACountY, true, c, font);
		Text.drawString(g, Integer.toString(countDog), ACountX, 55, true, c, font);
		Text.drawString(g, Integer.toString(countCat), ACountX, 85, true, c, font);
		Text.drawString(g, Integer.toString(countBird), ACountX, 115, true, c, font);
		Text.drawString(g, Integer.toString(countWild), ACountX, 145, true, c, font);
		Text.drawString(g, animal.getEmotion().toString(), ACountX+62, 215, true, c3, font);

	}
	public void addAnimal(String type, String breed, String name, ID id,BufferedImage img) {
		animalsInPark.add(new Animal(type, breed, name, id,0,0,img));
	}
	public Animal getAnimal(int index) {
		return animalsInPark.get(index);
	}
	public void clearList() {
		animalsInPark.clear();
	}
	public int size() {
		return animalsInPark.size();
	}
	public void addToList(Animal a) {
		if(a.getType()=="Dog") {
			countDog++;
		}
		else if(a.getType()=="Cat") {
			countCat++;
		}
		else if(a.getType() == "Bird") {
			countBird++;
		}
		else if(a.getType() == "Squirrel") {
			countWild++;
		}
		animalsInPark.add(a);
	}
	public void GoingHomeFromPark() {
		handler.removePlayer();
		for(int i =0;i<animalsInPark.size();i++) {
			if(!animalsInPark.get(i).isIsUserPet()) {
				if(animalsInPark.get(i).getType()=="Dog") {
					countDog--;
				}
				else if(animalsInPark.get(i).getType()=="Cat") {
					countCat--;
				}
				else if(animalsInPark.get(i).getType() == "Bird") {
					countBird--;
				}
				else if(animalsInPark.get(i).getType() == "Squirrel") {
					countWild--;
				}
				handler.removeObject(animalsInPark.get(i));
				animalsInPark.remove(animalsInPark.get(i));
				i--;
			}
		}

	}
	public void WildOut() {
		int count = 0;
		for(int i =0;i<animalsInPark.size();i++) {
			if(count < 1 && animalsInPark.get(i).getId() == ID.WildAnimal) {
				animalsInPark.remove(animalsInPark.get(i));
				count++;
				countWild--;
			}
		}
	}

	public void PlayerPetOut(int playerPetSize) {
		int count = 0;
		for(int i =0;i<animalsInPark.size();i++) {
			if(count < playerPetSize && animalsInPark.get(i).getId() == ID.Pet) {
				if(animalsInPark.get(i).getType()=="Dog") {
					countDog--;
				}
				else if(animalsInPark.get(i).getType()=="Cat") {
					countCat--;
				}
				else if(animalsInPark.get(i).getType() == "Bird") {
					countBird--;
				}
				animalsInPark.remove(animalsInPark.get(i));
				count++;
			}
		}
	}

	public int getSizeofPark() {
		return animalsInPark.size();
	}

	public void reset() {
		animalsInPark.clear();
		countDog=0;
		countCat=0;
		countBird=0;
		countWild=0;
		selectedAnimal = 0;
	}

}
