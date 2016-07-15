package com.staranise.Basic;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by 현성 on 2016-05-04..
 */
public class BilliardBoard extends TexturedObject {

    public BilliardBoard(){
        _sprite = new Sprite(new Texture("BilliardBoard.png"));
        _sprite.setCenter(512.f, 384.f);
        setZIndex(1);
    }
}
