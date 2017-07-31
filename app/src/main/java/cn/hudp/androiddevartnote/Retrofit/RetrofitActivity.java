package cn.hudp.androiddevartnote.Retrofit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import cn.hudp.androiddevartnote.R;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RetrofitActivity extends AppCompatActivity {
    private static final String baseUrl = "https://api.douban.com/v2/";
    private TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
        tv = (TextView) findViewById(R.id.tv);
//        postRetrofit();
//        postRetrofitToRxJava();
        Subscriber subscriber = new ProgressSubscriber<DoubanMovieEntity>(getApplicationContext()) {
            @Override
            public void onError(Throwable e) {
                super.onError(e);
            }

            @Override
            public void onNext(DoubanMovieEntity doubanMovieEntity) {
                Log.e("RetrofitActivity", "onNext");
                tv.setText(doubanMovieEntity.toString());
            }
        };
        HttpMethods.getInstance().getTopMovie(subscriber,1,10);
    }

    private void postRetrofitToRxJava() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        API api = retrofit.create(API.class);
        api.getTopMovie2(1,10)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DoubanMovieEntity>() {
                    @Override
                    public void onCompleted() {
                        Toast.makeText(getApplicationContext(), "Get Top Movie Completed", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        tv.setText(e.getMessage());
                    }

                    @Override
                    public void onNext(DoubanMovieEntity doubanMovieEntity) {
                        tv.setText(doubanMovieEntity.toString());
                    }
                });
    }

    public void postRetrofit() {
        OkHttpClient client = new OkHttpClient();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        API api = retrofit.create(API.class);
        Call<DoubanMovieEntity> call = api.getTopMovie(1, 10);
        call.enqueue(new Callback<DoubanMovieEntity>() {
            @Override
            public void onResponse(Call<DoubanMovieEntity> call, Response<DoubanMovieEntity> response) {
                if(response!=null) {
                    Log.e("POST Succ", response.body().toString());
                }else{
                    Log.e("POST Error", "返回null");
                }
            }

            @Override
            public void onFailure(Call<DoubanMovieEntity> call, Throwable t) {
                Log.e("POST Error", t.toString());
            }
        });
    }
}
