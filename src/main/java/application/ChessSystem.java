
package application;
import chess.ChessMatch;
/**
 *
 * @author j040v
 */
public class ChessSystem {

    public static void main(String[] args) {
    ChessMatch chessMatch = new ChessMatch();
    UI.printBoard(chessMatch.getPieces());
    }
}
