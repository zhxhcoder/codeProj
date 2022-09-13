package com.zhxh.codeproj.designpattern.observer.classs;

import com.zhxh.codeproj.designpattern.observer.interfaces.Observer;
import com.zhxh.codeproj.designpattern.observer.interfaces.Subject;
import com.zhxh.codeproj.Log;

/**
 * Created by zhxh on 2016/10/21.
 * 模拟第一个使用者
 */

public class ObserverUser1 implements Observer {

    public ObserverUser1(Subject subject) {
        subject.registerObserver(this);
    }

    @Override
    public void update(String msg) {
        Log.e("-----ObserverUser1 ", "得到 3D 号码:" + msg + ", 我要记下来。 ");
//        Toast.makeText(PatternApplication.getInstance(), "-----ObserverUser1 得到 3D 号码:" + msg, Toast.LENGTH_SHORT).show();
    }
}
