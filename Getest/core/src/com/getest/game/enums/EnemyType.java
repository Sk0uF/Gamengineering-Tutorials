package com.getest.game.enums;

import com.badlogic.gdx.math.Vector2;


public enum EnemyType {

    GROUND1(  1F, 1F, 18F, 2.5F, 0.5F, new Vector2(-7.5F,0F), new  String[] {"1g","2g","3g","4g","5g","6g","7g","8g","9g","10g","11g","12g","13g","14g","15g","16g"}),
    GROUND2(  1F, 1F, -2.0F, 2.5F, 0.5F, new Vector2(7.5F,0F), new  String[] {"1g","2g","3g","4g","5g","6g","7g","8g","9g","10g","11g","12g","13g","14g","15g","16g"}),
    FLYING1(  1F, 1F, 18F, 3.6F, 0.5F, new Vector2(-7.5F,0F), new  String[] {"1f","2f","3f","4f","5f","6f","7f","8f","9f","10f","11f","12f","13f","14f","15f","16f"}),
    FLYING2(  1F, 1F, -2.0F, 3.6F, 0.5F, new Vector2(7.5F,0F), new  String[] {"1f","2f","3f","4f","5f","6f","7f","8f","9f","10f","11f","12f","13f","14f","15f","16f"});

    private float width;
    private float height;
    private float x;
    private float y;
    private float density;
    private Vector2 velocity;
    private String[] regions;


    private EnemyType(float width, float height, float x, float y, float density, Vector2 velocity, String[] regions) {
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
        this.density = density;
        this.velocity = velocity;
        this.regions = regions;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getDensity() {
        return density;
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public String[] getRegions() {return regions;}

}
