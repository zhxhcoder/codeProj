package com.zhxh.codeproj.designpattern.decorator2;

/**
 * Created by zhxh on 2019/4/13
 */
public abstract class Decorator extends Component {
    private Component component = null;

    public Decorator(Component component) {
        this.component = component;
    }

    @Override
    public void operate() {
        this.component.operate();
    }
}
