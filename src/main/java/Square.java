import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Square extends JButton {
    private static final Color LIGHT = new Color(238, 238, 210);
    private static final Color DARK = new Color(118, 150, 86);
    private static final Color HIGHLIGHT = new Color(186, 202, 68);

    private static final int WIDTH = 60;
    private  static final int HEIGHT = 60;


    private final Point pos;
    private final Color background; // default background color

    private boolean isSelected;
    private boolean isHighlighted;
    private Piece piece;


    public Square(Point pos) {
        super();
        this.pos = pos;
        background = pos.x % 2 == pos.y % 2 ? DARK : LIGHT;
        setBackground(background);
        setPiece(null);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }

    public void setHighlight(boolean b) {
        isHighlighted = b;
        setBackground(isHighlighted ? HIGHLIGHT : background);
    }

    public void setPiece(Piece p) {
        piece = p;
        if (piece != null)
            setIcon(new ImageIcon(p.getImage()));
        else
            setIcon(new ImageIcon(new BufferedImage(60, 60, BufferedImage.TYPE_INT_ARGB)));
    }

    public Piece getPiece() {
        return piece;
    }
}
