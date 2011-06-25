package org.Giraffe;


import org.Giraffe.GameViewOld.GameThread;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


public class GameCall extends Activity{

	GameViewOld view;
	SurfaceView surface;
	SurfaceHolder holder;
	GameController controller;
	GameModel model;
	Context context;
	
	
	//tims stuff
	/*	private SurfaceView surface;
	private SurfaceHolder holder;
	private GameModel model;
	private GameView view;
	private GameController controller;*/

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        model=new GameModel();
        
        setContentView(R.layout.game);
        
        surface=(SurfaceView)findViewById(R.id.gSurface);
        context=surface.getContext();
        holder=surface.getHolder();
        
        controller= new GameController(model);
        surface.setOnTouchListener(controller);
        
        view=new GameView(controller, holder, model, context);
        surface.getHolder().addCallback(view);
        
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE); 
       
    }

}