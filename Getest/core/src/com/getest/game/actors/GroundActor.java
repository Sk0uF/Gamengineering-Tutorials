package com.getest.game.actors;

import com.badlogic.gdx.physics.box2d.Body;
import com.getest.game.box2d.GroundUserData;

public class GroundActor extends GameActors {

    public GroundActor(Body body) {
        super(body);
    }

    public GroundUserData getUserData() {
        return (GroundUserData) userData;
    }

}



