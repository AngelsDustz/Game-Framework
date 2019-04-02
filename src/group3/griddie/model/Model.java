package group3.griddie.model;

import group3.griddie.util.Observer;

public abstract class Model {
    private Observer observer = new Observer();

    public Observer getObserver() {
        return observer;
    }
}
