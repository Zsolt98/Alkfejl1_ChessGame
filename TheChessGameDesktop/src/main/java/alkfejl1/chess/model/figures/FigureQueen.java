package alkfejl1.chess.model.figures;

import java.util.ArrayList;

import alkfejl1.chess.model.Point;

public class FigureQueen extends FigureBase {
    public FigureQueen(char color, int x, int y) {
        super('n', color, x, y);
    }

    @Override
    public void getPossibleStepPositions(ArrayList<Point> outOptions) {
        FigureRook.getValidStepPositions(this, outOptions);
        FigureBishop.getValidStepPositions(this, outOptions);
    }

    @Override
    public String toString() {
        return "Queen";
    }
}
