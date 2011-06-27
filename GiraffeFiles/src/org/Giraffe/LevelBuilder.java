package org.Giraffe;

public class LevelBuilder {
	String levels;
	int numberForBackground;
	public LevelBuilder(int level){
		
		switch(level){
		
		case(1):
	        long check=System.currentTimeMillis()+0;
			levels=""+check+"%1#";
			int g=0;
			for(int l=3000; l<20000; l+=5000){
				g++;
				if(g==1){
					check=System.currentTimeMillis()+l;
					levels+=check+"%1#";
				}else if(g==2){
					check=System.currentTimeMillis()+l;
					levels+=check+"%2#";
					g=0;
				}
				numberForBackground=1;
			}
			
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
