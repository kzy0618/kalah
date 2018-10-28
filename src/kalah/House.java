package kalah;

public class House extends Pit{
	
	
	public House(int houseNumber, Player player) {
		seeds = 4;
		owner = player;
		identifier = houseNumber;
	}
	
	public void clearSeed() {
		seeds = 0;
	}
	
}
