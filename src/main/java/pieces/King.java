package pieces;

import board.ChessBoard;
import board.Square;
import helper.Position;

import java.util.ArrayList;
import java.util.List;

public final class King extends Piece {
    public King(int team, boolean hasMoved) {
        super(Piece.PieceType.KING, team, hasMoved);
    }

    @Override
    public List<Position> getMoves(ChessBoard board, Position p, boolean testForCheck) {
        List<Position> validMoves = new ArrayList<>();

        validMoves.add(p.add(1, 1));
        validMoves.add(p.add(1, 0));
        validMoves.add(p.add(1, -1));
        validMoves.add(p.add(-1, 1));
        validMoves.add(p.add(-1, 0));
        validMoves.add(p.add(-1, -1));
        validMoves.add(p.add(0, 1));
        validMoves.add(p.add(0, -1));



        pruneMoves(validMoves, board, p);

        return validMoves;
    }

}