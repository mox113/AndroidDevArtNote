package cn.hudp.androiddevartnote.Builder;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import cn.hudp.androiddevartnote.R;

public class BuilderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_builder);

        People people = new People.Builder("我是名字", 22).setInterest("6666").setSex(1).build();
        Log.e("Builder",people.toString());
    }
}
