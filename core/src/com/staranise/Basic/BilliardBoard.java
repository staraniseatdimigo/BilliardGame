package com.staranise.Basic;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by 현성 on 2016-05-04..
 */
public class BilliardBoard extends TexturedObject {

    public BilliardBoard(){
        _sprite = new Sprite(new Texture("BilliardBoard.png"));
        _sprite.setCenter(320.f, 220.f);
        setZIndex(1);
    }
}
