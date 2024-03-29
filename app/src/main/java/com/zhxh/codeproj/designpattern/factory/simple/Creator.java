package com.zhxh.codeproj.designpattern.factory.simple;

/**
 * Created by zhxh on 2019/4/7
 */
public class Creator {

    public static <T extends Product> T createProduct(Class<T> c) {

        Product product = null;
        try {
            product = (Product) Class.forName(c.getName()).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return (T) product;
    }
}
