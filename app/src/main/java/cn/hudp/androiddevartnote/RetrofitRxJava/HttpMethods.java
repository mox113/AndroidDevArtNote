package cn.hudp.androiddevartnote.RetrofitRxJava;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by HuDP on 2017/7/28.
 */

public class HttpMethods {
    private static final String BASE_URL = "https://api.douban.com/v2/";
    private static final int DEFAULT_TIMEOUT = 5;

    private Retrofit retrofit;
    private API api;

    //构造方法私有
    private HttpMethods() {
        //手动创建一个OkHttpClient并设置超时时间
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);

        retrofit = new Retrofit.Builder()
                .client(httpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();

        api = retrofit.create(API.class);
    }


    //在访问HttpMethods时创建单例
    private static class SingletonHolder {
        private static final HttpMethods INSTANCE = new HttpMethods();
    }

    //获取单例
    public static HttpMethods getInstance() {
        return SingletonHolder.INSTANCE;
    }

    /**
     * 用于获取豆瓣电影Top250的数据
     *
     * @param subscriber 由调用者传过来的观察者对象
     * @param start      起始位置
     * @param count      获取长度
     */
    public void getTopMovie(Subscriber<DoubanMovieEntity> subscriber, int start, int count) {
        api.getTopMovie2(start, count)
                .map(new Func1<DoubanMovieEntity, DoubanMovieEntity>() {
                    @Override
                    public DoubanMovieEntity call(DoubanMovieEntity doubanMovieEntity) {
                        return doubanMovieEntity;
                    }
                })
                .subscribeOn(Schedulers.io())//改变订阅的线程 call执行的线程 也就是这里的getTopMovie2执行的线程
                .observeOn(AndroidSchedulers.mainThread())//改变发送的线程 onNext执行的线程
                .subscribe(subscriber);
    }
}
