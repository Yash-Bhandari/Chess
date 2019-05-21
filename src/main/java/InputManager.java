public class InputManager {
    ChessBoard board;
    Position selectedSquare;
    boolean canMove; // Used for multiplayer

    public InputManager(ChessBoard board) {
        this.board = board;
    }

    public void squareClicked(Position pos) {
        assert pos != null;
        if (selectedSquare == null) {
            select(pos);
        } else if (selectedSquare != null && board.validMoves(selectedSquare).contains(pos)) {
            Position temp = selectedSquare;
            deselect();
            board.movePiece(temp, pos);
        } else
            deselect();

    }

    private void select(Position pos) {
        selectedSquare = pos;
        board.squareAt(selectedSquare).setSelected(true);
        for (Position move : board.validMoves(selectedSquare))
            board.squareAt(move).setHighlight(true);
    }

    private void deselect() {
        if (selectedSquare == null)
            return;
        board.squareAt(selectedSquare).setSelected(false);
        for (Position move : board.validMoves(selectedSquare))
            board.squareAt(move).setHighlight(false);
        selectedSquare = null;
    }
}
