package com.staranise.basic;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.staranise.billiard.GameManager;

/**
 * Created by 현성 on 2016-07-19.
 */
public class ScoreBoard extends Actor {

    private Sprite[] _sprPlayerIndicator;

    private ScoreIndicator[] _scrIndic;

    private Sprite _sprTurnIndicator;

    private Sprite _line;

    public ScoreBoard(){
        _sprTurnIndicator = new Sprite(new Texture("turn.png"));
        _sprPlayerIndicator = new Sprite[]{
                new Sprite(new Texture("Player1_OrangeBall.png")),
                new Sprite(new Texture("Player2_WhiteBall.png"))
        };

        _scrIndic = new ScoreIndicator[]{
                new ScoreIndicator(ScoreIndicator.INDICATOR_DIR.LEFT2RIGHT),
                new ScoreIndicator(ScoreIndicator.INDICATOR_DIR.RIGHT2LEFT)
        };

        _line = new Sprite(new Texture("Line.png"));
    }

    public void initialize(){
        _sprPlayerIndicator[0].setCenter(68.f, 768.f - 673.f);
        _sprPlayerIndicator[1].setCenter(955.f, 768.f - 673.f);
        _line.setCenter(513.5f, 768.f - 677.5f);
        setPlayerTurn(GameManager.GAME_STEP.player1);
    }

    public void setPlayerTurn(GameManager.GAME_STEP player){
        if(player == GameManager.GAME_STEP.player1)
            _sprTurnIndicator.setCenter(68.f, 768.f - 673.f);
        else
            _sprTurnIndicator.setCenter(955.f, 768.f - 673.f);
    }

    public void incPointToPlayer(GameManager.GAME_STEP player){
        if(player == GameManager.GAME_STEP.player1){
            _scrIndic[0].incPoint();
        }
        else{
            _scrIndic[1].incPoint();
        }
    }

    public void decPointToPlayer(GameManager.GAME_STEP player){
        if(player == GameManager.GAME_STEP.player1){
            _scrIndic[0].decPoint();
        }
        else{
            _scrIndic[1].decPoint();
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        _line.draw(batch, parentAlpha);
        _sprTurnIndicator.draw(batch);
        for(int i=0;i<2;i++){
            _sprPlayerIndicator[i].draw(batch);
            _scrIndic[i].draw(batch, parentAlpha);
        }
    }
}
