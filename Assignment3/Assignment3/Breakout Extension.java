
/*
 * File: Breakout.java c
 * -------------------
 * Name: Giorgi Javakhishvili
 * Section Leader: Firuz Murgelidze
 * 
 * This file will eventually implement the game of Breakout.
 */

import acm.graphics.*; 
import acm.program.*;
import acm.util.*;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class Breakout extends GraphicsProgram {

	/** Width and height of application window in pixels */
	public static final int APPLICATION_WIDTH = 400;
	public static final int APPLICATION_HEIGHT = 600;

	/** Dimensions of game board (usually the same) */
	private static final int WIDTH = APPLICATION_WIDTH;
	private static final int HEIGHT = APPLICATION_HEIGHT;

	/** Dimensions of the paddle */
	private static final int PADDLE_WIDTH = 60;
	private static final int PADDLE_HEIGHT = 10;

	/** Offset of the paddle up from the bottom */
	private static final int PADDLE_Y_OFFSET = 30;

	/** Number of bricks per row */
	private static final int NBRICKS_PER_ROW = 10;

	/** Number of rows of bricks */
	private static final int NBRICK_ROWS = 10;

	/** Separation between bricks */
	private static final int BRICK_SEP = 4;

	/** Width of a brick */
	private static final int BRICK_WIDTH = 
			(WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

	/** Height of a brick */
	private static final int BRICK_HEIGHT = 8;

	/** Radius of the ball in pixels */
	private static final int BALL_RADIUS = 10;

	/** Offset of the top brick row from the top */
	private static final int BRICK_Y_OFFSET = 70;

	/** Number of turns */
	private static final int NTURNS = 3;
	
	AudioClip bounceClip =MediaTools.loadAudioClip("bounce.au");
	
	

	private RandomGenerator rgen = RandomGenerator.getInstance();

	/* Method: run() */
	/** Runs the Breakout program. */
	public void run() {
		setBounds(0, 0, WIDTH, HEIGHT);
		drawStartMessage();
		drawPlayerPoints(0);
		drawBricks();
		drawPaddle();
		gameEngine();
	}

	// This is a game Engine and it works for how many lives we have (NTURNS) and is whole mechanics of this game.
	private void gameEngine() {
		for (int i = NTURNS; i > 0; i--) {
			drawBall();
			// Gives ball random speed at X; 
			Vx = rgen.nextDouble(1.0, 3.0);
			if (rgen.nextBoolean(0.5))
				Vx = -Vx;
			// So here ball moves and checks paddle brick and wall collisions if any of them occurs it reacts accordingly.
			while (NBRICKS != 0 && ball.getY() + 2 * BALL_RADIUS <= HEIGHT) {
				ballAnimation();
				checkPaddleCollision();
				chekBrickCollision();
				checkWallCollision();
				moreFun();
			}
			// We need to remove ball at the end of each life because it doesn't stay under the canvas and reflect next life balls.
			remove(ball);
			// Draws a message about lives left, we need it if life is 1 and 2, if we won the game(NBRICKS == 0) we don't need any messages anymore. 
			if (i != 1 && NBRICKS != 0)
				drawTurnStartMessage(i - 1);
			// If i == 1 we don't have any lives left so this informs us that we lost the game.
			if (i == 1) {
				removeAll();
				drawYouLoose();
			}
			// Player wins if there is no bricks left to Handle so this informs us if we won or not.
			if (NBRICKS == 0) {
				removeAll();
				drawYouWin();
				break;
			}
			//reset.
			NPADDLE_REFLECTIONS = 1;
			Vy = 3;
		}
	}

	
	// This is a paddle move secret.
	public void mouseMoved(MouseEvent e) {
		if (e.getX() < WIDTH - PADDLE_WIDTH / 2 && e.getX() > PADDLE_WIDTH / 2) {
			paddle.setLocation(e.getX() - PADDLE_WIDTH / 2, paddle.getY());
		}
	}

	// This is a ball move animation.
	private void ballAnimation() {
		ball.move(Vx, Vy);
		pause(PAUSE);
	}

	private void moreFun() {
		// acceleration.
		double a = rgen.nextDouble(1.0, 3.0);
		if(NPADDLE_REFLECTIONS % SPEED_INCREASE_MOMENTUM == 0) {
			if(Vy > 0 && Vx > 0) {
				Vy += a;
				Vx = rgen.nextDouble(1.0, 3.0);
			} else {
				Vy -= a;
				Vx = -rgen.nextDouble(1.0, 3.0);
			}
			NPADDLE_REFLECTIONS++;
		}
	}
	
	// This Method checks if a ball hit a wall and reflects its self.
	private void checkWallCollision() {
		if (ball.getX() + 2 * BALL_RADIUS >= WIDTH || ball.getX() <= 0) {
			Vx = -Vx;
		}
		if (ball.getY() <= 0) {
			Vy = -Vy;
		}
	}

	// This Method checks if ball found a paddle on its way and reflects its self.
	private void checkPaddleCollision() {
		GObject collider = getCollidingObject();
		if (collider == paddle) {
			// This move function does so ball will never be stuck in a paddle when its a sticky situation, like paddle being too fast at crushing a ball.
			ball.move(0, HEIGHT - ball.getY() - PADDLE_HEIGHT - PADDLE_Y_OFFSET - 2 * BALL_RADIUS);
			if(NPADDLE_REFLECTIONS == 1) Vx = rgen.nextDouble(1.0, 3.0);
			// PLayer is able to control ball much better with this.
			if (ball.getX() + BALL_RADIUS < paddle.getX() + PADDLE_WIDTH / 2) {
				Vx = -Vx;
			} 
			Vy = -Vy;
			NPADDLE_REFLECTIONS++;
			bounceClip.play();
		}
	}

	// This Method checks if ball hit a brick and removes it as well as reflects a ball and removing brick of course takes one from brick count.
	private void chekBrickCollision() {
		GObject collider = getCollidingObject();
		if (collider != paddle && collider != null) {
			remove(collider);
			// Adds points to player according to a color that the ball hit.
			if(collider.getColor() == Color.RED) {
				PLAYER_POINTS += REDPOINT;
			}
			if(collider.getColor() == Color.ORANGE) {
				PLAYER_POINTS += ORANGEPOINT;
			}
			if(collider.getColor() == Color.YELLOW) {
				PLAYER_POINTS += YELLOWPOINT;
			}
			if(collider.getColor() == Color.GREEN) {
				PLAYER_POINTS += GREENPOINT;
			}
			if(collider.getColor() == Color.CYAN) {
				PLAYER_POINTS += CYANPOINT;
			}
			remove(points);
			drawPlayerPoints(PLAYER_POINTS);
			Vy = -Vy;
			NBRICKS--;
			bounceClip.play();
		}
	}
	
	// This Method returns collision object that ball finds, otherwise null.
	private GObject getCollidingObject() {
		if (getElementAt(ball.getX(), ball.getY()) != null) {
			return getElementAt(ball.getX(), ball.getY());
		}
		if (getElementAt(ball.getX() + 2 * BALL_RADIUS, ball.getY()) != null) {
			return getElementAt(ball.getX() + 2 * BALL_RADIUS, ball.getY());
		}
		if (getElementAt(ball.getX(), ball.getY() + 2 * BALL_RADIUS) != null) {
			return getElementAt(ball.getX(), ball.getY() + 2 * BALL_RADIUS);
		}
		if (getElementAt(ball.getX() + 2 * BALL_RADIUS, ball.getY() + 2 * BALL_RADIUS) != null) {
			return getElementAt(ball.getX() + 2 * BALL_RADIUS, ball.getY() + 2 * BALL_RADIUS);
		}
		return null;
	}
	
	// This Method draws a ball in the centre of the canvas.
	private void drawBall() {
		ball = new GOval(WIDTH / 2 - BALL_RADIUS, HEIGHT / 2 - BALL_RADIUS, 2 * BALL_RADIUS, 2 * BALL_RADIUS);
		ball.setFilled(true);
		add(ball);
	}

	// This Method just draws a paddle and adds MouseListeners to the program.
	private void drawPaddle() {
		paddle = new GRect(WIDTH / 2 - PADDLE_WIDTH / 2, HEIGHT - PADDLE_HEIGHT - PADDLE_Y_OFFSET, PADDLE_WIDTH, PADDLE_HEIGHT);
		paddle.setFilled(true);
		add(paddle);
		addMouseListeners();
	}

	// This Method sets up bricks for the game.
	private void drawBricks() {
		for (int j = 0; j < NBRICK_ROWS; j++) {
			for (int i = 0; i < NBRICKS_PER_ROW; i++) {
				GRect bricks = new GRect(WIDTH / 2 - (BRICK_WIDTH + BRICK_SEP) * NBRICKS_PER_ROW / 2 + i * (BRICK_WIDTH + BRICK_SEP), BRICK_Y_OFFSET + BRICK_HEIGHT + j * (BRICK_SEP + BRICK_HEIGHT), BRICK_WIDTH, BRICK_HEIGHT);
				bricks.setFilled(true);
				bricks.setColor(Color.RED);
				// This for loop makes colouring bricks general, so it doesn't matter you have 10 or 15 rows it all will be coloured as in the instructions.
				for (int h = 0; h < NBRICK_ROWS; h++) {
					if (j / 2 == 1 + 5 * h) {
						bricks.setColor(Color.ORANGE);
					}
					if (j / 2 == 2 + 5 * h) {
						bricks.setColor(Color.YELLOW);
					}
					if (j / 2 == 3 + 5 * h) {
						bricks.setColor(Color.GREEN);
					}
					if (j / 2 == 4 + 5 * h) {
						bricks.setColor(Color.CYAN);
					}
				}
				add(bricks);
				NBRICKS++;
			}
		}
	}

	// Draws a message that informs a player that he is going to play a BREAKOUT and starts after a click.
	private void drawStartMessage() {
		GLabel gameStart = new GLabel("Start !");
		gameStart.setFont("Lucida Blackletter-28");
		add(gameStart, WIDTH / 2 - gameStart.getWidth() / 2, HEIGHT / 2 - gameStart.getAscent() / 2 + DISTANCE_LABEL / 2);
		GLabel gameName = new GLabel("BREAKOUT");
		gameName.setFont("Lucida Blackletter-28");
		gameName.setColor(Color.BLACK);
		add(gameName, WIDTH / 2 - gameName.getWidth() / 2, HEIGHT / 2 - gameName.getAscent() / 2 - DISTANCE_LABEL / 2);
		waitForClick();
		remove(gameName);
		remove(gameStart);
	}

	// Draws a message that player has left x lives left.
	private void drawTurnStartMessage(int life) {
		GLabel start = new GLabel("You have left " + life + " lives !");
		start.setFont("Lucida Blackletter-28");
		add(start, WIDTH / 2 - start.getWidth() / 2, HEIGHT / 2 - start.getAscent() / 2);
		GLabel tip = new GLabel("Click anywhere to continue");
		tip.setFont("Lucida Blackletter-28");
		add(tip, WIDTH / 2 - tip.getWidth() / 2, HEIGHT / 2 + DISTANCE_LABEL);
		waitForClick();
		remove(start);
		remove(tip);
	}

	// Draws player points on a canvas.
	private void drawPlayerPoints(int Point) {
		points = new GLabel("Points: " + Point);
		points.setFont("Lucida Blackletter-20");
		add(points,0, HEIGHT + points.getAscent());
		
	}
	
	// Draws a message that player has lost the game.
	private void drawYouLoose() {
		GLabel loose = new GLabel("You Lost !");
		loose.setFont("Lucida Blackletter-28");
		loose.setColor(Color.RED);
		add(loose, WIDTH / 2 - loose.getWidth() / 2, HEIGHT / 2 - loose.getAscent() / 2);
		
		GLabel loosePoints = new GLabel("Your Points: " + PLAYER_POINTS);
		loosePoints.setFont("Lucida Blackletter-20");
		add(loosePoints, WIDTH / 2 - loosePoints.getWidth() / 2, HEIGHT / 2 + loosePoints.getAscent() / 2 + DISTANCE_LABEL/2);
	}
	
	// Draws a message that player has won the game.
	private void drawYouWin() {
		GLabel win = new GLabel("Congrats, You Won !");
		win.setFont("Lucida Blackletter-28");
		win.setColor(Color.GREEN);
		add(win, WIDTH / 2 - win.getWidth() / 2, HEIGHT / 2 - win.getAscent() / 2);
		
		GLabel winPoints = new GLabel("Your Points: " + PLAYER_POINTS);
		winPoints.setFont("Lucida Blackletter-20");
		add(winPoints, WIDTH / 2 - winPoints.getWidth() / 2, HEIGHT / 2 + winPoints.getAscent() / 2 + DISTANCE_LABEL/2);
	}
	
	
	private GLabel points;
	private GOval ball;
	private GRect paddle;
	private GLabel life;

	private int DISTANCE_LABEL = 70;
	private double PAUSE = 15;
	private double Vx = 0;
	private double Vy = 3;
	private int NBRICKS = 0;
	private int NPADDLE_REFLECTIONS = 1;
	private int SPEED_INCREASE_MOMENTUM = 7;
	
	private int REDPOINT = 5;
	private int ORANGEPOINT = 4;
	private int YELLOWPOINT = 3;
	private int GREENPOINT = 2;
	private int CYANPOINT = 1;
	
	private int PLAYER_POINTS = 0;
}
