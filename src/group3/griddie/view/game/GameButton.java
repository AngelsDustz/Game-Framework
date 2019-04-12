package group3.griddie.view.game;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class GameButton extends StackPane {

    public enum Size {
        LARGE,
        SMALL,
    }

    private Size size;
    private Text text;
    private ImageView imageView;

    public GameButton(String text, Size size) {
        this.size = size;
        this.text = new Text(text);

        imageView = new ImageView();
        imageView.setPreserveRatio(true);

        setImage(new Image("assets/images/gamebutton.png"));

        getChildren().add(imageView);
    }

    private void setImage(Image image) {
        imageView.setImage(image);

        switch (size) {
            case LARGE:
                imageView.setFitHeight(100);
                break;
            case SMALL:
                imageView.setFitHeight(50);
        }
    }

}
