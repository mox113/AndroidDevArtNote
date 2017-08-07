package cn.hudp.androiddevartnote.Other.LinkList;

/**
 * 节点类
 * Created by HuDP on 2016/5/27.
 */
public class Node {
    protected Object data;//节点数据
    protected Node next;//下一个节点的引用

    public Node(Object data) {
        this.data = data;
    }

    public void display() {
        System.out.println(data.toString());
    }
}
