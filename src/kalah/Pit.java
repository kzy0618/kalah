package kalah;

public class Pit {
	protected int seeds;
	protected Player owner;
	protected int identifier;
	
	protected void addSeeds(int numberOfSeeds) {
		seeds += numberOfSeeds;
	}
	
	public int getSeedsCount() {
		return seeds;
	}
	
	public Player getowner() {
		return owner;
	}	
	
	public int getIdentifier() {
		return identifier;
	}
}
