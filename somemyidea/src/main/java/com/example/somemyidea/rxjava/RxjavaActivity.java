package com.example.somemyidea.rxjava;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.SparseIntArray;

import com.example.somemyidea.R;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

public class RxjavaActivity extends AppCompatActivity 
{
    private final static String TAG = "ShayCormac";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava);
       /* Observable<String> myObservable = Observable.create(
                new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> sub) 
                    {
                        sub.onNext("Hello, world!");
                        sub.onCompleted();
                    }
                }
        );*/
        Flowable<String> flowable = Flowable.create(new FlowableOnSubscribe<String>() 
        {
            @Override
            public void subscribe(FlowableEmitter<String> e) throws Exception 
            {
                e.onNext("hello，Rxjava");
                e.onComplete();
            }
        }, BackpressureStrategy.BUFFER);

        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onSubscribe(Subscription s) {
                System.out.println(s);
                Log.i(TAG, s.toString());
                s.request(Long.MAX_VALUE);
            }

            @Override
            public void onNext(String s) {
                Log.i(TAG, s);
                System.out.println(s);
            }

            @Override
            public void onError(Throwable t) {
                System.out.println(t.getMessage());
            }

            @Override
            public void onComplete() {
                System.out.println("onCompelete");
                Log.i(TAG, "onCompelete");
            }
        };
        flowable.subscribe(subscriber);
        //简写
        Flowable.just("我了个大槽").subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception 
            {
                Log.i(TAG, s);
            }
        });
        //map的使用
        Flowable.just("我要家签名").map(new Function<String, String>() 
        {
            @Override
            public String apply(String s) throws Exception 
            {
                return s+"卧槽。太刁了";
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.i(TAG, s);
            }
        });
        //发射数组
        final List<String> sparseArray = new ArrayList<>();
        sparseArray.add(0, "xiuxi");
        sparseArray.add(1,"hehe");
        sparseArray.add(2,"haha");
        sparseArray.add(3,"haha");
        /*Flowable.fromIterable(sparseArray).subscribe(new Consumer<String>() {
            @Override
            //相当于迭代了
            public void accept(String s) throws Exception
            {
                Log.i(TAG, s);
            }
        });*/

        SparseIntArray sparseIntArray = new SparseIntArray();
        sparseIntArray.append(1, 1);
        sparseIntArray.append(2, 2);
        Flowable.just(sparseArray).flatMap(new Function<List<String>, Publisher<String>>() {
            @Override
            public Publisher<String> apply(List<String> strings) throws Exception {
                return Flowable.fromIterable(strings);
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String o) throws Exception 
            {
                Log.i(TAG, o.toString());
            }
        });
        //过滤
        Flowable.fromArray(0,8,12,5).filter(new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) throws Exception 
            {
                return integer>8;
            }
        }).subscribe(new Consumer<Integer>() 
        {
            @Override
            public void accept(Integer integer) throws Exception 
            {
                Log.i(TAG, integer.toString());
            }
        });
        //Rx随意切换线程
        Flowable.create(new FlowableOnSubscribe<String>() {
            @Override
            public void subscribe(FlowableEmitter<String> e) throws Exception {
                e.onNext("三秒后发射数据");
                SystemClock.sleep(3000);
                e.onNext("我来了");
                e.onComplete();
            }
        }, BackpressureStrategy.BUFFER).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception 
            {
                Log.i(TAG, s);
            }
        });
    }
    
}
