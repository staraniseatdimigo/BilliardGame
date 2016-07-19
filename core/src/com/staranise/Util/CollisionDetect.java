package com.staranise.Util;

import com.staranise.thing.Vec2;
import com.staranise.thing.shapes.Circle;
import com.staranise.thing.shapes.Rect;
import com.sun.istack.internal.Nullable;

/**
 * Created by 현성 on 2016-07-19.
 */
public class CollisionDetect {


    @Nullable
    public static Vec2 getSideNormCollidedWithCircle(Rect rect, Circle circle){
        float left = rect.getPosition().x - rect.getWidth() / 2.f;
        float right = rect.getPosition().x + rect.getWidth() / 2.f;
        float top = rect.getPosition().y + rect.getHeight() / 2.f;
        float bottom = rect.getPosition().y - rect.getHeight() / 2.f;

        float objPosX = circle.getPosition().x;
        float objPosY = circle.getPosition().y;
        float radius = circle.getRadius();

        if(left - radius < objPosX && objPosX < left + radius){
            return Constants.LEFT_SIDE_NORM;
        }
        else if(right - radius < objPosX && objPosX < right + radius){
            return Constants.RIGHT_SIDE_NORM;
        }
        else if(top - radius < objPosY && objPosY < top + radius){
            return Constants.TOP_SIDE_NORM;
        }
        else if(bottom - radius < objPosY && objPosY < bottom + radius){
            return Constants.BOTTOM_SIDE_NORM;
        }

        return null;
    }

}
