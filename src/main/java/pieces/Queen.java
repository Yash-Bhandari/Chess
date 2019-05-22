package pieces;

import board.ChessBoard;
import helper.Position;

import java.util.List;

public final class Queen extends Piece {
    public Queen(int team, boolean hasMoved) {
        super(Piece.PieceType.QUEEN, team, hasMoved);
    }

    @Override
    public List<Position> getMoves(ChessBoard board, Position selfPos) {
        return null;
    }
}
