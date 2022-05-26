package pl.tcs.po.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import pl.tcs.po.models.*;
import pl.tcs.po.models.blocks.BlockCreator;
import pl.tcs.po.models.blocks.EmptyBlock;
import pl.tcs.po.models.blocks.Rotation;
import pl.tcs.po.views.ImageLoader;
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
                boardController.currentText.setText(boardController.currentBlock.name());
            });
            blockButtons.add(button);
        }

        List<Button> rotationButtons = new ArrayList<>();
        for(var rotation : Rotation.values()){
            var button = new Button(rotation.toString().toLowerCase());
            button.setOnAction(e -> {
                boardController.currentRotation = rotation;
            });
            rotationButtons.add(button);
        }

        menuBar.getChildren().addAll(blockButtons);
        menuBar.getChildren().add(boardController.currentText);
        menuBar.getChildren().addAll(rotationButtons);

        var button = new Button("toggle debug");
        button.setOnAction(e -> boardController.toggleDebug());
        menuBar.getChildren().add(button);
    }
}

