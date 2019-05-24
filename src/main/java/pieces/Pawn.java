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
    public List<Position> getMoves(ChessBoard board, Position p, boolean testForCheck) {
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

        for (Position m : getCaptures(board, p, testForCheck))
            if (board.validSpot(m, getTeam()) && board.pieceAt(m) != null)
                validMoves.add(m);

        if (testForCheck)
            pruneMoves(validMoves, board, p);

        return validMoves;
    }

    @Override
    public List<Position> getCaptures(ChessBoard board, Position p, boolean testForCheck) {
        ArrayList<Position> validMoves = new ArrayList<>();
        int direction = getTeam() == 0 ? -1 : 1;

        validMoves.add(p.add(direction, -1));
        validMoves.add(p.add(direction, 1));

        if (testForCheck)
            pruneMoves(validMoves, board, p);
        return validMoves;
    }
}
