package kalah;

import java.util.ArrayList;

import com.qualitascorpus.testsupport.IO;

public class kalahGameController implements gameController {

	private IO io;
	private Printer printer;

	ArrayList<Player> players = new ArrayList<Player>();

	Player currentPlayer;
	private int move;
	boolean gameOver = false;

	public kalahGameController(IO io) {
		this.io = io;
		printer = new Printer(io);
		init();
		start();
	}

	@Override
	public void init() {
		players = new ArrayList<Player>();
		players.add(new Player(6,0));
		players.add(new Player(6,1));
		currentPlayer = players.get(0);
	}

	@Override
	public void start() {
		printer.printPlayboard(players);
		
		//game running
		game: while(!gameOver) {
			move = currentPlayer.makeMove(printer);
			if(move == -1) {
				gameOver = true;
				break game;
			}
			while(players.get(currentPlayer.getIdentifier()).getHouseSeedsCount(move) == 0) {
				printer.printPrompt("House is empty. Move again.");
				printer.printPlayboard(players);

				move = currentPlayer.makeMove(printer);
				if(move == -1) {
					gameOver = true;
					break game;
				}
			}
			sowing(move);
			ifgameOver();
			printer.printPlayboard(players);
		}

	//game over
	printer.printPrompt("Game over");
	printer.printPlayboard(players);
	if(move == -1) {

	}else {
		int player1_score = players.get(0).getScore();
		int player2_score = players.get(1).getScore();
		printer.gameEnd(getWinner(),player1_score,player2_score);
	}
	}

	private Player getOpponent(Player currentPlayer) {
		int CP_ID = currentPlayer.getIdentifier();
		switch(CP_ID){
		case 0: return players.get(1);
		default: return players.get(0);
		}
	}

	private void sowing(int houseNumber) {
		Pit lastSeed = searchLastSeed(currentPlayer.getHouse(houseNumber));

		switch(lastSeed.getIdentifier()) {
		case 0: 
			if(lastSeed.getowner() == currentPlayer) {lastSeed.addSeeds(1);}
			else{currentPlayer = getOpponent(currentPlayer);}
			break;
		default: 
			if(ifCaptured((House)lastSeed)){
				capture((House)lastSeed);
				currentPlayer = getOpponent(currentPlayer);
			}else {
				lastSeed.addSeeds(1);
				currentPlayer = getOpponent(currentPlayer);
			};
		}
	}

	private String getWinner() {
		int player1_score = players.get(0).getScore();
		int player2_score = players.get(1).getScore();	

		if(player1_score>player2_score) {
			return "Player 1 wins!" ;
		}else if(player1_score<player2_score){
			return "Player 2 wins!" ;
		}else {
			return "A tie!" ;
		}
	}

	private Pit searchLastSeed(House currentHouse){
		Player player = currentPlayer;
		int pitIdentifier = currentHouse.getIdentifier();
		int numberOfSeeds = currentPlayer.sow(pitIdentifier);
		Pit currentPit = currentHouse;
		while(numberOfSeeds > 0) {
			if(pitIdentifier == 0) {
				pitIdentifier++;
				player = getOpponent(player);
				currentPit = player.getHouse(pitIdentifier);
			}else if(pitIdentifier == 6) {
				pitIdentifier = 0;
				currentPit = player.getStore();
			}else {
				pitIdentifier++;
				currentPit = player.getHouse(pitIdentifier);
			}
			if(!(player != currentPlayer && currentPit.getIdentifier() == 0)) {
				if(numberOfSeeds != 1) {
					currentPit.addSeeds(1);
					numberOfSeeds-- ;
				}else if(numberOfSeeds == 1) {
					break;
				}
			}
		}	
		return currentPit;
	}

	private boolean ifCaptured(House house) {
		Player opponent = getOpponent(house.getowner());
		int oppositeHouseNumber = 7 - house.getIdentifier();
		if(house.getowner() == currentPlayer && house.getSeedsCount() == 0 && opponent.getHouse(oppositeHouseNumber).getSeedsCount()>0) {
			return true;
		}
		return false;
	}

	private void capture(House house) {
		Player opponent = getOpponent(house.getowner());
		House oppositeHouse = opponent.getHouse(7 - house.getIdentifier());
		house.getowner().getStore().addSeeds(oppositeHouse.getSeedsCount()+1);
		oppositeHouse.clearSeed();
	}

	private void ifgameOver() {
		for(int i = 1; i < 7; i++) {
			if(currentPlayer.getHouse(i).getSeedsCount()!= 0) {
				return;
			}
		}
		gameOver = true;
	}

}
