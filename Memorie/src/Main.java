import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // first opens input form for settings
        // than from input form new stage memory
        // next time better extend scene and not stage
        Settings form = new Settings();
        form.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
