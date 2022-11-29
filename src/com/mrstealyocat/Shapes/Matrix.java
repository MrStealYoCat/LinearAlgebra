package com.mrstealyocat.Shapes;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static java.lang.Math.*;

public class Matrix {
	private float[][] matrixArray;
	private final int size;

	public Matrix() {
		this.matrixArray = new float[][] {{1,0,0,0},{0,1,0,0},{0,0,1,0},{0,0,0,1}};
		this.size = 4;
	}
	public Matrix(float x, float y, float z) {
		float[] fa = new float[]{
						1f, 0f, 0f, x,
						0f, 1f, 0f, y,
						0f, 0f, 1f, z,
						0f, 0f, 0f, 1f};
		this.size = 4;
		this.matrixArray = convertToSquareArray(fa);
	}
	public Matrix(float[] matrixArray) {
		if (matrixArray.length == 4 || matrixArray.length == 9 || matrixArray.length == 16) {
			this.size = (int)sqrt(matrixArray.length);
			this.matrixArray = convertToSquareArray(matrixArray);
		} else {
			throw new RuntimeException(
							"Invalid matrix size! Matrix must be 2x2, 3x3, or 4x4. Figure yourself out.",
							new IndexOutOfBoundsException());
		}
	}
	public Matrix(float[][] matrixArray) {
		if ((matrixArray.length == 2 && matrixArray[0].length == 2) ||
				(matrixArray.length == 3 && matrixArray[0].length == 3) ||
				(matrixArray.length == 4 && matrixArray[0].length == 4)) {
			this.size = matrixArray.length;
			this.matrixArray = matrixArray;
		}else {
			throw new RuntimeException(
							"Invalid matrix size! Matrix must be 2x2, 3x3, or 4x4. Figure yourself out.",
							new IndexOutOfBoundsException());
		}
	}

	private float[][] convertToSquareArray(float[] array1D) {
		int size = (int)sqrt(array1D.length);
		float[][] array2D = new float[size][size];
		for (int i=0; i<array1D.length; i++) {
			array2D[i/size][i%size] = array1D[i];
		}
		return array2D;
	}

	public String toString() {
		StringBuilder s = new StringBuilder();
		for (int i=0; i<size;i++) {
			s.append("| ");
			for (int j=0; j<size;j++) {
				s.append(matrixArray[i][j]).append(" ");
			}
			s.append(" |\n");
		}
		return s.toString();
	}

	public float[][] getMatrixArray() {
		return matrixArray;
	}

	public void setMatrixArray(float[] matrixArray) {
		if (sqrt(matrixArray.length) != size) {
			throw new RuntimeException(String.format(
							"Invalid matrix size! Matrix must be %dx%d. Figure yourself out.", size, size),
							new IndexOutOfBoundsException());
		}
		this.matrixArray = convertToSquareArray(matrixArray);
	}
	public void setMatrixArray(float[][] matrixArray) {
		if (matrixArray.length != size || matrixArray[0].length != size) {
			throw new RuntimeException(String.format(
							"Invalid matrix size! Matrix must be %dx%d. Figure yourself out.", size, size),
							new IndexOutOfBoundsException());
		}
		this.matrixArray = matrixArray;
	}

	public int getSize() {
		return size;
	}

	public Matrix multiplicationBy(float[] vector) {
		if (vector.length != this.size) {
			throw new RuntimeException(String.format(
							"Invalid vector size! Vector must be %dx1. Take a Linear Algebra class already.", this.size),
							new IndexOutOfBoundsException());
		}
		float[][] array2D = new float[size][size];
		for (int i=0;i<this.size;i++) {
			for (int j=0; j<this.size;j++) {
				array2D[i][j] = matrixArray[i][j];
				array2D[i][j] *= vector[j];
			}
		}
		return new Matrix(array2D);
	}
	public Matrix multiplicationBy(Matrix matrix) {
		if (matrix.getSize() != this.size) {
			throw new RuntimeException(String.format(
							"Invalid matrix size! Matrix must be %dx%d. Take a Linear Algebra class already.", size, size),
							new IndexOutOfBoundsException());
		}

		float[][] array2D = new float[size][size];

		for (int i=0;i<this.size;i++) {
			for (int j=0; j<this.size;j++) {
				float placeholder = 0f;
				for (int k=0; k<this.size;k++) {
					placeholder += matrixArray[i][k] * matrix.getMatrixArray()[k][j];
				}
				array2D[i][j] = placeholder;
			}
		}
		return new Matrix(array2D);
	}

