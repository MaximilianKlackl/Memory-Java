import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.util.ArrayList;

public class Settings extends Stage
{
    private GridPane grid = new GridPane();

    private Label playerLabel = new Label("Number of Players");
    private Label sizeLabel = new Label("Board size (3 - 8)");
    private TextField playerTextField = new TextField();
    private TextField sizeTextField = new TextField();
    private Button submit = new Button("Start");
    private ArrayList<TextField> namesFields;

    public Settings(){
        grid.add(sizeLabel, 0,0);
        grid.add(sizeTextField, 1,0);
        grid.add(playerLabel, 0,1);
        grid.add(playerTextField, 1,1);

        playerTextField.setOnAction(e -> {
            if(playerTextField.getText().length() != 0 && isValidPlayerLength()){
                namesFields = new ArrayList<>();
                createNameFields();
            }
        });

        submit.setOnAction((e -> {
            if(isValidForm()){

                this.close();

                new Memory(
                        Integer.parseInt(sizeTextField.getText()),
                        createPlayers(namesFields.size() ,parseTextFields(namesFields))
                );
            }
        } ));

        grid.setAlignment(Pos.CENTER);
        grid.setVgap(10);
        grid.setHgap(10);
        this.setScene(new Scene(grid, 600, 400));
    }

    private void createNameFields()
    {
        int length = Integer.parseInt(playerTextField.getText());
        for(int i = 0; i < length; i++)
        {
            grid.add(new Label("Player " + Integer.toString(i+1)), 0, i+2);
            namesFields.add(new TextField());
            grid.add(namesFields.get(i), 1, i+2);
        }
         grid.add(submit, 1, length+4);
    }

    private boolean isValidPlayerLength(){

        String playerLength = playerTextField.getText();

        if(playerLength.length() != 0) {
            try{
                int length = Integer.parseInt(playerLength);

                if( length > 0 && length < 7){
                    return true;
                }

            }catch (Exception ex){
                return false;
            }
        }
        return false;
    }

    private Player[] createPlayers(int numOfPlayers, String[] names){
        Player[] players = new Player[numOfPlayers];
        players = new Player[numOfPlayers];
        for(int i = 0; i < numOfPlayers; i++) {
            players[i] = new Player(names[i]);
        }
        return players;
    }

    private String[] parseTextFields(ArrayList<TextField> textFields){
        String[] strings = new String[textFields.size()];
        for(int i = 0; i < textFields.size(); i++){
            strings[i] = textFields.get(i).getText();
        }
        return strings;
    }

    private boolean isValidForm(){

        for (TextField name: namesFields) {
            String playersString = name.getText();
            if(playersString.length() == 0){
                return false;
            }
        }
        String sizeString = sizeTextField.getText();

        if(sizeString.length() != 0) {
            try{
                int size = Integer.parseInt(sizeString);

                if(size > 0 && size < 9){
                    return true;
                }

            }catch (Exception ex){
                return false;
            }
        }
        return false;
    }


}
