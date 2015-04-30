/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hl.model.board;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Observable;


/**
 *Vytváří objekty reprezentující hrací desku, implementuje návrhový vzor Jedináček
 * @author Pikachu
 */
public  class MazeBoard extends Observable implements Serializable{
  private final MazeField[][] Maze; 
  private int dimension;
  private  MazeCard freeCard;
  private static MazeBoard maze;
  private  List<MazeFigur> figura=new ArrayList<>();

    /**
     *vytváří nebo vrací již existující instanci typu MazeBoard
     * @param n - rozměr hrací desky, přípustné hodnoty jsou : 5,7,9,11
     * @return vrací herní desku
     */
    public static MazeBoard createMazeBoard(int n){
     // if (maze==null){
      maze=new MazeBoard(n);
    
      //}
      return maze;
  }
  private MazeBoard(int n){
      Maze=new MazeField[n+1][n+1];
      dimension=n;
      
      for (int i=1;i<=n;i++){
          for (int j=1;j<=n;j++){
              Maze[i][j]=new MazeField(i,j);
          }
      
      }
  }

    /**
     *vytvoří novou hru, podle pravidel pro generaci, vygeneruje přesný počet instancí třídy <code> MazeCard</code>
     *<P>
     * zároveň vztvoří jednu instanci <code>MazeCard</code> navíc, tato slouží jako volná karta pro úpravu bludiště
     * @see MazeCard
     */
    public void newGame(){
      Random random=new Random();
      Random rrotate=new Random();
       switch (random.nextInt(3)){
                  case 0:
                      freeCard=MazeCard.create("C");
                  break;
                  case 1:
                      freeCard=MazeCard.create("L");
                  break;
                  case 2:
                      freeCard=MazeCard.create("F");
                  break;
              }
       rotate(freeCard,rrotate.nextInt(3));
      for (int i=1;i<=dimension;i++){
          for (int j=1;j<=dimension;j++){
            if ((i==1 && j==1) || (i==1 && j==dimension) || (i==dimension && j==1) || (i==dimension && j==dimension)){
                  Maze[i][j].putCard(MazeCard.create("C"));
                  if(i==1 && j==1){
                      Maze[i][j].getCard().position=1;
                      rotate(Maze[i][j].getCard(),2);
         
                  }else
                  if(i==1 && j==dimension){
                       Maze[i][j].getCard().position=2;
                      rotate(Maze[i][j].getCard(),3);
                     
                  }else
                  if(i==dimension && j==1){
                       Maze[i][j].getCard().position=3;
                      rotate(Maze[i][j].getCard(),1);
                        
                  }else
                   if(i==dimension && j==dimension){
                       Maze[i][j].getCard().position=4;
                    
                        
                  }   
              }else
              if(i %2==1 && j%2==1){
                Maze[i][j].putCard(MazeCard.create("F"));
                if(i==1){
                    rotate(Maze[i][j].getCard(),2);
                }else
                if(j==1){
                    rotate(Maze[i][j].getCard(),1);
                }else
                if(j==dimension){
                    rotate(Maze[i][j].getCard(),3); 
                }
              }else{
              switch (random.nextInt(3)){
                  case 0:
                      Maze[i][j].putCard(MazeCard.create("C"));
                  break;
                  case 1:
                      Maze[i][j].putCard(MazeCard.create("L"));
                  break;
                  case 2:
                      Maze[i][j].putCard(MazeCard.create("F"));
                  break;
              }
               rotate(Maze[i][j].getCard(),rrotate.nextInt(3)); 
               
              }
          }
      
      }
     
  }

    /**
     *pomocí indexu řádku a sloupce, získá jedno políčko z hrací desky
     * @param r index řádku
     * @param c index sloupce
     * @return 
     */
    public MazeField get(int r,int c){
      if (r<=dimension && c<=dimension){
          return Maze[r][c];
      }else return null;
      
  }
  private static  void rotate(MazeCard m,int i){
      
      
      for (int j=0;j<i;j++){
          m.turnRight();
         
      }
       
  }

