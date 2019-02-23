package cn.restlibs.model.observer;

import java.util.EventListener;

/**Subscribe
 *
 * @author Thief
 *
 */
public class StateChangeListener implements EventListener {

    public void handleEvent() {
        System.out.println("触发状态改变事件。。。");
    }
}