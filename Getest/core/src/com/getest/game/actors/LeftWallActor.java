package com.getest.game.actors;

import com.badlogic.gdx.physics.box2d.Body;
import com.getest.game.box2d.LeftWallUserData;

public class LeftWallActor extends GameActors {
    public LeftWallActor(Body body) {
        super(body);
    }

    public LeftWallUserData getUserData() {
        return (LeftWallUserData) userData;
    }
}
