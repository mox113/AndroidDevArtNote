package cn.hudp.androiddevartnote.Activity_1;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import cn.hudp.androiddevartnote.R;

public class IntentFilterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_filter);
    }

    public void Click(View view) {
        Intent intent = new Intent("cn.hudp.a");
//        intent.addCategory("cn.hudp.category");
//        intent.addCategory("android.intent.category.DEFAULT");
        startActivity(intent);
    }

    public void Click2(View view) {
        Intent intent = new Intent("cn.hudp.b");
        intent.setDataAndType(Uri.parse("cn://hudp"),"text/plain");
        startActivity(intent);
    }
}
