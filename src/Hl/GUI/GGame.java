/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hl.GUI;
import Hl.GUI.board.GMazeFigur;
import Hl.GUI.board.GTreasureCard;
import Hl.GUI.board.Gboard;

import Hl.model.Game;
import Hl.model.board.MazeFigur;

import java.awt.Color;
import javax.swing.BorderFactory;
import java.awt.Dimension;
import java.awt.Graphics;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
/**
 *
 * @author Pikachu
 */
public  class GGame extends JLayeredPane {
    
    /**
     *grafické rozhraní popisující herní desku
     * @see Gboard
     */
    public Gboard Gdeska;
    private final  GFreeCard Gfree;
    private boolean isSetClick; 
    private boolean isFocus;
    private final Point Freepoc;
    private final JLabel tah;
    private final List<GMazeFigur> Gfigura=new ArrayList<>();
    private final Rectangle2D.Double[][] rect2;
    private Dimension dim; 
    private final Game game; 
    private static GGame GGPokus;
    private final GTreasureCard Gtreasurecard;
    private final GObtainedTreasure[] gobtainedtreasure;

    /**
     *
     * @param game 
     * @return vytváří nové GUI hry, pokud existuje, vrací existující
     * @see Game
     */
    public static GGame newGPokus(Game game){
         
       // if(GGPokus==null) {
            GGame.GGPokus=new GGame(game);
        //}
       
        return GGPokus;
        
    }
    private GGame(Game game){
        double ratio,rH,rW;
        int gH,gW;
       this.game=game;
        int height=game.getHeight();
        int width=game.getWidth();
        rH=(double)game.getHeight()/height;
        rW=(double)game.getWidth()/width;
        ratio=rH>1 ? 1:rH;
        ratio=rW>1 ? ratio : ( rW > rH ? rH : rW);
        gH=(int)(height*ratio);
        gW=(int)(width*ratio);
        dim=new Dimension (gH,gW);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.setPreferredSize(dim);
        this.setSize(dim);
        this.setBackground(Color.white);
        this.setOpaque(true);
        this.isSetClick=false;  
        this.setLayout(null);
        
       
      
        
        this.Gdeska=Gboard.newGboard(game);
        System.out.print("za\n");
        this.Gfree=GFreeCard.newGFreeCard(game);
        
        this.add(Gdeska,1,0);
        this.add(Gfree.getGFreeCard(),2,0);
        this.isFocus=false;
        this.Freepoc=new Point();  
        
        this.Freepoc.setLocation(game.getHeight()-game.GetCardSize()-1, 1);
        this.Gfree.setLocation(this.Freepoc);
        this.Gtreasurecard=new GTreasureCard(game);
        this.add(Gtreasurecard,1,0);
        height=game.GetCardSize();
        width=game.GetCardSize();
        rH=(double)game.GetCardSize()/height;
        rW=(double)game.GetCardSize()/width;
        ratio=rH>1 ? 1:rH;
        ratio=rW>1 ? ratio : ( rW > rH ? rH : rW);
        gH=(int)(height*ratio);
        gW=(int)(width*ratio);
        dim=new Dimension (gH,gW);
        this.Gtreasurecard.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.Gtreasurecard.setPreferredSize(dim);
        this.Gtreasurecard.setSize(dim);
        this.Gtreasurecard.setBackground(Color.WHITE);
        this.Gtreasurecard.setLocation(0,0);
        rect2=new Rectangle2D.Double[game.getBoard().getDimension()][game.getBoard().getDimension()];
        for(int i=0;i<game.getBoard().getDimension();i++){
            for(int j=0;j<game.getBoard().getDimension();j++){
               rect2[i][j]=new Rectangle2D.Double();        
            }
        }
       
        gobtainedtreasure=new GObtainedTreasure[game.GetNumberOfPlayer()];
       // System.out
        int posun=0;
        for(int i=0;i<game.GetNumberOfPlayer();i++){
            this.gobtainedtreasure[i]=new GObtainedTreasure(game,game.getFigura().get(i).name,i);
            this.gobtainedtreasure[i].setLocation(posun, 60);
   
              this.add(this.gobtainedtreasure[i],0,0);
         
              posun+=60;
        }
        
        MListener ml=new MListener();
        this.addMouseListener(ml);
        this.addMouseMotionListener(ml);
        

        for (MazeFigur item : game.getFigura()) {     
          GMazeFigur  Gfigur=new GMazeFigur(game, item);
            this.add(Gfigur,3,0);
            Gfigura.add(Gfigur);
          } 
          tah=new GHracNaTahu(game);
        
          this.add(tah,5,0);
        repaint();
   }
  
  
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        if (!isSetClick){
            this.setClick();
            isSetClick=true;
        }
       
