package franz.with.tut.logic.board;

import franz.with.tut.logic.board.exceptions.AlreadyMarkedException;
import franz.with.tut.logic.board.exceptions.OutOfBoundsException;
import franz.with.tut.logic.players.Player;
import franz.with.tut.util.Vector2D;

import java.util.HashSet;
import java.util.Vector;

public class Board {
    private final Tile[][] tiles;

    public Board() {
        tiles = new Tile[3][3];

        // generate tiles
        for (int y = 0; y < getSize(); y++) {
            for (int x = 0; x < getSize(); x++) {
                tiles[y][x] = new Tile();
            }
        }
    }

    public void mark(Vector2D position, Player player) throws OutOfBoundsException, AlreadyMarkedException {
        mark(position.x, position.y, player);
    }

    public void mark(int x, int y, Player player) throws OutOfBoundsException, AlreadyMarkedException {
        if (isOutOfBounds(x, y)) {
            throw new OutOfBoundsException(x, y, player.getName());
        }

        if (!getTile(x, y).mark(player.getMark())) {
            throw new AlreadyMarkedException(x, y, player.getName());
        }
    }

    // getter / checks

    public Tile getTile(int x, int y) {
        return tiles[y][x];
    }

    public boolean isOutOfBounds(Vector2D position) {
        return isOutOfBounds(position.x, position.y);
    }

    private boolean isOutOfBounds(int x, int y) {
        return x < 0 || x >= getSize() || y < 0 || y >= getSize();
    }

    public int getSize() {
        return tiles.length;
    }

    public String asString() {
        StringBuilder board = new StringBuilder();

        // generate x-coordinates
        board.append("  1  2  3  \n");

        // generate rows
        int yCoordinate = 1;
        for (Tile[] rows : tiles) {
            // generate y-coordinate
            board.append(yCoordinate++).append(" ");

            // generate tiles
            for (Tile tile : rows) {
                board.append(tile.getContent()).append(" ");
            }

            // go to next line
            board.append('\n');
        }

        return board.toString();
    }

    public boolean isFilled() {
        for (Tile[] rows : tiles) {
            for (Tile tile : rows) {
                if (tile.isEmpty()) {
                    return false;
                }
            }
        }

        return true;
    }

    // dominance

    public int getRowDominance(int y, char playerMark) {
        int dominance = 0;
        for (Tile tile : tiles[y]) {
            if (tile.isOwnedBy(playerMark)) {
                dominance++;
            } else if (!tile.isEmpty()) {
                return 0;
            }
        }

        return dominance;
    }

    public int getColumnDominance(int x, char playerMark) {
        int dominance = 0;
        for (int y = 0; y < getSize(); y++) {
            if (getTile(x, y).isOwnedBy(playerMark)) {
                dominance++;
            } else if (!getTile(x, y).isEmpty()) {
                return 0;
            }
        }

        return dominance;
    }

    public int getDiagonalDominance(boolean secondAngleBisection, char playerMark) {
        int dominance = 0;

        for (int i = 0; i < getSize(); i++) {
            if (secondAngleBisection && getTile(i, i).isOwnedBy(playerMark)
                    || !secondAngleBisection && getTile(i, (getSize() - 1) - i).isOwnedBy(playerMark)) {
                // x - -      - - x
                // - x -  or  - x -
                // - - x      x - -
                dominance++;
            } else if (secondAngleBisection && !getTile(i, i).isEmpty() || !secondAngleBisection
                    && !getTile(i, (getSize() - 1) - i).isEmpty()) {
                return 0;
            }
        }

        return dominance;
    }

    public int getEmptyTileInRow(int y) {
        for (int x = 0; x < getSize(); x++) {
            if (getTile(x, y).isEmpty()) {
                return x;
            }
        }

        return -1;
    }

    public int getEmptyTileInColumn(int x) {
        for (int y = 0; y < getSize(); y++) {
            if (getTile(x, y).isEmpty()) {
                return y;
            }
        }

        return -1;
    }

    public Vector2D getEmptyTileInDiagonal(boolean secondAngleBisection) {
        for (int i = 0; i < getSize(); i++) {
            if (secondAngleBisection && getTile(i, i).isEmpty()) {
                // x - -
                // - x -
                // - - x
                return new Vector2D(i, i);
            } else if (!secondAngleBisection && getTile(i, (getSize() - 1) - i).isEmpty()) {
                // - - x
                // - x -
                // x - -
                return new Vector2D(i, (getSize() - 1) - i);
            }
        }

        return null;
    }
}