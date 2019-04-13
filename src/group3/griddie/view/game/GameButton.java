package group3.griddie.view.game;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class GameButton extends StackPane {

    public enum Size {
        LARGE,
        MEDIUM,
        SMALL,
    }

    private Size size;
    private Text text;
    private ImageView imageView;
    private StackPane textPane;

    public GameButton(String text, Size size) {
        this.size = size;
        this.text = new Text(text);
        this.textPane = new StackPane();

        imageView = new ImageView();
        imageView.setPreserveRatio(true);
        setImage(new Image("assets/images/gamebutton.png"));
        this.textPane.getChildren().add(this.text);
        this.textPane.getStyleClass().add("text-button");
        StackPane.setAlignment(this.text, Pos.CENTER);
        getChildren().add(imageView);
        getChildren().add(textPane);

    }

    private void setImage(Image image) {
        imageView.setImage(image);

        switch (size) {
            case LARGE:
                imageView.setFitHeight(250);
                break;
            case SMALL:
                imageView.setFitHeight(50);
                break;
            case MEDIUM:
                imageView.setFitHeight(180);
                break;

        }
    }

}
