import javax.swing.*;
import java.awt.*;

public class ChessBoard extends JPanel {

    private Square[][] board;

    public ChessBoard() {
        setLayout(new GridLayout(8, 8));
        board = new Square[8][8];
        for (int row = 0; row < board.length; row++)
            for (int col = 0; col < board[0].length; col++)
                add(board[row][col] = new Square(new Point(row, col)));

        setPieces();
    }

    private void setPieces(){
        // Black and white pawns
        for (int col = 0; col < 8; col++) {
            addPiece(Piece.PieceType.PAWN, 1, new Point(1, col));
            addPiece(Piece.PieceType.PAWN, 0, new Point(6, col));
        }

        // Black backline
        addPiece(Piece.PieceType.ROOK, 1, new Point(0, 0));
        addPiece(Piece.PieceType.ROOK, 1, new Point(0, 7));
        addPiece(Piece.PieceType.KNIGHT, 1, new Point(0, 6));
        addPiece(Piece.PieceType.KNIGHT, 1, new Point(0, 1));
        addPiece(Piece.PieceType.BISHOP, 1, new Point(0, 2));
        addPiece(Piece.PieceType.BISHOP, 1, new Point(0, 5));
        addPiece(Piece.PieceType.QUEEN, 1, new Point(0, 3));
        addPiece(Piece.PieceType.KING, 1, new Point(0, 4));

        // White backline
        addPiece(Piece.PieceType.ROOK, 0, new Point(7, 0));
        addPiece(Piece.PieceType.ROOK, 0, new Point(7, 7));
        addPiece(Piece.PieceType.KNIGHT, 0, new Point(7, 6));
        addPiece(Piece.PieceType.KNIGHT, 0, new Point(7, 1));
        addPiece(Piece.PieceType.BISHOP, 0, new Point(7, 2));
        addPiece(Piece.PieceType.BISHOP, 0, new Point(7, 5));
        addPiece(Piece.PieceType.QUEEN, 0, new Point(7, 3));
        addPiece(Piece.PieceType.KING, 0, new Point(7, 4));
    }

    private void addPiece(Piece.PieceType type, int team, Point pos) {
        try {
            board[pos.x][pos.y].setPiece(new Piece(type, team, pos));
        } catch (NullPointerException e) {
            System.out.println(pos);
        }
    }

}
