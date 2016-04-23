package com.staranise;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.staranise.Basic.BilliardBall;
import com.staranise.Basic.Cue;
import com.staranise.Basic.World;
import com.staranise.thing.Vec2;

public class GameMain implements Screen {
    private BilliardBall _ball1;
    private BilliardBall _ball2;
	private BilliardBall _ball3;
	private Cue _cue;

	private World world1;

	public GameMain(){
	}

	@Override
	public void show () {
		world1 = new World(true, true);

		_ball1 = new BilliardBall(1, new Vec2(66.f, 50.f), world1);
		_ball2 = new BilliardBall(2, new Vec2(50.f, 200.f), world1);
		_ball3 = new BilliardBall(3, new Vec2(82.f, 200.f), world1);
		_cue = new Cue();
		_cue.setVisible(false);

		world1.AddObject(_cue);
		world1.AddObject(_ball1);
		world1.AddObject(_ball2);
		world1.AddObject(_ball3);
	}

	@Override
	public void render (float delta) {
		Gdx.gl.glClearColor(0.75f, 0.75f, 0.75f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		world1.Render(delta);
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
