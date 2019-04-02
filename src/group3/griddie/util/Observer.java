package group3.griddie.util;

import java.util.ArrayList;

public class Observer {
    ArrayList<Listener> listeners;

    public Observer() {
        this.listeners = new ArrayList<>();
    }

    /**
     * Adds a listener to the watch list.
     *
     * @param listener
     */
    void addListener(Listener listener) {
        this.listeners.add(listener);
    }

    /**
     * Removes a listener from the watch list.
     *
     * @param listener
     */
    void removeListener(Listener listener) {
        this.listeners.remove(listener);
    }

    /**
     * Triggers an event for all listeners in the watch list.
     *
     * @param event
     */
    void trigger(Event event) {
        for (Listener listener : this.listeners) {
            listener.trigger(event);
        }
    }
}
