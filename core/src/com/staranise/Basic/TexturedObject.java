package com.staranise.Basic;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by 현성 on 2016-05-17.
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
}
