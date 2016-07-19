package com.staranise.basic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.staranise.billiard.HitChecker;
import com.staranise.thing.Thing;
import com.staranise.thing.Universe;
import com.staranise.thing.Vec2;

import java.util.List;

/**
 * Created by 현성 on 2016-04-10..
 */
public class GNPBatchProcessor {
    private Universe _universe;
    private Stage _stage;

    public GNPBatchProcessor(boolean bInputAllowed){
        _universe = new Universe(new Vec2(0,0));
        _stage = new Stage();
        if(bInputAllowed)
            Gdx.input.setInputProcessor(_stage);
    }

    public void AddObject(QueObject object){
        _stage.addActor(object);
        _universe.addThing(object.getEngine());
    }

    public void AddObject(Actor actor){
        _stage.addActor(actor);
    }

    public void AddObject(Thing object){
        _universe.addThing(object);
    }

    public void Render(float fDeltaTime) {
        _universe.flow(fDeltaTime);

        List<Thing> things = _universe.getThings();
        for (int i = 0; i < things.size(); i++) {
            if (things.get(i).getLinearSpeed().getLength() != 0) break;
            if (i == things.size() - 1) { // 모든 공이 멈춤
                if (HitChecker.getHitChecker() != null)
                    HitChecker.getHitChecker().notifyHitOvered();
            }
        }

        _stage.act(fDeltaTime);
        _stage.draw();
    }

    public Universe getUniverse(){
        return _universe;
    }

    public Stage getStage(){
        return _stage;
    }
}
