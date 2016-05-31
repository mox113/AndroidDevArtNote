package cn.hudp.androiddevartnote.Drawable_6;

import android.graphics.drawable.ScaleDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import cn.hudp.androiddevartnote.R;

public class DrawableActivity extends AppCompatActivity {
    public ImageView ivLevei;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawable);

        ivLevei = (ImageView) findViewById(R.id.iv_levei);
//        Drawable drawable = getDrawable(R.drawable.levei_list_drawable);
//        drawable.setLevel(1);
//        ivLevei.setBackground(drawable);

        //TransitionDrawable
//        TransitionDrawable drawable = (TransitionDrawable) ivLevei.getBackground();
//        drawable.startTransition(1000);

        ScaleDrawable scaleDrawable = (ScaleDrawable) ivLevei.getBackground();
        scaleDrawable.setLevel(1);

//        ClipDrawable clipDrawable = (ClipDrawable) ivLevei.getBackground();
//        clipDrawable.setLevel(7000);


    }
}
