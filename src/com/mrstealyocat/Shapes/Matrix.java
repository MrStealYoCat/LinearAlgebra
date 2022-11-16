package com.mrstealyocat.Shapes;

import static java.lang.Math.sqrt;

public class Matrix {
	private float[][] matrixArray;
	private final int size;

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

	public Matrix MultiplicationBy(float[] vector) {
		if (vector.length != this.size) {
			throw new RuntimeException(String.format(
							"Invalid vector size! Vector must be %dx1. Take a Linear Algebra class already.", this.size),
							new IndexOutOfBoundsException());
		}
		float[][] array2D = matrixArray;
		for (int i=0;i<this.size;i++) {
			for (int j=0; j<this.size;j++) {
				array2D[i][j] *= vector[j];
			}
		}
		return new Matrix(array2D);
	}

	public Matrix MultiplicationBy(Matrix matrix) {
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
}
