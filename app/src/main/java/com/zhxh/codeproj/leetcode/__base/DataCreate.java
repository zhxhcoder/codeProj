package com.zhxh.codeproj.leetcode.__base;

import java.util.*;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by zhxh on 2020/6/18
 */
public class DataCreate {
    public static void main(String[] args) {
        /*
        Deque 是Queue的子接口，表示双端队列，即两端都能插入和删除的特殊队列
        所以，Deque可以使用Queue的全部方法，但是自己也扩展了很多方法，主要用于操作两个端口：
        特点就是Queue的两种方法都多了一个操作队尾的版本

        用Deque实现栈，Stack和Vector类一样太古老，基本弃用。
        void(push) //等价于 void addFirst(E e)
        E pop() //等价于  E removeFirst()

        如果有异常安全的需要那就自行使用 offer/poll (First/Last)方法

        ArrayDeque,只能在初始化的时候指定capacity，因此没有像ArrayList那样随时可以调整ensureCapacity方法，只能在构造器中指定初始的capacity，初始元素个数长度是16
        LinkedList 同时继承了List和Deque

         */
        //栈
        Stack<Integer> stack0 = new Stack<>();
        Deque<Integer> stack1 = new ArrayDeque<>();
        Deque<Integer> stack2 = new LinkedList<>();
        Deque<Integer> stack3 = new LinkedBlockingDeque<>();

        //队列
        Queue<Integer> queue0 = new LinkedList<>();
        Queue<Integer> queue1 = new PriorityQueue<>();
        Queue<Integer> queue2 = new PriorityBlockingQueue<>();
        Deque<Integer> queue4 = new LinkedList<>();

        /*
        int 占用4个字节，无论在32位和64位机器上都是32位（long占用8个字节）

        Integer.MAX_VALUE = 2147483647 = 2^31-1
        Integer.MIN_VALUE = -2147483648 = -2^31

        Integer.MAX_VALUE + 1 = -2147483648
        Integer.MIN_VALUE - 1 = 2147483647

        Integer.MIN_VALUE + Integer.MAX_VALUE = -1
         */
        System.out.println(Integer.MAX_VALUE + 1);//-2147483648
        System.out.println(Integer.MIN_VALUE - 1);//2147483647
        System.out.println(Integer.MAX_VALUE - Integer.MIN_VALUE);//-1
        System.out.println(Integer.MAX_VALUE + Integer.MIN_VALUE);//-1
        System.out.println(Math.abs(Integer.MIN_VALUE));//-2147483648


        //数组的初始化
        int[] array0 = new int[]{1, 2, 3, 4, 5};
        int[] array1 = {1, 2, 3, 4, 5};
        int[] array3 = new int[5]; // 初始值为0

        List<List<String>> listList0 = new LinkedList<List<String>>() {{
            add(new LinkedList<String>() {{
                add("a");
                add("b");
                add("c");
            }});
            add(new LinkedList<String>() {{
                add("a");
                add("b");
                add("c");
            }});
            add(new LinkedList<String>() {{
                add("a");
                add("b");
                add("c");
            }});
        }};

        List<List<String>> listList1 = new ArrayList<List<String>>() {{
            add(new LinkedList<String>() {{
                add("a");
                add("b");
                add("c");
            }});
            add(new LinkedList<String>() {{
                add("a");
                add("b");
                add("c");
            }});
            add(new LinkedList<String>() {{
                add("a");
                add("b");
                add("c");
            }});
        }};


        //列表的舒初始化
        List<String> stringList1 = new LinkedList<>();
        stringList1.add("a");
        stringList1.add("b");
        stringList1.add("c");

        List<String> stringList2 = new LinkedList<String>() {{
            add("a");
            add("b");
            add("c");
        }};

        List<String> stringList3 = Arrays.asList("a", "b", "c");//不支持增删

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            List<String> stringList4 = Stream.of("a", "b", "c").collect(Collectors.toList());
        }

        //List<String> stringList5 = Lists.newArrayList("a", "b", "c");

        Map<String, String> map1 = new HashMap<>();
        map1.put("a", "b");
        map1.put("c", "d");


        HashMap<String, String> map2 = new HashMap<String, String>() {{
            put("a", "b");
            put("b", "b");
        }};

        //Map.of("Hello", 1, "World", 2);//不可变集合

        //Map<String, Integer> map4 = ImmutableMap.of("a", 1, "b", 2, "c", 3);


        Set<String> set1 = new HashSet<>();
        set1.add("Monday");
        set1.add("Sunday");

        Set<String> set2 = new HashSet<String>() {{
            add("morning");
            add("afternoon");
        }};

        String s = "a,b,c,d,e,f";
        Set<String> set3 = new HashSet<>(Arrays.asList(s.split(",")));
    }
}
