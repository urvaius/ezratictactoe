package com.arne5.ezratictactoe;



import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

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
					new_game(p1name.getText());
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
		else if(item.getItemid()==1)//options menu
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
		
	}

}
