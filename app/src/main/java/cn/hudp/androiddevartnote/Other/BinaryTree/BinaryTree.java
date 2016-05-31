package cn.hudp.androiddevartnote.Other.BinaryTree;

import android.widget.TextView;

/**
 * Created by HuDP on 16/5/29.
 */
public class BinaryTree {
    private Node root;
    public static StringBuffer stringBuffer = new StringBuffer();

    public void addNode(int data) {
        if (root == null) {
            root = new Node(data);
        } else {
            root.add(data);
        }
    }

    public void printNode() {
        if (root != null) {
            root.print();
        }
    }

    private class Node {
        private int data;
        private Node left;
        private Node right;

        public Node(int data) {
            this.data = data;
        }

        //添加节点
        public void add(int data) {
            if (this.data > data) {
                if (this.left == null) {
                    this.left = new Node(data);
                } else {
                    this.left.add(data);//递归
                }
            } else if (this.data <= data) {
                if (this.right == null) {
                    this.right = new Node(data);
                } else {
                    this.right.add(data);
                }
            }
        }

        //中序遍历 左根右
        public void print() {
            if (this.left != null) {
                this.left.print();
            }
            System.out.print(this.data + "->");
            stringBuffer.append(this.data + "->");
            if (this.right != null) {
                this.right.print();
            }
        }

    }
}
