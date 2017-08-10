package cn.hudp.androiddevartnote.Activity_1;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import cn.hudp.androiddevartnote.R;

public class Activity extends AppCompatActivity {
    private TextView tv;
    private StringBuffer buffer;

    public void setTv(String tag) {
        Log.e("Activity", tag);
        buffer.append(tag);
        buffer.append("\n");
        tv.setText(buffer.toString());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);
        tv = (TextView) findViewById(R.id.tv);
        buffer = new StringBuffer();

        Uri uri = getIntent().getData();
        if (uri != null) {
            String data = uri.getHost();
            Toast.makeText(getApplicationContext(), data, Toast.LENGTH_SHORT).show();
        }
        setTv("onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        setTv("onStart(可见 但 还在后台)");
    }

    @Override
    protected void onResume() {
        super.onResume();
        setTv("onResume(可见 在前台)");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        setTv("onRestart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        setTv("onPause(执行完 新Activity才会执行onResume");
    }

    @Override
    protected void onStop() {
        super.onStop();
        setTv("onStop(不可见)");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        setTv("onDestroy");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setTv("onNewIntent");
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        setTv("onSaveInstanceState(异常情况下保存状态 在onStop 之前)");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        setTv("onRestoreInstanceState(异常情况下恢复状态 在onStart 之后)");
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        setTv("onConfigurationChanged");
    }

    public void SingleInstanceOnClick(View v) {
        Intent intent = new Intent(Activity.this, SingleInstanceActivity.class);
        startActivity(intent);
    }

    public void IntentFilterOnclick(View v) {
        Intent intent = new Intent(Activity.this, IntentFilterActivity.class);
        startActivity(intent);
    }

}
