package main;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] testarray = new int[10000][10000];
		
		long startTime = System.nanoTime();
		for(int i=0;i<10000;i++){
			for(int j=0;j<10000;j++){
				
				testarray[i][j] = 1;
				
			}
			
		}
		System.out.println((System.nanoTime()-startTime)/1000000 + "ms");
		
	}//main

}
