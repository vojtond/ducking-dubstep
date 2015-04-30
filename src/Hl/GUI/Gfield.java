/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hl.GUI;

import javax.swing.JLabel;

import Hl.model.Pokus;

import Hl.model.board.MazeCard;
import javax.swing.ImageIcon;

/**
 *
 * @author Pikachu
 */
public class Gfield extends JLabel {
 
    private int i;
    private int j;
    public MazeCard card;
   
   public Gfield(Pokus game,int i,int j){
        
        
        this.i=i;
        this.j=j;
        
      
        this.card=game.maze.get(i+1, j+1).getCard();
       
        this.setMyImage(this.card);
      
    }
   public void setMyImage(MazeCard card){
      
       ImageIcon icon=new ImageIcon("");
      
        if (card.canGo(MazeCard.CANGO.LEFT)){
          if (card.canGo(MazeCard.CANGO.RIGHT)){
              icon=new ImageIcon("LR.png");
          }
      }
        if (card.canGo(MazeCard.CANGO.UP)){
          if (card.canGo(MazeCard.CANGO.DOWN)){
              icon=new ImageIcon("LR90.png");
          }
      }  
      if (card.canGo(MazeCard.CANGO.LEFT)){
          if (card.canGo(MazeCard.CANGO.UP)){
              icon=new ImageIcon("LU.png");
          }
      }
      if (card.canGo(MazeCard.CANGO.RIGHT)){
          if (card.canGo(MazeCard.CANGO.UP)){
              icon=new ImageIcon("LU90.png");
          }
      }
      if (card.canGo(MazeCard.CANGO.RIGHT)){
          if (card.canGo(MazeCard.CANGO.DOWN)){
              icon=new ImageIcon("LU180.png");
          }
      }
      if (card.canGo(MazeCard.CANGO.LEFT)){
          if (card.canGo(MazeCard.CANGO.DOWN)){
              icon=new ImageIcon("LU270.png");
          }
      }
      if (card.canGo(MazeCard.CANGO.UP)){
          if (card.canGo(MazeCard.CANGO.RIGHT)){
             if (card.canGo(MazeCard.CANGO.LEFT)){
              
                icon=new ImageIcon("LUR.png");
             }
                 
             
          }
      }
       if (card.canGo(MazeCard.CANGO.UP)){
          if (card.canGo(MazeCard.CANGO.RIGHT)){
             if (card.canGo(MazeCard.CANGO.DOWN)){
              
                icon=new ImageIcon("LUR90.png");
             }
                 
             
          }
      }
        if (card.canGo(MazeCard.CANGO.DOWN)){
          if (card.canGo(MazeCard.CANGO.RIGHT)){
             if (card.canGo(MazeCard.CANGO.LEFT)){
              
                icon=new ImageIcon("LUR180.png");
             }
                 
             
          }
      }
      if (card.canGo(MazeCard.CANGO.UP)){
          if (card.canGo(MazeCard.CANGO.LEFT)){
             if (card.canGo(MazeCard.CANGO.DOWN)){
              
                icon=new ImageIcon("LUR270.png");
             }
                 
             
          }
      }
    
      setIcon(icon);
      setIconTextGap(0);
      setBorder(null);
      setText(null);
      setSize(icon.getImage().getWidth(null),icon.getImage().getHeight(null));
  
              }
  
  
    
}
