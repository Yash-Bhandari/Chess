package pieces;

import board.ChessBoard;
import helper.Position;

import java.util.ArrayList;
import java.util.List;

public final class Rook extends Piece {
    public Rook(int team, boolean hasMoved) {
        super(PieceType.ROOK, team, hasMoved);
    }

    @Override
    public List<Position> getMoves(ChessBoard board, Position p) {
        List<Position> validMoves = new ArrayList<>();

        int up = 1;
        while (board.validSpot(p.add(-up, 0), getTeam())) {
            validMoves.add(p.add(-up++, 0));
        }

        int down = 1;
        while (board.validSpot(p.add(down, 0), getTeam())) {
            validMoves.add(p.add(down++, 0));
        }

        int right = 1;
        while (board.validSpot(p.add(0, right), getTeam())) {
            validMoves.add(p.add(0, right++));
        }

        int left = 1;
        while (board.validSpot(p.add(0, -left), getTeam())) {
            validMoves.add(p.add(0, -left++));
        }

        return validMoves;
    }
}