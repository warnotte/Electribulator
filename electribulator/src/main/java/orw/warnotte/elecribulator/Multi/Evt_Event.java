package orw.warnotte.elecribulator.Multi;
public class Evt_Event {
	
    public static enum Refresh_MSG {
    	RECORDING_ARPEGIATOR_FINISHED
    };
    
    public Refresh_MSG message;
    Object obj=null; // Wich object
    
    public Evt_Event(Refresh_MSG message, Object obj) {
		super();
		this.message = message;
		this.obj = obj;
    }
    
}
