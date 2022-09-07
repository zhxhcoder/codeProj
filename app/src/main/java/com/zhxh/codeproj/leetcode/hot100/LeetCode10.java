package com.zhxh.codeproj.leetcode.hot100;

/*
10. 正则表达式匹配

给你一个字符串s和一个字符规律p，请你来实现一个支持 '.'和'*'的正则表达式匹配。

'.' 匹配任意单个字符
'*' 匹配零个或多个前面的那一个元素
所谓匹配，是要涵盖整个字符串s的，而不是部分字符串。


示例 1：

输入：s = "aa", p = "a"
输出：false
解释："a" 无法匹配 "aa" 整个字符串。
示例 2:

输入：s = "aa", p = "a*"
输出：true
解释：因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
示例3：

输入：s = "ab", p = ".*"
输出：true
解释：".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。


提示：

1 <= s.length<= 20
1 <= p.length<= 30
s只包含从a-z的小写字母。
p只包含从a-z的小写字母，以及字符.和*。
保证每次出现字符* 时，前面都匹配到有效的字符

 */
public class LeetCode10 {
    public static void main(String[] args) {

        //String s = "aa*abaaaccd";
        //String p = "a***abaaa*.*";

        String s = "aa*abaaaccd";
        String p = "a***abaaa*.*";

        System.out.println(new Solution().isMatch(s, p));
        System.out.println(new Solution2().isMatch(s, p));
        System.out.println(new Solution3().isMatch(s, p));
        System.out.println(new Solution4().isMatch(s, p));
        System.out.println(new Solution5().isMatch(s, p));
    }

    static class Solution {

        public boolean isMatch(String s, String p) {
            int m = s.length();
            int n = p.length();

            boolean[][] f = new boolean[m + 1][n + 1];
            f[0][0] = true;
            for (int i = 0; i <= m; ++i) {
                for (int j = 1; j <= n; ++j) {
                    if (p.charAt(j - 1) == '*') {
                        f[i][j] = f[i][j - 2];
                        if (matches(s, p, i, j - 1)) {
                            f[i][j] = f[i][j] || f[i - 1][j];
                        }
                    } else {
                        if (matches(s, p, i, j)) {
                            f[i][j] = f[i - 1][j - 1];
                        }
                    }
                }
            }
            return f[m][n];
        }

        public boolean matches(String s, String p, int i, int j) {
            if (i == 0) {
                return false;
            }
            if (p.charAt(j - 1) == '.') {
                return true;
            }
            return s.charAt(i - 1) == p.charAt(j - 1);
        }
    }


    /*
    动态规划 不支持多个连续*的情况

    String s = "aa*abaaaccd";
    String p = "a***abaaa*.*";
    报错为 false
     */
    static class Solution2 {
        public boolean isMatch(String s, String p) {
            //此处为length+1的目的是放入一个额外的为空的字符情况，以便于判断当*时，添加的字符情况
            boolean table[][] = new boolean[s.length() + 1][p.length() + 1];
            table[0][0] = true;
            for (int i = 1; i < table[0].length; i++) {
                char ch = p.charAt(i - 1);
                if (i > 1) {
                    //若ch=='*'，则看同一行内回退两格的boolean值：
                    //（因为相当于若回退两格为true，即在选择添加该字符时可以选择数量为0（因为是'*'））
                    if (ch == '*') {
                        table[0][i] = table[0][i - 2];
                    }
                    //因为第0行的s字符为空，所以和除了*以外的都不匹配，直接false
                    else table[0][i] = false;
                } else {
                    //如果填第一个空格，且字符为*，则赋值为true（因为*的匹配可以选择0个字符）
                    if (ch == '*') table[0][i] = true;
                }
            }
            //接下来按照行优先的顺序填充表格
            for (int j = 1; j < table.length; j++) {
                char ch01 = s.charAt(j - 1);
                for (int h = 1; h < table[j].length; h++) {
                    char ch02 = p.charAt(h - 1);
                    //如果行和列对应的字符相等 || 列的字符为'.'，则当前位置的值由左斜上方的值确定
                    if (ch02 == ch01 || ch02 == '.') {
                        table[j][h] = table[j - 1][h - 1];
                    }
                    //如果列的字符为'*'
                    else if (ch02 == '*') {
                        if (h > 1) {
                            //按照规则，先在同一行回退两格，若该值为true则直接赋值true
                            if (table[j][h - 2] == true) table[j][h] = true;
                            else {
                                //若不为true，则比较当前行的字符（s里的）与当前列-1的字符（p里的）是否相等
                                char prev = p.charAt(h - 2);
                                //若相等 || 当前列-1的字符（p里的）为'.'，将当前位置上方的值赋给当前位置
                                if (ch01 == prev || prev == '.') table[j][h] = table[j - 1][h];
                            }
                        }

                    }
                }
            }
            //返回table表的最右下方元素，即为整个字符串的匹配结果
            return table[s.length()][p.length()];
        }
    }

