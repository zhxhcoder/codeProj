package com.zhxh.codeproj.designpattern.strategy;

import com.zhxh.codeproj.designpattern.strategy.better.*;
import com.zhxh.codeproj.designpattern.strategy.better.RoleA;

/*
 * 策略模式（Strategy Pattern）：定义了算法族，分别封装起来，
 * 让它们之间可相互替换，此模式让算法的变化独立于使用算法的客户。
 */
public class App {
    public static void main(String[] args) {
        RoleA roleA = new RoleA("---A");
        roleA.setiDisplayBehavior(new DisplayYZ())
                .setiAttackBehavior(new AttackXL())
                .setiDefendBehavior(new DefendTMS())
                .setiRunBehavior(new RunJCTQ());
        roleA.display();// 样子
        roleA.attack();// 攻击
        roleA.run();// 逃跑
        roleA.defend();// 防御
    }
}
