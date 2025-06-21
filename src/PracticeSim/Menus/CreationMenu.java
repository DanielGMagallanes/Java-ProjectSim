package PracticeSim.Menus;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import PracticeSim.Animal;
import PracticeSim.Game;
import PracticeSim.Game.STATE;
import PracticeSim.humanOwner;
import PracticeSim.AnimalList.AnimalList;
import PracticeSim.Assets.Assets;
import PracticeSim.background.Handler;
import PracticeSim.background.ID;

public class CreationMenu extends MouseAdapter{

	private Game game;
	private Handler handler;
	private String name="";
	private String Petname="";
	private String PetType="";
	private String PetBreed="";
	private Random r;
	private ArrayList<Animal> pets;

	private int count = 0;
	public CreationMenu(Game game, Handler handler, AnimalList aList) {
		this.game = game;
		this.handler = handler;
		r = new Random();
		pets = new ArrayList<>();
	}



	@Override
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();

		if(game.gameState == STATE.Creation) {
			//play button
			if(mouseOver(mx,my,230, 230, 270, 80)) {
				getName();
				//game.gameState = STATE.Game;
			}
			if(pets.size()>=1) {
				// Start simulator
				if (mouseOver(mx, my, 960, 750, 215, 75)) {
					game.user = new humanOwner(name, pets);
					game.gameState = STATE.GameHome;
					game.window.sbar.setVisible(true);
					game.window.area.append(game.user.getHumanName() + " has woken up!\n");
					game.action.tick();
					addToHandler();

				}
			}


			//Add pet
			if(mouseOver(mx,my,720, 750, 180, 75)) {
				getPet();
			}

			//quit button
			if(mouseOver(mx,my,25, 750, 100, 75)) {
				System.exit(1);
			}
			//Random maker
			if(mouseOver(mx,my,135, 750, 200, 75)) {
				doRandom();
			}
		}

	}


	public void addToHandler() {
		for (Animal element : game.user.pets) {
			handler.addObject(element);

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
	public void doRandom() {
		pets.clear();
		count = 0;
		humanOwner temp = game.doingRandom();
		name = temp.getHumanName();
		for(int i=0;i<temp.pets.size();i++) {
			temp.pets.get(i).setId(ID.UserPet);
			temp.pets.get(i).setIsUserPet(true);
			temp.pets.get(i).setIsNotActive(true);
			pets.add(temp.pets.get(i));
			count++;
		}
	}

	public void render(Graphics g) {
		Font fnt = new Font("Times New Roman",1, 140);
		Font fnt2 = new Font("Times New Roman",1, 45);

		g.setFont(fnt);
		g.setColor(Color.RED);
		g.drawString("Creating Self", 220, 130);

		g.setColor(Color.white);
		g.drawRect(230, 230, 270, 80);


		g.setFont(fnt2);
		g.setColor(Color.WHITE);
		g.drawString("Enter Name:", 235,285);

		g.setFont(fnt2);
		g.setColor(Color.WHITE);
		g.drawString(name, 515,285);

		g.setColor(Color.white);
		g.drawRect(25, 750, 100, 75);

		g.setFont(fnt2);
		g.setColor(Color.WHITE);
		g.drawString("Quit", 30, 800);

		g.setColor(Color.white);
		g.drawRect(135, 750, 200, 75);

		g.setFont(fnt2);
		g.setColor(Color.WHITE);
		g.drawString("Random", 150, 800);


		if(pets.size()>=1) {
			g.setColor(Color.white);
			g.drawRect(960, 750, 215, 75);

			g.setFont(fnt2);
			g.setColor(Color.WHITE);
			g.drawString("Enter Sim", 975, 800);
		}

		if(count == 0) {
			g.setColor(Color.white);
			g.drawRect(720, 750, 180, 75);

			g.setFont(fnt2);
			g.setColor(Color.WHITE);
			g.drawString("Add Pet", 725, 800);

		}
		else if(count == 1) {
			g.setColor(Color.white);
			g.drawRect(720, 750, 180, 75);

			g.setFont(fnt2);
			g.setColor(Color.WHITE);
			g.drawString("Add Pet", 725, 800);

			g.setFont(fnt2);
			g.setColor(Color.WHITE);
			g.drawString(pets.get(0).getType() +" - "+ pets.get(0).getBreed() + ": " + pets.get(0).getName(), 235,385);
		}
		else if(count == 2) {
			g.setColor(Color.white);
			g.drawRect(720, 750, 180, 75);

			g.setFont(fnt2);
			g.setColor(Color.WHITE);
			g.drawString("Add Pet", 725, 800);

			g.setFont(fnt2);
			g.setColor(Color.WHITE);
			g.drawString(pets.get(0).getType() +" - "+ pets.get(0).getBreed() + ": " + pets.get(0).getName(), 235,385);

			g.setFont(fnt2);
			g.setColor(Color.WHITE);
			g.drawString(pets.get(1).getType() +" - "+ pets.get(1).getBreed() + ": " + pets.get(1).getName(), 235,485);

		}
		else if(count == 3) {
			g.setFont(fnt2);
			g.setColor(Color.WHITE);
			g.drawString(pets.get(0).getType() +" - "+ pets.get(0).getBreed() + ": " + pets.get(0).getName(), 235,385);

			g.setFont(fnt2);
			g.setColor(Color.WHITE);
			g.drawString(pets.get(1).getType() +" - "+ pets.get(1).getBreed() + ": " + pets.get(1).getName(), 235,485);

			g.setFont(fnt2);
			g.setColor(Color.WHITE);
			g.drawString(pets.get(2).getType() +" - "+ pets.get(2).getBreed() + ": " + pets.get(2).getName(), 235,585);
		}
	}

	public void getName() {
		String inStr="";
		do {
			inStr = JOptionPane.showInputDialog("What name to use?", "Nobody!");
		} while(inStr != null && inStr.equals(""));
		if(inStr != null) {
			name = inStr;
		}
	}
	public void makePet(String type, String breed, String name) {
		//making sure the user has the pet saved as well.
		BufferedImage picture = null;
		picture= getImage(breed);
		pets.add(new Animal(type,breed,name,ID.UserPet,r.nextInt(600),r.nextInt(900),picture));
		PetType ="";
		PetBreed="";
		Petname="";
	}
	public BufferedImage getImage(String e) {
		BufferedImage pic=null;
		if(e=="Pitbull") {
			pic = Assets.Pitbull;
		}
		else if(e == "Rat Terrier") {
			pic = Assets.RatTerrier;
		}
		else if(e == "Poodle") {
			pic = Assets.Poodle;
		}
		else if(e == "Parakeet") {
			pic = Assets.Parakeet;
		}
		else if(e == "Parrot") {
			pic = Assets.Parrot;
		}
		else if(e == "Hawk") {
			pic = Assets.Hawk;
		}
		else if(e == "Pigeon") {
			pic = Assets.Pigeon;
		}
		else if(e == "Sphinx") {
			pic = Assets.Sphinx;
		}
		else if(e == "Persian") {
			pic = Assets.Persian;
		}
		else if(e == "Tabicat") {
			pic = Assets.Tabicat;
		}
		else if(e == "Siamese") {
			pic = Assets.Siamese;
		}
		else if(e == "Maltese") {
			pic = Assets.Maltese;
		}
		else if(e == "Husky") {
			pic = Assets.Husky;
		}
		return pic;
	}

	public void getPet() {
		String[] type = {"Dog", "Cat", "Bird"};
		String[] breedD = {"Pitbull", "Rat Terrier", "Poodle", "Husky", "Maltese"};
		String[] breedC = {"Siamese", "Tabicat", "Persian", "Sphinx"};
		String[] breedB = {"Pigeon", "Hawk", "Parrot", "Parakeet"};

		ImageIcon dog= new ImageIcon(Assets.dog);
		 ImageIcon cat= new ImageIcon(Assets.cat);
		 ImageIcon bird= new ImageIcon(Assets.bird);

		PetType = (String) JOptionPane.showInputDialog(null, "Select Type ","Pet Creation", JOptionPane.CLOSED_OPTION, null, type, "Dog");
		if(PetType != null) {
			if(PetType == "Dog") {
				PetBreed = (String) JOptionPane.showInputDialog(null, "Select Type ","Pet Creation", JOptionPane.CLOSED_OPTION, dog, breedD, "Pitbull");
				if(PetBreed != null) {
					Petname = getPETName();
					if(Petname != null) {
						count +=1;
						makePet(PetType,PetBreed,Petname);
					}
				}
			}
			else if(PetType == "Cat") {
				PetBreed = (String) JOptionPane.showInputDialog(null, "Select Type ","Pet Creation", JOptionPane.CLOSED_OPTION, cat, breedC, "Siamese");
				if(PetBreed != null) {
					Petname = getPETName();
					if(Petname != null) {
						count +=1;
						makePet(PetType,PetBreed,Petname);
					}
				}
			}
			else if(PetType == "Bird") {
				PetBreed = (String) JOptionPane.showInputDialog(null, "Select Type ","Pet Creation", JOptionPane.CLOSED_OPTION, bird, breedB, "Pigeon");
				if(PetBreed != null) {
					Petname = getPETName();
					if(Petname != null) {
						count +=1;
						makePet(PetType,PetBreed,Petname);
					}
				}
			}
		}
	}
	public String getPETName() {
		String nametemp = "";

		do {
			nametemp = JOptionPane.showInputDialog(null, "What name do you want to use?", "Input Dialog", JOptionPane.CLOSED_OPTION);
		} while(nametemp != null && nametemp.equals(""));
		if(nametemp != null) {
			return nametemp;
		}
		return null;
	}

	public void reset() {
		pets.clear();
		count = 0;
		name = "";
	}


}