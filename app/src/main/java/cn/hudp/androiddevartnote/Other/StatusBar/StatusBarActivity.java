package cn.hudp.androiddevartnote.Other.StatusBar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import cn.hudp.androiddevartnote.Common.Utils.StatusBarUtil;
import cn.hudp.androiddevartnote.R;

public class StatusBarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status_bar);

    }

    public void changeColor(View view) {
        Toast.makeText(this, "修改状态栏颜色", Toast.LENGTH_SHORT).show();
        StatusBarUtil.StatusBarLightMode(this);
    }

}
