package cn.hudp.androiddevartnote.UpdateApp;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.util.Log;

import java.io.File;

/**
 * ApkInstallReceiver
 * Created by Naple on 2017/8/4.
 * 介绍：
 */

public class ApkInstallReceiver extends BroadcastReceiver {
    public static final String APK_NAME = "S288-D.apk";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(DownloadManager.ACTION_DOWNLOAD_COMPLETE)) {
            long downloadApkId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
            installApk(context, downloadApkId);
        }
    }

    /**
     * 安装apk
     */
    private void installApk(Context context, long downloadApkId) {
        // 获取存储ID
//        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
//        long downId = sp.getLong(DownloadManager.EXTRA_DOWNLOAD_ID, -1L);
        String apkPath = queryDownloadedApk(context, downloadApkId);
        if (apkPath.endsWith(".apk")) {
            if (Build.VERSION.SDK_INT > 23) {
                File apkFile = new File(apkPath);
                Intent install = new Intent(Intent.ACTION_VIEW);
                //参数1 上下文, 参数2 Provider主机地址 和配置文件中保持一致   参数3  共享的文件
                Uri apkUri = FileProvider.getUriForFile(context, context.getPackageName() + ".FileProvider", apkFile);
                // 由于没有在Activity环境下启动Activity,设置下面的标签
                install.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                install.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                install.setDataAndType(apkUri, "application/vnd.android.package-archive");
                context.startActivity(install);
            } else {
                ApkExtract.install(context, apkPath);
            }
        } else if (apkPath.endsWith(".patch")) {
            final File destApk = new File(Environment.getExternalStorageDirectory(), APK_NAME);
            final File patch = new File(Environment.getExternalStorageDirectory(), "PATCH.patch");
            Log.e("增量更新", "patch = " + patch.exists() + " , " + patch.getAbsolutePath());

            BsPatch.bspatch(ApkExtract.extract(context),
                    destApk.getAbsolutePath(),
                    patch.getAbsolutePath());
            Log.e("增量更新", new File(Environment.getExternalStorageDirectory(), "old").getAbsolutePath() + " , " + destApk.exists());

            if (destApk.exists())
                ApkExtract.install(context, destApk.getAbsolutePath());
        }
    }

    public static String queryDownloadedApk(Context context, long downloadApkId) {
        String targetApkPath = null;
        DownloadManager downManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        if (downloadApkId != -1) {
            DownloadManager.Query query = new DownloadManager.Query();
            query.setFilterById(downloadApkId);
            query.setFilterByStatus(DownloadManager.STATUS_SUCCESSFUL);
            Cursor cur = downManager.query(query);
            if (null != cur) {
                if (cur.moveToFirst()) {
                    String uriStr = cur.getString(cur.getColumnIndex(DownloadManager.COLUMN_LOCAL_URI));
                    if (!TextUtils.isEmpty(uriStr)) {
                        targetApkPath = Uri.parse(uriStr).getPath();
                    }
                }
                cur.close();
            }
        }
        return targetApkPath;
    }
}
