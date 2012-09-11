package com.arne5.ezratictactoe;



import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class GameStart extends Activity implements OnClickListener{

	
	ImageButton BTopLeft,BTopMiddle,BTopRight,BCenterLeft,BCenterMiddle,BCenterRight,BBottomLeft,BBottomMiddle,BBottomRight;
	TextView TxtPlayerOne, TxtPlayerTwo, TxtxoroTop,TxtxoroBottom,TxtGamesWonPlayOne,TxtGamesWonPlayTwo;
	Button btRestart, btQuit;
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
    	getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
    			WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
		setContentView(R.layout.game);
		initialize();
        
        
    }
    private void initialize(){
    	//connect variables to the xml variables in the game xml
    	BTopLeft = (ImageButton) findViewById(R.id.ibTopLeft);
    	BTopLeft.setOnClickListener(this);
    	
    	
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_game_start, menu);
        return true;
    }
    
    //create a reset method
    private void reset(){
    	
    }
    
    //create a quit method
    private void quit(){
    	
    	
    }

	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
}
