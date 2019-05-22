package board;

import helper.Position;
import pieces.Piece;

public class GameState {
    private final Piece [][] pieces;
    private final int turn;


    public GameState madeMove(ChessBoard board) {
        int newTurn = turn == 0 ? 1 : 0;

        return new GameState(board, newTurn);
    }

    public GameState(ChessBoard board, int turn) {
        pieces = new Piece[8][8];
        for (int row = 0; row < 8; row++)
            for (int col = 0; col < 8; col++)
                pieces[row][col] = board.pieceAt(new Position(row, col));

        this.turn = turn;
    }

    public void setBoard(ChessBoard board) {
        for (int row = 0; row < 8; row++)
            for (int col = 0; col < 8; col++)
                board.squareAt(new Position(row, col)).setPiece(pieces[row][col]);
    }

    public int getTurn() {return turn;}


}
