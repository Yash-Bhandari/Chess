package pieces;

import board.ChessBoard;
import helper.Position;

import java.util.ArrayList;
import java.util.List;

public final class Bishop extends Piece {
    public Bishop(int team, boolean hasMoved) {
        super(Piece.PieceType.BISHOP, team, hasMoved);
    }

    @Override
    public List<Position> getMoves(ChessBoard board, Position p, boolean testForCheck) {
        List<Position> validMoves = new ArrayList<>();
        Position move;

        int upLeft = 1;
        while (board.validSpot(move = p.add(-upLeft, -upLeft), getTeam())) {
            validMoves.add(move);
            if (board.pieceAt(move) != null)
                break;
            upLeft++;

        }

        int downLeft = 1;
        while (board.validSpot(move = p.add(downLeft, -downLeft), getTeam())) {
            validMoves.add(move);
            if (board.pieceAt(move) != null)
                break;
            downLeft++;
        }

        int upRight = 1;
        while (board.validSpot(move = p.add(-upRight, upRight), getTeam())) {
            validMoves.add(move);
            if (board.pieceAt(move) != null)
                break;
            upRight++;
        }

        int downRight = 1;
        while (board.validSpot(move = p.add(downRight, downRight), getTeam())) {
            validMoves.add(move);
            if (board.pieceAt(move) != null)
                break;
            downRight++;
        }

        if (testForCheck)
            pruneMoves(validMoves, board, p);

        return validMoves;
    }
}
