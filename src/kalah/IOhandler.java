package kalah;

import java.io.IOException;
import java.util.Scanner;

import com.qualitascorpus.testsupport.IO;

public class IOhandler{
	private IO io;
	
	public IOhandler(IO io) {
		this.io = io;
	}
	
	public void printPlayboard(Integer[][] data) {
		io.println("+----+-------+-------+-------+-------+-------+-------+----+");
		
		String gap =" ";
		String prompt = "| P2 |";
		for(int i = 6; i>0; i--) {
			if(data[1][i]>9) {
				gap = "";
			}else {
				gap = " ";
			}
			prompt = prompt + " "+  i + "[" + gap + data[1][i] +"] |";
		}
		if(data[0][0]>9) {
			gap = "";
		}else {
			gap = " ";
		}
		prompt = prompt + " "+ gap + data[0][0]+" |";
		io.println(prompt);
		
		io.println("|    |-------+-------+-------+-------+-------+-------|    |");
		
		if(data[1][0]>9) {
			gap = "";
		}else {
			gap = " ";
		}
		prompt = "| "+ gap + data[1][0]+" |";
		for(int i = 1; i < 7; i++) {
			if(data[0][i]>9) {
				gap = "";
			}else {
				gap = " ";
			}
			prompt = prompt + " "+  i + "[" + gap + data[0][i] +"] |";
		}
		prompt = prompt + " P1 |";
		io.println(prompt);

		io.println("+----+-------+-------+-------+-------+-------+-------+----+");
	}

	public int requestInput(int currentPlayer, int houseNumber) {
		String prompt = "Player P" + (currentPlayer+1) +"'s turn - Specify house number or 'q' to quit: ";
		return houseNumber = io.readInteger(prompt, 1, 6, -1, "q");
	}
	
	public void gameInfo(Integer[][] data, String prompt) {
		io.println(prompt);
		printPlayboard(data);
	}
	
	public void gameEnd(Integer[][] data) {
		io.println("Game over");
		printPlayboard(data);
		
		int player1Score = 0;
		for(int i = 0 ; i<7; i++) {
			player1Score += data[0][i];
		}
		
		int player2Score = 0;
		for(int i = 0 ; i<7; i++) {
			player2Score += data[1][i];
		}
		
		io.println("	player 1:" + player1Score);
		io.println("	player 2:" + player2Score);
		String output;
		if(player1Score>player2Score) {
			output = "Player 1 wins!" ;
		}else if(player1Score<player2Score){
			output = "Player 2 wins!" ;
		}else {
			output = "A tie!" ;
		}
		io.println(output);
	}
	
}

