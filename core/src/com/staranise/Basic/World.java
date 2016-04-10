package com.staranise.Basic;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.staranise.thing.Universe;

/**
 * Created by 현성 on 2016-04-10.
 */
public class World {
    private Universe _universe;
    private Stage _stage;
    private boolean _bUniverseExist;

    public World(boolean bUniverseExist){
        _bUniverseExist = bUniverseExist;
        if(bUniverseExist)
            _universe = new Universe();
        _stage = new Stage();
    }

    public void AddObject(QueObject object){
        _stage.addActor(object);
        if(_bUniverseExist)
            _universe.addThing(object.getEngine());
    }

    public void Render(){
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
