public class InputManager {
    ChessBoard board;
    Position selectedSquare;

    public InputManager(ChessBoard board) {
        this.board = board;
    }

    public void squareClicked(Position pos) {
        if (pos.equals(selectedSquare))
            deselect();
        else if (selectedSquare != null && board.validMoves(selectedSquare).contains(pos)) {
            board.movePiece(selectedSquare, pos);
            deselect();
        }
        else {
            select(pos);
        }
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
