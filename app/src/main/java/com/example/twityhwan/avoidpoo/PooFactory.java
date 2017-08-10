package com.example.twityhwan.avoidpoo;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout.LayoutParams;
import android.view.animation.Animation;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.Random;

import static com.example.twityhwan.avoidpoo.R.anim.poo_animation;

class PooFactory {
    private final int FALLING_DURATION = 5000;
    private final int DELAY = 1000;
    private Context m_context;
    private ArrayList<Poo> m_pooList;
    ArrayList<Animator> m_animatorList;
    private int m_width;
    private int m_height;
    boolean isPaused = false;
    private Random m_random = new Random();

    PooFactory(Context context) {
        m_context = context;
        m_pooList = new ArrayList<>();
        m_animatorList = new ArrayList<>();
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        m_width = metrics.widthPixels;
        m_height = metrics.heightPixels;
    }

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
            params.setMargins(m_random.nextInt(m_width-w), 0, 0, 0);
            setLayoutParams(params);
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
            final Poo t_poo = new Poo(m_context, w, h);
            ll.addView(t_poo);
            m_pooList.add(t_poo);

            ValueAnimator animator = ValueAnimator.ofFloat(0, m_height);
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    float value = (float) animation.getAnimatedValue();
                    t_poo.setTranslationY(value);
                }
            });
            animator.setDuration(FALLING_DURATION);
            animator.setStartDelay(m_random.nextInt(DELAY)*(i+1));
            animator.start();
            m_animatorList.add(animator);
        }
    }

    void pause() {
        if (isPaused) return;

        isPaused = true;
        int num = m_animatorList.size();
        for (int i=0; i<num; i++) {
            if (!m_animatorList.get(i).isPaused()) {
                m_animatorList.get(i).pause();
            }
        }
    }

    void start() {

    }

    void restart() {

    }

    void resume() {
        if (!isPaused) return;

        isPaused = false;
        int num = m_animatorList.size();
        for (int i=0; i<num; i++) {
            m_animatorList.get(i).resume();
        }
    }

    private class PooAnimator extends ValueAnimator implements Animation.AnimationListener {

        PooAnimator() {
        }

        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {

        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }
}

