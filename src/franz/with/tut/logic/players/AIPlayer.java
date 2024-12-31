package franz.with.tut.logic.players;

import franz.with.tut.logic.board.Board;
import franz.with.tut.ui.UI;
import franz.with.tut.util.Vector2D;

public class AIPlayer extends Player {
    private final char opponentsMark;

    public AIPlayer(PlayerMark mark, char opponentsMark) {
        super(mark);
        this.opponentsMark = opponentsMark;
    }

    @Override
    public void perform(Board board, UI ui) {
        ui.showMessage("AI performs turn...");

        // 1. complete row
        if (completeLineOf(mark.asChar(), board)) {
            return;
        }

        // 2. block opponent
        if (completeLineOf(opponentsMark, board)) {
            return;
        }

        // 3. place in middle
        if (board.getTile(1, 1).isEmpty()) {
            mark(1, 1, board);
            return;
        }

        // 4. place in corner
        for (int y = 0; y < board.getSize(); y += 2) {
            for (int x = 0; x < board.getSize(); x += 2) {
                if (board.getTile(x, y).isEmpty()) {
                    mark(x, y, board);
                    return;
                }
            }
        }

        // 5. place on side
        if (board.getTile(1, 0).isEmpty()) {
            mark(1, 0, board);
        } else if (board.getTile(0, 1).isEmpty()) {
            mark(0, 1, board);
        } else if (board.getTile(2, 1).isEmpty()) {
            mark(2, 1, board);
        } else if (board.getTile(1, 2).isEmpty()) {
            mark(1, 2, board);
        }
    }


    private boolean completeLineOf(char playerMark, Board board) {
        for (int i = 0; i < board.getSize(); i++) {
            // check columns
            if (board.getRowDominance(i, playerMark) == 2) {
                mark(board.getEmptyTileInRow(i), i, board);
                return true;
            }

            // check columns
            if (board.getColumnDominance(i, playerMark) == 2) {
                mark(i, board.getEmptyTileInColumn(i), board);
                return true;
            }
        }

        // check diagonals
        if (board.getDiagonalDominance(true, playerMark) == 2) {
            mark(board.getEmptyTileInDiagonal(true), board);
            return true;
        }
        if (board.getDiagonalDominance(false, playerMark) == 2) {
            mark(board.getEmptyTileInDiagonal(false), board);
            return true;
        }

        return false;
    }

    private void mark(Vector2D position, Board board) {
        mark(position.x, position.y, board);
    }

    private void mark(int x, int y, Board board) {
        try {
            board.mark(x, y, this);
        } catch (Exception ignored) {
        }
    }
}