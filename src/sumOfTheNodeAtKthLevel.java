import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

public class sumOfTheNodeAtKthLevel {
    public static class Node {
        int data;
        Node left;
        Node right;

        Node(int data, Node left, Node right) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    public static class pair {
        Node node;
        int state;

        pair(Node node, int state) {
            this.node = node;
            this.state = state;
        }
    }

    public static int  sumOfTheNodeAtKthLevel1(Node node, int level) {
        Queue<Node> mq = new ArrayDeque<>();
        mq.add(node);
        int c = 0;
        int sum = 0;
        int f=0;
        while (mq.size() > 0) {

            int count = mq.size();
            for (int i = 0; i < count; i++) {
                if(c==level) {
                    f=1;
                    node = mq.remove();
                    sum = sum + node.data;

                    if (node.left != null) {
                        mq.add(node.left);
                    }
                    if (node.right != null) {
                        mq.add(node.right);
                    }
                }
                else
                {
                    node = mq.remove();

                    if (node.left != null) {
                        mq.add(node.left);
                    }
                    if (node.right != null) {
                        mq.add(node.right);
                    }
                }
            }
            c++;
            if(f==1)
            {
                break;
            }
        }
        return sum;




    }

    public static void preorderTraversal(Node root) {
        if (root == null) {
            return;
        }
        System.out.println(root.data);
        preorderTraversal(root.left);
        preorderTraversal(root.right);
    }

    public static void main(String[] args) {
        Integer []arr={50,25,12,null,null,37,30,null,null,null,75,62,null,70,null,null,87,null,null};
        Node root = new Node(arr[0], null, null);
        pair rtp = new pair(root, 1);
        Stack<pair> st = new Stack<>();
        st.push(rtp);
        int idx = 0;
        while (st.size() > 0) {
            pair top = st.peek();
            if (top.state == 1) {
                idx++;
                if (arr[idx] != null) {
                    Node ln = new Node(arr[idx], null, null);
                    top.node.left = ln;
                    pair lp = new pair(ln, 1);
                    st.push(lp);

                } else {
                    top.node.left = null;
                }
                top.state++;
            } else if (top.state == 2) {
                idx++;
                if (arr[idx] != null) {
                    Node rn = new Node(arr[idx], null, null);
                    top.node.right = rn;
                    pair rp = new pair(rn, 1);
                    st.push(rp);

                } else {
                    top.node.right = null;
                }
                top.state++;

            } else {
                st.pop();
            }
        }

     int res=  sumOfTheNodeAtKthLevel1(root,2);
        System.out.println(res);

       // preorderTraversal(root);
}





}
