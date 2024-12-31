package franz.with.tut.logic.players;

public enum PlayerMark {
    X('x'),
    O('o');

    private final char mark;

    PlayerMark(char mark) {
        this.mark = mark;
    }

    public char asChar() {
        return mark;
    }
}
