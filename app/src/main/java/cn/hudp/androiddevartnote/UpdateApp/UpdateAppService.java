package cn.hudp.androiddevartnote.UpdateApp;

import android.app.DownloadManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.io.File;

/**
 * Created by HuDP on 2017/8/11.
 */
public class UpdateAppService extends Service {
    public static final String FILE_DOC_PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Download";
    private static final String  TITLE = "TITLE", CONTENT = "CONTENT", URL = "URL";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public static void start(Context context, String title, String content, String url) {
        Intent intent = new Intent(context, UpdateAppService.class);
        intent.putExtra(TITLE, title);
        intent.putExtra(CONTENT, content);
        intent.putExtra(URL, url);
        context.startService(intent);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String title = intent.getStringExtra(TITLE);
        String content = intent.getStringExtra(CONTENT);
        String url = intent.getStringExtra(URL);
        download(title, content, url);
        return super.onStartCommand(intent, flags, startId);
    }

    public void download(String title, String content, String url) {
        File file = new File(FILE_DOC_PATH, title);
        if (file.exists()) {
            file.delete();
        }
        DownloadManager dowanloadmanager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        request.setDestinationInExternalFilesDir(getApplicationContext(), FILE_DOC_PATH, title);
        request.setTitle(title);
        request.setDescription(content);
        request.setMimeType("application/vnd.android.package-archive");
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE | DownloadManager.Request.NETWORK_WIFI);
        request.allowScanningByMediaScanner();
        request.setVisibleInDownloadsUi(true);
        dowanloadmanager.enqueue(request);
    }
}
