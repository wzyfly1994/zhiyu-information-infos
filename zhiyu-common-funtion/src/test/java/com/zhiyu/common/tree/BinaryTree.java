package com.zhiyu.common.tree;

import com.alibaba.fastjson.JSON;
import lombok.Data;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author wengzhiyu
 * @since 2022/2/10 17:15
 */
@Data
public class BinaryTree {
    public Node tree;  //  树的根节点

    @Data
    public static class Node {
        int data;
        Node left;
        Node right;

        public Node(int data) {
            this.data = data;
            //this.left = null;
            //this.right = null;
        }
    }

    /**
     * 创建一棵临时的树
     * +----------------+
     * |              1       |
     * |          +       +
     * |        +          +      |
     * |      +             +     |
     * |     2               3    |
     * |    + +             +  +
     * |   +   +           +     +    |
     * |  4     5         10     11
     * | + +   +  +      +  +    +  +  |
     * |6   7  8  9     12  13  14  15   |
     * +----------------+
     * K
     *
     * @return
     */
    public Node createTreeTemp() {
        Node tree = new Node(1);
        tree.left = new Node(2);
        tree.right = new Node(3);
        tree.left.left = new Node(4);
        tree.left.right = new Node(5);

        tree.left.left.left = new Node(6);
        tree.left.left.right = new Node(7);

        tree.left.right.left = new Node(8);
        tree.left.right.right = new Node(9);

        tree.right.left = new Node(10);
        tree.right.right = new Node(11);

        tree.right.left.left = new Node(12);
        tree.right.left.right = new Node(13);

        tree.right.right.left = new Node(14);
        tree.right.right.right = new Node(15);

        return tree;
    }

    /**
     * 前序遍历 根左右
     *
     * @param tree
     */
    public void preOrder(Node tree) {
        if (tree == null) {
            return;
        }

        // 根节点
        System.out.print(tree.data + "  ");

        preOrder(tree.left);

        preOrder(tree.right);
    }

    /**
     * 中序遍历 左根右
     *
     * @param tree
     */
    public void inOrder(Node tree) {
        if (tree == null) {
            return;
        }

        inOrder(tree.left);

        // 根节点
        System.out.print(tree.data + "  ");

        inOrder(tree.right);
    }

    /**
     * 后序遍历 左右根
     *
     * @param tree
     */
    public void postOrder(Node tree) {
        if (tree == null) {
            return;
        }

        postOrder(tree.left);

        postOrder(tree.right);

        // 根节点
        System.out.print(tree.data + "  ");
    }

    /**
     * 层次遍历
     *
     * @param tree
     */
    public void BFSOrder(Node tree) {
        if (tree == null) {
            return;
        }

        Queue<Node> queue = new LinkedList<>();
        Node temp = null;
        // 使用 offer 和 poll 优于 add 和 remove 之处在于前者可以通过返回值判断成功与否，而不抛出异常
        queue.offer(tree);
        while (!queue.isEmpty()) {
            temp = queue.poll();
            System.out.print(temp.data + "  ");
            if (temp.left != null) {
                queue.offer(temp.left);
            }

            if (temp.right != null) {
                queue.offer(temp.right);
            }
        }
    }

    /**
     * 求树的高度，从 1 开始
     *
     * @param tree
     * @return
     */
    public int treeHeight(Node tree) {
        if (tree == null) {
            return 0;
        }

        int lHeight = treeHeight(tree.left);
        int rHeight = treeHeight(tree.right);

        return lHeight > rHeight ? lHeight + 1 : rHeight + 1;
    }

    public static void main(String[] args) {
        //BinaryTree bt = new BinaryTree();
        //bt.tree = bt.createTreeTemp();
        //System.out.println("树结构：" + JSON.toJSONString(bt.tree));
        //System.out.println("前序遍历：");
        //bt.preOrder(bt.tree);
        //System.out.println();
        //
        //System.out.println("中序遍历：");
        //bt.inOrder(bt.tree);
        //System.out.println();
        //
        //System.out.println("后序遍历：");
        //bt.postOrder(bt.tree);
        //System.out.println();
        //
        //System.out.println("层次遍历：");
        //bt.BFSOrder(bt.tree);
        //System.out.println();
        //
        //System.out.println("树的高度：");
        //System.out.println(bt.treeHeight(bt.tree));


        int[] arr = new int[]{1, 4, 3, 5, 6, 8, 9};

        Arrays.sort(arr);

        System.out.println(Arrays.toString(arr));
        System.out.println(bsearchWithoutRecursion(arr, 9));

    }


    // 二分查找
    public static int bsearchWithoutRecursion(int[] arr, int target) {
        if (arr.length == 0) {
            return -1;
        }
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            System.out.println("left --> " + left);
            System.out.println("right --> " + right);
            System.out.println("right - left --> " + (right - left));
            System.out.println("mid --> " + mid);

            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }


    public static int binarySearch(int[] arr, int target, int left, int right) {
        if (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] < target) {
                return binarySearch(arr, target, mid + 1, right);
            } else {
                return binarySearch(arr, target, left, mid - 1);
            }
        }
        return -1;
    }

}