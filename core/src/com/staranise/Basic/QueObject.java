package com.staranise.Basic;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.staranise.thing.Thing;
import com.staranise.thing.Vec2;

/**
 * Created by 현성 on 2016-04-09.
 */
public class QueObject extends Thing{
    private Texture _texObjImg;

    public QueObject(String strImgPath, float fMass, Vec2 vecPosition){
        super(fMass, vecPosition);
        _texObjImg = new Texture(strImgPath);
        ObjectManager.getInstance().RegisterObject(this);
    }

    public void Render(SpriteBatch batch){
        batch.draw(_texObjImg, position.x, position.y);
    }
}
