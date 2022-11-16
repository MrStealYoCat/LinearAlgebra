package com.mrstealyocat.Shapes;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static java.lang.Math.*;

public class Shape {

	private Matrix coordinate;

	public Shape() {
		this.coordinate = new Matrix(new float[]{
						0.0f, 0.2f, 0f,
						0.0f, 0.1f, 0f,
						0f, 0f, 1f});
	}

	public Matrix getCoordinate() {
		return coordinate;
	}
	public void setCoordinate(float[] coordinate) {
		this.coordinate.setMatrixArray(coordinate);
	}
	public void setCoord(float[][] coord) {
		this.coordinate.setMatrixArray(coord);
	}
	public void setCoord(Matrix coord) {
		this.coordinate = coord;
	}
	public void transform() {
		setCoord(coordinate.MultiplicationBy(new float[]{-1f,-1f, 1f}));
	}
	public void transform(float degrees) {
		rotate(degrees);
	}
	public void rotate(float degrees) {
		float pi180 = (float)(PI/180);
		float[] rotation = new float[]{
						(float) cos(degrees*pi180), (float) (-1*sin(degrees*pi180)), 0f,
						(float) sin(degrees*pi180), (float) cos(degrees*pi180), 0f,
						0f, 0f, 1f};
		for (int i=0; i<rotation.length; i++) {
			rotation[i] = round(rotation[i], 2);
		}
		Matrix rotationMatrix = new Matrix(rotation);
		System.out.println(coordinate);
		System.out.println(rotationMatrix);
		System.out.println(rotationMatrix.MultiplicationBy(coordinate));
		coordinate = rotationMatrix.MultiplicationBy(coordinate);

	}

	public static float round(float d, int decimalPlace)
	{
		return BigDecimal.valueOf(d).setScale(decimalPlace, RoundingMode.HALF_UP).floatValue();
	}
}
