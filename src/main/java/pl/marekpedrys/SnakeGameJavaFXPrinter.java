package pl.marekpedrys;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class SnakeGameJavaFXPrinter {
    GraphicsContext graphicsContext;
    int pointSize;


    public SnakeGameJavaFXPrinter(GraphicsContext graphicsContext, int pointSize) {
        this.graphicsContext = graphicsContext;
        this.pointSize = pointSize;
    }

    public void drawRect(Point point, Color color) {
        graphicsContext.setFill(color);
        graphicsContext.fillRect(point.getX() * pointSize, point.getY() * pointSize, pointSize, pointSize);

    }

    public void drawCircle(Point point, Color color) {
        graphicsContext.setFill(color);
        graphicsContext.fillOval(point.getX() * pointSize, point.getY() * pointSize, pointSize, pointSize);

    }

    public void print(String gameBoard) {
        int pointX = 0;
        int pointY = 0;

        Canvas canvas = graphicsContext.getCanvas();
        graphicsContext.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        for (int i = 0; i < gameBoard.length(); i++) {
            switch (gameBoard.charAt(i)) {
                case '.':
                    drawRect(new Point(pointX, pointY), Color.BLACK);
                    pointX++;
                    break;
                case 'H':
                    drawRect(new Point(pointX, pointY), Color.GREEN);
                    pointX++;
                    break;
                case 'B':
                    drawRect(new Point(pointX, pointY), Color.LIGHTGREEN);
                    pointX++;
                    break;
                case 'A':
                    drawRect(new Point(pointX, pointY), Color.BLACK);
                    drawCircle(new Point(pointX, pointY), Color.RED);
                    pointX++;
                    break;
                case '\n':
                    pointX = 0;
                    pointY++;
                    break;
            }
        }

        Text textScore = new Text("Score: " + SnakeGame.getAmountOfEatenApples());
        graphicsContext.setFill(Color.RED);
        graphicsContext.fillText(textScore.getText(), 10, 15);

    }
}
