package franz.with.tut.logic.players;

import franz.with.tut.logic.board.Board;
import franz.with.tut.logic.board.exceptions.AlreadyMarkedException;
import franz.with.tut.logic.board.exceptions.OutOfBoundsException;
import franz.with.tut.ui.UI;

import java.util.Scanner;

public class HumanPlayer extends Player {
    private final Scanner userInput;

    public HumanPlayer(PlayerMark mark, Scanner userInput) {
        super(mark);
        this.userInput = userInput;
    }

    @Override
    public void perform(Board board, UI ui) {
        // get user input
        ui.showMessage(getName() + "please enter a position (x | y):");
        int x = userInput.nextInt() - 1;
        int y = userInput.nextInt() - 1;

        try {
            board.mark(x, y, this);
        } catch (AlreadyMarkedException | OutOfBoundsException e) {
            // marked unsuccessfully
            ui.showMessage(e.getLocalizedMessage());
            ui.showBoard(board);
            perform(board, ui);
        }
    }
}
