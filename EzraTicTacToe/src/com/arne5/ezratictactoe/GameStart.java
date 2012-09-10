package com.arne5.ezratictactoe;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class GameStart extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_start);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_game_start, menu);
        return true;
    }
}
