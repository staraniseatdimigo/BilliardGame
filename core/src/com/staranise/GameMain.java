package com.staranise;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.staranise.Basic.*;
import com.staranise.thing.Vec2;

//
public class GameMain implements Screen {

	private World world1;
	private Cue _cue;

	private Vec2 _oldDir = null;

	public static GameMain _temp = null;

	public GameMain(){
		_temp = this;
	}

	public static int _gameStep = 0;

	@Override
	public void show () {

		BilliardBoard _board;
		BilliardBall _ball1;
		BilliardBall _ball2;
		BilliardBall _ball3;
		final BallSpinDecider _decider;
		final BallSpinPoint _point;

		world1 = new World(true, true);

		_board = new BilliardBoard();
		_ball1 = new BilliardBall(1, new Vec2(200.f, 200.f), world1);
		_ball2 = new BilliardBall(2, new Vec2(300.f, 300.f), world1);
		_ball3 = new BilliardBall(3, new Vec2(100.f, 100.f), world1);

		_decider = new BallSpinDecider();
		_point = new BallSpinPoint();

		_cue = new Cue();

		_ball1.getEngine().setLinearSpeed(new Vec2(0.f, 0.f));
		_ball2.getEngine().setLinearSpeed(new Vec2(0.f, 0.f));

		GameResource.getInstance().getDrawable("new_button");
		Button spinBtn;
		Button.ButtonStyle spinBtnStyle = new Button.ButtonStyle();

		spinBtnStyle.up = GameResource.getInstance().getDrawable("new_button");
		spinBtnStyle.down = GameResource.getInstance().getDrawable("new_button_pressed");

		spinBtn = new Button(spinBtnStyle);
		spinBtn.addListener( new InputListener() {
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
				if(_decider.isVisible()) {
					_decider.setVisible(false);
					_point.setVisible(false);
				}
				else
					_decider.setVisible(true);
			}
		});

		world1.getStage().addListener(new DragListener(){
			@Override
			public void dragStop(InputEvent event, float x, float y, int pointer) {
				super.dragStop(event, x, y, pointer);
				_gameStep = 3;
			}
		});

		world1.AddObject(_board);
		world1.AddObject(_cue);
		world1.AddObject(_decider);
		world1.AddObject(_point);
		world1.AddObject(spinBtn);
		world1.AddObject(_ball1);
		world1.AddObject(_ball2);
		world1.AddObject(_ball3);

		GameManager.getInstance().setCue(_cue);
	}

	private void cueEvent(){
		_cue.realTimeCueEvent(_gameStep);
		if(_gameStep == 3)
			_gameStep = 1;
	}

	@Override
	public void render (float delta) {
		cueEvent();
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
