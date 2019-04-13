package group3.griddie.util.event;

import java.util.ArrayList;

public class ArgEvent<T> {

    private ArrayList<ArgListener<T>> listeners;

    public ArgEvent() {
        listeners = new ArrayList<ArgListener<T>>();
    }

    public void addListener(ArgListener<T> listener) {
        listeners.add(listener);
    }

    public void call(T arg) {
        for (ArgListener<T> listener : listeners) {
            listener.call(arg);
        }
    }

}
