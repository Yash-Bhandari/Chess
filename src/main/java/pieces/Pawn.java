package pieces;

import board.ChessBoard;
import helper.Position;

import java.util.ArrayList;
import java.util.List;

public final class Pawn extends Piece {
    public Pawn(int team, boolean hasMoved) {
        super(Piece.PieceType.PAWN, team, hasMoved);
    }


    @Override
    public List<Position> getMoves(ChessBoard board, Position p) {
        ArrayList<Position> validMoves = new ArrayList<>();
        int direction = getTeam() == 0 ? -1 : 1;
        Position m1 = new Position(p.getRow() + direction, p.getCol());
        if (board.pieceAt(m1) == null)
            validMoves.add(m1);

        if (!hasItMoved()) {
            Position m2 = new Position(p.getRow() + 2 * direction, p.getCol());
            if (board.validSpot(m2, getTeam()) && board.pieceAt(m2) == null)
                validMoves.add(m2);
        }

        Position cLeft = new Position(p.getRow() + direction, p.getCol() - 1);
        Position cRight = new Position(p.getRow() + direction, p.getCol() + 1);
        if (board.pieceAt(cLeft) != null && board.pieceAt(cLeft).getTeam() != getTeam())
            validMoves.add(cLeft);
        if (board.pieceAt(cRight) != null && board.pieceAt(cRight).getTeam() != getTeam())
            validMoves.add(cRight);

        return validMoves;
    }
}
