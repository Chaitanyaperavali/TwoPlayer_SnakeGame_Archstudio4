package edu.umkc.chaitu.as.snakegame.side;


import edu.umkc.chaitu.as.snakegame.side.SidePanelArch;

import edu.umkc.chaitu.as.snakegame.snake.SnakeImp;

public interface ISidePanelImp 
{

	/*
	  Getter and Setter of architecture reference
	*/
    public void setArch (SidePanelArch arch);
	public SidePanelArch getArch();
	
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
  
    //To be imported: SnakeImp
    public void repaint ()  ;        
    public void initialize (SnakeImp game)  ;        
}