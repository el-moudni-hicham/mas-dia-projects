package ma.enset.game.server;

import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ServerGui extends Application {
    private ServerAgent serverAgent;
    private ObservableList<String> observableList;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        startContainer();
        BorderPane borderPane = new BorderPane();
        borderPane.setStyle("-fx-font-weight: bold;"+"-fx-background-color: #eee319;");

        HBox hbox2 = new HBox();
        Label title = new Label("Server");
        title.setStyle("-fx-font-family: Corbel;"+"-fx-font-size: 24px;"+"-fx-text-fill: #02025d;"+"-fx-font-weight: bold;");
        hbox2.getChildren().add(title);
        hbox2.alignmentProperty().setValue(Pos.CENTER);
        hbox2.setPadding(new Insets(0,0,10,0));
        borderPane.setTop(hbox2);

        observableList = FXCollections.observableArrayList();
        ListView<String> listView = new ListView<>(observableList);


        borderPane.setPadding(new Insets(10,10,20,10));
        borderPane.setCenter(listView);
        Scene scene = new Scene(borderPane,450,500);
        scene.getStylesheets().add(getClass().getResource("../style.css").toExternalForm());
        //scene.getStylesheets().add("../style.css");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void showMessage(String message){
        observableList.add(message);
    }

    private void startContainer() throws StaleProxyException {
        Runtime runtime = Runtime.instance();
        ProfileImpl profile = new ProfileImpl();
        profile.setParameter("host", "localhost");
        AgentContainer agentContainer = runtime.createAgentContainer(profile);
        AgentController agentController = agentContainer.createNewAgent("server","ma.enset.game.server.ServerAgent",new Object[]{this});
        agentController.start();
    }
}
