package cn.hudp.androiddevartnote.RetrofitRxJava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

import cn.hudp.androiddevartnote.R;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class RxJavaActivity extends AppCompatActivity {
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java);
        tv = (TextView) findViewById(R.id.tv);
    }

    public void onClick1(View v) {
        //被观察者
        Observable observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                String toast = "我是被观察者 线程：" + Thread.currentThread().getName();
                subscriber.onNext(toast);
                subscriber.onCompleted();
            }
        });
        Observer observer = new Observer<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                s = s + "\n 我是观察者 线程：" + Thread.currentThread().getName();
                Log.e("RxJavaActivity", s);
                tv.setText(s);
            }
        };
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);

        observable.timer(10,TimeUnit.SECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Action1() {
            @Override
            public void call(Object o) {
                Toast.makeText(getApplicationContext(),"定时器",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onClick2(View v) {

    }

    public void onClick3(View v) {

    }

    public void onClick4(View v) {

    }

    public void onClick5(View v) {

    }
}
