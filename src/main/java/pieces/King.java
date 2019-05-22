package pieces;

import board.ChessBoard;
import helper.Position;

import java.util.List;

public final class King extends Piece {
    public King(int team, boolean hasMoved) {
        super(Piece.PieceType.KING, team, hasMoved);
    }

    @Override
    public List<Position> getMoves(ChessBoard board, Position selfPos) {
        return null;
    }
}