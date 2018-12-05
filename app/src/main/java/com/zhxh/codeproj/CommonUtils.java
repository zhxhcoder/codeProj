package com.zhxh.codeproj;

import java.util.ArrayList;
import java.util.List;

public class CommonUtils {
    public static void main(String[] args) {
        int[] candidates = {3, 5};
        combinationSum(candidates, 4);

    }


    static List<List<Integer>> res = new ArrayList<List<Integer>>();

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {

        helper(candidates, target, new ArrayList<Integer>(), 0);
        return res;
    }

    private static void helper(int[] candidates, int target, ArrayList<Integer> list, int index) {

        if (target == 0) {
            res.add(new ArrayList<>(list));
        }

        for (int i = index; i < candidates.length; i++) {

            System.out.println("i " + i);

            if (candidates[i] <= target) {

                System.out.println("candidates " + candidates[i]);

                list.add(candidates[i]);
                helper(candidates, target - candidates[i], list, i);
                list.remove(list.size() - 1);

                System.out.println("last");

            }
        }
    }
}