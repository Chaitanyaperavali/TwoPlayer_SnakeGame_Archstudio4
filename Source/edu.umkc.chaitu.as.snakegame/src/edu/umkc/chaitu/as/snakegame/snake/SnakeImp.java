package edu.umkc.chaitu.as.snakegame.snake;

import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.JFrame;

import edu.umkc.chaitu.as.snakegame.Direction;
import edu.umkc.chaitu.as.snakegame.IClock;
import edu.umkc.chaitu.as.snakegame.IGameBoard;
import edu.umkc.chaitu.as.snakegame.ISidePanel;
import edu.umkc.chaitu.as.snakegame.TileType;
import edu.umkc.chaitu.as.snakegame.board.GameBoardArch;
import edu.umkc.chaitu.as.snakegame.clock.ClockArch;
import edu.umkc.chaitu.as.snakegame.side.SidePanelArch;

public class SnakeImp extends JFrame implements ISnakeImp {
	private SnakeArch _arch;

	/**
	 * The Serial Version UID.
	 */
	private static final long serialVersionUID = 6678292058307426314L;

	/**
	 * The number of milliseconds that should pass between each frame.
	 */
	private static final long FRAME_TIME = 1000L / 50L;

	/**
	 * The minimum length of the snake. This allows the snake to grow right when
	 * the game starts, so that we're not just a head moving around on the
	 * board.
	 */
	private static final int MIN_SNAKE_LENGTH = 5;

	/**
	 * The maximum number of directions that we can have polled in the direction
	 * list.
	 */
	private static final int MAX_DIRECTIONS = 3;
	/**
	 * The random number generator (used for spawning fruits).
	 */
	private Random random;

	/**
	 * Whether or not we're running a new game.
	 */
	private boolean isNewGame;

	/**
	 * Whether or not the game is over.
	 */
	private boolean isGameOver;

	/**
	 * Whether or not the game is paused.
	 */
	private boolean isPaused;

	/**
	 * The list that contains the points for the snake.
	 */
	private LinkedList<Point> snake;
	private LinkedList<Point> snakePlayer2;
	/**
	 * The list that contains the queued directions.
	 */
	private LinkedList<Direction> directions;
	private LinkedList<Direction> directionsPlayer2;
	/**
	 * The current score.
	 */
	private int score;
	private int scorePlayer2;
	String winner;
	/**
	 * The number of fruits that we've eaten.
	 */
	private int fruitsEaten;
	private int fruitsEatenPlayer2;
	private IGameBoard board;
	private ISidePanel side;
	private IClock clock;

	/**
	 * The number of points that the next fruit will award us.
	 */
	private int nextFruitScore;

	public SnakeImp() {
		// System.out.println("snakeimp constructor");
	}

	public void setArch(SnakeArch arch) {
		_arch = arch;
	}

	public SnakeArch getArch() {
		return _arch;
	}

	/*
	 * Myx Lifecycle Methods: these methods are called automatically by the
	 * framework as the bricks are created, attached, detached, and destroyed
	 * respectively.
	 */
	public void init() {
		// System.out.println("inside snakeImp");
	}
	
	public String getWinner(){
		return winner;
	}
	
