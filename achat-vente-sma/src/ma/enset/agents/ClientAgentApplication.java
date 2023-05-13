package ma.enset.agents;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ClientAgentApplication extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane root = FXMLLoader.load(getClass().getResource("view/clientAgentView.fxml"));
        Scene scene = new Scene(root,600,400);
        primaryStage.setTitle("Client Agent");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
