package pl.marekpedrys;

import java.util.Random;

public class SnakeGame {
    private final int xBound;
    private final int yBound;
    private final Snake snake;
    private Point apple;

    private static int amountOfEatenApples;
    private final SnakeGameJavaFXPrinter printer;


    public SnakeGame(int xBound, int yBound, Snake snake, SnakeGameJavaFXPrinter printer) {
        this.printer = printer;
        if (xBound > 0 && yBound > 0 && snake.getDirection() != null) {
            this.xBound = xBound;
            this.yBound = yBound;
            this.snake = snake;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public SnakeGame(int xBound, int yBound, SnakeGameJavaFXPrinter printer) {
        this(xBound, yBound, new Snake(), printer);
    }

    private int threadSleepTime = 256;

    public void start() {
        randomizeApple();

        while (isSnakeInBounds()) {

            printer.print(toString());

            try {
                Thread.sleep(threadSleepTime);
            } catch (InterruptedException e) {
                return;
            }

            snake.expand();

            if (apple.equals(snake.getHead())) {
                amountOfEatenApples++;
                threadSleepTime *= 0.95;
                randomizeApple();
            } else {
                snake.cutTail();
            }

            boolean snakesHeadHitsBody = snake.getBody().contains(snake.getHead());

            if (snakesHeadHitsBody) {
                try {
                    throw new IllegalMoveException();
                } catch (IllegalMoveException e) {
                    System.out.println(e.getMessage());
                    break;
                }
            }
        }
        System.out.println("Game over!");
        System.out.println("Score: " + amountOfEatenApples);
    }

    private boolean isSnakeInBounds() {
        Point head = snake.getHead();
        int headX = head.getX();
        int headY = head.getY();
        return headX < xBound
                && headX >= 0
                && headY < yBound
                && headY >= 0;
    }

    private void randomizeApple() {
        Random random = new Random();
        do {
            int appleX = random.nextInt(xBound);
            int appleY = random.nextInt(yBound);
            apple = new Point(appleX, appleY);
        }
        while (snake.contains(apple));
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int y = 0; y < yBound; y++) {
            for (int x = 0; x < xBound; x++) {
                Point point = new Point(x, y);
                char boardCharacter = getBoardCharacter(point);
                stringBuilder.append(boardCharacter);
            }
            stringBuilder.append('\n');
        }
        return stringBuilder.toString();
    }


    private char getBoardCharacter(Point point) {
        if (point.equals(apple)) {
            return 'A';
        } else if (point.equals(snake.getHead())) {
            return 'H';
        } else if (snake.getBody().contains(point)) {
            return 'B';
        } else {
            return '.';
        }
    }

    public void setSnakeDirection(Direction direction) {
        snake.setDirection(direction);
    }

    public Direction getSnakeDirection() {
        return snake.getDirection();
    }

    public static int getAmountOfEatenApples() {
        return amountOfEatenApples;
    }

}
