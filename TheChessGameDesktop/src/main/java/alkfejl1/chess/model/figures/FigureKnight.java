package alkfejl1.chess.model.figures;

import java.util.ArrayList;

import alkfejl1.chess.GameLogic;
import alkfejl1.chess.model.Point;

public class FigureKnight extends FigureBase {
    public FigureKnight(char color, int x, int y) {
        super('l', color, x, y);
    }

    @Override
    public void getPossibleStepPositions(ArrayList<Point> outOptions) {
        var column = this.column;
        var row = this.row;

        // Up-Left
        GameLogic.markPosition(column - 2, row + 1, outOptions, this);
        GameLogic.markPosition(column - 1, row + 2, outOptions, this);

        // Up-Right
        GameLogic.markPosition(column + 2, row + 1, outOptions, this);
        GameLogic.markPosition(column + 1, row + 2, outOptions, this);

        // Down-Left
        GameLogic.markPosition(column - 2, row - 1, outOptions, this);
        GameLogic.markPosition(column - 1, row - 2, outOptions, this);

        // Down-Right
        GameLogic.markPosition(column + 2, row - 1, outOptions, this);
        GameLogic.markPosition(column + 1, row - 2, outOptions, this);
    }

    @Override
    public String toString() {
        return "Knight";
    }
}
