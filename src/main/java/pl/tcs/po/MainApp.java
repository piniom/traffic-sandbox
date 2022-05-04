package pl.tcs.po;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.tcs.po.models.Board;
import pl.tcs.po.views.BoardView;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) {

        Board board = new Board(20 , 10);
        BoardView boardView = new BoardView(board);

        Scene scene = new Scene(boardView.getParentView());

        stage.setScene(scene);

        stage.setTitle("Traffic Sandbox");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
