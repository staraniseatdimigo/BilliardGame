package com.staranise.Basic;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.staranise.thing.Thing;
import com.staranise.thing.Vec2;

/**
 * Created by 현성 on 2016-04-09..
 */
public class QueObject extends Actor{
    protected Thing _physEng;
    protected Sprite _sprite;
    protected World _world;

    public QueObject(String strImgPath, float fMass, Vec2 vecPosition, World world){
        _physEng = new Thing(fMass, vecPosition);
        _sprite = new Sprite(new Texture(strImgPath));
        setWidth(_sprite.getWidth());
        setHeight(_sprite.getHeight());
        _world = world;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        Vec2 pos = _physEng.getPosition();
        float trX = pos.x - _sprite.getWidth() / 2.f;
        float trY = pos.y - _sprite.getHeight() / 2.f;
        _sprite.setCenter(pos.x, pos.y);
        _sprite.draw(batch);
        setPosition(trX, trY);
    }

    public Thing getEngine(){
        return _physEng;
    }
    public World getWorld() { return _world; }


}
