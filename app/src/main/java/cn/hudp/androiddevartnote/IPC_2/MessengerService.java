package cn.hudp.androiddevartnote.IPC_2;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

import cn.hudp.androiddevartnote.Common.MyConfig;
import cn.hudp.androiddevartnote.Common.MyUtils;

/**
 * Messenger的服务端,在单独的进程中。
 */
public class MessengerService extends Service {

    private static final String TAG = "MessengerService";
    private final Messenger messenger = new Messenger(new MessengerHandler());

    @Override
    public IBinder onBind(Intent intent) {
        return messenger.getBinder();
    }

    private class MessengerHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MyConfig.MSG_FROM_CLIENT:
                    //接收客户端发来的消息
                    Log.e(TAG,"当前进程："+MyUtils.getCurProcessName(MessengerService.this));
                    Log.e(TAG, "消息来源于客户端：" + msg.getData().getString("msg"));

                    //服务器返回消息给客户端
                    Messenger client = msg.replyTo;
                    Message relpyMessage  =Message.obtain(null,MyConfig.MSG_FROM_SERVICE);
                    Bundle bundle = new Bundle();
                    bundle.putString("reply","消息收到了，已经处理完成了");
                    relpyMessage.setData(bundle);
                    try {
                        client.send(relpyMessage);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    super.handleMessage(msg);
                    break;
            }
        }
    }
}
