/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hl.GUI;
import Hl.GUI.board.GCard;

import Hl.model.Game;

import Hl.model.board.MazeCard;

import java.awt.Point;

import java.util.Observable;
import java.util.Observer;
import javax.swing.ImageIcon;


/**
 *
 * @author Pikachu
 */

public final class GFreeCard  {   
    private final GSObserver obs;
    private  Point pozice;
    private  MazeCard FreeCard;
    private final GCard Gfreecard;
    private static GFreeCard GGfreeCard;

    /**
     *vytvoří novou volnou hrací kartu popřípadě vrátí již existující
     * @param game
     * @return
     * @see Game
     */
    public static GFreeCard newGFreeCard(Game game){
       // if (GGfreeCard==null){
            GFreeCard.GGfreeCard=new GFreeCard( game);
        //}
        return GGfreeCard;
    
    }
    private GFreeCard(Game game){
       
        this.pozice=new Point(game.GetCardSize(),0);
        this.FreeCard=game.getBoard().getFreeCard();
        obs=new GSObserver();
        game.getBoard().addObserver(obs);
        game.getBoard().getFreeCard().addObserver(obs);
      
        
        this.Gfreecard=new GCard(game,FreeCard,0,0);
     
                Gfreecard.setLocation(this.pozice);
   

   
    }
     private class GSObserver implements Observer{
       
       @Override 
       public void update(Observable o,Object arg){
           
           MazeCard maze=(MazeCard)arg;
           GFreeCard.this.FreeCard.deleteObserver(obs);
           GFreeCard.this.FreeCard=maze;
           GFreeCard.this.FreeCard.addObserver(obs);
            setMyFreeImage();
           
       
         
       }
   }

    /**
     *
     * @return vrací volnou kartu
     * @see MazeCard
     */
    public MazeCard getFreeCard(){
         return this.FreeCard;
     }
     public void setMyFreeImage(){
        // this.FreeCard=game.maze.getFreeCard();
         Gfreecard.setImage(this.FreeCard);
 
         // this.Gfreecard.setImage(FreeCard);
         //   setIcon(icon);
         
     }

    /**
     *Nastaví novou poyici volné karty na hracím plánu
     * @param p -<code>Point</code>
     * @see Point
     */
    public void setLocation(Point p){
         this.pozice=p;
         this.Gfreecard.setLocation(pozice);
     }
      public void setLocation(int x,int y){
         this.pozice.x=x;
         this.pozice.y=y;
         this.Gfreecard.setLocation(pozice);
     }

    /**
     *
     * @return vrací bod na kterém se momentálně nachází volná karta
     */
    public  Point getLocation(){
        return this.pozice;
     }
    
    /**
     *
     * @return vrací grafikcé rozhraní volné karty
     * @see GCard
     */
    public GCard getGFreeCard(){
        return this.Gfreecard;
    }
    
  
    
}
