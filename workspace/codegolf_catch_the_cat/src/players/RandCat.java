package players;

import main.Field;

public class RandCat implements Cat{
	public String getName(){
		return "RandCat";
	}
	public int[] takeTurn(Field f){
		int[][] turns = {{-1,1},{0,1},{-1,0},{1,0},{0,-1},{1,-1}};//all valid moves
		int[] move;
		do {
			move = turns[(int) (turns.length * Math.random())];
		} while(f.isValidMove(move)==false);
		return move;//chose one at random
	}
}
