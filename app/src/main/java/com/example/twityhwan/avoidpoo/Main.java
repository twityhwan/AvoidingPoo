package com.example.twityhwan.avoidpoo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.RelativeLayout;

public class Main extends AppCompatActivity {
    int dw, dh;
    Player player;
    PooFactory pooFact;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RelativeLayout layout = (RelativeLayout)findViewById(R.id.activity_main);
        ViewTreeObserver observer = layout.getViewTreeObserver();
        observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                dw = layout.getWidth();
                dh = layout.getHeight();
                Log.d("onCreate", "dw: "+dw+", dh: "+dh);
            }
        });

        player = new Player(this);

        // Set button listener
        //findViewById(R.id.btn_left).setOnClickListener(buttonListener);
        //findViewById(R.id.btn_right).setOnClickListener(buttonListener);
        findViewById(R.id.btn_pause).setOnClickListener(buttonListener);

        findViewById(R.id.btn_left).setOnTouchListener(buttonTouchListener);
        findViewById(R.id.btn_right).setOnTouchListener(buttonTouchListener);

        // Create poo
        pooFact = new PooFactory(this);
        pooFact.create(100, 100, 10);

    }

    Button.OnClickListener buttonListener = new Button.OnClickListener() {

        @Override
        public void onClick(View v) {
            int id = v.getId();
            if (id == R.id.btn_pause) {
                Log.d("buttonListener", "btn_pause");
                pooFact.pause();
            } else if (id == R.id.btn_left) {
                Log.d("buttonListener", "btn_left");
                player.moveToLeft();
            } else if (id == R.id.btn_right) {
                Log.d("buttonListener", "btn_right");
                player.moveToRight();
            }
        }
    };

    Button.OnTouchListener buttonTouchListener = new Button.OnTouchListener() {

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            //Log.d("OnTouchListener", "onTouch event : "+event);
            int action = event.getAction();
            int id = v.getId();
            if (action == MotionEvent.ACTION_MOVE) {
                //buttonListener.onClick(v);
            } else if (action == MotionEvent.ACTION_DOWN) {
                switch(id) {
                    case R.id.btn_left:
                        player.moveToLeft();
                        break;
                    case R.id.btn_right:
                        player.moveToRight();
                        break;
                }
            } else if (action == MotionEvent.ACTION_UP) {
                player.stop();
            }
            return false;
        }
    };
}
