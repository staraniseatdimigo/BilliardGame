package com.staranise.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.staranise.basic.*;
import com.staranise.Util.DebugLine;
import com.staranise.thing.Vec2;

//
public class GameMain implements Screen {

	private World world1;
	private Cue _cue;

	private int _gameMode;

	public static GameMain _temp = null;

	private OrthographicCamera camera;

	public GameMain(int gameMode){
		_temp = this;
		_gameMode = gameMode;
	}

	private void fourBallModeInit(){
		BilliardBall ball1 = new BilliardBall(1, new Vec2(512.f, 384.f), world1, true);
		BilliardBall ball2 = new BilliardBall(2, new Vec2(512.f, 450.f), world1, true);
		BilliardBall ball3 = new BilliardBall(3, new Vec2(600.f, 384.f), world1, true);
		BilliardBall ball4 = new BilliardBall(3, new Vec2(400.f, 450.f), world1, true);

		world1.AddObject(ball1);
		world1.AddObject(ball2);
		world1.AddObject(ball3);
		world1.AddObject(ball4);
	}

	private void threeBallModeInit(){
		BilliardBall ball1 = new BilliardBall(1, new Vec2(512.f, 384.f), world1, true);
		BilliardBall ball2 = new BilliardBall(2, new Vec2(512.f, 450.f), world1, true);
		BilliardBall ball3 = new BilliardBall(3, new Vec2(600.f, 384.f), world1, true);

		world1.AddObject(ball1);
		world1.AddObject(ball2);
		world1.AddObject(ball3);
	}

	@Override
	public void show () {
		camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.position.set(Gdx.graphics.getWidth() / 2.f, Gdx.graphics.getHeight() / 2.f, 0);
		camera.update();

		GameManager.getInstance().cam = camera;

		world1 = new World(true, true);
		world1.getUniverse().setBorder(new Vec2(512-768/2, 384-384/2), new Vec2(768, 384), false);

		BilliardBoard board = new BilliardBoard();
		BallSpinDecider decider = new BallSpinDecider();

		GameManager.getInstance().setSpinDecider(decider);

		_cue = new Cue();
		GameManager.getInstance().setCue(_cue);

		world1.AddObject(board);
		world1.AddObject(decider);

		if(_gameMode == 4){
			fourBallModeInit();
		}
		else if(_gameMode == 3){
			threeBallModeInit();
		}
		world1.AddObject(_cue);
	}

	@Override
	public void render (float delta) {
		Vec2 targetBallPos = GameManager.getInstance().getCue().getTargetBallPos();
		Vec2 shotDirection = GameManager.getInstance().getCue().getDirection();
		camera.update();
		_cue.realTimeCueEvent();

		Gdx.gl.glClearColor(0.f, 0.f, 0.f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		world1.Render(delta);
		if(targetBallPos != null && shotDirection != null)
			DebugLine.RenderLine(targetBallPos, Vec2.add(targetBallPos, shotDirection.norm().multi(-10000.f)));
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
