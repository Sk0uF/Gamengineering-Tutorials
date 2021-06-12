package com.getest.game.camera;

import com.badlogic.gdx.graphics.OrthographicCamera;

public class AndroidCamera extends OrthographicCamera {
    public AndroidCamera(float width, float height) {

        super((float)width, (float)height);
        this.position.x = (float)(width / 2);
        this.position.y = (float)(height / 2);
    }
}