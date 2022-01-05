package alkfejl1.chess.model.figures;

import java.util.ArrayList;
import alkfejl1.chess.*;
import alkfejl1.chess.model.Point;

public class FigurePawn extends FigureBase {
    public FigurePawn(char color, int x, int y) {
        super('p', color, x, y);
    }

    @Override
    public void getPossibleStepPositions(ArrayList<Point> outOptions) {
        var row = this.row;
        var column = this.column;
        var color = this.color;
        var cornerPositionRowOffset = color == 'w' ? -1 : 1;

        if(!GameLogic.isEnemyAtPosition(column, row + cornerPositionRowOffset, this)) {
            GameLogic.markPosition(column, row + cornerPositionRowOffset, outOptions, this);          // Up/Down 1
        }
        var upDown2RowOffset = cornerPositionRowOffset * 2;
        var onStartingRow = color == 'w' ? row == 6 : row == 1;
        if(onStartingRow && GameLogic.isPositionEmpty(column, row + upDown2RowOffset)) {
            GameLogic.markPosition(column, row + upDown2RowOffset, outOptions, this);             // Up/Down 2
        }

        if(GameLogic.isEnemyAtPosition(column - 1, row + cornerPositionRowOffset, this)) {
            GameLogic.markPosition(column - 1, row + cornerPositionRowOffset, outOptions, this);  // Cross left
        }

        if(GameLogic.isEnemyAtPosition(column + 1, row + cornerPositionRowOffset, this)) {
            GameLogic.markPosition(column + 1, row + cornerPositionRowOffset, outOptions, this);  // Cross right
        }
    }

    @Override
    public void afterSuccessfulStep() {
        if(row == 0 || row == 7) {
            var figuresList = color == 'w' ? GameLogic.whiteKnockedFigures : GameLogic.blackKnockedFigures;

            if(!figuresList.isEmpty()) {
                HelperClass.showFigureChoiceDialog(figuresList, this);
            }
        }
    }

    @Override
    public String toString() {
        return "Pawn";
    }
}
