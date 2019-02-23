package cn.restlibs.model.observer;

import java.util.EventListener;

public class TestEvent {

    public static void main(String[] args) {

        Source source = new Source();
        source.addStateChangeListener(new StateChangeListener());
        source.addStateChangeListener(new EventListener(){
            public void handleEvent() {
                System.out.println("触发状态改变事件2。。。");
                System.out.println("当前事件源状态为2：" );
            }
        });

        source.changeFlag();
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        source.changeFlag();

    }
}
