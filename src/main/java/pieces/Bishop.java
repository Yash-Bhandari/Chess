package pieces;

import board.ChessBoard;
import helper.Position;

import java.util.List;

public final class Bishop extends Piece {
    public Bishop(int team, boolean hasMoved) {
        super(Piece.PieceType.BISHOP, team, hasMoved);
    }

    @Override
    public List<Position> getMoves(ChessBoard board, Position selfPos) {
        return null;
    }
}
