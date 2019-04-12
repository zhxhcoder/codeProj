package com.zhxh.codeproj.algorithm;


import junit.framework.Assert;

import org.junit.Test;

/**
 * Created by zhxh on 2019/4/12
 */
public class ArraySingle {
    @Test
    public void test2() {
        Assert.assertEquals(0, singleNumber2(new int[]{}));
        Assert.assertEquals(3, singleNumber2(new int[]{1, 1, 3, 2, 2}));
        Assert.assertEquals(3, singleNumber2(new int[]{1, 3, 1, 2, 4, 2, 4}));
        Assert.assertEquals(3, singleNumber2(new int[]{1, 4, 2, 3, 2, 4, 1}));
        Assert.assertEquals(3, singleNumber2(new int[]{1, 1, 2, 2, 3, 4, 4}));
    }

    public int singleNumber2(int[] nums) {
        int res = 0;
        for (int i : nums) {
            res ^= i;
        }
        return res;
    }

}
