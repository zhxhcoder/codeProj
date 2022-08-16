package com.zhxh.codeproj.leetcode.day2;

import java.util.*;

/*
给定一个仅包含数字2-9的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。

给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。

示例 1：

输入：digits = "23"
输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
示例 2：

输入：digits = ""
输出：[]
示例 3：

输入：digits = "2"
输出：["a","b","c"]

提示：

0 <= digits.length <= 4
digits[i] 是范围 ['2', '9'] 的一个数字。

 */
public class LeetCode17 {
    public static void main(String[] args) {
        System.out.println(new Solution().letterCombinations("234"));
        System.out.println(new Solution2().letterCombinations("234"));
        System.out.println(new Solution3().letterCombinations("234"));
    }

    /*
    回溯是一种通过穷举所有可能情况来找到所有解的算法。
    如果一个候选解最后被发现并不是可行解，回溯算法会舍弃它，并在前面的一些步骤做出一些修改，并重新尝试找到可行解。
     */

    static class Solution {
        Map<String, String> phone = new HashMap<String, String>() {{
            put("2", "abc");
            put("3", "def");
            put("4", "ghi");
            put("5", "jkl");
            put("6", "mno");
            put("7", "pqrs");
            put("8", "tuv");
            put("9", "wxyz");
        }};

        List<String> output = new ArrayList<String>();

        public void backtrack(String combination, String next_digits) {
            //如果没有更多数字要检查
            if (next_digits.length() == 0) {
                //组合完成
                output.add(combination);
            } else {//如果还有数字要检查
                //遍历映射下一个可用数字的所有字母
                String digit = next_digits.substring(0, 1);
                String letters = phone.get(digit);
                for (int i = 0; i < letters.length(); i++) {
                    String letter = phone.get(digit).substring(i, i + 1);
                    //将当前字母附加到组合并继续下一个数字
                    backtrack(combination + letter, next_digits.substring(1));
                }
            }
        }

        public List<String> letterCombinations(String digits) {
            if (digits.length() != 0)
                backtrack("", digits);
            return output;
        }
    }

    static class Solution2 {
        public List<String> letterCombinations(String digits) {
            List<String> res = new ArrayList<>();  //使用一个集合来存储所有的字母组合结果
            if (digits == null || digits.length() == 0) return res;

            //将号码字母对应关系存储进Map
            Map<Character, String[]> map = new HashMap<Character, String[]>() {{    //存储为数组更好操作
                put('2', new String[]{"a", "b", "c"});
                put('3', new String[]{"d", "e", "f"});
                put('4', new String[]{"g", "h", "i"});
                put('5', new String[]{"j", "k", "l"});
                put('6', new String[]{"m", "n", "o"});
                put('7', new String[]{"p", "q", "r", "s"});
                put('8', new String[]{"t", "u", "v"});
                put('9', new String[]{"w", "x", "y", "z"});
            }};

            //定义一个队列来存储所有的组合结果
            Queue<String> queue = new LinkedList<>();
            //遍历Digits，取到对应号码对应的字母数组
            for (int i = 0; i < digits.length(); i++) {
                queue_letterCombinations(queue, map.get(digits.charAt(i)));
            }
            //要求返回List
            res.addAll(queue);
            return res;
        }

        private Queue<String> queue_letterCombinations(Queue<String> queue, String[] letters) {
            //Queue<String> queue = new LinkedList<String>();
            //初始定义的queue一定是空的，所以这时候把第一个号码的字母放入队列
            if (queue.size() == 0) {
                queue.addAll(Arrays.asList(letters));
            } else {
                //对于后面到来字母，把queue出队列然后拼接以后进入队列
                int queueLength = queue.size(); //记录本次需要进行出列组合的元素数量
                for (int i = 0; i < queueLength; i++) {
                    String s = queue.poll();    //队列头元素出队列
                    for (String letter : letters) {
                        queue.add(s + letter);  //将出来的队列元素和电话号码对应的字母依次进行拼接并添加进队列
                    }
                }
            }
            return queue;
        }
    }

    /*
    我们可以利用队列的先进先出特点，再配合循环完成题目要求。
    比如，我们先将对应的字符 a,b,c依次放入队列中
    之后再从队列中拿出第一个元素a，跟3对应的字符d,e,f挨个拼接

     */
    static class Solution3 {
        public List<String> letterCombinations(String digits) {
            if (digits == null || digits.length() == 0) {
                return new ArrayList<String>();
            }
            //一个映射表，第二个位置是"abc“,第三个位置是"def"。。。
            //这里也可以用map，用数组可以更节省点内存
            //前两个只是占位
            String[] letter_map = {
                    "#", "*", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"
            };
            List<String> res = new ArrayList<>();
            //先往队列中加入一个空字符
            res.add("");
            for (int i = 0; i < digits.length(); i++) {
                //由当前遍历到的字符，取字典表中查找对应的字符串
                String letters = letter_map[digits.charAt(i) - '0'];
                int size = res.size();
                //计算出队列长度后，将队列中的每个元素挨个拿出来
                for (int j = 0; j < size; j++) {
                    //每次都从队列中拿出第一个元素
                    String tmp = res.remove(0);
                    //然后跟"def"这样的字符串拼接，并再次放到队列中
                    for (int k = 0; k < letters.length(); k++) {
                        res.add(tmp + letters.charAt(k));
                    }
                }
            }
            return res;
        }
    }
}
