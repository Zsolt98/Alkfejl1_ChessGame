package alkfejl1.chess.model.figures;

import java.util.ArrayList;

import alkfejl1.chess.model.Point;

public class FigurePlaceHolder extends FigureBase {
    public FigurePlaceHolder(int x, int y) {
        super('0', '0', x, y);
    }

    @Override
    public void getPossibleStepPositions(ArrayList<Point> outOptions) {}

    @Override
    public String toString() {
        return "Placeholder";
    }
}
