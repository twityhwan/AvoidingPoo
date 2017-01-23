package com.example.twityhwan.avoidpoo;

import android.content.Context;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.view.animation.Animation;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.Random;

import static com.example.twityhwan.avoidpoo.R.anim.poo_animation;

public class PooFactory {
    public class Poo extends ImageView {
        public Poo(Context context) {
            super(context);
            setImageResource(R.drawable.poo);
            LayoutParams params = new LayoutParams(100, 100);
            setLayoutParams(params);
            Animation ani = AnimationUtils.loadAnimation(context, poo_animation);
            setAnimation(ani);

        }

        public Poo(Context context, int w, int h/*, float s*/) {
            super(context);
            setImageResource(R.drawable.poo);
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(w, h);
            params.setMargins(getRandomPosition(), 0, 0, 0);
            setLayoutParams(params);
            Animation ani = AnimationUtils.loadAnimation(context, poo_animation);
            setAnimation(ani);
        }

        float m_height;
        float m_width;
        float m_speed;
        float m_x;
        float m_y;
    }

    public Poo create(int w, int h/*, float s*/) {
        Poo t_poo = new Poo(m_context, w, h/*, s*/);
        m_pooList.add(t_poo);
        return t_poo;
    }

    public PooFactory(Context context) {
        m_context = context;
        m_pooList = new ArrayList<Poo>();
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        m_width = metrics.widthPixels;
        m_height = metrics.heightPixels;
    }

    private int getRandomPosition() {
        Random r = new Random();
        return r.nextInt(m_width);
    }

    private Context m_context;
    private ArrayList<Poo> m_pooList;
    private int m_width;
    private int m_height;
}

