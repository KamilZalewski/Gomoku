package com.kodilla.game.logic;

public class Pawn implements Figure{
    private PlayerColor color;

    public Pawn(PlayerColor color) {
        this.color = color;
    }

    @Override
    public PlayerColor getColor() {
        return color;
    }

    @Override
    public String toString() {
        if (color == PlayerColor.WHITE)
            return "W";
        else
            return "b";
    }
}
