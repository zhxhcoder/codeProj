package com.zhxh.codeproj.leetcode._bean;


import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
 * Created by zhxh on 2020/6/18
 * 特指二叉树
 *
 *
 */
public class TreeNode {

    public static void main(String[] args) {

        TreeNode node1 = buildBinaryTree(new Integer[]{1, 2, 3, null, 4, null, 5, null, 6});

        printTree(node1);

        System.out.println("\n*************\n");

        breakBinaryTree(node1);
    }

    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int x) {
        val = x;
    }


    //以下为静态方法
    //用于获得树的层数
    public static int getTreeDepth(TreeNode root) {
        return root == null ? 0 : (1 + Math.max(getTreeDepth(root.left), getTreeDepth(root.right)));
    }

    //中序遍历树
    public static void printTree(TreeNode root) {
        ArrayList<TreeNode> treeList = new ArrayList<>();
        if (root == null) {
            return;
        }
        treeList.add(root);
        while (treeList.size() > 0) {
            TreeNode node = treeList.remove(0);
            if (node.left != null) {
                treeList.add(node.left);
            }
            if (node.right != null) {
                treeList.add(node.right);
            }
            System.out.print("->" + node.val);
        }
    }


    private static boolean isSameTree(TreeNode node1, TreeNode node2) {
        return true;
    }

    /*
    见LeetCode102
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();

        Queue<TreeNode> queue = new ArrayDeque<>();
        if (root != null) {
            queue.add(root);
        }
        while (!queue.isEmpty()) {
            int n = queue.size();
            List<Integer> level = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                TreeNode node = queue.poll();
                level.add(node.val);
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            res.add(level);
        }
        return res;
    }

    /*
    与buildBinaryTree相对应 相互生成 中序
     */
    public static void breakBinaryTree(TreeNode root) {
        if (root == null) {
            return;
        }
        breakBinaryTree(root.left);
        System.out.print("->" + root.val);
        breakBinaryTree(root.right);
        if (root.left == null && root.right == null) {
            System.out.print("->null");
            return;
        }
    }

    public static TreeNode buildBinaryTree(Integer[] nums) {
        if (nums.length == 0) return new TreeNode(0);
        Deque<TreeNode> nodeQueue = new LinkedList<>();
        // 创建一个根节点
        TreeNode root = new TreeNode(nums[0]);
        nodeQueue.offer(root);
        TreeNode cur;
        // 记录当前行节点的数量（注意不一定是2的幂，而是上一行中非空节点的数量乘2）
        int lineNodeNum = 2;
        // 记录当前行中数字在数组中的开始位置
        int startIndex = 1;
        // 记录数组中剩余的元素的数量
        int restLength = nums.length - 1;

        while (restLength > 0) {
            for (int i = startIndex; i < startIndex + lineNodeNum; i = i + 2) {
                // 说明已经将nums中的数字用完，此时应停止遍历，并可以直接返回root
                if (i == nums.length) return root;
                cur = nodeQueue.poll();
                if (nums[i] != null) {
                    cur.left = new TreeNode(nums[i]);
                    nodeQueue.offer(cur.left);
                }
                // 同上，说明已经将nums中的数字用完，此时应停止遍历，并可以直接返回root
                if (i + 1 == nums.length) return root;
                if (nums[i + 1] != null) {
                    cur.right = new TreeNode(nums[i + 1]);
                    nodeQueue.offer(cur.right);
                }
            }
            startIndex += lineNodeNum;
            restLength -= lineNodeNum;
            lineNodeNum = nodeQueue.size() * 2;
        }
        return root;
    }
}
