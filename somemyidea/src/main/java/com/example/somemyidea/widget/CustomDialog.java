package com.example.somemyidea.widget;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.somemyidea.R;


public class CustomDialog extends Dialog {

    private final int DIALOG_BUTTON_TYPE_ONE = 1;
    private final int DIALOG_BUTTON_TYPE_TWO = 2;
    private final int DIALOG_BUTTON_TYPE_THREE = 3;

    private TextView tvTitle;
    private TextView tvContent;

    private Button btnLeft;
    private Button btnMiddle;
    private Button btnRight;


    private View.OnClickListener leftButtonDialogListener;
    private View.OnClickListener middleButtonDialogListener;
    private View.OnClickListener rightButtonDialogListener;

    private String title;
    private String content;
    private String btnLeftText;
    private String btnMiddleText;
    private String btnRightText;
    /**
     * 三個按鈕的顏色
     */
    private int btnLeftColor;
    private int btnMiddleColor;
    private int btnRightColor;
    private int contentGravity = Gravity.CENTER;

    private ImageView imgSplit1;

    private ImageView imgSplit2;

    private boolean canCanceledOnTouchOutside = false;

    private Context context;

    private boolean autoDismiss = true;

    //构造diaglog对象
    public static class Builder 
    {
        private final CustomDialog customDialog;

        public Builder(Context context) {
            customDialog = new CustomDialog(context);
        }

        public Context getContext() {
            return customDialog.context;
        }

        /**
         * @param titleId 标题Id
         * @return Builder
         */
        public Builder setTitle(int titleId) {
            customDialog.title = customDialog.context.getText(titleId).toString();
            return this;
        }

        /**
         * @param title 标题
         * @return Builder
         */
        public Builder setTitle(CharSequence title) {
            customDialog.title = title.toString();
            return this;
        }

        public Builder setAutoDismiss(boolean autoDismiss) {
            customDialog.autoDismiss = autoDismiss;
            return this;
        }

        /**
         * @param messageId 内容Id
         * @return Builder
         */
        public Builder setMessage(int messageId) {
            customDialog.content = customDialog.context.getText(messageId).toString();
            return this;
        }

        /**
         * @param message 内容
         * @return Builder
         */
        public Builder setMessage(CharSequence message) {
            customDialog.content = message.toString();
            return this;
        }

        /**
         * @param textId   显示按钮字符串Id
         * @param listener
         * @return
         */
        public Builder setPositiveButton(int textId, final View.OnClickListener listener) {
            customDialog.btnMiddleText = customDialog.context.getText(textId).toString();
            customDialog.middleButtonDialogListener = listener;
            return this;
        }

        /**
         * 改变按钮的字体颜色
         * @param textId 
         * @param textColor
         * @param listener
         * @return
         */
        public Builder setPositiveButton(int textId,int textColor, final View.OnClickListener listener) {
            customDialog.btnMiddleText = customDialog.context.getText(textId).toString();
            customDialog.middleButtonDialogListener = listener;
            customDialog.btnMiddleColor=textColor;
            return this;
        }

        /**
         * @param text     显示按钮字符串
         * @param listener
         * @return
         */
        public Builder setPositiveButton(CharSequence text, final View.OnClickListener listener) {
            customDialog.btnMiddleText = text.toString();
            customDialog.middleButtonDialogListener = listener;
            return this;
        }


        /**
         * 
         * @param text
         * @param textColor
         * @param listener
         * @return
         */
        public Builder setPositiveButton(CharSequence text, int textColor,final View.OnClickListener listener) {
            customDialog.btnMiddleText = text.toString();
            customDialog.middleButtonDialogListener = listener;
            customDialog.btnMiddleColor=textColor;
            return this;
        }

        /**
         * @param textId   显示按钮字符串Id
         * @param listener
         * @return
         */
        public Builder setNegativeButton(int textId, final View.OnClickListener listener) {
            customDialog.btnLeftText = customDialog.context.getText(textId).toString();
            customDialog.leftButtonDialogListener = listener;
            return this;
        }

