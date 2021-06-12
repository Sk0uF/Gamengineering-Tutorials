package com.getest.game.misc;

import com.badlogic.gdx.physics.box2d.Body;
import com.getest.game.box2d.UserData;
import com.getest.game.enums.UserDataType;

public class BodyMisc {

    public BodyMisc(){

    }

    public static boolean bodyInBounds(Body body) {
        UserData userData = (UserData)body.getUserData();
        switch(userData.getUserDataType()) {
            case HERO:
            case ENEMY:
                return body.getPosition().x + userData.getWidth() / 2.0F > -4.0F && body.getPosition().x + userData.getWidth() / 2.0F < 20F;
            default:
                return true;
        }
    }

    public static boolean bodyIsHero(Body body) {
        UserData userData = (UserData)body.getUserData();
        return userData != null && userData.getUserDataType() == UserDataType.HERO;
    }

    public static boolean bodyIsGround(Body body) {
        UserData userData = (UserData)body.getUserData();
        return userData != null && userData.getUserDataType() == UserDataType.GROUND;
    }

    public static boolean bodyIsEnemy(Body body) {
        UserData userData = (UserData)body.getUserData();
        return userData != null && userData.getUserDataType() == UserDataType.ENEMY;
    }
}
