package PracticeSim;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.text.DefaultCaret;

import PracticeSim.AnimalList.AnimalList;
import PracticeSim.Assets.Assets;
import PracticeSim.Menus.CreationMenu;
import PracticeSim.Menus.Menu;
import PracticeSim.Menus.deathState;
import PracticeSim.actions.ActionSection;
import PracticeSim.background.GameObject;
import PracticeSim.background.Handler;
import PracticeSim.background.ID;
import PracticeSim.background.KeyManager;
import PracticeSim.background.Spawn;
import PracticeSim.background.Time;


public class Game extends Canvas implements Runnable{

	private static final long serialVersionUID = -5495016350964169983L;
	public static final int WIDTH = 1200, HEIGHT = WIDTH / 12 * 9;
	//this will hold the users info and do all the actions with names.
	public humanOwner user;

	public Thread thread;
	private boolean running =false;
	private Random r;
	public Window window;
	private Handler handler;
	private AnimalList aList;
	public ActionSection action;

	private int fightingCountHome = 0;

	private boolean picked=false;
	private boolean pickedPark = false;
	private KeyManager keyManager;

	private Graphics g;

	private Spawn spawn;
	private Time time;

	private Menu menu;
	private CreationMenu creation;
	private deathState death;
	public enum STATE{
		Menu, Creation, GameHome, GamePark, Death
	}

	public STATE gameState = STATE.Menu;

	public Game() {
		handler = new Handler(this);
		death = new deathState(this);
		menu = new Menu(this,handler);
		aList = new AnimalList(handler);
		creation = new CreationMenu(this,handler, aList);
		keyManager = new KeyManager();

		this.addMouseListener(menu);
		this.addMouseListener(creation);
		this.addMouseListener(death);


		window = new Window(WIDTH, HEIGHT, "PETS Simulator", this);
		this.addKeyListener(getKeyManager());

		action = new ActionSection(this);

		time = new Time();
		spawn = new Spawn(handler,aList,time);

		setR(new Random());

	}
	public void WildOut() {
		aList.WildOut();
	}

	public void PlayerPetOut(int num) {
		aList.PlayerPetOut(num);
	}

