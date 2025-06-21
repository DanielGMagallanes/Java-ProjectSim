package PracticeSim.background;

import java.awt.Graphics;
import java.util.LinkedList;

import PracticeSim.Animal;
import PracticeSim.Game;
import PracticeSim.Game.STATE;
import PracticeSim.Player;

public class Handler {

	public LinkedList<GameObject> object = new LinkedList<>();
	private Game game;
	public Handler(Game game) {
		this.game = game;
	}

	public void tick() {
		for (GameObject tempObject : object) {
			tempObject.tick();
		}

	}
	public KeyManager getKeyManager() {
		return game.getKeyManager();
	}

	public void render(Graphics g) {
		for (GameObject tempObject : object) {
			tempObject.render(g);
		}
	}

	public void addObject(GameObject object) {
		this.object.add(object);
		game.addtoAList((Animal) object);
	}
	public void addPlayer(Player ob) {
		this.object.add(ob);
	}
	public void removePlayer() {
		for(int i =0; i<object.size();i++) {
			if(object.get(i).getId() == ID.player) {
				object.remove(i);
			}
		}
	}

	public void removeObject(GameObject object) {
		this.object.remove(object);
	}

	public STATE getGameState() {
		return game.gameState;
	}

	public STATE setGameState() {
		return game.gameState = STATE.GamePark;
	}

	public void removeWild() {
		game.WildOut();
		int count = 0;
		for(int i =0; i<object.size();i++) {
			if(count < 1) {
				if(object.get(i).getId() == ID.WildAnimal) {
					object.remove(i);
					count++;
				}
			}

		}
	}

	public void removePlayerandPets() {
		int count = 0;
		boolean next = false;
		int size = 0;
		for (GameObject element : object) {
			if(count < 1) {
				if(element.getId() == ID.player) {
					game.PlayerPetOut(element.getCpetsSize());
					size = element.getCpetsSize();
					count++;
					next=true;
				}
			}
		}
		removePlayer();
		for(int i =0; i<object.size();i++) {
			if(size > 0 && object.get(i).getId() ==ID.Pet) {
				removeObject(object.get(i));
				size--;
				i--;
			}
		}
	}

	public void addTextToArea(String str) {
		game.window.area.append(str+"\n");
	}
}
