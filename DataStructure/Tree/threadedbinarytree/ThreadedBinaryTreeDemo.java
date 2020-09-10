package com.Tree.threadedbinarytree;
/**
 * @
 * @date 2020/9/3 - 14:16
 */
public class ThreadedBinaryTreeDemo {
    public static void main(String[] args) {
        //测试一把中序线索二叉树的功能
        HeroNode root = new HeroNode(1, "tom");
        HeroNode node2 = new HeroNode(3, "jack");
        HeroNode node3 = new HeroNode(6, "smith");
        HeroNode node4 = new HeroNode(8, "mary");
        HeroNode node5 = new HeroNode(10, "king");
        HeroNode node6 = new HeroNode(14, "dim");

        //二叉树，后面我们要递归创建, 现在简单处理使用手动创建
        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);

        //测试中序线索化
        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree();
        threadedBinaryTree.setRoot(root);
        threadedBinaryTree.threadedNodes();

        //测试: 以10号节点测试
        HeroNode leftNode = node5.getLeft();
        HeroNode rightNode = node5.getRight();
        System.out.println("10号结点的前驱结点是 ="  + leftNode); //3
        System.out.println("10号结点的后继结点是="  + rightNode); //1
    }
}

//定义 ThreadedBinaryTree 实现了线索化功能的二叉树
class ThreadedBinaryTree {
    private HeroNode root;
    private HeroNode pre = null;

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    public void threadedNodes(){
        threadedNodes(root);
    }

    //遍历线索化二叉树的方法
    public void threadedList(){
        //定义一个变量，存储当前遍历的结点，从root开始
        HeroNode node = root;
        while (node != null){
            //循环的找到leftType == 1的结点，第一个找到就是8结点
            //后面随着遍历而变化,因为当leftType==1时，说明该结点是按照线索化
            //处理后的有效结点
            while (node.getLeftType() == 0){
                node = node.getLeft();
            }
            //打印当前这个结点
            System.out.println(node);
            //如果当前结点的右指针指向的是后继结点,就一直输出
            while (node.getRightType() == 1){
                //获取到当前结点的后继结点
                node = node.getRight();
                System.out.println(node);
            }
            //替换这个遍历的结点
            node = node.getRight();
        }
    }


    /**
     *
     * @param node 就是当前需要线索化的节点
     */
    public void threadedNodes(HeroNode node){
        //如果node==null, 不能线索化
        if (node == null){
            return;
        }
        //(一)先线索化左子树
        threadedNodes(node.getLeft());
        //(二)线索化当前结点[有难度]

        //处理当前结点的前驱结点
        //以8结点来理解
        //8结点的.left = null , 8结点的.leftType = 1
        if (node.getLeft() == null){
            //让当前结点的左指针指向前驱结点
            node.setLeft(pre);
            //修改当前结点的左指针的类型,指向前驱结点
            node.setLeftType(1);
        }
        //处理后继结点
        if (pre != null && pre.getRight() == null){
            //让前驱结点的右指针指向当前结点
            pre.setRight(node);
            //修改前驱结点的右指针类型
            node.setRightType(1);
        }
        //!!! 每处理一个结点后，让当前结点是下一个结点的前驱结点
        pre = node;
        //(三)在线索化右子树
        threadedNodes(node.getRight());
    }
    //删除结点
    public void delete(int No){
        if (root == null){
            System.out.println("this tree is empty, so it's nothing to delete.");
        }else {
            //如果只有一个root结点, 这里立即判断root是不是就是要删除结点
            if (root.getNo() == No){
                root = null;
            }else {
                //递归删除
                root.delete(No);
            }
        }
    }
    // 前序遍历
    public void preOrder() {
        if (root != null) {
            this.root.preOrder();
        } else {
            System.out.println("The binary tree is empty, so i can't traverse.");
        }
    }

    // 中序遍历
    public void infixOrder() {
        if (root != null) {
            this.root.infixOrder();
        } else {
            System.out.println("The binary tree is empty, so i can't traverse.");
        }
    }

    // 后序遍历
    public void postOrder() {
        if (root != null) {
            this.root.postOrder();
        } else {
            System.out.println("The binary tree is empty, so i can't traverse.");
        }
    }

    // 前序遍历查找
    public HeroNode preSearch(int No) {
        if (root != null) {
            return root.preSearch(No);
        } else {
            return null;
        }
    }

    // 中序遍历查找
    public HeroNode infixSearch(int No) {
        if (root != null) {
            return root.infixSearch(No);
        } else {
            return null;
        }
    }

