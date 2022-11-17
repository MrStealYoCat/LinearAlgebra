package com.mrstealyocat.Shapes;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static java.lang.Math.*;

public class Shape {

	private Matrix coordinate;
	private Matrix[] vertices;

	public Shape() {
		float[] fa1 = new float[]{
						1f, 0f, 0.2f,
						0f, 1f, 0.1f,
						0f, 0f, 1f };
		float[] fa2 = new float[]{
						1f, 0f, 0.0f,
						0f, 1f, 0.0f,
						0f, 0f, 1f };
		this.coordinate = new Matrix(fa1);
		this.vertices = new Matrix[]{ new Matrix(fa1), new Matrix(fa2) };
	}

	public Matrix[] getVertices() {
		return vertices;
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
		float[] fa = new float[]{1, 1, -1};
		transform(fa);
	}
	public void transform(float[] fa) {
		for (Matrix vector:vertices) {
			vector = vector.multiplicationBy(fa);
		}
	}
	public void transform(Matrix matrix) {
		//setCoord(coordinate.multiplicationBy(new float[]{-1f,-1f, 1f}));
		for (Matrix vector:vertices) {
			vector.multiplicationBy(matrix);
		}
	}

	public void transform(float degrees) {
		rotate(degrees);
	}
	public void rotate(float degrees) {
		coordinate = coordinate.rotateMatrix(degrees);
	}
	public void rotate() {
		for (Matrix vector:vertices) {
			vector.rotateMatrix(10);
		}
	}
}
