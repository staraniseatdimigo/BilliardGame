package com.staranise.basic;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.staranise.billiard.GameManager;

/**
 * Created by 현성 on 2016-05-17.
 * j
 */
public class TexturedObject extends Actor {
    protected Sprite _sprite;

    @Override
    public void draw(Batch batch, float parentAlpha){
        if(_sprite != null) {
            batch.setProjectionMatrix(GameManager.getInstance().cam.combined);
            _sprite.draw(batch);
        }
    }

    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x, y);
        _sprite.setPosition(x, y);
    }
}
