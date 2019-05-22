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
        content.add(board);
        content.add(new JToolBar());



        gameStates = new Stack<>();
        gameStates.push(new GameState(board, 0));
    }

    public void makeMove(Position start, Position end, boolean isCastling) {
        board.movePiece(start, end);
        gameStates.push(gameStates.peek().madeMove(board));
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
