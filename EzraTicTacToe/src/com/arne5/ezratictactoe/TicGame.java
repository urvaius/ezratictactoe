package com.arne5.ezratictactoe;



import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class TicGame extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.greetings);
		final ImageView ivnewgame = (ImageView) findViewById(R.id.new_game);
        final ImageView ivoptions = (ImageView) findViewById(R.id.options);
        
        final ImageView ivquit = (ImageView) findViewById(R.id.quit);
        ivnewgame.setOnClickListener(greetings_listener);
        ivoptions.setOnClickListener(greetings_listener);
        ivquit.setOnClickListener(greetings_listener);
        
	}
	
	//variables 
	int count = 0; //number of moves
	int movearr[][]={{0,0,0},{0,0,0},{0,0,0}}; //stores movement
	int player = 1; //set player one to default
	int gametype = 1;// type of game
	int analysis_arr[][]={{0,0},{0,0},{0,0},{0,0},{0,0},{0,0},{0,0},{0,0}};//analyze
	int map_arr[][]={{1,1,1},{1,1,1},{1,1,1}};// map initizlize
	int user_symbol =0;
	boolean sound_enabled = true;//sound on default
	int cross = R.drawable.default_cross;//was skin_cross
	int dot = R.drawable.default_dot;//was dot_cross
	int skin_layout = R.layout.main;
	int game_bg = 2;
	
	//player name initialize
	CharSequence player_name_1 = "Player 1";
	CharSequence player_name_2 = "Player 2";
	//intialize score
	int player1score = 0;
	int player2score = 0;
	//menu items
	int MENU_NEW_GAME = 0;
	int MENU_OPTIONS = 1;
	int MENU_QUIT = 2;
	//dialog id
	final int NAME_DIALOG_ID = 1;
	final int HELP_DIALOG_ID =2;
	
	
	
	//end variables
	
	OnClickListener greetings_listener = new View.OnClickListener() {
		
		public void onClick(View v) {
			final ImageView iv = (ImageView) v;
			if (iv.getId()== R.id.new_game){
				showDialog(NAME_DIALOG_ID);
			}
			else if (iv.getId()== R.id.new_game){
				options_menu();
				
			}
			else if (iv.getId()== R.id.quit){
				finish();
			}
			
			
			
			
		}
	};
	
	//enter names
	
	protected Dialog OnCreateDialog(int id) {
		Dialog mdialog = new Dialog(this);
		switch(id) {
		case NAME_DIALOG_ID:
			mdialog.setContentView(R.layout.dialogname);
			mdialog.setTitle("Player Names");
			mdialog.setCancelable(true);
			final EditText p1name =(EditText) mdialog.findViewById(R.id.p1name);
			final EditText p2name =(EditText) mdialog.findViewById(R.id.p2name);
			
			Button bOk = (Button) mdialog.findViewById(R.id.ok);
			bOk.setOnClickListener(new OnClickListener() {
				public void onClick(View v){
					player_name_2 = p2name.getText();
					player_name_1 = p1name.getText();
					player1score =0;
					player2score =0;
					gameStart(p1name.getText());
					dismissDialog(1);
				}
			});
			break;
			
		default:
			mdialog = null;
			
		}
		return mdialog;
	}
	// create menu
	public boolean onCreateOptionsMenu(Menu menu){
		menu.add(0, MENU_NEW_GAME, 0, "NEW Game");
		menu.add(0, MENU_OPTIONS,0, "Options");
		menu.add(0, MENU_QUIT,0,"Quit");
		return true;
	}
	// options selected
	public boolean onOptoinsItemSelected(MenuItem item){
		if(item.getItemId()==0)  //new game
			showDialog(NAME_DIALOG_ID);
		else if(item.getItemId()==1)//options menu
		{
			options_menu();
		}
		else
		{
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setMessage("Do you wish to Quit?");
			builder.setCancelable(true);
			builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
				

				public void onClick(DialogInterface dialog, int id) {
					// TODO Auto-generated method stub
					
				}

				
			});
			
			
			
				builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
					
					public void onClick(DialogInterface dialog, int which) {
						finish();
						// TODO Auto-generated method stub
						
					}
				});
				AlertDialog alert = builder.create();
				alert.show();
			}
		return true;
		
	}
	//options
	public void options_menu(){
		final CharSequence[] options_items = {"Pick X or O", "Game Type", "Player Names", "Exit"};
		
		AlertDialog.Builder options_builder = new AlertDialog.Builder(this);
		options_builder.setTitle("Options");
		options_builder.setItems(options_items, new DialogInterface.OnClickListener() {
			
			public void onClick(DialogInterface dialog, int item) {
				if(options_items[item] == "Pick X or O")
					XorOselect();
				else if (options_items[item] == "Game Type")
					mode_select();
				else if (options_items[item]== "Player Names")
					showDialog(NAME_DIALOG_ID);
				else if (options_items[item] == "Exit")
					return;
				
				
			}
		});
		options_builder.show();
	}
	// dialog for options
	public void XorOselect(){
		AlertDialog.Builder XorObuilder = new AlertDialog.Builder(this);
		XorObuilder.setMessage("Select X or O");
		XorObuilder.setCancelable(false);
		XorObuilder.setNegativeButton("O", new DialogInterface.OnClickListener() {
			
			
			public void onClick(DialogInterface dialog, int id) {
				user_symbol = 0;
				gameStart(player_name_1);
				
				
				
				
			}
		});
		XorObuilder.setPositiveButton("X", new DialogInterface.OnClickListener() {
			
			public void onClick(DialogInterface dialog, int which) {
				user_symbol = 1;
				gameStart(player_name_1);
				
			}
		});
		XorObuilder.show();
		
	}
	public void mode_select(){
		
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("Choose Game Type : ").setPositiveButton("Against Kindle", new DialogInterface.OnClickListener(){
			public void onClick(DialogInterface dialog, int id){
				Toast.makeText(getApplicationContext(), "Game Changed to Vs Kindle", Toast.LENGTH_SHORT).show();
				gametype =1;
				player1score =0;
				player2score =0;
			}
			
		}).setNegativeButton("Two Player", new DialogInterface.OnClickListener() {
			
			public void onClick(DialogInterface dialog, int id) {
				Toast.makeText(getApplicationContext(), "Play Human Vs Human!!", Toast.LENGTH_SHORT).show();
				gametype = 0;
				player1score = 0;
				player2score = 0;
				showDialog(NAME_DIALOG_ID);
				
			}
		});
		builder.show();
		return;
		
	}
	
	//left out change skin for now. 
		
	// common onclicklistener
	OnClickListener button_listener = new View.OnClickListener() {
		
		public void onClick(View v) {
			ImageButton ticbutton = (ImageButton) v;
			//button inactive until end
			ticbutton.setClickable(false);
			//increment count on clickint
			count++;
			if((count % 2 ==0) || (gametype ==1)){
				player =2;//human player
				if ((user_symbol ==0) && (gametype == 1))
					ticbutton.setImageResource(dot);
				else if ((user_symbol ==1) && (gametype ==1))
					ticbutton.setImageResource(cross);
				else
					ticbutton.setImageResource(dot);
			}
			//after move functions
			after_move(ticbutton);
			
			
		}
	};
	
	// check array and return result true if array full
	public boolean arr_isFull (){
		for (int i =0; i<3;i++)
			for (int j =0;j<3;j++)
				if (movearr[i][j] == 0)
					return false;
		return true;
	}
	// starting point fo game. starting all. etc.
	public void gameStart(CharSequence player_name){
		
		//reset game view
		setContentView (skin_layout);
		
	}
	

}
