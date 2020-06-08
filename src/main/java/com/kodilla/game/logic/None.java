package com.kodilla.game.logic;

public class None implements Figure {

    @Override
    public PlayerColor getColor() {
        return PlayerColor.NONE;
    }

    @Override
    public String toString() {
       return " ";
    }
}