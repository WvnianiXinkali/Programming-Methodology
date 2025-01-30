/*
 * File: HangmanCanvas.java 
 * ------------------------
 * This file keeps track of the Hangman display.
 */

import acm.graphics.*;
import acm.util.ErrorException;

public class HangmanCanvas extends GCanvas {

	private GLabel guessWordIsLike = null;
	private String lettersNotInWord = "";
	private GLabel lettersNotInWordObj = null;
	private static final int HEIGHT = 400;
	private static final int WIDTH = 400;
	
/** Resets the display so that only the scaffold appears */
	public void reset() {
		removeAll();
		drawScaffold();
	}
	
	private void drawScaffold() {
		GLine scaffold = new GLine(WIDTH/2 - BEAM_LENGTH, HEIGHT/2 + SCAFFOLD_HEIGHT/2, WIDTH/2 - BEAM_LENGTH, HEIGHT/2 - SCAFFOLD_HEIGHT/2);
		add(scaffold);
		GLine beam = new GLine(WIDTH/2 - BEAM_LENGTH, HEIGHT/2 - SCAFFOLD_HEIGHT/2, WIDTH/2 , HEIGHT/2 - SCAFFOLD_HEIGHT/2);
		add(beam);
		GLine rope = new GLine(WIDTH/2 ,HEIGHT/2 - SCAFFOLD_HEIGHT/2, WIDTH/2, HEIGHT/2 - SCAFFOLD_HEIGHT/2 + ROPE_LENGTH);
		add(rope);
	}
/**
 * Updates the word on the screen to correspond to the current
 * state of the game.  The argument string shows what letters have
 * been guessed so far; unguessed letters are indicated by hyphens.
 */
	public void displayWord(String word) {
		if(guessWordIsLike != null) {
			remove(guessWordIsLike);
		}
		guessWordIsLike = new GLabel(word, WIDTH/2 - BEAM_LENGTH, HEIGHT/2 + SCAFFOLD_HEIGHT/2 + 50);
		guessWordIsLike.setFont("ITALIC-30");
		add(guessWordIsLike);
	}

/**
 * Updates the display to correspond to an incorrect guess by the
 * user.  Calling this method causes the next body part to appear
 * on the scaffold and adds the letter to the list of incorrect
 * guesses that appears at the bottom of the window.
 */
	public void noteIncorrectGuess(char letter, int count) {
		if(lettersNotInWordObj != null) {
			remove(lettersNotInWordObj);
		}
		lettersNotInWord += letter;
		switch (count) {
		case 0: GLine rightFoot = new GLine(WIDTH/2 + HIP_WIDTH, HEIGHT/2 - SCAFFOLD_HEIGHT/2 + ROPE_LENGTH + 2*HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH, WIDTH/2 + HIP_WIDTH + FOOT_LENGTH, HEIGHT/2 - SCAFFOLD_HEIGHT/2 + ROPE_LENGTH + 2*HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH);
				add(rightFoot);
				break;
		case 1: GLine leftFoot = new GLine(WIDTH/2 - HIP_WIDTH, HEIGHT/2 - SCAFFOLD_HEIGHT/2 + ROPE_LENGTH + 2*HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH, WIDTH/2 - HIP_WIDTH - FOOT_LENGTH, HEIGHT/2 - SCAFFOLD_HEIGHT/2 + ROPE_LENGTH + 2*HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH);
				add(leftFoot);
				break;
		case 2: GLine rightLeg = new GLine(WIDTH/2, HEIGHT/2 - SCAFFOLD_HEIGHT/2 + ROPE_LENGTH + 2*HEAD_RADIUS + BODY_LENGTH, WIDTH/2 + HIP_WIDTH, HEIGHT/2 - SCAFFOLD_HEIGHT/2 + ROPE_LENGTH + 2*HEAD_RADIUS + BODY_LENGTH);
				add(rightLeg);
				GLine rightLeg2 = new GLine(WIDTH/2 + HIP_WIDTH, HEIGHT/2 - SCAFFOLD_HEIGHT/2 + ROPE_LENGTH + 2*HEAD_RADIUS + BODY_LENGTH, WIDTH/2 + HIP_WIDTH, HEIGHT/2 - SCAFFOLD_HEIGHT/2 + ROPE_LENGTH + 2*HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH);
				add(rightLeg2);
				break;
		case 3: GLine leftLeg = new GLine(WIDTH/2, HEIGHT/2 - SCAFFOLD_HEIGHT/2 + ROPE_LENGTH + 2*HEAD_RADIUS + BODY_LENGTH, WIDTH/2 - HIP_WIDTH, HEIGHT/2 - SCAFFOLD_HEIGHT/2 + ROPE_LENGTH + 2*HEAD_RADIUS + BODY_LENGTH);
				add(leftLeg);
				GLine leftLeg2 = new GLine(WIDTH/2 - HIP_WIDTH, HEIGHT/2 - SCAFFOLD_HEIGHT/2 + ROPE_LENGTH + 2*HEAD_RADIUS + BODY_LENGTH, WIDTH/2 - HIP_WIDTH, HEIGHT/2 - SCAFFOLD_HEIGHT/2 + ROPE_LENGTH + 2*HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH);
				add(leftLeg2);
				break;
		case 4: GLine rightArm = new GLine(WIDTH/2, HEIGHT/2 - SCAFFOLD_HEIGHT/2 + ROPE_LENGTH + 2*HEAD_RADIUS + ARM_OFFSET_FROM_HEAD, WIDTH/2 + UPPER_ARM_LENGTH, HEIGHT/2 - SCAFFOLD_HEIGHT/2 + ROPE_LENGTH + 2*HEAD_RADIUS + ARM_OFFSET_FROM_HEAD);
				add(rightArm);
				GLine rightHand = new GLine(WIDTH/2 + UPPER_ARM_LENGTH, HEIGHT/2 - SCAFFOLD_HEIGHT/2 + ROPE_LENGTH + 2*HEAD_RADIUS + ARM_OFFSET_FROM_HEAD,  WIDTH/2 + UPPER_ARM_LENGTH, HEIGHT/2 - SCAFFOLD_HEIGHT/2 + ROPE_LENGTH + 2*HEAD_RADIUS + ARM_OFFSET_FROM_HEAD + LOWER_ARM_LENGTH);
				add(rightHand);
				break;
		case 5: GLine leftArm = new GLine(WIDTH/2, HEIGHT/2 - SCAFFOLD_HEIGHT/2 + ROPE_LENGTH + 2*HEAD_RADIUS + ARM_OFFSET_FROM_HEAD ,WIDTH/2 - UPPER_ARM_LENGTH, HEIGHT/2 - SCAFFOLD_HEIGHT/2 + ROPE_LENGTH + 2*HEAD_RADIUS + ARM_OFFSET_FROM_HEAD);
				add(leftArm);
				GLine leftHand = new GLine(WIDTH/2 - UPPER_ARM_LENGTH, HEIGHT/2 - SCAFFOLD_HEIGHT/2 + ROPE_LENGTH + 2*HEAD_RADIUS + ARM_OFFSET_FROM_HEAD, WIDTH/2 - UPPER_ARM_LENGTH, HEIGHT/2 - SCAFFOLD_HEIGHT/2 + ROPE_LENGTH + 2*HEAD_RADIUS + ARM_OFFSET_FROM_HEAD + LOWER_ARM_LENGTH);
				add(leftHand);
				break;
		case 6: GLine body = new GLine(WIDTH/2, HEIGHT/2 - SCAFFOLD_HEIGHT/2 + ROPE_LENGTH + 2*HEAD_RADIUS,WIDTH/2, HEIGHT/2 - SCAFFOLD_HEIGHT/2 + ROPE_LENGTH + 2*HEAD_RADIUS + BODY_LENGTH);
				add(body);
		case 7: GOval head = new GOval(WIDTH/2 - HEAD_RADIUS, HEIGHT/2 - SCAFFOLD_HEIGHT/2 + ROPE_LENGTH, 2*HEAD_RADIUS, 2*HEAD_RADIUS );
				add(head);
				break;
		default: break;
	}
		lettersNotInWordObj = new GLabel(lettersNotInWord, WIDTH/2 - BEAM_LENGTH, HEIGHT/2 + SCAFFOLD_HEIGHT/2 + 80);
		lettersNotInWordObj.setFont("ROMAN_BASELINE-20");
		add(lettersNotInWordObj);
	}
	
/* Constants for the simple version of the picture (in pixels) */
	private static final int SCAFFOLD_HEIGHT = 360;
	private static final int BEAM_LENGTH = 144;
	private static final int ROPE_LENGTH = 18;
	private static final int HEAD_RADIUS = 36;
	private static final int BODY_LENGTH = 144;
	private static final int ARM_OFFSET_FROM_HEAD = 28;
	private static final int UPPER_ARM_LENGTH = 72;
	private static final int LOWER_ARM_LENGTH = 44;
	private static final int HIP_WIDTH = 36;
	private static final int LEG_LENGTH = 108;
	private static final int FOOT_LENGTH = 28;

}
