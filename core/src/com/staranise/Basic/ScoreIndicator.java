package com.staranise.basic;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Queue;

/**
 * Created by 현성 on 2016-07-19.
 */
public class ScoreIndicator extends Actor{

    public enum INDICATOR_DIR{
        LEFT2RIGHT,
        RIGHT2LEFT
    }

    private INDICATOR_DIR _dir;

    private Sprite _sprIndicatorOrange;
    private Sprite _sprIndicatorWhite;

    private Queue<Sprite> _arrSprRemained = new Queue<Sprite>();
    private Queue<Sprite> _arrSprGained = new Queue<Sprite>();

    public ScoreIndicator(INDICATOR_DIR dir){
        _sprIndicatorOrange = new Sprite(new Texture("Score_Orange.png"));
        _sprIndicatorWhite = new Sprite(new Texture("Score_White.png"));

        _dir = dir;
        initialize();
    }

    private void initialize(){
        int cnt = 0;
        for(int i=0;i<4;i++){
            for(int j=0;j<5;j++, cnt++) {
                Sprite clone;
                if (_dir == INDICATOR_DIR.LEFT2RIGHT) {
                    if (i % 2 == 0)
                        clone = new Sprite(_sprIndicatorWhite);
                    else
                        clone = new Sprite(_sprIndicatorOrange);
                }
                else{
                    if(i % 2 == 0)
                        clone = new Sprite(_sprIndicatorOrange);
                    else
                        clone = new Sprite(_sprIndicatorWhite);
                }

                if (_dir == INDICATOR_DIR.LEFT2RIGHT)
                    clone.setCenter(139.f + 12.f * cnt, 91.f);
                else if (_dir == INDICATOR_DIR.RIGHT2LEFT)
                    clone.setCenter(885.f - 12.f * cnt, 91.f);
                _arrSprRemained.addLast(clone);
            }
        }
    }

    public void incPoint(){
        Sprite sprite = _arrSprRemained.last();
        if(_dir == INDICATOR_DIR.LEFT2RIGHT)
            sprite.setCenter(426.f - _arrSprGained.size * 12.f, 91.f);
        else
            sprite.setCenter(598.f + _arrSprGained.size * 12.f, 91.f);
        _arrSprGained.addLast(sprite);
        _arrSprRemained.removeLast();
    }

    public void decPoint(){
        Sprite sprite = _arrSprGained.last();

        if(_dir == INDICATOR_DIR.LEFT2RIGHT)
            sprite.setCenter(139.f + _arrSprRemained.size * 12.f, 91.f);
        else
            sprite.setCenter(885.f - _arrSprRemained.size * 12.f, 91.f);
        _arrSprRemained.addLast(sprite);
        _arrSprGained.removeLast();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        for(Sprite sprite : _arrSprRemained){
            sprite.draw(batch);
        }

        for(Sprite sprite : _arrSprGained){
            sprite.draw(batch);
        }
    }
}
