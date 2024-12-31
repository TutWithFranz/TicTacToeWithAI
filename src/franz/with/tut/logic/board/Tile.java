package franz.with.tut.logic.board;

public class Tile {
    private char content;
    public static final char EMPTY = '-';

    public Tile() {
        content = EMPTY;
    }

    public boolean mark(char mark) {
        if(isEmpty()) {
            content = mark;
            return true;
        }

        return false;
    }

    public char getContent() {
        return content;
    }

    public boolean isEmpty() {
        return content == EMPTY;
    }

    public boolean isOwnedBy(char playerMark) {
        return content == playerMark;
    }
}