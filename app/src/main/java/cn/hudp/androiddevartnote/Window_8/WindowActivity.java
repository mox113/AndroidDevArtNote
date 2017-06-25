package cn.hudp.androiddevartnote.Window_8;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import cn.hudp.androiddevartnote.R;

public class WindowActivity extends AppCompatActivity {
    Button btn, btn_start_window;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_window);
        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dimBackground(1.0f, 0.3f);
            }
        });

        btn_start_window = (Button) findViewById(R.id.btn_start_window);
        btn_start_window.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WindowActivity.this,FloatWindowService.class);
                startService(intent);
            }
        });
//        MyWindowManager.createSmallWindow(getApplicationContext());
    }

    //Activity 变暗
    private void dimBackground(float from, float to) {
        final Window window = getWindow();
        ValueAnimator animator = ValueAnimator.ofFloat(from, to);
        animator.setDuration(500);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                WindowManager.LayoutParams params = window.getAttributes();
                params.alpha = (float) animation.getAnimatedValue();
                window.setAttributes(params);
            }
        });
        animator.start();
    }
}
