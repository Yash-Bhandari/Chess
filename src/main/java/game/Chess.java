package game;

import board.ChessBoard;
import board.GameState;
import helper.Position;
import input.InputManager;

import javax.swing.*;
import java.util.Stack;

public class Chess {
    JPanel content;

    Stack<GameState> gameStates;
    ChessBoard board;
    InputManager manager;

    public Chess() {
        manager = new InputManager(this);
        board = new ChessBoard(manager);
        manager.setBoard(board);

        content = new JPanel();
        content.add(board.getContent());
        //content.add(new JToolBar());



        gameStates = new Stack<>();
        gameStates.push(new GameState(board, 0, 0));
    }

    public void makeMove(Position start, Position end, boolean isCastling) {
        board.movePiece(start, end);
        gameStates.push(gameStates.peek().madeMove(board));
        if (board.checkGameOver(gameStates.peek().getWhoseTurn())) {
            String winner = gameStates.peek().getWhoseTurn() == 1 ? "White" : "Black";
            JOptionPane.showMessageDialog(board.getContent(), String.format("Checkmate. %s has won.", winner));
        }
    }

    public int getWhoseTurn() {
        return gameStates.peek().getWhoseTurn();
    }

    public void undo() {
        gameStates.pop().setBoard(board);
    }

    public JPanel getContent() {
        return content;
    }

    private void newGame() {
        board = new ChessBoard(manager);
    }
}
