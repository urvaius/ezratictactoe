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
import android.widget.TextView;
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
			else if (iv.getId()== R.id.options){
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
			mdialog.setContentView(R.layout.name_dialog_2);
			mdialog.setTitle("Player Names");
			mdialog.setCancelable(true);
			final EditText p1name =(EditText) mdialog.findViewById(R.id.namep1);
			final EditText p2name =(EditText) mdialog.findViewById(R.id.namep1);
			
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
			
		case HELP_DIALOG_ID:
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
	public boolean onOptionsItemSelected(MenuItem item){
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
					
				}

				
			});
			
			
			
				builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
					
					public void onClick(DialogInterface dialog, int which) {
						finish();
						
						
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
		final ImageButton b3 = (ImageButton) findViewById(R.id.b3);
        final ImageButton b2 = (ImageButton) findViewById(R.id.b2);
        final ImageButton b1 = (ImageButton) findViewById(R.id.b1);

        final ImageButton b6 = (ImageButton) findViewById(R.id.b6);
        final ImageButton b5 = (ImageButton) findViewById(R.id.b5);
        final ImageButton b4 = (ImageButton) findViewById(R.id.b4);
        
        final ImageButton b9 = (ImageButton) findViewById(R.id.b9);
        final ImageButton b8 = (ImageButton) findViewById(R.id.b8);
        final ImageButton b7 = (ImageButton) findViewById(R.id.b7);
        
        // set the OnClickListeners.
        b1.setOnClickListener(button_listener);
        b2.setOnClickListener(button_listener);
        b3.setOnClickListener(button_listener);
        b4.setOnClickListener(button_listener);
        b5.setOnClickListener(button_listener);
        b6.setOnClickListener(button_listener);
        b7.setOnClickListener(button_listener);
        b8.setOnClickListener(button_listener);
        b9.setOnClickListener(button_listener);
        
        // Re-enable the Click-able property of buttons.
        b1.setClickable(true);
        b2.setClickable(true);
        b3.setClickable(true);
        b4.setClickable(true);
        b5.setClickable(true);
        b6.setClickable(true);
        b7.setClickable(true);
        b8.setClickable(true);
        b9.setClickable(true);
        //update score etc..
        set_score(3);
        //reset array move arr
        for (int i = 0; i <3;i++)
        	for (int j=0;j<3;j++)
        		movearr[i][j] = 0;
        //initiate computer chance during start of the game
        
        if((gametype ==1) && (count % 2 !=0))
        	kindleGame();
        
        
		
	}
	// check for result and select next player. 
	public void after_move (ImageButton ib){
		CharSequence pos_str = "";
		int pos =0;
		boolean result = false;
		pos_str = (CharSequence) ib.getTag();
		pos = (int)pos_str.charAt(0) -48;//char to integer conversion
		//set the values in the array according to the player number
		if (player ==1){
			if (pos <4)
				movearr[0][pos -1]= 1;
			else if (pos < 7) 
    			movearr[1][(pos - 1) % 3] = 1;
    		else if (pos < 10)
    			movearr[2][(pos - 1) % 3] = 1;
    	}
    	else {
    		if (pos < 4)				
    			movearr[0][pos - 1] = 2;
    		else if (pos < 7) 
    			movearr[1][(pos - 1) % 3] = 2;
    		else if (pos < 10)
    			movearr[2][(pos - 1) % 3] = 2;
			
		}
		//check fof game result
		result = result_check(player);
		//result check seciton
		if(result ==true){
			//check player number
			if(player ==1){
				set_score(1);
				if(gametype ==0){
					show_result("Wow " + player_name_1 + " wins !!");
					
				}
				else {
					show_result("Kindle Wins!!");
				}
			}
			else {
				set_score(2);
				if (gametype ==0){ //human vs human
					show_result("Wow " + player_name_2 + " wins!!");
					
					
				}
				else
				{
					show_result("Wow You beat the Kindle ");
					
				}
			}
			return;
		}
		else if ((result == false) && arr_isFull()) {
    		show_result("    Game Draw !    ");				// leave the space, or else dialog becomes cramped.
    		return;
    	}
    	
    	// Next Player select section.
    	if ((gametype == 1) && (player == 2) && (result == false)) {  // player 2 : next is computer (player 1)'s chance.
			// CompGame - plays the computer's chance.
		   	kindleGame();
    	}
    	else { } // continue game.
    
		
	}
	//set score
	public void set_score(int player_number) {
    	TextView tv = (TextView) findViewById(R.id.scoreboard);
    	
    	if (player_number == 1)
    		player1score += 1;
    	else if (player_number == 2)
    		player2score += 1;
    	else ;							// Don't change score, but set the score board right.
    	
    	// player name and number relation.
    	if (gametype == 1) {
    		player_name_1 = "Computer";
    	}
    		
    	CharSequence score_txt = 
    		player_name_1 + " : " + player1score + "                   " + player_name_2 + " : " + player2score;
    	
    	tv.setText(score_txt);
    }
	//show result of game
	 public boolean show_result(CharSequence message)		//function to select the game mode
	    {   
	        AlertDialog.Builder builder = new AlertDialog.Builder(this);
	        builder.setMessage(message)
	        			.setPositiveButton("Continue", new DialogInterface.OnClickListener() {
	        				public void onClick(DialogInterface dialog, int id) {
	        		    		// reset the game environment.
	        						gameStart(player_name_1);
	        				}
	        			});
	        AlertDialog alert = builder.create();
	        alert.show();
	        return true;
	    }
	 //result check
	 public boolean result_check(int player_local) {
	    	boolean win = true;
	    	int k = 0;
	    	
	    	// check for horizontal condition only.
	    	for (int i = 0; i < 3; i++) {
	    		for (int j = 0; j < 3; j++) {
	    			if (movearr[i][j] != player_local) {		// check with player number.
	    				win = false;
	    				break;
	    			}
	    		} // column loop.
	    		if (win == true) {
					return true;
	    		}
	    		win = true;
	    	} // row loop.
	    	
	    	win = true;			// resetting win to true.
	    	
	    	// checking for vertical condition only.
	    	for (int i = 0; i < 3; i++) {
	    		for (int j = 0; j < 3; j++) {
	    			if (movearr[j][i] != player_local) {
	    				win = false;
	    				break;
	    			}
	    		} // column loop.
	    		if (win == true) {
					return true;
	    		}
	    		win = true;
	    	} // row loop.
	    	
	    	win = true;			// reset win to true.
	    	
	    	// check for diagonal condition 1.
	    	for (int i = 0; i < 3; i++)
	    		if (movearr[i][k++] != player_local) {
	    			win = false;
	    			break;
	    		}

	    	if (win == true) {
	    		return true;
	    	}
	    	
	    	k = 2;
	    	win = true;			// reset win to true;
	    	
	    	// check for diagonal condition 2.
	    	for (int i = 0; i < 3; i++)
	    		if (movearr[i][k--] != player_local) {
	    			win = false;
	    			break;
	    		}
	    	
	    	if (win == true) {
	    		return true;
	    	}
	    	
	       	return false;
	    }
