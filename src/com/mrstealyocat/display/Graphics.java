package com.mrstealyocat.display;

import com.mrstealyocat.Shapes.Matrix;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.stb.STBImage.*;
import static org.lwjgl.stb.STBImageResize.*;


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

	public static void drawMatrixArray(Matrix[] vertices, int[] color) {
		glLineWidth(8.0f);
		glBegin(GL_LINES);
		glColor3f(color[0],color[1],color[2]);
//		for (Matrix vertex:vertices) {
//			glVertex2f(vertex.getX(), vertex.getY());
//		}
		for (int i=0; i<vertices.length-1; i++) {
			//glVertex2f(vertices[i].getX(), vertices[i].getY());
			//glVertex2f(vertices[i+1].getX(), vertices[i+1].getY());
			glVertex3f(vertices[i].getX(), vertices[i].getY(), vertices[i].getZ());
			glVertex3f(vertices[i+1].getX(), vertices[i+1].getY(), vertices[i+1].getZ());
		}
		glEnd();
	}
	public static void drawPicture(Matrix[] vertices) {
		glBegin(GL_QUADS);
		for (int i=0; i<4; i++) {
			glVertex3f(vertices[i].getX(), vertices[i].getY(), vertices[i].getZ());
		}
		glTexCoord2f(0,1);
		glTexCoord2f(1,1);
		glTexCoord2f(1,0);
		glTexCoord2f(0,0);
		glEnd();
	}

}
