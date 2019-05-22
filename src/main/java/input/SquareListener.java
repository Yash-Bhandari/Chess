package input;

import board.Square;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SquareListener implements ActionListener {

    private InputManager manager;

    /**
     * Only to be added to Squares
     */
    public SquareListener(InputManager manager) {
        this.manager = manager;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Square s = (Square)actionEvent.getSource();
        manager.squareClicked(s.getPosition());
    }
}
