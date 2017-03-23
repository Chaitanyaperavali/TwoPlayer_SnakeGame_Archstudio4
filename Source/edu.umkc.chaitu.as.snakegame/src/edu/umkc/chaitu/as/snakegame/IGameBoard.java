package edu.umkc.chaitu.as.snakegame;

import java.awt.Point;

import edu.umkc.chaitu.as.snakegame.snake.SnakeImp;

public interface IGameBoard {
	public static final int COL_COUNT = 50;

	/**
	 * The number of rows on the board. (Should be odd so we can start in the
	 * center).
	 */
	public static final int ROW_COUNT = 50;

	/**
	 * The size of each tile in pixels.
	 */
	public static final int TILE_SIZE = 20;
	public void clearBoard();
	public void setTile(Point point, TileType type);
	public void setTile(int x, int y, TileType type);
	public TileType getTile(int x, int y);
	public void repaint();
	public void initialize(SnakeImp game);
}
