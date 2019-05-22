package board;

import helper.Position;
import pieces.Piece;

public class GameState {
    private final Piece [][] pieces;
    private final int whoseTurn;
    private final int turnNumber;

    public GameState madeMove(ChessBoard board) {

        return new GameState(board, (turnNumber + 1) % 2, turnNumber + 1);
    }

    public GameState(ChessBoard board, int whoseTurn, int turnNumber) {
        pieces = new Piece[8][8];
        for (int row = 0; row < 8; row++)
            for (int col = 0; col < 8; col++)
                pieces[row][col] = board.pieceAt(new Position(row, col));

        this.whoseTurn = whoseTurn;
        this.turnNumber = turnNumber;
    }

    public void setBoard(ChessBoard board) {
        for (int row = 0; row < 8; row++)
            for (int col = 0; col < 8; col++)
                board.squareAt(new Position(row, col)).setPiece(pieces[row][col]);
    }

    public int getWhoseTurn() {return whoseTurn;}


}
