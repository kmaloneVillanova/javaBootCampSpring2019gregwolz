package com.example.restaurantfinder;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements Animation.AnimationListener {

    boolean b1nButtonRotation = true;
    int intNumber=6;
    long lngDegrees=0;
    SharedPreferences sharedPreferences;

    ImageView selected,imageRoulette;

    Button b_start;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        getWindow().addFlags(1024);
        requestWindowFeature(1);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b_start= (Button)findViewById(R.id.buttonStart);

        selected = (ImageView)findViewById(R.id.ImageSelected);
        imageRoulette = (ImageView)findViewById(R.id.rouletteImage);

        this.sharedPreferences= PreferenceManager.getDefaultSharedPreferences(this);
        this.intNumber=this.sharedPreferences.getInt("INT_NUMBER",6);
        //setImageRoulette(this.intNumber);
        imageRoulette.setImageDrawable(getResources().getDrawable(R.drawable.wheel));
    }

    @Override
    public void onAnimationStart(Animation animation) {

        this.b1nButtonRotation=false;
        b_start.setVisibility(View.VISIBLE);

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        Toast toast= Toast.makeText(this, " " +String.valueOf((int)(((double)this.intNumber)
                - Math.floor(((double)this.lngDegrees)/ (360.0d / ((double)this.intNumber)))))+ " ",0);
        toast.setGravity(49,0,0);
        toast.show();
        this.b1nButtonRotation=true;
        b_start.setVisibility(View.VISIBLE);
    }

    @Override
    public void onAnimationRepeat(Animation animation) {


    }

    public void onClickButtonRotation(View v){

    if(this.b1nButtonRotation){
        int ran = new Random().nextInt(360)+3600;
        RotateAnimation rotateAnimation = new RotateAnimation((float)this.lngDegrees,(float)
                (this.lngDegrees+ ((long)ran)),1,0.5f,1,0.5f);

        this.lngDegrees = (this.lngDegrees + ((long)ran)) %360;
        rotateAnimation.setDuration((long)ran);
        rotateAnimation.setFillAfter(true);
        rotateAnimation.setInterpolator(new DecelerateInterpolator());
        rotateAnimation.setAnimationListener(this);
        imageRoulette.setAnimation(rotateAnimation);
        imageRoulette.startAnimation(rotateAnimation);

    }
    }



}
