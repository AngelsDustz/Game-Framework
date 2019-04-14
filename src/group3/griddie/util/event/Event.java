package group3.griddie.util.event;

import java.util.ArrayList;

public class Event {

    private ArrayList<Listener> listeners;

    public Event() {
        listeners = new ArrayList<Listener>();
    }

    public void addListener(Listener listener) {
        listeners.add(listener);
    }

    public void removeListener(Listener listener) {
        listeners.remove(listener);
    }

    public void call() {
        for (Listener listener : listeners) {
            listener.call();
        }
    }

}
