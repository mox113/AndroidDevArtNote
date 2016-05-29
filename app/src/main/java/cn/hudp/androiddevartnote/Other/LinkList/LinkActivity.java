package cn.hudp.androiddevartnote.Other.LinkList;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cn.hudp.androiddevartnote.R;

public class LinkActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_link);
        LinkList.Test();
    }
}
