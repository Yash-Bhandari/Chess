import javax.swing.*;

public class Chess {
    public static final int WIDTH = 500;
    public static final int HEIGHT = 600;
    JFrame program;
    JPanel content;

    public Chess() {
        program = new JFrame("Chess");
        program.setVisible(true);
        program.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        content = new JPanel();
        content.add(new ChessBoard());
        program.add(content);
        program.setSize(WIDTH, HEIGHT);
    }

    public static void main(String[] args) {
        new Chess();
    }
}
