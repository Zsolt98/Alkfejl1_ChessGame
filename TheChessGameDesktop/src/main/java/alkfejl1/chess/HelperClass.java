package alkfejl1.chess;

import alkfejl1.chess.controller.*;
import alkfejl1.chess.model.*;
import alkfejl1.chess.model.figures.*;
import alkfejl1.chess.view.*;
import java.time.*;
import java.util.*;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class HelperClass {

    public static Image errorLogo = new Image(App.class.getResource("/images/chess_error.png").toString());
    public static Image logo = new Image(App.class.getResource("/images/chess_logo.png").toString());

    public static void showWarning(String message) {
        var alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Hiba");
        alert.setResizable(false);
        alert.setHeaderText(message);
        alert.getDialogPane().getScene().getRoot().setStyle("-fx-base:grey");
        addDialogIconTo(alert, errorLogo);
        alert.showAndWait();
    }

    public static void showSaveGame(PartyController controller) {
        if (App.player1label.getText() != null){
            var alert = new Alert(AlertType.WARNING, "", ButtonType.YES, ButtonType.NO);
            alert.setTitle("Játék mentése");
            alert.setResizable(false);
            alert.setHeaderText("Szeretnél új játékot kezdeni?");
            alert.getDialogPane().getScene().getRoot().setStyle("-fx-base:grey");
            addDialogIconTo(alert, logo);

            var result = alert.showAndWait().get();

            System.out.println(result);

            if(result == ButtonType.YES) {
                controller.add(new Party(GameLogic.player1name, GameLogic.player2name, String.valueOf(GameLogic.ACTIVE_COLOR), createBoardStateString(), LocalDateTime.now()));
                App.root.setCenter(App.createEmptyBoard());
                new AddPartyDialog().showDialog();
            }else if(result == ButtonType.NO){
                controller.add(new Party(GameLogic.player1name, GameLogic.player2name, String.valueOf(GameLogic.ACTIVE_COLOR), createBoardStateString(), LocalDateTime.now()));
            }
        }
        else {
            HelperClass.showWarning("Üres pályát nem lehet elmenteni!");
        }
    }

    private static String createBoardStateString() {
        var boardState = "";

        for(var row = 0; row < 8; ++row) {
            for(var column = 0; column < 8; ++column) {
                var currentFigure = GameLogic.figures[column][row];

                if(currentFigure instanceof FigurePlaceHolder) {
                    boardState += FigureBase.getTypeFromFigure(currentFigure) + ",";
                }else {
                    boardState += currentFigure.color + "" + FigureBase.getTypeFromFigure(currentFigure) + ",";
                }
            }
        }
        return boardState;
    }

    public static void showYesNo(String title, String message, Runnable onYesButtonHandler) {
        var alert = new Alert(AlertType.WARNING, "", ButtonType.YES, ButtonType.NO);
        alert.setTitle(title);
        alert.setResizable(false);
        alert.setHeaderText(message);
        alert.getDialogPane().getScene().getRoot().setStyle("-fx-base:grey");
        addDialogIconTo(alert, logo);

        var result = alert.showAndWait().get();
        if(result == ButtonType.YES) {
            onYesButtonHandler.run();
        }
    }

    public static void addDialogIconTo(Dialog<?> alert, Image image) {
        Stage dialogStage = (Stage) alert.getDialogPane().getScene().getWindow();
        dialogStage.getIcons().add(image);
    }

    public static void showFigureChoiceDialog(List<FigureBase> figures, FigurePawn pawn) {
        var dialog = new ChoiceDialog<>(figures.get(0), figures);
        dialog.setTitle("Átváltozás");
        dialog.setHeaderText("Bábu kiválasztása");
        dialog.getDialogPane().getScene().getRoot().setStyle("-fx-base:grey");
        addDialogIconTo(dialog, logo);

        var selectionMaybe = dialog.showAndWait();
        if(selectionMaybe.isPresent()) {
            var selection = selectionMaybe.get();
            var row = pawn.row;
            var column = pawn.column;
            var color = pawn.color;
            var type = selection.type;

            GameLogic.figureButtons[column][row].setGraphic(GameLogic.loadFigureTexture(type, color));
            GameLogic.figures[column][row] = FigureBase.createFigureByType(type, color, column, row);
        }
    }
}
