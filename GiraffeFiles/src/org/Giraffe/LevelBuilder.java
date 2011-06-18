package org.Giraffe;

public class LevelBuilder {
	String levels;
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

			}
		case(2):
		case(3):
		}
	}
	public String getLevel(){
		return levels;
	}
}
