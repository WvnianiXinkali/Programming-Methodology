
/*
 * File: Yahtzee.java
 * ------------------
 * This program will eventually play the Yahtzee game.
 */

import acm.io.*;
import java.util.*;
import acm.program.*;
import acm.util.*;

public class Yahtzee extends GraphicsProgram implements YahtzeeConstants {

	public static void main(String[] args) {
		new Yahtzee().start(args);
	}

	public void run() {
		IODialog dialog = getDialog();
		nPlayers = dialog.readInt("Enter number of players");
		while(nPlayers > 4) {
			nPlayers = dialog.readInt("Enter number of players");
		}
		playerNames = new String[nPlayers];
		for (int i = 1; i <= nPlayers; i++) {
			playerNames[i - 1] = dialog.readLine("Enter name for player " + i);
		}
		display = new YahtzeeDisplay(getGCanvas(), playerNames);
		playGame();
	}

	private void playGame() {
		DICE = new int[N_DICE];
		boardMatrix = new int[TOTAL][nPlayers];
		collision = new int[TOTAL][nPlayers];
		initMatrix();
		for (int j = 0; j < N_SCORING_CATEGORIES; j++) {
			for (int i = 1; i <= nPlayers; i++) {
				display.printMessage(playerNames[i-1] + "'s turn! Click \"Roll Dice\" button to roll the dice.");
				rollForPlayer(i);
				display.printMessage("Select category for this roll");
				while (true) {
					int category = display.waitForPlayerToSelectCategory();
					if (boardMatrix[category - 1][i - 1] == 0 && category != UPPER_SCORE && category != UPPER_BONUS && category != LOWER_SCORE && category != TOTAL && collision[category - 1][i - 1] != 1) {
						checkForScore(i, category);
						display.updateScorecard(category, i, boardMatrix[category - 1][i - 1]);
						collision[category - 1][i - 1]++;
						break;
					}
				}
				countTotal(i);
			}
		}
		for (int i = 1; i <= nPlayers; i++) {
			checkForBonus(i);
			lowerLevelScore(i);
		}
		Winner();
	}

	private void rollForPlayer(int i) {
		display.waitForPlayerToClickRoll(i);
		firstRoll();
		display.printMessage("Select the dice you wish to re-roll and click \"Roll Again\".");
		display.waitForPlayerToSelectDice();
		secondNThirdRoll();
		display.printMessage("Select the dice you wish to re-roll and click \"Roll Again\".");
		display.waitForPlayerToSelectDice();
		secondNThirdRoll();
	}

	private void firstRoll() {
		for (int i = 0; i < N_DICE; i++) {
			DICE[i] = rgen.nextInt(1, 6);
		}
		display.displayDice(DICE);
	}

	private void secondNThirdRoll() {
		for (int i = 0; i < N_DICE; i++) {
			if (display.isDieSelected(i)) {
				DICE[i] = rgen.nextInt(1, 6);
			}
		}
		display.displayDice(DICE);
	}

	private void initMatrix() {
		for (int j = 0; j < TOTAL; j++) {
			for (int i = 0; i < nPlayers; i++) {
				boardMatrix[j][i] = 0;
			}
		}
	}

	private void countTotal(int player) {
		int totalScore = 0;
		for (int j = 0; j < TOTAL; j++) {
			if (j != (UPPER_BONUS - 1) && j != (UPPER_BONUS - 1) && j != (UPPER_SCORE - 1) && j != (LOWER_SCORE - 1))
				totalScore += boardMatrix[j][player - 1];
		}
		boardMatrix[TOTAL - 1][player - 1] = totalScore;
		display.updateScorecard(TOTAL, player, boardMatrix[TOTAL - 1][player - 1]);
		boardMatrix[TOTAL - 1][player - 1] = 0;
	}

	private void checkForBonus(int player) {
		int upperScoreSum = 0;
		for (int j = 0; j < SIXES; j++) {
			upperScoreSum += boardMatrix[j][player - 1];
		}
		boardMatrix[UPPER_SCORE - 1][player - 1] = upperScoreSum;
		display.updateScorecard(UPPER_SCORE, player, boardMatrix[UPPER_SCORE - 1][player - 1]);
		if (upperScoreSum >= 63) {
			boardMatrix[UPPER_BONUS - 1][player - 1] = 35;
			display.updateScorecard(UPPER_BONUS, player, boardMatrix[UPPER_BONUS - 1][player - 1]);
		} else {
			boardMatrix[UPPER_BONUS - 1][player - 1] = 0;
			display.updateScorecard(UPPER_BONUS, player, boardMatrix[UPPER_BONUS - 1][player - 1]);
		}
	}

	private void lowerLevelScore(int player) {
		int lowerScoreSum = 0;
		for (int j = 0; j > THREE_OF_A_KIND && j < CHANCE; j++) {
			lowerScoreSum += boardMatrix[j][player - 1];
		}
		boardMatrix[UPPER_SCORE - 1][player - 1] = lowerScoreSum;
		display.updateScorecard(LOWER_SCORE, player, boardMatrix[LOWER_SCORE - 1][player - 1]);
	}

