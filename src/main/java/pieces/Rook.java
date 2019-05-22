package pieces;

import board.ChessBoard;
import helper.Position;

import java.util.List;

public final class Rook extends Piece {
    public Rook(int team, boolean hasMoved) {
        super(PieceType.ROOK, team, hasMoved);
    }

    @Override
    public List<Position> getMoves(ChessBoard board, Position selfPos) {
        return null;
    }
}