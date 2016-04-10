package com.staranise.Basic;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.staranise.thing.Thing;
import com.staranise.thing.Vec2;

/**
 * Created by 현성 on 2016-04-09.
 */
public class QueObject extends Actor{
    protected Thing _physEng;
    protected Texture _texture;

    public QueObject(String strImgPath, float fMass, Vec2 vecPosition){
        _physEng = new Thing(fMass, vecPosition);
        _texture = new Texture(strImgPath);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        Vec2 pos = _physEng.getPosition();
        batch.draw(_texture, pos.x, pos.y);
    }

    public Thing getEngine(){
        return _physEng;
    }
}
