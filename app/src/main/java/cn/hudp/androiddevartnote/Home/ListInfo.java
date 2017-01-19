package cn.hudp.androiddevartnote.Home;

import android.app.Activity;

/**
 * Created by HuDP on 16/5/28.
 */
public class ListInfo {
    private String show;
    private Class activityClass;

    public ListInfo(String show, Class activityClass) {
        this.show = show;
        this.activityClass = activityClass;
    }

    @Override
    public String toString() {
        return "ListInfo{" +
                "show='" + show + '\'' +
                ", activityClass=" + activityClass +
                '}';
    }

    public String getShow() {
        return show;
    }

    public void setShow(String show) {
        this.show = show;
    }

    public Class<Activity> getActivityClass() {
        return activityClass;
    }

    public void setActivityClass(Class<Activity> activityClass) {
        this.activityClass = activityClass;
    }
}
