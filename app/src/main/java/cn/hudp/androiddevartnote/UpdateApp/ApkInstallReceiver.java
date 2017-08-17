package cn.hudp.androiddevartnote.UpdateApp;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * ApkInstallReceiver
 * Created by Naple on 2017/8/4.
 * 介绍：
 */

public class ApkInstallReceiver extends BroadcastReceiver {
    public static final String APK_NAME = "S288-D.apk";
    private Handler handler;

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(DownloadManager.ACTION_DOWNLOAD_COMPLETE)) {
            long downloadApkId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
            installApk(context, downloadApkId);
            if (handler == null) {
                handler = new Handler();
            }
        }
    }

    /**
     * 安装apk
     */
    private void installApk(Context context, long downloadApkId) {
        // 获取存储ID
//        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
//        long downId = sp.getLong(DownloadManager.EXTRA_DOWNLOAD_ID, -1L);
        String filePath = queryDownloadedApk(context, downloadApkId);
        if (filePath.endsWith(".apk")) {
            if (Build.VERSION.SDK_INT > 23) {
                File apkFile = new File(filePath);
                Intent install = new Intent(Intent.ACTION_VIEW);
                //参数1 上下文, 参数2 Provider主机地址 和配置文件中保持一致   参数3  共享的文件
                Uri apkUri = FileProvider.getUriForFile(context, context.getPackageName() + ".FileProvider", apkFile);
                // 由于没有在Activity环境下启动Activity,设置下面的标签
                install.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                install.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                install.setDataAndType(apkUri, "application/vnd.android.package-archive");
                context.startActivity(install);
            } else {
                ApkExtract.install(context, filePath);
            }
        } else if (filePath.endsWith(".patch")) {
            File apkFile = new File(context.getFilesDir().getAbsolutePath());//当前app 安装包
            final File destApk = new File(DownloadService.FILE_DOC_PATH, APK_NAME);//
            copyFile(apkFile.getAbsolutePath(), destApk.getAbsolutePath());//复制出来

            final File patch = new File(filePath);
//            final File patch = new File(DownloadService.FILE_DOC_PATH, "PATCH.patch");
            Log.e("增量更新", "patch = " + patch.exists() + " , " + patch.getAbsolutePath());

            BsPatch.bspatch(ApkExtract.extract(context),
                    destApk.getAbsolutePath(),
                    patch.getAbsolutePath());
            Log.e("增量更新", new File(Environment.getExternalStorageDirectory(), "old").getAbsolutePath() + " , " + destApk.exists());

            if (destApk.exists()) {
                ApkExtract.install(context, destApk.getAbsolutePath());

//                ToastManager.showShort("增量更新包下载安装完成，即将自动重启");
                if (handler != null) {
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            android.os.Process.killProcess(android.os.Process.myPid());
                        }
                    }, 1000);
                } else {
                    android.os.Process.killProcess(android.os.Process.myPid());
                }
            }
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

    /**
     * 复制单个文件
     *
     * @param oldPath String 原文件路径 如：c:/fqf.txt
     * @param newPath String 复制后路径 如：f:/fqf.txt
     * @return boolean
     */
    public static void copyFile(String oldPath, String newPath) {
        try {
            int bytesum = 0;
            int byteread = 0;
            File oldfile = new File(oldPath);
            File newFile = new File(newPath);
            if (newFile.exists()) {
                newFile.delete();
            }
            if (oldfile.exists()) { //文件存在时
                InputStream inStream = new FileInputStream(oldPath); //读入原文件
                FileOutputStream fs = new FileOutputStream(newPath);
                byte[] buffer = new byte[1444];
                int length;
                while ((byteread = inStream.read(buffer)) != -1) {
                    bytesum += byteread; //字节数 文件大小
                    System.out.println(bytesum);
                    fs.write(buffer, 0, byteread);
                }
                inStream.close();
            }
        } catch (Exception e) {
            System.out.println("复制单个文件操作出错");
            e.printStackTrace();
        }
    }
}
