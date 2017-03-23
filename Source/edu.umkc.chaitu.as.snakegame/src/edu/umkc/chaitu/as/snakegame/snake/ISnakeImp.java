package edu.umkc.chaitu.as.snakegame.snake;


import edu.umkc.chaitu.as.snakegame.snake.SnakeArch;

public interface ISnakeImp 
{

	/*
	  Getter and Setter of architecture reference
	*/
    public void setArch (SnakeArch arch);
	public SnakeArch getArch();
	
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
}