        /**
         * 
         * @param textId
         * @param textColor
         * @param listener
         * @return
         */
        public Builder setNegativeButton(int textId,int textColor, final View.OnClickListener listener) {
            customDialog.btnLeftText = customDialog.context.getText(textId).toString();
            customDialog.leftButtonDialogListener = listener;
            customDialog.btnLeftColor=textColor;
            return this;
        }

        /**
         * @param text     显示按钮字符串
         * @param listener
         * @return
         */
        public Builder setNegativeButton(CharSequence text, final View.OnClickListener listener) {
            customDialog.btnLeftText = text.toString();
            customDialog.leftButtonDialogListener = listener;
            return this;
        }

        /**
         * 
         * @param text
         * @param textColor
         * @param listener
         * @return
         */
        public Builder setNegativeButton(CharSequence text,int textColor, final View.OnClickListener listener) {
            customDialog.btnLeftText = text.toString();
            customDialog.leftButtonDialogListener = listener;
            customDialog.btnLeftColor=textColor;
            return this;
        }

        /**
         * @param textId   显示按钮字符串Id
         * @param listener
         * @return
         */
        public Builder setNeutralButton(int textId, final View.OnClickListener listener) {
            customDialog.btnRightText = customDialog.context.getText(textId).toString();
            customDialog.rightButtonDialogListener = listener;
            return this;
        }

        /**
         * 
         * @param textId
         * @param textColor
         * @param listener
         * @return
         */
        public Builder setNeutralButton(int textId, int textColor,final View.OnClickListener listener) {
            customDialog.btnRightText = customDialog.context.getText(textId).toString();
            customDialog.rightButtonDialogListener = listener;
            customDialog.btnRightColor=textColor;
            return this;
        }

        /**
         * @param text     显示按钮字符串
         * @param listener
         * @return
         */
        public Builder setNeutralButton(CharSequence text, final View.OnClickListener listener) {
            customDialog.btnRightText = text.toString();
            customDialog.rightButtonDialogListener = listener;
            return this;
        }

        public Builder setNeutralButton(CharSequence text, int textColor,final View.OnClickListener listener) {
            customDialog.btnRightText = text.toString();
            customDialog.rightButtonDialogListener = listener;
            customDialog.btnRightColor=textColor;
            return this;
        }

       
        /**
         * @param cancelable 是否可以在外部取消
         * @return
         */
        public Builder setCancelable(boolean cancelable) {
            customDialog.canCanceledOnTouchOutside = cancelable;
            customDialog.setCanceledOnTouchOutside(cancelable);
            return this;
        }

        /**
         * @param onCancelListener 监听Dialog Cancel
         * @return
         */
        public Builder setOnCancelListener(OnCancelListener onCancelListener) {
            customDialog.setOnCancelListener(onCancelListener);
            return this;
        }

        /**
         * 监听Dialog Dismiss
         *
         * @param onDismissListener
         * @return
         */
        public Builder setOnDismissListener(OnDismissListener onDismissListener) {
            customDialog.setOnDismissListener(onDismissListener);
            return this;
        }

        /**
         * 显示Dialog
         */
        public CustomDialog show() {
            customDialog.show();
            return customDialog;
        }

        /**
         * 构建Dialog
         *
         * @return
         */
        public CustomDialog createDialog() {
            return customDialog;
        }

        /**
         * @param gravity 对话框内容对齐方式，默认为Center
         * @return
         */
        public Builder setContentGravity(int gravity) {
            if (customDialog != null) {
                customDialog.contentGravity = gravity;
            }
            return this;
        }
        
      
    }


    public CustomDialog(Context context) {
        super(context);
        this.context = context;
        btnLeftColor = context.getResources().getColor(R.color.text_grey);
        btnMiddleColor = context.getResources().getColor(R.color.colorAccent);
        btnRightColor = context.getResources().getColor(R.color.colorAccent);
    }

