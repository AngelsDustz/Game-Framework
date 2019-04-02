package group3.griddie.util;

@FunctionalInterface
public interface Listener {
    void trigger(Event event);
}
