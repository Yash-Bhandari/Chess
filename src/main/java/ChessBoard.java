import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ChessBoard extends JPanel {

    private Square[][] board;


    public ChessBoard() {
        InputManager manager = new InputManager(this);
        setLayout(new GridLayout(8, 8));
        board = new Square[8][8];
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                board[row][col] = new Square(new Position(row, col), new SquareListener(manager));
                add(board[row][col]);
            }
        }
        setPieces();
    }

    private void setPieces(){
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

        // White backline
        addPiece(Piece.PieceType.ROOK, 0, new Position(7, 0));
        addPiece(Piece.PieceType.ROOK, 0, new Position(7, 7));
        addPiece(Piece.PieceType.KNIGHT, 0, new Position(7, 6));
        addPiece(Piece.PieceType.KNIGHT, 0, new Position(7, 1));
        addPiece(Piece.PieceType.BISHOP, 0, new Position(7, 2));
        addPiece(Piece.PieceType.BISHOP, 0, new Position(7, 5));
        addPiece(Piece.PieceType.QUEEN, 0, new Position(7, 3));
        addPiece(Piece.PieceType.KING, 0, new Position(7, 4));
    }

    private void addPiece(Piece.PieceType type, int team, Position pos) {
            board[pos.getRow()][pos.getCol()].setPiece(new Piece(type, team));

    }

    public Square squareAt(Position pos) {
        return board[pos.getRow()][pos.getCol()];
    }

    public Piece pieceAt(Position pos) {
        return squareAt(pos).getPiece();
    }

    // Moves piece from start to end, deleting whatever piece was at end
    public void movePiece(Position start, Position end) {
        squareAt(end).setPiece(squareAt(start).getPiece());
        squareAt(start).setPiece(null);
        pieceAt(end).setHasMoved(true);
    }

    /**
     *
     * @param pos position on chessboard
     * @param team 0 for white, 1 for black
     * @return true if specified position is on the board (0,0 <= x,y <= 7,7) and does not contain allied piece
     */
    private boolean validSpot(Position pos, int team) {
        if (pos.getRow() < 0 || pos.getRow() > 7 || pos.getCol() < 0 || pos.getCol() > 7)
            return false;
        return pieceAt(pos) == null || pieceAt(pos).getTeam() != team;
    }

    public ArrayList<Position> validMoves(Position pos) {
        Piece p = pieceAt(pos);
        ArrayList<Position> validMoves = new ArrayList<>();
        if (p.getType() == Piece.PieceType.PAWN)
            return pawnMoves(pos, p);
        return validMoves;
    }

    //move this to a pawn class
    private ArrayList<Position> pawnMoves(Position pos, Piece p) {
        ArrayList<Position> validMoves = new ArrayList<>();
        int direction = p.getTeam() == 0 ? -1 : 1;
        Position m1 = new Position(pos.getRow() + direction, pos.getCol());
        if (validSpot(m1, p.getTeam()))
            validMoves.add(m1);

        if (!p.hasMoved()) {
            Position m2 = new Position(pos.getRow() + 2 * direction, pos.getCol());
            if (validSpot(m2, p.getTeam()))
                validMoves.add(m2);
        }
        return validMoves;
    }


}