    /**
     *vrací aktuální volnou kartu, tato karta se mění po každém upravení hrací desky
     * @return volnou kartu, která je instancí třídy <code>MazeCard</code>
     *@see MazeCard
     */
  
    public MazeCard getFreeCard(){
          return freeCard;    
  }
  private MazeField lastshift=null;

    /**
     *provede vložení aktuální volné karty na políčku určené argumentem <code>mf</code> a tím dojde k posunutí celého řádku či sloupce
     * @param mf políčko na které se má vložit volný kámen, přípustné políčka je jen každé poláčko které je na okraji hracího plánu a zároveň je sudé
     * @return zda změna herního plánu byla provedena
     * @see MazeField
    
     */
    public boolean shift (MazeField mf){
      if (this.lastshift!=null) {
          if ((mf.row()==this.dimension && this.lastshift.row()==1)||(mf.row()==1 && this.lastshift.row()==this.dimension)){
              if (mf.col()==this.lastshift.col()){
                  return false;
              }
          }
          if  ((mf.col()==this.dimension && this.lastshift.col()==1)||(mf.col()==1 && this.lastshift.col()==this.dimension)){
              if (mf.row()==this.lastshift.row()){
                  return false;
              }
          }
          
      }

        this.lastshift=mf;
      MazeCard pom=freeCard;
      if (mf.row()==1 &&( mf.col()%2==0)){
          pom=Maze[dimension][mf.col()].getCard();
          for (MazeFigur item : figura) {
            if (item.y==mf.col()){
                item.changeShift(1, 0);
            }
          }
          for (int i=dimension-1;i>0;i--){
              Maze[i+1][mf.col()].putCard(Maze[i][mf.col()].getCard());
          }
          Maze[1][mf.col()].putCard(freeCard);
          freeCard=pom;
      }else 
      if (mf.row()==dimension &&( mf.col()%2==0)){ 
         
           for (MazeFigur item : figura) {
            if (item.y==mf.col()){
                item.changeShift(-1, 0);
            }
          }
          pom=Maze[1][mf.col()].getCard();         
          for (int i=1;i<dimension;i++){
              Maze[i][mf.col()].putCard(Maze[i+1][mf.col()].getCard());
          }
          Maze[dimension][mf.col()].putCard(freeCard);
          freeCard=pom;
      }else
      if (mf.col()==1 &&( mf.row()%2==0)){
          pom=Maze[mf.row()][dimension].getCard();  
       
           for (MazeFigur item : figura) {
            if (item.x==mf.row()){
                item.changeShift(0, 1);
            }
          }
          for (int i=dimension-1;i>0;i--){
              Maze[mf.row()][i+1].putCard(Maze[mf.row()][i].getCard());
          }
          Maze[mf.row()][1].putCard(freeCard);
          freeCard=pom;
      }else
       if (mf.col()==dimension &&( mf.row()%2==0)){
     
          for (MazeFigur item : figura) {
            if (item.x==mf.row()){
                item.changeShift(0, -1);
            }
          }
          pom=Maze[mf.row()][1].getCard();         
          for (int i=1;i<dimension;i++){
              Maze[mf.row()][i].putCard(Maze[mf.row()][i+1].getCard());
          }
          Maze[mf.row()][dimension].putCard(freeCard);
          freeCard=pom;
        
       }
      
           setChanged();
       notifyObservers(this.freeCard);
       return true;
  }

    /**
     *nastaví interní proměnnou <code>lastshift</code>, která určuje na kterém políčku bylo naposledy shiftováno, toto se používá pro zamezení reverzních tahů
     * @param mf - instance <code>MazeField</code>
     * @see MazeField
     */
    public void setLastShift(MazeField mf){
      this.lastshift=mf;
      
  }

    /**
     *
     * @return vrací rozměr herní desky aktuální hry
     */
    public int getDimension(){
      return this.dimension;
  }

    /**
     *nastaví hráče, kteří se nachazí na herní desce
     * @param figura  <code>ArrayList</code> figurek pro aktuální hru
     * @see MazeFigur
   */
  
    public void setFigura(List<MazeFigur> figura){
      this.figura=figura;
  }
}