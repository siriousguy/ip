//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.animation.PauseTransition;
import javafx.util.Duration;

import java.util.Objects;

public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    private Handsome handsome;
    private Stage stage;
    private final Image userImage = new Image(Objects.requireNonNull(this.getClass().
                                        getResourceAsStream("/images/HandsomeUser2.png")));
    private final Image HandsomeImage = new Image(Objects.requireNonNull(this.getClass().
                                            getResourceAsStream("/images/HandsomeMan2.png")));

    public MainWindow() {
    }

    @FXML
    public void initialize() {
        this.scrollPane.vvalueProperty().bind(this.dialogContainer.heightProperty());
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(
                                        "Hey, I'm Handsome! Nice to meet you!", HandsomeImage));
    }

    public void setHandsome(Handsome h) {
        this.handsome = h;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private void handleUserInput() {
        String input = this.userInput.getText();
        String response = this.handsome.getResponse(input);
        this.dialogContainer.getChildren().addAll(
                new Node[]{DialogBox.getUserDialog(input, this.userImage),
                        DialogBox.getDukeDialog(response, this.HandsomeImage)});
        this.userInput.clear();

        // Closes the GUI window
        if (input.equals("bye")) {
            PauseTransition delay = new PauseTransition(Duration.seconds(2)); // Give a 2 seconds delay
            delay.setOnFinished(event -> this.stage.close());
            delay.play();
        }
    }
}
