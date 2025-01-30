/*
 * File: Pyramid.java
 * Name: 
 * Section Leader: 
 * ------------------
 * This file is the starter file for the Pyramid problem.
 * It includes definitions of the constants that match the
 * sample run in the assignment, but you should make sure
 * that changing these values causes the generated display
 * to change accordingly.
 */

import acm.graphics.*; 
import acm.program.*;
import java.awt.*;

public class Pyramid extends GraphicsProgram {

/** Width of each brick in pixels */
	private static final int BRICK_WIDTH = 30;

/** Width of each brick in pixels */
	private static final int BRICK_HEIGHT = 12;

/** Number of bricks in the base of the pyramid */
	private static final int BRICKS_IN_BASE = 14;
	
//This function is drawing each line of the pyramid one by one creating whole pyramid.
	public void run() {
		int x = BRICKS_IN_BASE;
		//This for draws horizontal lines at a different stage until it hits 1 brick at a top.
		for (int j = 0; j < BRICKS_IN_BASE; j++) {
			//This for draws horizontal lines each brick at a time.
			for (int i = 0; i < x; i++) {
				GRect rect = new GRect(getWidth() / 2 - BRICKS_IN_BASE * BRICK_WIDTH / 2 + i * BRICK_WIDTH + BRICK_WIDTH * j / 2,getHeight() - BRICK_HEIGHT - j * BRICK_HEIGHT, BRICK_WIDTH, BRICK_HEIGHT);
				add(rect);
			}
			x--;
		}
	}
}

