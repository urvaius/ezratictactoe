package com.arne5.ezratictactoe;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageButton;

public class TicButton extends ImageButton{

	public TicButton(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		
		
	}
	public TicButton(Context context, AttributeSet attrs){
		super(context, attrs);
		
		
		
		
	}
	public TicButton(Context context, AttributeSet attrs, int defStyle){
		super(context, attrs, defStyle);
		
	}
	public boolean checkX(boolean xcheck){
		//if true is x if false is o
		
		
		return xcheck;
		
	}
	public boolean checkO(boolean ocheck){
		
		
		return ocheck;
	}

}
