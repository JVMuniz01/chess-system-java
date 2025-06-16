/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chess;

import boardgame.BoardException;

/**
 *
 * @author j040v
 */
public class ChessException extends BoardException{
    private static final long serialVersionUID = 1L;

    public ChessException(String msg){
        super(msg);
    }
}
