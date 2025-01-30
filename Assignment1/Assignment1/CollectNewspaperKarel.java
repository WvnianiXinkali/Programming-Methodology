/*
 * File: CollectNewspaperKarel.java 
 */

import stanford.karel.*;

public class CollectNewspaperKarel extends SuperKarel {
	//Precondition:karel is standing at a up left corner of her house.
	//Postcondition:karel is standing where she stood at the start but she has her magazine with her.
	public void run(){
		bringMagazine();
		putBeeper();
    }
	//Precondition:karel is standing at a up left corner of her house.
	//Postcondition:karel is standing where she stood at the start but has walked to magazine picked picked it up and walked back.
	private void bringMagazine()
	{
		turnPlace();
		walkDistance();
		pickBeeper();
		turnAround();
		walkDistance();
		turnPlace();
	}
	//Precondition:karel is standing at the start or is coming back from a magazine and is at a turnPlace.
	//Postcondition:karel had turned at a turnPlace and has finished work or has started it.
   private void turnPlace()
   {
	   turnRight();
	   move();
	   turnLeft();
   }
 //Precondition:karel is at the start of a straight line to magazine or is coming from it..
 //Postcondition:karel is on a magazine or karel is at a turnPlace.
   private void walkDistance()
   {
	   move();
	   move();
	   move();
   }
}
