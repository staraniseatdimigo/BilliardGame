package com.staranise;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.staranise.Basic.*;
import com.staranise.Util.DebugLine;
import com.staranise.thing.Vec2;

//
public class GameMain implements Screen {

	private World world1;
	private Cue _cue;

	public static GameMain _temp = null;

	private OrthographicCamera camera;

	public GameMain(){
		_temp = this;
	}

	@Override
	public void show () {

		BilliardBoard _board;
		BilliardBall _ball1;
		BilliardBall _ball2;
		BilliardBall _ball3;
		final BallSpinDecider _decider;
		final BallSpinPoint _point;

		camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.position.set(Gdx.graphics.getWidth() / 2.f, Gdx.graphics.getHeight() / 2.f, 0);
		camera.update();

		GameManager.getInstance().cam = camera;

		world1 = new World(true, true);
		world1.getUniverse().setBorder(new Vec2(100, 110), new Vec2(440, 220), false);

		_board = new BilliardBoard();
		_ball1 = new BilliardBall(1, new Vec2(450.f, 260.f), world1);
		_ball2 = new BilliardBall(2, new Vec2(220.f, 220.f), world1);
		_ball3 = new BilliardBall(3, new Vec2(450.f, 220.f), world1);

		_ball1.getEngine().setId("Ball1");
		_ball2.getEngine().setId("Ball2");

		_decider = new BallSpinDecider();
		_point = new BallSpinPoint();

		_point.setVisible(false);

		GameManager.getInstance().setSpinDecider(_decider);

		_cue = new Cue();
		GameManager.getInstance().setCue(_cue);

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
				else {
					_decider.setVisible(true);
					_point.setVisible(true);
				}
			}
		});


		world1.getStage().addListener(new DragListener(){
			@Override
			public void dragStop(InputEvent event, float x, float y, int pointer) {
				super.dragStop(event, x, y, pointer);
			}
		});

		world1.getStage().addListener(new InputListener(){
			@Override
			public boolean keyDown(InputEvent event, int keycode) {

				return super.keyDown(event, keycode);
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

	@Override
	public void render (float delta) {
		Vec2 targetBallPos = GameManager.getInstance().getCue().getTargetBallPos();
		Vec2 shotDirection = GameManager.getInstance().getCue().getDirection();
		camera.update();
		_cue.realTimeCueEvent();

		Gdx.gl.glClearColor(0.75f, 0.75f, 0.75f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		world1.Render(delta);
		if(targetBallPos != null && shotDirection != null)
			DebugLine.RenderLine(targetBallPos, Vec2.add(targetBallPos, shotDirection.multi(-10000.f)));
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
