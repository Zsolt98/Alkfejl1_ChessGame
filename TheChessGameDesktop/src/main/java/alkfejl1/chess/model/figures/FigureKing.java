package alkfejl1.chess.model.figures;

import java.util.ArrayList;

import alkfejl1.chess.GameLogic;
import alkfejl1.chess.model.Point;

public class FigureKing extends FigureBase {
    public FigureKing(char color, int x, int y) {
        super('k', color, x, y);
    }

    @Override
    public void getPossibleStepPositions(ArrayList<Point> outOptions) {
        var column = this.column;
        var row = this.row;

        // Down with crosses
        GameLogic.markPosition(column, row + 1, outOptions, this);
        GameLogic.markPosition(column + 1, row + 1, outOptions, this);
        GameLogic.markPosition(column - 1, row + 1, outOptions, this);

        // Up with crosses
        GameLogic.markPosition(column, row - 1, outOptions, this);
        GameLogic.markPosition(column + 1, row - 1, outOptions, this);
        GameLogic.markPosition(column - 1, row - 1, outOptions, this);

        // Left/Right
        GameLogic.markPosition(column - 1, row, outOptions, this);
        GameLogic.markPosition(column + 1, row, outOptions, this);
    }

    @Override
    public String toString() {
        return "King";
    }
}
