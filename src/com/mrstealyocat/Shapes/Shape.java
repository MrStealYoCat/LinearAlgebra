package com.mrstealyocat.Shapes;

import com.mrstealyocat.TextIO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.Math.abs;

public class Shape {

	private Matrix[] vertices;
	private String color;
	private int[] rgba;
	float posX = 0f;
	float posY = 0f;
	float posZ = 0f;

	public Shape() {
		//fileInput("src/com/mrstealyocat/Shapes/vertices.txt");
	}
	public Shape(String fileName) {
		fileInput(fileName);
	}

	public void fileInput(String filename) {
		TextIO.readFile(filename);
		List<float[]> verts = new ArrayList<>();
		String[] parts;
		this.rgba = Arrays.stream(TextIO.getln().split(" ")).mapToInt(Integer::parseInt).toArray();
		//System.out.printf("R: %d, G: %d, B: %d\n", rgba[0], rgba[1], rgba[2]);
		while (!TextIO.eof()) {
			parts = TextIO.getln().split(" ");
			verts.add(new float[]{
							Float.parseFloat(parts[0]),
							Float.parseFloat(parts[1]),
							Float.parseFloat(parts[2])
			});
		}
		Matrix[] result = new Matrix[verts.size()];

		float biggestNum = 0f;
		for (float[] vert : verts) {
			if (abs(vert[0]) > biggestNum)
				biggestNum = abs(vert[0]);
			if (abs(vert[1]) > biggestNum)
				biggestNum = abs(vert[1]);
			if (abs(vert[2]) > biggestNum)
				biggestNum = abs(vert[2]);
		}
		if (!(biggestNum <= 1)) {
			biggestNum = biggestNum * 2;
		} else {
			biggestNum = 1;
		}

		for (int i=0; i< verts.size();i++) {
			verts.set(i, new float[]{verts.get(i)[0]/biggestNum, verts.get(i)[1]/biggestNum, verts.get(i)[2]/biggestNum});
			result[i] = new Matrix(verts.get(i)[0], verts.get(i)[1], verts.get(i)[2]);
		}
		this.vertices = result;
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
			vertices[i].setZ(vertices[i].getZ()-posZ);
			vertices[i] = vertices[i].multiplicationBy(floatArray);
			vertices[i].setX(vertices[i].getX()+posX);
			vertices[i].setY(vertices[i].getY()+posY);
			vertices[i].setZ(vertices[i].getZ()+posZ);
		}
		//transformationMatrix.
	}
	public void transform(Matrix matrix) {
		//setCoord(coordinate.multiplicationBy(new float[]{-1f,-1f, 1f}));
		for (Matrix vector:vertices) {
			vector.multiplicationBy(matrix).getMatrixArray();
		}
	}
	public void rotateX(float degrees) {
		Matrix[] verts = vertices;
		for (int i=0; i<vertices.length;i++) {
			vertices[i].setX(vertices[i].getX()-posX);
			vertices[i].setY(vertices[i].getY()-posY);
			vertices[i].setZ(vertices[i].getZ()-posZ);
			vertices[i] = vertices[i].rotateMatrixX(degrees);
			vertices[i].setX(vertices[i].getX()+posX);
			vertices[i].setY(vertices[i].getY()+posY);
			vertices[i].setZ(vertices[i].getZ()+posZ);
		}
	}
	public void rotateY(float degrees) {
		Matrix[] verts = vertices;
		for (int i=0; i<vertices.length;i++) {
			vertices[i].setX(vertices[i].getX()-posX);
			vertices[i].setY(vertices[i].getY()-posY);
			vertices[i].setZ(vertices[i].getZ()-posZ);
			vertices[i] = vertices[i].rotateMatrixY(degrees);
			vertices[i].setX(vertices[i].getX()+posX);
			vertices[i].setY(vertices[i].getY()+posY);
			vertices[i].setZ(vertices[i].getZ()+posZ);
		}
	}
	public void rotateZ(float degrees) {
		Matrix[] verts = vertices;
		for (int i=0; i<vertices.length;i++) {
			vertices[i].setX(vertices[i].getX()-posX);
			vertices[i].setY(vertices[i].getY()-posY);
			vertices[i].setZ(vertices[i].getZ()-posZ);
			vertices[i] = vertices[i].rotateMatrixZ(degrees);
			vertices[i].setX(vertices[i].getX()+posX);
			vertices[i].setY(vertices[i].getY()+posY);
			vertices[i].setZ(vertices[i].getZ()+posZ);
		}
	}
//	public void rotate() {
//		rotate(10);
//	}

	public void move(float x, float y, float z) {
		posX += x;
		posY += y;
		posZ += z;

		for (int i=0; i<vertices.length;i++) {
			vertices[i] = vertices[i].moveBy(x,y,z);
		}
	}

	public String getColor() {
		return color;
	}

	public int[] getRgba() {
		return rgba;
	}
}
