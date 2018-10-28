package kalah;

import java.util.ArrayList;

import com.qualitascorpus.testsupport.IO;

public class Printer {
	private IO io;
	
	public Printer(IO io) {
		this.io = io;
	}
	
	public void printPlayboard(ArrayList<Player> players) {
		io.println("+----+-------+-------+-------+-------+-------+-------+----+");
		
		String gap =" ";
		String prompt = "| P2 |";
		for(int i = 6; i > 0; i--) {
			if(players.get(1).getHouseSeedsCount(i) > 9) {
				gap = "";
			}else {
				gap = " ";
			}
			prompt = prompt + " "+  i + "[" + gap + players.get(1).getHouseSeedsCount(i) +"] |";
		}
		if(players.get(0).getStoreSeedsCount() > 9) {
			gap = "";
		}else {
			gap = " ";
		}
		prompt = prompt + " "+ gap + players.get(0).getStoreSeedsCount()+" |";
		io.println(prompt);
		
		io.println("|    |-------+-------+-------+-------+-------+-------|    |");
		
		if(players.get(1).getStoreSeedsCount() > 9) {
			gap = "";
		}else {
			gap = " ";
		}
		prompt = "| "+ gap + players.get(1).getStoreSeedsCount()+" |";
		for(int i = 1; i < 7; i++) {
			if(players.get(0).getHouseSeedsCount(i) > 9) {
				gap = "";
			}else {
				gap = " ";
			}
			prompt = prompt + " "+  i + "[" + gap + players.get(0).getHouseSeedsCount(i) +"] |";
		}
		prompt = prompt + " P1 |";
		io.println(prompt);

		io.println("+----+-------+-------+-------+-------+-------+-------+----+");
	}
	
	public int requestInput(Player player) {
		int playID = player.getIdentifier()+1;
		String prompt = "Player P" + playID +"'s turn - Specify house number or 'q' to quit: ";
		return io.readInteger(prompt, 1, 6, -1, "q");
	}
	
	public void printPrompt(String prompt) {
		io.println(prompt);
	}
	
	public void gameEnd(String prompt, int player1_score, int player2_score) {
		io.println("	player 1:" + player1_score);
		io.println("	player 2:" + player2_score);
		io.println(prompt);
	}
	
	
}
