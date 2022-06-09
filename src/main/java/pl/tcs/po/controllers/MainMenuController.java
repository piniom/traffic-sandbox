package pl.tcs.po.controllers;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import pl.tcs.po.MainApp;
import pl.tcs.po.models.Board;
import pl.tcs.po.models.file.FileOperator;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ResourceBundle;

public class MainMenuController implements Initializable {

    @FXML
    StackPane stackPane;

    ImageView backgroundImageView, logoImageView;
    Button newButton, loadButton, settingsButton;
    FileChooser fileChooser;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeImageViews();
        configureFileChooser();
        initializeButtons();
        Group group = getMenuGroup();
        stackPane.getChildren().add(group);
    }

    private void configureFileChooser() {
        fileChooser = new FileChooser();
        fileChooser.setTitle("Select map file");
        // TODO: uncomment
        //fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("MAP file", "*.map"));
    }

    private Group getMenuGroup() {
        VBox vBox = new VBox();
        HBox hBox = new HBox();
        hBox.getChildren().addAll(
                createSpacer(), newButton, createSpacer(), loadButton, createSpacer(), settingsButton, createSpacer()
        );
        vBox.setPrefHeight(stackPane.getPrefHeight());
        vBox.getChildren().addAll(logoImageView, createSpacer(), hBox, createSpacer(),createSpacer());

        return new Group(backgroundImageView, vBox);
    }

    /**
     * returns a spacer used to space elements in VBox or HBox evenly
     */
    private Node createSpacer() {
        final Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        VBox.setVgrow(spacer, Priority.ALWAYS);
        return spacer;
    }

    private void initializeButtons() {
        // TODO change this to use ImageLoader
        // TODO also image on button should have no background, background should be set through css styles
        Image newImage = new Image(getClass().getResource("/images/new.png").toString());
        ImageView newImageView = new ImageView(newImage);
        Image loadImage = new Image(getClass().getResource("/images/load.png").toString());
        ImageView loadImageView = new ImageView(loadImage);
        Image settingsImage = new Image(getClass().getResource("/images/settings.png").toString());
        ImageView settingsImageView = new ImageView(settingsImage);

        newButton = new Button();
        newButton.setGraphic(newImageView);
        newButton.setStyle("-fx-background-color: #5d69bf;");
        newButton.setOnAction(e -> {
            try {
                MainApp.instance.runNewSandbox();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        loadButton = new Button();
        loadButton.setGraphic(loadImageView);
        loadButton.setStyle("-fx-background-color: #5d69bf; ");
        loadButton.setOnAction(e -> {
            // TODO load file
            File file = fileChooser.showOpenDialog(MainApp.instance.getMainStage());
            if(file != null) {
                try {
                    System.out.println("got file: " + file);
                    String fileString = Files.readString(file.toPath());
                    Board board = FileOperator.stringToBoard(fileString);
                    MainApp.instance.runNewSandbox(board);

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }


        });
        settingsButton = new Button();
        settingsButton.setGraphic(settingsImageView);
        settingsButton.setStyle("-fx-background-color: #5d69bf; ");
        settingsButton.setOnAction(e -> {
            // TODO go to settings
        });


    }

    private void initializeImageViews() {
        Image backgroundImage = new Image(getClass().getResource("/images/menuBackground.png").toString());
        Image logoImage = new Image(getClass().getResource("/images/logo.png").toString());
        backgroundImageView = new ImageView(backgroundImage);
        logoImageView = new ImageView(logoImage);
    }
}
