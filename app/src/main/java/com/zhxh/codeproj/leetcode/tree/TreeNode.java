package com.zhxh.codeproj.leetcode.tree;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int x) {
        val = x;
    }

    /**************************************************************************************************************************/
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            TreeNode root = stringToTreeNode(line);
            prettyPrintTree(root);
        }
    }

    public static String treeNodeToString(TreeNode root) {
        if (root == null) {
            return "[]";
        }

        String output = "";
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);
        while (!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.remove();

            if (node == null) {
                output += "null, ";
                continue;
            }

            output += String.valueOf(node.val) + ", ";
            nodeQueue.add(node.left);
            nodeQueue.add(node.right);
        }
        return "[" + output.substring(0, output.length() - 2) + "]";
    }

    public static TreeNode stringToTreeNode(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) {
            return null;
        }

        String[] parts = input.split(",");
        String item = parts[0];
        TreeNode root = new TreeNode(Integer.parseInt(item));
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);

        int index = 1;
        while (!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.remove();

            if (index == parts.length) {
                break;
            }

            item = parts[index++];
            item = item.trim();
            if (!item.equals("null")) {
                int leftNumber = Integer.parseInt(item);
                node.left = new TreeNode(leftNumber);
                nodeQueue.add(node.left);
            }

            if (index == parts.length) {
                break;
            }

            item = parts[index++];
            item = item.trim();
            if (!item.equals("null")) {
                int rightNumber = Integer.parseInt(item);
                node.right = new TreeNode(rightNumber);
                nodeQueue.add(node.right);
            }
        }
        return root;
    }

    public static void prettyPrintTree(TreeNode node, String prefix, boolean isLeft) {
        if (node == null) {
            System.out.println("Empty tree");
            return;
        }

        if (node.right != null) {
            prettyPrintTree(node.right, prefix + (isLeft ? "│   " : "    "), false);
        }

        System.out.println(prefix + (isLeft ? "└── " : "┌── ") + node.val);

        if (node.left != null) {
            prettyPrintTree(node.left, prefix + (isLeft ? "    " : "│   "), true);
        }
    }

    public static void prettyPrintTree(TreeNode node) {
        prettyPrintTree(node, "", true);
    }

    /**************************************************************************************************************************/

    //用于获得树的层数
    public static int getBinaryTreeDepth(TreeNode root) {
        return root == null ? 0 : (1 + Math.max(getBinaryTreeDepth(root.left), getBinaryTreeDepth(root.right)));
    }

    private static boolean isSameBinaryTree(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) {
            return true;
        }
        if (root1 == null || root2 == null) {
            return false;
        }
        if (root1.val != root2.val) {//判断每个节点的值是否相等，如果去除此判断，则判断两个二叉树是否结构相等
            return false;
        }
        return isSameBinaryTree(root1.left, root2.left) && isSameBinaryTree(root1.right, root2.right);
    }

    /*
    见LeetCode102
     */
    public static List<List<Integer>> binaryTree2List(TreeNode root) {
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


    public static void printBinaryTree(TreeNode root) {
        if (root == null) {
            return;
        }
        System.out.print("\n**********begin***********\n");
        System.out.print("先序遍历");
        preBinaryTree(root);
        System.out.print("\n中序遍历");
        ldrBinaryTree(root);
        System.out.print("\n后序遍历");
        lrdBinaryTree(root);
        System.out.print("\n***********end************\n");
    }

    /*

  关于如何解决

   1
   / \
  4   2
    /
   3

   1
   / \
  4   2
  /
 3

{1,4,2,3}

此类问题

精确表示

经验值

上面是  {1,4,2,null,null,3}
下面是  {1,4,2,3}

凡是root(4)不为null，所以孩子null，null，都要标记出来

     */

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

    /*
    与buildBinaryTree相对应 相互生成 中序
     */
    private static void ldrBinaryTree(TreeNode root) {
        if (root == null) {
            return;
        }
        ldrBinaryTree(root.left);
        System.out.print("->" + root.val);
        ldrBinaryTree(root.right);
    }

    //先序遍历
    private static void preBinaryTree(TreeNode root) {
        if (root == null) {
            return;
        }
        System.out.print("->" + root.val);
        preBinaryTree(root.left);
        preBinaryTree(root.right);
    }

    //后序遍历
    private static void lrdBinaryTree(TreeNode root) {
        if (root == null) {
            return;
        }
        lrdBinaryTree(root.left);
        lrdBinaryTree(root.right);
        System.out.print("->" + root.val);
    }

}
