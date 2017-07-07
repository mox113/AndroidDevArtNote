package cn.hudp.androiddevartnote.Other.Reflect;

/**
 * Created by HuDP on 2017/7/7.
 */

public class ReflectTestInfo {
    private String name;
    private int age;

    public ReflectTestInfo(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ReflectTestInfo{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
