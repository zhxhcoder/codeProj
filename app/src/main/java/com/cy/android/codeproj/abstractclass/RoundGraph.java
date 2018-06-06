package com.cy.android.codeproj.abstractclass;

public class RoundGraph extends Graph {
    int radius = 1;

    public RoundGraph(int i) {
        radius = i;
        System.out.println("RoundGraph().radius=" + radius);
    }
    @Override
    void draw() {
        System.out.println("RoundGraph().draw() radius=" + radius);
    }
}
