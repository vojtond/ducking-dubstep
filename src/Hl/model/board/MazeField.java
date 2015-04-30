/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hl.model.board;

import Hl.model.treasure.Treasure;
import java.io.Serializable;
import java.util.Observable;

/**
 *
 * @author Pikachu
 */

public class MazeField extends Observable implements  Serializable{
    private int row;
    private int col;
    private MazeCard Rock;
   
    public int row(){
        return row;
    } 
    public int col(){
        return col;
    }
    public MazeField(int row, int col){
        this.row=row;
        this.col=col;
        
    }
    public void putCard(MazeCard c){
       
        Rock=c;
           setChanged();
       notifyObservers(this);
    }
    public MazeCard getCard(){
        return Rock;
    }
  
}
