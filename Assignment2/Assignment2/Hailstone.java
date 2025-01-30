/*
 * File: Hailstone.java 
 * Name: 
 * Section Leader: 
 * --------------------
 * This file is the starter file for the Hailstone problem.
 */

import acm.program.*;

public class Hailstone extends ConsoleProgram {
	public void run() {
		int counter = 0;
		int n = readInt("Enter a number:");
		//We need to make sure user enter a valid number.
		while (true) {
			if (n <= 0) {
				println("Please enter positive integer below ! ");
				n = readInt("Enter a number:");
			}
			while (n > 1) {
				if (n % 2 == 1) {
					println(n + " is odd, so i make 3n+1: " + (n * 3 + 1));
					n = n * 3 + 1;
				} else {
					println(n + " is even, so i take half: " + n / 2);
					n /= 2;
				}
				counter++;
			}
			if (n == 1) {
				println("The process took " + counter + " steps ");
				break;
			}
		}
	}
}