// kindle ai play
	 public void kindleGame() {
	    	player = 1;
	    	count++;
	    	analysis_array();
	    	if (easy_move_win() == true)
	    		return;
	    	else if (easy_move_block() == true)
	    		return;
	    	else {
	    		f_e_map();
	    		best_move();
	    	}
	    	
	    }
	 //best move
	 public void best_move() {
	    	int highest = 0, k = 0;	// k - increment the x_pos, y_pos.
	    	int pos[][] = {{0,0},{0,0},{0,0},{0,0},{0,0},{0,0},{0,0},{0,0},{0,0}};
	    	int random_index = 0;	// stores the random index number.
	    	int x = 0, y = 0;		// compatibility with comp_play (int, int)
	    	
	    	// calculate the highest score in the map_arr.
	    	for (int i = 0; i < 3; i++)
	    		for (int j = 0; j < 3; j++)
	    			if (map_arr[i][j] > highest)
	    				highest = map_arr[i][j];
	    	
	    	// traverse map_arr and store all the highest score indices (x, y) in pos[][].
	    	for(int i = 0; i < 3; i++)
	    		for (int j = 0; j < 3; j++)
	    			if (map_arr[i][j] == highest) {
	    				pos[k][0] = i;
	    				pos[k][1] = j;
	    				k++;
	    			}
	    	
	    	// get a random index ( <= k ).
	    	random_index = ((int) (Math.random() * 10)) % (k);
	    	x = pos[random_index][0];
	    	y = pos[random_index][1];			
	    	
	    	comp_play(x, y);
	    }
	 
	 //creates maps and searches analysys array to increment
	 public void f_e_map() {
	    	int k = 0;	// for diagonal traversal.
	    	
	    	// reset map_arr to all 1's every time function is called.
	    	for (int i = 0; i < 3; i++)
	    		for (int j = 0; j < 3; j++)
	    			map_arr[i][j] = 1;
	    	
	    	// search for existing moves and mark 0 in map_arr, if found in arr.
	    	for (int i = 0; i < 3; i++)
	    		for (int j = 0; j < 3; j++)
	    			if ((movearr[i][j] == 1) || (movearr[i][j] == 2))
	    				map_arr[i][j] = 0;

	    	for (int i = 0; i < 8; i++) {
	    		if (((analysis_arr[i][0] == 1) && (analysis_arr[i][1] == 0)) || ((analysis_arr[i][0] == 0) && (analysis_arr[i][1] == 1)))
	    			if (i < 3) { 
	    				for (int j = 0; j < 3; j++)
	    					if (map_arr[i][j] != 0)
	    						map_arr[i][j] += 1;
	    			}
	    			else if (i < 6) {
	    				for (int j = 0; j < 3; j++)
	    					if (map_arr[j][i - 3] != 0)
	    						map_arr[j][i - 3] += 1;
	    			}
	    			else if (i == 6) {
	    				k = 0;
	    				for (int m = 0; m < 3; m++) {
	    					if (map_arr[m][k] != 0)
	    						map_arr[m][k] += 1;
	    					k++;
	    				}
	    			}
	    			else if (i == 7) {
	    				k = 2;
	    				for (int m = 0; m < 3; m++) {
	    					if (map_arr[m][k] != 0)
	    						map_arr[m][k] += 1;
	    					k--;
	    				}
	    			}
	    	}
	    }
	 
	 public boolean easy_move_block(){
		 boolean flag = false;
		 int i,k =0;
		 for (i =0; i<8;i++)
			 if ((analysis_arr[i][0]== 0)&& (analysis_arr[i][1] == 2)){
				 flag =true;
				 break;
			 }
		 if (flag == true) {
	    		// when position < 3, it is one of the 3 rows.
	        	if (i < 3)	{
	        		// search for the vacant position
	        		for (int j = 0; j < 3; j++)
	        			if (movearr[i][j] == 0) {
	        				comp_play(i, j);
	        				return true;
	        			}
	        	}
	        	else if (i < 6) {
	        		for (int j = 0; j < 3; j++)
	        			if (movearr[j][i - 3] == 0) {
	        				comp_play(j, (i - 3));
	        				return true;
	        			}
	        	}
	        	else if (i == 6) {
	        		for (int j = 0; j < 3; j++) {
	        			if (movearr[j][k] == 0) {
	        				comp_play(j, k);
	        				return true;
	        			}
	        			k++;
	        		}
	        	}
	        	else if (i == 7) {
	        		k = 2;
	        		for (int j = 0; j < 3; j++) {
	        			if (movearr[j][k] == 0) {
	        				comp_play(j, k);
	        				return true;
	        			}
	        			k--;
	        		}
	        	}
	    	}
	    	return false;	// false if easy move win is NOT available.
	    }
	 public boolean easy_move_win () {
	    	boolean flag = false;		// temporary flag to indicate a (2,0) find.
	    	int i, k = 0;		// k used for diagonal search.
	    	// search analysis_arr for (2,0) combination.
	    	for (i = 0; i < 8; i++)
	    		if ((analysis_arr[i][0] == 2) && (analysis_arr[i][1] == 0)) {
	    			flag = true;
	    			break;
	    		}
	    	
	    	if (flag == true) {
	    		// when position < 3, it is one of the 3 rows.
	        	if (i < 3)	{
	        		// search for the vacant position
	        		for (int j = 0; j < 3; j++)
	        			if (movearr[i][j] == 0) {
	        				comp_play(i, j);
	        				return true;
	        			}
	        	}
	        	else if (i < 6) {
	        		for (int j = 0; j < 3; j++)
	        			if (movearr[j][i - 3] == 0) {
	        				comp_play(j, (i - 3));
	        				return true;
	        			}
	        	}
	        	else if (i == 6) {
	        		for (int j = 0; j < 3; j++) {
	        			if (movearr[j][k] == 0) {
	        				comp_play(j, k);
	        				return true;
	        			}
	        			k++;
	        		}
	        	}
	        	else if (i == 7) {
	        		k = 2;
	        		for (int j = 0; j < 3; j++) {
	        			if (movearr[j][k] == 0) {
	        				comp_play(j, k);
	        				return true;
	        			}
	        			k--;
	        		}
	        	}
	    	}
	    	return false;	// false if easy move win is NOT available.
	    }
	 // make kindle move
	 public void comp_play (int x, int y) {
	       	final ImageButton ib_tmp = (ImageButton) findViewById(R.id.b1);
	       	int ib_id = ib_tmp.getId();		// initialize with 1st button's id.
	       	
	       	// set ib_id to exact ImageButton Id
	       	if ((x == 0) && (y == 0)) {	
	       		// ib_id same as initialized value.
	       	}
	       	else {
	       		if (x == 0)
	       			ib_id -= y;			// minus '-' : because id number not in proper order.
	       		else if (x == 1)
	       			ib_id += (3 - y);
	       		else if (x == 2)
	       			ib_id += (6 - y);	
	       	}
	       	
	       	// bind new ib_id Image Button to variable ib.
	       	final ImageButton ib = (ImageButton) findViewById (ib_id);
	       	
	       	// draw the symbol on the button
	       	if (user_symbol == 0)
	       		ib.setImageResource(cross);
	       	else
	       		ib.setImageResource(dot);
	       	
	       	// make the button un-clickable.
	       	ib.setClickable(false);
	   
	       	// call the after_move function with the arguments.
	       	after_move(ib);
	    }
	    
	 public void analysis_array() {
	    	
	    	// initialize to zero every time this function is called.
	    	for (int i = 0; i < 8; i++)
	    		analysis_arr[i][0] = analysis_arr[i][1] = 0;
	    	
	    	// row-wise traversal and increment the value.
	    	for (int i = 0; i < 3; i++)
	    		for (int j = 0; j < 3; j++)
	    			if (movearr[i][j] == 1) // 1 = player 1 : computer
	    				analysis_arr[i][0] += 1;
	    			else if(movearr[i][j] == 2) // 2 = player 2 : human
	    				analysis_arr[i][1] += 1;
	    	
	    	
	    	
	    	// column-wise traversal and increment the value.
	    	for (int i = 0; i < 3; i++)
	    		for (int j = 0; j < 3; j++)
	    			if (movearr[j][i] == 1) 			// 1 = player 1
	    				analysis_arr[i + 3][0] += 1;
	    			else if(movearr[j][i] == 2) 		// 2 = player 2, i + 3 to change index to refer to column.
	    				analysis_arr[i + 3][1] += 1;
	    	
	    	
	    	// diagonal 1 traversal.
	    	int k = 0;
	    	for (int i = 0; i < 3; i++) {
	    		if (movearr[i][k] == 1)
	    			analysis_arr[6][0] +=1;
	    		else if (movearr[i][k] == 2)
	    			analysis_arr[6][1] +=1;
	    		k++;
	    	}
	    	
	    	// diagonal 2 traversal.
	    	// --> reset k to point to the 1st row, and last(3rd) element.
	    	k = 2;						
	    	for (int i = 0; i < 3; i++) {
	    		if (movearr[i][k] == 1)
	    			analysis_arr[7][0] +=1;
	    		else if (movearr[i][k] == 2)
	    			analysis_arr[7][1] +=1;
	    		k--;
	    	}
	    		
	    	// ------ end of analysis array initialization ------------- //
	    }
}
