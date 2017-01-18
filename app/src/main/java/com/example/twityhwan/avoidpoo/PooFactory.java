package com.example.twityhwan.avoidpoo;

import android.content.Context;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.view.animation.Animation;

import java.util.ArrayList;

import static com.example.twityhwan.avoidpoo.R.anim.poo_animation;

/**
 * Created by twityhwan on 17. 1. 9.
 */

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

        public Poo(Context context, int w, int h/*, float s*/, float x, float y) {
            super(context);
            setImageResource(R.drawable.poo);
            LayoutParams params = new LayoutParams(w, h);
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

    public Poo create(int w, int h/*, float s*/, float x, float y) {
        Poo t_poo = new Poo(m_context, w, h/*, s*/, x, y);
        m_pooList.add(t_poo);
        return t_poo;
    }

    public PooFactory(Context context) {
        m_context = context;
        m_pooList = new ArrayList<Poo>();
    }

    private Context m_context;
    private ArrayList<Poo> m_pooList;
}