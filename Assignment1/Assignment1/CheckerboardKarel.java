/*
 * File: CheckerboardKarel.java  
 */

import stanford.karel.*;

public class CheckerboardKarel extends SuperKarel {
	//Precondition: karel is standing in a left bottom corner of the world.
	//Postcondition: karel is standing either side of the world and the whole world is covered with beepers like a checkers board.
	 public void run() {
	  putBeeper();
	  oneEightWorld();
	  fillOtherWorld();
	 
	}
	 //Precondition: karel is standing at a bottom of 1X8 world.
	 //Postcondition: karel is standing at a top of 1X8 world and the world is covered with beepers like on a checkers board.
	 private void oneEightWorld()
	 {
		 if (!frontIsClear()) {
			 turnLeft();
			 fillLine();
		 }
	 }
	 //Precondition:karel is standing in a Left bottom corner of the world on a beeper.
	 //Postcondition:karel is standing either side of the world(other than 1xn world) and the whole world is covered with beepers like a checkers board.
	 private void fillOtherWorld() {
		 while(leftIsClear())
		 {
			 fillLine();
			 safeTransition();
			 fillLine();
			 turnRight();
			 if(!frontIsClear())
			 {
				 turnRight();
			 }
			 else {
				 safeTransition();
			 }
		 }
		 if(beepersPresent()) 
			 fillLine();
	 }
	 // Precondition: karel is on a beeper at a start of a line if its odd, if its even she is at a second square.
	 // Postcondition: karel is at the end of a line and has covered the line with beepers like a checkers board.
	 private void fillLine()
	 {
		 while(frontIsClear())
		 {
			 move();
			 if(frontIsClear())
			 {
			 move();
			 putBeeper();
			 }
		 }
	 }
	 // Precondition: karel is at the end of a row facing either west or east.
	 // Postcondition: karel is on a next row standing on a beeper on a first square or second.
	 private void safeTransition()
	 {
		 if (beepersPresent())
		 {
			 goUpBothSides();
			 move();
			 putBeeper();
		 }
		 else {
			 goUpBothSides();
			 putBeeper();
		 }
	 }
	 //Precondition:karel is standing at the end of any row and facing either west or east.
	 //Postcondition:if karel was facing east she is on a next line and ready to start filling it. if she was facing west she has finished her line and is facing up.
	 private void goUpBothSides()
	 {
		 if(facingEast())
		 {
			 turnLeft();
			 move();
			 turnLeft();
		 }else {
			 move();
			 turnRight();
		 }
	 }
}
