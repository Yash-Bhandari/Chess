package pieces;

import board.ChessBoard;
import helper.Position;

import java.util.ArrayList;
import java.util.List;

public final class Knight extends Piece {
    public Knight(int team, boolean hasMoved) {
        super(Piece.PieceType.KNIGHT, team, hasMoved);
    }

    @Override
    public List<Position> getMoves(ChessBoard board, Position p) {
        List<Position> validMoves = new ArrayList<>();

        validMoves.add(p.add(1, 2));
        validMoves.add(p.add(2, 1));
        validMoves.add(p.add(1, -2));
        validMoves.add(p.add(2, -1));
        validMoves.add(p.add(-1, 2));
        validMoves.add(p.add(-2, 1));
        validMoves.add(p.add(-1, -2));
        validMoves.add(p.add(-2, -1));

        for (int i = validMoves.size() - 1; i > -1; i--)
            if (!board.validSpot(validMoves.get(i), getTeam()))
                validMoves.remove(i);

        return validMoves;
    }
}
