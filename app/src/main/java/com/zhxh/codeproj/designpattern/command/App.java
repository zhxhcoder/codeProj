package com.zhxh.codeproj.designpattern.command;

/*
 * 命令模式:
 * 定义：将"请求"封装成对象，以便使用不同的请求、队列或者日志来参数化其他对象。命令模式也支持可撤销的操作。
 * 简化: 将请求封装成对象，将动作请求者和动作执行者解耦。
 * 命令模式的核心就是把命令封装成类，对于命令执行者不需要知道现在执行的具体是什么命令。
 * <p>
 * 假设现在有电视、电脑、电灯等家电，现在需要你做个遥控器控制所有家电的开关，
 * 要求做到每个按钮对应的功能供用户个性化，对于新买入家电要有非常强的扩展性。
 */
public class App {


    public static void main(String[] args) {
        ControlPanel controlPanel = new ControlPanel();
        setCommands(controlPanel);

        for (int i = 1; i <= 8; i++) {
            controlPanel.keyPressed(i);
        }

    }

    private static void setCommands(ControlPanel controlPanel) {
        /*
         * 三个家电
         */
        Door door = new Door();
        Light light = new Light();
        Computer computer = new Computer();

        /*
         * 一个控制器,假设是我们的APP界面
         */
        controlPanel.setCommands(0, new DoorOpenCommand(door));// 开门
        controlPanel.setCommands(3, new DoorCloseCommand(door));// 关门
        controlPanel.setCommands(1, new LightOnCommand(light));// 开灯
        controlPanel.setCommands(4, new LightOffCommand(light));// 关灯
        controlPanel.setCommands(2, new ComputerOnCommand(computer));// 开电脑
        controlPanel.setCommands(5, new ComputerOffCommand(computer));// 关电脑
        //controlPanel.keyPressed(8);//这个没有设置命令,但不会出问题,因为我们的NoCommand的功劳

        QuickCommand quickOpenCommand = new QuickCommand(new Command[]{new LightOnCommand(light), new ComputerOnCommand(computer), new DoorOpenCommand(door)});
        QuickCommand quickCloseCommand = new QuickCommand(new Command[]{new LightOffCommand(light), new ComputerOffCommand(computer), new DoorCloseCommand(door)});
        controlPanel.setCommands(6, quickOpenCommand);
        controlPanel.setCommands(7, quickCloseCommand);
    }
}
