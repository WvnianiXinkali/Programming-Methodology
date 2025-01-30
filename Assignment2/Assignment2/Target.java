/*
 * File: Target.java 
 * Name: 
 * Section Leader: 
 * -----------------
 * This file is the starter file for the Target problem.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class Target extends GraphicsProgram {	
	
	private static final double RADIUS_OF_BIGGEST_CIRCLE_CM = 2.54;
	private static final double RADIUS_OF_MIDDLE_CIRCLE_CM  = 1.65;
	private static final double RADIUS_OF_SMALLEST_CIRCLE_CM  = 0.76;
	private static final double RADIUS_OF_BIGGEST_CIRCLE_PIXEL = 72;
	
	public void run() {
		drawBigCircle();
		drawMiddleCircle();
		drawLittleCircle();
	}
	//Transfers sizes from CM to pixels.
	private double cmToPixel( double x ) {
		x = x*RADIUS_OF_BIGGEST_CIRCLE_PIXEL/RADIUS_OF_BIGGEST_CIRCLE_CM;
		return x;
	}
	//This function just draws biggest circle.
	private void drawBigCircle() {
		GOval bigCircle = new GOval(getWidth()/2-RADIUS_OF_BIGGEST_CIRCLE_PIXEL,getHeight()/2-RADIUS_OF_BIGGEST_CIRCLE_PIXEL,2*RADIUS_OF_BIGGEST_CIRCLE_PIXEL,2*RADIUS_OF_BIGGEST_CIRCLE_PIXEL);
		bigCircle.setFilled(true);
		bigCircle.setColor(Color.red);
		add(bigCircle);
	}
	//This function draws middle white circle.
	private void drawMiddleCircle() {
		GOval middleCircle = new GOval(getWidth()/2-cmToPixel( RADIUS_OF_MIDDLE_CIRCLE_CM ),getHeight()/2-cmToPixel( RADIUS_OF_MIDDLE_CIRCLE_CM ),2*cmToPixel( RADIUS_OF_MIDDLE_CIRCLE_CM ),2*cmToPixel( RADIUS_OF_MIDDLE_CIRCLE_CM ));
		middleCircle.setFilled(true);
		middleCircle.setColor(Color.white);
		add(middleCircle);
	}
	//This function draws smallest red circle. 
	private void drawLittleCircle() {
		GOval littleCircle = new GOval(getWidth()/2-cmToPixel( RADIUS_OF_SMALLEST_CIRCLE_CM ),getHeight()/2-cmToPixel( RADIUS_OF_SMALLEST_CIRCLE_CM ),2*cmToPixel( RADIUS_OF_SMALLEST_CIRCLE_CM ),2*cmToPixel( RADIUS_OF_SMALLEST_CIRCLE_CM ));
		littleCircle.setFilled(true);
		littleCircle.setColor(Color.red);
		add(littleCircle);
	}
}
