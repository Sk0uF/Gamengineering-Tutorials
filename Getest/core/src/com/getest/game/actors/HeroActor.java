package com.getest.game.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Timer;
import com.getest.game.box2d.HeroUserData;
import com.getest.game.data.AssetMan;

public class HeroActor extends GameActors {

    private Vector2 velocityEachTime;
    private Vector2 movingLinearImpulse;
    private float velocityX;
    private float finalVel;
    private float  heroPositionY, heroPositionX;
    private boolean jumping = false;
    private boolean dodging = false;
    private boolean land = true;
    private boolean runningRight = false, runningLeft = false;
    private boolean right = true, left = false;
    private Vector2 heroPosition;
    private Timer timer1 = new Timer();
    private float stateTimeRunningRight, stateTimeRunningLeft;
    private String SLIDE, JUMP, IDLE;
    private String [] RUNNING;
    private TextureAtlas heroMovement;
    private TextureRegion slideFrame, jumpFrame, idleFrame;
    private Animation<TextureRegion> runningAnimation;
    private AssetMan manager;

    public HeroActor(Body body, AssetMan manager) {
        super(body);
        this.manager = manager;
        RUNNING =  new String[] {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32"};
        SLIDE = "8";
        JUMP = "8";
        IDLE = "1";

        heroMovement = manager.getHero();
        TextureRegion[] runningFrames = new TextureRegion[RUNNING.length];

        for (int i = 0; i < RUNNING.length ; i++){
            String pathRunning = RUNNING[i];
            runningFrames[i] = heroMovement.findRegion(pathRunning);
        }
        runningAnimation = new Animation<TextureRegion>(0.03125f,runningFrames);

        slideFrame = heroMovement.findRegion(SLIDE);
        idleFrame = heroMovement.findRegion(IDLE);
        jumpFrame = heroMovement.findRegion(JUMP);

        stateTimeRunningRight = 0f;
        stateTimeRunningLeft = 0f;
    }

    public HeroUserData getUserData() {
        return (HeroUserData) userData;
    }

    public void moveRight() {
        runningRight = true;
        right = true;
        velocityEachTime = body.getLinearVelocity();
        velocityX = velocityEachTime.x;
        finalVel = getUserData().getSpeedImpulse() - velocityX;
        movingLinearImpulse = new Vector2(finalVel, 0.0F);
        body.applyLinearImpulse(movingLinearImpulse, body.getWorldCenter(), true);

    }

    public void moveLeft() {
        runningLeft = true;
        right = false;
        velocityEachTime = body.getLinearVelocity();
        velocityX = velocityEachTime.x;
        finalVel = -getUserData().getSpeedImpulse() - velocityX;
        movingLinearImpulse = new Vector2(finalVel, 0.0F);
        body.applyLinearImpulse(movingLinearImpulse, body.getWorldCenter(), true);

    }

    public void moveStop() {
        runningRight = false;
        runningLeft = false;
        velocityEachTime = body.getLinearVelocity();
        velocityX = velocityEachTime.x;
        finalVel = 0.0F - velocityX;
        movingLinearImpulse = new Vector2(finalVel, 0.0F);
        body.applyLinearImpulse(movingLinearImpulse, body.getWorldCenter(), true);

    }

    public void jump() {
        if(!jumping && !dodging) {
            body.applyLinearImpulse(getUserData().getJumpingImpulse(), body.getWorldCenter(), true);
            jumping = true;
            land = false;
        }
    }

    public void landed() {
        jumping = false;
        land = true;
    }

    public void dodge() {
        if(land && !dodging) {
            heroPosition = body.getPosition();
            heroPositionY = heroPosition.y - 0.4F;
            heroPositionX = heroPosition.x;
            heroPosition = new Vector2(heroPositionX, heroPositionY);
            body.setTransform(heroPosition, -1.5707964F); // -90 degrees in radians
            dodging = true;
            Timer.Task task = new Timer.Task() {
                public void run() {
                    heroPosition = body.getPosition();
                    heroPositionY = heroPosition.y + 0.4F;
                    heroPositionX = heroPosition.x;
                    heroPosition = new Vector2(heroPositionX, heroPositionY);
                    body.setTransform(heroPosition, 0.0F);
                    dodging = false;
                }
            };
            timer1.scheduleTask(task, getUserData().getSlideDuration());
        }
    }

    public void act(float delta) {
        super.act(delta);

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        if (runningRight && !jumping && !dodging){

            batch.draw(runningAnimation.getKeyFrame(stateTimeRunningRight,true),screenRectangle.x-screenRectangle.width/2F +1.2F,screenRectangle.y - screenRectangle.height/2+0.75F, -1.1F*0.675F,1.1F*1.6F);
            stateTimeRunningRight += Gdx.graphics.getDeltaTime();
        }
        else if(runningLeft && !jumping && !dodging){

            batch.draw(runningAnimation.getKeyFrame(stateTimeRunningLeft,true),screenRectangle.x-screenRectangle.width/2F +0.4F,screenRectangle.y - screenRectangle.height/2+0.75F,1.1F*0.675F,1.1F*1.6F);
            stateTimeRunningLeft += Gdx.graphics.getDeltaTime();

        }
        else if (right && !runningRight && !jumping && !dodging){

            stateTimeRunningRight = 0;
            stateTimeRunningLeft = 0;
            batch.draw(idleFrame,screenRectangle.x-screenRectangle.width/2F +1.2F,screenRectangle.y - screenRectangle.height/2+0.75F, -1.1F*0.675F,1.1F*1.6F);
        }
        else if (!right && !runningLeft && !jumping && !dodging){

            stateTimeRunningRight = 0;
            stateTimeRunningLeft = 0;
            batch.draw(idleFrame,screenRectangle.x-screenRectangle.width/2F +0.4F,screenRectangle.y - screenRectangle.height/2+0.75F,1.1F*0.675F,1.1F*1.6F);
        }
        else if(right && jumping){
            stateTimeRunningRight = 0;
            stateTimeRunningLeft = 0;

            batch.draw(jumpFrame,screenRectangle.x-screenRectangle.width/2F +1.2F,screenRectangle.y - screenRectangle.height/2+0.75F, -1.1F*0.675F,1.1F*1.6F);

        }
        else if(!right && jumping){
            stateTimeRunningRight = 0;
            stateTimeRunningLeft = 0;

            batch.draw(jumpFrame,screenRectangle.x-screenRectangle.width/2F +0.4F,screenRectangle.y - screenRectangle.height/2+0.75F,1.1F*0.675F,1.1F*1.6F);
        }
        else if(right && dodging){
            stateTimeRunningRight = 0;
            stateTimeRunningLeft = 0;

            batch.draw(slideFrame,screenRectangle.x-screenRectangle.width/2F +0.55F,screenRectangle.y - screenRectangle.height/2+1.4F,0.675F/2,1.6F/2, -1.1F*0.675F,1.1F*1.6F,1, 1, 90);

        }
        else if(!right && dodging){
            stateTimeRunningRight = 0;
            stateTimeRunningLeft = 0;

            batch.draw(slideFrame,screenRectangle.x-screenRectangle.width/2F +0.4F,screenRectangle.y - screenRectangle.height/2+0.75F,0.675F/2,1.6F/2,1.1F*0.675F,1.1F*1.6F,1, 1, -90);
        }





    }

}