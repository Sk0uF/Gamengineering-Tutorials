package com.getest.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.getest.game.ads.AdsController;
import com.getest.game.data.AssetMan;
import com.getest.game.stages.GameStage;

public class GameScreen implements Screen {

    private GameStage gameStage;
    private AssetMan manager;
    private AdsController adsController;

    public GameScreen(AssetMan manager, AdsController adsController) {
        this.manager = manager;
        this.adsController = adsController;
    }

    @Override
    public void show() {
        gameStage = new GameStage(manager, adsController);
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0.0F, 0.0F, 0.0F, 1.0F);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        gameStage.draw();
        gameStage.act(delta);

    }

    @Override
    public void resize(int width, int height) {
        gameStage.getViewport().update(width,height,true);
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        gameStage.dispose();
    }
}