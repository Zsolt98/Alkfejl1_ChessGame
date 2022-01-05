package alkfejl1.chess;

import alkfejl1.chess.model.figures.FigureBase;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class LaunchParty {

    public static final String[] BASE_BOARD_STATE = ("bb,bl,bf,bk,bn,bf,bl,bb," +
                                                     "bp,bp,bp,bp,bp,bp,bp,bp," +
                                                     "0,0,0,0,0,0,0,0," +
                                                     "0,0,0,0,0,0,0,0," +
                                                     "0,0,0,0,0,0,0,0," +
                                                     "0,0,0,0,0,0,0,0," +
                                                     "wp,wp,wp,wp,wp,wp,wp,wp," +
                                                     "wb,wl,wf,wk,wn,wf,wl,wb").split(",");

    public static void startGame(String[] boardState, String player1Name, String player2Name, char startingPlayerColor){
        GameLogic.player1name = player1Name;
        GameLogic.player2name = player2Name;
        GameLogic.ACTIVE_COLOR = startingPlayerColor;
        GameLogic.lastClickedFigure = null;
        GameLogic.whiteKnockedFigures.clear();
        GameLogic.blackKnockedFigures.clear();

        App.activeColorLabelPrefix.setText("A következő színű játékos: ");
        App.activeColorLabel.setText(startingPlayerColor == 'w' ? "White" : "Black");
        App.player1label.setText("Player 1: " + player1Name + " (white)");
        App.player2label.setText("Player 2: " + player2Name + " (black)");
        App.surrenderButton.setVisible(false);
        App.drawButton.setVisible(false);

        var figuresLayout = new GridPane();
        figuresLayout.setPadding(new Insets(0,0,0,0));

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
                figuresLayout.add(fieldButton, column, row);

                var fieldString = boardState[(row * 8) + column];
                var colorChar = fieldString.charAt(0);
                var typeChar = fieldString.charAt(fieldString.length() - 1);
                var figure = FigureBase.createFigureByType(typeChar, colorChar, column, row);
                var figureButton = new Button();

                figureButton.setGraphic(typeChar == '0' ? GameLogic.EMPTY_ZONE_TEXTURE : GameLogic.loadFigureTexture(typeChar, colorChar));
                figureButton.setStyle("-fx-background-color: transparent; -fx-border-style: none;");

                var rowCopy = row;
                var columnCopy = column;
                figureButton.setOnAction(e -> GameLogic.onFigureButtonClick(columnCopy, rowCopy));

                figuresLayout.add(figureButton, column, row);
                GameLogic.figures[column][row] = figure;
                GameLogic.figureButtons[column][row] = figureButton;
            }
        }
        App.root.setCenter(figuresLayout);
    }
}