	private boolean checkForThreeOrFour() {
		int count = 0;
		for (int j = 0; j < N_DICE; j++) {
			for (int i = 0; i < N_DICE; i++) {
				if (DICE[j] == DICE[i]) {
					count++;
				}
			}
			if (count  == 3 || count == 4) {
				return true;
			}
			count = 0;
		}
		return false;
	}
	
	private boolean checkForYahtzee() {
		int count = 0;
		for (int j = 0; j < N_DICE; j++) {
			for (int i = 0; i < N_DICE; i++) {
				if (DICE[j] == DICE[i]) {
					count++;
				}
			}
			if (count  == 5) {
				return true;
			}
			count = 0;
		}
		return false;
	}
	
	private boolean checkForFullHouse() {
		int count = 0;
		int count2 = 0;
		for (int j = 0; j < N_DICE; j++) {
			for (int i = 0; i < N_DICE; i++) {
				if (DICE[j] == DICE[i]) {
					count++;
				}
				if (count == 3) {
					for (int q = 0; q < N_DICE; q++) {
						for (int k = 0; k < N_DICE; k++) {
							if (DICE[q] == DICE[k]) {
								count2++;
							}
						}
						if (count2 == 2) {
							return true;
						}
						count2 = 0;
					}
				}
			}
			count = 0;
		}
		return false;
	}
	
	private boolean checkForSmallStraight() {
		int one = 0; int two = 0; int three = 0; int four = 0; int five = 0; int six = 0;
		for (int j = 0; j < N_DICE; j++) {
			if(DICE[j] == 1) {
				one = 1;
			}
			if(DICE[j] == 2) {
				two = 2;
			}
			if(DICE[j] == 3) {
				three = 3;
			}
			if(DICE[j] == 4) {
				four = 4;
			}
			if(DICE[j] == 5) {
				five = 5;
			}
			if(DICE[j] == 6) {
				six = 6;
			}
		}
		if (one != 0 && two != 0 && three != 0 && four != 0) {
			return true;
		}
		if (five != 0 && two != 0 && three != 0 && four != 0) {
			return true;
		}
		if (five != 0 && six != 0 && three != 0 && four != 0) {
			return true;
		}
		return false;
	}
	
	private boolean checkForLargeStraight() {
		int one = 0; int two = 0; int three = 0; int four = 0; int five = 0; int six = 0;
		for (int j = 0; j < N_DICE; j++) {
			if(DICE[j] == 1) {
				one = 1;
			}
			if(DICE[j] == 2) {
				two = 2;
			}
			if(DICE[j] == 3) {
				three = 3;
			}
			if(DICE[j] == 4) {
				four = 4;
			}
			if(DICE[j] == 5) {
				five = 5;
			}
			if(DICE[j] == 6) {
				six = 6;
			}
		}
		if (one != 0 && two != 0 && three != 0 && four != 0 && five != 0) {
			return true;
		}
		if (five != 0 && two != 0 && three != 0 && four != 0 && six != 0) {
			return true;
		}
		return false;
	}
	
	private void checkForScore(int player, int category) {
		int scores = 0;
		if (category >= ONES && category <= SIXES) {
				for (int i = 0; i < N_DICE; i++) {
					if (DICE[i] == category) {
						scores += category;
					}
				}
		}
		if (category == CHANCE) {
			for (int i = 0; i < N_DICE; i++) {
				scores += DICE[i];
			}
		}
		if (category == THREE_OF_A_KIND || category == FOUR_OF_A_KIND) {
			if (checkForThreeOrFour()) {
				for (int i = 0; i < N_DICE; i++) {
					scores += DICE[i];
				}
			} else {
				scores = 0;
			}
		}
		if (category == FULL_HOUSE) {
			if (checkForFullHouse()) {
				scores = 25;
			} else {
				scores = 0;
			}
		}
		if (category == SMALL_STRAIGHT) {
			if (checkForSmallStraight()) {
				scores = 30;
			} else {
				scores = 0;
			}
		}
		if (category == LARGE_STRAIGHT) {
			if (checkForLargeStraight()) {
				scores = 40;
			} else {
				scores = 0;
			}
		}
		if (category == YAHTZEE) {
			if (checkForYahtzee()) {
				scores = 50;
			} else {
				scores = 0;
			}
		}
		boardMatrix[category - 1][player - 1] = scores;
	}
	
	private void Winner() {
		int winner = 0;
		int score = 0;

		for (int i = 0; i < nPlayers; i++) {
			if (boardMatrix[TOTAL - 1][i] > score) {
				score = boardMatrix[TOTAL - 1][i];
				winner = i;
			}
		}
		display.printMessage("Congratulations, " + playerNames[winner] + ". " + "you’re the winner with a total score of " + score);
	}

	/* Private instance variables */
	private int nPlayers;
	private String[] playerNames;
	private YahtzeeDisplay display;
	private RandomGenerator rgen = new RandomGenerator();

	private int[] DICE;
	private int[][] boardMatrix;
	int[][] collision;

}
