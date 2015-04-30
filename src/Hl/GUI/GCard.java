/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hl.GUI;

import javax.swing.JLabel;

import Hl.model.Pokus;
import Hl.model.board.MazeCard;
import Hl.model.board.MazeField;
import java.awt.Dimension;
import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;
import javax.swing.ImageIcon;
import javax.swing.JLayeredPane;

/**
 *
 * @author Pikachu
 */
public class GCard extends JLayeredPane implements Serializable{
 
   
    private MazeCard card;
    private ImageIcon icon;
    private JLabel Gcard;
    private JLabel Gtreasure;
    private Dimension dim; 
    public GCard(Pokus game,MazeCard card,int i,int j){
          double ratio,rH,rW;
        int gH,gW;
         int height=60;
        int width=60;
        rH=(double)60/60;
        rW=(double)60/60;
        ratio=rH>1 ? 1:rH;
        ratio=rW>1 ? ratio : ( rW > rH ? rH : rW);
        gH=(int)(height*ratio);
        gW=(int)(width*ratio);
        dim=new Dimension (gH,gW);
        this.setPreferredSize(dim);
        this.setSize(dim);
        this.setOpaque(true);
        this.setLayout(null);
        this.card=card;
        if (i>0 && j>0){
          
            game.getBoard().get(i, j).addObserver(new GCard.GSObserver());
        
        }
        this.icon=setMyImage(this.card);
        this.Gtreasure=new JLabel();
      this.setLayout(null);
      if (card.poklad!=null){
         
        this.Gtreasure.setText(""+card.poklad.Gcode);
        this.Gtreasure.setSize(60, 60);
       this.Gtreasure.setIcon(GCard.setTreasureImage(card.poklad.Gcode));
                
      }
  this.add(Gtreasure,2,0);
 
       this.Gcard=new JLabel();
     
      Gcard.setIcon(icon);
      Gcard.setIconTextGap(0);
      Gcard.setSize(60,60);
      this.add(Gcard,1,0);
 
      
    }
    public static  ImageIcon setTreasureImage(int Gcode){
        ImageIcon icon=new ImageIcon(""+Gcode+".png");
       
        return icon;
    }
   public  void setImage(MazeCard card){
       if (card.poklad!=null){
           
            this.Gtreasure.setIcon(GCard.setTreasureImage(card.poklad.Gcode));
             this.Gtreasure.setSize(Gtreasure.getIcon().getIconHeight(), Gtreasure.getIcon().getIconHeight());
           
         
        
       }else  {this.Gtreasure.setIcon(null);this.Gtreasure.setText("");}
       this.card=card;
       this.icon=GCard.setMyImage(card);
       this.Gcard.setIcon(icon); 
       repaint();
   }
   public static ImageIcon setMyImage(MazeCard card){
      
       ImageIcon icon=new ImageIcon("");
      if (card.position==1){
          icon=new ImageIcon("LN.png");
      }else
      if (card.position==2){
          icon=new ImageIcon("PN.png");
      }else
      if (card.position==3){
          icon=new ImageIcon("LD.png");
      }else
       if (card.position==4){
          icon=new ImageIcon("PD.png");
      }else{
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
       }
      return icon;
     
  
              }
  
    private class GSObserver implements Observer{
       
       @Override 
       public void update(Observable o,Object arg){
           
           MazeField argu=(MazeField)arg;
          // System.out.print("tady\n"+argu.row()+argu.col());
           GCard.this.setImage(argu.getCard());

           //
       }
   }
  
    
}
