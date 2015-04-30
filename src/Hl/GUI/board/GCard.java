/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hl.GUI.board;

import Hl.GUI.GTreasureOnCard;
import javax.swing.JLabel;

import Hl.model.Game;
import Hl.model.board.MazeCard;
import Hl.model.board.MazeField;
import java.awt.Dimension;
import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;
import javax.swing.ImageIcon;
import javax.swing.JLayeredPane;

/**
 *každý objekt reprezentuje jedno políčko ve hře
 * @author Pikachu
 */
public class GCard extends JLayeredPane implements Serializable{
 
   
    private MazeCard card;
    private ImageIcon icon;
    private final JLabel Gcard;
    private final JLabel Gtreasure;

    private  static Dimension dim;

    /**
     *nastaví dimensi pro objekty třídy GCard
     * @param dim
     */
    public static void setDimension(Dimension dim){
        GCard.dim=dim;
    }
    /**
     *vytvoří noý objekt typu GCard, tento Objekt je typu JLayeredPane a obsahuje na sobě kámen a popřípadě poklad
     * @param game aktuální hra
     * @param card nová karta na políčku
     * @param i řádek
     * @param j sloupec
     */
   
    public GCard(Game game,MazeCard card,int i,int j){
        
        this.setPreferredSize(dim);
        this.setSize(dim);
        this.setOpaque(true);
        this.setLayout(null);
        this.card=card;
        if (i>0 && j>0){
          
            game.getBoard().get(i, j).addObserver(new GCard.GSObserver());
        
        }
        this.icon=setMyImage(this.card);
        this.Gtreasure=new GTreasureOnCard();
      this.setLayout(null);
      if (card.getTreasure()!=null){
         
     
        this.Gtreasure.setSize(game.GetCardSize(), game.GetCardSize());
       this.Gtreasure.setIcon(GCard.setTreasureImage(card.getTreasure().getGCode()));
                
      }
  this.add(Gtreasure,2,0);
 
       this.Gcard=new JLabel();
     
      Gcard.setIcon(icon);
      Gcard.setIconTextGap(0);
      Gcard.setSize(game.GetCardSize(),game.GetCardSize());
      this.add(Gcard,1,0);
 
      
    }

    /**
     *vrací <code>Icon</code> podle zadaného argumentu getGCode(), cesta pro image se nastaví getGCode().png
     * @param GCode číslo obrázku pro poklad
     * @return vrací <code>Icon</code> podle getGCode()
     */
    public static  ImageIcon setTreasureImage(int GCode){
        ImageIcon icon=new ImageIcon(""+GCode+".png");
       
        return icon;
    }

    /**
     *aktualizuje či nastaví grafiku pokladu a kamene
     * @param card 
     * @see MazeCard
     */
    public  void setImage(MazeCard card){
       if (card.getTreasure()!=null){
           
            this.Gtreasure.setIcon(GCard.setTreasureImage(card.getTreasure().getGCode()));
             this.Gtreasure.setSize(Gtreasure.getIcon().getIconHeight(), Gtreasure.getIcon().getIconHeight());
           
         
        
       }else  {this.Gtreasure.setIcon(null);}
       this.card=card;
       this.icon=GCard.setMyImage(card);
       this.Gcard.setIcon(icon); 
       repaint();
   }
   private static ImageIcon setMyImage(MazeCard card){
      
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
         
           GCard.this.setImage(argu.getCard());

           //
       }
   }
   
  
    
}
