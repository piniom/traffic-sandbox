package pl.tcs.po;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.tcs.po.controllers.MainWindowController;

import java.io.IOException;
import java.util.Objects;
import java.util.PrimitiveIterator;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        Parent root = getMainWindow();

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);


        stage.setTitle("Traffic Sandbox");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    private Parent getMainWindow() throws IOException {
        var url = MainWindowController.class.getClassLoader().getResource("MainWindow.fxml");
        return FXMLLoader.load(Objects.requireNonNull(url));
    }
}