    /*
    dp五部曲:
    设主串s的长度为m,设模式串p的长度为n;其中s只有小写字母,p有字母.和*
    1.状态定义:dp[i][j]为考虑s[0,i-1]与p[0,j-1]是否能匹配上,能匹配上就为true
    2.状态转移:若要求dp[i][j]就必须考虑到s[i-1]与p[j-1]
        2.1 当p[j-1]不是'*'时
            2.1.1 若s[i-1]==p[j-1]时,即p[j-1]为'a'-'z'且与s[i-1]相等,看dp[i-1][j-1]
            2.1.2 p[j-1] == '.'时,直接将'.'变成s[i-1],看dp[i-1][j-1]
            注意:这里的'.'是匹配一个字符,而不是一连串,如"a.b"->"axb"
        2.2 当p[j-1]是'*'时,主要看p[j-2]做判断
            2.2.1 p[j-2]为'a'-'z'并且p[j-2]==s[i-1],那么此时s继续往前看,p暂时不动
                即:看dp[i-1][j]
            2.2.2 p[j-2]为'.',那么".*"可以变为"....."可以匹配s[i-1]前面的任何字符(万能串)
                因此,直接看dp[i-1][j](或者直接返回true)
            2.2.3 剩下的就是p[j-2]为'a'-'z'且p[j-2]!=s[i-1],那么此时p的"x*"作废,看dp[i][j-2]
        这里:2.1.1与2.2.2可以看成一种情形:即s与p均前移一位
            2.2.1与2.2.2可以看成一种情形:即p不动s前移一位
    3.初始化:
        3.1 空的s
            3.1.1 空的p,空的s可以匹配空的p,因此dp[0][0]=true
            3.1.2 非空的p,空的s可能可以匹配非空的p,例如"a*",因此需要经过转移计算
        3.2 空的p
            3.2.1 空的s,同3.1.1
            3.2.2 非空的s,空的p不可能匹配非空的s,因此dp[i][0]=false,i∈[1,m]
        3.3 非空的s与非空的p:需要经过转移计算
        其中:3.1.1与3.2.2可以合并为dp[i][0]=i==0
    4.遍历顺序:显然是正序遍历
    5.返回形式:返回dp[m][n]就是考虑s[0,m-1]与p[0,n-1]是否能匹配上
    */
    static class Solution3 {
        public boolean isMatch(String s, String p) {
            int m = s.length(), n = p.length();
            boolean[][] dp = new boolean[m + 1][n + 1];
            // 初始化dp[i][0]
            // for(int i = 0; i <= m; i++) {
            //     dp[i][0] = i == 0;
            // }
            dp[0][0] = true;
            // i从0开始
            for (int i = 0; i <= m; i++) {
                // 注意j从1开始
                for (int j = 1; j <= n; j++) {
                    if (p.charAt(j - 1) != '*') {
                        // 1.当p[j-1]不是'*'时(注意j已经从1开始了)
                        // 这里要注意运算优先级问题(加括号)
                        if (i >= 1 && (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.')) {
                            // s与p均前移一位
                            dp[i][j] = dp[i - 1][j - 1];
                        }
                    } else {
                        // 2.当p[j-1]是'*'时,主要看p[j-2]做判断
                        if (j >= 2 && i >= 1 &&
                                (p.charAt(j - 2) == s.charAt(i - 1) || p.charAt(j - 2) == '.')) {
                            // 看"x*":p不动s前移一位
                            dp[i][j] = dp[i - 1][j];
                        }
                        // 不看"x*":
                        // 剩下的为p[j-2]为'a'-'z'且p[j-2]!=s[i-1],那么此时p的"x*"作废,看dp[i][j-2]
                        if (j >= 2) {
                            dp[i][j] |= dp[i][j - 2];
                        }
                        // 这里的|=表示只要满足了其中一个条件就可以使得dp[i][j]==true
                        // 通俗一点的解释就是:当p[j-1]=='*'时,
                        // 若p[j-2]==s[i-1]||p[j-2]=='.',则dp[i][j]可以继承dp[i-1][j]:转移路径1
                        // 若p[j-2]!=s[i-1],则dp[i][j]可以继承dp[i][j-2]:转移路径2
                        // 满足任意一条转移路径就可以使得dp[i][j]=true
                    }
                }
            }
            // 所求即为考虑s[0,m-1]与p[0,n-1]是否能匹配上
            return dp[m][n];
        }
    }

    /*
    递归方法
    */
    static class Solution4 {
        public boolean isMatch(String s, String p) {
            if (p.length() == 0)
                return s.length() == 0;

            if (p.length() > 1 && p.charAt(1) == '*') {
                if (isMatch(s, p.substring(2)))//匹配0次
                    return true;
                return matchFirst(s, p) && isMatch(s.substring(1), p);
            } else {
                return matchFirst(s, p) && isMatch(s.substring(1), p.substring(1));
            }
        }

        private boolean matchFirst(String s, String p) {
            return s.length() > 0 && p.length() > 0 && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.');
        }
    }

    /*
     动态规划
     */
    static class Solution5 {
        public boolean isMatch(String text, String pattern) {
            boolean[][] dp = new boolean[text.length() + 1][pattern.length() + 1];
            dp[text.length()][pattern.length()] = true;

            for (int i = text.length(); i >= 0; i--) {
                for (int j = pattern.length() - 1; j >= 0; j--) {
                    boolean first_match = (i < text.length() &&
                            (pattern.charAt(j) == text.charAt(i) ||
                                    pattern.charAt(j) == '.'));
                    if (j + 1 < pattern.length() && pattern.charAt(j + 1) == '*') {
                        dp[i][j] = dp[i][j + 2] || first_match && dp[i + 1][j];
                    } else {
                        dp[i][j] = first_match && dp[i + 1][j + 1];
                    }
                }
            }
            return dp[0][0];
        }
    }
}
