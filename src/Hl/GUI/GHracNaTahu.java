/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hl.GUI;

import Hl.model.Game;
import Hl.model.board.MazeField;
import Hl.model.board.MazeFigur;
import java.util.Observable;
import java.util.Observer;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author Pikachu
 */
public class GHracNaTahu extends JLabel {
    public   ImageIcon icon;
    public GHracNaTahu(Game game){
       this.setLocation(game.GetCardSize(), 0);
        this.setSize(150, 70);
             icon=new ImageIcon(game.getFigur().figurobr);
               this.setIcon(icon); 
        this.setText(game.getFigur().name);
       
        game.addObserver(new GHracNaTahu.GSObserver());
    }
    public void changeturn(MazeFigur figur){
      icon=new ImageIcon(figur.figurobr);
        this.setIcon(icon); 
        this.setText(figur.name);
    }
    private class GSObserver implements Observer{
       
       @Override 
       public void update(Observable o,Object arg){
           
          MazeFigur argu=(MazeFigur)arg;
          GHracNaTahu.this.changeturn(argu);
          // System.out.print("tady\n"+argu.row()+argu.col());
         
           //
       }
   }
}
