package pl.tcs.po.controllers;

import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import pl.tcs.po.MainApp;
import pl.tcs.po.models.Board;
import pl.tcs.po.models.blocks.BlockCreator;
import pl.tcs.po.models.file.FileOperator;
import pl.tcs.po.views.ImageLoader;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MainWindowZoomableAndPannableController implements Initializable {

    private final double SCALE_DELTA = 1.1;
    private final StackPane mainBoardStackPane = new StackPane();
    private final Group boardGroup = new Group();

    @FXML
    ScrollPane scrollPane;
    @FXML
    HBox menuBar;

    Board board;
    BoardController boardController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        board = new Board(4, 3);
        boardController = new BoardController(board);

        // TODO: adding cars layer to this group should work fine with pannable scrollPane
        boardGroup.getChildren().add(boardController.getParentView());
        mainBoardStackPane.getChildren().add(boardGroup);

        menuBar.setSpacing(20);

        addMenuBarButtons();
        initializeCorrectFading();
        initializeScrolling();
    }

    private void initializeScrolling() {
        Group scrollContent = new Group(mainBoardStackPane);
        scrollPane.setContent(scrollContent);
        scrollPane.setPannable(true);

        scrollPane.viewportBoundsProperty().addListener((ObservableValue<? extends Bounds> observable,
                                                         Bounds oldValue, Bounds newValue) -> {
            mainBoardStackPane.setMinSize(newValue.getWidth(), newValue.getHeight());
        });

        mainBoardStackPane.setOnScroll(
                (ScrollEvent event) -> {
                    event.consume();
                    if(event.getDeltaY() == 0) {
                        return;
                    }
                    double scaleFactor = (event.getDeltaY() > 0) ? SCALE_DELTA : 1 / SCALE_DELTA;
                    Point2D scrollOffset = figureScrollOffset(scrollContent, scrollPane);
                    boardGroup.setScaleX(boardGroup.getScaleX() * scaleFactor);
                    boardGroup.setScaleY(boardGroup.getScaleY() * scaleFactor);
                    repositionScroller(scrollContent, scrollPane, scaleFactor, scrollOffset);
                }
        );

        scrollPane.setOnDragDetected(e -> {
            boardController.handleDragDetected();
        });
    }

    private void repositionScroller(Node scrollContent, ScrollPane scroller, double scaleFactor, Point2D scrollOffset) {
        double scrollXOffset = scrollOffset.getX();
        double scrollYOffset = scrollOffset.getY();
        double extraWidth = scrollContent.getLayoutBounds().getWidth() - scroller.getViewportBounds().getWidth();
        if (extraWidth > 0) {
            double halfWidth = scroller.getViewportBounds().getWidth() / 2;
            double newScrollXOffset = (scaleFactor - 1) * halfWidth + scaleFactor * scrollXOffset;
            scroller.setHvalue(scroller.getHmin() + newScrollXOffset * (scroller.getHmax() - scroller.getHmin()) / extraWidth);
        } else {
            scroller.setHvalue(scroller.getHmin());
        }
        double extraHeight = scrollContent.getLayoutBounds().getHeight() - scroller.getViewportBounds().getHeight();
        if (extraHeight > 0) {
            double halfHeight = scroller.getViewportBounds().getHeight() / 2;
            double newScrollYOffset = (scaleFactor - 1) * halfHeight + scaleFactor * scrollYOffset;
            scroller.setVvalue(scroller.getVmin() + newScrollYOffset * (scroller.getVmax() - scroller.getVmin()) / extraHeight);
        } else {
            scroller.setHvalue(scroller.getHmin());
        }
    }

    private Point2D figureScrollOffset(Node scrollContent, ScrollPane scroller) {
        double extraWidth = scrollContent.getLayoutBounds().getWidth() - scroller.getViewportBounds().getWidth();
        double hScrollProportion = (scroller.getHvalue() - scroller.getHmin()) / (scroller.getHmax() - scroller.getHmin());
        double scrollXOffset = hScrollProportion * Math.max(0, extraWidth);
        double extraHeight = scrollContent.getLayoutBounds().getHeight() - scroller.getViewportBounds().getHeight();
        double vScrollProportion = (scroller.getVvalue() - scroller.getVmin()) / (scroller.getVmax() - scroller.getVmin());
        double scrollYOffset = vScrollProportion * Math.max(0, extraHeight);
        return new Point2D(scrollXOffset, scrollYOffset);
    }

    private void initializeCorrectFading() {
        menuBar.setOnMouseEntered(e -> {
            boardController.updatePreviousBlock();
            boardController.resetPrevious();
        });
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

        debugButton.setOnAction(e -> {
            boardController.toggleSimulation();
        });

        var saveButton = new Button();
        Image saveImage = new Image(getClass().getResource("/images/save.png").toString());
        ImageView saveImageView = new ImageView(saveImage);
        saveImageView.setFitWidth(90);
        saveImageView.setFitHeight(30);
        saveButton.setGraphic(saveImageView);
        saveButton.setStyle("-fx-background-color: #5d69bf;");
        //TODO: set on action

        saveButton.setOnAction(e -> {
            System.out.println(FileOperator.boardToString(board));
            try {
                System.out.println(FileOperator.stringToBoard(FileOperator.boardToString(board)));
            } catch(Exception ex) {
                ex.printStackTrace();
            }
            save();
        });

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

    private void save() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save map");
        //TODO: uncomment
        //fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("MAP file", "*.map"));
        File file = fileChooser.showSaveDialog(MainApp.instance.getMainStage());
        if(file != null) {
            String boardString = FileOperator.boardToString(board);
            try {
                Files.writeString(file.toPath(), boardString);
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
    }
}
