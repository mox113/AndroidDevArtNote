package cn.hudp.androiddevartnote.RemoveViews_5;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RemoteViews;

import cn.hudp.androiddevartnote.Home.MainActivity;
import cn.hudp.androiddevartnote.R;

public class RemoteViewsActivity extends AppCompatActivity {
    public Button btn;
    public LinearLayout llRemoteViews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remote_views);
        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNotification();
            }
        });
        llRemoteViews = (LinearLayout) findViewById(R.id.ll_remoteviews);
        initRemoveViews();
    }

    /**
     * 加载其他App的资源文件
     */
    private void initRemoveViews() {
        final String pkg = "cn.hudp.remoteviews";//需要加载app的包名
        Resources resources = null;
        try {
            resources = getPackageManager().getResourcesForApplication(pkg);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        if (resources != null) {
            int layoutId = resources.getIdentifier("activity_main", "layout", pkg);
            RemoteViews remoteViews = new RemoteViews(pkg, layoutId);
            View view = remoteViews.apply(this, llRemoteViews);//llRemoteViews是View所在的父容器
            llRemoteViews.addView(view);
        }
    }

    public void showNotification() {
        Notification notification = new Notification();
        notification.icon = R.mipmap.ic_launcher;
        notification.tickerText = "hello notification";
        notification.when = System.currentTimeMillis();
        notification.flags = Notification.FLAG_AUTO_CANCEL;
        Intent intent = new Intent(this, RemoteViewsActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.layout_notification);//RemoveViews所加载的布局文件
        remoteViews.setTextViewText(R.id.tv, "这是一个Test");//设置文本内容
        remoteViews.setTextColor(R.id.tv, Color.parseColor("#abcdef"));//设置文本颜色
        remoteViews.setImageViewResource(R.id.iv, R.mipmap.ic_launcher);//设置图片
        PendingIntent openActivity2Pending = PendingIntent.getActivity
                (this, 0, new Intent(this, MainActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);//设置RemoveViews点击后启动界面
        remoteViews.setOnClickPendingIntent(R.id.tv, openActivity2Pending);
        notification.contentView = remoteViews;
        notification.contentIntent = pendingIntent;
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(2, notification);
    }
}
