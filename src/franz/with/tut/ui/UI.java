package franz.with.tut.ui;

import franz.with.tut.logic.board.Board;

public interface UI {
    void showBoard(Board board);
    void showMessage(String message);
}
