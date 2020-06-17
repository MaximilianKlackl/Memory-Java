import javafx.animation.PauseTransition;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import java.io.File;

public class Card extends Button {

    private Image image;
    private boolean isRevealed;
    private int imageValue;

    private final Image EMPTY_IMAGE = new Image(getClass().getResourceAsStream("/assets/emptyImage.png"));
    private final Image CARD_BACKSIDE = new Image(getClass().getResourceAsStream("/assets/cardBackside.png"));

    public Card(int imageValue)
    {
        this.imageValue = imageValue;

        // since there a gifs and pngs i need somehow check what to load
        if(new File("src/assets/cards/" + imageValue + ".gif").exists()){
            this.image = new Image(getClass().getResourceAsStream("/assets/cards/" + imageValue + ".gif"));
        }
        else if(new File("src/assets/cards/" + imageValue + ".png").isFile()){
            this.image = new Image(getClass().getResourceAsStream("/assets/cards/" + imageValue + ".png"));
        }

        // set Image based on imageValue
        setButtonImage(CARD_BACKSIDE);
    }

    public int getImageValue(){
        return imageValue;
    }

    public void startAnimation(){
        setButtonImage(image);
        PauseTransition wait = new PauseTransition(Duration.seconds(2));
        wait.setOnFinished((e) -> {
            if(isRevealed){
                setButtonImage(EMPTY_IMAGE);
            }else{
                setButtonImage(CARD_BACKSIDE);
            }
        });
        wait.play();
    }

    public void toggleCard(){
        setButtonImage(image);
    }

    public void setRevealed(boolean revealed) {
        isRevealed = revealed;
        if(isRevealed){
            setButtonImage(EMPTY_IMAGE);
        }
    }

    public void setButtonImage(Image img){
        ImageView imageView = new ImageView(img);
        imageView.setFitHeight(80);
        imageView.setFitWidth(80);
        this.setGraphic(imageView);
    }

    public boolean isRevealed() {
        return isRevealed;
    }
}
