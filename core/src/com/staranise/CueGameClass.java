package com.staranise;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.staranise.thing.Shape;
import com.staranise.thing.Thing;
import com.staranise.thing.Universe;
import com.staranise.thing.Vec2;

public class CueGameClass extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;

	Universe u;
	Thing t1,t2;

	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");

		u = new Universe();
		t1 = new Thing(1, new Vec2(0,0), new Vec2(0,0), new Vec2(0,0));
		t2 = new Thing(1, new Vec2(200,0), new Vec2(0,0), new Vec2(0,0));

		t1.setShape(new Shape(1.0f));
		t2.setShape(new Shape(1.0f));

		u.addThing(t1);
		u.addThing(t2);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();

		u.flow(Gdx.graphics.getDeltaTime());

		System.out.println(t1.getPosition());
		System.out.println(t2.getPosition());
	}
}
