/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hl.GUI;

import Hl.model.Game;
import Hl.model.treasure.Treasure;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Pikachu
 */

public class GObtainedTreasure extends JPanel {
    public List<JLabel> TreasureLabel=new ArrayList<>();
    public GObtainedTreasure(Game game,String name,int figur){
        
        this.setPreferredSize(new Dimension(60,game.getWidth()-60));
        this.setSize(new Dimension(60,game.getWidth()-60));
        this.setOpaque(true);
        this.setLayout(null);
      GridLayout  g=new GridLayout((game.getNumberOfTreasure()/game.GetNumberOfPlayer())+1,1);
  
        this.setLayout(g);
        this.setBackground(Color.WHITE);
   
      
        JLabel nam=new JLabel(name);
        nam.setHorizontalAlignment(JLabel.CENTER);
        this.add(nam);
        for (Treasure item : game.getFigura().get(figur).obtainedTreasure) {  
            this.addTresure(item);
       
         } 
        
         game.getFigura().get(figur).addObserver(new GObtainedTreasure.GSObserver());
           
// this.setLocation(0, 60);
    }
    private void addTresure(Treasure treasure){
        ImageIcon icon=new ImageIcon("kameny\\"+treasure.getGCode()+".png");
        JLabel label=new JLabel();
        label.setIcon(icon);
        label.setSize(30,30);
       label.setHorizontalAlignment(JLabel.CENTER);
        this.add(label);
        TreasureLabel.add(label);
    }
     public class GSObserver implements Observer{
       
       @Override 
       public void update(Observable o,Object arg){
           System.out.print("blop\n");
           if (arg!=null){
           Treasure argu=(Treasure)arg;
           GObtainedTreasure.this.addTresure(argu);
           }
          // System.out.print("tady\n"+argu.row()+argu.col());
        

           //
       }
   }
  
}
