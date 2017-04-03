package com.example.studyjs2webview.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.studyjs2webview.R;

/**
 * ViewTreeObserver的学习
 */
public class ViewTreeObserverActivity extends AppCompatActivity implements ViewTreeObserver.OnGlobalFocusChangeListener 
,ViewTreeObserver.OnGlobalLayoutListener,ViewTreeObserver.OnPreDrawListener,ViewTreeObserver.OnTouchModeChangeListener
,View.OnClickListener{

    private LinearLayout llViewTree;
    EditText et_1,et_2;
    TextView tv_show;
    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_tree_observer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        llViewTree = (LinearLayout) findViewById(R.id.content_view_tree_observer);
        //得到实例
        ViewTreeObserver treeObserver = llViewTree.getViewTreeObserver();
        treeObserver.addOnPreDrawListener(this);
        treeObserver.addOnGlobalFocusChangeListener(this);
        treeObserver.addOnTouchModeChangeListener(this);
        treeObserver.addOnGlobalLayoutListener(this);
        et_1= (EditText) findViewById(R.id.et_1);
        et_2= (EditText) findViewById(R.id.et_2);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        findViewById(R.id.btn).setOnClickListener(this);
        tv_show= (TextView) findViewById(R.id.tv_show);
    }

    /**
     * Callback method to be invoked when the focus changes in the view tree. When
     * the view tree transitions from touch mode to non-touch mode, oldFocus is null.
     * When the view tree transitions from non-touch mode to touch mode, newFocus is
     * null. When focus changes in non-touch mode (without transition from or to
     * touch mode) either oldFocus or newFocus can be null.
     *
     * @param oldFocus The previously focused view, if any.
     * @param newFocus The newly focused View, if any.
     */
    @Override
    public void onGlobalFocusChanged(View oldFocus, View newFocus)
    {
        //全局焦点测试,主要监听全局的检点改变，刚进入这个界面没有oldFocus。
        if(oldFocus!=null) {
            tv_show.setText("Focus change from " + oldFocus.getTag() + " to " + newFocus.getTag());
        }else{
            tv_show.setText( newFocus.getTag()+"get focus");
        }
        
    }

    /**
     * Callback method to be invoked when the global layout state or the visibility of views
     * within the view tree changes
     * 当在一个视图树中全局布局发生改变或者视图树中的某个视图的可视状态发生改变时，所要调用的回调函数的接口类
     * 注意：在测试的时候发现使用
        et_1.setVisibility(View.INVISIBLE);时并不会触发OnGlobalLayoutListener而只能使用
        et_1.setVisibility(View.GONE);
     */
    @Override
    public void onGlobalLayout()
    {
        if(et_1.isShown()){
            tv_show.setText("EditText1 显示");
        }else{
            tv_show.setText("EditText1 隐藏");
        }
    }

    @Override
    public boolean onPreDraw() {
        //返回 true 继续绘制，返回false取消。
        //默认返回false,不绘制页面，整个页面啥也灭有
        //关于OnPreDrawListener的使用，有一个例子就是CoordinatorLayout调用Behavior的onDependentViewChanged
        // 就是通过注册OnPreDrawListener接口，在绘制的时候检查界面是否发生变化，
        // 如果变化就调用Behavior的onDependentViewChanged。
        return true;
    }

    @Override
    public void onTouchModeChanged(boolean isInTouchMode) {
        
    }

    @Override
    public void onClick(View v) 
    {
        //if (v.getId())
        int id = v.getId();
        switch (id) {
            case R.id.btn:
                if(et_1.isShown()){
                    et_1.setVisibility(View.INVISIBLE);
                }else{
                    et_1.setVisibility(View.VISIBLE);
                }
                break;
            default:
                break;
        }
    }
}
