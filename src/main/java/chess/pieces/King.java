/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chess.pieces;

import boardgame.Board;
import chess.ChessPiece;
import chess.Color;

/**
 *
 * @author j040v
 */
public class King extends ChessPiece {
    
    public King(Board board, Color color) {
        super(color, board);
    }
    
    @Override
    public String toString(){
        return "K";
    }
    
}
