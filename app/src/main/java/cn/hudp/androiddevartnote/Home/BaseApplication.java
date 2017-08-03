package cn.hudp.androiddevartnote.Home;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.facebook.stetho.Stetho;
import com.tencent.bugly.Bugly;
import com.tencent.tinker.loader.app.TinkerApplication;
import com.tencent.tinker.loader.shareutil.ShareConstants;

import cn.hudp.androiddevartnote.HotFix.SampleApplicationLike;
import cn.hudp.androiddevartnote.R;

/**
 * Created by HuDP on 16/6/2.
 */
public class BaseApplication extends TinkerApplication {
    public BaseApplication() {
        super(ShareConstants.TINKER_ENABLE_ALL, SampleApplicationLike.class.getName(),
                "com.tencent.tinker.loader.TinkerLoader", false);
    }

    @Override
    public void onCreate() {
        super.onCreate();
//        CrashHandler crashHandler = CrashHandler.getInstance();
//        crashHandler.init(this);
        Bugly.init(this, "55ff8a76e1", true);
        Stetho.initializeWithDefaults(this);

        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(final Activity activity, Bundle savedInstanceState) {
                //这里全局给Activity设置toolbar和title,你想象力有多丰富,这里就有多强大,以前放到BaseActivity的操作都可以放到这里
                View toolbar = activity.findViewById(R.id.action_bar);
                if (toolbar != null) { //找到 Toolbar 并且替换 Actionbar
                    toolbar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            activity.finish();
                        }
                    });
                }
            }

            @Override
            public void onActivityStarted(Activity activity) {
            }

            @Override
            public void onActivityResumed(Activity activity) {
            }

            @Override
            public void onActivityPaused(Activity activity) {
            }

            @Override
            public void onActivityStopped(Activity activity) {
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
            }

            @Override
            public void onActivityDestroyed(Activity activity) {
            }
        });
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
//        MultiDex.install(this);
//        // 安装tinker
//        Beta.installTinker();
    }
}
