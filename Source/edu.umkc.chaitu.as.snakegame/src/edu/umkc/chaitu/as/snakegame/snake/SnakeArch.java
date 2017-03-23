package edu.umkc.chaitu.as.snakegame.snake;


import edu.uci.isr.myx.fw.AbstractMyxSimpleBrick;
import edu.uci.isr.myx.fw.IMyxName;
import edu.uci.isr.myx.fw.MyxUtils;

import edu.umkc.chaitu.as.snakegame.IClock;
import edu.umkc.chaitu.as.snakegame.IGameBoard;
import edu.umkc.chaitu.as.snakegame.ISidePanel;

public class SnakeArch extends AbstractMyxSimpleBrick
{
    public static final IMyxName msg_IClock = MyxUtils.createName("edu.umkc.chaitu.as.snakegame.IClock");
    public static final IMyxName msg_ISidePanel = MyxUtils.createName("edu.umkc.chaitu.as.snakegame.ISidePanel");
    public static final IMyxName msg_IGameBoard = MyxUtils.createName("edu.umkc.chaitu.as.snakegame.IGameBoard");

    public IClock OUT_IClock;
    public ISidePanel OUT_ISidePanel;
    public IGameBoard OUT_IGameBoard;

	private ISnakeImp _imp;

    public SnakeArch (){
		_imp = getImplementation();
		if (_imp != null){
			_imp.setArch(this);
		} else {
			System.exit(1);
		}
	}
    
    protected ISnakeImp getImplementation(){
        try{
			return (ISnakeImp) new SnakeImp();    
        } catch (Exception e){
            System.err.println(e.getMessage());
            return null;
        }
    }

    public void init(){
        _imp.init();
    }
    
    public void begin(){
        OUT_IClock = (IClock) MyxUtils.getFirstRequiredServiceObject(this,msg_IClock);
        if (OUT_IClock == null){
 			System.err.println("Error: Interface edu.umkc.chaitu.as.snakegame.IClock returned null");
			return;       
        }
        OUT_ISidePanel = (ISidePanel) MyxUtils.getFirstRequiredServiceObject(this,msg_ISidePanel);
        if (OUT_ISidePanel == null){
 			System.err.println("Error: Interface edu.umkc.chaitu.as.snakegame.ISidePanel returned null");
			return;       
        }
        OUT_IGameBoard = (IGameBoard) MyxUtils.getFirstRequiredServiceObject(this,msg_IGameBoard);
        if (OUT_IGameBoard == null){
 			System.err.println("Error: Interface edu.umkc.chaitu.as.snakegame.IGameBoard returned null");
			return;       
        }
        _imp.begin();
    }
    
    public void end(){
        _imp.end();
    }
    
    public void destroy(){
        _imp.destroy();
    }
    
	public Object getServiceObject(IMyxName arg0) {
		return null;
	}
}