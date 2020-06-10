package com.kodilla.game.logic;

import java.util.Scanner;

public class UserDialogs {

    public static Coord getMove() {
        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.println("Enter move (for example: 0,3):");
            String s = scanner.nextLine();
            String[] input = s.split(",");
            if (input.length == 2){
                try {
                    int col = Integer.parseInt(input[0]);
                    int row = Integer.parseInt(input[1]);
                    if (col >= 0 && col <15 && row >=0 && row <15){
                        return new Coord(col,row);
                    }
                    System.out.println("Wrong coords, try again");
                } catch (Exception e){
                    System.out.println("Wrong coords, try again");
                }
            } else {
                System.out.println("Wrong coords, try again");
            }
        }
    }
}
