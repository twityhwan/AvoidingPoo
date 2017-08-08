package com.example.twityhwan.avoidpoo;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.os.Handler;
import android.os.Message;
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

class PooFactory {
    private Context m_context;
    private ArrayList<Poo> m_pooList;
    private int m_width;
    private int m_height;

    protected class Poo extends android.support.v7.widget.AppCompatImageView {
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
    }

    Poo create(int w, int h/*, float s*/) {
        Poo t_poo = new Poo(m_context, w, h/*, s*/);
        m_pooList.add(t_poo);
        return t_poo;
    }

    void create(int w, int h, int num) {
        RelativeLayout ll = (RelativeLayout) ((Activity)m_context).findViewById(R.id.poo_layout);
        for (int i=0; i<num; i++) {
            Poo t_poo = new Poo(m_context, w, h);
            ll.addView(t_poo);
            m_pooList.add(t_poo);
        }
    }

    PooFactory(Context context) {
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
}

