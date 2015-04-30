/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hl.model.board;

import Hl.model.treasure.Treasure;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
/**
 *
 * @author Pikachu
 */
/**
 * Třída popisující objekt typu MayeFigur který reprezentuje hráče 
 */ 
public class MazeFigur  extends Observable implements Serializable{
    public int x;
    public int y;
    private final MazeBoard maze;
    public Treasure treasure;
    public int playernumber;
    public String name;
    public String figurobr;
    public int winningondition;
    public List<Treasure> obtainedTreasure=new ArrayList<>();

    /**
     *metoda na přidání dosaženého pokladu
     * @param treasure
     */
    public void AddObtainedTreasure(Treasure treasure){
        obtainedTreasure.add(treasure);
        this.treasure=null;
        if (this.winningondition==obtainedTreasure.size()){
            System.out.print("\n\n"+"****win"+this.name+"\n\n\n");
        }
         setChanged();
       notifyObservers(treasure);
    }

    /**
     *
     * @param x
     * @param y
     * @param maze
     * @param playernumber
     * @param name
     * @param obr
     * @param winningcondition
     */
    public MazeFigur(int x,int y,MazeBoard maze,int playernumber,String name,String obr,int winningcondition){
       this.winningondition=winningcondition;
        this.x=x;
       this.y=y;
       this.maze=maze;
       this.treasure=null;
       this.playernumber=playernumber;
       this.name=name;
       this.figurobr=obr;
    }
    
    /**
     *změna pozice figurky při pohybu hrací deskou
     * @param x
     * @param y
     */
    public void changeShift(int x,int y){
      this.x=this.x+x;
      this.y=this.y+y;
      if (this.x<1 || this.x>maze.getDimension()){
          if (this.x<1){
            this.x=maze.getDimension();
          }else this.x=1;
      }
      if (this.y<1 || this.y>maze.getDimension()){
          if (this.y<1){
            this.y=maze.getDimension();
          }else this.y=1;
      }
         setChanged();
       notifyObservers(null);
      System.out.print("\n**"+this.x+" "+this.y+"**\n");
      
  }

    /**
     *pohyb hráče po desce
     * @param can
     * @return
     */
    public  boolean move(MazeCard.CANGO can){
     
      boolean move;
      move= false;
  
     if (maze.get(x, y).getCard().canGo(can)){
       
         if (can==MazeCard.CANGO.UP){
       
             if ( x-1>0 && maze.get(x-1, y).getCard().canGo(MazeCard.CANGO.DOWN)){
                x+=-1;
                move= true;
             }
         }else
         if (can==MazeCard.CANGO.DOWN){
          
               if ( x+1<=maze.getDimension() && maze.get(x+1, y).getCard().canGo(MazeCard.CANGO.UP)){
             x+=1;
             move= true;
               }
         }else
         if (can==MazeCard.CANGO.LEFT){
           
               if (y-1>0 && maze.get(x, y-1).getCard().canGo(MazeCard.CANGO.RIGHT)){
                y+=-1;
                move= true;
               }
         }else
         if (can==MazeCard.CANGO.RIGHT){
             
               if (y+1<=maze.getDimension() &&maze.get(x, y+1).getCard().canGo(MazeCard.CANGO.LEFT)){
             
                y+=1;
                move= true;
               }
         }
     } 
        setChanged();
        
       notifyObservers(null);
      
     return move;
  }
   
}
