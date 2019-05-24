package board;

import helper.Position;
import input.SquareListener;
import pieces.Piece;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Square extends JButton {
    private static final Color LIGHT = new Color(238, 238, 210);
    private static final Color DARK = new Color(118, 150, 86);
    private static final Color HIGHLIGHT = new Color(186, 202, 68);
    private static final Color SELECT = new Color(150, 100, 68);


    private static final int WIDTH = 60;
    private static final int HEIGHT = 60;


    private final Position pos;
    private final Color background; // default background color

    private boolean selected;
    private boolean highlighted;
    private Piece piece;


    public Square(Position pos, SquareListener listener) {
        super(String.format("%d%d",pos.getRow(), pos.getCol()));
        this.pos = pos;
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        background = pos.getRow() % 2 == pos.getCol() % 2 ? DARK : LIGHT;
        setBackground(background);
        setPiece(null);
        setBorderPainted(false);
        setHorizontalAlignment(SwingConstants.CENTER);
        setVerticalAlignment(SwingConstants.CENTER);
        addActionListener(listener);
    }

    public void setHighlight(boolean b) {
        highlighted = b;
        setBackground(highlighted ? HIGHLIGHT : background);
    }

    public void setSelected(boolean b) {
        selected = b;
        setBackground(selected ? SELECT : background);
    }

    public void setPiece(Piece p) {
        piece = p;
        if (piece != null)
            setIcon(new ImageIcon(p.getImage()));
        else
            setIcon(new ImageIcon(new BufferedImage(60, 60, BufferedImage.TYPE_INT_ARGB)));
    }

    public Position getPosition() {
        return pos;
    }

    public Piece getPiece() {
        return piece;
    }
}
