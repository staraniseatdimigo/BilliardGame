package com.staranise.desktop.thing;

/**
 * Created by YuTack on 2016-04-08.
 */
public class Shape {
    float radius = -1; //mean not circle

    //make circle Shape
    public Shape(float radius) {
        assert(radius > 0);
        this.radius = radius;
    }

    public float getRadius() { return radius; }
}
