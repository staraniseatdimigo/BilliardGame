package com.staranise.Basic;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.staranise.thing.Vec2;

/**
 * Created by 현성 on 2016-04-23.
 */
public class Cue extends Actor {

    private Sprite _sprite;
    private float _fDeltaX = 0.f;
    private float _fDeltaY = 0.f;

    public Cue(){
        _sprite = new Sprite(new Texture("cue.png"));
        setVisible(false);
        setWidth(_sprite.getWidth());
        setHeight(_sprite.getHeight());
        addListener(new DragListener(){
            @Override
            public void touchDragged(InputEvent event, float x, float y, int pointer) {
                super.touchDragged(event, x, y, pointer);
                _fDeltaX += getDeltaX();
                _fDeltaY += getDeltaY();
            }

            @Override
            public boolean mouseMoved(InputEvent event, float x, float y) {
                return super.mouseMoved(event, x, y);
            }

            @Override
            public void drag(InputEvent event, float x, float y, int pointer) {
                super.drag(event, x, y, pointer);
            }
        });
    }

    public void positioning(Vec2 targetBallPos, float angle){
        /*float cosvalue = (float)Math.cos(angle);
        float sinvalue = (float)Math.sin(angle);*/
        float critPosX = targetBallPos.x - 7.f;
        float critPosY = targetBallPos.y - 252.f;
        float finalPosX = critPosX + _fDeltaX;
        float finalPosY = critPosY + _fDeltaY;
        _sprite.setOrigin(7.f, 252.f);
        _sprite.setPosition(finalPosX, finalPosY);
        _sprite.setRotation(angle);
        //_sprite.setBounds();
    }

    @Override
    public void draw(Batch batch, float parentAlpha){
        _sprite.draw(batch);
    }
}
