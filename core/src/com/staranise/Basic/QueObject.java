package com.staranise.basic;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.staranise.thing.Thing;
import com.staranise.thing.Vec2;

/**
 * Created by 현성 on 2016-04-09..
 */
public class QueObject extends TexturedObject{
    protected Thing _physEng;
    protected GNPBatchProcessor _GNPBatchProcessor;

    public QueObject(String strImgPath, GNPBatchProcessor GNPBatchProcessor){
        _sprite = new Sprite(new Texture(strImgPath));
        setWidth(_sprite.getWidth());
        setHeight(_sprite.getHeight());
        _GNPBatchProcessor = GNPBatchProcessor;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        Vec2 pos = _physEng.getPosition();
        Vec2 speed = _physEng.getLinearSpeed();
        float trX = pos.x - _sprite.getWidth() / 2.f;
        float trY = pos.y - _sprite.getHeight() / 2.f;
        super.draw(batch, parentAlpha);
        _sprite.setCenter(pos.x, pos.y);
        setPosition(trX, trY);
        //DebugLine.RenderLine(pos, speed.add(pos));
    }

    public Thing getEngine(){
        return _physEng;
    }
    public GNPBatchProcessor getWorld() { return _GNPBatchProcessor; }


}
