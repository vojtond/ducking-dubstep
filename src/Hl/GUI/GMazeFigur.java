/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hl.GUI;


import javax.swing.JLabel;
import Hl.model.Pokus;
import Hl.model.board.MazeFigur;
import java.util.Observable;
import java.util.Observer;
import javax.swing.ImageIcon;
/**
 *
 * @author Pikachu
 */

public class GMazeFigur extends JLabel {
    private Pokus game;
   public   MazeFigur figur;
   
   public GMazeFigur(Pokus game,MazeFigur figur){
        
        this.game=game;
        this.figur=figur;
        this.figur.addObserver(new GMazeFigur.GSObserver());
        ImageIcon icon=new ImageIcon("");
        
        icon=new ImageIcon("figur"+figur.player+".png");
       
        setIcon(icon);
        setIconTextGap(0);
        setBorder(null);
        setText(null);
        setSize(icon.getImage().getWidth(null),icon.getImage().getHeight(null));
       
        
    }
     private class GSObserver implements Observer{
       
       @Override 
       public void update(Observable o,Object arg){
           
           repaint();
       }
   }
}
