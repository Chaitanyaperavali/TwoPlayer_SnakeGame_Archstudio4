package edu.umkc.chaitu.as.snakegame.side;


import edu.uci.isr.myx.fw.AbstractMyxSimpleBrick;
import edu.uci.isr.myx.fw.IMyxName;
import edu.uci.isr.myx.fw.MyxUtils;

import edu.umkc.chaitu.as.snakegame.ISidePanel;

import edu.umkc.chaitu.as.snakegame.snake.SnakeImp;

public class SidePanelArch extends AbstractMyxSimpleBrick implements ISidePanel
{
    public static final IMyxName msg_ISidePanel = MyxUtils.createName("edu.umkc.chaitu.as.snakegame.ISidePanel");


	private ISidePanelImp _imp;

    public SidePanelArch (){
		_imp = getImplementation();
		if (_imp != null){
			_imp.setArch(this);
		} else {
			System.exit(1);
		}
	}
    
    protected ISidePanelImp getImplementation(){
        try{
			return new SidePanelImp();    
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
		if (arg0.equals(msg_ISidePanel)){
			return this;
		}        
		return null;
	}
  
    //To be imported: SnakeImp
    public void repaint ()   {
		_imp.repaint();
    }    
    public void initialize (SnakeImp game)   {
		_imp.initialize(game);
    }    
}