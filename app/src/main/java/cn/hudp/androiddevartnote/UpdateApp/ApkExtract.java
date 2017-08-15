package cn.hudp.androiddevartnote.UpdateApp;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import java.io.File;

/**
 * Created by zhy on 16/10/7.
 */
public class ApkExtract {
    @RequiresApi(api = Build.VERSION_CODES.DONUT)
    public static String extract(Context context) {
        context = context.getApplicationContext();
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        String apkPath = applicationInfo.sourceDir;
        Log.e("hongyang", apkPath);
        return apkPath;
    }

    public static void install(Context context, String apkPath) {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.setDataAndType(Uri.fromFile(new File(apkPath)),
                "application/vnd.android.package-archive");
        context.startActivity(i);
        android.os.Process.killProcess(android.os.Process.myPid());
    }
}
