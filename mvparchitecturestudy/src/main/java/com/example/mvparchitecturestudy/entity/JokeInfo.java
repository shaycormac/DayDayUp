package com.example.mvparchitecturestudy.entity;

import com.google.gson.annotations.SerializedName;

/**
 * @author 作者：Shay-Patrick-Cormac
 * @datetime 创建时间：2017/2/15 22:25
 * @email 邮箱： 574583006@qq.com
 * @content 请求实体。
 */
public class JokeInfo
{
    @SerializedName("content")
    public String mContent;
    @SerializedName("addTime")
    public String mAddTime;

    @Override
    public String toString() {
        return "JokeInfo{" +
                "mContent='" + mContent + '\'' +
                ", mAddTime='" + mAddTime + '\'' +
                '}';
    }
}

/**
 *
 * { "status": "0", "msg": "ok", "result": { "total": "35328", "pagenum": "1",
 * "pagesize": "1", "list": [ { "content":
 * "刚才肚子饿下去吃夜宵，遇见一刚下班的程序猿来吃晚饭，一脸落魄。我坐他旁边跟他说：年轻人，生活总是苦尽甘来有笑有泪的，你看我，我五点半就下班了。",
 * "addtime": "2016-05-19 16:59:40", "url":
 * "http://m.kaixinhui.com/detail-38125.html" } ] } }
 *
 */
