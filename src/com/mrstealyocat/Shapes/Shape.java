package com.mrstealyocat.Shapes;

import static com.mrstealyocat.controlListeners.KeyListener.*;
import static com.mrstealyocat.controlListeners.MouseListener.*;
import static org.lwjgl.glfw.GLFW.*;

public class Shape {

	private double posX;
	private double posY;
	private double vectorX;
	private double vectorY;
	private int rotation = -90;
	private float[][] coord;
	private int counter;

	public float[][] getCoord() {
		return coord;
	}

	public void setCoord(float[][] coord) {
		this.coord = coord;
	}

	public Shape() {
		this.coord = new float[][]{
						{0.1f, 0.3f},
						{-0.2f, 0.1f}
		};
	}


	/* Checks normal game keys for movement */
	public void keyPressed() {
		rotation = (rotation + (int)(getDX()/2)) % 360;
		if (isKeyPressed(GLFW_KEY_LEFT) || isKeyPressed(GLFW_KEY_A)) {
			move(1,rotation-90);
		}
		if (isKeyPressed(GLFW_KEY_RIGHT) || isKeyPressed(GLFW_KEY_D)) {
			move(1,rotation+90);
		}
		if (isKeyPressed(GLFW_KEY_DOWN) || isKeyPressed(GLFW_KEY_S)) {
			move(1,rotation);
		}
		if (isKeyPressed(GLFW_KEY_UP) || isKeyPressed(GLFW_KEY_W)) {
			move(1,rotation+180);
		}
	}
	public void nextTransform() {
		float[][][] coord = {
						{{0.1f, 0.3f}, {-0.2f, 0.1f}},
						{{0.1f, 0.3f}, {0.4f, 0.7f}}
		};
		setCoord(coord[++counter % coord.length]);
	}
	public void move(int velocity, int rotation) {
		System.out.printf("%f, %f\n", vectorX, vectorY);
		if (vectorX > -10 && vectorX < 10) {
			vectorX += calculateRunD(velocity, rotation)/100;
		}
		if (vectorY > -10 && vectorY < 10 ) {
			vectorY += calculateRiseD(velocity, rotation)/100;
		}
		posX += vectorX/100;
		posY += vectorY/100;

	}
	public static double calculateRiseR(int velocity, double rotationInRadians) {
		return velocity*Math.sin(rotationInRadians);
	}
	public static double calculateRiseD(int velocity, double rotationInDegrees) {
		return calculateRiseR(velocity, rotationInDegrees * 3.14159/180);
	}
	public static double calculateRunR(int velocity, double rotationInRadians) {
		return velocity*Math.cos(rotationInRadians);
	}
	public static double calculateRunD(int velocity, double rotationInDegrees) {
		return calculateRunR(velocity, rotationInDegrees * 3.14159/180);
	}
	public void rotate() {

	}
	public double getPosX() {
		return posX;
	}

	public double getPosY() {
		return posY;
	}

	public int getRotation() {
		return rotation;
	}
}
