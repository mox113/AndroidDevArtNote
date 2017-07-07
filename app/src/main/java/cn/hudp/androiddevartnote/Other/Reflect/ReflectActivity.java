package cn.hudp.androiddevartnote.Other.Reflect;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.lang.reflect.Field;

import cn.hudp.androiddevartnote.R;

public class ReflectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reflect);

        ReflectTestInfo info = new ReflectTestInfo("666");
        Log.e("ReflectActivity", info.toString());
        Class cla = (Class) info.getClass();
        try {
            Field field = cla.getDeclaredField("name");
            field.setAccessible(true);
            field.set(info, "胡德培");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.e("ReflectActivity", info.toString());
    }
}
