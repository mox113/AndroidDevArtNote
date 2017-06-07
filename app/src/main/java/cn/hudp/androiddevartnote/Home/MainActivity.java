package cn.hudp.androiddevartnote.Home;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import cn.hudp.androiddevartnote.Activity_1.SingleInstanceActivity;
import cn.hudp.androiddevartnote.Anim_7.AnimActivity;
import cn.hudp.androiddevartnote.Builder.BuilderActivity;
import cn.hudp.androiddevartnote.Drawable_6.DrawableActivity;
import cn.hudp.androiddevartnote.IPC_2.IPC2Activity;
import cn.hudp.androiddevartnote.Other.BinaryTree.BinaryTreeActivity;
import cn.hudp.androiddevartnote.Other.BubbleSort.BubbleSortActivity;
import cn.hudp.androiddevartnote.Other.LinkList.LinkActivity;
import cn.hudp.androiddevartnote.Other.Permission.PermissionActivity;
import cn.hudp.androiddevartnote.R;
import cn.hudp.androiddevartnote.RemoveViews_5.RemoteViewsActivity;
import cn.hudp.androiddevartnote.Retrofit.RetrofitActivity;
import cn.hudp.androiddevartnote.Synthesis_13.SynthesisActivity;
import cn.hudp.androiddevartnote.ViewEvent_3.ViewEventActivity;
import cn.hudp.androiddevartnote.Window_8.WindowActivity;

//import cn.hudp.androiddevartnote.Other.Permission.PermissionActivity;

public class MainActivity extends AppCompatActivity {
    protected ListView lvMain;
    protected ListAdapter adapter;
    protected List<ListInfo> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvMain = (ListView) findViewById(R.id.lv_main);
        initData();
    }

    private void initData() {
        list = new ArrayList<>();

        list.add(new ListInfo("第1章 Activity的生命周期和启动模式", SingleInstanceActivity.class));
        list.add(new ListInfo("第2章 IPC机制", IPC2Activity.class));
        list.add(new ListInfo("第3章 View事件体系", ViewEventActivity.class));
        list.add(new ListInfo("第4章 View的工作原理", null));
        list.add(new ListInfo("第5章 理解RemoveViews", RemoteViewsActivity.class));
        list.add(new ListInfo("第6章 Android的Drawable", DrawableActivity.class));
        list.add(new ListInfo("第7章 动画深入分析", AnimActivity.class));
        list.add(new ListInfo("第8章 理解Window和WindowManager（未学完）", WindowActivity.class));
        list.add(new ListInfo("第9章 四大组件的工作过程（未学完）", null));
        list.add(new ListInfo("第10章 Android的消息机制", null));
        list.add(new ListInfo("第11章 线程与线程池", null));
        list.add(new ListInfo("第12章 Bitmap的加载和Cache", null));
        list.add(new ListInfo("第13章 综合技术", SynthesisActivity.class));
        list.add(new ListInfo("其他 链表", LinkActivity.class));
        list.add(new ListInfo("其他 建造者模式", BuilderActivity.class));
        list.add(new ListInfo("其他 二叉树", BinaryTreeActivity.class));
        list.add(new ListInfo("其他 权限管理", PermissionActivity.class));
        list.add(new ListInfo("其他 冒泡排序", BubbleSortActivity.class));
        list.add(new ListInfo("其他 Retrofit", RetrofitActivity.class));

        adapter = new ListAdapter(MainActivity.this, list);
        lvMain.setAdapter(adapter);
    }
}
