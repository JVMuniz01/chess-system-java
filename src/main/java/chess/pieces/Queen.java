/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

/**
 *
 * @author j040v
 */
public class Queen extends ChessPiece {

    public Queen(Board board, Color color) {
        super(color, board);
    }
    
    @Override
    public String toString(){
        return "Q";
    }
    @Override
    public boolean[][] possibleMoves() {
        boolean [][] mat = new boolean[getBoard().getRaws()][getBoard().getColumns()];
        
        Position p = new Position(0,0);
         
        
        //Above
        p.setValues(position.getRow() -1 ,position.getColumn());
        while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)){ //Enquanto a posição existir e não tiver o thereIsAPiece será true, movimento possivel
            mat[p.getRow()][p.getColumn()] = true;
            p.setRow(p.getRow()-1);  //sobe mais uma linha enquanto tiver casas vazias
        }
        if(getBoard().positionExists(p) && isThereOpponentPiece(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }
        
        //Left
        p.setValues(position.getRow(),position.getColumn() - 1);
        while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)){ 
            mat[p.getRow()][p.getColumn()] = true;
            p.setColumn(p.getColumn()-1);  
        }
        if(getBoard().positionExists(p) && isThereOpponentPiece(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }
        
        //Right
        p.setValues(position.getRow(),position.getColumn() + 1);
        while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)){ 
            mat[p.getRow()][p.getColumn()] = true;
            p.setColumn(p.getColumn()+1);  
        }
        if(getBoard().positionExists(p) && isThereOpponentPiece(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }
        
        //Below
        p.setValues(position.getRow() +1 ,position.getColumn());
        while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)){ //Enquanto a posição existir e não tiver o thereIsAPiece será true, movimento possivel
            mat[p.getRow()][p.getColumn()] = true;
            p.setRow(p.getRow()+1);  //sobe mais uma linha enquanto tiver casas vazias
        }
        if(getBoard().positionExists(p) && isThereOpponentPiece(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }
        //NW
        p.setValues(position.getRow() -1 ,position.getColumn() -1);
        while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)){ 
            mat[p.getRow()][p.getColumn()] = true;
            p.setValues(p.getRow() - 1, p.getColumn()-1);  
        }
        if(getBoard().positionExists(p) && isThereOpponentPiece(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }
        
        //NE
        p.setValues(position.getRow() -1,position.getColumn() + 1);
        while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)){ 
            mat[p.getRow()][p.getColumn()] = true;
            p.setValues(p.getRow()-1, p.getColumn() +1);  
        }
        if(getBoard().positionExists(p) && isThereOpponentPiece(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }
        
        //SE
        p.setValues(position.getRow() +1,position.getColumn() + 1);
        while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)){ 
            mat[p.getRow()][p.getColumn()] = true;
            p.setValues(p.getRow() +1, p.getColumn() +1);  
        }
        if(getBoard().positionExists(p) && isThereOpponentPiece(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }
        
        //SW
        p.setValues(position.getRow() +1 ,position.getColumn() -1);
        while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)){ 
            mat[p.getRow()][p.getColumn()] = true;
            p.setValues(p.getRow() +1,p.getColumn()-1);
        }
        if(getBoard().positionExists(p) && isThereOpponentPiece(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }
        
        return mat;
    }
}
