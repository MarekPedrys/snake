package pl.marekpedrys;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Snake {
    private Point head;
    private final List<Point> body;
    private Direction direction;


    public Snake(Point head, List<Point> body, Direction direction) {
        this.head = head;
        this.body = new ArrayList<>(body);
        this.direction = direction;
    }

    public Snake() {
        this(new Point(1,0), Collections.singletonList(new Point(0, 0)), Direction.RIGHT);
    }

    public void cutTail() {
        body.remove(body.size() - 1);
    }

    public void expand() {
        body.add(0, head);
        head = head.move(direction);
    }

    public Point getHead() {
        return head;
    }

    public List<Point> getBody() {
        return body;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public boolean contains (Point point){
        return head.equals(point) || body.contains(point);
    }

}
