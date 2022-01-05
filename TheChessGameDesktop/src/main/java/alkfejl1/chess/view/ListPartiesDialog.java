package alkfejl1.chess.view;

import static alkfejl1.chess.HelperClass.logo;

import alkfejl1.chess.controller.PartyController;
import alkfejl1.chess.model.Party;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ListPartiesDialog {
	
	private final PartyController controller;
	
    public ListPartiesDialog(PartyController controller) {
		this.controller = controller;
	}

	public void showDialog() {
        var stage = new Stage();
        var searchInputField = new TextField();
        var searchInputFieldLabel = new Label("Figyeljünk a kis és nagybetűkre!");
        var searchColumnBox = new ComboBox<>(FXCollections.observableArrayList("player1", "player2", "winner", "partytime"));
        var updateListButton = new Button("Keresés");
        
        searchColumnBox.getSelectionModel().selectFirst();
        
        var table = new TableView<>(FXCollections.observableArrayList(controller.listFinished("player1", "player1")));
        
        updateListButton.setOnAction(e -> {
        	var searchedField = searchColumnBox.getSelectionModel().getSelectedItem();
        	var searchedValue = searchInputField.getText();
        	var newItems = controller.listFinished(searchedField, searchedValue.isEmpty() ? searchedField : "'" + searchedValue + "'");
        	
        	table.setItems(FXCollections.observableArrayList(newItems));
        });
        
        var player1NameColumn = new TableColumn<Party, String>("Játékos 1");
        player1NameColumn.setCellValueFactory(new PropertyValueFactory<>("player1name"));
        
        var player2NameColumn = new TableColumn<Party, String>("Játékos 2");
        player2NameColumn.setCellValueFactory(new PropertyValueFactory<>("player2name"));
        
        var nextPlayerColumn = new TableColumn<Party, String>("Nyertes");
        nextPlayerColumn.setCellValueFactory(new PropertyValueFactory<>("winner"));
        
        var dateColumn = new TableColumn<Party, String>("Dátum");
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("partyTime"));
        
        table.getColumns().addAll(player1NameColumn, player2NameColumn, nextPlayerColumn, dateColumn);
        
        var root = new VBox(10, new HBox(10, searchInputFieldLabel, searchInputField, searchColumnBox, updateListButton), table);
        root.setPadding(new Insets(10,5,10,5));

		var scene = new Scene(root, 800, 600);
        scene.getRoot().setStyle("-fx-base:grey");

        stage.setScene(scene);
        stage.setTitle("Játék keresés");
        stage.getIcons().add(logo);
        stage.show();
    }
}