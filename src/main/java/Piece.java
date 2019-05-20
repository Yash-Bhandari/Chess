import javax.imageio.ImageIO;
import java.awt.*;
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
    private Point pos;
    private final PieceType type;

    /**
     *
     * @param type type of piece from PieceType
     * @param team 0 for white, 1 for black
     * @param pos coordinates on board
     */
    public Piece(PieceType type, int team, Point pos) {
        this.pos = pos;
        this.type = type;
        image = type.loadImage(team == 0 ? "w" : "b");
    }

    public BufferedImage getImage() {
        return image;
    }
}
