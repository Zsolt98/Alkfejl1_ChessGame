package alkfejl1.chess.model.figures;

import java.util.ArrayList;

import alkfejl1.chess.GameLogic;
import alkfejl1.chess.model.Point;

public class FigureBishop extends FigureBase {
    public FigureBishop(char color, int x, int y) {
        super('f', color, x, y);
    }

    @Override
    public void getPossibleStepPositions(ArrayList<Point> outOptions) {
        getValidStepPositions(this, outOptions);
    }

    public static void getValidStepPositions(FigureBase self, ArrayList<Point> outOptions) {
        var column = self.column;
        var row = self.row;

        for(int checkRow = row + 1, checkColumn = column + 1; checkRow < 8 && checkColumn < 8; ++checkRow, ++checkColumn) {  // Down-Right
            GameLogic.markPosition(checkColumn, checkRow, outOptions, self);

            if(!GameLogic.isPositionEmpty(checkColumn, checkRow)) {
                break;
            }
        }

        for(int checkRow = row + 1, checkColumn = column - 1; checkRow < 8 && checkColumn > -1; ++checkRow, --checkColumn) {  // Down-Left
            GameLogic.markPosition(checkColumn, checkRow, outOptions, self);

            if(!GameLogic.isPositionEmpty(checkColumn, checkRow)) {
                break;
            }
        }

        for(int checkRow = row - 1, checkColumn = column - 1; checkRow > -1 && checkColumn > -1; --checkRow, --checkColumn) {  // Up-Left
            GameLogic.markPosition(checkColumn, checkRow, outOptions, self);

            if(!GameLogic.isPositionEmpty(checkColumn, checkRow)) {
                break;
            }
        }

        for(int checkRow = row - 1, checkColumn = column + 1; checkRow > -1 && checkColumn < 8; --checkRow, ++checkColumn) {  // Up-Right
            GameLogic.markPosition(checkColumn, checkRow, outOptions, self);

            if(!GameLogic.isPositionEmpty(checkColumn, checkRow)) {
                break;
            }
        }
    }

    @Override
    public String toString() {
        return "Bishop";
    }
}
