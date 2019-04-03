package group3.griddie.model;

import java.util.ArrayList;
import java.util.Observable;

public abstract class Model extends Observable {

    public interface InteractListener {
       void onInteract();
    }

    private ArrayList<InteractListener> interactListeners;

    private ArrayList<Model> children;

    public Model() {
        interactListeners = new ArrayList<>();
        this.children = new ArrayList<>();
    }

    public void interact() {
        for (InteractListener listener : interactListeners) {
            listener.onInteract();
        }
    }

    public <T extends Model> void addInteractListener(InteractListener listener) {
        interactListeners.add(listener);
    }
    public <T extends Model> T addChild(T model) {
        children.add(model);
        return model;
    }

    public Model[] getChildren() {
        return (Model[]) children.toArray();
    }

}
