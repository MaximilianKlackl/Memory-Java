import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.Collections;

public class Memory extends Stage {

    private int numOfPlayers;
    private int size;

    private Player[] players;
    private int currentPlayer = 0;

    private ArrayList<Card> cards;
    private ArrayList<Card> turnedCards = new ArrayList<>();
    private Board board;

    public Memory(int size, int numberOfPlayers)
    {
        this.numOfPlayers = numberOfPlayers;
        this.size = size;

        createBoardValues(size);
        createPlayers(numOfPlayers);
        addEventHandlers(cards);
        players[currentPlayer].setIsCurrentPlayer(true);

        board = new Board(players, cards, size);

        // stage settings
        this.setTitle("Memory");
        this.setResizable(false);
        this.setScene(new Scene(board));
        this.show();
    }

    private void addEventHandlers(ArrayList<Card> cards){
        for (Card card: cards) {
            card.setOnAction(buttonEvent);
        }
    }

    private void createPlayers(int numOfPlayers){
        players = new Player[numOfPlayers];
        for(int i = 0; i < numOfPlayers; i++) {
            players[i] = new Player("Player" + Integer.toString(i + 1));
        }
    }

    private void createBoardValues(int size)
    {
        int cardValue = 0;
        int length = size % 2 == 0 ? size * size : (size * size) -1;
        cards = new ArrayList<Card>();

        // fill 2 index with same value
        for(int j = 0; j < 2; j++){
            for(int i = 0; i < length/2; i++){
                cards.add(new Card(i));
            }
        }

        Collections.shuffle(cards);
    }

    private void nextPlayer()
    {
        players[currentPlayer].setIsCurrentPlayer(false);

        if(currentPlayer == players.length-1){
            currentPlayer = 0;
        }else{
            currentPlayer++;
        }

        players[currentPlayer].setIsCurrentPlayer(true);
    }

    private boolean hasUnrevealedCard(){
        for (Card card: cards) {
            if(!card.isRevealed()){
                return true;
            }
        }
        return false;
    }

    final EventHandler buttonEvent = new EventHandler() {
        @Override
        public void handle(Event event)
        {
            Card source = (Card) event.getSource();
            System.out.println(source.getImageValue());

            if(!source.isRevealed() && !turnedCards.contains(cards))
            {
                source.toggleCard();
                turnedCards.add(source);

                if(turnedCards.size() == 2)
                {
                    if(turnedCards.get(0).getImageValue() == turnedCards.get(1).getImageValue()){
                        turnedCards.get(0).setRevealed(true);
                        turnedCards.get(1).setRevealed(true);
                        players[currentPlayer].incrementScore();

                        if(!hasUnrevealedCard()){
                            close();
                            new AfterGameWindow(players, size);
                        }
                    }
                    else{
                        nextPlayer();
                    }

                    turnedCards.get(0).startAnimation();
                    turnedCards.get(1).startAnimation();

                    turnedCards.clear();
                }
            }
        }
    };
}
