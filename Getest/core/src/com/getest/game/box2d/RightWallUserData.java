package com.getest.game.box2d;

import com.getest.game.enums.UserDataType;

public class RightWallUserData extends UserData {
    public RightWallUserData(float width, float height) {
        super(width, height);
        userDataType = UserDataType.RIGHTWALL;
    }
}