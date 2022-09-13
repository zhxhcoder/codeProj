package com.zhxh.codeproj.designpattern.templatemethod.worker;

import com.zhxh.codeproj.designpattern.templatemethod.Worker;
import com.zhxh.codeproj.Log;

/**
 * Created by zhxh on 2016/11/2.
 * 测试人员
 */

public class QAWorker extends Worker {

    public QAWorker(String name) {
        super(name);
    }

    @Override
    public void work() {
        Log.e("--work", "---" + name + ", 写测试用例 - 提交Bug - 复查Bug");
    }
}
