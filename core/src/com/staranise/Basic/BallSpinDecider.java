package com.staranise.Basic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.sun.glass.ui.Screen;

/**
 * Created by 현성 on 2016-04-23.
 */
public class BallSpinDecider extends TexturedObject {
    private float _fSpinX = 0.f;
    private float _fSpinY = 0.f;
    private static float _rootX = Gdx.graphics.getWidth() - 128.f;

    //private static final InputListener listener;

    public void setSpinPos(float spinX, float spinY){
        _fSpinX = spinX;
        _fSpinY = spinY;
    }

    public BallSpinDecider(){
        _sprite = new Sprite(new Texture("BallSpinDecider.png"));
        _sprite.setCenter(Gdx.graphics.getWidth()-64.f, 64.f);

        setVisible(false);
        setWidth(_sprite.getWidth());
        setHeight(_sprite.getHeight());
        setBounds(Gdx.graphics.getWidth()-_sprite.getWidth(), 0.f, _sprite.getWidth(), _sprite.getHeight());

        addListener(/*listener*/new InputListener(){

            private Actor _point;
            private boolean _touched = false;

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                _point = getStage().getActors().get(3);
                _point.setPosition(Gdx.graphics.getWidth()-90.f, 40.f);
                _point.setVisible(true);
                _point.setPosition(_rootX + x-26.f, y-26.f);
                return true;
            }

            @Override
            public void touchDragged(InputEvent event, float x, float y, int pointer) {
                if((0.f <= x &&
                        _sprite.getHeight() >= y))
                    _point.setPosition(_rootX + x-26.f, y-26.f);
                super.touchDragged(event, x, y, pointer);
            }

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                super.enter(event, x, y, pointer, fromActor);
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                //BallSpinDecider target = (BallSpinDecider)event.getTarget();
                //target.setSpinPos(x-64.f, y-64.f); // 박유택 : 왜 이렇게 한거?
                _fSpinX = x-64.f;
                _fSpinY = y-64.f;
                super.touchUp(event, x, y, pointer, button);
            }
        });
    }

    public float getSpinX(){
        return _fSpinX;
    }

    public float getSpinY(){
        return _fSpinY;
    }

    @Override
    public void setVisible(boolean visible) {
        super.setVisible(visible);
        if(visible){
            if(_fSpinX != -1.f){
                getStage().getActors().get(3).setVisible(true);
            }
        }
    }
}
