package com.example.mvparchitecturestudy.entity;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * @author 作者：Shay-Patrick-Cormac
 * @datetime 创建时间：2017/2/15 22:32
 * @email 邮箱： 574583006@qq.com
 * @content 说明：创建这个类的意义，目的。
 */
public class Joke {
    @SerializedName("msg")
    public String msg;

    @SerializedName("status")
    public String status;

    @SerializedName("result")
    public Result mResult;

    public class Result {
        /**
         * "total": "35328", "pagenum": "1", "pagesize": "6", "list": []
         */
        @SerializedName("total")
        public int total;

        @SerializedName("pagenum")
        public int pageNum;

        @SerializedName("pagesize")
        public int pageSize;

        @SerializedName("list")
        public ArrayList<JokeInfo> mJokeInfoArrayList = new ArrayList<>();

    }
}
