package com.staranise.basic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

/**
 * Created by 현성 on 2016-04-23..
 * ball spin decider
 */
public class BallSpinDecider extends TexturedObject {
    private float _fSpinX = 0.f;
    private float _fSpinY = 0.f;
    private static float _rootX = 512.f - 128.f / 2.f;

    private boolean _fDeciding = false;

    private BallSpinPoint _pointer = new BallSpinPoint();

    //private static final InputListener listener;

    public void setSpinPos(float spinX, float spinY){
        _fSpinX = spinX;
        _fSpinY = spinY;
    }

    public BallSpinDecider(){
        _sprite = new Sprite(new Texture("BallSpinDecider.png"));
        _sprite.setCenter(512.f, 64.f);
        _pointer.setPosition(512.f - 26.f, 64.f - 26.f);

        setWidth(_sprite.getWidth());
        setHeight(_sprite.getHeight());
        setBounds(512.f -_sprite.getWidth() / 2.f, 0.f, _sprite.getWidth(), _sprite.getHeight());

        addListener(new InputListener(){

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                _pointer.setPosition(_rootX + x-26.f, y-26.f);
                _fDeciding = true;
                return true;
            }

            @Override
            public void touchDragged(InputEvent event, float x, float y, int pointer) {
                if(0.f <= x && x <= 128.f && 0.f <= y && y <= 128.f)
                    _pointer.setPosition(_rootX + x-26.f, y-26.f);
                super.touchDragged(event, x, y, pointer);
            }

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                super.enter(event, x, y, pointer, fromActor);
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                _fSpinX = x-64.f;
                _fSpinY = y-64.f;
                _fDeciding = false;
                super.touchUp(event, x, y, pointer, button);
            }
        });
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        _pointer.draw(batch, parentAlpha);
    }

    public boolean isDeciding(){
        return _fDeciding;
    }

    public float getSpinX(){
        return _fSpinX;
    }

    public float getSpinY(){
        return _fSpinY;
    }

}
