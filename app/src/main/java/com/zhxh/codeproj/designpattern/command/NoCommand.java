package com.zhxh.codeproj.designpattern.command;

import com.zhxh.codeproj.Log;

/**
 * Created by zhxh on 2016/10/31.
 * 避免=null的情况
 */

public class NoCommand implements Command {

    @Override
    public void execute() {
        Log.d("哥们,这个没效!");
    }
}
