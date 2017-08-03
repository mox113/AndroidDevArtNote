package cn.hudp.androiddevartnote.Other.Suspend;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import cn.hudp.androiddevartnote.Common.Utils.StatusBarUtil;
import cn.hudp.androiddevartnote.R;

import static cn.hudp.androiddevartnote.R.id.app_bar;

public class Suspend2Activity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private CommonRecyclerAdapter<String> mAdapter;
    private List<String> mStringList;
    private AppBarLayout appBar;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private Toolbar mToolbar1, mToolbar;

    private CollapsingToolbarLayoutState state;

    private enum CollapsingToolbarLayoutState {EXPANDED, COLLAPSED, INTERNEDIATE}


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suspend3);
        StatusBarUtil.transparencyBar(this);

        initView();

        appBar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                Log.e("Scrool", "偏移量 : " + verticalOffset);
                if (verticalOffset == 0) {
                    if (state != CollapsingToolbarLayoutState.EXPANDED) {
                        state = CollapsingToolbarLayoutState.EXPANDED;//修改状态标记为展开
                        collapsingToolbarLayout.setTitle("");//设置title为EXPANDED
                        StatusBarUtil.StatusBarDarkMode(Suspend2Activity.this, 3);
                        StatusBarUtil.transparencyBar(Suspend2Activity.this);
                    }
                } else if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()) {
                    if (state != CollapsingToolbarLayoutState.COLLAPSED) {
                        StatusBarUtil.StatusBarLightMode(Suspend2Activity.this);
                        collapsingToolbarLayout.setTitle("Title");//设置title不显示
                        collapsingToolbarLayout.setCollapsedTitleTextColor(ContextCompat.getColor(getApplicationContext(), R.color.text_defualt));
//                        playButton.setVisibility(View.VISIBLE);//隐藏播放按钮
                        state = CollapsingToolbarLayoutState.COLLAPSED;//修改状态标记为折叠
                    }
                } else {
                    if (state != CollapsingToolbarLayoutState.INTERNEDIATE) {
                        if (state == CollapsingToolbarLayoutState.COLLAPSED) {
//                            playButton.setVisibility(View.GONE);//由折叠变为中间状态时隐藏播放按钮
                        }
                        collapsingToolbarLayout.setTitle("");//设置title为INTERNEDIATE
                        state = CollapsingToolbarLayoutState.INTERNEDIATE;//修改状态标记为中间
                    }
                }
            }
        });


        initRecyclerView();
    }

    private void initRecyclerView() {
        mStringList = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            mStringList.add("测试：" + i);
        }
        mAdapter = new CommonRecyclerAdapter<String>(this, mStringList, R.layout.item_suspend_layout) {
            @Override
            public void convert(CommonRecyclerHolder holder, String item, int position, boolean isScrolling) {
                holder.setText(R.id.item_text, mStringList.get(position));
            }
        };
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }


    private void initView() {
        appBar = (AppBarLayout) findViewById(app_bar);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar_layout);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar1 = (Toolbar) findViewById(R.id.toolbar1);
        initToolBar(mToolbar1, false, "Title");

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    /**
     * 初始化 Toolbar
     */
    public void initToolBar(Toolbar toolbar, boolean homeAsUpEnabled, String title) {
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(homeAsUpEnabled);
    }
}

