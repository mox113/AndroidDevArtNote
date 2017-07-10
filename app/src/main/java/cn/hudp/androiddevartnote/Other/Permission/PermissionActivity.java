package cn.hudp.androiddevartnote.Other.Permission;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import cn.hudp.androiddevartnote.R;

/**
 * 权限练习 6.0
 */
public class PermissionActivity extends AppCompatActivity {
    public Button btnRequest;
    private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 0x001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);

        init();
    }

    private void init() {
        //检查是否有权限
        int permissionCheck = ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_CALENDAR);
        if (permissionCheck == PackageManager.GET_INTENT_FILTERS) {
            Toast.makeText(PermissionActivity.this, "hava this permission", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(PermissionActivity.this, "no this permission", Toast.LENGTH_SHORT).show();
        }


        btnRequest = (Button) findViewById(R.id.btn_request);
        btnRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //判断是否有权限
                if (ContextCompat.checkSelfPermission(PermissionActivity.this,
                        Manifest.permission.READ_CONTACTS)
                        != PackageManager.PERMISSION_GRANTED) {
                    //当用户拒绝掉权限时.
                    if (ActivityCompat.shouldShowRequestPermissionRationale(PermissionActivity.this,
                            Manifest.permission.READ_CONTACTS)) {

                        Toast.makeText(PermissionActivity.this, "true", Toast.LENGTH_SHORT).show();
                        AlertDialog dialog = new AlertDialog.Builder(PermissionActivity.this).setTitle("该权限保证手机不会爆炸^ ^").setPositiveButton("我需要此权限!", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ActivityCompat.requestPermissions(PermissionActivity.this,
                                        new String[]{Manifest.permission.READ_CONTACTS},
                                        MY_PERMISSIONS_REQUEST_READ_CONTACTS);
                            }
                        }).setNegativeButton("炸吧炸吧~", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(PermissionActivity.this, "准备爆炸了", Toast.LENGTH_SHORT).show();
                            }
                        }).show();

                    } else {
                        Toast.makeText(PermissionActivity.this, "false", Toast.LENGTH_SHORT).show();
                        ActivityCompat.requestPermissions(PermissionActivity.this,
                                new String[]{Manifest.permission.READ_CONTACTS},
                                MY_PERMISSIONS_REQUEST_READ_CONTACTS);

                    }
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_CONTACTS: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                } else {
                    showDialog();
                }
            }
        }

    }

    private void showDialog() {
        AlertDialog dialog = new AlertDialog.Builder(this).setTitle("还可以手动开启哦~").setMessage("可以前往设置->app->myapp->permission打开").setPositiveButton("确定!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        }).show();
    }

}
