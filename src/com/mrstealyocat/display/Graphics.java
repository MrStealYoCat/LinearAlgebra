package com.mrstealyocat.display;

import com.mrstealyocat.Shapes.Matrix;

import static org.lwjgl.opengl.GL11.*;


public class Graphics {

	public static void drawGraph() {
		float unit = 0.1f;
		glLineWidth(5.0f);
		//x axis
		glBegin(GL_LINES);
		glColor3f(0.0f,0.0f,1.0f);
		glVertex2f(-1f, 0f);
		glVertex2f(1f, 0f);
		glEnd();

		//y axis
		glBegin(GL_LINES);
		glColor3f(0.0f,0.0f,1.0f);
		glVertex2f(0f, -1f);
		glVertex2f(0f, 1f);
		glEnd();

		for (float i = -1f; i < 1f; i += unit) {
			glLineWidth(3.0f);
			//x axis
			glBegin(GL_LINES);
			glColor3f(0.0f,0.0f,1.0f);
			glVertex2f(i, 0.05f);
			glVertex2f(i, -0.05f);
			glEnd();

			//y axis
			glBegin(GL_LINES);
			glColor3f(0.0f,0.0f,1.0f);
			glVertex2f(0.05f, i);
			glVertex2f(-0.05f, i);
			glEnd();
		}
	}

	public static void drawMatrixArray(Matrix[] vertices) {
		glLineWidth(10.0f);
		glBegin(GL_LINES);
		glColor3f((float)1.0,(float)0.0,(float)0.0);
		for (Matrix vertex:vertices) {
			glVertex2f(vertex.getX(), vertex.getY());
		}
		//glVertex2f(matrix1.getX(), matrix1.getY());
		//glVertex2f(matrix2.getX(), matrix2.getY());
		glEnd();
	}

}
