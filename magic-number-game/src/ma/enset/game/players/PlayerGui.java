package ma.enset.game.players;

import jade.gui.GuiEvent;
import jade.wrapper.AgentContainer;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.Scanner;

public class PlayerGui extends Application {
    private PlayerAgent playerAgent;
    private ObservableList<String> observableList;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        startContainer();
        BorderPane borderPane = new BorderPane();
        borderPane.setStyle("-fx-font-weight: bold;");

        HBox hbox2 = new HBox();
        Label title = new Label("Player");
        title.setStyle("-fx-font-family: Corbel;"+"-fx-font-size: 24px;"+"-fx-text-fill: #c0b601;"+"-fx-font-weight: bold;");
        hbox2.getChildren().add(title);
        hbox2.alignmentProperty().setValue(Pos.CENTER);
        hbox2.setPadding(new Insets(0,0,10,0));
        borderPane.setTop(hbox2);

        HBox hBox = new HBox();

        TextField nb = new TextField();
        nb.setPrefWidth(50);
        nb.setStyle("-fx-background-color: #c9c9c9;"+"-fx-background-radius: 30px;");

        Label label = new Label("Give me a number :");
        label.setStyle("-fx-text-fill: #02025d;");
        //TextField textField = new TextField();
        Button button = new Button("Guess");
        button.setPadding(new Insets(5));
        button.setStyle("-fx-background-color: #ffe0e0;");

        hBox.getChildren().addAll(label, nb, button);
        hBox.setPadding(new Insets(10));
        hBox.setSpacing(10);
        borderPane.setBottom(hBox);

        observableList = FXCollections.observableArrayList();
        ListView<String> listView = new ListView<>(observableList);
        //listView.setPadding(new Insets(20,10,20,10));
        borderPane.setCenter(listView);
        borderPane.setPadding(new Insets(10,10,20,10));

        Scene scene = new Scene(borderPane,450,500);
        scene.getStylesheets().add(getClass().getResource("../style.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();


        button.setOnAction((event) -> {
            String message = nb.getText();

            GuiEvent guiEvent = new GuiEvent(this, 1);
            guiEvent.addParameter(message);
            playerAgent.onGuiEvent(guiEvent);

            observableList.add(message);
            nb.clear();
        });
    }

    public void setPlayerAgent(PlayerAgent playerAgent) {
        this.playerAgent = playerAgent;
    }

    public void showMessage(String message){
        observableList.add(message);
    }

    private void startContainer() throws StaleProxyException {
        System.out.println("Give your player a name :");
        Scanner sc = new Scanner(System.in);
        String nickname = sc.nextLine();

        Runtime runtime = Runtime.instance();
        ProfileImpl profile = new ProfileImpl();
        profile.setParameter("host", "localhost");
        AgentContainer agentContainer = runtime.createAgentContainer(profile);

        AgentController agentController = agentContainer.createNewAgent(nickname, "ma.enset.game.players.PlayerAgent", new Object[]{this});
        agentController.start();
    }
}
