package game;

import board.ChessBoard;
import board.GameState;
import helper.Position;
import input.InputManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
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
        JButton undo = new JButton("Undo");
        content.add(undo);
        undo.addActionListener((ActionEvent e) -> {undo();});
        //content.add(new JToolBar());



        gameStates = new Stack<>();
        gameStates.push(new GameState(board, 0, 0));
    }

    public void makeMove(Position start, Position end) {
        board.movePiece(start, end);
        gameStates.push(gameStates.peek().madeMove(board));
        if (board.checkGameOver(gameStates.peek().getWhoseTurn())) {
            String winner = gameStates.peek().getWhoseTurn() == 1 ? "White" : "Black";
            JOptionPane.showMessageDialog(board.getContent(), String.format("Checkmate. %s has won.", winner));
        }
    }

    public void castle(Position kStart, Position kEnd, Position rStart, Position rEnd) {}


    public int getWhoseTurn() {
        return gameStates.peek().getWhoseTurn();
    }

    public void undo() {
        gameStates.pop();
        gameStates.peek().setBoard(board);
    }

    public JPanel getContent() {
        return content;
    }

    private void newGame() {
        board = new ChessBoard(manager);
    }
}

