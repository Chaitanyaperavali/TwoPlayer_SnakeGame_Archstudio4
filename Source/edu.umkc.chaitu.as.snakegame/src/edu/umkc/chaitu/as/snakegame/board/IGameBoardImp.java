package edu.umkc.chaitu.as.snakegame.board;


import edu.umkc.chaitu.as.snakegame.TileType;
import edu.umkc.chaitu.as.snakegame.board.GameBoardArch;

import edu.umkc.chaitu.as.snakegame.snake.SnakeImp;

import java.awt.Point;

public interface IGameBoardImp 
{
	/*
	  Getter and Setter of architecture reference
	*/
    public void setArch (GameBoardArch arch);
	public GameBoardArch getArch();
	
	/*
  	  Myx Lifecycle Methods: these methods are called automatically by the framework
  	  as the bricks are created, attached, detached, and destroyed respectively.
	*/	
	public void init();	
	public void begin();
	public void end();
	public void destroy();

	/*
  	  Implementation primitives required by the architecture
	*/
  
    //To be imported: Point,SnakeImp
    public void clearBoard ()  ;        
    public void setTile (Point point,TileType type)  ;        
    public void setTile (int x,int y,TileType type)  ;        
    public TileType getTile (int x,int y)  ;        
    public void repaint ()  ;        
    public void initialize (SnakeImp game)  ;        
}