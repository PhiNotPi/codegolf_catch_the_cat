package players;

import main.Field;

public class StupidFillCatcher implements Catcher {
	public String getName(){
		return "StupidFillCatcher";
	}
    public int[] takeTurn(Field f){
    	for(int i=0; i < f.SIZE; i++){
    		for(int j=0; j < f.SIZE; j++){
    			if(f.read(i,j) == Field.EMPTY){
    				return new int[] {i,j};
    			}
    		}
    	}
    	return new int[] {0,0};
    }
	
}
