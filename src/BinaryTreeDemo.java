import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BinaryTreeDemo {
    private static class Node{
        int data;
        Node left;
        Node right;
        Node (int data,Node left,Node right)
        {
            this.data=data;
            this.left=null;
            this.right=null;
        }
    }
    public static class pair{
        Node node;
        int state;
        pair(Node node,int state)
        {
            this.node=node;
            this.state=state;
        }
    }
    public static void display(Node node)
    {
        if(node==null)
        {
            return;
        }
        String str="";
        str+=node.left==null?".":node.left.data+"";
        str+="<-"+node.data+"->";
        str+=node.right==null?".":node.right.data+"";
        System.out.println(str);
        // node self work
        display(node.left);
        display(node.right);
    }
    public static int size(Node node)
    {
        // size
        if(node==null)
        {
            return 0;
        }
        int ls=size(node.left);
        int rs=size(node.right);
        int ts=ls+rs+1;
        return ts;


    }
    public static int sum(Node node)
    {
        //
        if(node ==null)
        {
            return 0;
        }
        int lsm=sum(node.left);
        int rsm=sum(node.right);
        int tsm=lsm+rsm+node.data;
        return tsm;

    }
    public static int max(Node node)
    {
        // max
        if(node==null)
        {
            return Integer.MIN_VALUE;
        }
        int lmax=max(node.left);
        int rmax=max(node.right);
        int max=Math.max(lmax,rmax);
        int allmax=Math.max(max,node.data);
        return  allmax;

    }
    public static int height(Node node)
    {
        // height
        if(node==null)
        {
            return -1;
        }
        int lh=height(node.left);
        int rh=height(node.right);
        int th=Math.max(lh,rh)+1;
        return th;


    }
    public static void traversal(Node node)
    {
        if(node==null)
        {
            return;
        }
        System.out.println(node.data+" in preorder");// euler left ->pre
        traversal(node.left);
        System.out.println(node.data+" in inorder");// euler between ->in
        traversal(node.right);
        System.out.println(node.data+" in postorder");// euler right->post
    }
    // level order traversal
    public static void levelOrderTraversal(Node node)
    {
        Queue<Node>mq=new ArrayDeque<>();
        mq.add(node);
        while(mq.size()>0)
        {
            int count=mq.size();
            for(int i=0;i<count;i++)
            {
                node=mq.remove();
                System.out.print(node.data+" ");
                if(node.left!=null)
                {
                    mq.add(node.left);
                }
                if(node.right!=null)
                {
                    mq.add(node.right);
                }
            }
            System.out.println();
        }

    }
    // preorder inorder postorder iteratively
    public static void preInPostOrder(Node node)
    {
        String pre="";
        String in="";
        String post="";
        pair rt=new pair(node ,1);
        Stack<pair>st=new Stack<>();
        st.push(rt);

        while(st.size()>0)
        {
            pair top=st.peek();
            if(top.state==1)
            {
                pre=pre+top.node.data+" ";
                if(top.node.left!=null)
                {
                    pair lp=new pair(top.node.left,1);
                    st.push(lp);
                }
                top.state++;


            }
            else if(top.state==2)
            {
                in=in+top.node.data+" ";
                if(top.node.right!=null)
                {
                    pair rp=new pair(top.node.right,1);
                    st.push(rp);
                }
                top.state++;

            }
            else
            {
                post=post+top.node.data+" ";
                st.pop();

            }


        }
        System.out.println(pre);
        System.out.println(in);
        System.out.println(post);




    }


    public static void main(String[] args) {
        Integer []arr={50,25,12,null,null,37,30,null,null,null,75,62,null,70,null,null,87,null,null};
        Node root=new Node(arr[0],null,null);
        pair rtp=new pair(root,1);
        Stack<pair>st=new Stack<>();
        st.push(rtp);
        int idx=0;
        while(st.size()>0)
        {
            pair top=st.peek();
            if(top.state==1)
            {
                idx++;
                if(arr[idx]!=null)
                {
                    Node ln=new Node(arr[idx],null,null);
                    top.node.left=ln;
                    pair lp=new pair(ln,1);
                    st.push(lp);

                }
                else
                {
                    top.node.left=null;
                }
                top.state++;
            }
            else if(top.state==2)
            {
                idx++;
                if(arr[idx]!=null)
                {
                    Node rn=new Node(arr[idx],null,null);
                    top.node.right=rn;
                    pair rp=new pair(rn,1);
                    st.push(rp);

                }
                else
                {
                    top.node.right=null;
                }
                top.state++;

            }
            else
            {
                st.pop();
            }
        }
        //display(root);
        int max=max(root);
        int size=size(root);
        int hight=height(root);
        int sum=sum(root);
        System.out.println(max);
        System.out.println(size);
        System.out.println(hight);
        System.out.println(sum);
        //traversal(root);
       // levelOrderTraversal(root);
        preInPostOrder(root);





    }
}
