package com.staranise.basic;

import com.staranise.thing.Vec2;
import com.staranise.thing.shapes.Rect;

/**
 * Created by 현성 on 2016-07-19.
 */
public class Border extends Rect {

    public Border(Vec2 pos, float width, float height){
        super(width, height);
        position = pos;
        type = ThingType.Static;
    }
}
