package pl.marekpedrys;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class App extends Application {

    @Override
    public void start(Stage stage) {

        Canvas canvas = new Canvas(16 * 25, 20 * 25);
        SnakeGameJavaFXPrinter snakeGameJavaFXPrinter = new SnakeGameJavaFXPrinter(canvas.getGraphicsContext2D(), 25);
        SnakeGame snakeGame = new SnakeGame(16, 20, snakeGameJavaFXPrinter);

        VBox vBox = new VBox();

        vBox.getChildren().add(canvas);

        Thread threadGame = new Thread(snakeGame::start);
        threadGame.setDaemon(true);
        threadGame.start();

        Scene scene = new Scene(vBox);

        scene.setOnKeyPressed(keyEvent -> {
            switch (keyEvent.getCode()) {
                case UP:
                    if (!snakeGame.getSnakeDirection().equals(Direction.DOWN)) {
                        snakeGame.setSnakeDirection(Direction.UP);
                    }
                    break;

                case DOWN:
                    if (!snakeGame.getSnakeDirection().equals(Direction.UP)) {
                        snakeGame.setSnakeDirection(Direction.DOWN);
                    }
                    break;

                case LEFT:
                    if (!snakeGame.getSnakeDirection().equals(Direction.RIGHT)) {
                        snakeGame.setSnakeDirection(Direction.LEFT);
                    }
                    break;

                case RIGHT:
                    if (!snakeGame.getSnakeDirection().equals(Direction.LEFT)) {
                        snakeGame.setSnakeDirection(Direction.RIGHT);
                    }
                    break;
            }
        });
        stage.setTitle("Snake 1.0");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
