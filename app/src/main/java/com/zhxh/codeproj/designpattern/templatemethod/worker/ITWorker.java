package com.zhxh.codeproj.designpattern.templatemethod.worker;

import com.zhxh.codeproj.designpattern.templatemethod.Worker;
import com.zhxh.codeproj.Log;

/**
 * Created by zhxh on 2016/11/2.
 * 程序员
 */

public class ITWorker extends Worker {

    public ITWorker(String name) {
        super(name);
    }

    /**
     * 重写父类的此方法,使可以查看离开公司时间
     */
    @Override
    public boolean isNeedPrintDate() {
        return true;
    }

    @Override
    public void work() {
        Log.e("--work", "---" + name + ", 写程序 - 测bug - 修复bug");
    }
}
