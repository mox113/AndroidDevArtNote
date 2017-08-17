package cn.hudp.androiddevartnote.UpdateApp;

import android.app.DownloadManager;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.io.File;

import cn.hudp.androiddevartnote.R;

public class UploadAppActivity extends AppCompatActivity {
    private ApkInstallReceiver mInstallReceiver = new ApkInstallReceiver();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_app);
        registerReceiver(mInstallReceiver,
                new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
    }

    public void InstallApp(View view) {
        DownloadService.start(getApplicationContext(), "应用宝.apk", "发现应用的乐趣",
                "http://121.15.220.140/download.sj.qq.com/upload/connAssitantDownload/upload/MobileAssistant_1.apk?mkey=59917e34f200372f&f=e518&c=0&p=.apk");
    }

    public void PatchApp(View view) {
        final File destApk = new File(Environment.getExternalStorageDirectory(), "old.apk");
        final File patch = new File(Environment.getExternalStorageDirectory(), "PATCH.patch");

        Log.e("hongyang", "patch = " + patch.exists() + " , " + patch.getAbsolutePath());

        BsPatch.bspatch(ApkExtract.extract(this),
                destApk.getAbsolutePath(),
                patch.getAbsolutePath());

        Log.e("hongyang", new File(Environment.getExternalStorageDirectory(), "old").getAbsolutePath() + " , " + destApk.exists());

        if (destApk.exists())
            ApkExtract.install(this, destApk.getAbsolutePath());
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(mInstallReceiver);
        super.onDestroy();
    }
}
