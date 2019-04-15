package com.zhxh.codeproj.algorithm;

import java.util.ArrayList;
import java.util.Set;

/**
 * Created by zhxh on 2019/4/15
 */
public class WordInclude {
    public ArrayList<String> res = new ArrayList<>();

    public ArrayList<String> wordBreak(String s, Set<String> dict) {
        dfSearch(s, s.length(), dict, "");
        return res;
    }

    public void dfSearch(String s, int index, Set<String> dict, String temp) {
        if (index == 0) {
            if (temp.length() > 0) {
                res.add(temp.substring(0, temp.length() - 1));//如果写成res.add(temp)则末尾会多一个空格，小细节
            }
        }
        for (int i = index - 1; i >= 0; i--) {
            if (dict.contains(s.substring(i, index))) {
                dfSearch(s, i, dict, s.substring(i, index) + " " + temp);
            }
        }
    }
}
