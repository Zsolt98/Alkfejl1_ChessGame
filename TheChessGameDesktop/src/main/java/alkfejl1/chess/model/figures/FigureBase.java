package alkfejl1.chess.model.figures;

import java.util.ArrayList;

import alkfejl1.chess.GameLogic;
import alkfejl1.chess.model.Point;

public abstract class FigureBase {
    public int column;
    public int row;
    public final char type;
    public final char color;

    public FigureBase(char type, char color, int column, int row) {
        this.type = type;
        this.column = column;
        this.row = row;
        this.color = color;
    }

    public boolean canStep() {
        for(var pos : GameLogic.lastClickedFigureStepPositions) {
            if(pos.column == this.column && pos.row == this.row) {
                return true;
            }
        }
        return false;
    }

    public void afterSuccessfulStep() {}

    public abstract void getPossibleStepPositions(ArrayList<Point> outOptions);

    public static FigureBase createFigureByType(char type, char color, int column, int row) {
        switch(type) {
            case 'b': return new FigureRook(color, column, row);
            case 'l': return new FigureKnight(color, column, row);
            case 'f': return new FigureBishop(color, column, row);
            case 'k': return new FigureKing(color, column, row);
            case 'n': return new FigureQueen(color, column, row);
            case 'p': return new FigurePawn(color, column, row);

            default:  return new FigurePlaceHolder(column, row);
        }
    }

    public static char getTypeFromFigure(FigureBase figure) {
    	return figure instanceof FigureRook ? 'b' :
    		   figure instanceof FigureKnight ? 'l' :
    		   figure instanceof FigureBishop ? 'f' :
    		   figure instanceof FigureKing ? 'k' :
    		   figure instanceof FigureQueen ? 'n' :
    		   figure instanceof FigurePawn ? 'p' : '0';
    }
}
