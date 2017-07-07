package cn.hudp.androiddevartnote.IPC_2;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import cn.hudp.androiddevartnote.Home.MyConfig;
import cn.hudp.androiddevartnote.Home.MyUtils;
import cn.hudp.androiddevartnote.R;

public class IPC2Activity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "IPC2Activity";
    protected Button btnMessenger;

    protected Messenger messenger;//客户端发送消息用
    protected Messenger getReplyMessenger = new Messenger(new MessengerHandler());//接受服务端返回消息用
    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            messenger = new Messenger(service);
            Message msg = Message.obtain(null, MyConfig.MSG_FROM_CLIENT);
            Bundle data = new Bundle();
            data.putString("msg", "你好， 我是客户端传来的消息");
            msg.setData(data);
            //如果需要服务端返回消息 需要
            msg.replyTo = getReplyMessenger;
            try {
                messenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ipc2);

        btnMessenger = (Button) findViewById(R.id.btn_messenger);
        btnMessenger.setOnClickListener(this);
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_messenger:
                startMessenger();
                break;
        }
    }

    private void startMessenger() {
        Log.e(TAG, "当前进程：" + MyUtils.getCurProcessName(this));
        Intent intent = new Intent(this, MessengerService.class);
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        if (mConnection != null) {
//            unbindService(mConnection);
        }
        super.onDestroy();
    }

    protected class MessengerHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MyConfig.MSG_FROM_SERVICE:
                    Log.e(TAG, "接受到服务端返回的消息：" + msg.getData().getString("reply"));
                    break;
                default:
                    super.handleMessage(msg);
                    break;
            }
        }
    }
}
