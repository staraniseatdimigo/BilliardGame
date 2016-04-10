package com.staranise.Basic;

import com.staranise.thing.Shape;
import com.staranise.thing.Vec2;

/**
 * Created by 현성 on 2016-04-10.
 */
public class BilliardBall extends QueObject {

    public BilliardBall(int num, Vec2 vecPos){
        super("Ball" + num + ".png", 2.f, vecPos);
        _physEng.setShape(new Shape(16.f));
    }

}
