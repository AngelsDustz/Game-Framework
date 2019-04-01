package group3.griddie.controller;

import group3.griddie.model.Model;

public abstract class Controller<M extends Model> {
    private M model;

    public Controller(M model) {
        this.model = model;
    }
}
