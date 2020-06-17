import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.text.ParseException;

public class Settings extends Stage
{
    private GridPane grid = new GridPane();

    private Label playerLabel = new Label("Number of Players");
    private Label sizeLabel = new Label("Board size (3 - 8)");

    private TextField playerTextField = new TextField();
    private TextField sizeTextField = new TextField();
    private Button submit = new Button("Start");

    public Settings(){
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(10);
        grid.setHgap(10);

        grid.add(playerLabel, 0,0);
        grid.add(playerTextField, 1,0);
        grid.add(sizeLabel, 0,1);
        grid.add(sizeTextField, 1,1);
        grid.add(submit, 1,2);

        submit.setOnAction((e -> {

            if(isValidForm()){
                this.close();

                new Memory(
                        Integer.parseInt(sizeTextField.getText()),
                        Integer.parseInt(playerTextField.getText())
                );
            }
        } ));

        this.setScene(new Scene(grid, 500, 200));
    }

    private boolean isValidForm(){
        String sizeString = sizeTextField.getText();
        String playersString = playerTextField.getText();

        if(sizeString.length() != 0 && playersString.length() != 0) {
            try{
                int size = Integer.parseInt(sizeString);
                int players = Integer.parseInt(playersString);

                if(size > 0 && size < 9 && players > 1 && players < 11){
                    return true;
                }

            }catch (Exception ex){
                return false;
            }
        }
        return false;
    }
}
