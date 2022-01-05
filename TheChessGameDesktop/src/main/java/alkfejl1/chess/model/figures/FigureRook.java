package alkfejl1.chess.model.figures;

import java.util.ArrayList;

import alkfejl1.chess.GameLogic;
import alkfejl1.chess.model.Point;

public class FigureRook extends FigureBase {
    public FigureRook(char color, int x, int y) {
        super('b', color, x, y);
    }

    @Override
    public void getPossibleStepPositions(ArrayList<Point> outOptions) {
        getValidStepPositions(this, outOptions);
    }

    public static void getValidStepPositions(FigureBase self, ArrayList<Point> outOptions) {
        var column = self.column;
        var row = self.row;

        for(var checkRow = row + 1; checkRow < 8; ++checkRow) {      // Down
            GameLogic.markPosition(column, checkRow, outOptions, self);

            if(!GameLogic.isPositionEmpty(column, checkRow)) {
                break;
            }
        }

        for(var checkRow = row - 1; checkRow > -1; --checkRow) {      // Up
            GameLogic.markPosition(column, checkRow, outOptions, self);

            if(!GameLogic.isPositionEmpty(column, checkRow)) {
                break;
            }
        }

        for(var checkColumn = column + 1; checkColumn < 8; ++checkColumn) {      // Left
            GameLogic.markPosition(checkColumn, row, outOptions, self);

            if(!GameLogic.isPositionEmpty(checkColumn, row)) {
                break;
            }
        }

        for(var checkColumn = column - 1; checkColumn > -1; --checkColumn) {      // Left
            GameLogic.markPosition(checkColumn, row, outOptions, self);

            if(!GameLogic.isPositionEmpty(checkColumn, row)) {
                break;
            }
        }
    }

    @Override
    public String toString() {
        return "Rook";
    }
}
