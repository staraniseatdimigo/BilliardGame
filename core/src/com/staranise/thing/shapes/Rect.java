package com.staranise.thing.shapes;

import com.staranise.thing.Thing;
import com.staranise.thing.Vec2;

/**
 * Created by YuTack on 2016-04-08.
 *
 * container of two Vec2
 */
public class Rect extends Thing {
    protected float width;
    protected float height;

    public Rect(float width, float height){
        this.width = width;
        this.height = height;
    }

    public float getWidth(){
        return width;
    }

    public float getHeight(){
        return height;
    }
}
