package com.zhxh.codeproj.designpattern.mediator;

/*
中介者模式也叫做调停者模式，一个对象要和N多个对象交流，就像对象间的战争，很混乱。
这时，加入一个中心，所有的类都和中心交流，中心说怎么处理就怎么处理

例如：机场调度中心（中介者）查看其他飞机（同事类）情况，然后通知飞机降落。

MVC框架，大家都应该使用过Struts，MVC框架，其中的C（Controller）就是一个中介者，叫做前端控制器（Front Controller）,
它的作用就是把M（Model，业务逻辑）和V（View，视图）隔离开，协调M和V协同工作，把M运行的结果和V代表的视图融合成一个前端可以展示的页面，，减少
M和V的依赖关系。
 */
public class App {
    public static void main(String[] args) {
        User user1 = new User("aa");
        User user2 = new User("bb");
        CharRoom.showMessage(user1, "11");
        CharRoom.showMessage(user2, "22");
    }
}