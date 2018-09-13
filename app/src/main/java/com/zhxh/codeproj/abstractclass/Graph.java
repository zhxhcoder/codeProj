package com.zhxh.codeproj.abstractclass;

public abstract class Graph {
    public Graph() {
        System.out.println("Graph() before draw()");
        draw();
        System.out.println("Graph() after draw()");
    }

    abstract void draw();

}
