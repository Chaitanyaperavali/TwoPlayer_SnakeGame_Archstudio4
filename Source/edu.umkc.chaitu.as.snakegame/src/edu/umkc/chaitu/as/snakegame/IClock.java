package edu.umkc.chaitu.as.snakegame;

public interface IClock {
	
	public void initialize(float cyclesPerSecond);

	public void reset();

	public void update();

	public void setPaused(boolean paused);

	public boolean hasElapsedCycle();

}