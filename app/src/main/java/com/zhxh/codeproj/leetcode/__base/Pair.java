package com.zhxh.codeproj.leetcode.__base;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.Objects;

/**
 * Created by zhxh on 2020/7/6
 */
public class Pair<F, S> {
    public final F first;
    public final S second;

    public Pair(F first, S second) {
        this.first = first;
        this.second = second;
    }

    public F getKey() {
        return first;
    }

    public S getValue() {
        return second;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Pair)) {
            return false;
        }
        Pair<?, ?> p = (Pair<?, ?>) o;
        return Objects.equals(p.first, first) && Objects.equals(p.second, second);
    }

    @Override
    public int hashCode() {
        return (first == null ? 0 : first.hashCode()) ^ (second == null ? 0 : second.hashCode());
    }

    @Override
    public String toString() {
        return "Pair{" + String.valueOf(first) + " " + String.valueOf(second) + "}";
    }

    public static <A, B> Pair<A, B> create(A a, B b) {
        return new Pair<A, B>(a, b);
    }
}
