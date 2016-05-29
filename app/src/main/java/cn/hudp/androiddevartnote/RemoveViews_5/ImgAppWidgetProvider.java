package cn.hudp.androiddevartnote.RemoveViews_5;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import cn.hudp.androiddevartnote.R;

/**
 * Created by HuDP on 16/5/28.
 */
public class ImgAppWidgetProvider extends AppWidgetProvider {
    public static final String TAG = "ImgAppWidgetProvider";
    public static final String CLICK_ACTION = "cn.hudp.androiddevartnote.action.click";
    private static int index;

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        if (intent.getAction().equals(CLICK_ACTION)) {
            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget);
            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);

            updateView(context, remoteViews, appWidgetManager);
        }
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget);

        updateView(context, remoteViews, appWidgetManager);
    }

    // 由于onReceive 和 onUpdate中部分代码相同 则抽成一个公用方法
    public void updateView(Context context, RemoteViews remoteViews, AppWidgetManager appWidgetManager) {
        index = (int) (Math.random() * 3);
        if (index == 1) {
            remoteViews.setImageViewResource(R.id.iv, R.mipmap.haimei1);
        } else if (index == 2) {
            remoteViews.setImageViewResource(R.id.iv, R.mipmap.haimei2);
        } else {
            remoteViews.setImageViewResource(R.id.iv, R.mipmap.haimei3);
        }
        Intent clickIntent = new Intent();
        clickIntent.setAction(CLICK_ACTION);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, clickIntent, 0);
        remoteViews.setOnClickPendingIntent(R.id.iv, pendingIntent);
        appWidgetManager.updateAppWidget(new ComponentName(context, ImgAppWidgetProvider.class), remoteViews);
    }
}
