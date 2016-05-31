package cn.hudp.androiddevartnote.Other.BinaryTree;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import cn.hudp.androiddevartnote.R;

/**
 * 二叉树
 */
public class BinaryTreeActivity extends AppCompatActivity {
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binary_tree);
        tv = (TextView) findViewById(R.id.tv);


        BinaryTree tree = new BinaryTree();
        tree.addNode(2);
        tree.addNode(4);
        tree.addNode(3);
        tree.addNode(6);
        tree.addNode(2);
        tree.addNode(9);

        tree.printNode();

        tv.setText(BinaryTree.stringBuffer.toString());
    }
}
