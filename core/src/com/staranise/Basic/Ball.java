package com.staranise.basic;

import com.staranise.thing.Vec2;
import com.staranise.thing.shapes.Circle;

/**
 * Created by 현성 on 2016-07-19.
 */
public class Ball extends Circle {

    public Ball(Vec2 position, Vec2 linearSpeed, float radius){
        super(radius);
        this.position = position;
        this.linearSpeed = linearSpeed;
    }

}
