package cn.hudp.androiddevartnote.Common;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import cn.hudp.androiddevartnote.IPC_2.IPC2Activity;
import cn.hudp.androiddevartnote.Other.BinaryTree.BinaryTreeActivity;
import cn.hudp.androiddevartnote.Other.LinkList.LinkActivity;
//import cn.hudp.androiddevartnote.Other.Permission.PermissionActivity;
import cn.hudp.androiddevartnote.Other.Permission.PermissionActivity;
import cn.hudp.androiddevartnote.R;
import cn.hudp.androiddevartnote.RemoveViews_5.RemoteViewsActivity;

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

        list.add(new ListInfo("第一章 Activity的生命周期和启动模式", null));
        list.add(new ListInfo("第二章 IPC机制", IPC2Activity.class));
        list.add(new ListInfo("第五章 理解RemoveViews", RemoteViewsActivity.class));
        list.add(new ListInfo("其他 链表", LinkActivity.class));
        list.add(new ListInfo("其他 二叉树", BinaryTreeActivity.class));
        list.add(new ListInfo("其他 权限管理", PermissionActivity.class));

        adapter = new ListAdapter(MainActivity.this, list);
        lvMain.setAdapter(adapter);
    }
}
