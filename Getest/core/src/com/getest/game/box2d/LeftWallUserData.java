package com.getest.game.box2d;

import com.getest.game.enums.UserDataType;

public class LeftWallUserData extends UserData {
    public LeftWallUserData(float width, float height) {
        super(width, height);
        userDataType = UserDataType.LEFTWALL;
    }
}