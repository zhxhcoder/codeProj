package com.zhxh.codeproj.designpattern.builder;

/**
 * Created by zhxh on 2020-01-31.
 * 具体建造者
 */
public class ConcreteBuilder extends Builder {

    private Product product = new Product();

    @Override
    public void setPart(String name, String type) {
        product.setName(name);
        product.setType(type);
    }

    @Override
    public Product getProduct() {
        return product;
    }
}
