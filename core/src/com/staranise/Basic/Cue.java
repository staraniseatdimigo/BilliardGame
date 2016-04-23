package com.staranise.Basic;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.staranise.thing.Vec2;

/**
 * Created by 현성 on 2016-04-23.
 */
public class Cue extends Actor {

    private Sprite _sprite;

    public Cue(){
        _sprite = new Sprite(new Texture("cue.png"));
    }

    public void positioning(Vec2 targetBallPos, float angle){
        _sprite.setOrigin(7.f, 252.f);
        _sprite.setPosition(targetBallPos.x - 7.f, targetBallPos.y - 252.f);
        _sprite.setRotation(angle);
    }

    @Override
    public void draw(Batch batch, float parentAlpha){
        _sprite.draw(batch);
    }
}
