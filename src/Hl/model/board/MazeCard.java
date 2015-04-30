/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hl.model.board;


import Hl.model.treasure.Treasure;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Observable;

/**
 * @author Pikachu
 * Každá instance popisuje chodbu v labyrintu, základní chodby které mouhou nastat:
 * chodba z leva doprava
 * chodba z leva nahoru
 * chodba z leva nahoru a zároveň doprava
 * každá z těchto základních chodeb můžebýt libovolně natočená
 */
public class MazeCard extends Observable implements Serializable {
    private ArrayList<CANGO> CardCanGo=new ArrayList<CANGO>();
    public int position;
     private Treasure poklad;

    /**
     *výčtový typ popisující jednu větev chodby na kartě
     */
    public static enum CANGO{

        /**
         *chodba obsahuje cestu doleva
         */
        LEFT,

        /**
         *chodba obsahuje cestu nahoru
         */
        UP,

        /**
         *chodba obsahuje cestu doprava
         */
        RIGHT,

        /**
         *chodba obsahuje cestu dolů
         */
        DOWN;
    }

    public static MazeCard create (String type){
        MazeCard nova=new MazeCard(type);
              
        return nova;
        
    }
    private MazeCard(String type){
        this.poklad=null;
       
        switch (type) {
         
             
            case "C":
                this.CardCanGo.add(CANGO.LEFT); 
                this.CardCanGo.add(CANGO.UP); 
                
            break;
            case "L":
                this.CardCanGo.add(CANGO.LEFT);  
               this.CardCanGo.add(CANGO.RIGHT);  
               
            break;
            case "F":
                  this.CardCanGo.add(CANGO.LEFT); 
                  this.CardCanGo.add(CANGO.UP); 
                  this.CardCanGo.add(CANGO.RIGHT);
            break;
            default:
                throw new IllegalArgumentException("blop");
        }
    }
    public boolean canGo(MazeCard.CANGO dir){
        return CardCanGo.contains(dir);
    }
    public void turnRight(){
         
      ArrayList<CANGO> PomCardCanGo=new ArrayList<CANGO>();
      if (  CardCanGo.contains(CANGO.UP)){
          CardCanGo.remove(CANGO.UP);
          PomCardCanGo.add(CANGO.RIGHT);
      }
      if (  CardCanGo.contains(CANGO.RIGHT)){
          CardCanGo.remove(CANGO.RIGHT);
          PomCardCanGo.add(CANGO.DOWN);
      }
          if (  CardCanGo.contains(CANGO.DOWN)){
          CardCanGo.remove(CANGO.DOWN);
          PomCardCanGo.add(CANGO.LEFT);
      }
      if (  CardCanGo.contains(CANGO.LEFT)){
          CardCanGo.remove(CANGO.LEFT);
          PomCardCanGo.add(CANGO.UP);
      }
      CardCanGo=PomCardCanGo;
      setChanged();
       notifyObservers(this);
    }

    /**
     *vloží na kartu poklad
     * @param poklad nový poklad nacházející se na kartě
     */
    public void putTreasure(Treasure poklad){
        this.poklad=poklad;
  
    }

    /**
     *vratí poklad který se nachází na kartě
     * @return <code>Treasure</code> nebo null
     * @see Treasure
     */
    public Treasure getTreasure(){
        return this.poklad;
    }

}
