package com.mrstealyocat.display;

import static org.lwjgl.opengl.GL11.*;


public class Graphics {

	public static void draw(double posX, double posY) {
		//System.out.println(posX + ", " + posY);
//		glBegin(GL_QUADS);
//		glColor3f((float)1.0,(float)0.0,(float)0.0);
//		glVertex2f((float) (posX-0.05),(float) (posY+0.05));
//		glVertex2f((float) (posX-0.05),(float) (posY-0.05));
//		glVertex2f((float) (posX+0.05),(float) (posY-0.05));
//		glVertex2f((float) (posX+0.05),(float) (posY+0.05));
//		glEnd();


//		glLineWidth(10.0f);
//		glBegin(GL_LINES);
//		glColor3f((float)1.0,(float)0.0,(float)0.0);
//		glVertex2f(coord[0][0],coord[0][1]);
//		glVertex2f(coord[1][0],coord[1][1]);
//		glEnd();

	}

	public static void drawMatrix(float[][] coord) {
		glLineWidth(10.0f);
		glBegin(GL_LINES);
		glColor3f((float)1.0,(float)0.0,(float)0.0);
		glVertex2f(coord[0][0],coord[0][1]);
		glVertex2f(coord[1][0],coord[1][1]);
		glEnd();
	}

}
