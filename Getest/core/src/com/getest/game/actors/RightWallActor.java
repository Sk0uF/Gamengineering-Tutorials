package com.getest.game.actors;

import com.badlogic.gdx.physics.box2d.Body;
import com.getest.game.box2d.RightWallUserData;

public class RightWallActor extends GameActors {
    public RightWallActor(Body body) {
        super(body);
    }

    public RightWallUserData getUserData() {
        return (RightWallUserData) userData;
    }
}
