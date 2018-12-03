package com.migara.mudan.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.migara.mudan.MudanDemo;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = MudanDemo.WITDH;
		config.height = MudanDemo.HEIGHT;
		config.title = MudanDemo.TITLE;
		new LwjglApplication(new MudanDemo(), config);
	}
}
