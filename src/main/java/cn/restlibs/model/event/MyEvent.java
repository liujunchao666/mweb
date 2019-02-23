package cn.restlibs.model.event;

import java.util.EventObject;


//LifecycleEvent
public class MyEvent extends EventObject {

    private static final long serialVersionUID = 1L;
    private int sourceState;

    public MyEvent(Object source) {
        super(source);
        sourceState = ((Source)source).getFlag();
    }

    public int getSourceState() {
        return sourceState;
    }

}
