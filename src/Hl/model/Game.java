/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hl.model;
import Hl.model.board.*;
import Hl.model.treasure.CardPack;
import Hl.model.treasure.Treasure;
import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Random;
/**
 *třída typu jedináček vytvářející hru
 * @author Pikachu
 */
public final class Game extends Observable implements Serializable {
    private final int height;
    private final int width;
    private final MazeBoard maze;
    private fazeofgame faze;
    private boolean hasShift;
    private final int cardSize;
    private final CardPack pack;
    private final int numberOfPlayer;
    private int playerOnTurn;
    private final int numberOfTreasure;

    /**
     *výčtový typ pro fázy hry
     * @see SHIFT
     *@see TURN    
     */
    public static enum fazeofgame{

        /**
         *Fáze hry ve které se může shiftovat
         */
        SHIFT,

        /**
         *Fáze hry pouze pro pohyb po bludišti
         */
        TURN;
    }

    private final List<MazeFigur> figura=new ArrayList<>();
    private MazeFigur figur;
    private static Game PPokus;

    /**
     *vrací pole všech figurek
     * @return Array list který obsahuje všechny figurky v aktuální hře
     *@see MazeFigur
     */
    public List<MazeFigur> getFigura(){
        return this.figura;
    }

    /**
     *
     * @return vrátí figurku která je zrovna na tahu
     * @see MazeFigur
     */
    public MazeFigur getFigur(){
        return this.figur;
    }
    /**
     *
     * @return vrací jestli v daném tahu byl změněn hrací plán
     */
    public boolean GetHasShift(){
    return this.hasShift;
}

    /**
     *
     * @return vrací velikost hrací karty v pixelech
     */
public int GetCardSize(){
    return this.cardSize;
}

    /**
     *
     * @return vrací počet hráčů v aktivní hře
     */
    public int GetNumberOfPlayer(){
    return this.numberOfPlayer;
}

    /**
     *
     * @param shift nastavuje použití změny hracího pole v probíhajícím tahu
     */
    public void SetHasShift(boolean shift){
    this.hasShift=shift;
}

    /**
     *Mění fázy probíhajícího tahu
     * @param faze - výčtový typ <code>fazeofgame</code>
     * @see fazeofgame
     */
    public void setFaze(fazeofgame faze){
    this.faze=faze;
}

    /**
     * @return vrací fázy probíhajícího tahu
     * @see fazeofgame
     */
    public fazeofgame getFaze(){
    return this.faze;
}

    /**
     *
     * @return vrací aktuální výšku okna
     */
    public int getHeight(){
    return this.height;
}

    /**
     *
     * @return vrací aktuální šířku okna
     */
    public int getWidth(){
    return this.width;
}

    /**
     *
     * @return vrací herní desku pro probíhající hru
     */
    public MazeBoard getBoard(){
    return this.maze;
}

    /**
     *
     * @return vrací počet pokladů vyhenerovaných na začátku hry
     */
    public int getNumberOfTreasure(){
    return this.numberOfTreasure;
}

    /**
     *
     * @return vrací aktuální balíček pokladů ze kterých je možno vybírat
     * @see CardPack
     */
    public CardPack getPack(){

    CardPack pom=this.pack;
    
    return pom;
}

    /**
     * 
     * @param numberOfPlayer -počet hráčů při zapínání hry
     * @param dimension -   rozměr hrací desky
     * @param figuraName - pole názvů hráčů
     * @param figuraObr - pole obrázků figurek hráčů
     * @param numberOfTreasure  - počet pokladů při zapínání hry
     * @return vtvoří novou nebo vratí probíhající hru 
     */
    public static Game NewPokus(int numberOfPlayer,int dimension,List<String> figuraName,List<String> figuraObr,int numberOfTreasure){
        //  if (PPokus==null)
            PPokus=new Game(numberOfPlayer,dimension,figuraName,figuraObr,numberOfTreasure);
          return PPokus;
      }
    private Game(int numberOfPlayer,int dimension,List<String> figuraName,List<String> figuraObr,int numberOfTreasure){
        this.cardSize=60;
        this.numberOfTreasure=numberOfTreasure;
         Treasure.createSet();
        maze=MazeBoard.createMazeBoard(dimension);
        figura.clear();
        maze.newGame();
        this.playerOnTurn=-1;
        int winningCondition=numberOfTreasure/numberOfPlayer;  
        System.out.print("win:\n"+winningCondition+"\n");
        if (numberOfPlayer>=1)
                
                figura.add(new MazeFigur(1,1,maze,0,figuraName.get(0),figuraObr.get(0),winningCondition));
               
        if (numberOfPlayer>=2)   
               
                figura.add(new MazeFigur(1,maze.getDimension(),maze,1,figuraName.get(1),figuraObr.get(1),winningCondition));
               
        if (numberOfPlayer>=3)
              
                figura.add(new MazeFigur(maze.getDimension(),1,maze,2,figuraName.get(2),figuraObr.get(2),winningCondition));
        if (numberOfPlayer>=4)
            figura.add(new MazeFigur(maze.getDimension(),maze.getDimension(),maze,3,figuraName.get(3),figuraObr.get(3),winningCondition));
        this.numberOfPlayer=numberOfPlayer;
        height=(this.cardSize)*maze.getDimension()+(this.numberOfPlayer*60)+(2*this.cardSize)+1;        
        width=this.cardSize*maze.getDimension()+ cardSize*2+2;
          
       
        pack=new CardPack(this.numberOfTreasure,this.numberOfTreasure);
        pack.shuffle();
       
        changePlayer();
        this.figur=figura.get(this.playerOnTurn);
        maze.setFigura(figura);
        Random random=new Random();
        int x=0;
        int y=0;
       
        for (int i=0;i<this.numberOfTreasure;i++){
            boolean obsazeno=true;           
           while (obsazeno){  
                x=random.nextInt(maze.getDimension())+1;
                y=random.nextInt(maze.getDimension())+1;
                if (maze.get(x, y).getCard().getTreasure()==null){
                    obsazeno=false;
                }
           }
        
           maze.get(x, y).getCard().putTreasure(Treasure.getTreasure(i));
        }
    }

    /**
     *změní který hráč je na tahu
     */
    public void changePlayer(){
    
        if(!this.GetHasShift()){
            this.maze.setLastShift(null);
        } 
        this.SetHasShift(false);
        if (this.playerOnTurn<this.numberOfPlayer-1){
            playerOnTurn++;
        }else playerOnTurn=0;
        faze=Game.fazeofgame.SHIFT;
         figur=figura.get(this.playerOnTurn);
      
      
          if (figur.treasure==null){ 
                                
                      
                        figur.treasure=this.getPack().popCard().TreasureOnCard;
          }
      
            setChanged();
            notifyObservers(figur);
         
            
    }
  
        
    
}
