package io.github.mikolajbartela.solver;

public class Position {

    private final int COLUMN;
    private final int ROW;

    public Position(int row, int column) {
        this.ROW = row;
        this.COLUMN = column;
    }

    public int getROW() {
        return ROW;
    }

    public int getCOLUMN() {
        return COLUMN;
    }
}
