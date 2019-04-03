package group3.griddie.model;

import java.util.ArrayList;
import java.util.Observable;

public abstract class Model extends Observable {

    private ArrayList<Model> children;

    public Model() {
        this.children = new ArrayList<>();
    }

    public void tick() {
        for (Model child : children) {
            child.tick();
        }

        onTick();
    }

    protected abstract void onTick();

    public <T extends Model> T addChild(T model) {
        children.add(model);
        return model;
    }

}
