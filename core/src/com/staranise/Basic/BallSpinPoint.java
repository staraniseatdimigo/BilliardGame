package com.staranise.Basic;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by 현성 on 2016-04-24.
 */
public class BallSpinPoint extends TexturedObject {

    public BallSpinPoint(){
        _sprite = new Sprite(new Texture("BallSpinPoint.png"));
        setVisible(false);
        setWidth(_sprite.getWidth());
        setHeight(_sprite.getHeight());
    }

    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x, y);
        _sprite.setPosition(x, y);
    }
}
