package com.getest.game.box2d;

import com.badlogic.gdx.math.Vector2;
import com.getest.game.enums.UserDataType;

public class HeroUserData extends UserData {

    private Vector2 jumpingImpulse;
    private float slideDuration;
    private float speedImpulse;

    public HeroUserData(float width, float height) {

        super(width, height);
        jumpingImpulse = new Vector2(0,11F);
        slideDuration = 1.5F;
        speedImpulse = 5.0F;
        userDataType = UserDataType.HERO;

    }

    public void setJumpingImpulse(Vector2 jumpingImpulse) {
        this.jumpingImpulse = jumpingImpulse;
    }

    public void setSlideDuration(float slideDuration) {
        this.slideDuration = slideDuration;
    }

    public void setSpeedImpulse(float SpeedImpulse) {
        this.speedImpulse = speedImpulse;
    }

    public Vector2 getJumpingImpulse() {
        return jumpingImpulse;
    }

    public float getSlideDuration() {
        return slideDuration;
    }

    public float getSpeedImpulse() {
        return speedImpulse;
    }
}