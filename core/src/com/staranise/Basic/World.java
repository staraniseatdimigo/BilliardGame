package com.staranise.Basic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.staranise.thing.Universe;
import com.staranise.thing.Vec2;

/**
 * Created by 현성 on 2016-04-10..
 */
public class World {
    private Universe _universe;
    private Stage _stage;
    private boolean _bUniverseExist;

    public World(boolean bUniverseExist, boolean bInputAllowed){
        _bUniverseExist = bUniverseExist;
        if(bUniverseExist)
            _universe = new Universe(new Vec2(0,0));
        _stage = new Stage();
        if(bInputAllowed)
            Gdx.input.setInputProcessor(_stage);
    }

    public void AddObject(Actor actor){
        _stage.addActor(actor);
    }

    public void AddObject(QueObject object){

        _stage.addActor(object);
        if(_bUniverseExist)
            _universe.addThing(object.getEngine());
    }

    public void Render(float fDeltaTime){
        _universe.flow(fDeltaTime);
        _stage.act(fDeltaTime);
        _stage.draw();
    }

    public Universe getUniverse(){
        if(_bUniverseExist)
            return  _universe;
        System.out.printf("This world doesn't have universe.");
        return null;
    }

    public Stage getStage(){
        return _stage;
    }
}
