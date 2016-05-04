package com.staranise.Basic;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.*;
import com.staranise.thing.Vec2;

/**
 * Created by 현성 on 2016-04-23.
 */
public class Cue extends Actor {

    private float _fDelta;
    private Sprite _sprite;
    private float _fAngle;
    private Vec2 _targetBallPos = null;

    public Cue(){
        _sprite = new Sprite(new Texture("cue.png"));
        setVisible(false);
        setWidth(_sprite.getWidth());
        setHeight(_sprite.getHeight());
        setZIndex(6);
    }

    public void setTargetBallPos(Vec2 targetBallPos){
        _targetBallPos = targetBallPos;
    }

    public Vec2 getTargetBallPos(){
        return _targetBallPos;
    }

    public void setAngle(float fAngle){
        _fAngle = fAngle;
    }

    public void positioning(){

        //기준 : 큐의 회전축
        _sprite.setOrigin(7.f, 252.f + _fDelta);
        float critPosX = _targetBallPos.x - (7.f);         //기준 x좌표 : 공의 x좌표/7.f : 이미지 위치 보정
        float critPosY = _targetBallPos.y - (252.f + _fDelta);       //기준 y좌표 : 공의 y좌표/252.f : 이미지 위치 보정
        float finalPosX = critPosX;
        float finalPosY = critPosY;

        _sprite.setPosition(finalPosX, finalPosY);
        _sprite.setRotation((float)Math.toDegrees(_fAngle));
    }

    public void addScrolling(float delta){
        _fDelta += delta;
    }

    @Override
    public void draw(Batch batch, float parentAlpha){
        positioning();
        _sprite.draw(batch);
    }
}
