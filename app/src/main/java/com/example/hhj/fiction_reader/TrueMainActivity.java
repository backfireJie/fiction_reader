package com.example.hhj.fiction_reader;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageButton;
import android.widget.ImageView;

/**
 * Created by XX on 2016/10/13.
 */
public class TrueMainActivity extends AppCompatActivity implements View.OnClickListener{
    private ImageView ivGg;


    private ImageView ivRound;
    private ImageButton ibBox;
    private ImageButton ibFoundation;
    private ImageButton ibWard;

    private final static int clickBox = 1;
    private final static int clickFuture = 2;
    private final static int clickAward = 3;
    private int currentClick = 0;

    private ObjectAnimator animatorBG;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_true_main);
        getSupportActionBar().hide();
        initView();
    }

    private void initView() {
        ivGg = (ImageView) findViewById(R.id.iv_bg);
        bgRotation();
        ivRound = (ImageView) findViewById(R.id.iv_round);
        ibBox =(ImageButton) findViewById(R.id.ib_box);
        ibFoundation =(ImageButton) findViewById(R.id.ib_foundation);
        ibWard = (ImageButton) findViewById(R.id.ib_award);
        ibBox.setOnClickListener(this);
        ibWard.setOnClickListener(this);
        ibFoundation.setOnClickListener(this);

    }

    private void bgRotation() {
        ObjectAnimator animatorBG = ObjectAnimator.ofFloat(ivGg, "rotation", 0f, 360f);
        animatorBG.setDuration(60000);
        LinearInterpolator lin = new LinearInterpolator();
        animatorBG.setInterpolator(lin);
        animatorBG.setRepeatCount(-1);
        animatorBG.start();

    }
    private void enlargeRound(){

        AnimatorSet animSet = new AnimatorSet();
        ObjectAnimator animatorX = ObjectAnimator.ofFloat(ivRound,"scaleX",1f,0f,1f);
        ObjectAnimator animatorY = ObjectAnimator.ofFloat(ivRound,"scaleY",1f,0f,1f);

        animSet.play(animatorX).with(animatorY);
        animSet.setDuration(2000);
        animSet.start();

        animSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
               switch(currentClick){
                   case clickBox:
                       Intent intent = new Intent(TrueMainActivity.this,MainActivity.class);
                       startActivity(intent);
                       currentClick = 0;
                       break;
                   case clickFuture:

                       currentClick = 0;
                       break;
                   case clickAward:
                       Intent intent2 = new Intent(TrueMainActivity.this,FutureMasterActivity.class);
                       startActivity(intent2);
                       currentClick = 0;
                       break;
                   default:
                       break;
               }


            }
        });


    }


    @Override
    public void onClick(View v) {
        ivGg.clearAnimation();
        enlargeRound();
        switch(v.getId()){
            case R.id.ib_box:
                currentClick = clickBox;
                break;
            case R.id.ib_foundation:
                currentClick = clickFuture;
                break;
            case R.id.ib_award:

                currentClick = clickAward;







        }
    }
}
