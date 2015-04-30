/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hl.GUI;
import Hl.GUI.Gboard;

import Hl.model.Pokus;
import Hl.model.board.MazeCard;
import Hl.model.board.MazeFigur;

import java.awt.Color;
import javax.swing.BorderFactory;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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
public  class GPokus extends JLayeredPane {
    
    public Gboard Gdeska;
    private  GFreeCard Gfree;
    private boolean isSetClick; 
    private boolean isFocus;
    private Point Freepoc;
    public JLabel tah;
   // public GMazeFigur Gfigur; 
    public List<GMazeFigur> Gfigura=new ArrayList<>();
    private Rectangle2D.Double[][] rect2;
    private Dimension dim; 
    private Pokus game; 
    private static GPokus GGPokus;
    public GTreasureCard Gtreasurecard;
    public GObtainedTreasure[] gobtainedtreasure;
    public static GPokus newGPokus(Pokus game){
         System.out.print("***"+game.getBoard().get(2, 2).getCard().CardCanGo);
         
        GPokus.GGPokus=new GPokus(game);
       
        return GGPokus;
        
    }
    private GPokus(Pokus game){
        double ratio,rH,rW;
        int gH,gW;
       this.game=game;
        int height=game.getHeight();
        int width=game.getWidth();
        rH=(double)game.getHeight()/height;
        rW=(double)game.getHeight()/width;
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
        this.Gfree=GFreeCard.newGFreeCard(game);
       // this.Gfree.setOpaque(true);
        this.add(Gdeska,1,0);
        this.add(Gfree.Gfreecard,2,0);
        this.isFocus=false;
        this.Freepoc=new Point();  
        this.Gdeska.setLocation(this.getHeight()/2-(this.Gdeska.getHeight()/2),0+60);
        this.Freepoc.setLocation(game.CardSize, 0);
        this.Gfree.pozice=this.Freepoc;
        this.Gtreasurecard=new GTreasureCard(game);
        this.add(Gtreasurecard,1,0);
        height=game.CardSize;
        width=game.CardSize;
        rH=(double)game.CardSize/height;
        rW=(double)game.CardSize/width;
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
        rect2=new Rectangle2D.Double[game.getBoard().rozmer][game.getBoard().rozmer];
        for(int i=0;i<game.getBoard().rozmer;i++){
            for(int j=0;j<game.getBoard().rozmer;j++){
               rect2[i][j]=new Rectangle2D.Double();        
            }
        }
        gobtainedtreasure=new GObtainedTreasure[game.pocethrac];
       // System.out
        int posun=0;
        for(int i=0;i<game.pocethrac;i++){
            this.gobtainedtreasure[i]=new GObtainedTreasure(game,game.figura.get(i).name,i);
            this.gobtainedtreasure[i].setLocation(posun, 60);
            System.out.print( this.gobtainedtreasure[i].getLocation());
              this.add(this.gobtainedtreasure[i],0,0);
            /* switch(i){
                 case 0:
                     posun+=60;
                 break;
                      case 1:
                     posun=game.getWidth()-60;
                 break;
                          case 2:
                     posun=game.getWidth()-120;
                 break;
             }*/
              posun+=60;
        }
        
        MListener ml=new MListener();
        this.addMouseListener(ml);
        this.addMouseMotionListener(ml);
        

        for (MazeFigur item : game.figura) {     
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

    
    public void setClick(){
        for(int i=0;i<Gdeska.getBoard().rozmer;i++){
            for(int j=0;j<Gdeska.getBoard().rozmer;j++){
                this.rect2[i][j].setRect(Gdeska.getField(i, j).getX()+Gdeska.getX(),Gdeska.getY()+Gdeska.getField(i, j).getY(),60,60);
            }
        }    
    }
    private class MListener extends MouseAdapter{
        @Override
        public void mousePressed(MouseEvent e){
            
           if (Gfree.Gfreecard.contains(e.getX()-Gfree.Gfreecard.getX(), e.getY()-Gfree.Gfreecard.getY())&& game.getFaze()==0){
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
            if (isFocus && game.getFaze()==0){
                if (e.getButton()==MouseEvent.BUTTON1){
                    
                    Point p;
                    Gfree.pozice=e.getPoint();
                    Gfree.pozice.setLocation(e.getX()+game.CardSize/2, e.getY()+game.CardSize/2);
                    p=e.getPoint();
                    if (game.getFaze()==0){
                    for (int i=1;i<=Gdeska.getBoard().rozmer;i=i+Gdeska.getBoard().rozmer-1){
                        for (int j=2;j<Gdeska.getBoard().rozmer;j=j+2){                   
                            if (rect2[i-1][j-1].contains(Gfree.pozice)){
                               
                               game.setFaze(1);
                                Gdeska.getBoard().shift(Gdeska.getBoard().get(i, j));
                                if(i==1){
                                    p.setLocation(rect2[Gdeska.getBoard().rozmer-1][j-1].getX(), rect2[Gdeska.getBoard().rozmer-1][j-1].getY()+60);
                                }else
                                if(i==Gdeska.getBoard().rozmer){
                                    p.setLocation(rect2[0][j-1].getX(), rect2[0][j-1].getY()-game.CardSize);
                                }
                               
                            }
                            if (rect2[j-1][i-1].contains(Gfree.pozice)){
                                game.setFaze(1);
                                Gdeska.getBoard().shift(Gdeska.getBoard().get(j, i));
                                if(i==1){
                                    p.setLocation(rect2[j-1][Gdeska.getBoard().rozmer-1].getX()+game.CardSize, rect2[j-1][Gdeska.getBoard().rozmer-1].getY());
                                }else
                                if(i==Gdeska.getBoard().rozmer){
                                        p.setLocation(rect2[j-1][0].getX()-game.CardSize, rect2[j-1][0].getY());
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
            if(isFocus && game.getFaze()==0){ 
                Gfree.setLocation(e.getPoint());
                 
               
               
            }
          
        }
        
    
   
    }
    
}
