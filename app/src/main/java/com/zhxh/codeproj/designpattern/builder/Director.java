package com.zhxh.codeproj.designpattern.builder;

/**
 * Created by zhxh on 2019/4/9
 */
public class Director {
    private Builder builder=new ConcreteProdut();
    public Product getAProduct(){
        builder.setPart();
        return builder.buildProduct();
    }
}
