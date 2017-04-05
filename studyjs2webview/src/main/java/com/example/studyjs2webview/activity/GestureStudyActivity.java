package com.example.studyjs2webview.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.studyjs2webview.R;

/**
 * 手势识别学习(实现向左滑动结束当前的activity)
 */
public class GestureStudyActivity extends AppCompatActivity implements View.OnTouchListener,GestureDetector.OnGestureListener {
    TextView tvGestureListener;
    private GestureDetector gestureDetector;
    private final String TAG = this.getClass().getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesture_study);
        tvGestureListener= (TextView) findViewById(R.id.tvGestureListener);
        gestureDetector = new GestureDetector(this, this);
        tvGestureListener.setOnTouchListener(this);
    }
    /**
     * 在onTouch()方法中，调用GestureDetector的onTouchEvent()方法，将捕捉到的MotionEvent交给GestureDetector处理
     *
     * @param v
     * @param event
     * @return
     */
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent e) 
    {
        Log.e(TAG, "onDown");
        //默认值返回false
        return true;
        //return false;
    }
    /**
     * 用户轻触触摸屏，尚未松开或拖动
     *
     * @param e
     */
    @Override
    public void onShowPress(MotionEvent e) {
        Log.e(TAG, "onShowPress");
    }
    /**
     * 用户轻触触摸屏后松开
     *
     * @param e
     * @return
     */
    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        Log.e(TAG, "onSingleTapUp");
        return false;
    }

    /**
     * 用户按下触摸屏，并拖动，由1个MotionEvent ACTION_DOWN, 多个ACTION_MOVE触发
     *
     * @param e1
     * @param e2
     * @param distanceX
     * @param distanceY
     * @return
     */
    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        Log.e(TAG, "onScroll");
        return false;
    }
    /**
     * 用户长按触摸屏，由多个MotionEvent ACTION_DOWN触发
     *
     * @param e
     */
    @Override
    public void onLongPress(MotionEvent e) {
        Log.e(TAG, "onLongPress");
    }
    /**
     * 用户按下触摸屏、快速移动后松开，由1个MotionEvent ACTION_DOWN, 多个ACTION_MOVE, 1个ACTION_UP触发
     *
     * @param e1
     * @param e2
     * @param velocityX X轴上的移动速度，像素/秒
     * @param velocityY Y轴上的移动速度，像素/秒
     * @return
     */
    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        Log.e(TAG, "onFling");
        //判断变化，滑的太慢，不处理
        if (Math.abs(e1.getRawX()-e2.getRawX())<200)
            Toast.makeText(this, "你画的太慢了", Toast.LENGTH_SHORT).show();
        //向右滑动，结束当前activity
        if ((e2.getRawX()-e1.getRawX()>200))
        {
            GestureStudyActivity.this.finish();
            //结束动画
            overridePendingTransition(R.anim.left_other_to_right,R.anim.self_to_right);
        }
            
        
        return false;
    }
}
