package kalah;

import java.util.ArrayList;

import com.qualitascorpus.testsupport.IO;

public class Player {

	private ArrayList<House> houses;
	private Store store;
	private int identifier;
	
	public Player(int numberOfHouses, int identifier) {
		store = new Store(this);
		houses = new ArrayList<House>();
		this.identifier = identifier;
		for (int i = 0; i < numberOfHouses; i++) {
			houses.add(new House(i+1,this));
		}
	}
	
	public int getHouseSeedsCount(int housesNumber) {
		return houses.get(housesNumber-1).getSeedsCount();
	}
	
	public int getStoreSeedsCount() {
		return store.getSeedsCount();
	}
	
	public int getIdentifier() {
		return identifier;
	}
	
	public int sow(int houseNumber) {
		houseNumber--;
		int numberOfSeeds = houses.get(houseNumber).getSeedsCount();
		houses.get(houseNumber).clearSeed();
		return numberOfSeeds;
	}

	public int getScore() {
		int score = 0;
		for(House house: houses) {
			score += house.getSeedsCount();
		}
		score += store.getSeedsCount();
		return score	;
	}
	
	public House getHouse(int houseNumber) {
		return houses.get(houseNumber-1);
	}
	
	public Store getStore() {
		return store;
	}
	
	public int makeMove(Printer printer) {
		int move = printer.requestInput(this);
		return move;
	}
	
	
}


