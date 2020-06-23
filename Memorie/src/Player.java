import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class Player extends VBox {

    private String name;
    private int score;

    private boolean isCurrentPlayer = true;
    private boolean hasGaveUp = false;

    private Label playerLabel;
    private Label scoreLabel;

    public Player(String name){
        this.name = name;
        this.score = 0;

        initUI();
    }

    private void initUI(){
        playerLabel = new Label(name);
        scoreLabel = new Label("Score: " + Integer.toString(score));

        // styling
        this.setPadding(new Insets(10,0,0,0));
        scoreLabel.setStyle("-fx-text-fill: black; -fx-font-size: 16;");
        playerLabel.setStyle("-fx-text-fill: black;-fx-font-size: 18;");

        this.getChildren().addAll(playerLabel, scoreLabel);
    }

    public void incrementScore()
    {
        this.score++;
        updateScoreLabel();
    }

    private void updateScoreLabel(){
        scoreLabel.setText("Score: " + Integer.toString(score));
    }

    public void setIsCurrentPlayer(boolean val){
        this.isCurrentPlayer = val;
        setCurrentPlayerHighlight();
    }

    public void setCurrentPlayerHighlight(){
        if(isCurrentPlayer){
            scoreLabel.setStyle("-fx-text-fill: green;-fx-font-size: 18;");
            playerLabel.setStyle("-fx-text-fill: green;-fx-font-size: 20;");
        }else{
            scoreLabel.setStyle("-fx-text-fill: black; -fx-font-size: 16;");
            playerLabel.setStyle("-fx-text-fill: black;-fx-font-size: 18;");
        }
    }

    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }

    public void setHasGaveUp(boolean bool){
        this.hasGaveUp = bool;
    }

    public boolean hasGaveUp(boolean b) {
        return hasGaveUp;
    }
}
