
package application;
import chess.ChessException;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
/**
 *
 * @author j040v
 */
public class ChessSystem {

    public static void main(String[] args) {
        Scanner s = new Scanner (System.in);
        ChessMatch chessMatch = new ChessMatch();
        List<ChessPiece> captured = new ArrayList<>();
    
        while(!chessMatch.getCheckMate()){
            try{
                UI.clearScreen();
                UI.printMatch(chessMatch, captured);   
                System.out.println();
                System.out.print("Source: ");
                ChessPosition source = UI.readChessPosition(s);
                
                boolean[][] possibleMoves = chessMatch.possibleMoves(source);
                UI.clearScreen();
                UI.printBoard(chessMatch.getPieces(), possibleMoves);
                
                System.out.println();
                System.out.print("Target: ");
                ChessPosition target = UI.readChessPosition(s);
                ChessPiece capturedPiece = chessMatch.performChessMove(source, target);
                
                if(capturedPiece != null){
                    captured.add(capturedPiece);
                }
                
            }         
            catch(ChessException e){
                System.out.println(e.getMessage());
                s.nextLine();
            }
            catch(InputMismatchException e){
                System.out.println(e.getMessage());
                s.nextLine();
            }
        }
        UI.clearScreen();
        UI.printMatch(chessMatch, captured);
    }
}
