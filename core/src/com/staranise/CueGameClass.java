package com.staranise;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.staranise.Basic.BilliardBall;
import com.staranise.Basic.QueObject;
import com.staranise.Basic.World;
import com.staranise.thing.Shape;
import com.staranise.thing.Universe;
import com.staranise.thing.Vec2;

public class CueGameClass implements Screen {
    private BilliardBall _ball1;
    private BilliardBall _ball2;

	private World world1;

	@Override
	public void show () {
		world1 = new World(true);

        _ball1 = new BilliardBall(1, new Vec2(50.f, 50.f));
        _ball2 = new BilliardBall(2, new Vec2(50.f, 300.f));

        _ball1.getEngine().setLinearSpeed(new Vec2(0.f, 150.f));

		world1.AddObject(_ball1);
		world1.AddObject(_ball2);
	}

	@Override
	public void render (float delta) {
		Gdx.gl.glClearColor(0.75f, 0.75f, 0.75f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		world1.Render();
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void hide() {

	}

	@Override
	public void dispose() {

	}
}
