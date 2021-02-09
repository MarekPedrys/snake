package pl.marekpedrys;

public class Point {
    private final int x;
    private final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point point = (Point) o;

        if (x != point.x) return false;
        return y == point.y;
    }

    public Point move(Direction direction) {
        switch (direction) {

            case UP:
                return new Point(x, y - 1);

            case DOWN:
                return new Point(x, y + 1);

            case RIGHT:
                return new Point(x + 1, y);

            case LEFT:
                return new Point(x - 1, y);

            default:
                return new Point(x, y);
        }
    }
}
