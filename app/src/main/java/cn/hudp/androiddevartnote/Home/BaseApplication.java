package cn.hudp.androiddevartnote.Home;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import cn.hudp.androiddevartnote.Synthesis_13.CrashHandler;

/**
 * Created by HuDP on 16/6/2.
 */
public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(this);

    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
