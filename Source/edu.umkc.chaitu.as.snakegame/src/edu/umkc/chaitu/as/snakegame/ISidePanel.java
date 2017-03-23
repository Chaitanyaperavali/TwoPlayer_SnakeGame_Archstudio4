package edu.umkc.chaitu.as.snakegame;

import edu.umkc.chaitu.as.snakegame.snake.SnakeImp;

public interface ISidePanel {
	//public void initialize(Snake game);
	public void repaint();
	public void initialize(SnakeImp game);
}
