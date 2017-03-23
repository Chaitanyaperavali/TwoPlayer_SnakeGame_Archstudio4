package edu.umkc.chaitu.as.snakegame.side;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

import edu.umkc.chaitu.as.snakegame.snake.SnakeImp;
import edu.umkc.chaitu.as.snakegame.IGameBoard;
import edu.umkc.chaitu.as.snakegame.board.GameBoardImp;

public class SidePanelImp extends JPanel implements ISidePanelImp
{
	/**
	 * Serial Version UID.
	 */
	private static final long serialVersionUID = -40557434900946408L;

	/**
	 * The large font to draw with.
	 */
	private static final Font LARGE_FONT = new Font("Tahoma", Font.BOLD, 20);
	
	/**
	 * The medium font to draw with.
	 */
	private static final Font MEDIUM_FONT = new Font("Tahoma", Font.BOLD, 16);

	/**
	 * The small font to draw with.
	 */
	private static final Font SMALL_FONT = new Font("Tahoma", Font.BOLD, 12);
	
	private static final int STATISTICS_OFFSET = 150;

	private static final int CONTROLS_OFFSET = 500;

	private static final int MESSAGE_STRIDE = 30;

	private static final int SMALL_OFFSET = 30;
	
	
	private static final int LARGE_OFFSET = 50;
	
	/**
	 * The SnakeGame instance.
	 */
	private SnakeImp game;
	private SidePanelArch _arch;

    public SidePanelImp (){
    	//System.out.println("sidepanleimp constructor");
    }

	public void setArch(SidePanelArch arch){
		_arch = arch;
	}
	public SidePanelArch getArch(){
		return _arch;
	}

	/*
  	  Myx Lifecycle Methods: these methods are called automatically by the framework
  	  as the bricks are created, attached, detached, and destroyed respectively.
	*/	
	public void init(){
		//System.out.println("inside sidepanel init");
	}
	public void begin(){
		//TODO Auto-generated method stub
	}
	public void end(){
		//TODO Auto-generated method stub
	}
	public void destroy(){
		//TODO Auto-generated method stub
	}

	@Override
	public void repaint() {

		super.repaint();
		
	}

	@Override
	public void initialize(SnakeImp game) {
		this.game = game;
		this.game.add(this, BorderLayout.EAST);
		setPreferredSize(new Dimension(300, IGameBoard.ROW_COUNT * IGameBoard.TILE_SIZE));
		setBackground(Color.BLACK);
		
	}

	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		/*
		 * Set the color to draw the font in to white.
		 */
		g.setColor(Color.WHITE);

		/*
		 * Draw the game name onto the window.
		 */
		g.setFont(LARGE_FONT);
		g.drawString("Snake Game", getWidth() / 2 - g.getFontMetrics().stringWidth("Snake Game") / 2, 50);

		/*
		 * Draw the categories onto the window.
		 */
		g.setFont(MEDIUM_FONT);
		g.drawString("Statistics", SMALL_OFFSET, STATISTICS_OFFSET);
		g.drawString("Controls", SMALL_OFFSET, CONTROLS_OFFSET);

		/*
		 * Draw the category content onto the window.
		 */

		// Draw the content for the statistics category.
		int drawY = STATISTICS_OFFSET;
		g.setFont(MEDIUM_FONT);
		g.drawString(" ", LARGE_OFFSET, drawY += MESSAGE_STRIDE);
		g.drawString("Player 1: ", LARGE_OFFSET, drawY += MESSAGE_STRIDE);
		g.drawString(" ", LARGE_OFFSET, drawY += MESSAGE_STRIDE);
		g.setFont(SMALL_FONT);
		g.drawString("Total Score: " + game.getScore(), LARGE_OFFSET, drawY += MESSAGE_STRIDE);
		g.drawString("Fruit Eaten: " + game.getFruitsEaten(), LARGE_OFFSET, drawY += MESSAGE_STRIDE);
		//g.drawString("Fruit Score: " + game.getNextFruitScore(), LARGE_OFFSET, drawY += MESSAGE_STRIDE);
		g.setFont(MEDIUM_FONT);
		g.drawString(" ", LARGE_OFFSET, drawY += MESSAGE_STRIDE);
		g.drawString("Player 2: ", LARGE_OFFSET, drawY += MESSAGE_STRIDE);
		g.drawString(" ", LARGE_OFFSET, drawY += MESSAGE_STRIDE);
		g.setFont(SMALL_FONT);
		g.drawString("Total Score: " + game.getScorePlayer2(), LARGE_OFFSET, drawY += MESSAGE_STRIDE);
		g.drawString("Fruit Eaten: " + game.getFruitsEatenPlayer2(), LARGE_OFFSET, drawY += MESSAGE_STRIDE);
		// Draw the content for the controls category.
		drawY = CONTROLS_OFFSET;
		g.drawString("Move Up: W / Up Arrowkey", LARGE_OFFSET, drawY += MESSAGE_STRIDE);
		g.drawString("Move Down: S / Down Arrowkey", LARGE_OFFSET, drawY += MESSAGE_STRIDE);
		g.drawString("Move Left: A / Left Arrowkey", LARGE_OFFSET, drawY += MESSAGE_STRIDE);
		g.drawString("Move Right: D / Right Arrowkey", LARGE_OFFSET, drawY += MESSAGE_STRIDE);
		g.drawString("Pause Game: P", LARGE_OFFSET, drawY += MESSAGE_STRIDE);
		g.setFont(LARGE_FONT);
		g.drawString("Fruit Score: " + game.getNextFruitScore(), LARGE_OFFSET, 900);
	}
  
    
}