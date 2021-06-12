package com.getest.game.data;


import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.SkinLoader;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class AssetMan {
    private  AssetManager manager;


    public AssetMan() {
        manager = new AssetManager();

    }

    public  void loadAllAssets() {

        SkinLoader.SkinParameter menu = new SkinLoader.SkinParameter("skins/mainMenuPack.atlas");
        SkinLoader.SkinParameter buttons = new SkinLoader.SkinParameter("skins/inGameButtons.atlas");

        manager.load("inGame/enemies.atlas", TextureAtlas.class);
        manager.load("inGame/hero.atlas", TextureAtlas.class);

        manager.load("mainMenu/mainMenu.png", Texture.class);
        manager.load("inGame/backGround.png", Texture.class);


        manager.load("skins/play.json", Skin.class, menu);
        manager.load("skins/jumpButton.json", Skin.class, buttons);
        manager.load("skins/slideButton.json", Skin.class, buttons);
        manager.load("skins/rightButton.json", Skin.class, buttons);
        manager.load("skins/leftButton.json", Skin.class, buttons);

    }

    public void unloadMenuAssets() {
        manager.unload("skins/play.json");
        manager.unload("mainMenu/mainMenu.png");
    }


    public TextureAtlas getEnemies(){
        return manager.get("inGame/enemies.atlas");

    }

    public TextureAtlas getHero(){
        return manager.get("inGame/hero.atlas");
    }

    public Texture getMainMenu(){
        return manager.get("mainMenu/mainMenu.png");
    }

    public Texture getBackGround(){
        return manager.get("inGame/backGround.png");
    }


    public Skin getPlay(){
        return manager.get("skins/play.json");
    }

    public Skin getJump(){
        return manager.get("skins/jumpButton.json");
    }

    public Skin getRight(){
        return manager.get("skins/rightButton.json");
    }


    public Skin getLeft(){
        return manager.get("skins/leftButton.json");
    }


    public Skin getSlide(){
        return manager.get("skins/slideButton.json");
    }

    public boolean update() {
        return manager.update();
    }

    public void clear(){ manager.clear();}

    public void finish(){manager.finishLoading();}
}
