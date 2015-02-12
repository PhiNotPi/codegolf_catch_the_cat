package players;

import main.Field;

/**
 * Just fills column by column.
 * @author user
 *
 */

public class StupidFillCatcher implements Catcher {
	public String getName(){
		return "StupidFillCatcher";
	}
    public int[] takeTurn(Field f){
    	for(int i=0; i < f.SIZE; i++){
    		for(int j=0; j < f.SIZE; j++){
    			if(f.isValidPosition(new int[] {i,j})){
    				return new int[] {i,j};
    			}
    		}
    	}
    	return new int[] {0,0};
    }
	
}
