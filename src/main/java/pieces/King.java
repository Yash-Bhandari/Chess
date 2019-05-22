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
    public List<Position> getMoves(ChessBoard board, Position p) {
        List<Position> validMoves = new ArrayList<>();

        validMoves.add(p.add(1, 1));
        validMoves.add(p.add(1, 0));
        validMoves.add(p.add(1, -1));
        validMoves.add(p.add(-1, 1));
        validMoves.add(p.add(-1, 0));
        validMoves.add(p.add(-1, -1));

        Position enemyKing = new Position(0, 0); // Position of enemy king will definitely be found in loop
        for (Square s : board.getAllSquares()) {
            Piece piece = s.getPiece();
            if (piece != null && piece.getTeam() != getTeam()) {
                if (piece.getType() == PieceType.KING)
                    enemyKing = s.getPosition();
            }
        }

        for (int i = validMoves.size() - 1; i > -1; i--)
            if (!board.validSpot(validMoves.get(i), getTeam()) || validMoves.get(i).squaresAwayFrom(enemyKing) <= 1)
                validMoves.remove(i);
            else if (board.wouldBeChecked(p, validMoves.get(i)))
                validMoves.remove(i);

        return validMoves;
    }

    private List<Position> pawnCaptures(Square s) {
        List<Position> out = new ArrayList<>();
        int direction = s.getPiece().getTeam() == 0 ? -1 : 1;
        out.add(s.getPosition().add(direction, -1));
        out.add(s.getPosition().add(direction, 1));
        return out;
    }
}