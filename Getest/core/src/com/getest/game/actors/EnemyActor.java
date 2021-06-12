package com.getest.game.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.getest.game.box2d.EnemyUserData;
import com.getest.game.data.AssetMan;

public class EnemyActor extends GameActors {

    private Animation<TextureRegion> animation;
    private TextureRegion[] runningFrames;
    private TextureAtlas textureAtlas;
    private float stateTimeGroundRight,stateTimeGroundLeft,stateTimeAirLeft,stateTimeAirRight;
    private AssetMan manager;

    public EnemyActor(Body body, AssetMan manager) {
        super(body);
        this.manager = manager;
        textureAtlas = manager.getEnemies();
        runningFrames = new TextureRegion[getUserData().getTextureRegions().length];
        for (int i = 0; i < getUserData().getTextureRegions().length; i++) {
            String path = getUserData().getTextureRegions()[i];
            runningFrames[i] = textureAtlas.findRegion(path);
        }
        animation = new Animation<TextureRegion>(0.03125f, runningFrames);
        stateTimeGroundRight = 0f;
        stateTimeGroundLeft = 0f;
        stateTimeAirLeft = 0f;
        stateTimeAirRight = 0f;
    }

    public EnemyUserData getUserData() {
        return (EnemyUserData) userData;
    }

    public void act(float delta) {
        super.act(delta);

    }

    public Body getBody(){return body;}

    @Override
    public void draw(Batch batch, float parentAlpha) {

        super.draw(batch, parentAlpha);
        if (getUserData().getX() < 0 && getUserData().getY() == 2.5F) {

            batch.draw(animation.getKeyFrame(stateTimeGroundRight, true), screenRectangle.x - screenRectangle.width / 2 +0.5F , screenRectangle.y - screenRectangle.height / 2 +0.4F, (float)1.2*screenRectangle.getWidth(), (float)1.2*screenRectangle.getHeight());
            stateTimeGroundRight += Gdx.graphics.getDeltaTime();

        }
        else if(getUserData().getX() >0 && getUserData().getY() == 2.5F){
            batch.draw(animation.getKeyFrame(stateTimeGroundLeft, true), screenRectangle.x - screenRectangle.width / 2 +1.4F , screenRectangle.y - screenRectangle.height / 2 +0.4f, -(float)1.2*screenRectangle.getWidth(), (float)1.2*screenRectangle.getHeight());
            stateTimeGroundLeft += Gdx.graphics.getDeltaTime();
        }
        else if(getUserData().getX() <0 && getUserData().getY() == 3.6F){
            batch.draw(animation.getKeyFrame(stateTimeAirRight, false), screenRectangle.x - screenRectangle.width / 2 +0.5F, screenRectangle.y - screenRectangle.height / 2 +0.4F, (float)1.2*screenRectangle.getWidth(), (float)1.2*screenRectangle.getHeight());
            stateTimeAirRight += Gdx.graphics.getDeltaTime();
        }
        else{
            batch.draw(animation.getKeyFrame(stateTimeAirLeft, false), screenRectangle.x - screenRectangle.width / 2 +1.4F , screenRectangle.y - screenRectangle.height / 2 +0.4F, -(float)1.2*screenRectangle.getWidth(), (float)1.2*screenRectangle.getHeight());
            stateTimeAirLeft += Gdx.graphics.getDeltaTime();
        }


    }
}
