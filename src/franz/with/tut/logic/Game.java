package franz.with.tut.logic;

import franz.with.tut.logic.board.Board;
import franz.with.tut.logic.players.Player;
import franz.with.tut.ui.UI;

public class Game {
    private final Board board;
    private final Player[] players;
    private int activePlayerIndex;
    private GameState state;

    public Game(Player firstPlayer, Player secondPlayer) {
        board = new Board();
        players = new Player[]{firstPlayer, secondPlayer};
        activePlayerIndex = (int) (Math.random() * players.length);
        state = GameState.ONGOING;
    }

    public void performTurn(UI ui) {
        ui.showBoard(board);

        getActivePlayer().perform(board, ui);

        if (isWon() || isDraw()) {
            return;
        }

        switchActivePlayer();
    }

    private boolean isWon() {
        char activePlayerMark = getActivePlayer().getMark();
        // check rows and columns
        for (int yx = 0; yx < board.getSize(); yx++) {
            if (board.getRowDominance(yx, activePlayerMark) == 3
                    || board.getColumnDominance(yx, activePlayerMark) == 3) {
                state = GameState.WON;
                return true;
            }
        }

        // check diagonals
        if (board.getDiagonalDominance(true, activePlayerMark) == 3
                || board.getDiagonalDominance(false, activePlayerMark) == 3) {
            state = GameState.WON;
            return true;
        }

        return false;
    }

    private boolean isDraw() {
        if (board.isFilled()) {
            state = GameState.DRAW;
            return true;
        }

        return false;
    }

    private void switchActivePlayer() {
        activePlayerIndex = ++activePlayerIndex % players.length;
    }

    public void showGameEndedMessage(UI ui) {
        String message;

        if (state == GameState.WON) {
            message = getActivePlayer().getName() + "%s is the Winner!";
        } else {
            message = "The game was drawn!";
        }

        ui.showBoard(board);
        ui.showMessage(message);
    }

    private Player getActivePlayer() {
        return players[activePlayerIndex];
    }

    public boolean isOngoing() {
        return state == GameState.ONGOING;
    }
}