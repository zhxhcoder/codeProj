package com.zhxh.codeproj.javatest.javatest6;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhxh on 2019/4/4
 */
public class JavaTest6 {

    public static void main(String[] args) {
        NodeBean innerBean = new NodeBean();
        NodeBean bean = new NodeBean();

        innerBean.index = 1;
        innerBean.key = "内标1";
        innerBean.value = "内值1";

        bean.index = 11;
        bean.key = "外标1";
        bean.value = "外值1";
        bean.innerNode = innerBean;

        System.out.println("******************************************");

        System.out.println(bean.getBeanString());
        System.out.println(innerBean.getBeanString());

        System.out.println("******************************************");

        //innerBean = new NodeBean();

        System.out.println(bean.getBeanString());
        System.out.println(innerBean.getBeanString());

        System.out.println("******************************************");

        //bean = new NodeBean();

        Map<String, NodeBean> map = new HashMap<>();

        map.put("outer", bean);
        map.put("inner", innerBean);

        map.clear();

        System.out.println(bean.getBeanString());
        System.out.println(innerBean.getBeanString());
    }
}
