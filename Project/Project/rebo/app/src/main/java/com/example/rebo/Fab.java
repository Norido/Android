package com.example.rebo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.view.animation.TranslateAnimation;

import com.gordonwong.materialsheetfab.AnimatedFab;

public class Fab extends FloatingActionButton implements AnimatedFab {

    private static final int FAB_ANIM_DURATION = 500;
    TranslateAnimation animation, animationD;

    public Fab(Context context) {
        super(context);
    }
    public Fab(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Fab(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    /**
     * Shows the FAB.
     */
    @Override
    public void show() {
        show(0, 0);
    }

    /**
     * Shows the FAB and sets the FAB's translation.
     *
     * @param translationX translation X value
     * @param translationY translation Y value
     */
    @Override
    public void show(float translationX, float translationY) {

        if (getVisibility() != View.VISIBLE) {
            animation = new TranslateAnimation(0.0f, 0.0f, 600.0f, 0.0f);
            animation.setDuration(FAB_ANIM_DURATION);
            animation.setInterpolator(getInterpolator());
            startAnimation(animation);
        }
    }

    /**
     * Hides the FAB.
     */
    @Override
    public void hide() {
        // NOTE: This immediately hides the FAB. An animation can
        // be used instead - see the sample app.
        if (getVisibility() == View.VISIBLE) {


            // Animate FAB shrinking
            animationD = new TranslateAnimation(0.0f, 0.0f, 0.0f, 600.0f);
            animationD.setDuration(FAB_ANIM_DURATION);
            animationD.setInterpolator(getInterpolator());
            startAnimation(animationD);
        }
    }
    private Interpolator getInterpolator() {
        return AnimationUtils.loadInterpolator(getContext(), R.interpolator.msf_interpolator);
    }
}
