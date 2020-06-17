import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import java.util.ArrayList;

public class Board extends BorderPane
{
    private ArrayList<Card> cards;
    private Player[] players;
    private int boardSize;

    private GridPane board = new GridPane();
    private VBox playerContainer = new VBox();

    public Board(Player[] players, ArrayList<Card> cards, int boardSize)
    {
        this.players = players;
        this.cards = cards;
        this.boardSize = boardSize;

        initUI();
    }

    private void initUI()
    {
        // add cards to gridpane

        for(int i = 0; i < boardSize; i++)
        {
            for(int j = 0; j < boardSize; j++)
            {
                // leave last index blank if uneven
                if(boardSize % 2 != 0){
                    if(i == (boardSize-1) && j == (boardSize-1)){
                        break;
                    }
                }

                int index = i * boardSize + j;
                board.add(cards.get(index), i, j);
            }
        }

        // add player to container
        playerContainer.setPadding(new Insets(15));
        playerContainer.setMinWidth(140);


        for (Player player: players) {
            playerContainer.getChildren().add(player);
        }

        // add components to borderpane
        setCenter(board);
        setRight(playerContainer);
    }
}
