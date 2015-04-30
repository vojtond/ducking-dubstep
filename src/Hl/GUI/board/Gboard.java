/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hl.GUI.board;
import Hl.model.Game;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JPanel;

import Hl.model.board.MazeBoard;


/**
 *vytváří objekt který popisuje grafiku objektů typu MazeBoard, implementuje Návrhový vzor Jedináček. 
 * @see Board
 * @author Pikachu
 */
public  final class Gboard extends JPanel{
    private final GCard[][] label;
    private final Dimension dimB; 
    private final MazeBoard maze;
    private static Gboard GGboard;
    
    /**
     *pokud grafika hrací desky neexistuje vytvoří nový objekt, pokud již takový objekt existuje, vrátí jej 
     * @param game
     * @return grafiku hrací desky
     *@see Game
     */
    public static Gboard newGboard(Game game){  
      //  if (GGboard==null){
            GGboard=new Gboard(game);
        //}
      
        return GGboard;
    }
   private Gboard(Game game){
      
    
        this.maze=game.getBoard();
            
        label=new GCard[game.getBoard().getDimension()][game.getBoard().getDimension()];
        GridLayout lay =new GridLayout(game.getBoard().getDimension(),game.getBoard().getDimension());
        this.setLayout(lay);
        dimB=new Dimension (game.getBoard().getDimension()*game.GetCardSize(),game.GetCardSize()*game.getBoard().getDimension());
        this.setPreferredSize(dimB);
        this.setSize(dimB);
         this.setLocation(game.getHeight()-(this.getHeight())-game.GetCardSize()-1,1+game.GetCardSize());
        this.setBackground(Color.LIGHT_GRAY);
         GCard.setDimension(new Dimension(game.GetCardSize(),game.GetCardSize()));
        for(int i=0;i<game.getBoard().getDimension();i++){
            for(int j=0;j<game.getBoard().getDimension();j++){   
                label[i][j]=new GCard(game,game.getBoard().get(i+1, j+1).getCard(),i+1,j+1);  
       
                this.add(label[i][j]);
                 
            }
        }

        this.setOpaque(true);
       repaint();
    }

    /**
     *
     * @return vrací herní pole které přísluší jeho grafické reprezentaci
     * @see MazeBoard
     */
    public MazeBoard getBoard(){
        return this.maze;
    }

    /**
     * 
     * @param i
     * @param j
     * @return vrací objekt typu GCard na pozici i-tý řádek a j-tý sloupec
     * @see GCard
     */
    public  GCard getField(int i,int j){
        return label[i][j];
    } 
}
