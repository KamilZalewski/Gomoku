package GameJavaFX;

import com.kodilla.game.logic.Game;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class Main extends Application {

    private Image imageback = new Image("file:src/main/resources/gomoku.gif");

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);
        BackgroundImage backgroundImage = new BackgroundImage(imageback, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);

        GridPane grid = new GridPane();
        grid.setBackground(background);
        for(int n = 0; n <15; n++){
            grid.getColumnConstraints().add(new ColumnConstraints(30));
            grid.getRowConstraints().add(new RowConstraints(30));
        }

        Scene scene = new Scene(grid, 450, 450, Color.BLACK);
        Game game = new Game(grid);
        game.display();
        grid.setOnMouseClicked(e -> {
            System.out.println(e.getX() + "," + e.getY());
            int x = (int)((e.getX() - 15)/30);
            int y = (int)((e.getY())/30);
            System.out.println(x + "," + y);
            game.makeMove(x,y);
            game.display();
        });
        primaryStage.setTitle("Gomoku - Five In A Row");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

