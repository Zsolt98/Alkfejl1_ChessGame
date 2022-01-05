package alkfejl1.chess.view;

import static alkfejl1.chess.HelperClass.logo;

import alkfejl1.chess.HelperClass;
import alkfejl1.chess.LaunchParty;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AddPartyDialog extends Stage {

    public void showDialog() {
        var stage = new Stage();

        var gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.setPadding(new Insets(10));

        var player1NameTF = new TextField();
        var player2NameTF = new TextField();

        gridPane.add(new Text("Név:"), 0, 0);
        gridPane.add(player1NameTF, 1, 0);

        gridPane.add(new Text("Név:"), 0, 1);
        gridPane.add(player2NameTF, 1, 1);

        var okButton = new Button("OK");
        okButton.setOnAction(e -> {
        	var player1Name = player1NameTF.getText();
        	var player2Name = player2NameTF.getText();
        	
        	if (player1Name.length() < 3 || player2Name.length() < 3) {
                HelperClass.showWarning("Mind a kettő játékos nevét kötelező megadni és legalább három karakter hosszúaknak kell lenniük!");
                return;
            }
        	
        	LaunchParty.startGame(LaunchParty.BASE_BOARD_STATE, player1Name, player2Name, 'w');            
            stage.close();
        });

        var cancelButton = new Button("Mégse");
        cancelButton.setCancelButton(true);
        cancelButton.setOnAction(e -> stage.close());

        var buttonPane = new FlowPane();
        buttonPane.setOrientation(Orientation.HORIZONTAL);
        buttonPane.setHgap(20);
        buttonPane.setAlignment(Pos.CENTER);
        buttonPane.getChildren().addAll(okButton, cancelButton);

        gridPane.add(buttonPane, 0, 4, 2, 1);

        var scene = new Scene(gridPane);
        scene.getRoot().setStyle("-fx-base:grey");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.setTitle("Új játék indítása");
        stage.getIcons().add(logo);
        stage.show();
    }
}