	public synchronized  void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
		Assets.init();
	}

	public synchronized  void stop() {
		try {
			thread.join();
			running = false;
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		this.requestFocus();
		int fps = 60;
		double timePerTick= 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		while(running) {
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;

			if(delta >=1) {
				tick();
				render();
				delta--;
			}
			if(timer >= 1000000000) {
				timer = 0;
			}
		}
		stop();
	}

	private void tick() {
		handler.tick();
		keyManager.tick();

		if(gameState == STATE.GameHome) {
			if(user.getReport()==0) {
				gameState = STATE.Death;
				window.sbar.setVisible(false);
			}
			aList.tick();
			addTextArea();
			time.addMin(1);
			collision2();
			newVisable();
		}
		else if(gameState == STATE.GamePark) {
			if(user.getReport() == 0) {
				gameState = STATE.Death;
				window.sbar.setVisible(false);
			}
			aList.tick();
			spawn.tick();
			time.addMin(1);
			if(user.pets.size()>=1 && !picked && getOpenHour() >=7) {
				pickAanimal();
			}
			collision();
			newVisable();
		}
		else if(gameState == STATE.Menu) {
			menu.tick();
		}
		else if(gameState == STATE.Creation) {
			creation.tick();
		}
		else if(gameState == STATE.Death) {
			death.tick();
		}

	}

	private void render() {

		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();

		g.clearRect(0, 0, WIDTH, HEIGHT);

		handler.render(g);

		if(gameState == STATE.GameHome) {

			g.drawImage(Assets.home, 0, 0, null);
			aList.render(g);
			action.render(g);
			time.render(g);

		}
		else if(gameState == STATE.GamePark) {

			g.drawImage(Assets.park, 0, 0, null);
			aList.render(g);
			action.render(g);
			time.render(g);

		}
		else if(gameState == STATE.Menu) {
			g.setColor(Color.black);
			g.fillRect(0, 0, WIDTH, HEIGHT);
			menu.render(g);
		}
		else if(gameState == STATE.Creation) {
			g.setColor(Color.black);
			g.fillRect(0, 0, WIDTH, HEIGHT);
			creation.render(g);
		}
		else if(gameState == STATE.Death) {
			g.setColor(Color.black);
			g.fillRect(0, 0, WIDTH, HEIGHT);
			death.render(g);
		}

		g.dispose();
		bs.show();

	}

	public humanOwner doingRandom() {
		return spawn.getComHuman();
	}

	public void setPicked(boolean picked) {
		this.picked = picked;
	}

	public void collision() {
		for (int i = 0; i < handler.object.size()-1; i++) {
			for(int j = i+1;j<handler.object.size();j++) {
				GameObject tempObject = handler.object.get(i);
				GameObject tempObject2 = handler.object.get(j);

				if (tempObject.getId() == ID.player && tempObject2.getId() == ID.UserPet ||
						tempObject.getId() == ID.player && tempObject2.getId() == ID.Pet ||
						tempObject.getId() == ID.player && tempObject2.getId() == ID.WildAnimal) {
					if (tempObject2.getBounds().intersects(tempObject.getBounds())) {
						window.area.append(tempObject.getName()+" is playing with " + tempObject2.getName() + "\n");
					}
					else {
					}
				}

				if (tempObject.getId() == ID.UserPet && tempObject2.getId() == ID.WildAnimal || tempObject.getId() == ID.WildAnimal && tempObject2.getId() == ID.UserPet) {
					if (tempObject2.getBounds().intersects(tempObject.getBounds())) {
						tempObject.awayAction();
						tempObject2.awayAction();
						window.area.append(tempObject.getAwayAction()+" "+tempObject2.getName()+"\n");
						window.area.append(tempObject2.getAwayAction()+" "+tempObject.getName()+"\n");
					}
					else {
					}
				}
				if (tempObject.getId() == ID.UserPet && tempObject2.getId() == ID.UserPet || tempObject.getId() == ID.UserPet && tempObject2.getId() == ID.UserPet) {
					if (tempObject2.getBounds().intersects(tempObject.getBounds())) {
						tempObject.awayAction();
						tempObject2.awayAction();
						window.area.append(tempObject.getAwayAction()+" "+tempObject2.getName()+"\n");
						window.area.append(tempObject2.getAwayAction()+" "+tempObject.getName()+"\n");
						if (tempObject.isFighting()) {
							Thread child = new Thread() {
								@Override
								public void run() {
									fightingResponse((Animal) tempObject);
								}
							};
							child.start();
							}
					}
					else {
					}
				}

				if (tempObject.getId() == ID.UserPet && tempObject2.getId() == ID.Pet || tempObject.getId() == ID.Pet && tempObject2.getId() == ID.UserPet) {
					if (tempObject2.getBounds().intersects(tempObject.getBounds())) {
						tempObject.awayAction();
						tempObject2.awayAction();
						window.area.append(tempObject.getAwayAction()+" "+tempObject2.getName()+"\n");
						window.area.append(tempObject2.getAwayAction()+" "+tempObject.getName()+"\n");
						if (tempObject.isFighting()) {
							Thread child = new Thread() {
								@Override
								public void run() {
									fightingResponse((Animal) tempObject);
								}
							};
							child.start();
						}
					}
					else {
					}
				}
			}
		}
	}

	public void collision2() {
		for (int i = 0; i < handler.object.size() - 1; i++) {
			for (int j = i + 1; j < handler.object.size(); j++) {
				GameObject tempObject = handler.object.get(i);
				GameObject tempObject2 = handler.object.get(j);

				if(fightingCountHome <= 2){
					if (tempObject2.getBounds().intersects(tempObject.getBounds())) {
						// collision code

						tempObject.awayAction();
						tempObject2.awayAction();
						window.area.append(tempObject.getAwayAction() + " " + tempObject2.getName() + "\n");
						window.area.append(tempObject2.getAwayAction() + " " + tempObject.getName() + "\n");

						if (tempObject.isFighting()) {
							Thread child = new Thread() {
								@Override
								public void run() {
									fightingResponse((Animal) tempObject);
									fightingCountHome++;
								}
							};
							child.start();
						}

					}
				}else {
					int ran= r.nextInt(100);
					if (tempObject2.getBounds().intersects(tempObject.getBounds()) && ran >= 20 && ran <= 45) {
						window.area.append(tempObject2.getName() + " is in its carrying case.\n");
						window.area.append(tempObject.getName() + " is in its carrying case.\n");
					}
				}

			}
		}
	}

	public void fightingResponse(Animal e) {
		String[] options= {"Good Job!","Do Nothing", "Hit and say NO!"};

		int choice = JOptionPane.showOptionDialog(null, "Your animal is Fighting with another animal.",
				"Fight!", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null,
				options, options[0]);
		if(choice == 0) {
			e.Hit();
		}
		else if(choice == 2) {
			e.GoodJob();
			if(gameState == STATE.GamePark) {
				user.reportMade();
			}
		}

	}

	public void pickAanimal() {
		if(user.pets.size()==1) {
			user.activePet = user.pets.get(0);
			user.activePet.setIsUserPet(true);
			user.activePet.setId(ID.ActivePet);
		}
		else {
			Object[] options = new Object[user.pets.size()];
			for (int i = 0; i < user.pets.size(); i++) {
				options[i] = user.pets.get(i).getName();
				user.pets.get(i).setIsUserPet(true);
				user.pets.get(i).setIsNotActive(true);
			}
			int choice = JOptionPane.showOptionDialog(null, "Which animal would you like to play with?",
					"Chose who to play with?", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null,
					options, options[0]);
			if(choice == -1) {
				gameState = STATE.GameHome;
				window.ReactionPanel.setVisible(false);
				window.ParkActionPanel.setVisible(false);
				action.tick();
				tick();
			}
			else {
				user.activePet = user.pets.get(choice);
				user.activePet.setIsNotActive(false);
				user.activePet.setId(ID.ActivePet);
				picked = true;
			}
		}
	}

	public void pickAanimalAtPark() {
		if(user.activePet.isIsUserPet()){
			user.activePet.setId(ID.UserPet);
			user.activePet.setIsNotActive(true);
		}else if(user.activePet.getName() == "Squirrel") {
			user.activePet.setId(ID.WildAnimal);
		}
		else {
			user.activePet.setId(ID.Pet);
		}


		Object[] options = new Object[aList.getSizeofPark()];
		for (int i = 0; i < aList.getSizeofPark(); i++) {
			options[i] = aList.getAnimal(i).getName();
		}
		int choice = JOptionPane.showOptionDialog(null, "Which animal would you like to play with?",
				"Chose who to play with?", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null,
				options, options[0]);


		user.activePet = aList.getAnimal(choice);
		if(user.activePet.getId() == ID.UserPet) {
			user.activePet.setIsNotActive(false);
			user.activePet.setId(ID.ActivePet);
		}
		else {
			user.activePet.setId(ID.ActivePet);
		}



		//picked = true;
	}

	public void newVisable() {
		window.area.setCaretPosition(window.area.getDocument().getLength());
	}

	public static int clamp(int var, int min, int max) {
		if(var >= max) {
			return var = max;
		}
		else if(var <= min) {
			return var = min;
		}
		else {
			return var;
		}
	}
	public Window getWindow() {
		return window;
	}

	public KeyManager getKeyManager() {
		return keyManager;
	}
	public humanOwner getHuman() {
		return user;
	}
	public void addTextArea() {
		JTextArea textarea = new JTextArea(5,25);
		textarea.setEditable(false);
		textarea.setBackground(Color.gray);
		DefaultCaret caret = (DefaultCaret) textarea.getCaret();
		caret.setUpdatePolicy(DefaultCaret.NEVER_UPDATE);
		JScrollPane scrollBar = new JScrollPane(textarea);
		scrollBar.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		window.getFrame().getContentPane().add(scrollBar);

	}

	public void addtoAList(Animal a) {
		aList.addToList(a);
	}

	public void leavingPark() {
		aList.GoingHomeFromPark();
	}

	public int getOpenHour() {
		return time.getMILHour();
	}

	public int getOpenMin() {
		return time.getMinutes();
	}
	public int getAmOrPm() {
		return time.getAmOrPm();
	}

	public void changeTime(int h, int m) {
		time.changeTime(h, m);
	}
	public void reset() {
		handler.object.clear();
		user.reset();
		aList.reset();
		creation.reset();
		window.area.setText("A new Day has come\n");
		gameState = STATE.Menu;
	}

	public static void main(String args[]) {
		new Game();
	}
	public Random getR() {
		return r;
	}
	public void setR(Random r) {
		this.r = r;
	}

}
