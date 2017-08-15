package cn.hudp.androiddevartnote.Window_8;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * Created by HuDP on 2017/6/24.
 */

public class FloatWindowService extends Service {
    public static final String PS_PACKAGE = "com.zhy.bsdiff_and_patch";//com.tencent.isux.psplay
    public static final String NOTE_PACKAGE = "cn.hudp.androiddevartnote";
    public static final String PACKAGE = PS_PACKAGE;
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // 开启定时器，每隔0.5秒刷新一次
        MyWindowManager.createSmallWindow(getApplicationContext());
        return super.onStartCommand(intent, flags, startId);
    }
}