        for (GMazeFigur item : Gfigura) {
            item.setLocation(Gdeska.getField(item.figur.x-1, item.figur.y-1).getX()+Gdeska.getX(),Gdeska.getField(item.figur.x-1, item.figur.y-1).getY()+Gdeska.getY());
        }           
    }

    
    private void setClick(){
        for(int i=0;i<Gdeska.getBoard().getDimension();i++){
            for(int j=0;j<Gdeska.getBoard().getDimension();j++){
                this.rect2[i][j].setRect(Gdeska.getField(i, j).getX()+Gdeska.getX(),Gdeska.getY()+Gdeska.getField(i, j).getY(),60,60);
            }
        }    
    }
    private class MListener extends MouseAdapter{
        @Override
        public void mousePressed(MouseEvent e){
            
           if (Gfree.getGFreeCard().contains(e.getX()-Gfree.getGFreeCard().getX(), e.getY()-Gfree.getGFreeCard().getY())&& game.getFaze()==Game.fazeofgame.SHIFT){
               if (e.getButton()==MouseEvent.BUTTON3){
                   Gdeska.getBoard().getFreeCard().turnRight();
                   
                   repaint();
                   isFocus=false;
               }else
              isFocus=true;
           }else isFocus=false;
        }
        @Override
        public void mouseReleased(MouseEvent e){            
            if (isFocus && game.getFaze()==Game.fazeofgame.SHIFT){
                if (e.getButton()==MouseEvent.BUTTON1){
                    
                    Point p;
                    Gfree.setLocation(e.getPoint());
                    Gfree.setLocation(e.getX()+game.GetCardSize()/2, e.getY()+game.GetCardSize()/2);
                    p=e.getPoint();
                    if (game.getFaze()==Game.fazeofgame.SHIFT){
                    for (int i=1;i<=Gdeska.getBoard().getDimension();i=i+Gdeska.getBoard().getDimension()-1){
                        for (int j=2;j<Gdeska.getBoard().getDimension();j=j+2){                   
                            if (rect2[i-1][j-1].contains(Gfree.getLocation())){
                               
                               
                                if (Gdeska.getBoard().shift(Gdeska.getBoard().get(i, j))){
                                    game.SetHasShift(true);
                                    game.setFaze(Game.fazeofgame.TURN);
                                    if(i==1){
                                    p.setLocation(rect2[Gdeska.getBoard().getDimension()-1][j-1].getX(), rect2[Gdeska.getBoard().getDimension()-1][j-1].getY()+60);
                                }else
                                if(i==Gdeska.getBoard().getDimension()){
                                    p.setLocation(rect2[0][j-1].getX(), rect2[0][j-1].getY()-game.GetCardSize());
                                }}
                               
                            }
                            if (rect2[j-1][i-1].contains(Gfree.getLocation())){
                                
                                if (Gdeska.getBoard().shift(Gdeska.getBoard().get(j, i))){
                                    game.SetHasShift(true);
                                    game.setFaze(Game.fazeofgame.TURN);
                                    if(i==1){
                                    p.setLocation(rect2[j-1][Gdeska.getBoard().getDimension()-1].getX()+game.GetCardSize(), rect2[j-1][Gdeska.getBoard().getDimension()-1].getY());
                                }else
                                if(i==Gdeska.getBoard().getDimension()){
                                        p.setLocation(rect2[j-1][0].getX()-game.GetCardSize(), rect2[j-1][0].getY());
                                }
                                }
                                
                            }
                          
                        }
                    }
               
                    Gfree.setLocation(p);
                    }else {Gfree.setLocation(Freepoc);repaint();}
                    
                }
            }
        }
         @Override
        public void mouseDragged(MouseEvent e){
            if(isFocus && game.getFaze()==Game.fazeofgame.SHIFT){ 
                Gfree.setLocation(e.getPoint());
                 
               
               
            }
          
        }
        
    
   
    }
    
}
