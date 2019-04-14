package com.zhxh.codeproj.designpattern.bridge;

/**
 * Created by zhxh on 2019/4/14
 */
public class RefinedAbstraction extends Abstraction {

    public RefinedAbstraction(Implementor imp) {
        super(imp);
    }

    @Override
    public void request() {
        //todo do something else
        System.out.println("refine something else");

        super.request();
        super.getImp().doAnything();
    }
}
