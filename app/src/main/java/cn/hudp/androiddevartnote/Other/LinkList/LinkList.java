package cn.hudp.androiddevartnote.Other.LinkList;

/**
 * Created by HuDP on 16/5/28.
 */
public class LinkList {
    private Node root;//根节点

    public static void Test(){
        LinkList ll = new LinkList();
        ll.addNode("1");
        ll.addNode("2");
        ll.addNode("3");
        ll.addNode("4");
        ll.printNode();
        ll.delNode("2");
        ll.printNode();
    }

    public void addNode(Object obj) {
        if (root == null) {
            root = new Node(obj);
        } else {
            root.add(obj);
        }
    }

    public void delNode(Object obj) {
        if (root.next.equals(obj)) {
            root = root.next;
        } else {
            root.del(obj);
        }
    }

    public void printNode() {
        if(root!=null){
            System.out.print(root.obj+"->");
        }
        root.print();
        System.out.println();
    }


    public class Node {
        private Object obj;
        private Node next;

        public Node(Object obj) {
            this.obj = obj;
        }

        //添加节点
        public void add(Object obj) {
            if (next == null) {
                next = new Node(obj);
            } else {
                this.next.add(obj);//递归
            }
        }

        //删除节点
        public void del(Object obj) {
            if (this.next != null) {
                if (this.next.obj.equals(obj)) {
                    this.next = this.next.next;
                }else{
                    this.next.del(obj);//递归
                }
            }
        }

        //输出节点
        public void print() {
            if(this.next!=null){
                System.out.print(this.next.obj);
                this.next.print();
            }
        }
    }
}
