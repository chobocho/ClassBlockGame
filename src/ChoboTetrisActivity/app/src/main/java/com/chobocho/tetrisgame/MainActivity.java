package com.chobocho.tetrisgame;

import android.app.*;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.chobocho.player.Player;
import com.chobocho.player.PlayerImpl;
import com.chobocho.tetris.R;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        initUI();
    }

    private void initUI() {
        Button startTetris = (Button) findViewById(R.id.btnTetris);
        startTetris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,ChoboTetrisActivity.class);
                startActivity(intent);
            }
        });
        Button startItemTetris = (Button) findViewById(R.id.btnItemTetris);
        Button quitTetris = (Button) findViewById(R.id.btnQuit);
        quitTetris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 finish();
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}