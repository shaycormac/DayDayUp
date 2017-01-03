package com.example.myapplication.utils;

import android.content.Context;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;

import com.example.myapplication.R;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;

public class SpanUtils {

    private static final String[] emotions = new String[]{"[tongue]", "[smile]", "[lol]",
            "[loveliness]", "[titter]", "[biggrin]", "[shy]", "[sweat]", "[yun]", "[ku]",
            "[88]", "[mad]", "[fendou]", "[funk]", "[cry]", "[sad]", "[ha]", "[huffy]", "[pig]",
            "[guzhang]", "[victory]", "[ok]", "[tu]", "[cake]", "[hug]", "[beer]", "[call]",
            "[time]", "[moon]", "[hei]"};

    
    static Hashtable<String, String> html_specialchars_table = new Hashtable<String, String>();

    static {
        html_specialchars_table.put("&#091;", "[");
        html_specialchars_table.put("&#093;", "]");
    }

    static String htmlspecialchars_decode_ENT_NOQUOTES(String s) {
        Enumeration<String> en = html_specialchars_table.keys();
        while (en.hasMoreElements()) {
            String key = (String) en.nextElement();
            String val = (String) html_specialchars_table.get(key);
            s = s.replaceAll(key, val);
        }
        return s;
    }

    public static String getTimeString(String createdAt) {
        try {
            long time = Long.parseLong(createdAt);
            Date date = new Date(time * 1000);
            String timeString = "";
            timeString = new SimpleDateFormat("yyyy-M-d H:m").format(date);
            return timeString;
        } catch (Exception e) {
            return "";
        }
    }

    public static String FormetFileSize(long fileS) {// 转换文件大小
        DecimalFormat df = new DecimalFormat("#.00");
        String fileSizeString = "";
        if (fileS < 1024) {
            fileSizeString = df.format((double) fileS) + "B";
        } else if (fileS < 1048576) {
            fileSizeString = df.format((double) fileS / 1024) + "K";
        } else if (fileS < 1073741824) {
            fileSizeString = df.format((double) fileS / 1048576) + "M";
        } else {
            fileSizeString = df.format((double) fileS / 1073741824) + "G";
        }
        return fileSizeString;
    }

    public static int IndexOfAny(String test, String[] values, int index) {
        int first = -1;
        for (String item : values) {
            int i = test.indexOf(item, index);
            if (i >= 0) {
                if (first > 0) {
                    if (i < first) {
                        first = i;
                    }
                } else {
                    first = i;
                }
            }
        }
        return first;
    }

    /**
     * 该方法主要用于链接两个字符串（颜色和样式不一样），
     * @param left
     * @param leftColor
     * @param leftTextSize
     * @param right
     * @param rightColor
     * @param rightTextSize
     * @return
     */
    public static SpannableStringBuilder spanString(Context context,String left,int leftColor,int leftTextSize,String right,int rightColor,int rightTextSize)
    {
        if (TextUtils.isEmpty(left)||TextUtils.isEmpty(right))
        return null;
        SpannableStringBuilder spannableString = new SpannableStringBuilder(left + right);
        spannableString.setSpan(new ForegroundColorSpan(context.getResources().getColor(leftColor)), 0, left.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new AbsoluteSizeSpan(leftTextSize), 0, left.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new ForegroundColorSpan(context.getResources().getColor(rightColor)), left.length(), left.length()+right.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new AbsoluteSizeSpan(rightTextSize), left.length(), left.length()+right.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannableString;
    }
    
    public static SpannableStringBuilder spanCredit(Context context,String left,String right)
    {
        return spanString(context, left, R.color.colorAccent, DensityUtil.sp2px(context, 18f), right, R.color.colorPrimaryDark, DensityUtil.sp2px(context, 12f));
           
    }
}
