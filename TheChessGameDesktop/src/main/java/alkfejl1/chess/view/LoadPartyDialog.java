package alkfejl1.chess.view;

import static alkfejl1.chess.HelperClass.logo;

import alkfejl1.chess.LaunchParty;
import alkfejl1.chess.controller.PartyController;
import alkfejl1.chess.model.Party;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class LoadPartyDialog {
	
	private final PartyController controller;
	
    public LoadPartyDialog(PartyController controller) {
		this.controller = controller;
	}

	public void showDialog() {
        var stage = new Stage();
        var table = new TableView<>(FXCollections.observableArrayList(controller.listUnfinished()));
        
        table.setOnMousePressed(e -> {
        	if(e.isPrimaryButtonDown() && e.getClickCount() == 2) {
        		var selectedParty = table.getSelectionModel().getSelectedItem();
        		var boardState = selectedParty.getBoardStates().split(",");
        		
        		LaunchParty.startGame(boardState, selectedParty.getPlayer1name(), selectedParty.getPlayer2name(), selectedParty.getWinner().charAt(0));
        		stage.close();
        	}
        });
        
        var player1NameColumn = new TableColumn<Party, String>("Játékos 1");
        player1NameColumn.setCellValueFactory(new PropertyValueFactory<>("player1name"));
        
        var player2NameColumn = new TableColumn<Party, String>("Játékos 2");
        player2NameColumn.setCellValueFactory(new PropertyValueFactory<>("player2name"));
        
        var nextPlayerColumn = new TableColumn<Party, String>("Következő játékos");
        nextPlayerColumn.setCellValueFactory(k -> {
        	var party = k.getValue();
        	
        	return party.getWinner().equals("w") ? new ReadOnlyObjectWrapper<>(party.getPlayer1name()) : new ReadOnlyObjectWrapper<>(party.getPlayer2name());
        });
        
        var dateColumn = new TableColumn<Party, String>("Dátum");
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("partyTime"));
        
        table.getColumns().addAll(player1NameColumn, player2NameColumn, nextPlayerColumn, dateColumn);
        
        var scene = new Scene(table, 800, 600);
        scene.getRoot().setStyle("-fx-base:grey");

        stage.setScene(scene);
        stage.setTitle("Játék betöltése");
        stage.getIcons().add(logo);
        stage.show();
    }
}