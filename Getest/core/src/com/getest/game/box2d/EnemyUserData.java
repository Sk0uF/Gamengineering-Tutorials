package com.getest.game.box2d;

import com.getest.game.enums.UserDataType;


public class EnemyUserData extends UserData {
    private float x,y;
    private String[] textureRegions;

    public EnemyUserData(float width, float height, float x, float y, String[] textureRegions) {
        super(width, height);
        userDataType = UserDataType.ENEMY;
        this.x = x;
        this.y = y;
        this.textureRegions = textureRegions;
    }

    public float getX() {return x;}

    public float getY() {return y;}

    public String[] getTextureRegions() {return  textureRegions;}
}
