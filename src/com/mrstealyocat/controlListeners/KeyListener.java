package com.mrstealyocat.controlListeners;
import com.mrstealyocat.sprites.Player;

import static org.lwjgl.glfw.GLFW.*;

public class KeyListener {
	private static KeyListener instance;
	private final boolean[] keyPressed = new boolean[GLFW_KEY_LAST+2];
	private static Player player;

	public static KeyListener get() {
		if (instance == null) {
			instance = new KeyListener();
		}
		return instance;
	}

	public static void setPlayer(Player player) {
		KeyListener.player = player;
	}

	public static void keyCallback(long window, int key, int scancode, int action, int mods) {
		if ( key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE )
			glfwSetWindowShouldClose(window, true); // We will detect this in the rendering loop
		if (key == GLFW_KEY_ENTER && action == GLFW_PRESS) {
			player.nextTransform();
		}
		if (action == GLFW_PRESS) {
			get().keyPressed[key] = true;
		} else if (action == GLFW_RELEASE) {
			get().keyPressed[key] = false;
		}
	}

	public static boolean isKeyPressed(int keyCode) {
		return get().keyPressed[keyCode];
	}
}
