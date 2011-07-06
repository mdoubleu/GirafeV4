package org.Giraffe;

public class LevelBuilder {
	String levels;
	int numberForBackground;
	public LevelBuilder(int level){
		
		switch(level){
		
		case(1):
			
			levels="0%2#7000%2#5000%2#5000%1#3000%1#6000%3#7000%2#"+
			"5000%2#5000%1#3000%1#6000%3#3000%1#3000%1#3000%1#7000%2"+
			"#2000%3#1000%1#5000%2#10000%2#3000%1#5000%1#7000%2#4000%2#9000%3#"+
			"5670%2#4521%1#6000%2#8901%3#1290%1#4560%3#";
			//levels="0%2#";
			numberForBackground=1;
			break;
		case(2):
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
