package cn.hudp.androiddevartnote.Retrofit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import cn.hudp.androiddevartnote.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
        post();
    }

    public void post() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.douban.com/v2/movie/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        API api = retrofit.create(API.class);
        Call<DoubanMovieEntity> call = api.getTopMovie(1, 10);
        call.enqueue(new Callback<DoubanMovieEntity>() {
            @Override
            public void onResponse(Call<DoubanMovieEntity> call, Response<DoubanMovieEntity> response) {
                Log.e("POST", response.toString());
            }

            @Override
            public void onFailure(Call<DoubanMovieEntity> call, Throwable t) {
                Log.e("POST", t.toString());
            }
        });
    }
}
