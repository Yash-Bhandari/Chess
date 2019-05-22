package helper;

public final class Position {
    private final int row;
    private final int col;

    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    /**
     * @param rowAdd number of rows to go down
     * @param colAdd number of columns to go right
     * @return new Position at row = oldRow + rowAdd, col = oldCol + colAdd
     */
    public Position add(int rowAdd, int colAdd) {
        return new Position(getRow() + rowAdd, getCol() + colAdd);
    }

    /**
     * @param p position to compare to
     * @return The distance this point is from p (diagonals are equal to 1). Number of moves a king would take to travel
     * from this point to p.
     */
    public int squaresAwayFrom(Position p) {
        return Math.max(Math.abs(p.getRow() - getRow()), Math.abs(p.getCol() - getCol()));
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Position) {
            Position p = (Position) o;
            return p.getRow() == getRow() && p.getCol() == getCol();
        }
        return false;
    }

    @Override
    public String toString() {
        return String.format("[%d, %d]", row, col);
    }

}
