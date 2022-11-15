package com.mrstealyocat;

import com.mrstealyocat.display.Graphics;
import com.mrstealyocat.display.Window;
import com.mrstealyocat.sprites.Player;
import org.lwjgl.opengl.GL;

import java.util.Objects;

import static com.mrstealyocat.controlListeners.MouseListener.endMouseFrame;
import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

public class Main {

	public static void main(String[] args) {
		Player player = new Player();
		Window window = new Window("My display.Window", 1000, 1000, player);
		loop(window, player);
		cleanup(window);
	}

	/* Game Loop Function */
	public static void loop(Window window, Player player) {
		// This line is critical for LWJGL's interoperation with GLFW's
		// OpenGL context, or any context that is managed externally.
		// LWJGL detects the context that is current in the current thread,
		// creates the GLCapabilities instance and makes the OpenGL
		// bindings available for use.
		GL.createCapabilities();

		// Keep mouse inside window and hidden
		glfwSetInputMode(window.getWindowHandle(), GLFW_CURSOR, GLFW_CURSOR_HIDDEN);
		glfwSetInputMode(window.getWindowHandle(), GLFW_CURSOR, GLFW_CURSOR_DISABLED);

		// Set the clear color
		glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
		// Run the rendering loop until the user has attempted to close
		// the window or has pressed the ESCAPE key.
		while ( !glfwWindowShouldClose(window.getWindowHandle()) ) {

			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer

			// Poll for window events. The key callback above will only be
			// invoked during this call.
			glfwPollEvents();

			//TODO change to an object that only re-calculates opengl walls when you move
			//Frame.drawWalls(player);
			//Graphics.draw(player.getPosX(), player.getPosY());
			Graphics.drawGraph();
			Graphics.drawMatrix(player.getCoord());

			//player.keyPressed();
			//player.nextTransform();
			//player.rotate();
			//player.move(0,player.getRotation());
			endMouseFrame();

			glFlush(); // render now

			glfwSwapBuffers(window.getWindowHandle()); // swap the color buffers
		}
	}

	private static void cleanup(Window window) {
		// Free up cursor after exiting
		glfwSetInputMode(window.getWindowHandle(), GLFW_CURSOR, GLFW_CURSOR_NORMAL);

		// Free the window callbacks and destroy the window
		glfwFreeCallbacks(window.getWindowHandle());
		glfwDestroyWindow(window.getWindowHandle());

		// Terminate GLFW and free the error callback
		glfwTerminate();
		Objects.requireNonNull(glfwSetErrorCallback(null)).free();
	}
}
