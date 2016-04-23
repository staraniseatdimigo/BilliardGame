package com.staranise.Basic;

import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.staranise.thing.Shape;
import com.staranise.thing.Thing;
import com.staranise.thing.Vec2;
import sun.security.ssl.Debug;

/**
 * Created by 현성 on 2016-04-10.
 */
public class BilliardBall extends QueObject {

    public BilliardBall(int num, Vec2 vecPos, World world){
        super("Ball" + num + ".png", 2.f, vecPos, world);
        _physEng.setShape(new Shape(15.f));

        setTouchable(Touchable.enabled);
        InputListener listener = new InputListener()
        {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if(Option.DEBUG_MODE) {
                    Debug.println("asd", "asd");
                    Stage stage = event.getTarget().getStage();
                    stage.addListener(new InputListener() {
                        @Override
                        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                            return true;
                        }

                        @Override
                        public void touchDragged(InputEvent event, float x, float y, int pointer) {
                            super.touchDragged(event, x, y, pointer);
                        }

                        @Override
                        public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                            Thing uni = BilliardBall.this.getEngine();
                            Vec2 pos = uni.getPosition();
                            Vec2 mouse = new Vec2(x, y);
                            uni.setLinearSpeed(mouse.minus(pos));
                            super.touchUp(event, x, y, pointer, button);
                            event.getStage().removeListener(this);
                        }
                    });
                }
                else {
                    final QueObject obj = (QueObject)event.getTarget();
                    World world = obj.getWorld();
                    final Cue cue = (Cue)(world.getStage().getActors().first());
                    cue.setVisible(true);
                    Stage stage = world.getStage();
                    stage.addListener(new InputListener() {
                        @Override
                        public boolean mouseMoved(InputEvent event, float x, float y) {
                            Vec2 origin = new Vec2(0.f, -1.f);
                            Vec2 direction = new Vec2(x, y);
                            Vec2 newdir = direction.minus(obj.getEngine().getPosition());
                            float cosvalue = origin.getCos(newdir);
                            float angle = (float)Math.acos(cosvalue) * (newdir.x >= 0 ?  1.f : -1.f) * 180.f / Constants.PI;
                            cue.positioning(obj.getEngine().getPosition(), angle);
                            return super.mouseMoved(event, x, y);
                        }
                    });
                }
                super.touchUp(event, x, y, pointer, button);
            }
        };
        addListener(listener);
    }

}
