package com.kodilla.game.logic;

public class TextApp {
    public static void main(String[] args) {
        Board board = new Board();
        board.setFigure(5, 5, new Pawn(PlayerColor.WHITE));
        System.out.println(board);
    }

}
