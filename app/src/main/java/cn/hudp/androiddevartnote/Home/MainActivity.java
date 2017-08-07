package cn.hudp.androiddevartnote.Home;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;

import com.zhy.m.permission.MPermissions;
import com.zhy.m.permission.PermissionDenied;
import com.zhy.m.permission.PermissionGrant;

import java.util.ArrayList;
import java.util.List;

import cn.hudp.androiddevartnote.Activity_1.SingleInstanceActivity;
import cn.hudp.androiddevartnote.Anim_7.AnimActivity;
import cn.hudp.androiddevartnote.Builder.BuilderActivity;
import cn.hudp.androiddevartnote.CustomView.CustomViewActivity;
import cn.hudp.androiddevartnote.Drawable_6.DrawableActivity;
import cn.hudp.androiddevartnote.IPC_2.IPC2Activity;
import cn.hudp.androiddevartnote.Other.BinaryTree.BinaryTreeActivity;
import cn.hudp.androiddevartnote.Other.BubbleSort.BubbleSortActivity;
import cn.hudp.androiddevartnote.Other.Java2JS.Java2JsActivity;
import cn.hudp.androiddevartnote.Other.LinkList.LinkActivity;
import cn.hudp.androiddevartnote.Other.Permission.PermissionActivity;
import cn.hudp.androiddevartnote.Other.Reflect.ReflectActivity;
import cn.hudp.androiddevartnote.Other.StatusBar.StatusBarActivity;
import cn.hudp.androiddevartnote.Other.Suspend.SuspendActivity;
import cn.hudp.androiddevartnote.R;
import cn.hudp.androiddevartnote.RemoveViews_5.RemoteViewsActivity;
import cn.hudp.androiddevartnote.RetrofitRxJava.RetrofitActivity;
import cn.hudp.androiddevartnote.RetrofitRxJava.RxJavaActivity;
import cn.hudp.androiddevartnote.Synthesis_13.SynthesisActivity;
import cn.hudp.androiddevartnote.ViewEvent_3.ViewEventActivity;
import cn.hudp.androiddevartnote.Window_8.FloatWindowService;
import cn.hudp.androiddevartnote.Window_8.WindowActivity;

public class MainActivity extends AppCompatActivity {
    private static final int REQUECT_CODE_SDCARD = 0x001;
    protected ListView lvMain;
    protected ListAdapter adapter;
    protected List<ListInfo> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvMain = (ListView) findViewById(R.id.lv_main);

        MPermissions.requestPermissions(MainActivity.this, REQUECT_CODE_SDCARD, Manifest.permission.SYSTEM_ALERT_WINDOW);
        initData();
    }


    private void initData() {
        list = new ArrayList<>();

        list.add(new ListInfo("其他 自定义View", CustomViewActivity.class));
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
        list.add(new ListInfo("其他 RxJava", RxJavaActivity.class));
        list.add(new ListInfo("其他 Retrofit", RetrofitActivity.class));
        list.add(new ListInfo("其他 悬浮Tab效果", SuspendActivity.class));
//        list.add(new ListInfo("其他 悬浮Tab效果2", Suspend2Activity.class));
        list.add(new ListInfo("其他 反射", ReflectActivity.class));
        list.add(new ListInfo("其他 状态栏", StatusBarActivity.class));
        list.add(new ListInfo("其他 Java与Html(Js)交互", Java2JsActivity.class));

        adapter = new ListAdapter(MainActivity.this, list);
        lvMain.setAdapter(adapter);
    }

    private void startWindowService() {
        Intent intent = new Intent(MainActivity.this, FloatWindowService.class);
        startService(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        MPermissions.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @PermissionGrant(REQUECT_CODE_SDCARD)
    public void requestSdcardSuccess() {
        startWindowService();
    }

    @PermissionDenied(REQUECT_CODE_SDCARD)
    public void requestSdcardFailed() {
        showDialog();
    }

    private void showDialog() {
//        AlertDialog dialog = new AlertDialog.Builder(this).setTitle("还可以手动开启哦~").setMessage("可以前往设置->app->myapp->permission打开").setPositiveButton("确定!", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//            }
//        }).show();
        Toast.makeText(getApplicationContext(), "权限被禁止，如需要可前往权限管理中打开", Toast.LENGTH_SHORT).show();
    }

}