    @Override
    public void show() {
        super.show();
        btnLeft.setVisibility(View.GONE);
        imgSplit1.setVisibility(View.GONE);
        btnMiddle.setVisibility(View.GONE);
        imgSplit2.setVisibility(View.GONE);
        btnRight.setVisibility(View.GONE);
        if (!TextUtils.isEmpty(btnLeftText)) {
            btnLeft.setVisibility(View.VISIBLE);
        }
        if (!TextUtils.isEmpty(btnMiddleText))
        {
            if (!TextUtils.isEmpty(btnLeftText)) 
            {
                imgSplit1.setVisibility(View.VISIBLE);
                btnMiddle.setVisibility(View.VISIBLE);
            } else
            {
                btnMiddle.setVisibility(View.VISIBLE);
            }
        }

        if (!TextUtils.isEmpty(btnRightText)) 
        {
            if (!TextUtils.isEmpty(btnLeftText) || !TextUtils.isEmpty(btnMiddleText))
            {
                imgSplit2.setVisibility(View.VISIBLE);
                btnRight.setVisibility(View.VISIBLE);
            } else
            {
                btnRight.setVisibility(View.VISIBLE);
            }

        }

    }

    @SuppressWarnings("deprecation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);

        getWindow().setBackgroundDrawable(new BitmapDrawable());

        this.setCanceledOnTouchOutside(canCanceledOnTouchOutside);
        setContentView(R.layout.custom_dialog_layout);

        tvTitle = (TextView) findViewById(R.id.alert_popupwindow_title);
        tvTitle.setText(title);
//        TextPaint tpaint = tvTitle.getPaint();
//        tpaint.setFakeBoldText(true);

        if (TextUtils.isEmpty(title)) {
            tvTitle.setVisibility(View.GONE);
        } else {
            tvTitle.setVisibility(View.VISIBLE);
        }

        tvContent = (TextView) findViewById(R.id.alert_popupwindow_content);
        tvContent.setText(content);
        tvContent.setMovementMethod(new ScrollingMovementMethod());
        //设置内容文字对齐方式
        if (tvContent != null) {
            tvContent.setGravity(contentGravity);
        }

        if (TextUtils.isEmpty(content)) {
            tvContent.setVisibility(View.GONE);
        } else {
            tvContent.setVisibility(View.VISIBLE);
        }

        btnLeft = (Button) findViewById(R.id.alert_popupwindow_btn_left);
        btnLeft.setText(btnLeftText);
        btnLeft.setTextColor(btnLeftColor);
        btnMiddle = (Button) findViewById(R.id.alert_popupwindow_btn_middle);
        btnMiddle.setText(btnMiddleText);
        btnMiddle.setTextColor(btnMiddleColor);
        btnRight = (Button) findViewById(R.id.alert_popupwindow_btn_right);
        btnRight.setText(btnRightText);
        btnRight.setTextColor(btnRightColor);
        imgSplit1 = (ImageView) findViewById(R.id.alert_popupwindow_btn_split);
        imgSplit2 = (ImageView) findViewById(R.id.alert_popupwindow_btn_split_middle);

        btnLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (leftButtonDialogListener != null) {
                    leftButtonDialogListener.onClick(v);
                }
                if (autoDismiss || leftButtonDialogListener == null)
                    CustomDialog.this.cancel();
            }
        });

        btnMiddle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (middleButtonDialogListener != null) {
                    middleButtonDialogListener.onClick(v);
                }
                if (autoDismiss || middleButtonDialogListener == null)
                    CustomDialog.this.cancel();
            }
        });
        btnRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rightButtonDialogListener != null) {
                    rightButtonDialogListener.onClick(v);
                }
                if (autoDismiss || rightButtonDialogListener == null)
                    CustomDialog.this.cancel();
            }
        });

    }
}
