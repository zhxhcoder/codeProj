package com.zhxh.codeproj.designpattern.builder;

/**
 * Created by zhxh on 2019/4/9
 */
public class ConcreteProdut extends Builder {
    private Product product=new Product();
    @Override
    public void setPart() {
        // todo something

    }

    @Override
    public Product buildProduct() {
        return product;
    }
}
