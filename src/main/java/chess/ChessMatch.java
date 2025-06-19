
package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Pawn;
import chess.pieces.Rook;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
public class ChessMatch {
    
    private int turn;
    private Color currentPlayer;
    private Board board;
    private boolean check;
    private boolean checkMate;
    
    private List <Piece> piecesOnTheBoard = new ArrayList<>();
    private List <Piece> capturedPieces = new ArrayList<>();
    
    public ChessMatch(){
        board = new Board(8,8);
        turn = 1;
        currentPlayer = Color.WHITE;
        initialSetup();
    }
    
    public int getTurn(){
        return turn;
    }
    public boolean getCheckMate(){
        return checkMate;
    }
    
    public Color getCurrentPlayer(){
        return currentPlayer;
    }
    public boolean getCheck(){
        return check;
    }
    
    public ChessPiece[][]getPieces(){
        ChessPiece[][] mat = new ChessPiece[board.getRaws()][board.getColumns()];
        for(int i = 0; i<board.getRaws();i++){
            for(int j = 0;j<board.getColumns();j++){
                mat[i][j] = (ChessPiece) board.piece(i,j);
            }
        }
        return mat;
    }
    
    public boolean[][] possibleMoves(ChessPosition sourcePosition){
        Position position = sourcePosition.toPosition();
        validateSourcePosition(position);
        return board.piece(position).possibleMoves();
    }
    
    public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition){
        Position source = sourcePosition.toPosition();
        Position target = targetPosition.toPosition();
        validateSourcePosition(source);
        validateTargetPosition(source, target);
        Piece capturePiece = makeMove(source, target);
        
        if(testCheck(currentPlayer)){
            undoMove(source, target, capturePiece);
            throw new ChessException("You can't put yourself in check");
        }
        check = (testCheck(opponent(currentPlayer))) ? true : false;
        
        if(testCheckMate(opponent(currentPlayer))){
            checkMate = true;
        }else{
            nextTurn();
        }
        return (ChessPiece)capturePiece;
    }
    
    private Piece makeMove(Position source, Position target){
        ChessPiece p = (ChessPiece)board.removePiece(source);
        p.increaseMoveCount();
        Piece capturedPiece = board.removePiece(target);
        board.placePiece(p, target);
        
        if(capturedPiece != null){
            piecesOnTheBoard.remove(capturedPiece);
            capturedPieces.add(capturedPiece);
        }
        return capturedPiece;
    }
    
    private void undoMove(Position source, Position target, Piece capturedPiece){
       ChessPiece p = (ChessPiece)board.removePiece(target);
       p.decreaseMoveCount();
       board.placePiece(p, source);
       
       if(capturedPiece != null){
           board.placePiece(capturedPiece, target);
           capturedPieces.remove(capturedPiece);
           piecesOnTheBoard.add(capturedPiece);
       }
    }
    
    private void validateSourcePosition(Position position){
        if(!board.thereIsAPiece(position)){
            throw new ChessException("There is no piece on source position");
        }
        if(!board.piece(position).isThereAnyPossibleMove()){
            throw new ChessException("There is no possible moves for chosen piece");
        }
        if(currentPlayer != ((ChessPiece)board.piece(position)).getColor()){
            throw new ChessException("The choen piece is not yours");
        }
    }
    private void validateTargetPosition(Position source, Position target){
        if(!board.piece(source).possibleMove(target)){
            throw new ChessException("The chose piece can't move to target position");
        }
        
    }
    private void nextTurn(){
        turn++;
        currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE; 
    }
    private Color opponent(Color color){
        return (color == Color.WHITE) ? Color.BLACK : Color.WHITE;
    }
    
    private ChessPiece king(Color color){
        List<Piece> list = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == color).collect(Collectors.toList());
        for(Piece p : list){
            if(p instanceof King){
                return (ChessPiece)p;
            }
        }
        throw new IllegalStateException("There is no "+color + "King on the board");
    }
    
    private boolean testCheck(Color color){
        Position kingPosition = king(color).getChessPosition().toPosition(); //Posição do rei em forma de matriz
        List<Piece> opponentPieces = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == opponent(color)).collect(Collectors.toList());
        
        for(Piece p : opponentPieces){
            boolean[][] mat = p.possibleMoves();
            if(mat[kingPosition.getRow()][kingPosition.getColumn()]){
                return true;
            }
        }return false;
    }
    
    private boolean testCheckMate(Color color){
        if(!testCheck(color)){
            return false;
        }
        List<Piece> list = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == color).collect(Collectors.toList());
        for(Piece p : list){
            boolean[][] mat = p.possibleMoves();    //Para cada p piece da list crio a matriz dos movimentos possiveis
            for(int i = 0; i < board.getRaws();i++){
                for(int j = 0; j < board.getColumns(); j++){    //looping para percorrer se alguma jogada é válida
                    if(mat[i][j]){  //Se for true: 
                        Position source = ((ChessPiece)p).getChessPosition().toPosition();  //Crio a variavel source e target
                        Position target = new Position(i,j);    //Passo as possibilidades como targete o p como source
                        Piece capturedPiece = makeMove(source, target); //Chamo o make move para testar se depois do movimento continuarei em check
                        boolean testCheck = testCheck(color);   //Crio uma variável testCheck recebendo a função testCheck 
                        undoMove(source, target, capturedPiece);    //undoMove para desfazer o teste feito no makeMove
                        if(!testCheck){ //Se não está em cheque, retorno false indicando que existe a possibilidade de salvar o Rei
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
    
    private void placeNewPiece(char coluumn, int row, ChessPiece piece){
        board.placePiece(piece, new ChessPosition(coluumn,row).toPosition());
        piecesOnTheBoard.add(piece);
    }
    private void initialSetup(){
        placeNewPiece('a', 1, new Rook(board, Color.WHITE));
        placeNewPiece('e', 1, new King(board, Color.WHITE));
        placeNewPiece('h', 1, new Rook(board, Color.WHITE));
        placeNewPiece('a', 2, new Pawn(board, Color.WHITE));
        placeNewPiece('b', 2, new Pawn(board, Color.WHITE));
        placeNewPiece('c', 2, new Pawn(board, Color.WHITE));
        placeNewPiece('d', 2, new Pawn(board, Color.WHITE));
        placeNewPiece('e', 2, new Pawn(board, Color.WHITE));
        placeNewPiece('f', 2, new Pawn(board, Color.WHITE));
        placeNewPiece('g', 2, new Pawn(board, Color.WHITE));
        placeNewPiece('h', 2, new Pawn(board, Color.WHITE));

        placeNewPiece('a', 8, new Rook(board, Color.BLACK));
        placeNewPiece('e', 8, new King(board, Color.BLACK));
        placeNewPiece('h', 8, new Rook(board, Color.BLACK));
        placeNewPiece('a', 7, new Pawn(board, Color.BLACK));
        placeNewPiece('b', 7, new Pawn(board, Color.BLACK));
        placeNewPiece('c', 7, new Pawn(board, Color.BLACK));
        placeNewPiece('d', 7, new Pawn(board, Color.BLACK));
        placeNewPiece('e', 7, new Pawn(board, Color.BLACK));
        placeNewPiece('f', 7, new Pawn(board, Color.BLACK));
        placeNewPiece('g', 7, new Pawn(board, Color.BLACK));
        placeNewPiece('h', 7, new Pawn(board, Color.BLACK));
        
    }
    
}
