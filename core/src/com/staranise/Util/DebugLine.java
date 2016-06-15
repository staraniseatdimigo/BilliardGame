package com.staranise.Util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.staranise.Basic.GameManager;
import com.staranise.thing.Vec2;

/**
 * Created by 현성 on 2016-05-17.
 */
public class DebugLine {
    private static ShapeRenderer debugRender = new ShapeRenderer();

    public static void RenderLine(Vec2 start, Vec2 end){
        Gdx.gl.glLineWidth(1.f);
        debugRender.begin(ShapeRenderer.ShapeType.Line);
        debugRender.setProjectionMatrix(GameManager.getInstance().cam.combined);
        debugRender.setColor(Color.BLACK);
        debugRender.line(new Vector2(start.x, start.y), new Vector2(end.x, end.y));
        debugRender.end();
        Gdx.gl.glLineWidth(1.f);
    }
}
