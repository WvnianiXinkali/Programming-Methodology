
/*
 * File: FindRange.java 
 * Name: 
 * Section Leader: 
 * --------------------
 * This file is the starter file for the FindRange problem.
 */

import acm.program.*;

public class FindRange extends ConsoleProgram {

	private static final int STOP_SYMBOL = 0;

	public void run() {
		println("This program finds the largest and smallest numbers. ");
		int a = readInt("Write an int = ");
		int smallest = a;
		int largest = a;
		// Again we have to make sure user enters a valid numbers.
		while (true) {
			if (a != 0) {
				while (a != STOP_SYMBOL) {
					if (a > largest) {
						largest = a;
					}
					if (smallest > a && a != 0)
						smallest = a;
					a = readInt("Write an int = ");
				}
				println("smallest: " + smallest);
				println("largest: " + largest);
				break;
			} else {
				println("You have entered no nubmers so we can not provide you with largest and smallest ones try again bellow !");
				a = readInt("Write an int = ");
			}
		}
	}
}
