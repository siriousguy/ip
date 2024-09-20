import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The entry point for the Handsome GUI application.
 * This class sets up the JavaFX application, loads FXML layout, and injects Handsome instance into controller.
 */
public class Main extends Application {
    private final Handsome handsome = new Handsome("./data/handsome.txt");

    /**
     * Starts the JavaFX application.
     * Loads the FXML file for the layout and initializes the scene with the Handsome instance.
     *
     * @param stage The primary stage for this application, where the main window will be displayed.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);

            MainWindow controller = fxmlLoader.<MainWindow>getController();
            controller.setHandsome(handsome); // inject the handsome instance
            controller.setStage(stage);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
