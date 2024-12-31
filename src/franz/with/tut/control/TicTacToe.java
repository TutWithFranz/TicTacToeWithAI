package franz.with.tut.control;

import franz.with.tut.logic.Game;
import franz.with.tut.logic.players.AIPlayer;
import franz.with.tut.logic.players.HumanPlayer;
import franz.with.tut.logic.players.Player;
import franz.with.tut.logic.players.PlayerMark;
import franz.with.tut.ui.ConsoleUI;
import franz.with.tut.ui.UI;

import java.util.Scanner;

public class TicTacToe {
    public static void main(String[] args) {
        UI ui = new ConsoleUI();
        Scanner input = new Scanner(System.in);
        Player humanPlayer = new HumanPlayer(PlayerMark.X, input);
        Game game = new Game(humanPlayer, new AIPlayer(PlayerMark.O, humanPlayer.getMark()));
        // game loop
        do {
            game.performTurn(ui);
        } while (game.isOngoing());

        // game end
        input.close();
        game.showGameEndedMessage(ui);
    }
}
