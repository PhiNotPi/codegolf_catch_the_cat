package main;

import players.*;

public class Controller {
	public static final int NUMBER_OF_GAMES = 1;
	public static final int FIELD_SIZE = 9;
	Cat[] cats;
	Catcher[] catchers;
	int[][][] results;
	
	/**
	 * Add your cat class here.
	 * @return
	 */
	public Cat[] getCats(){
		return new Cat[] {new RandCat(),new StupidRightCat()};//,new RandCat2()};
	}
	/**
	 * Add your catcher class here.
	 * @return
	 */
	public Catcher[] getCatchers(){
		return new Catcher[] {new RandCatcher(),new StupidFillCatcher()};		
	}
	
	
	Controller(){
		cats = getCats();
		catchers = getCatchers();
		results = new int[cats.length][catchers.length][NUMBER_OF_GAMES];
	}

	/**
	 * Plays and evaluates the games
	 */
	public void playGames(){
		System.out.println("Playing Games");
		//for each pairing of cat/catcher
		for(int i=0;i<cats.length;i++){
			for(int j=0;j<catchers.length;j++){//TODO: Implement number of games.
				results[i][j][0] = playGame(cats[i],catchers[j]);
			}
		}	
	}
	
	public int playGame(Cat cat, Catcher catcher){
		System.out.println("Playing a game:"+cat.getName()+" vs "+catcher.getName());
		// new game
		Field field = new Field(FIELD_SIZE);
		
		int count = 0;
		Field fieldCopy;//only pass copies of the field in order to prevent the submissions from manipulating data
		
		for(count=0; field.isFinished()==false; count++){//turn by turn
			
			fieldCopy = new Field(field);
			
			if(count%2==0){//catcher's turn
				
				int[] pos = catcher.takeTurn(fieldCopy);//check validity of turns and execute turns
				if(field.isValidPosition(pos)){
					field.placeBucket(pos);					
				} else {
					System.out.println(catcher.getName()+" is disqualified");
					return -10;
				}
				
			} else {//cat's turn
				
				int[] move = cat.takeTurn(fieldCopy);
				if(field.isValidMove(move)){
					field.executeMove(move);
				} else {
					System.out.println(cat.getName()+" is disqualified");
					int[] pos = field.findCat();
					System.out.println("Position was "+pos[0]+" , "+pos[1]);
					System.out.println("Move was "+move[0] + " , "+ move[1]);
					return -1;
				}

			}//end catcher/cats turn
			if(count > field.SIZE*field.SIZE*2){
				System.out.println("Game ended with too many moves. Something went terribly wrong.");
				return -100;
			}
			
			System.out.println("Current State");
			System.out.println(field.toString());
			
			
			
		}//for (turns9
		
		return count;
	}//end of playGame()
	
	public static void main(String[] args){
		Controller c = new Controller();
		c.playGames();
		for(int i=0;i<c.cats.length;i++){
			System.out.println("");
			for(int j=0;j<c.catchers.length;j++){
				System.out.print(c.results[i][j][0]+"    ");
			}
		}
	}

}
