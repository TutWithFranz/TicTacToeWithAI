package franz.with.tut.logic.players;

import franz.with.tut.logic.board.Board;
import franz.with.tut.ui.UI;

public abstract class Player {
    protected final PlayerMark mark;

    public Player(PlayerMark mark) {
        this.mark = mark;
    }

    public abstract void perform(Board board, UI ui);

    public String getName() {
        return String.format("Player \"%s\"", mark.name());
    }

    public final char getMark() {
        return mark.asChar();
    }
}