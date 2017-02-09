package cn.hudp.androiddevartnote.Retrofit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import cn.hudp.androiddevartnote.R;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);

    }

    public void post() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofit.create(API.class);

    }
}
