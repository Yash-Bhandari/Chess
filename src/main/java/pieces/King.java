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


        validMoves.addAll(castle(board, p));

        pruneMoves(validMoves, board, p);

        return validMoves;
    }

    // TODO
    private List<Position> castle(ChessBoard board, Position p){
        ArrayList<Position> validMoves = new ArrayList<>();
        if (hasItMoved())
            return validMoves;

        Piece leftRook = board.pieceAt(new Position(getTeam() == 0 ? 7 : 7, 0));
        Piece rightRook = board.pieceAt(new Position(getTeam() == 0 ? 7 : 7, 0));

        if (leftRook != null && leftRook.getType() == PieceType.ROOK && !leftRook.hasItMoved()) {
            boolean blocked = false;
            //or (int i = 1; i < 4)
        }
        return validMoves;
    }


}