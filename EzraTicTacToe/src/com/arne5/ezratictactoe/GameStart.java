package com.arne5.ezratictactoe;



import android.R.bool;
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
	boolean playeroneturn;
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
    	getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
    			WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
		setContentView(R.layout.game);
		int playerturn=0;
		initialize();
        
        
    }
    private void initialize(){
    	//connect variables to the xml variables in the game xml
    	BTopLeft = (ImageButton) findViewById(R.id.ibTopLeft);
    	BTopLeft.setOnClickListener(this);
    	BTopMiddle = (ImageButton) findViewById(R.id.ibTopMiddle);
    	BTopMiddle.setOnClickListener(this);
    	BTopRight = (ImageButton) findViewById(R.id.ibTopRight);
    	BCenterLeft = (ImageButton) findViewById(R.id.ibCenterLeft);
    	BCenterLeft.setOnClickListener(this);
    	BCenterMiddle = (ImageButton) findViewById(R.id.ibCenterMiddle);
    	BCenterMiddle.setOnClickListener(this);
    	BCenterRight = (ImageButton) findViewById(R.id.ibCenterRight);
    	BCenterRight.setOnClickListener(this);
    	BBottomLeft = (ImageButton) findViewById(R.id.ibBottomLeft);
    	BBottomLeft.setOnClickListener(this);
    	BBottomMiddle = (ImageButton) findViewById(R.id.ibBottomMiddle);
    	BBottomMiddle.setOnClickListener(this);
    	BBottomRight = (ImageButton) findViewById(R.id.ibBottomRight);
    	BBottomRight.setOnClickListener(this);
    	TxtPlayerOne = (TextView) findViewById(R.id.txtplayerone);
    	TxtPlayerTwo = (TextView) findViewById(R.id.txtplayertwo);
    	TxtGamesWonPlayOne = (TextView) findViewById(R.id.txtGameWonPlayOne);
    	TxtGamesWonPlayTwo = (TextView) findViewById(R.id.txtGameWonPlayTwo);
    	btRestart = (Button) findViewById(R.id.btRestart);
    	btQuit = (Button) findViewById(R.id.btQuit);
    	playeroneturn = true;
    	
    	boolean isX;
    	boolean isO;
    	isX =false;
    	isO = false;
    	
    	
    	
    			
    	
    	
    	
    	
    	
    	
    	
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
		switch (v.getId()){
		case R.id.ibTopLeft:
			if (playeroneturn= true){
				BTopLeft.setImageResource(R.drawable.cruz);
				
				
				
			}else{
				
				
			
			BTopLeft.setImageResource(R.drawable.bola);
			}
			
		}
		
	}
}
