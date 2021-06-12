package com.getest.game.misc;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.getest.game.box2d.EnemyUserData;
import com.getest.game.box2d.GroundUserData;
import com.getest.game.box2d.HeroUserData;
import com.getest.game.box2d.LeftWallUserData;
import com.getest.game.box2d.RightWallUserData;
import com.getest.game.enums.EnemyType;

public class WorldMisc {

    public  World createWorld() {

        return new World(new Vector2(0, -10), true);

    }

    public Body createGround(World world) {

        BodyDef ground = new BodyDef();
        ground.type = BodyDef.BodyType.StaticBody;
        ground.position.set(new Vector2(0.0F, 0.0F));
        Body body = world.createBody(ground);
        PolygonShape groundShape = new PolygonShape();
        groundShape.setAsBox(16.01F, 2.0F);
        body.createFixture(groundShape, 0.5F);
        body.setUserData(new GroundUserData(2*(16.01F), 4.0F));
        groundShape.dispose();
        return body;

    }

    public Body createLeftWall(World world) {

        BodyDef leftWall = new BodyDef();
        leftWall.type = BodyDef.BodyType.StaticBody;
        leftWall.position.set(new Vector2(-1.0F, 12.0F));
        Body body = world.createBody(leftWall);
        PolygonShape leftWallShape = new PolygonShape();
        leftWallShape.setAsBox(1.0F, 11.0F);
        body.createFixture(leftWallShape, 0.5F);
        body.setUserData(new LeftWallUserData(2.0F, 22.0F));
        leftWallShape.dispose();
        return body;

    }

    public Body createRightWall(World world) {

        BodyDef rightWall = new BodyDef();
        rightWall.type = BodyDef.BodyType.StaticBody;
        rightWall.position.set(new Vector2(17F, 12.0F));
        Body body = world.createBody(rightWall);
        PolygonShape rightWallShape = new PolygonShape();
        rightWallShape.setAsBox(0.999F, 11.0F);
        body.createFixture(rightWallShape, 0.5F);
        body.setUserData(new RightWallUserData(1.998F, 22.0F));
        rightWallShape.dispose();
        return body;

    }

    public  Body createHero(World world) {
        BodyDef hero = new BodyDef();
        hero.type = BodyDef.BodyType.DynamicBody;
        hero.position.set(new Vector2(8.0F, 2F));
        PolygonShape heroShape = new PolygonShape();
        heroShape.setAsBox(0.4F, 0.8F);
        Body body = world.createBody(hero);
        FixtureDef heroFixture = new FixtureDef();
        heroFixture.density = 0.5F;
        heroFixture.shape = heroShape;
        heroFixture.friction = 0.0F;
        body.createFixture(heroFixture);
        body.setGravityScale(5F);
        body.setFixedRotation(true);
        body.resetMassData();
        body.setUserData(new HeroUserData(0.8F, 1.6F));
        heroShape.dispose();
        return body;
    }

    public static Body createEnemy(World world) {
        EnemyType enemyType = RandomMisc.getRandomEnemyType();
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.KinematicBody;
        bodyDef.position.set(new Vector2(enemyType.getX(), enemyType.getY()));
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(enemyType.getWidth() / 2, enemyType.getHeight() / 2);
        Body body = world.createBody(bodyDef);
        body.createFixture(shape, enemyType.getDensity());
        body.resetMassData();
        EnemyUserData userData = new EnemyUserData(enemyType.getWidth(), enemyType.getHeight(), enemyType.getX(), enemyType.getY(), enemyType.getRegions());
        body.setLinearVelocity(enemyType.getVelocity());
        body.setUserData(userData);
        shape.dispose();
        return body;
    }
}