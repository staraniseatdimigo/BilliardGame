package com.staranise.thing.shapes;

import com.staranise.thing.Thing;
import com.staranise.thing.Vec2;

/**
 * Created by 현성 on 2016-07-19.
 */
public class Circle extends Thing{
    private float radius = -1; //mean not circle
    public float getRadius() { return radius; }

    //make circle Shape
    public Circle(float radius) {
        assert(radius > 0);
        this.radius = radius;
    }
}
