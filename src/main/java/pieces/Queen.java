package pieces;

import board.ChessBoard;
import helper.Position;

import java.util.ArrayList;
import java.util.List;

public final class Queen extends Piece {
    public Queen(int team, boolean hasMoved) {
        super(Piece.PieceType.QUEEN, team, hasMoved);
    }

    @Override
    public List<Position> getMoves(ChessBoard board, Position p, boolean testForCheck) {
        List<Position> validMoves = new ArrayList<>();
        Position move;

        int up = 1;
        while (board.validSpot(move = p.add(-up, 0), getTeam())) {
            validMoves.add(move);
            if (board.pieceAt(move) != null)
                break;
            up++;
        }

        int down = 1;
        while (board.validSpot(move = p.add(down, 0), getTeam())) {
            validMoves.add(move);
            if (board.pieceAt(move) != null)
                break;
            down++;
        }

        int right = 1;
        while (board.validSpot(move = p.add(0, right), getTeam())) {
            validMoves.add(move);
            if (board.pieceAt(move) != null)
                break;
            right++;
        }

        int left = 1;
        while (board.validSpot(move = p.add(0, -left), getTeam())) {
            validMoves.add(move);
            if (board.pieceAt(move) != null)
                break;
            left++;
        }

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
