package com.staranise.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.staranise.basic.*;
import com.staranise.Util.DebugLine;
import com.staranise.billiard.GameManager;
import com.staranise.billiard.HitChecker;
import com.staranise.thing.Vec2;

//
public class GameMain implements Screen {

	private GNPBatchProcessor GNPBatchProcessor1;
	private Cue _cue;

	private int _gameMode;

	public static GameMain _temp = null;

	private OrthographicCamera camera;

	public GameMain(int gameMode){
		_temp = this;
		_gameMode = gameMode;
	}

	private void fourBallModeInit(){
		BilliardBall player1ball = new BilliardBall(1, new Vec2(512.f, 384.f), GNPBatchProcessor1, true);
		player1ball.setActive(true);
		GameManager.getInstance().setPlayer1Ball(player1ball);
		player1ball.getEngine().setId("player1");

		BilliardBall player2ball = new BilliardBall(2, new Vec2(512.f, 450.f), GNPBatchProcessor1, true);
		GameManager.getInstance().setPlayer2Ball(player2ball);
		player2ball.getEngine().setId("player2");

		BilliardBall ball1 = new BilliardBall(3, new Vec2(600.f, 384.f), GNPBatchProcessor1, true);
		ball1.getEngine().setId("ball1");
		BilliardBall ball2 = new BilliardBall(3, new Vec2(400.f, 450.f), GNPBatchProcessor1, true);
		ball2.getEngine().setId("ball2");

//		GameManager.getInstance().addBilliardBall(player1ball);
//		GameManager.getInstance().addBilliardBall(player2ball);
//		GameManager.getInstance().addBilliardBall(ball1);
//		GameManager.getInstance().addBilliardBall(ball2);
//
		GNPBatchProcessor1.AddObject(player1ball);
		GNPBatchProcessor1.AddObject(player2ball);
		GNPBatchProcessor1.AddObject(ball1);
		GNPBatchProcessor1.AddObject(ball2);
	}

	private void threeBallModeInit(){
		BilliardBall player1ball = new BilliardBall(1, new Vec2(512.f, 384.f), GNPBatchProcessor1, true);
		player1ball.setActive(true);
		GameManager.getInstance().setPlayer1Ball(player1ball);
		player1ball.getEngine().setId("player1");

		BilliardBall player2ball = new BilliardBall(2, new Vec2(512.f, 450.f), GNPBatchProcessor1, true);
		GameManager.getInstance().setPlayer2Ball(player2ball);
		player2ball.getEngine().setId("player2");

		BilliardBall ball = new BilliardBall(3, new Vec2(600.f, 384.f), GNPBatchProcessor1, true);
		ball.getEngine().setId("ball");

		GNPBatchProcessor1.AddObject(player1ball);
		GNPBatchProcessor1.AddObject(player2ball);
		GNPBatchProcessor1.AddObject(ball);
	}

	@Override
	public void show () {
		camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.position.set(Gdx.graphics.getWidth() / 2.f, Gdx.graphics.getHeight() / 2.f, 0.f);
		camera.update();

		GameManager.getInstance().cam = camera;

		//GNPBatchProcessor1 = GameManager.getInstance().getWorld();

		GNPBatchProcessor1 = new GNPBatchProcessor(true);

		GameManager.getInstance().setGNPBatchProcessor(GNPBatchProcessor1);

		GNPBatchProcessor1.getUniverse().setBorder(new Vec2(512-768/2, 384-384/2), new Vec2(768, 384), false);

		BilliardBoard board = new BilliardBoard(GNPBatchProcessor1);
		BallSpinDecider decider = new BallSpinDecider();

		GameManager.getInstance().setSpinDecider(decider);

		_cue = new Cue();
		GameManager.getInstance().setCue(_cue);

		final ScoreBoard scoreBoard = new ScoreBoard();
		scoreBoard.initialize();

		GameManager.getInstance().setOnExecuteStepListener(new GameManager.ExecuteStepListener() {
			@Override
			public void executeStep(GameManager.GAME_STEP player) {
				scoreBoard.setPlayerTurn(player);
			}
		});
		GameManager.getInstance().setOnHitSuccessListener(new GameManager.HitSuccessListener() {
			@Override
			public void notifyHitSuccess(GameManager.GAME_STEP player, boolean isThreeCushion) {
				scoreBoard.incPointToPlayer(player);
			}
		});
		GameManager.getInstance().setOnHitBadListener(new GameManager.HitBadListener() {
			@Override
			public void notifyHitBad(GameManager.GAME_STEP player) {
				scoreBoard.decPointToPlayer(player);
			}
		});

		GNPBatchProcessor1.AddObject(board);
		GNPBatchProcessor1.AddObject(scoreBoard);
		GNPBatchProcessor1.AddObject(decider);

		if(_gameMode == 4){
			fourBallModeInit();
		}
		else if(_gameMode == 3){
			threeBallModeInit();
		}
		GNPBatchProcessor1.AddObject(_cue);
	}

	@Override
	public void render (float delta) {

		if(HitChecker.getHitChecker() != null) {
			HitChecker.getHitChecker().log();
		}

		Vec2 targetBallPos = GameManager.getInstance().getCue().getTargetBallPos();
		Vec2 shotDirection = GameManager.getInstance().getCue().getDirection();
		camera.update();

		_cue.realTimeCueEvent();

		Gdx.gl.glClearColor(0.f, 0.f, 0.f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		GNPBatchProcessor1.Render(delta);
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
