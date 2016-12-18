package com.example.somemyidea.rxjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.somemyidea.R;

public class RxjavaActivity extends AppCompatActivity 
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
    }
}
