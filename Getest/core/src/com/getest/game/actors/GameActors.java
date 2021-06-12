package com.getest.game.actors;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.getest.game.box2d.UserData;

public abstract class GameActors extends Actor {

    protected Body body;
    protected UserData userData;
    protected Rectangle screenRectangle;

    public GameActors(Body body) {
        this.body = body;
        this.userData = (UserData)body.getUserData();
        screenRectangle = new Rectangle();
    }

    public abstract UserData getUserData();

    @Override
    public void act(float delta) {
        super.act(delta);

        if (body.getUserData() != null) {
            updateRectangle();
        } else {
            remove();
        }
    }

    private void updateRectangle() {
        screenRectangle.x = (body.getPosition().x - userData.getWidth() / 2);
        screenRectangle.y = (body.getPosition().y - userData.getHeight() / 2);
        screenRectangle.width = (userData.getWidth());
        screenRectangle.height = (userData.getHeight());
    }
}