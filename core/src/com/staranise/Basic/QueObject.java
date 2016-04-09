package com.staranise.Basic;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by 현성 on 2016-04-09.
 */
public class QueObject {
    private Texture _texObjImg;
    private float _fX;
    private float _fY;

    public QueObject(String strImgPath, float fX, float fY){
        _texObjImg = new Texture(strImgPath);
        _fX = fX;
        _fY = fY;
        ObjectManager.getInstance().RegisterObject(this);
    }

    public void Render(SpriteBatch batch){
        batch.draw(_texObjImg, _fX, _fY);
    }
}
