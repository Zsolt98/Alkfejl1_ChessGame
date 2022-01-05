package alkfejl1.chess;

import java.util.ArrayList;

import alkfejl1.chess.model.Point;
import alkfejl1.chess.model.figures.*;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;


public class GameLogic {
    public static final ImageView EMPTY_ZONE_TEXTURE = loadFigureTexture('0', '0');
    public static FigureBase lastClickedFigure;
    public static char ACTIVE_COLOR;
    public static ArrayList<Point> lastClickedFigureStepPositions;
    public static String player1name;
    public static String player2name;


    public static final FigureBase[][] figures = new FigureBase[8][8];
    public static final Button[][] figureButtons = new Button[8][8];
    public static final ArrayList<FigureBase> whiteKnockedFigures = new ArrayList<>();
    public static final ArrayList<FigureBase> blackKnockedFigures = new ArrayList<>();
    private static final ArrayList<Button> highlightedFigureButtons = new ArrayList<>();


    public static boolean isEnemyAtPosition(int column, int row, FigureBase self) {
        if(isPositionInBounds(column, row)) {
            var colorAtPos = figures[column][row].color;

            return colorAtPos != '0' && colorAtPos != self.color;
        }
        return false;
    }

    public static void markPosition(int column, int row, ArrayList<Point> outOptions, FigureBase self) {
        if(isPositionInBounds(column, row)) {
            var figureAtPos = figures[column][row];

            if(!(figureAtPos instanceof FigureKing) && figureAtPos.color != self.color) {
                outOptions.add(new Point(column, row));
            }
        }
    }

    public static boolean isPositionInBounds(int column, int row) {
        return column >= 0 && column < 8 && row >= 0 && row < 8;
    }

    public static void stepWithFigure(FigureBase toStepWith, FigureBase toStepOnto) {
        var toStepWithColumn = toStepWith.column;
        var toStepWithRow = toStepWith.row;
        var toStepWithButton = figureButtons[toStepWithColumn][toStepWithRow];

        var toStepOntoColumn = toStepOnto.column;
        var toStepOntoRow = toStepOnto.row;
        var toStepOntoButton = figureButtons[toStepOntoColumn][toStepOntoRow];

        toStepOntoButton.setGraphic(toStepWithButton.getGraphic());
        toStepWithButton.setGraphic(EMPTY_ZONE_TEXTURE);

        figures[toStepOntoColumn][toStepOntoRow] = toStepWith;
        figures[toStepWithColumn][toStepWithRow] = new FigurePlaceHolder(toStepWithColumn, toStepWithRow);

        toStepWith.column = toStepOntoColumn;
        toStepWith.row = toStepOntoRow;
        toStepWith.afterSuccessfulStep();
    }

    public static void onFigureButtonClick(int column, int row) {
        var figureAtButtonPos = figures[column][row];

        if(lastClickedFigure == null) {
            if(figureAtButtonPos.color == ACTIVE_COLOR){
                var validStepPositions = new ArrayList<Point>();
                figureAtButtonPos.getPossibleStepPositions(validStepPositions);

                if(!(figureAtButtonPos instanceof FigurePlaceHolder)) {
                    highlightCurrentFigure(column, row);
                    lastClickedFigure = figureAtButtonPos;
                    lastClickedFigureStepPositions = validStepPositions;
                }

                for(var option : validStepPositions) {
                    highlightSuggestionFigure(option.column, option.row);
                }
            }
        }else{
            for(var butt : highlightedFigureButtons) {
                butt.setStyle("-fx-background-color: transparent;");
            }

            highlightedFigureButtons.clear();

            if(figureAtButtonPos.canStep()) {
                var isWhite = ACTIVE_COLOR == 'w';

                stepWithFigure(lastClickedFigure, figureAtButtonPos);
                ACTIVE_COLOR = isWhite ? 'b' : 'w';
                App.activeColorLabel.setText(isWhite ? "White" : "Black");

                if(!(figureAtButtonPos instanceof FigurePlaceHolder) && !(figureAtButtonPos instanceof FigurePawn)) {
                    if(isWhite) {
                        blackKnockedFigures.add(figureAtButtonPos);
                    }else{
                        whiteKnockedFigures.add(figureAtButtonPos);
                    }
                }
            }

            lastClickedFigure = null;
        }
    }

    private static void updateFigureStyleAt(int column, int row, String style) {
        if(isPositionInBounds(column, row)) {
            var figureButtonAtPos = figureButtons[column][row];

            figureButtonAtPos.setStyle(style);
            highlightedFigureButtons.add(figureButtonAtPos);
        }
    }

    public static boolean isPositionEmpty(int column, int row) {
        return isPositionInBounds(column, row) && figures[column][row] instanceof FigurePlaceHolder;
    }

    private static void highlightCurrentFigure(int column, int row) {
        updateFigureStyleAt(column, row, "-fx-background-color: rgb(200,20,20);");
    }

    private static void highlightSuggestionFigure(int column, int row) {
        updateFigureStyleAt(column, row, "-fx-background-color: rgba(250,20,20,0.85);");
    }

    static ImageView loadFigureTexture(char image, char color) {
        var img = new ImageView("images/" + color + "_" + image + ".png");
        img.setFitHeight(85);
        img.setFitWidth(85);

        return img;
    }
}