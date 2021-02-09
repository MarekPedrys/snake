package pl.marekpedrys;

public class IllegalMoveException extends Exception {
    public IllegalMoveException() {
        super("Illegal move - game over!");
    }
}
