package com.arne5.ezratictactoe;




import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;

import android.widget.TextView;
import com.arne5.ezratictactoe.*;
public class GameStart extends Activity implements OnClickListener{

	
	TicButton BTopLeft,BTopRight,BTopMiddle,BCenterLeft,BCenterMiddle,BCenterRight,BBottomLeft,BBottomMiddle,BBottomRight;
	TextView TxtPlayerOne, TxtPlayerTwo, TxtxoroTop,TxtxoroBottom,TxtGamesWonPlayOne,TxtGamesWonPlayTwo;
	Button btRestart, btQuit;
	boolean playeroneturn;
	boolean check;
	boolean isX;
	boolean isO;
	
	
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
    	BTopLeft = (TicButton) findViewById(R.id.ibTopLeft);
    	BTopLeft.setOnClickListener(this);
    	
    	BTopMiddle = (TicButton) findViewById(R.id.ibTopMiddle);
    	BTopMiddle.setOnClickListener(this);
    	
    	BTopRight = (TicButton) findViewById(R.id.ibTopRight);
    	BTopRight.setOnClickListener(this);
    	 	   			
    			
    	BCenterLeft = (TicButton) findViewById(R.id.ibCenterLeft);
    	BCenterLeft.setOnClickListener(this);
    	
    	BCenterMiddle = (TicButton) findViewById(R.id.ibCenterMiddle);
    	BCenterMiddle.setOnClickListener(this);
    	
    	BCenterRight = (TicButton) findViewById(R.id.ibCenterRight);
    	BCenterRight.setOnClickListener(this);
    	
    	BBottomLeft = (TicButton) findViewById(R.id.ibBottomLeft);
    	BBottomLeft.setOnClickListener(this);
    	
    	BBottomMiddle = (TicButton) findViewById(R.id.ibBottomMiddle);
    	BBottomMiddle.setOnClickListener(this);
    	
    	BBottomRight = (TicButton) findViewById(R.id.ibBottomRight);
    	BBottomRight.setOnClickListener(this);
    	
    	TxtPlayerOne = (TextView) findViewById(R.id.txtplayerone);
    	TxtPlayerTwo = (TextView) findViewById(R.id.txtplayertwo);
    	TxtGamesWonPlayOne = (TextView) findViewById(R.id.txtGameWonPlayOne);
    	TxtGamesWonPlayTwo = (TextView) findViewById(R.id.txtGameWonPlayTwo);
    	BTopLeft.setImageResource(R.drawable.vazio);
    	BTopMiddle.setImageResource(R.drawable.vazio);
    	BTopRight.setImageResource(R.drawable.vazio);
    	BCenterLeft.setImageResource(R.drawable.vazio);
    	BCenterMiddle.setImageResource(R.drawable.vazio);
    	BCenterRight.setImageResource(R.drawable.vazio);
    	BBottomLeft.setImageResource(R.drawable.vazio);
    	BBottomMiddle.setImageResource(R.drawable.vazio);
    	BBottomRight.setImageResource(R.drawable.vazio);
    	btRestart = (Button) findViewById(R.id.btRestart);
    	btQuit = (Button) findViewById(R.id.btQuit);
    	playeroneturn = true;
    	check =false;
    	
    	//just to test commits
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
				//try to do a check for x or o and set them
				BTopLeft.checkX(true);	
				BTopLeft.checkO(false);
					
								
			}else{			
				
			
			BTopLeft.setImageResource(R.drawable.bola);
			BTopLeft.checkX(false);
			BTopLeft.checkO(true);
			}
			break;
			
		case R.id.ibTopRight:
			if (playeroneturn = true){
				
				BTopRight.setImageResource(R.drawable.cruz);
				
				
				BTopRight.checkX(true);	
				BTopRight.checkO(false);
				
			}else{
				BTopRight.setImageResource(R.drawable.bola);
				BTopRight.checkX(false);	
				BTopRight.checkO(true);
			}
			break;
			
		case R.id.ibTopMiddle:
			if (playeroneturn= true){
				BTopMiddle.setImageResource(R.drawable.cruz);
				
				
			}else{
				BTopMiddle.setImageResource(R.drawable.bola);
			}
			break;
			
		
			
		case R.id.ibCenterRight:
			if(playeroneturn = true){
				BCenterRight.setImageResource(R.drawable.cruz);
			}else{
				BCenterRight.setImageResource(R.drawable.bola);
			}
			break;
			
		case R.id.ibCenterMiddle:
			if(playeroneturn = true){
				BCenterMiddle.setImageResource(R.drawable.cruz);
				
			}else{
				BCenterMiddle.setImageResource(R.drawable.bola);
			}
			break;
			
		case R.id.ibCenterLeft:
			if(playeroneturn = true){
				BCenterLeft.setImageResource(R.drawable.cruz);
				
			}else{
				BCenterLeft.setImageResource(R.drawable.bola);
			}
			break;
		case R.id.ibBottomLeft:
			if(playeroneturn = true){
				BBottomLeft.setImageResource(R.drawable.cruz);
				
			}else{
				BBottomLeft.setImageResource(R.drawable.bola);
			}
			break;
		case R.id.ibBottomMiddle:
			if(playeroneturn = true){
				BBottomMiddle.setImageResource(R.drawable.cruz);
			}else{
				BBottomMiddle.setImageResource(R.drawable.bola);
			}
			break;
		
		case R.id.ibBottomRight:
			if(playeroneturn = true){
				BBottomRight.setImageResource(R.drawable.cruz);
			}else{
				BBottomRight.setImageResource(R.drawable.bola);
			}
			break;
		case R.id.btRestart:
			//Intent restart = new Intent("com.arne5.GAMESTART");
			//startActivity(restart);
			onRestart();
			break;
			
		case R.id.btQuit:
			finish();
			break;
			
		}
		
	}
}
