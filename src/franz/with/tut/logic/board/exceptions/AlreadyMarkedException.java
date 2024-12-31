package franz.with.tut.logic.board.exceptions;

public class AlreadyMarkedException extends Exception {
    public AlreadyMarkedException(int x, int y, String playerName) {
        super(String.format("%s can't mark tile at (%d, %d), because it is already marked!", playerName, x + 1, y + 1));
    }
}