	public void begin() {
		this.setTitle("Snake Game");
		setLayout(new BorderLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);

		/*
		 * Initialize the game's panels and add them to the window.
		 */
		board = (GameBoardArch) _arch.OUT_IGameBoard;
		board.initialize(this);
		side = (SidePanelArch) _arch.OUT_ISidePanel;
		side.initialize(this);
		clock = (ClockArch) _arch.OUT_IClock;
		clock.initialize(9.0f);

		/*
		 * Adds a new key listener to the frame to process input.
		 */
		addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {

				/*
				 * If the game is not paused, and the game is not over...
				 * 
				 * Ensure that the direction list is not full, and that the most
				 * recent direction is adjacent to North before adding the
				 * direction to the list.
				 */
				case KeyEvent.VK_UP:
					if (!isPaused && !isGameOver) {
						if (directionsPlayer2.size() < MAX_DIRECTIONS) {
							Direction last = directionsPlayer2.peekLast();
							if (last != Direction.South && last != Direction.North) {
								directionsPlayer2.addLast(Direction.North);
							}
						}
					}
					break;
				case KeyEvent.VK_W:
					if (!isPaused && !isGameOver) {
						if (directions.size() < MAX_DIRECTIONS) {
							Direction last = directions.peekLast();
							if (last != Direction.South && last != Direction.North) {
								directions.addLast(Direction.North);
							}
						}
					}
					break;

				/*
				 * If the game is not paused, and the game is not over...
				 * 
				 * Ensure that the direction list is not full, and that the most
				 * recent direction is adjacent to South before adding the
				 * direction to the list.
				 */
				case KeyEvent.VK_DOWN:
					if (!isPaused && !isGameOver) {
						if (directionsPlayer2.size() < MAX_DIRECTIONS) {
							Direction last = directionsPlayer2.peekLast();
							if (last != Direction.North && last != Direction.South) {
								directionsPlayer2.addLast(Direction.South);
							}
						}
					}
					break;
				case KeyEvent.VK_S:
					if (!isPaused && !isGameOver) {
						if (directions.size() < MAX_DIRECTIONS) {
							Direction last = directions.peekLast();
							if (last != Direction.North && last != Direction.South) {
								directions.addLast(Direction.South);
							}
						}
					}
					break;

				/*
				 * If the game is not paused, and the game is not over...
				 * 
				 * Ensure that the direction list is not full, and that the most
				 * recent direction is adjacent to West before adding the
				 * direction to the list.
				 */
				case KeyEvent.VK_LEFT:
					if (!isPaused && !isGameOver) {
						if (directionsPlayer2.size() < MAX_DIRECTIONS) {
							Direction last = directionsPlayer2.peekLast();
							if (last != Direction.East && last != Direction.West) {
								directionsPlayer2.addLast(Direction.West);
							}
						}
					}
					break;
				case KeyEvent.VK_A:
					if (!isPaused && !isGameOver) {
						if (directions.size() < MAX_DIRECTIONS) {
							Direction last = directions.peekLast();
							if (last != Direction.East && last != Direction.West) {
								directions.addLast(Direction.West);
							}
						}
					}
					break;

				/*
				 * If the game is not paused, and the game is not over...
				 * 
				 * Ensure that the direction list is not full, and that the most
				 * recent direction is adjacent to East before adding the
				 * direction to the list.
				 */
				case KeyEvent.VK_RIGHT:
					if (!isPaused && !isGameOver) {
						if (directionsPlayer2.size() < MAX_DIRECTIONS) {
							Direction last = directionsPlayer2.peekLast();
							if (last != Direction.West && last != Direction.East) {
								directionsPlayer2.addLast(Direction.East);
							}
						}
					}
					break;
				case KeyEvent.VK_D:
					if (!isPaused && !isGameOver) {
						if (directions.size() < MAX_DIRECTIONS) {
							Direction last = directions.peekLast();
							if (last != Direction.West && last != Direction.East) {
								directions.addLast(Direction.East);
							}
						}
					}
					break;

				/*
				 * If the game is not over, toggle the paused flag and update
				 * the logicTimer's pause flag accordingly.
				 */
				case KeyEvent.VK_P:
					if (!isGameOver) {
						isPaused = !isPaused;
						clock.setPaused(isPaused);
					}
					break;

				/*
				 * Reset the game if one is not currently in progress.
				 */
				case KeyEvent.VK_ENTER:
					if (isNewGame || isGameOver) {
						resetGame();
					}
					break;
				}
			}

		});

		/*
		 * Resize the window to the appropriate size, center it on the screen
		 * and display it.
		 */
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		startGame();
	}

	public void end() {
		// TODO Auto-generated method stub
	}

	public void destroy() {
		// TODO Auto-generated method stub
	}

	/*
	 * Implementation primitives required by the architecture
	 */
	public void startGame() {
		/*
		 * Initialize everything we're going to be using.
		 */
		this.random = new Random();
		this.snake = new LinkedList<>();
		this.snakePlayer2 = new LinkedList<>();
		this.directions = new LinkedList<>();
		this.directionsPlayer2 = new LinkedList<>();
		this.isNewGame = true;

		// Set the timer to paused initially.
		clock.setPaused(true);

		/*
		 * This is the game loop. It will update and render the game and will
		 * continue to run until the game window is closed.
		 */
		while (true) {
			// Get the current frame's start time.
			long start = System.nanoTime();

			// Update the logic timer.
			clock.update();

			/*
			 * If a cycle has elapsed on the logic timer, then update the game.
			 */
			if (clock.hasElapsedCycle()) {
				updateGame();
			}

			// Repaint the board and side panel with the new content.
			board.repaint();
			side.repaint();

			/*
			 * Calculate the delta time between since the start of the frame and
			 * sleep for the excess time to cap the frame rate. While not
			 * incredibly accurate, it is sufficient for our purposes.
			 */
			long delta = (System.nanoTime() - start) / 1000000L;
			if (delta < FRAME_TIME) {
				try {
					Thread.sleep(FRAME_TIME - delta);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Updates the game's logic.
	 */
	private void updateGame() {
		/*
		 * Gets the type of tile that the head of the snake collided with. If
		 * the snake hit a wall, SnakeBody will be returned, as both conditions
		 * are handled identically.
		 */
		TileType[] collisions = updateSnake();
		TileType collision = collisions[0];
		TileType collisionPlayer2 = collisions[1];
		/*
		 * 
		 * Here we handle the different possible collisions.
		 * 
		 * Fruit: If we collided with a fruit, we increment the number of fruits
		 * that we've eaten, update the score, and spawn a new fruit.
		 * 
		 * SnakeBody: If we collided with our tail (or a wall), we flag that the
		 * game is over and pause the game.
		 * 
		 * If no collision occurred, we simply decrement the number of points
		 * that the next fruit will give us if it's high enough. This adds a bit
		 * of skill to the game as collecting fruits more quickly will yield a
		 * higher score.
		 */
		int flag = -1;

		if (collision == TileType.Fruit) {
			fruitsEaten++;
			score += nextFruitScore;
			spawnFruit();
			flag = 1;
		}
		if (collisionPlayer2 == TileType.Fruit) {
			fruitsEatenPlayer2++;
			scorePlayer2 += nextFruitScore;
			spawnFruit();
			flag = 1;
		}
		if (collision == TileType.SnakeBody) {
			isGameOver = true;
			clock.setPaused(true);
			winner = "Player 2";
			flag = 1;
		}
		if (collisionPlayer2 == TileType.SnakeBody) {
			isGameOver = true;
			clock.setPaused(true);
			winner = "Player 1";
			flag = 1;
		}
		if (nextFruitScore > 10 && flag == -1) {
			nextFruitScore--;
		}
		
		if(fruitsEaten >= 5){
			isGameOver = true;
			clock.setPaused(true);
			winner = "Player 1";
		}
		if(fruitsEatenPlayer2 >= 5){
			isGameOver = true;
			clock.setPaused(true);
			winner = "Player 2";
		}

	}

	/**
	 * Updates the snake's position and size.
	 * 
	 * @return Tile tile that the head moved into.
	 */
	private TileType[] updateSnake() {
		TileType[] result = new TileType[2];
		/*
		 * Here we peek at the next direction rather than polling it. While not
		 * game breaking, polling the direction here causes a small bug where
		 * the snake's direction will change after a game over (though it will
		 * not move).
		 */
		Direction direction = directions.peekFirst();
		Direction directionPlayer2 = directionsPlayer2.peekFirst();
		// System.out.println(direction);
		/*
		 * Here we calculate the new point that the snake's head will be at
		 * after the update.
		 */
		Point head = new Point(snake.peekFirst());
		Point headPlayer2 = new Point(snakePlayer2.peekFirst());

		switch (direction) {
		case North:
			head.y--;
			break;

		case South:
			head.y++;
			break;

		case West:
			head.x--;
			break;

		case East:
			head.x++;
			break;
		}

		switch (directionPlayer2) {
		case North:
			headPlayer2.y--;
			break;

		case South:
			headPlayer2.y++;
			break;

		case West:
			headPlayer2.x--;
			break;

		case East:
			headPlayer2.x++;
			break;
		}

		int flag = -1;

		/*
		 * If the snake has moved out of bounds ('hit' a wall), we can just
		 * return that it's collided with itself, as both cases are handled
		 * identically.
		 */
		if (head.x < 0 || head.x >= board.COL_COUNT || head.y < 0 || head.y >= board.ROW_COUNT) {
			result[0] = TileType.SnakeBody; // Pretend we collided with our body.
			flag = 1;
		}
		if (headPlayer2.x < 0 || headPlayer2.x >= board.COL_COUNT || headPlayer2.y < 0
				|| headPlayer2.y >= board.ROW_COUNT) {
			result[1] = TileType.SnakeBody; // Pretend we collided with our
											// body.
			flag = 1;
		}
		if(flag == 1){
			return result;
		}
//System.out.println(flag);
		/*
		 * Here we get the tile that was located at the new head position and
		 * remove the tail from of the snake and the board if the snake is long
		 * enough, and the tile it moved onto is not a fruit.
		 * 
		 * If the tail was removed, we need to retrieve the old tile again
		 * incase the tile we hit was the tail piece that was just removed to
		 * prevent a false game over.
		 */
		
		/*System.out.println("player 1; x = "+head.x+" y= "+head.y);
		System.out.println("player 2; x = "+headPlayer2.x+" y= "+headPlayer2.y);*/
		TileType old = board.getTile(head.x, head.y);
		TileType oldPlayer2 = board.getTile(headPlayer2.x, headPlayer2.y);

		if (old != TileType.Fruit && snake.size() > MIN_SNAKE_LENGTH) {
			Point tail = snake.removeLast();
			board.setTile(tail, null);
			old = board.getTile(head.x, head.y);
		}

		if (oldPlayer2 != TileType.Fruit && snakePlayer2.size() > MIN_SNAKE_LENGTH) {
			Point tailPlayer2 = snakePlayer2.removeLast();
			board.setTile(tailPlayer2, null);
			oldPlayer2 = board.getTile(headPlayer2.x, headPlayer2.y);
		}
		/*
		 * Update the snake's position on the board if we didn't collide with
		 * our tail:
		 * 
		 * 1. Set the old head position to a body tile. 2. Add the new head to
		 * the snake. 3. Set the new head position to a head tile.
		 * 
		 * If more than one direction is in the queue, poll it to read new
		 * input.
		 */
		if (old != TileType.SnakeBody) {
			board.setTile(snake.peekFirst(), TileType.SnakeBody);
			snake.push(head);
			board.setTile(head, TileType.SnakeHead);
			if (directions.size() > 1) {
				directions.poll();
			}
		}

		if (oldPlayer2 != TileType.SnakeBody) {
			board.setTile(snakePlayer2.peekFirst(), TileType.SnakeBody);
			snakePlayer2.push(headPlayer2);
			board.setTile(headPlayer2, TileType.SnakeHead);
			if (directionsPlayer2.size() > 1) {
				directionsPlayer2.poll();
			}
		}
		result[0] = old;
		result[1] = oldPlayer2;
		return result;
	}

	/**
	 * Resets the game's variables to their default states and starts a new
	 * game.
	 */
	private void resetGame() {
		/*
		 * Reset the score statistics. (Note that nextFruitPoints is reset in
		 * the spawnFruit function later on).
		 */
		this.score = 0;
		this.fruitsEaten = 0;
		this.scorePlayer2 = 0;
		this.fruitsEatenPlayer2 = 0;

		/*
		 * Reset both the new game and game over flags.
		 */
		this.isNewGame = false;
		this.isGameOver = false;

		/*
		 * Create the head at the center of the board.
		 */
		Point head = new Point(board.COL_COUNT/25, board.ROW_COUNT /25 );
		Point headPlayer2 = new Point(board.COL_COUNT-2, board.ROW_COUNT-2);

		/*
		 * Clear the snake list and add the head.
		 */
		snake.clear();
		snake.add(head);
		snakePlayer2.clear();
		snakePlayer2.add(headPlayer2);

		/*
		 * Clear the board and add the head.
		 */
		board.clearBoard();
		board.setTile(head, TileType.SnakeHead);
		board.setTile(headPlayer2, TileType.SnakeHead);

		/*
		 * Clear the directions and add north as the default direction.
		 */
		directions.clear();
		directions.add(Direction.East);
		directionsPlayer2.clear();
		directionsPlayer2.add(Direction.West);

		/*
		 * Reset the logic timer.
		 */
		clock.reset();

		/*
		 * Spawn a new fruit.
		 */
		spawnFruit();
	}

	/**
	 * Gets the flag that indicates whether or not we're playing a new game.
	 * 
	 * @return The new game flag.
	 */
	public boolean isNewGame() {
		return isNewGame;
	}

	/**
	 * Gets the flag that indicates whether or not the game is over.
	 * 
	 * @return The game over flag.
	 */
	public boolean isGameOver() {
		return isGameOver;
	}

	/**
	 * Gets the flag that indicates whether or not the game is paused.
	 * 
	 * @return The paused flag.
	 */
	public boolean isPaused() {
		return isPaused;
	}

	/**
	 * Spawns a new fruit onto the board.
	 */
	private void spawnFruit() {
		// Reset the score for this fruit to 100.
		this.nextFruitScore = 100;

		/*
		 * Get a random index based on the number of free spaces left on the
		 * board.
		 */
		int index = random.nextInt(board.COL_COUNT * board.ROW_COUNT - snake.size());

		/*
		 * While we could just as easily choose a random index on the board and
		 * check it if it's free until we find an empty one, that method tends
		 * to hang if the snake becomes very large.
		 * 
		 * This method simply loops through until it finds the nth free index
		 * and selects uses that. This means that the game will be able to
		 * locate an index at a relatively constant rate regardless of the size
		 * of the snake.
		 */
		int freeFound = -1;
		for (int x = 0; x < board.COL_COUNT; x++) {
			for (int y = 0; y < board.ROW_COUNT; y++) {
				TileType type = board.getTile(x, y);
				if (type == null || type == TileType.Fruit) {
					if (++freeFound == index) {
						board.setTile(x, y, TileType.Fruit);
						break;
					}
				}
			}
		}
	}

	/**
	 * Gets the current score.
	 * 
	 * @return The score.
	 */
	public int getScore() {
		return score;
	}
	
	public int getScorePlayer2(){
		return scorePlayer2;
	}
	/**
	 * Gets the number of fruits eaten.
	 * 
	 * @return The fruits eaten.
	 */
	public int getFruitsEaten() {
		return fruitsEaten;
	}
	
	public int getFruitsEatenPlayer2() {
		return fruitsEatenPlayer2;
	}

	/**
	 * Gets the next fruit score.
	 * 
	 * @return The next fruit score.
	 */
	public int getNextFruitScore() {
		return nextFruitScore;
	}

	/**
	 * Gets the current direction of the snake.
	 * 
	 * @return The current direction.
	 */
	public Direction getDirection() {
		return directions.peek();
	}
}