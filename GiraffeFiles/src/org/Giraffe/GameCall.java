package org.Giraffe;


import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


public class GameCall extends Activity{

	GameView view;
	SurfaceView surface;
	SurfaceHolder holder;
	GameController controller;
	GameModel model;
	Context context;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.game);
        
        surface=(SurfaceView)findViewById(R.id.gSurface);
        context=surface.getContext();
        model=new GameModel(context);
        
        holder=surface.getHolder();
        
        controller= new GameController(model);
        surface.setOnTouchListener(controller);
        
        view=new GameView(controller, holder, model, context);
        surface.getHolder().addCallback(view);
        
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE); 
       
    }

}