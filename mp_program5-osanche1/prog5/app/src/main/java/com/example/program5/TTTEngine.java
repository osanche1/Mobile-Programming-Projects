package com.example.program5;

import java.util.Random;

public class TTTEngine {

    private static final Random RANDOM = new Random();
    private boolean finished;
    private char[] moves;
    private char currentPlayer;


    public TTTEngine(){
        moves = new char[9];
        newGame();
    }

    public void newGame() {
        for (int i = 0; i  < moves.length; i++) {
            moves[i] = ' ';
        }
        currentPlayer = 'X';
        finished = false;
    }

    public boolean isFinished(){
        return finished;
    }

    public char getMoves(int x, int y) {
        return moves[3 * y + x];
    }

    public char checkEnd() {
        for (int i = 0; i < 3; i++) {
            if (getMoves(i, 0) != ' ' &&
                    getMoves(i, 0) == getMoves(i, 1)  &&
                    getMoves(i, 1) == getMoves(i, 2)) {
                finished = true;
                return getMoves(i, 0);
            }

            if (getMoves(0, i) != ' ' &&
                    getMoves(0, i) == getMoves(1, i)  &&
                    getMoves(1, i) == getMoves(2, i)) {
                finished = true;
                return getMoves(0, i);
            }
        }

        if (getMoves(0, 0) != ' '  &&
                getMoves(0, 0) == getMoves(1, 1)  &&
                getMoves(1, 1) == getMoves(2, 2)) {
            finished = true;
            return getMoves(0, 0);
        }

        if (getMoves(2, 0) != ' '  &&
                getMoves(2, 0) == getMoves(1, 1)  &&
                getMoves(1, 1) == getMoves(0, 2)) {
            finished = true;
            return getMoves(2, 0);
        }

        for (int i = 0; i < 9; i++) {
            if (moves[i] == ' ')
                return ' ';
        }

        return 'T';
    }

    public void changePlayer() {
        currentPlayer = (currentPlayer == 'X' ? 'O' : 'X');
    }

    public char makeMove(int x, int y) {
        if (!finished  &&  moves[3 * y + x] == ' ') {
            moves[3 * y + x] = currentPlayer;
            changePlayer();
        }
        return checkEnd();
    }

}
