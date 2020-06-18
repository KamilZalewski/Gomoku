package com.kodilla.game.logic;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Game {
    private final GridPane grid;
    private PlayerColor whoseMove = PlayerColor.WHITE;
    private Board board = new Board();

    public Game(GridPane grid) {
        this.grid = grid;
    }

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
            if (isWinner(PlayerColor.WHITE)){
                System.out.println("White is a winner.");
                endGame(PlayerColor.WHITE);

            }
            if (isWinner(PlayerColor.BLACK))
                System.out.println("Black is a winner.");
                endGame(PlayerColor.BLACK);

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

    public void display() {
        grid.getChildren().clear();
        for(int col = 0; col < 15; col++){
            for(int row = 0; row < 15; row++){
                Figure figure = board.getFigure(col, row);
                if (figure instanceof Pawn){
                    Circle circle = new Circle(15);
                    circle.setFill(figure.getColor() == PlayerColor.WHITE ? Color.WHITE:Color.BLACK);
                    grid.add(circle,col,row);
                }
            }
        }
    }

    public void endGame(PlayerColor playerColor){
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Game Over!");
        window.setMinWidth(250);
        Label label = new Label();
        label.setText(playerColor + " is a Winner!\n Congratulations!");
        Button newGameButton = new Button("New Game");
        newGameButton.setOnAction(e ->
                window.close());
        Button endGameButton = new Button("End Game");
        endGameButton.setOnAction(e ->
                window.close());
        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, newGameButton, endGameButton);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.show();
    }
}
