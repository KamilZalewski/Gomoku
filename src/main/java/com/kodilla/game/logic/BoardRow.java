package com.kodilla.game.logic;

import java.util.ArrayList;
import java.util.List;

public class BoardRow {
    private List<Figure> cols = new ArrayList<>();

    public BoardRow() {
        for (int n = 0; n<15; n++)
            cols.add(new None());
    }

    public List<Figure> getCols() {
        return cols;
    }

    @Override
    public String toString() {
      String s = "|";
      for (int col = 0; col <15; col++){
          s += " " + cols.get(col) + " |";
      }
      return s;
    }
}
