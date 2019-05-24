package input;

import board.ChessBoard;
import helper.Position;
import game.Chess;

public class InputManager {
    ChessBoard board;
    Position selectedSquare;
    Chess game;
    boolean canMove; // Used for multiplayer
    private int team;

    public InputManager(Chess game) {
        this.game = game;
    }

    public InputManager(ChessBoard board, int team) {
        this.board = board;
        this.team = team;
    }

    public void setBoard(ChessBoard board){
        this.board = board;
    }

    public void squareClicked(Position pos) {
        if (board.pieceAt(pos) != null && board.pieceAt(pos).getTeam() == game.getWhoseTurn()) {
            if (selectedSquare == null) {
                select(pos);
            }
            else if (selectedSquare == pos) {
                deselect();
            }
            else {
                deselect();
                select(pos);
            }
        }
        else if (selectedSquare != null && board.validMoves(selectedSquare).contains(pos)) {
                Position temp = selectedSquare;
                deselect();
                game.makeMove(temp, pos, false);
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
