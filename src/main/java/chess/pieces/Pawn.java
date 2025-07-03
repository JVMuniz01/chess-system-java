/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

/**
 *
 * @author j040v
 */
public class Pawn extends ChessPiece {

    private ChessMatch chessMatch;
    
    public Pawn(Board board, Color color, ChessMatch chessMatch) {
        super(color, board);
        this.chessMatch = chessMatch;
    }
    
    @Override
    public boolean[][] possibleMoves() {
        boolean [][] mat = new boolean[getBoard().getRaws()][getBoard().getColumns()];
        
        Position p = new Position(0,0);
        
        if(getColor() == Color.WHITE){ //Apenas para as brancas
            
            p.setValues(position.getRow() -1 , position.getColumn()); //Se não tiver peça na frente movimento é possivel
            if(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)){
            mat[p.getRow()][p.getColumn()] = true;
            }
            
            p.setValues(position.getRow() -2 , position.getColumn());//Se, não tiver peça nas duas casas a frente e for seu primeiro movimento pode andar duas casas
            Position p2 = new Position(position.getRow() -1 , position.getColumn());
            if(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p) && getBoard().positionExists(p2) && !getBoard().thereIsAPiece(p2) && getMoveCount() == 0){
            mat[p.getRow()][p.getColumn()] = true;
            }
            
            p.setValues(position.getRow() -1 , position.getColumn() -1 ); //Testar se existe peça na diagonal direita do peão para capturar peça adversária
            if(getBoard().positionExists(p) && isThereOpponentPiece(p)){
            mat[p.getRow()][p.getColumn()] = true;
            }
            
            p.setValues(position.getRow() -1 , position.getColumn() +1 ); //Testar se existe peça na diagonal Esquerda do peão para capturar peça adversária
            if(getBoard().positionExists(p) && isThereOpponentPiece(p)){
            mat[p.getRow()][p.getColumn()] = true;
            }
            //Special Move enPassant WHITE
            if(position.getRow() == 3){
                Position left = new Position(position.getRow(), position.getColumn() - 1);
                if(getBoard().positionExists(left) && isThereOpponentPiece(left) && getBoard().piece(left) == chessMatch.getEnPassantVulnerable()){
                    mat[left.getRow() - 1][left.getColumn()] = true;
                }
                Position right = new Position(position.getRow(), position.getColumn() + 1);
                if(getBoard().positionExists(right) && isThereOpponentPiece(right) && getBoard().piece(right) == chessMatch.getEnPassantVulnerable()){
                    mat[right.getRow() - 1][right.getColumn()] = true;
                }
            }
            
        }else{ //Peças pretas
            
            p.setValues(position.getRow() +1 , position.getColumn()); //Se não tiver peça na frente movimento é possivel
            if(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)){
            mat[p.getRow()][p.getColumn()] = true;
            }
            
            p.setValues(position.getRow() +2 , position.getColumn());//Se, não tiver peça nas duas casas a frente e for seu primeiro movimento pode andar duas casas
            Position p2 = new Position(position.getRow() +1 , position.getColumn());
            if(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p) && getBoard().positionExists(p2) && !getBoard().thereIsAPiece(p2) && getMoveCount() == 0){
            mat[p.getRow()][p.getColumn()] = true;
            }
            
            p.setValues(position.getRow() +1 , position.getColumn() -1 ); //Testar se existe peça na diagonal direita do peão para capturar peça adversária
            if(getBoard().positionExists(p) && isThereOpponentPiece(p)){
            mat[p.getRow()][p.getColumn()] = true;
            }
            
            p.setValues(position.getRow() +1 , position.getColumn() +1 ); //Testar se existe peça na diagonal Esquerda do peão para capturar peça adversária
            if(getBoard().positionExists(p) && isThereOpponentPiece(p)){
            mat[p.getRow()][p.getColumn()] = true;
            }
            
            //Special Move enPassant BLACK
            if(position.getRow() == 4){
                Position left = new Position(position.getRow(), position.getColumn() - 1);
                if(getBoard().positionExists(left) && isThereOpponentPiece(left) && getBoard().piece(left) == chessMatch.getEnPassantVulnerable()){
                    mat[left.getRow() + 1][left.getColumn()] = true;
                }
                Position right = new Position(position.getRow(), position.getColumn() + 1);
                if(getBoard().positionExists(right) && isThereOpponentPiece(right) && getBoard().piece(right) == chessMatch.getEnPassantVulnerable()){
                    mat[right.getRow() + 1][right.getColumn()] = true;
                }
            }
            
        }
        return mat;
    }
    @Override
    public String toString(){
        return "P";
    }
}
