/*
 * File: MidpointFindingKarel.java 
 */

import stanford.karel.*;

public class MidpointFindingKarel extends SuperKarel {
	//Precondition:karel is standing at 1x1 cell.
		//Postcondition:karel is standing at a midpoint of first row on a beeper facin east or west(depends if map is evenxeven or oddxodd).
	 public void run() {
		 fillPartLine();
		 findsMIdPoint();
		 putBeeper();
	 }
	//Precondition:karel is standing at the end of first row facing east.
	//Postcondition:karel is standing at a midpoint facing east or west(depends if map is evenxeven or oddxodd).
	 private void findsMIdPoint() {
		 goBack();
		 while(beepersPresent()) {
			 move();
		 if(noBeepersPresent())
		 { 
			 turnAround();
			 move();
			 pickBeeper();
			 move();
		 }
		}
		 goBack();
	 }
	//Precondition:karel is standing at the end of first line facing east or near middle or in middle.
	//Postcondition:karel is standing at a second cell from last facing west or in an exact mid point facing west or east(depends if map is evenxeven or oddxodd).
	 private void goBack() {
		 turnAround();
		 move();
	 }
	 //Precondition:karel is standing at 1x1 cell facing east.
	 //Postcondition:karel is standing at the end of first row facing east and has filled part of a lane with beepers.
	 private void fillPartLine() {
		 while(frontIsClear())
		 {
			 move();
	         if (frontIsClear()) putBeeper();
		 }
	 }
}
