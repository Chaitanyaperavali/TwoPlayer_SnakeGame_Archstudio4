package edu.umkc.chaitu.as.snakegame.clock;


import edu.umkc.chaitu.as.snakegame.clock.ClockArch;

public interface IClockImp 
{

	/*
	  Getter and Setter of architecture reference
	*/
    public void setArch (ClockArch arch);
	public ClockArch getArch();
	
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
  
    
    public void initialize (float cyclesPerSecond)  ;        
    public void reset ()  ;        
    public void update ()  ;        
    public void setPaused (boolean paused)  ;        
    public boolean hasElapsedCycle ()  ;        
}