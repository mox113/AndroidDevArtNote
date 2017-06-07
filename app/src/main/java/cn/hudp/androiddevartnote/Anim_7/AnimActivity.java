package cn.hudp.androiddevartnote.Anim_7;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
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
        initAnim();
    }

    private void initAnim() {
        Animation translateAnim = AnimationUtils.loadAnimation(this,R.anim.translate_anim);
        tvTranslate = (TextView) findViewById(R.id.tv_translate);
        tvTranslate.startAnimation(translateAnim);
    }
}
