import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.Comparator;

public class ScorePopup extends Stage
{
    private VBox playerContainer = new VBox();
    private Button exit = new Button("Exit");
    private Button playAgain = new Button("Play Again");

    ScorePopup(Player[] players, int size)
    {
        playerContainer.setSpacing(10);
        playerContainer.setAlignment(Pos.CENTER);
        players = sortPlayersByScore(players);

        for(int i = 0; i < players.length; i++)
        {
            String text = (i + 1) + ". " + players[i].getName() + ": Score " + Integer.toString(players[i].getScore());
            playerContainer.getChildren().add(new Label(text));
        }

        playerContainer.getChildren().add(playAgain);
        playerContainer.getChildren().add(exit);

        Player[] finalPlayers = players;

        playAgain.setOnAction((event -> {
            for (Player player: finalPlayers) {
                player.setIsCurrentPlayer(false);
                player.hasGaveUp(false);
                player.setCurrentPlayerHighlight();
            }

            finalPlayers[0].setIsCurrentPlayer(true);
            this.close();
            new Memory(size, finalPlayers);
        }));

        exit.setOnAction((event -> {
            System.exit(0);
        }));

        this.setScene(new Scene(playerContainer, 300,400));
        this.show();
    }

    private Player[] sortPlayersByScore(Player[] players){
        Arrays.sort(players, new Comparator<Player>() {
            @Override
            public int compare(Player p1, Player p2) {
                if(p1.getScore() > p2.getScore()){ return -1; }
                if(p2.getScore() < p2.getScore()){ return 1; }
                return 0;
            }
        });
        return players;
    }
}
