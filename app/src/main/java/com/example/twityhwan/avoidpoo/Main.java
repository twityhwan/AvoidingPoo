package com.example.twityhwan.avoidpoo;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;

import android.widget.RelativeLayout;

import static java.lang.System.*;

public class Main extends AppCompatActivity {

    PlayerView pv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pv = new PlayerView(this);
        addContentView(pv, new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        findViewById(R.id.btn_left).setOnClickListener(pv.btnLeftClick);
        findViewById(R.id.btn_right).setOnClickListener(pv.btnRightClick);
        findViewById(R.id.btn_pause).setOnClickListener(btnPauseClick);


        PooFactory pooFact = new PooFactory(this);
        RelativeLayout ll = (RelativeLayout)findViewById(R.id.poo_layout);
        for(int i=0; i<1; i++) {
            ll.addView(pooFact.create(100, 100, 0, 0));
        }

    }

    class PlayerView extends View{
        Point outSize = new Point(); // width, height width = outSize.x, height = outSize.y
        int x,y;
        int sx;
        int pw, ph;
        Bitmap player;

        public PlayerView( Context context){
            super(context);
            Display display = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
            display.getSize(outSize);

            player = BitmapFactory.decodeResource(context.getResources(), R.drawable.player);
            pw = player.getWidth();
            ph = player.getHeight();
            x = 309;
            y = 755;
            sx = 20;

        }

        public void onDraw(Canvas canvas){
            if(x<sx){
                x = 0;
            }
            if(x>outSize.x-pw){
                x=outSize.x-pw;
            }

            canvas.drawBitmap(player, x, y, null);
        }
        Button.OnClickListener btnLeftClick = new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                out.println("left btn clicked");
                pv.x-=sx;
                invalidate();
            }
        };
        Button.OnClickListener btnRightClick = new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                out.println("right btn clicked");
                pv.x+=sx;
                invalidate();
            }
        };
    }


    Button.OnClickListener btnPauseClick = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            out.println("pause btn clicked");
        }
    };
}
