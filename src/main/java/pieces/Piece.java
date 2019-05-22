package pieces;

import board.ChessBoard;
import helper.Position;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class Piece {
    public enum PieceType {
        PAWN, KNIGHT, BISHOP, ROOK, QUEEN, KING;

        private final BufferedImage whiteImage;
        private final BufferedImage blackImage;

        PieceType() {
            whiteImage = loadImage("w");
            blackImage = loadImage("b");
        }

        private BufferedImage loadImage(String team) {
            try {
                return ImageIO.read(new File(String.format("assets/%s.png", team + this.name())));
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    private transient final BufferedImage image;
    private final PieceType type;
    private final int team;
    private final boolean hasMoved;


    public static Piece makePiece(PieceType type, int team, boolean hasMoved) {
        switch (type) {
            case PAWN:
                return new Pawn(team, hasMoved);
            case KNIGHT:
                return new Knight(team, hasMoved);
            case BISHOP:
                return new Bishop(team, hasMoved);
            case ROOK:
                return new Rook(team, hasMoved);
            case QUEEN:
                return new Queen(team, hasMoved);
            case KING:
                return new King(team, hasMoved);
        }
        return null; // Impossible to arrive here
    }

    protected Piece(PieceType type, int team, boolean hasMoved) {
        this.type = type;
        this.team = team;
        this.hasMoved = hasMoved;
        image = team == 0 ? type.whiteImage : type.blackImage;
    }

    public BufferedImage getImage() {
        return image;
    }

    /**
     * Call whenever the piece moves
     *
     * @return a piece whose hasMoved field
     */
    public Piece moved() {
        if (hasMoved)
            return this;
        return Piece.makePiece(type, team, true);
    }

    public PieceType getType() {
        return type;
    }

    public boolean hasItMoved() {
        return hasMoved;
    }

    public int getTeam() {
        return team;
    }

    public abstract List<Position> getMoves(ChessBoard board, Position p);

}
