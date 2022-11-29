package com.mrstealyocat.Shapes;

import com.mrstealyocat.controlListeners.MouseListener;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static java.lang.Math.*;

public class Shape {

	private Matrix[] vertices;
	float posX = 0f;
	float posY = 0f;
	//private Matrix transformationMatrix;

	public Shape() {
		this.vertices = new Matrix[]{
//						new Matrix(0.2f, 0.1f),
//						new Matrix(0.1f, 0.4f),
//						new Matrix(0.3f, 0.7f)
							new Matrix(-0.3f, 0.1f),
							new Matrix(-0.3f, -0.3f),
							new Matrix(0.3f, -0.3f),
							new Matrix(0.3f, 0.1f),
							new Matrix(-0.3f, 0.1f),
							new Matrix(0f, 0.4f),
							new Matrix(0.3f, 0.1f)
		};
	}

	public Matrix[] getVertices() {
		return vertices;
	}

	public void transform() {
		float[] fa = new float[]{1, 1, -1};
		transform(fa);
	}
	public void transform(float[] floatArray) {
		for (int i=0; i<vertices.length;i++) {
			vertices[i].setX(vertices[i].getX()-posX);
			vertices[i].setY(vertices[i].getY()-posY);
			vertices[i] = vertices[i].multiplicationBy(floatArray);
			vertices[i].setX(vertices[i].getX()+posX);
			vertices[i].setY(vertices[i].getY()+posY);
		}
		//transformationMatrix.
	}
	public void transform(Matrix matrix) {
		//setCoord(coordinate.multiplicationBy(new float[]{-1f,-1f, 1f}));
		for (Matrix vector:vertices) {
			vector.multiplicationBy(matrix).getMatrixArray();
		}
	}
	public void rotate(float degrees) {
		Matrix[] verts = vertices;
		for (int i=0; i<vertices.length;i++) {
			vertices[i].setX(vertices[i].getX()-posX);
			vertices[i].setY(vertices[i].getY()-posY);
			vertices[i] = vertices[i].rotateMatrix(degrees);
			vertices[i].setX(vertices[i].getX()+posX);
			vertices[i].setY(vertices[i].getY()+posY);
		}
	}
	public void rotate() {
		rotate(10);
	}

	public void move(float x, float y) {
		posX += x;
		posY += y;
		for (int i=0; i<vertices.length;i++) {
			vertices[i] = vertices[i].moveBy(x,y);
		}
	}
}
