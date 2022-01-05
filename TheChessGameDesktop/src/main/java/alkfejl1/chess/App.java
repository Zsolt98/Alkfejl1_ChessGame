package alkfejl1.chess;

import static alkfejl1.chess.HelperClass.logo;

import java.time.LocalDateTime;

import alkfejl1.chess.controller.PartyController;
import alkfejl1.chess.model.Party;
import alkfejl1.chess.view.AddPartyDialog;
import alkfejl1.chess.view.ListPartiesDialog;
import alkfejl1.chess.view.LoadPartyDialog;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {

    private final PartyController controller = new PartyController();

    public static final BorderPane root = new BorderPane();
    public static final Label player1label = new Label();
    public static final Label player2label = new Label();
    public static final Label activeColorLabelPrefix = new Label();
    public static final Label activeColorLabel = new Label();
    public static final Button surrenderButton = new Button("Feladás");
    public static final Button drawButton = new Button("Döntetlen");

    @Override
    public void start(Stage stage) {
        var newGame = new Menu("Új játék");
        var newGameMenuItem = new MenuItem("Local 1v1 játék");
        newGameMenuItem.setOnAction(e -> new AddPartyDialog().showDialog());

        newGame.getItems().add(newGameMenuItem);

        var saveGame = new Menu("Játék mentés");
        var saveGameMenuItem = new MenuItem("Játék mentése adatbázisba");
        saveGame.getItems().add(saveGameMenuItem);

        var loadGame = new Menu("Játék betöltés");
        var loadGameMenuItem = new MenuItem("Játék betöltés adatbázisból");
        loadGame.getItems().add(loadGameMenuItem);

        var listGamesMenu = new Menu("Játékok Listázása");
        var listGamesMenuItem = new MenuItem("Játékok listázása adatbázisból");
        listGamesMenu.getItems().add(listGamesMenuItem);

        surrenderButton.setOnAction(e -> {
        	var surrenderedPlayerName = GameLogic.ACTIVE_COLOR == 'w' ? GameLogic.player1name : GameLogic.player2name;
        	var winnerPlayerName = GameLogic.ACTIVE_COLOR == 'w' ? GameLogic.player2name : GameLogic.player1name;

            HelperClass.showWarning(surrenderedPlayerName + " feladta a játékot!");
            controller.add(new Party(GameLogic.player1name, GameLogic.player2name, winnerPlayerName, "", LocalDateTime.now()));
            root.setCenter(createEmptyBoard());
        });

        drawButton.setOnAction(e -> {
        	HelperClass.showYesNo("Döntetlen", "Elfogadod a döntetlent?", () -> {
        		controller.add(new Party(GameLogic.player1name, GameLogic.player2name, "X", "", LocalDateTime.now()));
                root.setCenter(createEmptyBoard());
        	});
        });

        saveGameMenuItem.setOnAction(e -> HelperClass.showSaveGame(controller));
        loadGameMenuItem.setOnAction(e -> new LoadPartyDialog(controller).showDialog());
        listGamesMenuItem.setOnAction(e -> new ListPartiesDialog(controller).showDialog());

        var rightMenuBox = new VBox(activeColorLabelPrefix, activeColorLabel, player1label, player2label, surrenderButton, drawButton);
        rightMenuBox.setSpacing(10);
        rightMenuBox.setPadding(new Insets(10,10,10,10));

        var menuBar = new MenuBar(newGame, saveGame, loadGame, listGamesMenu);
        menuBar.setPadding(new Insets(4,4,4,0));

        root.setPadding(new Insets(0,0,0,0));
        root.setTop(menuBar);
        root.setCenter(createEmptyBoard());
        root.setRight(rightMenuBox);
        var scene = new Scene(root, 1200, 780);

        stage.setScene(scene);
        stage.setTitle("Sakk");
        stage.getScene().getRoot().setStyle("-fx-base:grey");
        stage.setResizable(false);
        stage.getIcons().add(logo);
        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }

    public static GridPane createEmptyBoard() {
        surrenderButton.setVisible(false);
        drawButton.setVisible(false);
        player1label.setText(null);
        player2label.setText(null);
		activeColorLabelPrefix.setText(null);
        activeColorLabel.setText(null);

        drawButton.setMaxWidth(200);
        surrenderButton.setMaxWidth(200);

    	var emptyBoard = new GridPane();
        emptyBoard.setPadding(new Insets(0,0,0,0));

        var placeholderImage = new ImageView("images/0_0.png");
        placeholderImage.setFitHeight(85);
        placeholderImage.setFitWidth(85);

        for(int row = 0; row < 8; row++){
            for(int column = 0; column < 8; column++){
                var fieldButton = new Button();
                var grey = "-fx-background-color: grey;";
                var white = "-fx-background-color: white;";

                fieldButton.setStyle(column % 2 == row % 2 ? white : grey);
                fieldButton.setGraphic(placeholderImage);
                emptyBoard.add(fieldButton, column, row);
            }
        }
        return emptyBoard;
    }

}