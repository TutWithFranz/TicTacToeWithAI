package franz.with.tut.ui;

import franz.with.tut.logic.board.Board;
import franz.with.tut.logic.players.Player;

public class ConsoleUI implements UI {
    @Override
    public void showBoard(Board board) {
        System.out.println(board.asString());
    }

    @Override
    public void showMessage(String message) {
        System.out.format("\n%s\n", message);
    }
}
