package com.example.somemyidea.activity;

import android.animation.Animator;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.FrameLayout;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.somemyidea.R;
import com.example.somemyidea.rxjava.RxjavaActivity;
import com.example.somemyidea.utils.EnumUtils;
import com.example.somemyidea.utils.StringBufferUtils;

public class MainActivity extends AppCompatActivity {
    private Button btnTotast;
    private Button btnIntentService;
    private ToggleButton toggleButton;
    private CheckedTextView checkedTextView;
    private AppCompatActivity appCompatActivity;
    private Dialog dialog;
    private Button btnRcvDecoration;
    private FrameLayout flOnclickTest;
    private Button btnAlertDialog;
    AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        appCompatActivity = this;
        btnTotast = (Button) findViewById(R.id.btnTotast);
        btnIntentService = (Button) findViewById(R.id.btnIntentService);
        toggleButton = (ToggleButton) findViewById(R.id.toggleButton);
        checkedTextView = (CheckedTextView) findViewById(R.id.checkedTextView);
        btnRcvDecoration = (Button) findViewById(R.id.btnRcvDecoration);
        btnAlertDialog = (Button) findViewById(R.id.btnAlertDialog);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, StringBufferUtils.contactObjectToString("我是",10," 哈哈",20.5,true,20f), Snackbar.LENGTH_LONG)
                        .setAction("Action", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                startActivity(new Intent(MainActivity.this,ToastActivity.class));
                            }
                        }).show();
            }
        });
        flOnclickTest = (FrameLayout) findViewById(R.id.flOnclickTest);
        flOnclickTest.setClickable(true);
        flOnclickTest.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);
        flOnclickTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,RxjavaActivity.class));
            }
        });
        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) 
        {
            btnTotast.setOutlineProvider(new ViewOutlineProvider() 
            {
                @Override
                public void getOutline(View view, Outline outline) 
                {
                    int shapeSize = (int) getResources().getDimension(R.dimen.fab_margin);
                    shapeSize = btnTotast.getWidth();
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) 
                    {
                      //  outline.setRoundRect(0, 0, shapeSize / 2, shapeSize / 2, shapeSize);
                        outline.setRect(0, 0, shapeSize, shapeSize/2);
                        
                    }
                }
            });
            btnTotast.setClipToOutline(true);
        }*/
        

        bindListener();
    }

    private void bindListener()
    {
        btnTotast.setOnClickListener(new View.OnClickListener() 
        {
            @Override
            public void onClick(View v) 
            {
                //Circular Reveal
                /*Animator animator = createAnimator(v);
                if (animator!=null)
                    animator.start();*/
             
               /* View view = LayoutInflater.from(appCompatActivity).inflate(R.layout.dialog_quit_upload, null);
                TextView tv_quit_upload = (TextView) view.findViewById(R.id.tv_dialog_quit_upload);
                TextView tv_quit_cancle = (TextView) view.findViewById(R.id.tv_dialog_quit_cancle);
                if (dialog==null)
                    dialog = new Dialog(appCompatActivity, R.style.dialog);
                dialog.show();
                WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
                lp.width = DensityUtil.getDisplayMetrics(appCompatActivity).widthPixels - DensityUtil.dip2px(appCompatActivity, 24f);
                //重新获取到底部的距离，大于0位于基础之上，小于0位于之下。
                lp.y = DensityUtil.dip2px(appCompatActivity, 12f);
                Window window = dialog.getWindow();
                window.setAttributes(lp);
                window.setContentView(view);
                window.setGravity(Gravity.BOTTOM);
                dialog.setCanceledOnTouchOutside(true);
                tv_quit_cancle.setOnClickListener(new View.OnClickListener() 
                {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });

                tv_quit_upload.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v) {
                        setResult(RESULT_CANCELED);
                        finish();
                    }
                });
*/
                  startActivity(new Intent(MainActivity.this,RxjavaActivity.class));
            }
        });
            
        
        btnIntentService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,TestIntentServiceActivity.class));
            }
        });
        toggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,SingleItemRecycleViewActivity.class));  
            }
        });
        checkedTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,ItemTouchHelperActivity.class));
            }
        });
        btnRcvDecoration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,ItemDecorationActivity.class));
            }
        });
        EnumUtils enumUtils = new EnumUtils();
       // enumUtils.chooseEnum();
        btnAlertDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                //生成AlerDialog
                if (alertDialog==null)
                 alertDialog = new AlertDialog.Builder(MainActivity.this)
                        .setMessage("我是标题")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        }).setOnDismissListener(new DialogInterface.OnDismissListener() {
                            @Override
                            public void onDismiss(DialogInterface dialog) {
                                Toast.makeText(MainActivity.this, "我是dismiss监听", Toast.LENGTH_SHORT).show();
                            }
                        }).setOnCancelListener(new DialogInterface.OnCancelListener() {
                            @Override
                            public void onCancel(DialogInterface dialog) {
                                Toast.makeText(MainActivity.this, "我是Cancel监听", Toast.LENGTH_SHORT).show();
                            }
                        }).setCancelable(true)
                        .setTitle("标题啊")
                        .create();
                alertDialog.show();
                
            }
        });
    }
    
    
    public Animator createAnimator(View v)
    {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) 
        {
            Animator animator = ViewAnimationUtils.createCircularReveal(v, v.getWidth() / 2, v.getHeight() / 2, v.getWidth(), 0);
            animator.setInterpolator(new AccelerateInterpolator());
            animator.setDuration(500);
            return animator;
        }
        return null;

    }

   

}
