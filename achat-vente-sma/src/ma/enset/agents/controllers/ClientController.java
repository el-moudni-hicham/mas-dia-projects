package ma.enset.agents.controllers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import ma.enset.agents.ClientAgent;
import ma.enset.containers.ClientContainer;

import java.net.URL;
import java.util.ResourceBundle;

public class ClientController implements Initializable {
    @FXML
    private ListView<String> messagesListView;
    @FXML
    private TextField txtBook;

    ObservableList<String> observableList = FXCollections.observableArrayList();

    ClientContainer clientContainer = new ClientContainer();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        clientContainer.startContainer();
        messagesListView.setItems(observableList);
    }

    ClientAgent clientAgent = new ClientAgent();
    @FXML
    void buttonOk(ActionEvent event) {
        String bookName = txtBook.getText();
        observableList.add(bookName);
        clientContainer.Gui(bookName);
    }
}
