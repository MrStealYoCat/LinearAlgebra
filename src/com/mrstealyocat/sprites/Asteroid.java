package com.mrstealyocat.sprites;

public class Asteroid {

	private double startX;
	private double startY;
	private double rotation;
	private double size;

	public void move(int keyCode) {

	}
	public static Double calculateRiseR(Double riseDistance, Double rotationInRadians) {
		return riseDistance * Math.sin(rotationInRadians);
	}
	public static Double calculateRiseD(Double riseDistance, Double rotationInDegrees) {
		return calculateRiseR(riseDistance, rotationInDegrees * 3.14159/180);
	}
	public static Double calculateRunR(Double runDistance, Double rotationInRadians) {
		return runDistance * Math.cos(rotationInRadians);
	}
	public static Double calculateRunD(Double runDistance, Double rotationInDegrees) {
		return calculateRunR(runDistance, rotationInDegrees * 3.14159/180);
	}
	public void rotate() {

	}
	public void explode() {

	}
}
