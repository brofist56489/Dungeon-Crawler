package com.gb.war;

import java.applet.Applet;
import java.applet.AudioClip;

public class Sound {
//	public static final Sound Example = new Sound("/example.mid");
	public static final Sound calm = new Sound("/sounds/ludumdare.mid");
	public static final Sound use = new Sound("/sounds/use.wav");

	private AudioClip clip;

	private Sound(String name) {
		try {
			clip = Applet.newAudioClip(Sound.class.getResource(name));
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	public void play() {
		try {
			new Thread() {
				public void run() {
					clip.play();
				}
			}.start();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	public void stop() {
		clip.stop();
	}
}