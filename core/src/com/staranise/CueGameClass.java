package com.staranise;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.staranise.Basic.ObjectManager;
import com.staranise.Basic.QueObject;
import com.staranise.thing.Shape;
import com.staranise.thing.Universe;
import com.staranise.thing.Vec2;

public class CueGameClass extends ApplicationAdapter {
    QueObject ball1;
    QueObject ball2;

	Universe u;

	@Override
	public void create () {
        ball1 = new QueObject("./core/assets/Ball1.png", 2.f, new Vec2(50.f, 50.f));
        ball2 = new QueObject("./core/assets/Ball2.png", 2.f, new Vec2(50.f, 300.f));

		u = new Universe();

		ball1.setShape(new Shape(16.0f));
		ball2.setShape(new Shape(16.0f));

        ball1.setLinearSpeed(new Vec2(0.f, 150.f));

		u.addThing(ball1);
		u.addThing(ball2);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0.75f, 0.75f, 0.75f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        u.flow(Gdx.graphics.getDeltaTime());
        ObjectManager.getInstance().Render();
	}
}
