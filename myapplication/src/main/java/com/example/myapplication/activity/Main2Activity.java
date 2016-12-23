package com.example.myapplication.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.myapplication.R;
import com.example.myapplication.utils.DensityUtil;
import com.example.myapplication.widget.CirclePercentView;
import com.example.myapplication.widget.TasksCompletedView;

public class Main2Activity extends AppCompatActivity {
    private TextInputLayout inputLayout;
    private TasksCompletedView mTasksView;

    private int mTotalProgress;
    private int mCurrentProgress;

    private Button mButton;
    private CirclePercentView mCirclePercentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        inputLayout = (TextInputLayout) findViewById(R.id.textInput);
        initInputLayout();
        initVariable();
        initView();

      //  new Thread(new ProgressRunable()).start();

        mCirclePercentView = (CirclePercentView) findViewById(R.id.circleView);
       /* int widthPixels = DensityUtil.getDisplayMetrics(this).widthPixels;
       LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mCirclePercentView.getLayoutParams();
        params.width = (int) (0.9 * widthPixels);
        params.height = (int) (0.9 * widthPixels);
        mCirclePercentView.setLayoutParams(params);*/
        mCirclePercentView.setTextSize(DensityUtil.sp2px(this,40));
        mCirclePercentView.setText("已移除！！hahahhhshshhs");
        mButton = (Button) findViewById(R.id.button1);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int n = (int)(Math.random()*100);
               mCirclePercentView.setPercent(n);
               // mTasksView.setProgress(n);
            }
        });
    }

    private void initInputLayout() 
    {
       
        inputLayout.setHint("请输入姓名:");

        EditText editText = inputLayout.getEditText();
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length()>4){
                    inputLayout.setErrorEnabled(true);
                    inputLayout.setError("姓名长度不能超过4个");
                }else{
                    inputLayout.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }
    private void initVariable() {
        mTotalProgress = 80;
        mCurrentProgress = 0;
    }

    private void initView() {
        mTasksView = (TasksCompletedView) findViewById(R.id.tasks_view);
    }

    class ProgressRunable implements Runnable {

        @Override
        public void run() {
            while (mCurrentProgress < mTotalProgress) {
                mCurrentProgress += 1;
                mTasksView.setProgress(mCurrentProgress);
                try {
                    Thread.sleep(100);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }


}
