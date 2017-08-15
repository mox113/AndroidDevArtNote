package cn.hudp.androiddevartnote.UpdateApp;

/**
 * Created by zhy on 16/10/7.
 */
public class BsPatch {

    static {
        System.loadLibrary("bsdiff");
    }

    public static native int bspatch(String oldApk, String newApk, String patch);

}
