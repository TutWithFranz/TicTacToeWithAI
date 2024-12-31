package franz.with.tut.logic.board.exceptions;

public class OutOfBoundsException extends Exception {
    public OutOfBoundsException (int x, int y, String playerName) {
        super(String.format("%s can't mark position (%d | %d), because it's out of bounds!", playerName, x, y));
    }
}