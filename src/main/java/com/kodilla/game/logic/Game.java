package com.kodilla.game.logic;

public class Game {
    private PlayerColor whoseMove = PlayerColor.WHITE;
    private Board board = new Board();

    public void play(){
        System.out.println(board);
        while(true){
            Coord coord = UserDialogs.getMove();
            makeMove(coord.getCol(),coord.getRow());
            System.out.println(board);
        }
    }
    public void makeMove(int col, int row) {
        PlayerColor color = board.getFigure(col, row).getColor();
        if (color == PlayerColor.NONE) {
            board.setFigure(col, row, new Pawn(whoseMove));
            if (whoseMove == PlayerColor.WHITE) {
                whoseMove = PlayerColor.BLACK;
            } else {
                whoseMove = PlayerColor.WHITE;
            }
            if (isWinner(PlayerColor.WHITE))
                System.out.println("White is a winner.");
            if (isWinner(PlayerColor.BLACK))
                System.out.println("Black is a winner.");
        }
    }

    private void nextMove() {
        if (whoseMove == PlayerColor.WHITE) {
            whoseMove = PlayerColor.BLACK;
        } else {
            whoseMove = PlayerColor.WHITE;
        }
    }
    private boolean isWinner(PlayerColor playerColor){
        boolean result = false;
        for (int col = 0; col <15; col++){
            for (int row = 0; row < 15; row++){
                result = result||isWinner(playerColor, col, row);
            }
        }
        return result;
    }

    private boolean isWinner(PlayerColor playerColor, int col, int row) {
        boolean result = true;
        result = checkRight(playerColor, col, row);
        result = result || checkDown(playerColor, col, row);
        result = result || checkRightDiagonal(playerColor, col, row);
        result = result || checkLeftDiagonal(playerColor, col, row);
        return result;
    }

    private boolean checkLeftDiagonal(PlayerColor playerColor, int col, int row) {
        boolean result = true;
        if (row > 10 || col < 4)
            return false;
        for (int n = 0; n <5; n++){
            result = result && board.getFigure(col - n,row + n).getColor()==playerColor;
        }
        return result;
    }

    private boolean checkRightDiagonal(PlayerColor playerColor, int col, int row) {
        boolean result = true;
        if (row > 10 || col > 10)
            return false;
        for (int n = 0; n <5; n++){
            result = result && board.getFigure(col + n,row + n).getColor()==playerColor;
        }
        return result;
    }

    private boolean checkDown(PlayerColor playerColor, int col, int row) {
        boolean result = true;
        if (row > 10)
            return false;
        for (int n = 0; n <5; n++){
            result = result && board.getFigure(col,row + n).getColor()==playerColor;
        }
        return result;
    }

    private boolean checkRight(PlayerColor playerColor, int col, int row) {
        boolean result = true;
        if (col >10)
            return false;
        for (int n = 0; n <5; n++){
            result = result && board.getFigure(col + n,row).getColor()==playerColor;
        }
        return result;
    }

}
