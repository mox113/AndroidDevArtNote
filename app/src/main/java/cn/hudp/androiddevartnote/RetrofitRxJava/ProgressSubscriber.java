package cn.hudp.androiddevartnote.RetrofitRxJava;

import android.content.Context;
import android.widget.Toast;

import rx.Subscriber;

/**
 * Created by HuDP on 2017/7/31.
 */

public abstract class ProgressSubscriber<T> extends Subscriber<T>{
    private Context context;

    public ProgressSubscriber(Context context) {
        this.context = context;
    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onStart() {
        super.onStart();
        Toast.makeText(context,"加载中...",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCompleted() {
        Toast.makeText(context,"加载完成",Toast.LENGTH_SHORT).show();
    }
}