    // 后序遍历查找
    public HeroNode postSearch(int No) {
        if (root != null) {
            return root.postSearch(No);
        } else {
            return null;
        }
    }
}

// 创建 HeroNode 节点
class HeroNode {
    private int No;
    private String name;
    private HeroNode left;//默认null
    private HeroNode right;//默认null

    //说明
    //1. 如果leftType == 0 表示指向的是左子树, 如果 1 则表示指向前驱结点
    //2. 如果rightType == 0 表示指向是右子树, 如果 1表示指向后继结点
    private int leftType;
    private int rightType;

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    public HeroNode(int no, String name) {
        No = no;
        this.name = name;
    }

    public int getNo() {
        return No;
    }

    public void setNo(int no) {
        No = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "No=" + No +
                ", name='" + name + '\'' +
                '}';
    }

    //编写前序遍历的方法
    public void preOrder() {
        //先输出父结点
        System.out.println(this);
        //递归向左子树前序遍历
        if (this.left != null) {
            this.left.preOrder();
        }
        //递归向右子树前序遍历
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    // 中序遍历
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    //    后序遍历
    public void postOrder() {
        if (this.right != null) {
            this.right.postOrder();
        }
        if (this.left != null) {
            this.left.postOrder();
        }
        System.out.println(this);
    }

    // 前序遍历查找

    /**
     * @param No 需要查找的 No
     * @return 如果找到就返回 Hero，否则就返回 null
     */
    public HeroNode preSearch(int No) {
        System.out.println("plunge into tree");
        //比较当前结点是不是
        if (this.No == No) {
            return this;
        }
        //1.则判断当前结点的左子节点是否为空，如果不为空，则递归前序查找
        //2.如果左递归前序查找，找到结点，则返回
        HeroNode reshero = null;
        if (this.left != null) {
            reshero = this.left.preSearch(No);
        }
        if (reshero != null) {//说明我们左子树找到
            return reshero;
        }
        //1.左递归前序查找，找到结点，则返回，否继续判断，
        //2.当前的结点的右子节点是否为空，如果不空，则继续向右递归前序查找
        if (this.right != null) {
            reshero = this.right.preSearch(No);
        }
        return reshero;
    }

    //中序遍历查找
    public HeroNode infixSearch(int No) {
        HeroNode reshero = null;
        if (this.left != null) {
            reshero = this.left.infixSearch(No);
        }
        if (reshero != null) {
            return reshero;
        }
        System.out.println("plunge into tree");
        if (this.No == No) {
            return this;
        }
        if (this.right != null) {
            reshero = this.right.infixSearch(No);
        }
        return reshero;
    }

    // 后序遍历查找
    public HeroNode postSearch(int No) {
        HeroNode reshero = null;
        if (this.left != null) {
            reshero = this.left.postSearch(No);
        }
        if (reshero != null) {
            return reshero;
        }
        if (this.right != null) {
            reshero = this.right.postSearch(No);
        }
        if (reshero != null) {
            return reshero;
        }
        System.out.println("plunge into tree");
        if (this.No == No) {
            return this;
        }
        return reshero;
    }

    //递归删除结点
    //1.如果删除的节点是叶子节点，则删除该节点
    //2.如果删除的节点是非叶子节点，则删除该子树
    public void delete(int No) {
        //思路
		/*1. 因为我们的二叉树是单向的，所以我们是判断当前结点的子结点是否需要删除结点，而不能去判断当前这个结点是不是需要删除结点.
        2. 如果当前结点的左子结点不为空，并且左子结点 就是要删除结点，就将this.left = null; 并且就返回(结束递归删除)
        3. 如果当前结点的右子结点不为空，并且右子结点 就是要删除结点，就将this.right= null ;并且就返回(结束递归删除)
        4. 如果第2和第3步没有删除结点，那么我们就需要向左子树进行递归删除
        5.  如果第4步也没有删除结点，则应当向右子树进行递归删除.*/

        //1. 如果当前结点的左子结点不为空，并且左子结点 就是要删除结点，就将this.left = null; 并且就返回(结束递归删除)
        if (this.left != null && this.left.No == No){
            this.left = null;
        }
        //2.如果当前结点的右子结点不为空，并且右子结点 就是要删除结点，就将this.right= null ;并且就返回(结束递归删除)
        if (this.right != null && this.right.No == No){
            this.right = null;
        }
        //3.我们就需要向左子树进行递归删除
        if (this.left != null){
            this.left.delete(No);
        }
        //4.则应当向右子树进行递归删除
        if (this.right != null){
            this.right.delete(No);
        }
    }
}