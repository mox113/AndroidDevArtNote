package cn.hudp.androiddevartnote.Anim_7;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.hudp.androiddevartnote.R;

public class AnimActivity extends AppCompatActivity {
    @BindView(R.id.tv_translate)
    TextView tvTranslate;
    @BindView(R.id.tv_scale)
    TextView tvScale;
    @BindView(R.id.tv_rotate)
    TextView tvRotate;
    @BindView(R.id.tv_alpha)
    TextView tvAlpha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anim);
        ButterKnife.bind(this);
        findViewById(R.id.btn_svg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AnimActivity.this, SvgAnimActivity.class));
            }
        });
        initAnim();
    }

    private void initAnim() {
        Animation translateAnim = AnimationUtils.loadAnimation(this, R.anim.translate_anim);
        tvTranslate = (TextView) findViewById(R.id.tv_translate);
        tvTranslate.startAnimation(translateAnim);
    }

    @OnClick({R.id.tv_translate, R.id.tv_scale, R.id.tv_rotate, R.id.tv_alpha, R.id.btn_svg})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_translate:
                break;
            case R.id.tv_scale:
                break;
            case R.id.tv_rotate:
                break;
            case R.id.tv_alpha:
                break;
            case R.id.btn_svg:
                startActivity(new Intent(AnimActivity.this, SvgAnimActivity.class));
                break;
        }
    }
}
