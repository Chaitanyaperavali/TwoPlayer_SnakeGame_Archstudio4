package edu.umkc.chaitu.as.snakegame.board;


import edu.uci.isr.myx.fw.AbstractMyxSimpleBrick;
import edu.uci.isr.myx.fw.IMyxName;
import edu.uci.isr.myx.fw.MyxUtils;

import edu.umkc.chaitu.as.snakegame.IGameBoard;
import edu.umkc.chaitu.as.snakegame.TileType;
import edu.umkc.chaitu.as.snakegame.snake.SnakeImp;

import java.awt.Point;

public class GameBoardArch extends AbstractMyxSimpleBrick implements IGameBoard
{
    public static final IMyxName msg_IGameBoard = MyxUtils.createName("edu.umkc.chaitu.as.snakegame.IGameBoard");


	private IGameBoardImp _imp;

    public GameBoardArch (){
		_imp = getImplementation();
		if (_imp != null){
			_imp.setArch(this);
		} else {
			System.exit(1);
		}
	}
    
    protected IGameBoardImp getImplementation(){
        try{
			return new GameBoardImp();    
        } catch (Exception e){
            System.err.println(e.getMessage());
            return null;
        }
    }

    public void init(){
        _imp.init();
    }
    
    public void begin(){
        _imp.begin();
    }
    
    public void end(){
        _imp.end();
    }
    
    public void destroy(){
        _imp.destroy();
    }
    
	public Object getServiceObject(IMyxName arg0) {
		if (arg0.equals(msg_IGameBoard)){
			return this;
		}        
		return null;
	}
  
    //To be imported: Point,SnakeImp
    public void clearBoard ()   {
		_imp.clearBoard();
    }    
    public void setTile (Point point,TileType type)   {
		_imp.setTile(point,type);
    }    
    public void setTile (int x,int y,TileType type)   {
		_imp.setTile(x,y,type);
    }    
    public TileType getTile (int x,int y)   {
		return _imp.getTile(x,y);
    }    
    public void repaint ()   {
		_imp.repaint();
    }    
    public void initialize (SnakeImp game)   {
		_imp.initialize(game);
    }    
}