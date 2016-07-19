package com.staranise.basic;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.staranise.thing.Vec2;

/**
 * Created by 현성 on 2016-05-04..
 */
public class BilliardBoard extends TexturedObject {

    private GNPBatchProcessor _GNPBatchProcessor;

    public BilliardBoard(GNPBatchProcessor GNPBatchProcessor){
        _sprite = new Sprite(new Texture("BilliardBoard.png"));
        _sprite.setCenter(512.f, 768.f - 334.5f);
        setZIndex(1);

        _GNPBatchProcessor = GNPBatchProcessor;

        initialize();
    }

    private void initialize(){
        _GNPBatchProcessor.AddObject(new Border(new Vec2(123.f, 433.5f), 10.f, 405.f)); //left
        _GNPBatchProcessor.AddObject(new Border(new Vec2(901.f, 433.5f), 10.f, 405.f)); //right
        _GNPBatchProcessor.AddObject(new Border(new Vec2(512.f, 631.f), 768.f, 10.f)); //top
        _GNPBatchProcessor.AddObject(new Border(new Vec2(512.f, 236.f), 768.f, 10.f)); //bottom
    }
}
