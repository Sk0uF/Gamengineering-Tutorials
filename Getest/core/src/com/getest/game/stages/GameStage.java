package com.getest.game.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Timer;
import com.getest.game.actors.EnemyActor;
import com.getest.game.actors.GroundActor;
import com.getest.game.actors.HeroActor;
import com.getest.game.actors.LeftWallActor;
import com.getest.game.actors.RightWallActor;
import com.getest.game.ads.AdsController;
import com.getest.game.camera.AndroidCamera;
import com.getest.game.data.AssetMan;
import com.getest.game.misc.BodyMisc;
import com.getest.game.misc.WorldMisc;
import com.getest.game.viewport.MyViewport;

public class GameStage extends Stage implements ContactListener{

    private float accumulator, TIME_STEP, alpha;
    private Box2DDebugRenderer renderer;
    private WorldMisc wrl;
    private World world;
    private GroundActor ground;
    private LeftWallActor leftWall;
    private RightWallActor rightWall;
    private HeroActor hero;
    private Boolean right = false, left = false;
    private Skin leftButtonSkin, rightButtonSkin, jumpButtonSkin, slideButtonSkin;
    private ImageButton leftButton, rightButton, jumpButton, slideButton;
    private Texture backGroundTexture;
    private Image backGroundImage;
    private Timer timerEnemy = new Timer();
    private AssetMan manager;
    private AdsController adsController;


    public GameStage(AssetMan manager, AdsController adsController){

        super(new MyViewport(16f, 9f,
                new AndroidCamera(16f, 9f)));

        accumulator = 0.0F;
        TIME_STEP = 1/300F; // Recomended by libgdx
        this.manager = manager;
        this.adsController = adsController;
        setupWorld();
        if(adsController.isWifiConnected())
            adsController.showBannerAd();
    }

    private void setupWorld() {
        wrl = new WorldMisc();
        world = wrl.createWorld();
        renderer = new Box2DDebugRenderer();
        Gdx.input.setInputProcessor(this);
        world.setContactListener(this);
        setupBackGround();
        setupGround();
        setupLeftWall();
        setupRightWall();
        setupHero();
        setupButtons();
        createEnemy();

    }

    private void setupBackGround(){
        backGroundTexture = manager.getBackGround();

        backGroundTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        backGroundImage = new Image(backGroundTexture);
        backGroundImage.setSize(19.5F, 12F);
        backGroundImage.setPosition(-1.75F,0);
        addActor(backGroundImage);
    }

    private void setupGround(){
        ground = new GroundActor(wrl.createGround(world));
        addActor(ground);
    }

    private void setupLeftWall(){
        leftWall = new LeftWallActor(wrl.createLeftWall(world));
        addActor(leftWall);
    }

    private void setupRightWall(){
        rightWall = new RightWallActor(wrl.createRightWall(world));
        addActor(rightWall);
    }

    private void setupHero() {
        hero = new HeroActor(wrl.createHero(world), manager);
        addActor(hero);
    }

    private void setupButtons() {
        leftButtonSkin = manager.getLeft();
        rightButtonSkin = manager.getRight();
        jumpButtonSkin = manager.getJump();
        slideButtonSkin = manager.getSlide();

        leftButton = new ImageButton(leftButtonSkin);
        rightButton = new ImageButton(rightButtonSkin);
        jumpButton = new ImageButton(jumpButtonSkin);
        slideButton = new ImageButton(slideButtonSkin);

        leftButton.setSize(2.35F, 5F);
        rightButton.setSize(2.35F, 5F);
        jumpButton.setSize(2.35F, 5F);
        slideButton.setSize(2.35F, 5F);

        leftButton.setPosition(1.25F, -0.5F);
        rightButton.setPosition(4.0F, -0.5F);
        jumpButton.setPosition(12.75F, -0.5F);
        slideButton.setPosition(10F, -0.5F);

        addActor(leftButton);
        addActor(rightButton);
        addActor(jumpButton);
        addActor(slideButton);

        leftButton.addListener(new ActorGestureListener() {
            public void touchDown(InputEvent event, float x, float y, int pointer, int button) {
                left = true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                left = false;            }
        });


        rightButton.addListener(new ActorGestureListener() {
            public void touchDown(InputEvent event, float x, float y, int pointer, int button) {
                right = true;            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                right = false;            }
        });

        jumpButton.addListener(new ActorGestureListener() {
            public void touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    hero.jump();
            }

        });

        slideButton.addListener(new ActorGestureListener() {
            public void touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    hero.dodge();
            }

        });
    }


    private void createEnemy() {
        Timer.Task task = new Timer.Task() {
            public void run() {
                EnemyActor enemy = new EnemyActor(wrl.createEnemy(world), manager);         //I am setting up useless tasks when not needed on some worlds. Fix that.
                addActor(enemy);
            }
        };
        timerEnemy.scheduleTask(task, 1.4F, 4);

    }



    @Override
    public void act(float delta) {

        super.act(delta);
        Array<Body> bodies = new Array<Body>(world.getBodyCount());
        world.getBodies(bodies);

        step(delta);

        for(Body body : bodies){
            update(body);
        }

        if (left) {
            hero.moveLeft();
        } else if (right) {
            hero.moveRight();
        } else {
            hero.moveStop();
        }

    }

    public void step(float dt) {
        if ( dt > 0.25){
            dt = 0.25f;
        }
        accumulator += dt;
        while( accumulator >= TIME_STEP){
            world.step(TIME_STEP, 8,4); // Recomended by libgdx (8,3)
            accumulator -= TIME_STEP;

        }

        alpha = accumulator / TIME_STEP;
        Array<Body> bodies = new Array<Body>(world.getBodyCount());
        world.getBodies(bodies);
        for ( Body body : bodies){
            if ( !(body.getType() == BodyDef.BodyType.StaticBody)){
                Vector2 previousPos = body.getPosition();
                float previousAngle = body.getAngle();
                float posX = previousPos.x * (1.0F - alpha) + body.getPosition().x * alpha;
                float posY = previousPos.y * (1.0F - alpha) + body.getPosition().y * alpha;
                float angle = previousAngle * (1.0F - alpha) + body.getAngle() * alpha;
                body.setTransform(posX, posY, angle);
            }
        }
    }

    private void update(Body body) {
        if((!BodyMisc.bodyInBounds(body) && BodyMisc.bodyIsEnemy(body))){
            world.destroyBody(body);
        }

    }

//    @Override
//    public void draw() {
//        super.draw();
//        renderer.render(world, getViewport().getCamera().combined);
//    }

    @Override
    public void beginContact(Contact contact) {
        Body a = contact.getFixtureA().getBody();
        Body b = contact.getFixtureB().getBody();

        if((BodyMisc.bodyIsHero(a) && (BodyMisc.bodyIsGround(b) )) || ((BodyMisc.bodyIsGround(a) && (BodyMisc.bodyIsHero(b))))) {
            hero.landed();
        }

        if(BodyMisc.bodyIsHero(a) && BodyMisc.bodyIsEnemy(b) || BodyMisc.bodyIsHero(b) && BodyMisc.bodyIsEnemy(a)){
            adsController.showInterstitialAd(new Runnable() {
                @Override
                public void run() {
                }
            });
        }
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}