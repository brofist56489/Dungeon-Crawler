package com.gb.war;

import java.applet.Applet;
import java.applet.AudioClip;

public class Sound {
//	public static final Sound Example = new Sound("/example.mid");
	public static final Sound calm = new Sound("/sounds/ludumdare.mid");
	public static final Sound use = new Sound("/sounds/use.wav");
	public static final Sound silence = new Sound("/sounds/silence.wav");

	private AudioClip clip;
	private Thread soundThread;

	private Sound(String name) {
		try {
			clip = Applet.newAudioClip(Sound.class.getResource(name));
			soundThread = new Thread() {
				public void run() {
					clip.play();
				}
			};
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	public void play() {
		try {
			soundThread.start();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	public void stop() {
		try {
			clip.stop();
			soundThread.join(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}