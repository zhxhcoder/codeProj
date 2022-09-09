package com.zhxh.codeproj.designpattern.decorator2;

/**
 * Created by zhxh on 2019/4/13
 */
public class ConcreteComponent extends Component {
    @Override
    public void operate() {
        System.out.println("do something");
    }
}
