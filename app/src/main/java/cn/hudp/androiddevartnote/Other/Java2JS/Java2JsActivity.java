package cn.hudp.androiddevartnote.Other.Java2JS;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Button;

import cn.hudp.androiddevartnote.R;

public class Java2JsActivity extends AppCompatActivity {
    WebView webView;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java2_js);
        webView = (WebView) findViewById(R.id.webview);
        btn = (Button) findViewById(R.id.btn);

        //开启JavaScript支持
        webView.getSettings().setJavaScriptEnabled(true);
        //放在assets的html需加上android_asset/  也可以用网络上的文件
        webView.loadUrl("file:///android_asset/show.html");
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String color = "#abcdaa";
                webView.loadUrl("javascript: changeColor('" + color + "')");
            }
        });
        // 添加一个对象, 让JS可以访问该对象的方法, 该对象中可以调用JS中的方法
        webView.addJavascriptInterface(new JSInterface1(), "baobao");
    }

    class JSInterface1 {
        //JavaScript调用此方法
        @JavascriptInterface
        public void showDialog(int a, float b, String c, boolean d) {
            if (d) {
                String strMessage = "a+b+c=" + a + b + c;
                new AlertDialog.Builder(Java2JsActivity.this).setTitle(c).setMessage(strMessage).show();
            }
        }
    }
}
