package com.staranise.Basic;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by 현성 on 2016-04-09.
 */
public class ObjectManager {

    static private ObjectManager _instance = new ObjectManager();

    private SpriteBatch _spriteBatch;
    private List<QueObject> _listQueObject = new LinkedList<QueObject>();

    public static ObjectManager getInstance(){
        return _instance;
    }

    public ObjectManager(){
        _spriteBatch = new SpriteBatch();
    }

    public void RegisterObject(QueObject obj){
        _listQueObject.add(obj);
    }

    public void Render(){
        _spriteBatch.begin();
        for(QueObject obj : _listQueObject){
            obj.Render(_spriteBatch);
        }
        _spriteBatch.end();
    }
}
