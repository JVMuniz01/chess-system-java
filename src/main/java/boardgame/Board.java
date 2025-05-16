
package boardgame;

public class Board {
    
    private int raws;
    private int columns;
    private Piece[][] pieces;

    public Board(int raws, int columns) {
        this.raws = raws;
        this.columns = columns;
        pieces = new Piece[raws][columns];
    }

    public int getRaws() {
        return raws;
    }

    public void setRaws(int raws) {
        this.raws = raws;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }
    
}
