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

    public Position add(int rowAdd, int colAdd) {
        return new Position(getRow() + rowAdd, getCol() + colAdd);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Position) {
            Position p = (Position)o;
            return p.getRow() == getRow() && p.getCol() == getCol();
        }
        return false;
    }

    @Override
    public String toString(){
        return String.format("[%d, %d]", row, col);
    }

}
