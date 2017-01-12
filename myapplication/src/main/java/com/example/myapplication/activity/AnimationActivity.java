package com.example.myapplication.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListenerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.myapplication.R;

public class AnimationActivity extends AppCompatActivity {

    private ImageView imageView;
    private RelativeLayout activity_animation;
    private ImageView imgBlue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        imageView = (ImageView) findViewById(R.id.imgAnimation);
        activity_animation = (RelativeLayout) findViewById(R.id.activity_animation);
        imgBlue = (ImageView) findViewById(R.id.imgBlue);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity_animation.post(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                        {
                            activity_animation.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                            Animator animation = ViewAnimationUtils.createCircularReveal( activity_animation,(int) (activity_animation.getWidth()*0.5), (int) (activity_animation.getHeight()*0.5), 0, activity_animation.getHeight());
                            //  animation.setStartDelay(1000);
                            animation.setInterpolator(new AccelerateDecelerateInterpolator());
                            animation.setDuration(2000);
                            animation.addListener(new AnimatorListenerAdapter() {


                                @Override
                                public void onAnimationEnd(Animator animation) {
                                    super.onAnimationEnd(animation);
                                    startActivity(new Intent(AnimationActivity.this,Main2Activity.class));

                                }
                            });
                            animation.start();
                        }else
                        {
                            imgBlue.setVisibility(View.VISIBLE);
                            ViewCompat.animate(imgBlue).scaleX(10.0f).scaleY(10.0f).alpha(0.9f).translationZ(20f).setDuration(2000)
                                    .setInterpolator(new AccelerateDecelerateInterpolator()).setListener(new ViewPropertyAnimatorListenerAdapter()
                            {
                                @Override
                                public void onAnimationEnd(View view)
                                {
                                    super.onAnimationEnd(view);
                                    //跳转新页面
                                    startActivity(new Intent(AnimationActivity.this,Main2Activity.class));
                                }
                            }).start();

                        }

                    }
                });
                
            }
        });
       
       // AnimationSet set = new AnimationSet();
        //Animation animation= AnimationUtils.loadAnimation(this, R.anim.anim_set);
        //animation.
       
       // ViewPropertyAnimatorCompat compat = ViewPropertyAnimatorCompat.BaseViewPropertyAnimatorCompatImpl
       
    }
}
