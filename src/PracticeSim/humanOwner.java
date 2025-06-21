package PracticeSim;

import java.util.ArrayList;

public class humanOwner {

	private String humanName;
	public ArrayList<Animal> pets;
	public Animal activePet;
	private int report = 2;

	public humanOwner(String name, ArrayList<Animal> pets) {
		this.humanName = name;
		this.pets = pets;
	}

	public humanOwner(String name,Animal pet) {
		this.humanName = name;
		this.activePet = pet;
	}

	public void addPet(Animal e) {
		pets.add(e);
	}
	public void setHumanName(String humanName) {
		this.humanName = humanName;
	}
	public String getHumanName() {
		return humanName;
	}
	public void changeActivePet() {

	}
	public void setActivePet(Animal pet) {
		activePet = pet;
	}
	public ArrayList<Animal> getPets() {
		return pets;
	}
	public void HitAnimal() {
		activePet.Hit();
	}
	public void reportMade() {
		report -= 1;
	}
	public void StayHome() {
		for (Animal pet : pets) {
			pet.stayedHome();
		}
	}
	public void WentToPark() {
		for (Animal pet : pets) {
			pet.WentToPark();
		}
	}

	public int getReport() {
		return report;
	}
	public void reset() {
		this.humanName = "";
		pets.clear();
		activePet=null;
	}
}
