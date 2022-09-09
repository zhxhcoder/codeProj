package com.zhxh.codeproj.designpattern.facade;

import com.zhxh.codeproj.designpattern.facade.device.*;
import com.zhxh.codeproj.designpattern.facade.theater.HomeTheaterFacade;

/*
 * 外观模式:
 * 定义：提供一个统一的接口，用来访问子系统中的一群接口，外观定义了一个高层的接口，让子系统更容易使用。
 * 其实就是为了方便客户的使用，把一群操作，封装成一个方法。
 * 举个例子：
 * 我比较喜欢看电影，于是买了投影仪、电脑、音响、设计了房间的灯光、买了爆米花机，然后我想看电影的时候，我需要：
 * 1、打开爆米花机
 * 2、制作爆米花
 * 3、将灯光调暗
 * 4、打开投影仪
 * 5、放下投影仪投影区
 * 6、打开电脑
 * 7、打开播放器
 * 8、将播放器音调设为环绕立体声
 * ...
 * 花了一笔钱，看电影还要这么多的步骤，太累了，而且看完还要一个一个关掉。
 * 所有，我们使用外观模式解决这些复杂的步骤，轻松享受电影：
 */
public class App {
    public static void main(String[] args) {
        HomeTheaterFacade homeTheaterFacade;

        // 外观模式
        Computer computer = new Computer();
        Light light = new Light();
        Player player = new Player();
        PopcornPopper popcornPopper = new PopcornPopper();
        Projector projector = new Projector();
        homeTheaterFacade = new HomeTheaterFacade(computer, light, player, popcornPopper, projector);


        // 一键观影
        if (homeTheaterFacade != null) {
            homeTheaterFacade.watchMovie();
        }
        // 一键关闭
        if (homeTheaterFacade != null) {
            homeTheaterFacade.stopMovie();
        }
    }
}