	public Matrix moveBy(float x, float y, float z) {
		float[][] fa = getMatrixArray();
		fa[0][3] += x;
		fa[1][3] += y;
		fa[2][3] += z;
		return new Matrix(fa);
	}

	public Matrix translateToOrigin() {
		float[][] translationMatrix = matrixArray;
		translationMatrix[0][2] *= -1;
		translationMatrix[1][2] *= -1;
		return this.multiplicationBy(new Matrix(translationMatrix));
	}
	public Matrix scaleMatrix(float scaleX, float scaleY, float scaleZ) {
		Matrix scalarMatrix = new Matrix(new float[]{
						scaleX, 0f,     0f, 0f,
						0f,     scaleY, 0f, 0f,
						0f,     0f,     scaleZ, 0f,
						0f, 0f, 0f, 1f});
		return this.multiplicationBy(scalarMatrix);
	}
	//TODO MAKE THIS WORK IN 3D
	public Matrix rotateMatrixX(double degrees) {
		float pi180 = (float)(PI/180);
		Matrix rotationX = new Matrix(new float[]{
						1f, 0f, 0f, 0f,
						0f, (float) cos(degrees*pi180), (float) (-1*sin(degrees*pi180)), 0f,
						0f, (float) sin(degrees*pi180), (float) cos(degrees*pi180), 0f,
						0f, 0f, 0f, 1f});
		return rotationX.multiplicationBy(this);
	}
	public Matrix rotateMatrixY(double degrees) {
		float pi180 = (float)(PI/180);
		Matrix rotationY = new Matrix(new float[]{
						(float) cos(degrees*pi180), 0f, (float) sin(degrees*pi180), 0f,
						0f, 1f, 0f, 0f,
						(float) (-1*sin(degrees*pi180)), 0f, (float) cos(degrees*pi180), 0f,
						0f, 0f, 0f, 1f});
		return rotationY.multiplicationBy(this);
	}
	public Matrix rotateMatrixZ(double degrees) {
		float pi180 = (float)(PI/180);
		Matrix rotationZ = new Matrix(new float[]{
						(float) cos(degrees*pi180), (float) (-1*sin(degrees*pi180)), 0f, 0f,
						(float) sin(degrees*pi180), (float) cos(degrees*pi180), 0f, 0f,
						0f, 0f, 1f, 0f,
						0f, 0f, 0f, 1f});
		return rotationZ.multiplicationBy(this);
	}
	public static float round(float d, int decimalPlace) {
		return BigDecimal.valueOf(d).setScale(decimalPlace, RoundingMode.HALF_UP).floatValue();
	}

	public float getX() {
		return matrixArray[0][3];
	}
	public float getY() {
		return matrixArray[1][3];
	}
	public float getZ() {
		return matrixArray[2][3];
	}
	public void setX(float x) {
		matrixArray[0][3] = x;
	}
	public void setY(float y) {
		matrixArray[1][3] = y;
	}
	public void setZ(float z) {
		matrixArray[2][3] = z;
	}

	/*
	* Scale Matrix
	*    | ScaleX   0        0 |
	*    | 0        ScaleY   0 |
	*    | 0        0        1 |
	*
	* Translate To Origin Matrix
	*    | 1        0       -X |
	*    | 0        1       -Y |
	*    | 0        0        1 |
	*
	* Translate Back From Origin Matrix
	*    | 1        0       +X |
	*    | 0        1       +Y |
	*    | 0        0        1 |
	*
	* */
}
