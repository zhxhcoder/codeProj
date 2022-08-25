package com.zhxh.codeproj.leetcode.day5;

import com.zhxh.codeproj.leetcode.__base.TreeNode;

import java.util.HashMap;
import java.util.Map;

/*
根据一棵树的前序遍历与中序遍历构造二叉树。

注意:
你可以假设树中没有重复的元素。

例如，给出

前序遍历 preorder =[3,9,20,15,7]
中序遍历 inorder = [9,3,15,20,7]
返回如下的二叉树：

    3
   / \
  9  20
    /  \
   15   7

 */
class LeetCode105 {
    public static void main(String[] args) {
        TreeNode node = new Solution().buildTree(
                new int[]{3, 9, 20, 15, 7},
                new int[]{9, 3, 15, 20, 7});
        TreeNode.prettyPrintTree(node);
        System.out.println(TreeNode.serialize(node));


        TreeNode node2 = new Solution2().buildTree(
                new int[]{3, 9, 20, 15, 7},
                new int[]{9, 3, 15, 20, 7});
        TreeNode.prettyPrintTree(node2);
        System.out.println(TreeNode.serialize(node2));
    }

    /*
    二叉树前序遍历的顺序为：

先遍历根节点；
随后递归地遍历左子树；
最后递归地遍历右子树。

二叉树中序遍历的顺序为：
先递归地遍历左子树；
随后遍历根节点；
最后递归地遍历右子树。
在「递归」地遍历某个子树的过程中，我们也是将这颗子树看成一颗全新的树，按照上述的顺序进行遍历。挖掘「前序遍历」和「中序遍历」的性质，我们就可以得出本题的做法。


对于任意一颗树而言，前序遍历的形式总是

[ 根节点, [左子树的前序遍历结果], [右子树的前序遍历结果] ]
即根节点总是前序遍历中的第一个节点。而中序遍历的形式总是

[ [左子树的中序遍历结果], 根节点, [右子树的中序遍历结果] ]
只要我们在中序遍历中定位到根节点，那么我们就可以分别知道左子树和右子树中的节点数目。由于同一颗子树的前序遍历和中序遍历的长度显然是相同的，因此我们就可以对应到前序遍历的结果中，对上述形式中的所有左右括号进行定位。

这样以来，我们就知道了左子树的前序遍历和中序遍历结果，以及右子树的前序遍历和中序遍历结果，我们就可以递归地对构造出左子树和右子树，再将这两颗子树接到根节点的左右位置。
细节
在中序遍历中对根节点进行定位时，一种简单的方法是直接扫描整个中序遍历的结果并找出根节点，但这样做的时间复杂度较高。我们可以考虑使用哈希映射（HashMap）来帮助我们快速地定位根节点。对于哈希映射中的每个键值对，键表示一个元素（节点的值），值表示其在中序遍历中的出现位置。在构造二叉树的过程之前，我们可以对中序遍历的列表进行一遍扫描，就可以构造出这个哈希映射。在此后构造二叉树的过程中，我们就只需要 O(1)O(1) 的时间对根节点进行定位了。
     */

    static class Solution {
        private Map<Integer, Integer> indexMap;

        public TreeNode myBuildTree(int[] preorder, int preorder_left, int preorder_right, int inorder_left, int inorder_right) {
            if (preorder_left > preorder_right) {
                return null;
            }

            // 前序遍历中的第一个节点就是根节点
            int preorder_root = preorder_left;
            // 在中序遍历中定位根节点 就是pIndex值
            int inorder_root = indexMap.get(preorder[preorder_root]);

            // 先把根节点建立出来
            TreeNode root = new TreeNode(preorder[preorder_root]);
            // 得到左子树中的节点数目
            int size_left_subtree = inorder_root - inorder_left;
            // 递归地构造左子树，并连接到根节点
            // 先序遍历中「从 左边界+1 开始的 size_left_subtree」个元素就对应了中序遍历中「从 左边界 开始到 根节点定位-1」的元素
            root.left = myBuildTree(preorder, preorder_left + 1, preorder_left + size_left_subtree, inorder_left, inorder_root - 1);
            // 递归地构造右子树，并连接到根节点
            // 先序遍历中「从 左边界+1+左子树节点数目 开始到 右边界」的元素就对应了中序遍历中「从 根节点定位+1 到 右边界」的元素
            root.right = myBuildTree(preorder, preorder_left + size_left_subtree + 1, preorder_right, inorder_root + 1, inorder_right);
            return root;
        }

        public TreeNode buildTree(int[] preorder, int[] inorder) {
            int n = preorder.length;
            // 构造哈希映射，帮助我们快速定位根节点
            indexMap = new HashMap<Integer, Integer>();
            for (int i = 0; i < n; i++) {
                indexMap.put(inorder[i], i);
            }
            return myBuildTree(preorder, 0, n - 1, 0, n - 1);
        }
    }

    /*分而治之 -官方视频的 */
    static class Solution2 {
        public TreeNode buildTree(int[] preorder, int[] inorder) {
            int preLen = preorder.length;
            int inLen = inorder.length;

            if (preLen != inLen) {
                throw new RuntimeException("incorrect input data");
            }
            Map<Integer, Integer> map = new HashMap<>(inLen);
            for (int i = 0; i < inLen; i++) {
                map.put(inorder[i], i);
            }
            return buildTree(preorder, 0, preLen - 1, map, 0, inLen - 1);
        }

        /**
         * @param preorder 前序遍历序列
         * @param preLeft  前序遍历序列子区间的左边界，可以取到
         * @param preRight 前序遍历序列子区间的右边界，可以取到
         * @param map      在中序遍历序列中，数值与下标的对应关系
         * @param inLeft   中序遍历序列子区间的左边界，可以取到
         * @param inRight  前序遍历序列子区间的右边界，可以取到
         * @return
         */
        private TreeNode buildTree(int[] preorder, int preLeft, int preRight, Map<Integer, Integer> map, int inLeft, int inRight) {
            //递归终止条件
            if (preLeft > preRight || inLeft > inRight) {
                return null;
            }
            //构造根节点
            int rootVal = preorder[preLeft];
            TreeNode root = new TreeNode(rootVal);
            int pIndex = map.get(rootVal);

            //前序遍历  根(preLeft)   (preLeft+1)左子树(pIndex-inLeft+preLeft)   (pIndex-inLeft+preLeft+1)右子树(preRight)
            //中序遍历  (inLeft)左子树(pIndex-1)   根(pIndex)    (pIndex+1)右子树(inRight)
            root.left = buildTree(preorder, preLeft + 1, pIndex - inLeft + preLeft, map, inLeft, pIndex - 1);
            root.right = buildTree(preorder, pIndex - inLeft + preLeft + 1, preRight, map, pIndex + 1, inRight);
            return root;
        }
    }
}