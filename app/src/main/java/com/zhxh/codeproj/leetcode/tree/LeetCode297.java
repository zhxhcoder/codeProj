package com.zhxh.codeproj.leetcode.tree;

import com.zhxh.codeproj.leetcode.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/*
序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，同时也可以通过网络传输到另一个计算机环境，采取相反方式重构得到原数据。

请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。

示例:

你可以将以下二叉树：

    1
   / \
  2   3
     / \
    4   5

序列化为 "[1,2,3,null,null,4,5]"
提示:这与 LeetCode 目前使用的方式一致，详情请参阅LeetCode 序列化二叉树的格式。你并非必须采取这种方式，你也可以采用其他的方法解决这个问题。

说明:不要使用类的成员 / 全局 / 静态变量来存储状态，你的序列化和反序列化算法应该是无状态的。

 */
class LeetCode297 {
    public static void main(String[] args) {

        TreeNode node = TreeNode.buildBinaryTree(new Integer[]{1, 2, 3, null, null, 4, 5});
        System.out.println(new Solution().serialize(node));
        TreeNode.prettyPrintTree(node);

        String str1 = "[1,2,3,null,null,4,5]";
        TreeNode node1 = new Solution().deserialize(str1);
        TreeNode.prettyPrintTree(node1);

        // System.out.println(new Solution2().serialize(node1));
    }

    //去掉末尾无用的null
    static class Solution {
        public String serialize(TreeNode root) {
            StringBuilder res = new StringBuilder("[");
            Queue<TreeNode> queue = new LinkedList();
            queue.add(root);
            while (!queue.isEmpty()) {
                TreeNode cur = queue.remove();
                if (cur == null) {
                    res.append("null,");
                } else {
                    res.append(cur.val + ",");
                    queue.add(cur.left);
                    queue.add(cur.right);
                }
            }
            res.setLength(res.length() - 1);
            res.append("]");

            String result = res.toString();
            while (result.endsWith(",null]")) {
                result = result.replace(",null]", "]");
            }
            return result;
        }

        public TreeNode deserialize(String data) {
            String[] nodes = data.substring(1, data.length() - 1).split(",");
            TreeNode root = getNode(nodes[0]);
            Queue<TreeNode> parents = new LinkedList();
            TreeNode parent = root;
            boolean isLeft = true;
            for (int i = 1; i < nodes.length; i++) {
                TreeNode cur = getNode(nodes[i]);
                if (isLeft) {
                    parent.left = cur;
                } else {
                    parent.right = cur;
                }
                if (cur != null) {
                    parents.add(cur);
                }
                isLeft = !isLeft;
                if (isLeft) {
                    parent = parents.poll();
                }
            }
            return root;
        }

        private TreeNode getNode(String val) {
            if (val.equals("null")) {
                return null;
            }
            return new TreeNode(Integer.parseInt(val));
        }
    }
    /*
    方法一：深度优先搜索
思路和算法

二叉树的序列化本质上是对其值进行编码，更重要的是对其结构进行编码。可以遍历树来完成上述任务。众所周知，我们一般有两个策略：BFS / DFS。

BFS 可以按照层次的顺序从上到下遍历所有的节点
DFS 可以从一个根开始，一直延伸到某个叶，然后回到根，到达另一个分支。根据根节点、左节点和右节点之间的相对顺序，可以进一步将DFS策略区分为：
先序遍历
中序遍历
后序遍历
     */


    static class Solution1 {
        public String serialize(TreeNode root) {
            StringBuilder builder = new StringBuilder();
            LinkedList<TreeNode> stack = new LinkedList<>();
            stack.offer(root);
            while (!stack.isEmpty()) {
                TreeNode node = stack.poll();
                if (node != null) {
                    builder.append(node.val);
                    stack.offer(node.left);
                    stack.offer(node.right);
                } else
                    builder.append("null");
                builder.append(",");
            }
            return builder.toString();
        }


        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            String[] splits = data.split(",");
            if (splits[0].equals("null"))
                return null;
            TreeNode root = new TreeNode(Integer.parseInt(splits[0]));
            LinkedList<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            for (int i = 1; i < splits.length; ++i) {
                TreeNode node = queue.poll();
                if (node == null)
                    continue;
                if (!splits[i].equals("null")) {
                    node.left = new TreeNode(Integer.parseInt(splits[i++]));
                    queue.offer(node.left);
                } else
                    ++i;
                if (!splits[i].equals("null")) {
                    node.right = new TreeNode(Integer.parseInt(splits[i]));
                    queue.offer(node.right);
                }
            }
            return root;
        }
    }

    static class Solution2 {
        public String serialize(TreeNode root) {
            //tree: [v1,v2,null,...]
            StringBuilder res = new StringBuilder("[");
            Queue<TreeNode> queue = new LinkedList();
            queue.add(root);
            while (!queue.isEmpty()) {
                TreeNode cur = queue.remove();
                if (cur == null) {
                    res.append("null,");
                } else {
                    res.append(cur.val + ",");
                    queue.add(cur.left);
                    queue.add(cur.right);
                }
            }
            res.setLength(res.length() - 1);
            res.append("]");
            return res.toString();
        }

        public TreeNode deserialize(String data) {
            String[] nodes = data.substring(1, data.length() - 1).split(",");
            TreeNode root = getNode(nodes[0]);
            Queue<TreeNode> parents = new LinkedList();
            TreeNode parent = root;
            boolean isLeft = true;
            for (int i = 1; i < nodes.length; i++) {
                TreeNode cur = getNode(nodes[i]);
                if (isLeft) {
                    parent.left = cur;
                } else {
                    parent.right = cur;
                }
                if (cur != null) {
                    parents.add(cur);
                }
                isLeft = !isLeft;
                if (isLeft) {
                    parent = parents.poll();
                }
            }
            return root;
        }

        private TreeNode getNode(String val) {
            if (val.equals("null")) {
                return null;
            }
            return new TreeNode(Integer.valueOf(val));
        }
    }
}
