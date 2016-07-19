package com.staranise.basic;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by 현성 on 2016-04-24..
 */
public class BallSpinPoint extends TexturedObject {

    public BallSpinPoint(){
        _sprite = new Sprite(new Texture("BallSpinPoint.png"));
        setVisible(false);
        setWidth(_sprite.getWidth());
        setHeight(_sprite.getHeight());
    }

}
