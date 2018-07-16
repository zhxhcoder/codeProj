package com.zhxh.codeproj.abstractclass;

public abstract class Graph {
    abstract void draw();

    public Graph() {
        System.out.println("Graph() before draw()");
        draw();
        System.out.println("Graph() after draw()");
    }

}
