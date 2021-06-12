package com.getest.game.game;

import com.badlogic.gdx.Game;
import com.getest.game.ads.AdsController;
import com.getest.game.screens.SplashScreen;

public class GeTest extends Game {
	private AdsController adsController;

	public GeTest(AdsController adsController){
		this.adsController = adsController;

	}

	public void create() {
		setScreen(new SplashScreen(adsController));
	}

	public void render() {
		super.render();
	}
}