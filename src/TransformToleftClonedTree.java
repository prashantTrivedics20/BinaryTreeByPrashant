import java.util.Stack;

public class TransformToleftClonedTree {
    public static class Node
    {
        int data;
        Node left;
        Node right;
        Node(int data,Node left,Node right)
        {
            this.data=data;
            this.left=null;
            this.right=null;
        }
    }
    public static class Pair
    {
        Node node;
        int state;
        Pair(Node node,int state)
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
        display(node.left);
        display(node.right);
    }
    public static Node createLeftClonedTree(Node node)
    {
        if(node==null)
        {
            return null;
        }
        Node lcr=createLeftClonedTree(node.left);
        Node rcr=createLeftClonedTree(node.right);
        Node nn=new Node(node.data,lcr,null);
        node.left=nn;
        node.right=rcr;
        return node;
    }
    public static Node TransformBackFromALeftCloneTree(Node node)
    {
        if(node==null)
        {
            return null;
        }
        Node lnn=TransformBackFromALeftCloneTree(node.left.left);
        Node rnn=TransformBackFromALeftCloneTree(node.right);
        node.left=lnn;
        node.right=rnn;
        return node;
    }
    public static void printSingleChildNode(Node node,Node parent)
    {
        if(node==null)
        {
            return ;
        }
       if(parent!=null&&parent.left==node&&parent.right==null)
       {
           System.out.println(node.data);
       }
       else if(parent!=null&&parent.right==node&&parent.left==null)
       {
           System.out.println(node.data);
       }
        printSingleChildNode(node.left,node);
        printSingleChildNode(node.right,node);

    }
    public static Node removeLeaf(Node node)
    {
        if(node==null)
        {
            return null;
        }
        if(node.left==null&&node.right==null)
        {
            return null;
        }
        Node nlr=removeLeaf(node.left);
        Node nrr=removeLeaf(node.right);
        node.left=nlr;
        node.right=nrr;
        return node;
    }
    public  static void preorder(Node node)
    {
        if(node==null)
        {
            return;
        }
        System.out.println(node.data);
        preorder(node.left);
        preorder(node.right);
    }
    // height
    public static int height(Node node)
    {
        if(node==null)
        {
            return -1;
        }
        int lf=height(node.left);
        int rf=height(node.right);
        int max=Math.max(lf,rf)+1;
        return max;

    }
    // diameter
    public static int diameter1(Node node)
    {
        if(node==null)
        {
            return 0;
        }
        int ld=diameter1(node.left);//maximum distance of two node of left hand side
        int rd=diameter1(node.right);////maximum distance of two node of right hand side
        // maximum distance between left's deepest and right's deepest
        int f=height(node.left)+height(node.right)+2;//
        int dia=Math.max(f,Math.max(ld,rd));
        return dia;

    }
    // diameter 2
    static class DiaPair
    {
        int ht;
        int dia;
    }
    public static DiaPair diameter2(Node node)
    {
        if(node==null)
        {
            DiaPair bp=new DiaPair();
            bp.ht=-1;
            bp.dia=0;
            return bp;
        }
        DiaPair lp=diameter2(node.left);
        DiaPair rp=diameter2(node.right);
        DiaPair mp=new DiaPair();
        mp.ht=Math.max(lp.ht,rp.ht)+1;
        int fes=lp.ht+rp.ht+2;

        mp.dia=Math.max(fes,Math.max(lp.dia,rp.dia));
        return mp;

    }
    static int tilt=0;
    public static int tiltOfBinaryTree(Node node)
    {
        if(node==null)
        {
            return 0;
        }
        int ls=tiltOfBinaryTree(node.left);// will return left's sum and change tilt for left side
        int rs=tiltOfBinaryTree(node.right);// will return right's sum and change tilt for right side

        int ltilt=Math.abs(ls-rs);
        tilt=tilt+ltilt;
        int ts=ls+rs+node.data;
        return ts;
    }
    static boolean isBal=true;
    public static int isBalanced(Node node)
    {
        if(node==null)
        {
            return -1;
        }

        int lh=isBalanced(node.left);
        int rh=isBalanced(node.right);
        int gap=Math.abs(lh-rh);
        if(gap>1)
        {
            isBal=false;
        }
        int th=Math.max(lh,rh)+1;
        return th;
    }
    // check weather a tree is balanced tree or not by using pair class
    public static class BalPair {
        int ht;
        boolean isBal;


    }
    public static BalPair isbal(Node node)
    {
        if(node==null)
        {
            BalPair bp=new BalPair();
            bp.ht=0;
            bp.isBal=true;
            return bp;
        }
        BalPair  lp=isbal(node.left);
        BalPair rp=isbal(node.right);
        BalPair mp=new BalPair();
        mp.isBal=Math.abs(lp.ht-rp.ht)<=1&&lp.isBal&&rp.isBal;
        mp.ht=Math.max(lp.ht,rp.ht)+1;
        return mp;

    }


    public static void main(String[] args) {
        Integer []arr={50,25,12,null,null,37,30,null,null,null,75,62,null,70,null,null,87,null,null};
        Node root=new Node(arr[0],null,null);
        Pair rtp=new Pair(root,1);
        Stack<Pair>st=new Stack<>();
        st.push(rtp);
        int idx=0;
        while(st.size()>0)
        {
            Pair top=st.peek();
            if(top.state==1)
            {
                idx++;
                if(arr[idx]!=null)
                {
                    Node ln=new Node(arr[idx],null,null);
                    top.node.left=ln;
                    Pair lp=new Pair(ln,1);
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
                    Pair rp=new Pair(rn,1);
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
        //Node res=createLeftClonedTree(root);
       // display(createLeftClonedTree(root));
        //display(root);
        //Node result=TransformBackFromALeftCloneTree(res);
        //display(result);
       // printSingleChildNode(root,null);
       Node res= removeLeaf(root);

        // display(root);
        //preorder(res);
//      int diameter=  diameter1(root);
//        System.out.println(diameter);
//        DiaPair p=diameter2(root);
//        System.out.println(p.dia);
//        tiltOfBinaryTree(root);
//        System.out.println(tilt);
        isBalanced(root);
        System.out.println(isBal);


    }
}
