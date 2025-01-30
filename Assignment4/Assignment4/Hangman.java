/*
 * File: Hangman.java
 * ------------------
 * This program will eventually play the Hangman game from
 * Assignment #4.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.awt.*;

public class Hangman extends ConsoleProgram {

	private String guessWord;
	private String guessedWord;
	private int guessNum = 8;
	
	private static final int ASCII_START_LITTLE = 97;
	private static final int ASCII_END_LITTLE = 122;
	private static final int ASCII_START_BIG = 65;
	private static final int ASCII_END_BIG = 90;

	private RandomGenerator rgen = RandomGenerator.getInstance();
	private HangmanLexicon lexicon = new HangmanLexicon();
	
	private HangmanCanvas canvas;

	public void init() { 
		canvas = new HangmanCanvas(); 
		add(canvas); 
	} 
	
	public void run() {
		gameCreation();
		canvas.reset();
		canvas.displayWord(guessedWord);
		gameEngine();
	}

	private void gameEngine() {
		while (true) {
			String input = readLine("Your guess: ");
			while (input.length() < 1) {
				input = readLine("Your guess: ");
			}
			input = input.toUpperCase();
			while (inputChecker(input)) {
				if (rightGuessOrNot(input)) {
					println("That guess is correct !");
					changedGuessedWord(input);
					canvas.displayWord(guessedWord);
					if (guessWord.compareTo(guessedWord) == 0) {
						println("You guessed the word: " + guessWord);
						println("You win.");
						break;
					}
					println("The word now looks like this: " + guessedWord);
					if (guessNum != 1) {
						println("You have " + guessNum + " guesses left.");
					} else {
						println("You have only one guess left.");
					}
				} else {
					println("There are no " + input + "'s in the word.");
					changedGuessedWord(input);
					guessNum--;
					canvas.noteIncorrectGuess(input.charAt(0), guessNum);
					if (guessNum == 0) {
						println("You are completely hung.");
						println("The word was: " + guessWord);
						println("You loose.");
						break;
					}
					println("The word now looks like this: " + guessedWord);
					if (guessNum != 1) {
						println("You have " + guessNum + " guesses left.");
					} else {
						println("You have only one guess left.");
					}
				}
				input = readLine("Your guess: ");
				input = input.toUpperCase();
			}
			if (!((guessWord.compareTo(guessedWord) == 0) || (guessNum == 0))) {
				println("Please enter a valid value ");
			} else {
				break;
			}
		}
	}

	private void gameCreation() {
		println("Welcome to Hangman !");
		guessWord = wordChooser();
		guessedWord = wordStartingLook();
		println("You have " + guessNum + " guesses left.");
	}

	private String wordStartingLook() {
		String result = "";
		for (int i = 0; i < guessWord.length(); i++) {
			result += "-";
		}
		println("The word now looks like this: " + result);
		return result;
	}

	private boolean inputChecker(String input) {
		if(!((input.charAt(0) >= ASCII_START_LITTLE && input.charAt(0) <= ASCII_END_LITTLE) || (input.charAt(0) >= ASCII_START_BIG && input.charAt(0) <= ASCII_END_BIG))){
			return false;
		}
		if(input.length() != 1) {
			return false;
		}
		return true;
	}
	
	private boolean rightGuessOrNot(String input) {
		input = input.toUpperCase();
		for(int i = 0;i < guessWord.length();i++) {
			if(guessWord.charAt(i) == input.charAt(0)) {
				return true;
			}
		}
		return false;
	}
	
	private void changedGuessedWord(String input) {
		String result = "";
		for (int i = 0; i < guessWord.length(); i++) {
			if(guessWord.charAt(i) == input.charAt(0)) {
				result += input.charAt(0);
			} else {
				result += guessedWord.charAt(i);
			}
		}
		guessedWord = result;
	}
	
	private String wordChooser() {
		return (lexicon.getWord(rgen.nextInt(0, lexicon.getWordCount())));
	}
}
