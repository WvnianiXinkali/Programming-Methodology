/*
 * File: PythagoreanTheorem.java 
 * Name: 
 * Section Leader: 
 * -----------------------------
 * This file is the starter file for the PythagoreanTheorem problem.
 */

import acm.program.*;

public class PythagoreanTheorem extends ConsoleProgram {
	public void run() {
		while (true) {
			int a = readInt("a: ");
			int b = readInt("b: ");
			if (a > 0 && b > 0) {
				println("c = " + pythagoreanTheorem(a, b));
				break;
			} else {
				println("The side of the triangle can not be negative or equel to 0 please try again: ");
			}
		}
	}

	// Calculates c-hypotenuse for any a and b. Where c^2=a^2+b^2 because of the theorem.
	private double pythagoreanTheorem(int a, int b) {
		double c = Math.sqrt(a * a + b * b);
		return c;
	}
}
