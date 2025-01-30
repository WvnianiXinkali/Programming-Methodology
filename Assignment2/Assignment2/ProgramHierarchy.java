/*
 * File: ProgramHierarchy.java 
 * Name: 
 * Section Leader: 
 * ---------------------------
 * This file is the starter file for the ProgramHierarchy problem.
 */

import acm.graphics.*; 
import acm.program.*;
import java.awt.*;

public class ProgramHierarchy extends GraphicsProgram {	
	
	private static final double HEIGHT_OF_RECTANGLE = 50;
	private static final double WIDTH_OF_RECTANGLE = 150;
	private static final double BOXES_DISTANCE = 20;//Distance between boxes that are side by side.
	private static final double TOP_BOX_DISTANCE = 60; //Distance between middle box and the box on top of it, to be more exact distance between the box that holds GraphicsProgram in it and the one that holds Program in it. 
	
	public void run() {
		drawBoxes();
		drawLines();
		drawTexts();
	}
	//This function draws all the boxes needed for ProgramHierarchy
	private void drawBoxes() {
		GRect rect = new GRect(getWidth()/2-WIDTH_OF_RECTANGLE/2,getHeight()/2-HEIGHT_OF_RECTANGLE/2,WIDTH_OF_RECTANGLE,HEIGHT_OF_RECTANGLE);
		add(rect);
		GRect rect1 = new GRect(getWidth()/2-WIDTH_OF_RECTANGLE/2-WIDTH_OF_RECTANGLE-BOXES_DISTANCE,getHeight()/2-HEIGHT_OF_RECTANGLE/2,WIDTH_OF_RECTANGLE,HEIGHT_OF_RECTANGLE);
		add(rect1);
		GRect rect2 = new GRect(getWidth()/2-WIDTH_OF_RECTANGLE/2+WIDTH_OF_RECTANGLE+BOXES_DISTANCE,getHeight()/2-HEIGHT_OF_RECTANGLE/2,WIDTH_OF_RECTANGLE,HEIGHT_OF_RECTANGLE);
		add(rect2);
		GRect rect3 = new GRect(getWidth()/2-WIDTH_OF_RECTANGLE/2,getHeight()/2-HEIGHT_OF_RECTANGLE/2-TOP_BOX_DISTANCE-HEIGHT_OF_RECTANGLE,WIDTH_OF_RECTANGLE,HEIGHT_OF_RECTANGLE);
		add(rect3);
	}
	//This function draws all the lines needed for ProgramHierarchy
	private void drawLines() {
		GLine line = new GLine(getWidth()/2,getHeight()/2-HEIGHT_OF_RECTANGLE/2,getWidth()/2,getHeight()/2-HEIGHT_OF_RECTANGLE/2-TOP_BOX_DISTANCE);
		add(line);
		GLine line1 = new GLine(getWidth()/2-WIDTH_OF_RECTANGLE-BOXES_DISTANCE,getHeight()/2-HEIGHT_OF_RECTANGLE/2,getWidth()/2,getHeight()/2-HEIGHT_OF_RECTANGLE/2-TOP_BOX_DISTANCE);
		add(line1);
		GLine line2 = new GLine(getWidth()/2+WIDTH_OF_RECTANGLE+BOXES_DISTANCE,getHeight()/2-HEIGHT_OF_RECTANGLE/2,getWidth()/2,getHeight()/2-HEIGHT_OF_RECTANGLE/2-TOP_BOX_DISTANCE);
		add(line2);
	}
	//This function draws all the texts needed for ProgramHierarchy
	private void drawTexts() {
		GLabel simravle = new GLabel("Program");
		simravle.setLocation(getWidth()/2-simravle.getWidth()/2,getHeight()/2+simravle.getAscent()/2-TOP_BOX_DISTANCE-HEIGHT_OF_RECTANGLE);
		add(simravle);
		GLabel qveSimravle = new GLabel("ConsoleProgram");
		qveSimravle.setLocation(getWidth()/2-qveSimravle.getWidth()/2,getHeight()/2+qveSimravle.getAscent()/2);
		add(qveSimravle);
		GLabel qveSimravle1 = new GLabel("GraphicsProgram");
		qveSimravle1.setLocation(getWidth()/2-WIDTH_OF_RECTANGLE-BOXES_DISTANCE-qveSimravle1.getWidth()/2,getHeight()/2+qveSimravle1.getAscent()/2);
		add(qveSimravle1);
		GLabel qveSimravle2 = new GLabel("DialogProgram");
		qveSimravle2.setLocation(getWidth()/2+WIDTH_OF_RECTANGLE+BOXES_DISTANCE-qveSimravle2.getWidth()/2,getHeight()/2+qveSimravle2.getAscent()/2);
		add(qveSimravle2);
	}
}

