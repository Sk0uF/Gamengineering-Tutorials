package com.getest.game.viewport;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.Viewport;


public class MyViewport extends Viewport {
    private float minWorldWidth, minWorldHeight;
    private float maxWorldWidth, maxWorldHeight;
    private float WIDTH, HEIGHT, AR, V_WIDTH, V_HEIGHT, V_AR;


    public MyViewport (float minWorldWidth, float minWorldHeight) {
        this(minWorldWidth, minWorldHeight, 0, 0, new OrthographicCamera());

    }


    public MyViewport (float minWorldWidth, float minWorldHeight, Camera camera) {
        this(minWorldWidth, minWorldHeight, 0, 0, camera);

    }


    public MyViewport (float minWorldWidth, float minWorldHeight, float maxWorldWidth, float maxWorldHeight) {
        this(minWorldWidth, minWorldHeight, maxWorldWidth, maxWorldHeight, new OrthographicCamera());

    }


    public MyViewport (float minWorldWidth, float minWorldHeight, float maxWorldWidth, float maxWorldHeight, Camera camera) {
        this.minWorldWidth = minWorldWidth;
        this.minWorldHeight = minWorldHeight;
        this.maxWorldWidth = maxWorldWidth;
        this.maxWorldHeight = maxWorldHeight;
        setCamera(camera);

    }

    @Override
    public void update (int screenWidth, int screenHeight, boolean centerCamera) {

        // Fit min size to the screen.
        float worldWidth = minWorldWidth;
        float worldHeight = minWorldHeight;

        WIDTH = Gdx.graphics.getWidth();
        HEIGHT = Gdx.graphics.getHeight();
        AR = WIDTH/HEIGHT;

        V_WIDTH = 16;
        V_HEIGHT = 9;
        V_AR = V_WIDTH/V_HEIGHT;

        Vector2 scaled = Scaling.fit.apply(worldWidth, worldHeight, screenWidth, screenHeight);

        // Extend in the short direction.
        int viewportWidth = Math.round(scaled.x);
        int viewportHeight = Math.round(scaled.y);

        if (viewportWidth < screenWidth) {
            float toViewportSpace = viewportHeight / worldHeight;
            float toWorldSpace = worldHeight / viewportHeight;
            float lengthen = (screenWidth - viewportWidth) * toWorldSpace;
            if (maxWorldWidth > 0) lengthen = Math.min(lengthen, maxWorldWidth - minWorldWidth);
            worldWidth += lengthen;
            viewportWidth += Math.round(lengthen * toViewportSpace);
        } else if (viewportHeight < screenHeight) {
            float toViewportSpace = viewportWidth / worldWidth;
            float toWorldSpace = worldWidth / viewportWidth;
            float lengthen = (screenHeight - viewportHeight ) * toWorldSpace;
            if (maxWorldHeight > 0) lengthen = Math.min(lengthen, maxWorldHeight - minWorldHeight);
            worldHeight += lengthen;
            viewportHeight += Math.round(lengthen * toViewportSpace);
        }
        setWorldSize(worldWidth, worldHeight);

        setScreenBounds((screenWidth - viewportWidth) / 2, (screenHeight - viewportHeight) / 2, viewportWidth, viewportHeight);


        if( V_AR < AR)
            apply(false);  //Only this line differs from
        else
            apply(centerCamera);
    }

    public float getMinWorldWidth () {
        return minWorldWidth;
    }

    public void setMinWorldWidth (float minWorldWidth) {
        this.minWorldWidth = minWorldWidth;
    }

    public float getMinWorldHeight () {
        return minWorldHeight;
    }

    public void setMinWorldHeight (float minWorldHeight) {
        this.minWorldHeight = minWorldHeight;
    }

    public float getMaxWorldWidth () {
        return maxWorldWidth;
    }

    public void setMaxWorldWidth (float maxWorldWidth) {
        this.maxWorldWidth = maxWorldWidth;
    }

    public float getMaxWorldHeight () {
        return maxWorldHeight;
    }

    public void setMaxWorldHeight (float maxWorldHeight) {
        this.maxWorldHeight = maxWorldHeight;
    }
}