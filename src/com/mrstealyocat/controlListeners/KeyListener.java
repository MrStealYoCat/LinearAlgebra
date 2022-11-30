package com.mrstealyocat.controlListeners;
import com.mrstealyocat.Shapes.Shape;

import static org.lwjgl.glfw.GLFW.*;

public class KeyListener {
	private static KeyListener instance;
	private final boolean[] keyPressed = new boolean[GLFW_KEY_LAST+2];
	private static Shape[] shapes;

	public static KeyListener get() {
		if (instance == null) {
			instance = new KeyListener();
		}
		return instance;
	}

	public static void setPlayer(Shape[] shapes) {
		KeyListener.shapes = shapes;
	}

	public static void keyCallback(long window, int key, int scancode, int action, int mods) {
		if ( key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE )
			glfwSetWindowShouldClose(window, true); // We will detect this in the rendering loop
		if (key == GLFW_KEY_EQUAL && action == GLFW_PRESS) {
			for (Shape shape : shapes) {
				shape.transform(new float[]{1f, 1f, 1f, 1.1f});
			}
		}
		if (key == GLFW_KEY_MINUS && action == GLFW_PRESS) {
			for (Shape shape : shapes) {
				shape.transform(new float[]{1f, 1f, 1f, 0.9f});
			}
		}
//		if (key == GLFW_KEY_R && action == GLFW_PRESS) {
//			shape.rotate();
//		}
		if (action == GLFW_PRESS) {
			get().keyPressed[key] = true;
		} else if (action == GLFW_RELEASE) {
			get().keyPressed[key] = false;
		}

		if (key == GLFW_KEY_LEFT || key == GLFW_KEY_A) {
			for (Shape shape : shapes) {
				shape.move(-0.1f, 0f, 0f);
			}
			//shape.rotateZ(-10f);
		}
		if (key == GLFW_KEY_RIGHT || key == GLFW_KEY_D) {
			for (Shape shape : shapes) {
				shape.move(0.1f, 0f, 0f);
			}
			//shape.rotateZ(10f);
		}
		if (key == GLFW_KEY_DOWN || key == GLFW_KEY_S) {
			for (Shape shape : shapes) {
				shape.move(0f, -0.1f, 0f);
			}
		}
		if (key == GLFW_KEY_UP || key == GLFW_KEY_W) {
			for (Shape shape : shapes) {
				shape.move(0f, 0.1f, 0f);
			}
		}
		if (key == GLFW_KEY_I) {
			for (Shape shape : shapes) {
				shape.move(0f, 0f, 0.1f);
			}
		}
		if (key == GLFW_KEY_O) {
			for (Shape shape : shapes) {
				shape.move(0f, 0f, -0.1f);
			}
		}
	}

	public static boolean isKeyPressed(int keyCode) {
		return get().keyPressed[keyCode];
	}
}
