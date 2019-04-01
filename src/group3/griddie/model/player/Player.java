package group3.griddie.model.player;

import group3.griddie.model.Model;

public abstract class Player extends Model {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
