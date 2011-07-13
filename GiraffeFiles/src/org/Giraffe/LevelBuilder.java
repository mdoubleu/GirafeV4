package org.Giraffe;

public class LevelBuilder {
	String levels;
	int numberForBackground;
	public LevelBuilder(int level){
		
		switch(level){
		
		case(1):
			
			levels="0%0#0%2#1300%1#1800%2" + 
   "3000%1#800%2#1500%1#3000%2#1400%3#1700%2#1500%2#800%1#1400%2#" + 
   "2700%1#800%1#800%1#800%1#1400%2#3000%2#1400%3" +
   "3000%1#1800%2#1800%2#1200%2#1400%2#1500%1#800%2";
			
			//this number refers to what picture set should be loaded for a given level.
			numberForBackground=1;
			break;
		case(2):
			levels="0%0#0%2#1300%1#1800%2" + 
			   "3000%1";
			//this number refers to what picture set should be loaded for a given level.
			numberForBackground=1;
			break;
		case(3):
		}
	}
	
	public int getNumForBackground(){
		return numberForBackground;
	}
	public String getLevel(){
		return levels;
	}
	
}
