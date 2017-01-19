package cn.hudp.androiddevartnote.ViewEvent_3;

import android.animation.ValueAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import cn.hudp.androiddevartnote.Home.MyUtils;
import cn.hudp.androiddevartnote.R;

public class ViewEventActivity extends AppCompatActivity {
    private static final int MSG_SCROLL_TO = 1;
    private static final int FRAME_COUNT = 30;
    private static final int DELAYED_TIME = 33;
    TestView testView;
    Button btn;
    private int mCount = 0;

    private HorizontalScrollViewEx mListContainer;


    //--------------------------

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_SCROLL_TO:
                    mCount++;
                    if (mCount <= FRAME_COUNT) {
                        //进度比 = 当前进度 / 总进度
                        float fraction = mCount / (float) FRAME_COUNT;
                        //偏移量 = 当前进度 * 总偏移量
                        int scrollX = (int) (fraction * 100);
                        btn.scrollTo(scrollX, 0);
                        mHandler.sendEmptyMessageDelayed(MSG_SCROLL_TO, DELAYED_TIME);
                    }
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        Toast.makeText(getApplicationContext(), "Activity onTouchEvent", Toast.LENGTH_SHORT).show();
        return super.onTouchEvent(event);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_event);
        testView = (TestView) findViewById(R.id.testView);
        btn = (Button) findViewById(R.id.btn);

        scrollTo();
//        updateLayoutParams();
//        anim();
//        mHandler.sendEmptyMessage(MSG_SCROLL_TO);

//        initViews();


    }

    private void initViews() {
        LayoutInflater inflater = getLayoutInflater();
        mListContainer = (HorizontalScrollViewEx) findViewById(R.id.container);
        int screenWidth = MyUtils.getScreenMetrics(getApplicationContext()).widthPixels;
        int screenHeight = MyUtils.getScreenMetrics(getApplicationContext()).heightPixels;

        for (int i = 0; i < 3; i++) {
            ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.content_layout, mListContainer, false);
            layout.getLayoutParams().width = screenWidth;
            TextView textView = (TextView) layout.findViewById(R.id.title);
            textView.setText("page " + (i + 1));
            layout.setBackgroundColor(Color.rgb(255 / (i + 1), 255 / (i + 1), 0));
            createList(layout);
            mListContainer.addView(layout);
        }
    }

    private void createList(ViewGroup layout) {
        ListView listView = (ListView) layout.findViewById(R.id.listView);
        ArrayList<String> datas = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            datas.add("name " + i);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.content_list_item, R.id.tv_name, datas);
        listView.setAdapter(adapter);
    }

    private void anim() {
//        ObjectAnimator.ofFloat(testView, "translationX", 0, 100).setDuration(500).start();
        final int startX = 0;
        final int deltaX = 100;
        final ValueAnimator animator = ValueAnimator.ofInt(0, 1).setDuration(1000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float fraction = animator.getAnimatedFraction();
                btn.scrollTo(startX + (int) (deltaX * fraction), 0);
            }
        });
        animator.start();
    }

    private void updateLayoutParams() {
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) testView.getLayoutParams();
        params.width += 200;
        testView.requestLayout();
    }

    private void scrollTo() {
        btn.post(new Runnable() {
            @Override
            public void run() {
                btn.scrollTo(500, 300);
            }
        });
    }
}
