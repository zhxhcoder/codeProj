package com.zhxh.codeproj.rxcourse;

import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by zhxh on 2018/7/16
 */
public class RxTest {

    static String TAG = "";
    static String result = "";

    public static void main(String[] args) {
        if (true) {
            return;
        }

        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.i(TAG, "onSubscribe: " + d);
                result += "onSubscribe: " + d + "\n";
            }

            @Override
            public void onNext(String string) {
                Log.i(TAG, "onNext: " + string);
                result += "onNext: " + string + "\n";
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG, "onError: " + e);
                result += "onError: " + e + "\n";
            }

            @Override
            public void onComplete() {
                Log.i(TAG, "onComplete: ");
                result += "onComplete: " + "\n";
            }
        };
        Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("Hello");
                emitter.onNext("Rxjava2");
                emitter.onNext("My name is zhxh");
                emitter.onNext("What's your name");
                //一旦调用onComplete,下面将不在接受事件
                emitter.onComplete();
            }
        });

        observable.subscribe(observer);

        System.out.println("**********************************************************************************************");
    }

}
