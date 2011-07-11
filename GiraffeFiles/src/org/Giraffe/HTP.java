package org.Giraffe;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup.LayoutParams;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class HTP extends Activity/* implements OnClickListener*/
{
	LinearLayout buttonHolder;
	LinearLayout mLinearLayout;

	protected void onCreate(Bundle savedInstanceState)
	{
	    super.onCreate(savedInstanceState);

	    // Create a LinearLayout in which to add the ImageView
	    buttonHolder = new LinearLayout(this);
	    mLinearLayout = new LinearLayout(this);

	    // Instantiate an ImageView and define its properties
	    ImageView i = new ImageView(this);
	   // i.setImageResource(R.drawable.g3);
	    i.setAdjustViewBounds(true); // set the ImageView bounds to match the Drawable's dimensions
	    i.setLayoutParams(new Gallery.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));

	    // Add the ImageView to the layout and set the layout as the content view
	
	    mLinearLayout.addView(i);
	  //  mLinearLayout.addView(buttonHolder);
	    setContentView(mLinearLayout);
	    
	    /*
	    View leftButton = findViewById(R.id.left_button);
        leftButton.setOnClickListener(this);
        View rightButton = findViewById(R.id.right_button);
        rightButton.setOnClickListener(this);
        buttonHolder.addView(leftButton);
        buttonHolder.addView(rightButton);
	}
	@Override
	public void onClick(View v) 
	{
		// TODO Auto-generated method stub
		switch (v.getId())
		{
			case R.id.left_button:
				//openNewGameDialog();
				break;
			case R.id.right_button:
			Intent a = new Intent(this, About.class);
			
			startActivity(a);
			break;
			case R.id.how_to_play_button:
			Intent b= new Intent(this, HTP.class);
			startActivity(b);
			break;
			
		}
		
	}
	*/
	}
}
