package main;

public class Field {
	public final int SIZE;
	public int[][] field;
	public static final int EMPTY = 0;
	public static final int CAT = -1;
	public static final int BUCKET = 1; 
	
	
	/**
	 * Constructor
	 * @param size
	 */
	Field(int size){
		SIZE = size;
		field = new int[SIZE][SIZE];
		//initialize cat
		field[SIZE/2][SIZE/2] = CAT;
	}
	/**
	 * Copy/Clone constructor (for cloning existing field)
	 * @return
	 */
	Field(Field f){//TODO: Keep this up to date.
		this(f.SIZE);
		//clone the field:
		for(int i=0;i<SIZE;i++){
			for(int j=0;j<SIZE;j++){
				this.field[i][j] = f.field[i][j];
			}
		}
	}
	
	/**
	 * Accessing the array field[i][j] but allows indices out of range. (Torroidal topology.)
	 * @param i
	 * @param j
	 * @return
	 */
	
	public int read(int i, int j){
		i = (i%SIZE + SIZE)%SIZE;
		j = (j%SIZE + SIZE)%SIZE;
		return field[i][j];
	}
	
	/**
	 * Accessing the array field[i][j] but allows indices out of range. (Torroidal topology.)
	 * @param i
	 * @param j
	 * @return
	 */
	
	private int write(int i, int j, int value){
		i = (i%SIZE + SIZE)%SIZE;
		j = (j%SIZE + SIZE)%SIZE;
		return field[i][j] = value;
	}	
	
	
	
	/**
	 * first element is x coordinate, second is y coordinate
	 * (0,0) is at bottom left, x goes to right, y up
	 * Allowed steps for the cat:
	 * y
	 * [1,1,0]
	 * [1,C,1]
	 * [0,1,1]x
	 * @param move
	 * @return
	 */
	public boolean isValidMove(int[] move){
		if( (move[1] == 1 && (move[0] == -1 || move[0] == 0)) ||
			(move[1] == 0 && (move[0] == -1 || move[0] == 1)) ||
			(move[1] ==-1 && (move[0] ==  0 || move[0] == 1))    )
		{//good so far, check availability of the step.
			//find cat:
			int pos[] = findCat();
			//check neighbours
			if(this.read(pos[0]+move[0], pos[1]+move[1]) == EMPTY){
				return true; //empty spot
			} else {
				return false; //bucket already blocking the spot
			}
		} else {//invalid
			return false;			
		}
	}
	/**
	 * Find the position of the cat.
	 * @return
	 */
	
	public int[] findCat(){
		int i=0,j=0;
		for(i=0;i<SIZE;i++){
			for(j=0;j<SIZE;j++){
				if(this.read(i,j) == CAT){
					return new int[] {i,j};
				}
			}
		}
		System.out.println("Error 404 (Furr-Oh-Furr): Cat not found.");
		return new int[] {-1,-1};
	}
	
	
	
	
	/**
	 * Arguments are the coordinates on the grid
	 * @param pos
	 * @return
	 */
	public boolean isValidPosition(int[] pos){
		if(this.read(pos[0],pos[1]) == EMPTY){
			return true;
		} else {
			return false;
		}
	}
	
	
	public void placeBucket(int[] pos){
		this.write(pos[0], pos[1], BUCKET);
	}
	
	public void executeMove(int[] move){
		int[] pos = findCat();
		this.write(pos[0],pos[1],EMPTY);
		this.write(pos[0]+move[0],pos[1]+move[1],CAT);
	}
	
	
	
	
	
	public boolean isFinished(){
		int[] pos = findCat();
		if(		this.read(pos[0]-1,pos[1]+1)==BUCKET &&
				this.read(pos[0]+0,pos[1]+1)==BUCKET &&
				this.read(pos[0]-1,pos[1]+0)==BUCKET &&
				this.read(pos[0]+1,pos[1]+0)==BUCKET &&
				this.read(pos[0]+0,pos[1]-1)==BUCKET &&
				this.read(pos[0]+1,pos[1]-1)==BUCKET)
		{
			return true;
		} else {
			return false;
		}
	}
	
	public String toString(){
		String s = "";
		for(int j=SIZE-1;j>=0;j--){//lines
			s += "|";
			for(int i=0;i<SIZE;i++){//columns
				if(field[i][j] == EMPTY){
					s+=".";
				} else if(field[i][j] == BUCKET){
					s+="B";
				} else {//cat
					s+="C";
				}
			}
			s+="|"+j+"\n";
		}
		return s;	
	}

}
