package com.zhxh.codeproj.designpattern.iterator;

import android.util.Log;

public class Client {
    public static void main(String[] args) {
        //3. 使用 NameRepository 来获取迭代器，并打印名字。
        NameRepository nameRepository = new NameRepository();
        for (Iterator iterator = nameRepository.getIterator(); iterator.hasNext(); ) {
            String name = (String) iterator.next();
            Log.e("---", name);
            /*
             * /---: John
             * /---: jingbin
             * /---: youlookwhat
             * /---: lookthis
             */
        }
    }
}
