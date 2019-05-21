import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Piece {
    public enum PieceType {
        PAWN, KNIGHT, BISHOP, ROOK, QUEEN, KING;

        public BufferedImage loadImage(String team) {
            try {
                return ImageIO.read(new File(String.format("assets/%s.png", team + this.name())));
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    private transient final BufferedImage image;
    private Position pos;
    private final PieceType type;
    private final int team;
    private boolean hasMoved = false;

    /**
     *
     * @param type type of piece from PieceType
     * @param team 0 for white, 1 for black
     */
    public Piece(PieceType type, int team) {
        this.type = type;
        this.team = team;
        image = type.loadImage(team == 0 ? "w" : "b");
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setHasMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }

    public PieceType getType() {
        return type;
    }

    public boolean hasMoved() {
        return hasMoved;
    }

    public int getTeam() {
        return team;
    }

}
