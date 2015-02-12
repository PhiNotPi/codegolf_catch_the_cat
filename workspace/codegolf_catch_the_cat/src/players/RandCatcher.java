package players;

import main.Field;

public class RandCatcher implements Catcher {
	public String getName(){
		return "RandCatcher";
	}
    public int[] takeTurn(Field f){
    	int[] pos = {0,0};
    	do {
    		pos[0] = (int) (Math.random()*f.SIZE);
    		pos[1] = (int) (Math.random()*f.SIZE);
    	} while( f.isValidPosition(pos)==false );
    	return pos;
    }
	
}
