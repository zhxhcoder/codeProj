package com.zhxh.codeproj.kotlin.leetcode

/**
 * Created by zhxh on 2019/05/12
 *
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
 */
object LeetCode001 {
    
    fun twoSum1(nums: IntArray, target: Int): IntArray {
        for (i in nums.indices) {
            for (j in i + 1 until nums.size) {
                if (nums[j] == target - nums[i]) {
                    return intArrayOf(i, j)
                }
            }
        }
        throw IllegalArgumentException("No two sum solution")
    }
    
    fun twoSum2(nums: IntArray, target: Int): IntArray {
        val map = HashMap<Int, Int>()
        for (i in nums.indices) {
            map.put(nums[i], i)
        }
        for (i in nums.indices) {
            val complement = target - nums[i]
            if (map.containsKey(complement) && map.get(complement) != i) {
                return intArrayOf(i, map.get(complement)!!)
            }
        }
        throw IllegalArgumentException("No two sum solution")
    }
    
    /**
     * 复杂度分析：
    
    时间复杂度：O(n)O(n)， 我们只遍历了包含有 nn 个元素的列表一次。在表中进行的每次查找只花费 O(1)O(1) 的时间。
    
    空间复杂度：O(n)O(n)， 所需的额外空间取决于哈希表中存储的元素数量，该表最多需要存储 nn 个元素。
     */
    
    fun twoSum3(nums: IntArray, target: Int): IntArray {
        val map = HashMap<Int, Int>()
        for (i in nums.indices) {
            val complement = target - nums[i]
            if (map.containsKey(complement)) {
                return intArrayOf(map.get(complement)!!, i)
            }
            map.put(nums[i], i)
        }
        throw IllegalArgumentException("No two sum solution")
    }
    
    @JvmStatic
    fun main(args: Array<String>) {
        
        val arrays1 = intArrayOf(1, 2, 3, 4, 5)
        val arrays2 = intArrayOf(2, 3, 4, 5)
        
        val target = 5
        
        val res1 = twoSum1(arrays1, target)
        val res2 = twoSum2(arrays2, target)
        val res3 = twoSum3(arrays2, target)
        println(res1[0].toString() + "," + res1[1])
        println(res2[0].toString() + "," + res2[1])
        println(res3[0].toString() + "," + res2[1])
        
    }
}
