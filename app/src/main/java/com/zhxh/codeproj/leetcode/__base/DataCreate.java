package com.zhxh.codeproj.leetcode.__base;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by zhxh on 2020/6/18
 */
public class DataCreate {

    public static void main(String[] args) {
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
