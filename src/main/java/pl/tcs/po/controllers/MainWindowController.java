package pl.tcs.po.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import pl.tcs.po.MainApp;
import pl.tcs.po.models.*;
import pl.tcs.po.models.blocks.BlockCreator;
import pl.tcs.po.models.blocks.EmptyBlock;
import pl.tcs.po.models.blocks.Rotation;
import pl.tcs.po.views.ImageLoader;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable {

    @FXML
    Pane mainBoard;

    @FXML
    HBox menuBar;


    Board board;
    BoardController boardController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        board = new Board(15, 8);
        boardController = new BoardController(board);
        mainBoard.getChildren().add(boardController.getParentView());
        menuBar.setSpacing(20);
        addMenuBarButtons();
    }

    private void addMenuBarButtons() {
        /**
         *
         * TODO: make imageLoader a singleton?
         * TODO: maybe have the empty map preloaded?
         */
        ImageLoader imageLoader = new ImageLoader();
        List<Button> blockButtons = new ArrayList<>();
        for(var menuButton : BlockCreator.values()){
            var button = new Button();

            // TODO: make the buttons prettier
            Image image = imageLoader.loadImage(menuButton.name());
            ImageView buttonImageView = new ImageView(image);
            buttonImageView.setFitHeight(60);
            buttonImageView.setFitWidth(60);
            button.setGraphic(buttonImageView);

            button.setOnAction(e -> {
                boardController.currentBlock = menuButton;
            });
            blockButtons.add(button);
        }

        menuBar.getChildren().add(createSpacer());
        menuBar.getChildren().addAll(blockButtons);
        menuBar.getChildren().add(createSpacer());

        // TODO: create a method for this button modification
        var debugButton = new Button();
        Image debugImage = new Image(getClass().getResource("/images/debug.png").toString());
        ImageView debugImageView = new ImageView(debugImage);
        debugImageView.setFitWidth(90);
        debugImageView.setFitHeight(30);
        debugButton.setGraphic(debugImageView);
        debugButton.setStyle("-fx-background-color: #5d69bf;");

        debugButton.setOnAction(e -> boardController.toggleDebug());

        var saveButton = new Button();
        Image saveImage = new Image(getClass().getResource("/images/save.png").toString());
        ImageView saveImageView = new ImageView(saveImage);
        saveImageView.setFitWidth(90);
        saveImageView.setFitHeight(30);
        saveButton.setGraphic(saveImageView);
        saveButton.setStyle("-fx-background-color: #5d69bf;");
        //TODO: set on action

        var exitButton = new Button();
        Image exitImage = new Image(getClass().getResource("/images/exit.png").toString());
        ImageView exitImageView = new ImageView(exitImage);
        exitImageView.setFitWidth(90);
        exitImageView.setFitHeight(30);
        exitButton.setGraphic(exitImageView);
        exitButton.setStyle("-fx-background-color: #5d69bf;");

        exitButton.setOnAction(e ->{
            try {
                MainApp.instance.returnToMainMenu();
            } catch(IOException ex) {
                ex.printStackTrace();
            }
        });


        menuBar.getChildren().addAll(saveButton, exitButton, debugButton);
        menuBar.getChildren().add(createSpacer());
    }

    /**
     * returns a spacer used to space elements in VBox or HBox evenly
     * TODO: repeated code - put this in some factory or something
     */
    private Node createSpacer() {
        final Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        VBox.setVgrow(spacer, Priority.ALWAYS);
        return spacer;
    }
}

