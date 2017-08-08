package com.example.twityhwan.avoidpoo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class Main extends AppCompatActivity {
    int dw, dh;
    ImageView player;
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

        player = (ImageView) findViewById(R.id.img_player);

        // Set button listener
        findViewById(R.id.btn_left).setOnClickListener(buttonListener);
        findViewById(R.id.btn_right).setOnClickListener(buttonListener);
        findViewById(R.id.btn_pause).setOnClickListener(buttonListener);

        // Create poo
        PooFactory pooFact = new PooFactory(this);
        pooFact.create(100, 100, 10);

    }

    Button.OnClickListener buttonListener = new Button.OnClickListener() {

        @Override
        public void onClick(View v) {
            int id = v.getId();
            if (id == R.id.btn_pause) {
                Log.d("buttonListener", "btn_pause");
            } else if (id == R.id.btn_left) {
                Log.d("buttonListener", "btn_left");
                int nextX = (int) (player.getX()-10);
                if (nextX>0) {
                    player.setX(nextX);
                    player.invalidate();
                }
            } else if (id == R.id.btn_right) {
                Log.d("buttonListener", "btn_right");
                int nextX = (int) (player.getX()+10);
                if (nextX+player.getWidth()<dw) {
                    player.setX(nextX);
                    player.invalidate();
                }
            }
        }
    };
}
