/*
 * File: StoneMasonKarel.java 
 */

import stanford.karel.*;

public class StoneMasonKarel extends SuperKarel {
//Precondition:karel is standing at 1x1 cell,facing east.
//Postcondition:karel is standing at a bottom right corner of the map, facing east and 1,4,9... rows are covered with diamonds.
	public void run(){
		while(frontIsClear())
		{
			fillOneHalf();
		}
		fillOneHalf();
	}
	//Precondition:karel is standing at the start of a row that's number is 1,5,9..., facing east.
	//Postcondition:karel is standing on a next row, facing east and has filled last row.
	private void fillOneHalf(){
		fillLine();
		transition();
	}
	//Precondition:karel is standing at a start of 1,5,9... row facing east.
	//Postcondition:karel is standing at the end of that row facing north and has covered that row with diamonds.
	private void fillLine() {
		turnLeft();
		if(!beepersPresent())
			putBeeper();
		while(frontIsClear())
		{
			move();
			if(!beepersPresent())
			putBeeper();
			
		}
	}
	//Precondition:karel is standing at the end of 1,5,9..., facing north.
	//Postcondition:karel is standing at th start of a next row, facing east.
	private void transition() {
		turnAround();
		while(frontIsClear()) {
			move();
		}
		turnLeft();
		if(frontIsClear())
		{
			for(int i=0;i<4;i++) move();
	   }
	}
}
