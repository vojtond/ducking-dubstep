/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hl.GUI;
import Hl.model.Pokus;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import Hl.GUI.GCard;
import Hl.model.board.MazeBoard;


/**
 *
 * @author Pikachu
 */
public  final class Gboard extends JPanel{
    private GCard[][] label;
    private Dimension dimB; 
    private MazeBoard maze;
    private JLabel figur;
    private static Gboard GGboard;
    public static Gboard newGboard(Pokus game){  
        GGboard=new Gboard(game);
      
        return GGboard;
    }
   private Gboard(Pokus game){
       
    
        this.maze=game.getBoard();
            
        label=new GCard[game.getBoard().rozmer][game.getBoard().rozmer];
        GridLayout lay =new GridLayout(game.getBoard().rozmer,game.getBoard().rozmer);
        this.setLayout(lay);
        dimB=new Dimension (game.getBoard().rozmer*game.CardSize,game.CardSize*game.getBoard().rozmer);
        this.setPreferredSize(dimB);
        this.setSize(dimB);
        this.setBackground(Color.LIGHT_GRAY);
        for(int i=0;i<game.getBoard().rozmer;i++){
            for(int j=0;j<game.getBoard().rozmer;j++){   
                label[i][j]=new GCard(game,game.getBoard().get(i+1, j+1).getCard(),i+1,j+1);  
                
                this.add(label[i][j]);
                 
            }
        }

        this.setOpaque(true);
       repaint();
    }
    public MazeBoard getBoard(){
        return this.maze;
    }
    public  GCard getField(int i,int j){
        return label[i][j];
    } 
}
