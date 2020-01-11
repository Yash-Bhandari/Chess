package board;

import helper.Position;
import input.InputManager;
import input.SquareListener;
import pieces.Piece;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChessBoard{

    private Square[][] board;
    private Position[] kings = new Position[2];
    private JPanel content;


    public ChessBoard(InputManager manager) {
        content = new JPanel();
        content.setLayout(new GridLayout(8, 8));
        board = new Square[8][8];
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                board[row][col] = new Square(new Position(row, col), new SquareListener(manager));
                content.add(board[row][col]);
            }
        }
        setPieces();
    }

    private void setPieces() {
        System.out.println("adding pieces");
        // Black and white pawns
        for (int col = 0; col < 8; col++) {
            addPiece(Piece.PieceType.PAWN, 1, new Position(1, col));
            addPiece(Piece.PieceType.PAWN, 0, new Position(6, col));
        }

        // Black backline
        addPiece(Piece.PieceType.ROOK, 1, new Position(0, 0));
        addPiece(Piece.PieceType.ROOK, 1, new Position(0, 7));
        addPiece(Piece.PieceType.KNIGHT, 1, new Position(0, 6));
        addPiece(Piece.PieceType.KNIGHT, 1, new Position(0, 1));
        addPiece(Piece.PieceType.BISHOP, 1, new Position(0, 2));
        addPiece(Piece.PieceType.BISHOP, 1, new Position(0, 5));
        addPiece(Piece.PieceType.QUEEN, 1, new Position(0, 3));
        addPiece(Piece.PieceType.KING, 1, new Position(0, 4));
        kings[1] = new Position(0, 4);

        // White backline
        addPiece(Piece.PieceType.ROOK, 0, new Position(7, 0));
        addPiece(Piece.PieceType.ROOK, 0, new Position(7, 7));
        addPiece(Piece.PieceType.KNIGHT, 0, new Position(7, 6));
        addPiece(Piece.PieceType.KNIGHT, 0, new Position(7, 1));
        addPiece(Piece.PieceType.BISHOP, 0, new Position(7, 2));
        addPiece(Piece.PieceType.BISHOP, 0, new Position(7, 5));
        addPiece(Piece.PieceType.QUEEN, 0, new Position(7, 3));
        addPiece(Piece.PieceType.KING, 0, new Position(7, 4));
        kings[0] = new Position(7, 4);
    }

    private void addPiece(Piece.PieceType type, int team, Position pos) {
        board[pos.getRow()][pos.getCol()].setPiece(Piece.makePiece(type, team, false));

    }

    public boolean inBounds(Position pos) {
        return pos.getRow() >= 0 && pos.getRow() <= 7 && pos.getCol() >= 0 && pos.getCol() <= 7;
    }


    public Square squareAt(Position pos) {
        if (!inBounds(pos))
            System.out.println(pos);
        return board[pos.getRow()][pos.getCol()];
    }

    public Piece pieceAt(Position pos) {
        if (inBounds(pos))
            return squareAt(pos).getPiece();
        return null;
    }

    // Moves pieces from start to end, deleting whatever pieces was at end and updating kings location if moved
    public void movePiece(Position start, Position end) {
        for (int i = 0; i < 2; i++) {
            if (pieceAt(kings[i]) == pieceAt(start))
                kings[i] = end;
        }

        squareAt(end).setPiece(squareAt(start).getPiece().moved());
        squareAt(start).setPiece(null);
    }

    /**
     * @param pos  position on chessboard
     * @param team 0 for white, 1 for black
     * @return true if specified position is on the board (0,0 <= x,y <= 7,7) and does not contain allied pieces
     */
    public boolean validSpot(Position pos, int team) {
        return inBounds(pos) && (pieceAt(pos) == null || pieceAt(pos).getTeam() != team);
    }

    public List<Position> validMoves(Position pos) {
        Piece p = pieceAt(pos);
        if (p == null)
            return new ArrayList<>();
        return pieceAt(pos).getMoves(this, pos, true);
    }

    public List<Square> getAllSquares() {
        List<Square> squares = new ArrayList<>();
        for (int row = 0; row < 8; row++)
            squares.addAll(Arrays.asList(board[row]));
        return squares;
    }

    public boolean wouldBeChecked(Position start, Position end) {
        if (pieceAt(start).getType() == Piece.PieceType.KING)
            kings[pieceAt(start).getTeam()] = end;

        Piece temp = pieceAt(end);
        squareAt(end).setPiece(pieceAt(start));
        squareAt(start).setPiece(null);

        boolean ans = isChecked(pieceAt(end).getTeam());

        squareAt(start).setPiece(pieceAt(end));
        squareAt(end).setPiece(temp);

        if (pieceAt(start).getType() == Piece.PieceType.KING)
            kings[pieceAt(start).getTeam()] = start;

        return ans;
    }

    public JPanel getContent() {
        return content;
    }

    private boolean isChecked(int team) {
        for (Square s : getAllSquares())
            if (s.getPiece() != null && s.getPiece().getTeam() != team)
                if (s.getPiece().getType() != Piece.PieceType.KING)
                    for (Position m : s.getPiece().getCaptures(this, s.getPosition(), false))
                        if (m.equals(kings[team]))
                            return true;

        return kings[0].squaresAwayFrom(kings[1]) == 1;
    }

    /**
     *
     * @param whoseTurn
     * @return true if player whoseTurn has no valid moves
     */
    public boolean checkGameOver(int whoseTurn) {
        if (isChecked(whoseTurn)) {
            boolean hasMoves = false;
            for (Square s : getAllSquares()) {
                if (s.getPiece() != null && s.getPiece().getTeam() == whoseTurn)
                    if ( s.getPiece().getMoves(this, s.getPosition(), true).size() > 0)
                        hasMoves = true;
            }
            return !hasMoves;
        }
        return false;
    }


    public List<Piece> getAllPieces() {
        List<Piece> pieces = new ArrayList<>();
        Piece p;
        for (int row = 0; row < 8; row++)
            for (int col = 0; col < 8; col++)
                if ((p = pieceAt(new Position(row, col))) != null)
                    pieces.add(p);

        return pieces;
    }
}
