package players;

import main.Field;
/**
 * Moves always right. If not possible, moves randomly.
 * @author user
 *
 */

public class StupidRightCat implements Cat{
	public String getName(){
		return "StupidRightCat";
	}
	public int[] takeTurn(Field f){
		int[][] turns = {{-1,1},{0,1},{-1,0},{1,0},{0,-1},{1,-1}};//all valid moves
		int[] move;
		
		if(f.isValidMove(turns[3])){
			return turns[3];
		} else {
			do {
				move = turns[(int) (turns.length * Math.random())];
			} while(f.isValidMove(move)==false);
			return move;//chose one at random
		}
	}
}
